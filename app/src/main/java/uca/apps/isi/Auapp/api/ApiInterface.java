package uca.apps.isi.Auapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import uca.apps.isi.Auapp.models.Asignatura;
import uca.apps.isi.Auapp.models.Carrera;

/**
 * Created by isi3 on 17/4/2017.
 */

public interface ApiInterface {

    @GET("asignaturas")
    Call<List<Asignatura>> getAsignaturas();

    @GET("carreras")
    Call<List<Carrera>> getCarreras();

    @GET("deudas")
    Call<List<Carrera>> getDeudas();

}
