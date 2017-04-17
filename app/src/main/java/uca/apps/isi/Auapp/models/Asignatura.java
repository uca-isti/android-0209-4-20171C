package uca.apps.isi.Auapp.models;

/**
 * Created by isi3 on 17/4/2017.
 */

public class Asignatura {

    private int id_asignatura;
    private String asignatura;
    private float precio;

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
