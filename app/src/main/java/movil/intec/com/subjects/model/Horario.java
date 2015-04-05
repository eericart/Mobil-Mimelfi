package movil.intec.com.subjects.model;

import android.text.format.Time;

/**
 * Created by ernest on 04/04/15.
 */
public class Horario {

    int id;
    Time start;
    Time end;
    Time day;


    public Horario(Time start, Time end, Time day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(Time day) {
        this.day = day;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Time getStart() {
        return start;
    }

    public int getId() {
        return id;
    }

    public Time getDay() {

        return day;
    }

    public Time getEnd() {
        return end;
    }
}
