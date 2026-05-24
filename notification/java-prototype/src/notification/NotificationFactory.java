package notification;

public class NotificationFactory {
    private int nextId = 1;

    public Notification createAppointmentConfirmation(Appointment appointment) {
        return create("Appointment confirmed", "Appointment #" + appointment.getId() + " is confirmed.", "CONFIRMATION");
    }

    public Notification createAppointmentCancellation(Appointment appointment) {
        return create("Appointment cancelled", "Appointment #" + appointment.getId() + " was cancelled.", "CANCELLATION");
    }

    public Notification createAppointmentReminder(Appointment appointment) {
        return create("Appointment reminder", "Reminder for appointment #" + appointment.getId() + ".", "REMINDER");
    }

    public Notification createScheduleModification(Appointment appointment) {
        return create("Appointment rescheduled", "Appointment #" + appointment.getId() + " was rescheduled.", "SCHEDULE_MODIFICATION");
    }

    public Notification createForAppointmentStatus(Appointment appointment) {
        if ("CONFIRMED".equals(appointment.getStatus())) {
            return createAppointmentConfirmation(appointment);
        }
        if ("CANCELLED".equals(appointment.getStatus())) {
            return createAppointmentCancellation(appointment);
        }
        if ("RESCHEDULED".equals(appointment.getStatus())) {
            return createScheduleModification(appointment);
        }
        return createAppointmentReminder(appointment);
    }

    private Notification create(String title, String message, String type) {
        // Factory Pattern: object creation is centralized here.
        return new Notification(nextId++, title, message, type);
    }
}
