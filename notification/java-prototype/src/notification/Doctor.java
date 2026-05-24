package notification;

public class Doctor extends User {
    public Doctor(int id, String fullName, String email, String phone) {
        super(id, fullName, email, phone);
    }

    public void receiveNotification(Notification notification) {
        System.out.println("Doctor " + getFullName() + " received: " + notification.getTitle());
    }
}
