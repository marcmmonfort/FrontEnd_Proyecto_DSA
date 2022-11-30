package upc.edu.dsa.myapplication;

import java.util.List;

import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Activities.*;
import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PouServices {

    // Hacer Login en un Pou.
    @POST("/dsaApp/pougame/pou/login")
    Call<Pou> login(@Body Credenciales credenciales);

    // Registrar un Pou.
    @POST("/dsaApp/pougame/pou/registro")
    Call<Pou> registro(@Body InfoRegistro infoRegistro);

    // Obtener todos los Artículos de la Tienda.
    @GET("/dsaApp/pougame/tienda/listaObjetos")
    Call<List<ObjetoTienda>> obtenerObjetosTienda();
}
