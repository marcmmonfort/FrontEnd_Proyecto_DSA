package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import upc.edu.dsa.myapplication.Entities.ObjetoTienda;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import upc.edu.dsa.myapplication.Entities.VO.*;

public class Activity_Pou_Armario extends AppCompatActivity {

    ImageButton btnLeft, btnRight;
    RecyclerView rv_articuloTienda;
    TextView dinero_armario,hambre_armario,salud_armario,diversion_armario,sueno_armario,titulo_armario;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    PouServices pouServices;

    // Variables Globales con los Niveles del Estado del Pou ...
    int lvlHambre = 0;
    int lvlSalud = 0;
    int lvlDiversion = 0;
    int lvlSueno = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_armario_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_armario);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_armario);

        dinero_armario = findViewById(R.id.dinero_armario);
        hambre_armario = findViewById(R.id.hambre_armario);
        salud_armario = findViewById(R.id.salud_armario);
        diversion_armario = findViewById(R.id.diversion_armario);
        sueno_armario = findViewById(R.id.sueno_armario);
        titulo_armario = findViewById(R.id.titulo_armario);

        // Declaración de los 4 Estados del Pou ...
        hambre_armario.setText(Integer.toString(lvlHambre));
        salud_armario.setText(Integer.toString(lvlSalud));
        diversion_armario.setText(Integer.toString(lvlDiversion));
        sueno_armario.setText(Integer.toString(lvlSueno));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Armario.this, Activity_Pou_Tienda.class);
                Activity_Pou_Armario.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Armario.this, Activity_Pou_Cocina.class);
                Activity_Pou_Armario.this.startActivity(myIntent2);
            }
        });

        /*
        layoutManager = new LinearLayoutManager(this);

        rv_articuloTienda = (RecyclerView) findViewById(R.id.rv_articuloTienda);
        rv_articuloTienda.setLayoutManager(layoutManager);
        rv_articuloTienda.setItemAnimator(new DefaultItemAnimator());

        pouServices = PouRetrofit.getInstance().getPouServices();
        Call<List<ObjetoTienda>> call = pouServices.obtenerObjetosTienda();

        // Probamos la construcción de la tabla ...
        try {
            data = new ArrayList<DataModel>(); // Lista que llenaremos con la información de las diferentes Cards (Data Models).
            data = answersToData(call); // Llamamos a la construcción del vector "data".
            adapter = new Extra_CardAdapter(data); // Pasamos esta información al CardAdapter.
            rv_articuloTienda.setAdapter(adapter); // Llenamos el RecyclerView con la información.
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /*
    @SuppressLint("SetTextI18n")
    private ArrayList<DataModel> answersToData(Call<List<ObjetoTienda>> call) throws IOException {
        List<ObjetoTienda> objetosTienda = call.execute().body();
        assert objetosTienda != null;

        data = new ArrayList<DataModel>();

        for (ObjetoTienda objetoTienda : objetosTienda) {

            // Se añaden los datos de un artículo a la lista "data".
            data.add(new DataModel(objetoTienda.getArticuloId(),
                    objetoTienda.getNombreArticulo(),
                    Integer.toString((int) objetoTienda.getPrecioArticulo()),
                    objetoTienda.getTipoArticulo(),
                    Integer.toString((int) objetoTienda.getRecargaHambre()),
                    Integer.toString((int) objetoTienda.getRecargaSalud()),
                    Integer.toString((int) objetoTienda.getRecargaDiversion()),
                    Integer.toString((int) objetoTienda.getRecargaSueno())));
        }

        return data;
    }
    */
}
