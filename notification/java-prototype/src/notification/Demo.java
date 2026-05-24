package notification;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Patient patient = new Patient(1, "Alice Patient", "alice@example.com", "+330100000001");
        Doctor doctor = new Doctor(2, "Dr. Martin", "doctor@example.com", "+330100000002");
        patient.manageNotificationPreferences(true, false, true);
        doctor.manageNotificationPreferences(true, true, false);

        NotificationFactory factory = new NotificationFactory();
        NotificationService service = new NotificationService();

        Appointment appointment = new Appointment(100, new Date(), patient, doctor);
        appointment.attach(new EmailNotificationObserver(factory, service));
        appointment.attach(new SMSNotificationObserver(factory, service));
        appointment.attach(new InAppNotificationObserver(factory, service));

        appointment.confirm();
        appointment.reschedule(new Date(System.currentTimeMillis() + 86_400_000));
        appointment.cancel();
    }
}
