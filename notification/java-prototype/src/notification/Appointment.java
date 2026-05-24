package notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment {
    private final int id;
    private Date date;
    private String status;
    private final Patient patient;
    private final Doctor doctor;
    private final List<AppointmentObserver> observers = new ArrayList<>();

    public Appointment(int id, Date date, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.status = "SCHEDULED";
    }

    public void attach(AppointmentObserver observer) {
        observers.add(observer);
    }

    public void detach(AppointmentObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        // GRASP Low Coupling: depends on the observer abstraction only.
        for (AppointmentObserver observer : observers) {
            observer.update(this);
        }
    }

    public void confirm() {
        status = "CONFIRMED";
        notifyObservers();
    }

    public void cancel() {
        status = "CANCELLED";
        notifyObservers();
    }

    public void reschedule(Date newDate) {
        date = newDate;
        status = "RESCHEDULED";
        notifyObservers();
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
