package userroles;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        UserRepository repository = new InMemoryUserRepository();
        PasswordHasher hasher = new SimplePasswordHasher();
        SessionManager sessionManager = new SessionManager();

        AuthService authService = new AuthService(repository, hasher, sessionManager);
        UserService userService = new UserService(repository, hasher);
        RoleService roleService = new RoleService(repository, new RoleFactory());

        User user = authService.registerAccount(
                "alice@example.com",
                "secret",
                new UserProfile("Alice", "Patient", "+330100000001", "Paris")
        );

        Map<String, String> roleData = new HashMap<>();
        roleData.put("insuranceNumber", "INS-123");
        Role role = roleService.assignRole(user.getId(), "PATIENT", roleData);

        Session session = authService.login("alice@example.com", "secret");
        userService.changePassword(user.getId(), "secret", "new-secret");

        System.out.println("Logged in with session: " + session.getToken());
        System.out.println("Assigned role: " + role.getName() + " " + role.getPermissions());

        authService.logout(session);
        System.out.println("Session valid after logout: " + session.isValid());
    }
}
