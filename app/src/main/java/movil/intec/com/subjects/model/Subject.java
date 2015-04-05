package movil.intec.com.subjects.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ernest on 04/04/15.
 */

public class Subject {
    int id;
    String name;
    String profesor;
    String created_at;
    List<Horario> horario = new ArrayList<Horario>();

    // constructors
    public Subject() {
    }

    public Subject(String name, String profesor) {
        this.name = name;
        this.profesor = profesor;
    }

    public Subject(int id, String name, String profesor) {
        this.id = id;
        this.name = name;
        this.profesor = profesor;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setHorario(Horario horario) {
        this.horario.add(horario);
    }

    public void setHorario(ArrayList<Horario> horario) {
        this.horario = horario;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getProfesor() {
        return this.profesor;
    }
    public List<Horario> getHorario() {
        return horario;
    }

}
