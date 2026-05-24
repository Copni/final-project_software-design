package appointments;

public interface SchedulingStrategy {
    boolean canSchedule(Patient patient, Doctor doctor, TimeSlot slot);
}
