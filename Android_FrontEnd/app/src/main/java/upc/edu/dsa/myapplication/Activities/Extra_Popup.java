package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.ObjetoTienda;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

public class Extra_Popup extends AppCompatActivity {

    ImageView img_popup;

    TextView txt_sueno, txt_diversion, txt_salud, txt_hambre, txt_tipo, txt_precio, txt_nombre;

    Button popup_botonSalir;

    PouServices pouServices;

    String articuloID = "Error";
    String levelHambre = "";
    String levelSalud = "";
    String levelDiversion = "";
    String levelSueno = "";
    String nombre = "";
    String precio = "";
    String tipo = "";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE PASAN ENTRE LAS ACTIVITIES.
    String data_pouId = "marc";
    String data_nombrePou = "Marc";
    String data_nacimientoPou = "28/10/2001";
    String data_correoPou = "marc@gmail.com";
    String data_passwordPou = "Calella";
    int recordPou = 0;
    int lvlHambre = 28;
    int lvlSalud = 10;
    int lvlDiversion = 200;
    int lvlSueno = 1;
    int amountDinero = 50;
    int amountCandy = 1;
    int amountManzana = 6;
    int amountPizza = 6;
    int amountAgua = 6;
    int amountAquarius = 6;
    int amountRoncola = 6;
    int amountHambre = 1;
    int amountSalud = 1;
    int amountDiversion = 1;
    int amountSueno = 1;
    String pouEstado = "normal";
    String pouCamiseta = "spain";
    String pouBambas = "veja";
    String pouGafas = "nada";
    String pouGorro = "cerveza";
    String posee_pijama = "NO";
    String posee_fcb = "NO";
    String posee_spain = "YES";
    String posee_messi = "NO";
    String posee_rafa = "YES";
    String posee_veja = "NO";
    String posee_fiesta = "NO";
    String posee_rayban = "YES";
    String posee_ciclismo = "NO";
    String posee_cerveza = "YES";
    String posee_boina = "NO";
    String posee_polo = "YES";
    String activityOrigen = "Juego";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_popup);

        txt_sueno = findViewById(R.id.txt_sueno);
        txt_diversion = findViewById(R.id.txt_diversion);
        txt_salud = findViewById(R.id.txt_salud);
        txt_hambre = findViewById(R.id.txt_hambre);
        txt_tipo = findViewById(R.id.txt_tipo);
        txt_precio = findViewById(R.id.txt_precio);
        txt_nombre = findViewById(R.id.txt_nombre);

        popup_botonSalir = (Button) findViewById(R.id.popup_botonSalir);

        img_popup = (ImageView) findViewById(R.id.img_popup);

        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){
            articuloID = infoRecibida.getString("articuloID");

            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarNivelHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarNivelSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarNivelDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarNivelSueno"));
            amountDinero = Integer.parseInt(infoRecibida.getString("pasarDinero"));

            amountCandy = Integer.parseInt(infoRecibida.getString("pasarCandy"));
            amountManzana = Integer.parseInt(infoRecibida.getString("pasarManzana"));
            amountPizza = Integer.parseInt(infoRecibida.getString("pasarPizza"));
            amountAgua = Integer.parseInt(infoRecibida.getString("pasarAgua"));
            amountAquarius = Integer.parseInt(infoRecibida.getString("pasarAquarius"));
            amountRoncola = Integer.parseInt(infoRecibida.getString("pasarRoncola"));

            amountHambre = Integer.parseInt(infoRecibida.getString("pasarPocionHambre"));
            amountSalud = Integer.parseInt(infoRecibida.getString("pasarPocionSalud"));
            amountDiversion = Integer.parseInt(infoRecibida.getString("pasarPocionDiversion"));
            amountSueno = Integer.parseInt(infoRecibida.getString("pasarPocionSueno"));

            pouEstado = infoRecibida.getString("pasarPouEstado");
            pouCamiseta = infoRecibida.getString("pasarPouCamiseta");
            pouBambas = infoRecibida.getString("pasarPouBambas");
            pouGafas = infoRecibida.getString("pasarPouGafas");
            pouGorro = infoRecibida.getString("pasarPouGorro");

            data_pouId = infoRecibida.getString("pasarDataPouId");
            data_nombrePou = infoRecibida.getString("pasarDataNombrePou");
            data_nacimientoPou = infoRecibida.getString("pasarDataNacimientoPou");
            data_correoPou = infoRecibida.getString("pasarDataCorreoPou");
            data_passwordPou = infoRecibida.getString("pasarDataPasswordPou");

            posee_pijama = infoRecibida.getString("pasarPoseePijama");
            posee_fcb = infoRecibida.getString("pasarPoseeFcb");
            posee_spain = infoRecibida.getString("pasarPoseeSpain");
            posee_messi = infoRecibida.getString("pasarPoseeMessi");
            posee_rafa = infoRecibida.getString("pasarPoseeRafa");
            posee_veja = infoRecibida.getString("pasarPoseeVeja");
            posee_fiesta = infoRecibida.getString("pasarPoseeFiesta");
            posee_rayban = infoRecibida.getString("pasarPoseeRayban");
            posee_ciclismo = infoRecibida.getString("pasarPoseeCiclismo");
            posee_cerveza = infoRecibida.getString("pasarPoseeCerveza");
            posee_boina = infoRecibida.getString("pasarPoseeBoina");
            posee_polo = infoRecibida.getString("pasarPoseePolo");

            recordPou= Integer.parseInt(infoRecibida.getString("pasarRecordPou"));
            activityOrigen =infoRecibida.getString("pasarActividadOrigen");
        }

        //El SERVICIO
        pouServices = PouRetrofit.getInstance().getPouServices();

        Call<ObjetoTienda> peticionEstadisticas = pouServices.obtenerInfoObjeto(articuloID);

        peticionEstadisticas.enqueue(new Callback<ObjetoTienda>() {
            @Override
            public void onResponse(Call<ObjetoTienda> peticion, Response<ObjetoTienda> respuesta) {
                switch (respuesta.code()) {
                    case 201:
                        ObjetoTienda objetoTienda = respuesta.body();

                        levelHambre = objetoTienda.getRecargaHambre().toString();
                        levelSalud = objetoTienda.getRecargaSalud().toString();
                        levelDiversion = objetoTienda.getRecargaDiversion().toString();
                        levelSueno = objetoTienda.getRecargaSueno().toString();
                        nombre = objetoTienda.getNombreArticulo();
                        precio = String.valueOf(objetoTienda.getPrecioArticulo());
                        tipo = objetoTienda.getTipoArticulo();

                        txt_hambre.setText(levelHambre);
                        txt_diversion.setText(levelDiversion);
                        txt_salud.setText(levelSalud);
                        txt_sueno.setText(levelSueno);
                        txt_tipo.setText(tipo);
                        txt_precio.setText(precio + " €");
                        txt_nombre.setText(nombre);

                        String refEstado = "articulo_consumible_" + articuloID;
                        img_popup.setImageResource(getResources().getIdentifier(refEstado, "drawable", getPackageName()));

                        break;
                }
            }
            @Override
            public void onFailure(Call<ObjetoTienda> peticion, Throwable t) {
                Log.d("POU", " onFailure", t);
                StyleableToast.makeText(Extra_Popup.this, "¡Error!", R.style.exampleToast).show();
            }
        });
    }

    public void exit(View view) throws IOException {

        if (view==popup_botonSalir){
            Intent myIntent1 = new Intent(Extra_Popup.this, Activity_Pou_Cocina.class);

            myIntent1.putExtra("pasarNivelHambre",Integer.toString(lvlHambre));
            myIntent1.putExtra("pasarNivelSalud",Integer.toString(lvlSalud));
            myIntent1.putExtra("pasarNivelDiversion",Integer.toString(lvlDiversion));
            myIntent1.putExtra("pasarNivelSueno",Integer.toString(lvlSueno));
            myIntent1.putExtra("pasarDinero",Integer.toString(amountDinero));

            myIntent1.putExtra("pasarCandy",Integer.toString(amountCandy));
            myIntent1.putExtra("pasarManzana",Integer.toString(amountManzana));
            myIntent1.putExtra("pasarPizza",Integer.toString(amountPizza));
            myIntent1.putExtra("pasarAgua",Integer.toString(amountAgua));
            myIntent1.putExtra("pasarAquarius",Integer.toString(amountAquarius));
            myIntent1.putExtra("pasarRoncola",Integer.toString(amountRoncola));

            myIntent1.putExtra("pasarPocionHambre",Integer.toString(amountHambre));
            myIntent1.putExtra("pasarPocionSalud",Integer.toString(amountSalud));
            myIntent1.putExtra("pasarPocionDiversion",Integer.toString(amountDiversion));
            myIntent1.putExtra("pasarPocionSueno",Integer.toString(amountSueno));

            myIntent1.putExtra("pasarPouEstado",pouEstado);
            myIntent1.putExtra("pasarPouCamiseta",pouCamiseta);
            myIntent1.putExtra("pasarPouBambas",pouBambas);
            myIntent1.putExtra("pasarPouGafas",pouGafas);
            myIntent1.putExtra("pasarPouGorro",pouGorro);

            myIntent1.putExtra("pasarDataPouId",data_pouId);
            myIntent1.putExtra("pasarDataNombrePou",data_nombrePou);
            myIntent1.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
            myIntent1.putExtra("pasarDataCorreoPou",data_correoPou);
            myIntent1.putExtra("pasarDataPasswordPou", data_passwordPou);


            myIntent1.putExtra("pasarPoseePijama",posee_pijama);
            myIntent1.putExtra("pasarPoseeFcb",posee_fcb);
            myIntent1.putExtra("pasarPoseeSpain",posee_spain);
            myIntent1.putExtra("pasarPoseeMessi",posee_messi);
            myIntent1.putExtra("pasarPoseeRafa",posee_rafa);
            myIntent1.putExtra("pasarPoseeVeja",posee_veja);
            myIntent1.putExtra("pasarPoseeFiesta",posee_fiesta);
            myIntent1.putExtra("pasarPoseeRayban",posee_rayban);
            myIntent1.putExtra("pasarPoseeCiclismo",posee_ciclismo);
            myIntent1.putExtra("pasarPoseeCerveza",posee_cerveza);
            myIntent1.putExtra("pasarPoseeBoina",posee_boina);
            myIntent1.putExtra("pasarPoseePolo",posee_polo);

            myIntent1.putExtra("pasarRecordPou",Integer.toString(recordPou));
            myIntent1.putExtra("pasarActividadOrigen",activityOrigen);

            Extra_Popup.this.startActivity(myIntent1);
        }

    }

}


