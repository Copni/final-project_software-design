package appointments;

public class CancelledState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
        throw new IllegalStateException("A cancelled appointment cannot be confirmed.");
    }

    @Override
    public void cancel(Appointment appointment) {
        System.out.println("Appointment is already cancelled.");
    }

    @Override
    public void complete(Appointment appointment) {
        throw new IllegalStateException("A cancelled appointment cannot be completed.");
    }

    @Override
    public String getName() {
        return "CANCELLED";
    }
}
