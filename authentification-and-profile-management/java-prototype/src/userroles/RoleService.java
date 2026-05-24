package userroles;

import java.util.Map;

public class RoleService {
    private final UserRepository userRepository;
    private final RoleFactory roleFactory;

    public RoleService(UserRepository userRepository, RoleFactory roleFactory) {
        this.userRepository = userRepository;
        this.roleFactory = roleFactory;
    }

    public Role assignRole(int userId, String roleType, Map<String, String> roleData) {
        User user = findUser(userId);
        Role role = roleFactory.createRole(roleType, roleData);
        user.addRole(role);
        userRepository.save(user);
        return role;
    }

    public void removeRole(int userId, Role role) {
        User user = findUser(userId);
        user.removeRole(role);
        userRepository.save(user);
    }

    private User findUser(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }
}
