package appointments;

import java.util.List;

public class NoOverlapAvailabilityStrategy implements AvailabilityStrategy {
    @Override
    public boolean isAvailable(Doctor doctor, TimeSlot slot, List<Appointment> appointments) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().getId() == doctor.getId())
                .filter(appointment -> !"CANCELLED".equals(appointment.getStateName()))
                .noneMatch(appointment -> appointment.getSlot().overlaps(slot));
    }
}
