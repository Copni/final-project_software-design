package appointments;

public class ConfirmedState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
        System.out.println("Appointment is already confirmed.");
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setState(new CancelledState());
    }

    @Override
    public void complete(Appointment appointment) {
        appointment.setState(new CompletedState());
    }

    @Override
    public String getName() {
        return "CONFIRMED";
    }
}
