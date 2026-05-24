package appointments;

public class EmergencySchedulingStrategy implements SchedulingStrategy {
    @Override
    public boolean canSchedule(Patient patient, Doctor doctor, TimeSlot slot) {
        // Emergency strategy accepts broader conditions than the standard strategy.
        return true;
    }
}
