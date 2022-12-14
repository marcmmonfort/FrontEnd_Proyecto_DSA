package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class TiendaActivity extends AppCompatActivity {

    // Nuevo ...
    RecyclerView rv_articuloTienda;

    // Tienda antigua ...
    Button tienda_backHome;
    TextView tienda_textPou, tienda_textLasAventurasDe;

    // Atributos para las cards ...
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;

    // RETROFIT (Service) ...
    PouServices pouServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // tienda_textPou = findViewById(R.id.tienda_textPou);
        // tienda_textLasAventurasDe = findViewById(R.id.tienda_textLasAventurasDe);
        // tienda_backHome = (Button) findViewById(R.id.tienda_backHome);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            adapter = new CardAdapter(data); // Pasamos esta información al CardAdapter.
            rv_articuloTienda.setAdapter(adapter); // Llenamos el RecyclerView con la información.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(TiendaActivity.this, HomeActivity.class);
        TiendaActivity.this.startActivity(myIntent1);
    }

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
                    Integer.toString((int) objetoTienda.getRecargaSueno()),
                    1));
        }

        return data;
    }
}
