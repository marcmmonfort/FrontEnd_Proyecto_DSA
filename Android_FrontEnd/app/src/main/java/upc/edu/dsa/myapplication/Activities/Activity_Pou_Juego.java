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

import org.w3c.dom.Text;

import upc.edu.dsa.myapplication.Entities.VO.*;

public class Activity_Pou_Juego extends AppCompatActivity{

    ImageButton btnLeft, btnRight;

    TextView titulo_diasSinMorir,titulo_record,titulo_informativo;
    TextView numero_diasSinMorir;
    TextView dinero_juego,hambre_juego,salud_juego,diversion_juego,sueno_juego,titulo_juego;
    ImageButton btn_iniciarJuego;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_juego_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_juego);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_juego);

        titulo_diasSinMorir = findViewById(R.id.titulo_diasSinMorir);
        titulo_record = findViewById(R.id.titulo_record);
        titulo_informativo = findViewById(R.id.titulo_informativo);

        numero_diasSinMorir = findViewById(R.id.numero_diasSinMorir);

        dinero_juego = findViewById(R.id.dinero_juego);
        hambre_juego = findViewById(R.id.hambre_juego);
        salud_juego = findViewById(R.id.salud_juego);
        diversion_juego = findViewById(R.id.diversion_juego);
        sueno_juego = findViewById(R.id.sueno_juego);
        titulo_juego = findViewById(R.id.titulo_juego);

        btn_iniciarJuego =(ImageButton)findViewById(R.id.btn_iniciarJuego);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Juego.this, Activity_Pou_Lavabo.class);
                Activity_Pou_Juego.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Juego.this, Activity_Pou_Info.class);
                Activity_Pou_Juego.this.startActivity(myIntent2);
            }
        });
    }
}
