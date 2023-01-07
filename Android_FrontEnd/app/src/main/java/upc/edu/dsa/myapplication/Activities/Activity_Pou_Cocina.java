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

public class Activity_Pou_Cocina extends AppCompatActivity{

    ImageButton btnLeft, btnRight;

    TextView txt_cantidad_apple,txt_cantidad_roncola,txt_cantidad_aquarius,txt_cantidad_agua,txt_cantidad_pizza,txt_cantidad_candy;
    TextView dinero_cocina,hambre_cocina,salud_cocina,diversion_cocina,sueno_cocina;
    ImageView estado_cocina,camiseta_cocina,bambas_cocina,blink_cocina,gafas_cocina,gorra_cocina;
    ImageButton btn_consumir_roncola,btn_consumir_aquarius,btn_consumir_agua,btn_consumir_pizza,btn_consumir_candy,btn_consumir_manzana;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_cocina_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_cocina);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_cocina);

        // TextView ---> textPou = findViewById(R.id.textPou);
        // Button ---> botonLogin = (Button) findViewById(R.id.botonLogin);
        // ImageButton ---> btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_salon);
        // ImageView ---> estado_salon = findViewById(R.id.estado_salon);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Armario.class);
                Activity_Pou_Cocina.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Lavabo.class);
                Activity_Pou_Cocina.this.startActivity(myIntent2);
            }
        });
    }
}