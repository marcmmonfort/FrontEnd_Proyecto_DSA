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

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE PASAN ENTRE LAS ACTIVITIES.
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
    String pouGafas = "rayban";
    String pouGorro = "cerveza";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @SuppressLint({"CutPasteId", "SetTextI18n"})
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

        // Si venimos desde otra Activity que nos pasa Datos ...
        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){ // A no ser que venda de una actividad que no se le pasa nada ...
            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarSueno"));
        }

        // Declaración de los 4 Estados del Pou ...
        hambre_juego.setText(Integer.toString(lvlHambre));
        salud_juego.setText(Integer.toString(lvlSalud));
        diversion_juego.setText(Integer.toString(lvlDiversion));
        sueno_juego.setText(Integer.toString(lvlSueno));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Juego.this, Activity_Pou_Lavabo.class);
                myIntent1.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Juego.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Juego.this, Activity_Pou_Info.class);
                myIntent2.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent2.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent2.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent2.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Juego.this.startActivity(myIntent2);
            }
        });
    }
}
