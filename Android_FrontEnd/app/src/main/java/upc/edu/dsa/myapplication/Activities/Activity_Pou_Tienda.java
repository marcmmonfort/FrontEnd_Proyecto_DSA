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

public class Activity_Pou_Tienda extends AppCompatActivity {

    ImageButton btnLeft, btnRight;

    TextView dinero_tienda,hambre_tienda,salud_tienda,diversion_tienda,sueno_tienda,titulo_tienda;
    TextView titulo_acceso;
    ImageButton btn_abrirTienda;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_tienda_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_tienda);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_tienda);

        dinero_tienda = findViewById(R.id.dinero_tienda);
        hambre_tienda = findViewById(R.id.hambre_tienda);
        salud_tienda = findViewById(R.id.salud_tienda);
        diversion_tienda = findViewById(R.id.diversion_tienda);
        sueno_tienda = findViewById(R.id.sueno_tienda);
        titulo_tienda = findViewById(R.id.titulo_tienda);
        titulo_acceso = findViewById(R.id.titulo_acceso);

        btn_abrirTienda =(ImageButton)findViewById(R.id.btn_abrirTienda);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Tienda.this, Activity_Pou_Salon.class);
                Activity_Pou_Tienda.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Tienda.this, Activity_Pou_Armario.class);
                Activity_Pou_Tienda.this.startActivity(myIntent2);
            }
        });
    }
}
