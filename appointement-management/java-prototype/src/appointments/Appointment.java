package appointments;

import java.util.Date;

public class Appointment {
    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private Date appointmentDate;
    private TimeSlot slot;
    private final String reason;
    private final double price;
    private AppointmentState state;

    public Appointment(int id, Patient patient, Doctor doctor, TimeSlot slot, String reason, double price) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.appointmentDate = slot.getDate();
        this.reason = reason;
        this.price = price;
        this.state = new ScheduledState();
    }

    public void confirm() {
        // Information Expert: appointment owns and delegates its lifecycle state.
        state.confirm(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void complete() {
        state.complete(this);
    }

    public void reschedule(TimeSlot newSlot) {
        if ("CANCELLED".equals(state.getName()) || "COMPLETED".equals(state.getName())) {
            throw new IllegalStateException("Cannot reschedule an appointment in state " + state.getName());
        }
        this.slot = newSlot;
        this.appointmentDate = newSlot.getDate();
        this.state = new ScheduledState();
    }

    void setState(AppointmentState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getReason() {
        return reason;
    }

    public double getPrice() {
        return price;
    }

    public String getStateName() {
        return state.getName();
    }
}
