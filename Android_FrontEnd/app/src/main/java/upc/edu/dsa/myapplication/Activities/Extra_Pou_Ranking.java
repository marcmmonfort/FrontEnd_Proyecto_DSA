package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.ObjetoTienda;
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import upc.edu.dsa.myapplication.Entities.VO.*;

import java.util.*;
import io.github.muddz.styleabletoast.StyleableToast;

public class Extra_Pou_Ranking extends AppCompatActivity {

    TableLayout tablaPous;
    Button ranking_record_botonSalir;

    PouServices pouServices;

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
        setContentView(R.layout.pou_ranking);

        tablaPous = findViewById(R.id.tablaPous);

        ranking_record_botonSalir = (Button) findViewById(R.id.ranking_record_botonSalir);

        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){

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

        // AQUÍ DIFERENCIAR EL TIPO DE COLUMNA QUE QUEREMOS USAR PARA ORDENAR DESCENDIENTEMENTE LOS POUS.

        pouServices = PouRetrofit.getInstance().getPouServices();

        Call<List<Pou>> peticion = pouServices.obtenerPousOrdenadosDescendentemente("record");

        peticion.enqueue(new Callback<List<Pou>>() {
            @Override
            public void onResponse(Call<List<Pou>> peticion, Response<List<Pou>> respuesta) {
                switch (respuesta.code()){
                    case 201:
                        List<Pou> listaPous = respuesta.body();

                        // Rellenamos la tabla.
                        int positionPou = 1;
                        assert listaPous != null;
                        for (int i=0; i<listaPous.size();i++) {

                            View filaPou = LayoutInflater.from(Extra_Pou_Ranking.this).inflate(R.layout.extra_ranking_row, null, false);

                            TextView columna_position = filaPou.findViewById(R.id.columna_position);
                            TextView columna_ID = filaPou.findViewById(R.id.columna_ID);
                            TextView columna_nombre = filaPou.findViewById(R.id.columna_nombre);
                            TextView columna_record = filaPou.findViewById(R.id.columna_record);

                            columna_position.setText(Integer.toString(positionPou));
                            columna_ID.setText(listaPous.get(i).getPouId());
                            columna_nombre.setText(listaPous.get(i).getNombrePou());
                            columna_record.setText(Integer.toString(listaPous.get(i).getRecord()));

                            tablaPous.addView(filaPou);

                            positionPou++;
                        }

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Pou>> peticion, Throwable t) {
                StyleableToast.makeText(Extra_Pou_Ranking.this, "¡Error!", R.style.exampleToast).show();

            }
        });


        /*
        pouServices = PouRetrofit.getInstance().getPouServices();
        Call<List<Pou>> call = pouServices.obtenerPousOrdenadosDescendentemente("record");
        try {
            buildTable(call);
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }

    /*
    // Función para rellenar la tabla.
    @SuppressLint("SetTextI18n")
    private void buildTable(Call<List<Pou>> call) throws IOException {
        List<Pou> listaPous = call.execute().body();
        assert listaPous != null;
        int positionPou = 1;
        for (Pou pou : listaPous) {
            View filaPou = LayoutInflater.from(this).inflate(R.layout.extra_ranking_row, null, false);

            TextView columna_position = filaPou.findViewById(R.id.columna_position);
            TextView columna_ID = filaPou.findViewById(R.id.columna_ID);
            TextView columna_nombre = filaPou.findViewById(R.id.columna_nombre);
            TextView columna_record = filaPou.findViewById(R.id.columna_record);

            columna_position.setText(positionPou);
            columna_ID.setText(pou.getPouId());
            columna_nombre.setText(pou.getNombrePou());
            columna_record.setText(Integer.toString((int) pou.getRecord()));

            tablaPous.addView(filaPou);

            positionPou++;
        }
    }

     */

    public void exit(View view) throws IOException {

        if (view==ranking_record_botonSalir){
            Intent myIntent1 = new Intent(Extra_Pou_Ranking.this, Activity_Pou_Juego.class);

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

            Extra_Pou_Ranking.this.startActivity(myIntent1);
        }
    }


}
