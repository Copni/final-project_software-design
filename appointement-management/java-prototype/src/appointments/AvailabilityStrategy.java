package appointments;

import java.util.List;

public interface AvailabilityStrategy {
    boolean isAvailable(Doctor doctor, TimeSlot slot, List<Appointment> appointments);
}
