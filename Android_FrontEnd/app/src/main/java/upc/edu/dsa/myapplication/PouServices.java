package upc.edu.dsa.myapplication;

import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Activities.*;
import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface PouServices {

    @POST("/dsaApp/pougame/pou/login")
    Call<Pou>login(@Body Credenciales ref);

    @POST("/dsaApp/pougame/pou/registro")
    Call<Pou>registro(@Body InfoRegistro ref);

}
