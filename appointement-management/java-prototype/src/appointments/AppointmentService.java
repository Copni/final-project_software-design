package appointments;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final AvailabilityStrategy availabilityStrategy;
    private final SchedulingStrategy schedulingStrategy;
    private final List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;

    public AppointmentService(AvailabilityStrategy availabilityStrategy, SchedulingStrategy schedulingStrategy) {
        // Protected Variations: variable rules are injected through interfaces.
        this.availabilityStrategy = availabilityStrategy;
        this.schedulingStrategy = schedulingStrategy;
    }

    public Appointment scheduleAppointment(Patient patient, Doctor doctor, TimeSlot slot, String reason) {
        // GRASP Controller: this service coordinates the use case.
        if (!schedulingStrategy.canSchedule(patient, doctor, slot)) {
            throw new IllegalArgumentException("Scheduling rules rejected the appointment.");
        }
        if (!availabilityStrategy.isAvailable(doctor, slot, appointments)) {
            throw new IllegalArgumentException("Doctor is not available for this slot.");
        }
        Appointment appointment = new Appointment(nextId++, patient, doctor, slot, reason, 60.0);
        appointments.add(appointment);
        return appointment;
    }

    public void rescheduleAppointment(Appointment appointment, TimeSlot newSlot) {
        if (!availabilityStrategy.isAvailable(appointment.getDoctor(), newSlot, appointments)) {
            throw new IllegalArgumentException("Doctor is not available for the new slot.");
        }
        appointment.reschedule(newSlot);
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.cancel();
    }

    public void confirmAppointment(Appointment appointment) {
        appointment.confirm();
    }

    public void completeAppointment(Appointment appointment) {
        appointment.complete();
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
}
