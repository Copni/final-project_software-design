package userroles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final int id;
    private final String email;
    private String passwordHash;
    private UserStatus status;
    private UserProfile profile;
    private final List<Role> roles = new ArrayList<>();

    public User(int id, String email, String passwordHash, UserProfile profile) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profile = profile;
        this.status = UserStatus.ACTIVE;
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }
}
