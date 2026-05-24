package notification;

public class User {
    private final int id;
    private final String fullName;
    private final String email;
    private final String phone;
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean inAppNotifications;

    public User(int id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public void manageNotificationPreferences(boolean email, boolean sms, boolean inApp) {
        this.emailNotifications = email;
        this.smsNotifications = sms;
        this.inAppNotifications = inApp;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean wantsEmailNotifications() {
        return emailNotifications;
    }

    public boolean wantsSmsNotifications() {
        return smsNotifications;
    }

    public boolean wantsInAppNotifications() {
        return inAppNotifications;
    }
}
