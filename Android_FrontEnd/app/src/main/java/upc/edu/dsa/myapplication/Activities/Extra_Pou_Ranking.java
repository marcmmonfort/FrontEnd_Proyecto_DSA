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

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_ranking);

        tablaPous = findViewById(R.id.tablaPous);

        ranking_record_botonSalir = (Button) findViewById(R.id.ranking_record_botonSalir);

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
            Extra_Pou_Ranking.this.startActivity(myIntent1);
        }
    }


}
