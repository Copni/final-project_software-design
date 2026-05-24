package appointments;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Patient patient = new Patient(1, "Alice Patient");
        Doctor doctor = new Doctor(10, "Dr. Martin", "Cardiology");
        TimeSlot firstSlot = new TimeSlot(new Date(), "10:00", "10:30");
        TimeSlot secondSlot = new TimeSlot(new Date(), "11:00", "11:30");

        AppointmentService service = new AppointmentService(
                new NoOverlapAvailabilityStrategy(),
                new StandardSchedulingStrategy()
        );

        Appointment appointment = service.scheduleAppointment(patient, doctor, firstSlot, "Annual checkup");
        System.out.println("Created appointment #" + appointment.getId() + " in state " + appointment.getStateName());

        service.confirmAppointment(appointment);
        System.out.println("After confirmation: " + appointment.getStateName());

        service.rescheduleAppointment(appointment, secondSlot);
        System.out.println("After reschedule: " + appointment.getStateName() + " at " + appointment.getSlot());

        service.confirmAppointment(appointment);
        service.completeAppointment(appointment);
        System.out.println("After completion: " + appointment.getStateName());

        AppointmentService emergencyService = new AppointmentService(
                new NoOverlapAvailabilityStrategy(),
                new EmergencySchedulingStrategy()
        );
        Appointment emergency = emergencyService.scheduleAppointment(patient, doctor, new TimeSlot(new Date(), "22:00", "22:30"), "Emergency");
        System.out.println("Emergency appointment state: " + emergency.getStateName());
    }
}
