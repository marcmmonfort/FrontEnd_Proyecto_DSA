package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.ObjetoTienda;
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;

import upc.edu.dsa.myapplication.R;

public class TiendaActivity extends AppCompatActivity {

    TableLayout tablaArticulos;
    Button tienda_backHome;
    TextView tienda_textPou, tienda_textLasAventurasDe;
    PouServices pouServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda_main);

        tablaArticulos = findViewById(R.id.tablaArticulos);

        tienda_textPou = findViewById(R.id.tienda_textPou);
        tienda_textLasAventurasDe = findViewById(R.id.tienda_textLasAventurasDe);

        tienda_backHome = (Button) findViewById(R.id.tienda_backHome);

        pouServices = PouRetrofit.getInstance().getPouServices();
        Call<List<ObjetoTienda>> call = pouServices.obtenerObjetosTienda();
        try {
            buildTable(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildTable(Call<List<ObjetoTienda>> call) throws IOException {
        List<ObjetoTienda> objetosTienda = call.execute().body();
        assert objetosTienda != null;
        for (ObjetoTienda objetoTienda : objetosTienda) {

            View filaArticulo = LayoutInflater.from(this).inflate(R.layout.columnasarticulos_main, null, false);

            TextView columna_nombreArticulo = filaArticulo.findViewById(R.id.columna_nombreArticulo);
            TextView columna_idArticulo = filaArticulo.findViewById(R.id.columna_idArticulo);
            TextView columna_precioArticulo = filaArticulo.findViewById(R.id.columna_precioArticulo);
            TextView columna_tipoArticulo = filaArticulo.findViewById(R.id.columna_tipoArticulo);
            TextView columna_recargaHambre = filaArticulo.findViewById(R.id.columna_recargaHambre);
            TextView columna_recargaSalud = filaArticulo.findViewById(R.id.columna_recargaSalud);
            TextView columna_recargaDiversion = filaArticulo.findViewById(R.id.columna_recargaDiversion);
            TextView columna_recargaSueno = filaArticulo.findViewById(R.id.columna_recargaSueno);

            columna_nombreArticulo.setText(objetoTienda.getArticuloId());
            columna_idArticulo.setText(objetoTienda.getArticuloId());
            columna_precioArticulo.setText((int) objetoTienda.getPrecioArticulo());
            columna_tipoArticulo.setText(objetoTienda.getTipoArticulo());
            columna_recargaHambre.setText(objetoTienda.getRecargaHambre());
            columna_recargaSalud.setText(objetoTienda.getRecargaSalud());
            columna_recargaDiversion.setText(objetoTienda.getRecargaDiversion());
            columna_recargaSueno.setText(objetoTienda.getRecargaSueno());

            tablaArticulos.addView(filaArticulo);
        }
    }
    
}
