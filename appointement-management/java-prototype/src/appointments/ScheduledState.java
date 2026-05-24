package appointments;

public class ScheduledState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
        appointment.setState(new ConfirmedState());
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setState(new CancelledState());
    }

    @Override
    public void complete(Appointment appointment) {
        throw new IllegalStateException("A scheduled appointment must be confirmed before completion.");
    }

    @Override
    public String getName() {
        return "SCHEDULED";
    }
}
