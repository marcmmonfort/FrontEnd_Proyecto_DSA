package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Activity_Pou_Info extends AppCompatActivity {

    ImageButton btnLeft, btnRight;
    Button botonLogout;
    TextView titulo_correo_info,correo_info,titulo_nacimiento_info,nacimiento_info,titulo_nombre_info,nombre_info,titulo_id_info,id_info;
    TextView diversion_info,titulo_info,hambre_info,dinero_info,sueno_info,salud_info;

    // Variables Globales con los Niveles del Estado del Pou ...
    int lvlHambre = 0;
    int lvlSalud = 0;
    int lvlDiversion = 0;
    int lvlSueno = 0;

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_info_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_info);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_info);
        botonLogout = (Button) findViewById(R.id.botonLogout);

        titulo_correo_info = findViewById(R.id.titulo_correo_info);
        correo_info = findViewById(R.id.correo_info);
        titulo_nacimiento_info = findViewById(R.id.titulo_nacimiento_info);
        nacimiento_info = findViewById(R.id.nacimiento_info);
        titulo_nombre_info = findViewById(R.id.titulo_nombre_info);
        nombre_info = findViewById(R.id.nombre_info);
        titulo_id_info = findViewById(R.id.titulo_id_info);
        id_info = findViewById(R.id.id_info);

        diversion_info = findViewById(R.id.diversion_info);
        titulo_info = findViewById(R.id.titulo_info);
        hambre_info = findViewById(R.id.hambre_info);
        dinero_info = findViewById(R.id.dinero_info);
        sueno_info = findViewById(R.id.sueno_info);
        salud_info = findViewById(R.id.salud_info);

        // Si venimos desde otra Activity que nos pasa Datos ...
        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){ // A no ser que venda de una actividad que no se le pasa nada ...
            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarSueno"));
        }

        // Declaración de los 4 Estados del Pou ...
        hambre_info.setText(Integer.toString(lvlHambre));
        salud_info.setText(Integer.toString(lvlSalud));
        diversion_info.setText(Integer.toString(lvlDiversion));
        sueno_info.setText(Integer.toString(lvlSueno));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Info.this, Activity_Pou_Juego.class);
                myIntent1.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Info.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Info.this, Activity_Pou_Salon.class);
                myIntent2.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent2.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent2.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent2.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Info.this.startActivity(myIntent2);
            }
        });
    }

    public void clickLogout(View view) throws IOException {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putBoolean("isLogged",false);
        Obj_editor.apply();
        Intent i = new Intent(Activity_Pou_Info.this, Activity_Pou_Home.class);
        startActivity(i);
    }
}