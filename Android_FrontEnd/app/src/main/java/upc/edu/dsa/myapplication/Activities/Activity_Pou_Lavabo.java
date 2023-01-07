package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.*;

public class Activity_Pou_Lavabo extends AppCompatActivity {

    ImageButton btnLeft, btnRight;

    TextView dinero_lavabo,hambre_lavabo,salud_lavabo,diversion_lavabo,sueno_lavabo,titulo_lavabo;
    ImageView estado_lavabo,camiseta_lavabo,bambas_lavabo,blink_lavabo,gafas_lavabo,gorra_lavabo;
    ImageButton btn_consumir_sueno,btn_consumir_diversion,btn_consumir_salud,btn_consumir_hambre;
    TextView txt_cantidad_sueno,txt_cantidad_diversion,txt_cantidad_salud,txt_cantidad_hambre;

    Timer timer;
    TimerTask timerTask;
    int time = 0;
    boolean timerStarted = false;

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        if (time==7) {
                            blink_lavabo.setVisibility(View.VISIBLE);
                        }
                        if (time==8){
                            blink_lavabo.setVisibility(View.INVISIBLE);
                            time = 0;
                        }
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,300);
    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_lavabo_screen);

        timer = new Timer();
        timerStarted = true;
        time = 0;
        startTimer();

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_lavabo);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_lavabo);

        btn_consumir_sueno =(ImageButton)findViewById(R.id.btn_consumir_sueno);
        btn_consumir_diversion =(ImageButton)findViewById(R.id.btn_consumir_diversion);
        btn_consumir_salud =(ImageButton)findViewById(R.id.btn_consumir_salud);
        btn_consumir_hambre =(ImageButton)findViewById(R.id.btn_consumir_hambre);

        estado_lavabo = findViewById(R.id.estado_lavabo);
        camiseta_lavabo = findViewById(R.id.camiseta_lavabo);
        bambas_lavabo = findViewById(R.id.bambas_lavabo);
        blink_lavabo = findViewById(R.id.blink_lavabo);
        gafas_lavabo = findViewById(R.id.gafas_lavabo);
        gorra_lavabo = findViewById(R.id.gorra_lavabo);

        dinero_lavabo = findViewById(R.id.dinero_lavabo);
        hambre_lavabo = findViewById(R.id.hambre_lavabo);
        salud_lavabo = findViewById(R.id.salud_lavabo);
        diversion_lavabo = findViewById(R.id.diversion_lavabo);
        sueno_lavabo = findViewById(R.id.sueno_lavabo);
        titulo_lavabo = findViewById(R.id.titulo_lavabo);

        txt_cantidad_sueno = findViewById(R.id.txt_cantidad_sueno);
        txt_cantidad_diversion = findViewById(R.id.txt_cantidad_diversion);
        txt_cantidad_salud = findViewById(R.id.txt_cantidad_salud);
        txt_cantidad_hambre = findViewById(R.id.txt_cantidad_hambre);

        blink_lavabo.setVisibility(View.INVISIBLE);

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

    public void clicarPociones(View view) throws IOException {

        if (view==btn_consumir_sueno){
            int cantidadInt = Integer.parseInt(txt_cantidad_sueno.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Lavabo.this, "¡No tienes más Pociones de Sueño!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_sueno.setText(cantidadString);
            }
        }
        if (view==btn_consumir_diversion){
            int cantidadInt = Integer.parseInt(txt_cantidad_diversion.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Lavabo.this, "¡No tienes más Pociones de Diversión!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_diversion.setText(cantidadString);
            }
        }
        if (view==btn_consumir_salud){
            int cantidadInt = Integer.parseInt(txt_cantidad_salud.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Lavabo.this, "¡No tienes más Pociones de Salud!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_salud.setText(cantidadString);
            }
        }
        if (view==btn_consumir_hambre){
            int cantidadInt = Integer.parseInt(txt_cantidad_hambre.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Lavabo.this, "¡No tienes más Pociones de Hambre!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_hambre.setText(cantidadString);
            }
        }
    }
}
