package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;
import upc.edu.dsa.myapplication.R;

import java.util.*;

public class Activity_Pou_Salon extends AppCompatActivity{

    ImageButton btnLeft, btnRight;

    TextView dinero_salon,hambre_salon,titulo_salon,salud_salon,diversion_salon,sueno_salon;
    ImageView estado_salon,camiseta_salon,bambas_salon,blink_salon,gafas_salon,gorra_salon;

    Timer timer;
    TimerTask timerTask;
    int time = 0;
    boolean timerStarted = false;

    // Variables Globales con los Niveles del Estado del Pou ...
    int lvlHambre = 55;
    int lvlSalud = 1;
    int lvlDiversion = 0;
    int lvlSueno = 0;

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
                            blink_salon.setVisibility(View.VISIBLE);
                        }
                        if (time==8){
                            blink_salon.setVisibility(View.INVISIBLE);
                            time = 0;
                        }
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,300);
    }

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_salon_screen);

        timer = new Timer();
        timerStarted = true;
        time = 0;
        startTimer();

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_salon);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_salon);

        dinero_salon = findViewById(R.id.dinero_salon);
        hambre_salon = findViewById(R.id.hambre_salon);
        titulo_salon = findViewById(R.id.titulo_salon);
        salud_salon = findViewById(R.id.salud_salon);
        diversion_salon = findViewById(R.id.diversion_salon);
        sueno_salon = findViewById(R.id.sueno_salon);

        estado_salon = findViewById(R.id.estado_salon);
        camiseta_salon = findViewById(R.id.camiseta_salon);
        bambas_salon = findViewById(R.id.bambas_salon);
        blink_salon = findViewById(R.id.blink_salon);
        gafas_salon = findViewById(R.id.gafas_salon);
        gorra_salon = findViewById(R.id.gorra_salon);

        blink_salon.setVisibility(View.INVISIBLE);

        // Declaración de los 4 Estados del Pou ...
        hambre_salon.setText(Integer.toString(lvlHambre));
        salud_salon.setText(Integer.toString(lvlSalud));
        diversion_salon.setText(Integer.toString(lvlDiversion));
        sueno_salon.setText(Integer.toString(lvlSueno));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Salon.this, Activity_Pou_Info.class);
                myIntent1.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Salon.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Salon.this, Activity_Pou_Tienda.class);
                myIntent2.putExtra("lvlHambre",lvlHambre);
                myIntent2.putExtra("lvlSalud",lvlSalud);
                myIntent2.putExtra("lvlDiversion",lvlDiversion);
                myIntent2.putExtra("lvlSueno",lvlSueno);
                Activity_Pou_Salon.this.startActivity(myIntent2);
            }
        });
    }
}
