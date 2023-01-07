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
    TextView dinero_cocina,hambre_cocina,salud_cocina,diversion_cocina,sueno_cocina,titulo_cocina;
    ImageView estado_cocina,camiseta_cocina,bambas_cocina,blink_cocina,gafas_cocina,gorra_cocina;
    ImageButton btn_consumir_roncola,btn_consumir_aquarius,btn_consumir_agua,btn_consumir_pizza,btn_consumir_candy,btn_consumir_manzana;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_cocina_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_cocina);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_cocina);

        btn_consumir_roncola =(ImageButton)findViewById(R.id.btn_consumir_roncola);
        btn_consumir_aquarius =(ImageButton)findViewById(R.id.btn_consumir_aquarius);
        btn_consumir_agua =(ImageButton)findViewById(R.id.btn_consumir_agua);
        btn_consumir_pizza =(ImageButton)findViewById(R.id.btn_consumir_pizza);
        btn_consumir_candy =(ImageButton)findViewById(R.id.btn_consumir_candy);
        btn_consumir_manzana =(ImageButton)findViewById(R.id.btn_consumir_manzana);

        estado_cocina = findViewById(R.id.estado_cocina);
        camiseta_cocina = findViewById(R.id.camiseta_cocina);
        bambas_cocina = findViewById(R.id.bambas_cocina);
        blink_cocina = findViewById(R.id.blink_cocina);
        gafas_cocina = findViewById(R.id.gafas_cocina);
        gorra_cocina = findViewById(R.id.gorra_cocina);

        txt_cantidad_apple = findViewById(R.id.txt_cantidad_apple);
        txt_cantidad_roncola = findViewById(R.id.txt_cantidad_roncola);
        txt_cantidad_aquarius = findViewById(R.id.txt_cantidad_aquarius);
        txt_cantidad_agua = findViewById(R.id.txt_cantidad_agua);
        txt_cantidad_pizza = findViewById(R.id.txt_cantidad_pizza);
        txt_cantidad_candy = findViewById(R.id.txt_cantidad_candy);

        dinero_cocina = findViewById(R.id.dinero_cocina);
        hambre_cocina = findViewById(R.id.hambre_cocina);
        salud_cocina = findViewById(R.id.salud_cocina);
        diversion_cocina = findViewById(R.id.diversion_cocina);
        sueno_cocina = findViewById(R.id.sueno_cocina);
        titulo_cocina = findViewById(R.id.titulo_cocina);

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