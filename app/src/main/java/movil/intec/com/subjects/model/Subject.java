package movil.intec.com.subjects.model;

import java.util.ArrayList;

/**
 * Created by ernest on 04/04/15.
 */

public class Subject {

    private int id;
    private String name;
    private String profesor;
    private String created_at;
    private ArrayList<Horario> horarios;



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

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
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

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

}
