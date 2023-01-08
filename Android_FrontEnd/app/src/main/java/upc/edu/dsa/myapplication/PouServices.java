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

    // UBICACIÓN: Activity_Pou_Login.
    // DESCRIPCIÓN: Hacer Login en un Pou.
    @POST("/dsaApp/pougame/pou/login")
    Call<Void> login(@Body Credenciales credenciales);

    // UBICACIÓN: Activity_Pou_Register.
    // DESCRIPCIÓN: Registrar un nuevo Pou.
    @POST("/dsaApp/pougame/pou/registro")
    Call<Void> registro(@Body InfoRegistro infoRegistro);

    // UBICACIÓN: Activity_Pou_Login.
    // DESCRIPCIÓN: Se obtienen todos los parámetros del Pou y se pasan al salón cuando el Login se hace correctamente.

    // UBICACIÓN: Activity_Pou_Salon / Activity_Pou_Info / Activity_Pou_Juego / Activity_Pou_Tienda.
    // DESCRIPCIÓN: Se guardan todos los parámetros del Pou al pulsar el botón de Guardar Partida / al hacer Logout / al iniciar el Juego / cuando arrancas la Web.

    // UBICACIÓN: '-'.
    // DESCRIPCIÓN: Obtener todos los artículos de la tienda.
    // @GET("/dsaApp/pougame/tienda/listaObjetos")
    // Call<List<ObjetoTienda>> obtenerObjetosTienda();
}
