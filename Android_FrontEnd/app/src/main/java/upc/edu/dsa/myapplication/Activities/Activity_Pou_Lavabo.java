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

public class Activity_Pou_Lavabo extends AppCompatActivity {

    ImageButton btnLeft, btnRight;

    TextView dinero_lavabo,hambre_lavabo,salud_lavabo,diversion_lavabo,sueno_lavabo;
    ImageView estado_lavabo,camiseta_lavabo,bambas_lavabo,blink_lavabo,gafas_lavabo,gorra_lavabo;
    ImageButton btn_consumir_sueno,btn_consumir_diversion,btn_consumir_salud,btn_consumir_hambre;
    TextView txt_cantidad_sueno,txt_cantidad_diversion,txt_cantidad_salud,txt_cantidad_hambre;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_lavabo_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_lavabo);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_lavabo);

        // TextView ---> textPou = findViewById(R.id.textPou);
        // Button ---> botonLogin = (Button) findViewById(R.id.botonLogin);
        // ImageButton ---> btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_salon);
        // ImageView ---> estado_salon = findViewById(R.id.estado_salon);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Lavabo.this, Activity_Pou_Cocina.class);
                Activity_Pou_Lavabo.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Lavabo.this, Activity_Pou_Juego.class);
                Activity_Pou_Lavabo.this.startActivity(myIntent2);
            }
        });
    }
}
