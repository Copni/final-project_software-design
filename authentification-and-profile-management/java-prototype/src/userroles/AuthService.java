package userroles;

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final SessionManager sessionManager;
    private int nextId = 1;

    public AuthService(UserRepository userRepository, PasswordHasher passwordHasher, SessionManager sessionManager) {
        // DIP: this service depends on interfaces, not concrete implementations.
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.sessionManager = sessionManager;
    }

    public User registerAccount(String email, String password, UserProfile profile) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }
        User user = new User(nextId++, email, passwordHasher.hash(password), profile);
        userRepository.save(user);
        return user;
    }

    public Session login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unknown email."));
        if (!user.isActive() || !passwordHasher.verify(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials.");
        }
        return sessionManager.createSession(user);
    }

    public void logout(Session session) {
        sessionManager.invalidateSession(session);
    }

    public void resetPassword(String email) {
        System.out.println("Password reset requested for " + email);
    }
}
