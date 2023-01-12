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

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE PASAN ENTRE LAS ACTIVITIES.

    String articuloID = "Error";
    String lvlHambre = "";
    String lvlSalud = "";
    String lvlDiversion = "";
    String lvlSueno = "";
    String nombre = "";
    String precio = "";
    String tipo = "";

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

                        lvlHambre = objetoTienda.getRecargaHambre().toString();
                        lvlSalud = objetoTienda.getRecargaSalud().toString();
                        lvlDiversion = objetoTienda.getRecargaDiversion().toString();
                        lvlSueno = objetoTienda.getRecargaSueno().toString();
                        nombre = objetoTienda.getNombreArticulo();
                        precio = String.valueOf(objetoTienda.getPrecioArticulo());
                        tipo = objetoTienda.getTipoArticulo();

                        txt_hambre.setText(lvlHambre);
                        txt_diversion.setText(lvlDiversion);
                        txt_salud.setText(lvlSalud);
                        txt_sueno.setText(lvlSueno);
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
            Extra_Popup.this.startActivity(myIntent1);
        }

    }

}


