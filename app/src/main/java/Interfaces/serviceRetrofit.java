package Interfaces;

import java.util.List;

import Model.articuloModel;
import Model.loadModel;
import Model.msgModel;
import Model.msgModelEliminarArticulo;
import Model.pasilloModel;
import Model.reservaModel;
import Model.tareaModel;
import Model.userModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface serviceRetrofit {

    @POST("login")
    Call<msgModel> login(@Body userModel user);

    @GET("ArticuloConsultar/{id}")
    Call<articuloModel> consultaPorNumSerie(@Path("id") long id);

    @POST("ArticuloEditar/{id}")
    Call<msgModel> agregarArticuloAdmin(@Path("id") int id, @Body articuloModel articulo);

    @POST("ArticuloEditar/{id}")
    Call<msgModelEliminarArticulo> modificarArticuloAdmin(@Path("id") int id, @Body articuloModel articulo);

    @GET("ArticuloConsultarPorLoad/{id}")
    Call<List<articuloModel>> consultaPorLoad(@Path("id") int id);

    @GET("ArticuloEliminar/{id}")
    Call<msgModelEliminarArticulo> eliminarArticulo(@Path("id") int id);

    @GET("PasilloConsultar/{id}")
    Call<pasilloModel> consultaPasilloID(@Path("id") long id);

    @GET("ReservaConsultarPorPasillo/{id}")
    Call<List<reservaModel>> consultaReservaPasillo(@Path("id") int id);

    @GET("ReservaConsultar/{id}")
    Call<reservaModel> consultaReserva(@Path("id") long id);

    @GET("LoadConsultarUno/{id}")
    Call<loadModel> consultaLoadId(@Path("id") int id);

    @GET("LoadConsultar/{id}")
    Call<loadModel> consultaLoad(@Path("id") long id);

    @GET("LoadConsultarPorReserva/{id}")
    Call <List<loadModel>> consultaLoadPorReserva(@Path("id") int id);

    @POST("TareaAgregar")
    Call<msgModelEliminarArticulo> agregarTarea(@Body tareaModel tarea);

    @POST("LoadAgregar")
    Call<msgModelEliminarArticulo> agregarLoad(@Body loadModel load);

    @GET("Tareas")
    Call<List<tareaModel>> consultarTareas();

    @GET("TareaEditar/{id}")
    Call<msgModelEliminarArticulo> modificarTareaLoad(@Path("id") int id, @Body tareaModel tarea);
}
