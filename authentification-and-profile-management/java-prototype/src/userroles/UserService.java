package userroles;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public void updateProfile(int userId, UserProfile profileData) {
        User user = findUser(userId);
        user.setProfile(profileData);
        userRepository.save(user);
    }

    public void changePassword(int userId, String oldPassword, String newPassword) {
        User user = findUser(userId);
        if (!passwordHasher.verify(oldPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }
        user.setPasswordHash(passwordHasher.hash(newPassword));
        userRepository.save(user);
    }

    private User findUser(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }
}
