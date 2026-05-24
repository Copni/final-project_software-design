package appointments;

public class CompletedState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
        throw new IllegalStateException("A completed appointment cannot be confirmed again.");
    }

    @Override
    public void cancel(Appointment appointment) {
        throw new IllegalStateException("A completed appointment cannot be cancelled.");
    }

    @Override
    public void complete(Appointment appointment) {
        System.out.println("Appointment is already completed.");
    }

    @Override
    public String getName() {
        return "COMPLETED";
    }
}
