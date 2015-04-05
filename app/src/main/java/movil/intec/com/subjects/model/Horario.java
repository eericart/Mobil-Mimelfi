package movil.intec.com.subjects.model;

/**
 * Created by ernest on 04/04/15.
 */
public class Horario {

    int id;
    String start;
    String end;
    String day;


    public Horario(String start, String end, String day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public int getId() {
        return id;
    }

    public String getDay() {

        return day;
    }

    public String getEnd() {
        return end;
    }
}
