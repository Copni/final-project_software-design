package notification;

public class Patient extends User {
    public Patient(int id, String fullName, String email, String phone) {
        super(id, fullName, email, phone);
    }

    public void receiveNotification(Notification notification) {
        System.out.println("Patient " + getFullName() + " received: " + notification.getTitle());
    }
}
