package Interfaces;

import java.util.List;

import Model.articuloModel;
import Model.loadModel;
import Model.msgModel;
import Model.pasilloModel;
import Model.reservaModel;
import Model.userModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface serviceRetrofit {

    @POST("login")
    Call<msgModel> login(@Body userModel user);

    @GET("ArticuloConsultar/{id}")
    Call<articuloModel> consultaPorNumSerie(@Path("id") long id);

    @GET("ArticuloConsultarPorLoad/{id}")
    Call<articuloModel> consultaPorLoad(@Path("id") long id);

    @GET("PasilloConsultar/{id}")
    Call<pasilloModel> consultaPasilloID(@Path("id") long id);

    @GET("ReservaConsultarPorPasillo/{id}")
    Call<List<reservaModel>> consultaReservaPasillo(@Path("id") int id);

    @GET("LoadConsultar/{id}")
    Call<loadModel> consultaLoad(@Path("id") long id);
}
