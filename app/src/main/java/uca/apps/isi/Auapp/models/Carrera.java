package uca.apps.isi.Auapp.models;

import io.realm.RealmObject;

/**
 * Created by isi3 on 17/4/2017.
 */

public class Carrera  extends RealmObject {

    private int id_carrera;
    private String nombre;

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
