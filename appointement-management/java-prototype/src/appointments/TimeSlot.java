package appointments;

import java.util.Date;

public class TimeSlot {
    private final Date date;
    private final String startTime;
    private final String endTime;

    public TimeSlot(Date date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean overlaps(TimeSlot otherSlot) {
        boolean sameDate = date.equals(otherSlot.date);
        boolean timeOverlap = startTime.compareTo(otherSlot.endTime) < 0
                && endTime.compareTo(otherSlot.startTime) > 0;
        return sameDate && timeOverlap;
    }

    public Date getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }
}
