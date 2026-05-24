package notification;

public class NotificationService {
    public void send(Notification notification, User user) {
        notification.markAsSent();
        System.out.println("Sending " + notification.getType() + " to " + user.getFullName() + ": " + notification.getMessage());
    }
}
