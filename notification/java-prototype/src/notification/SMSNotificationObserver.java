package notification;

public class SMSNotificationObserver implements AppointmentObserver {
    private final NotificationFactory factory;
    private final NotificationService service;

    public SMSNotificationObserver(NotificationFactory factory, NotificationService service) {
        this.factory = factory;
        this.service = service;
    }

    @Override
    public void update(Appointment appointment) {
        sendIfAllowed(appointment.getPatient(), appointment);
        sendIfAllowed(appointment.getDoctor(), appointment);
    }

    private void sendIfAllowed(User user, Appointment appointment) {
        if (user.wantsSmsNotifications()) {
            Notification notification = factory.createForAppointmentStatus(appointment);
            service.send(notification, user);
        }
    }
}
