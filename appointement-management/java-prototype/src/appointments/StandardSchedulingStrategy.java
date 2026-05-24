package appointments;

public class StandardSchedulingStrategy implements SchedulingStrategy {
    @Override
    public boolean canSchedule(Patient patient, Doctor doctor, TimeSlot slot) {
        // Strategy Pattern: standard appointments require regular business hours.
        return slot.getStartTime().compareTo("08:00") >= 0 && slot.getEndTime().compareTo("18:00") <= 0;
    }
}
