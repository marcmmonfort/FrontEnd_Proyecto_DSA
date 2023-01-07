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

public class Activity_Pou_Cocina extends AppCompatActivity{

    ImageButton btnLeft, btnRight;

    TextView txt_cantidad_apple,txt_cantidad_roncola,txt_cantidad_aquarius,txt_cantidad_agua,txt_cantidad_pizza,txt_cantidad_candy;
    TextView dinero_cocina,hambre_cocina,salud_cocina,diversion_cocina,sueno_cocina,titulo_cocina;
    ImageView estado_cocina,camiseta_cocina,bambas_cocina,blink_cocina,gafas_cocina,gorra_cocina;
    ImageButton btn_consumir_roncola,btn_consumir_aquarius,btn_consumir_agua,btn_consumir_pizza,btn_consumir_candy,btn_consumir_manzana;

    Timer timer;
    TimerTask timerTask;
    int time = 0;
    boolean timerStarted = false;

    // Variables Globales con los Niveles del Estado del Pou ...
    int lvlHambre = 0;
    int lvlSalud = 0;
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
                            blink_cocina.setVisibility(View.VISIBLE);
                        }
                        if (time==8){
                            blink_cocina.setVisibility(View.INVISIBLE);
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
        setContentView(R.layout.pou_cocina_screen);

        timer = new Timer();
        timerStarted = true;
        time = 0;
        startTimer();

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

        blink_cocina.setVisibility(View.INVISIBLE);

        // Si venimos desde otra Activity que nos pasa Datos ...
        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){ // A no ser que venda de una actividad que no se le pasa nada ...
            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarSueno"));
        }

        // Declaración de los 4 Estados del Pou ...
        hambre_cocina.setText(Integer.toString(lvlHambre));
        salud_cocina.setText(Integer.toString(lvlSalud));
        diversion_cocina.setText(Integer.toString(lvlDiversion));
        sueno_cocina.setText(Integer.toString(lvlSueno));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Armario.class);
                myIntent1.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Cocina.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Lavabo.class);
                myIntent2.putExtra("pasarHambre",Integer.toString(lvlHambre));
                myIntent2.putExtra("pasarSalud",Integer.toString(lvlSalud));
                myIntent2.putExtra("pasarDiversion",Integer.toString(lvlDiversion));
                myIntent2.putExtra("pasarSueno",Integer.toString(lvlSueno));
                Activity_Pou_Cocina.this.startActivity(myIntent2);
            }
        });
    }

    public void clicarConsumible(View view) throws IOException {

        if (view==btn_consumir_roncola){
            int cantidadInt = Integer.parseInt(txt_cantidad_roncola.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Ron Cola!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_roncola.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
        if (view==btn_consumir_aquarius){
            int cantidadInt = Integer.parseInt(txt_cantidad_aquarius.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Aquarius!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_aquarius.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
        if (view==btn_consumir_agua){
            int cantidadInt = Integer.parseInt(txt_cantidad_agua.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Agua!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_agua.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
        if (view==btn_consumir_pizza){
            int cantidadInt = Integer.parseInt(txt_cantidad_pizza.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Pizzas!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_pizza.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
        if (view==btn_consumir_candy){
            int cantidadInt = Integer.parseInt(txt_cantidad_candy.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Candy!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_candy.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
        if (view==btn_consumir_manzana){
            int cantidadInt = Integer.parseInt(txt_cantidad_apple.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                Toast debesComprar = Toast.makeText(Activity_Pou_Cocina.this, "¡No tienes más Manzanas!", Toast.LENGTH_SHORT);
                debesComprar.show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_apple.setText(cantidadString);
                // (1) HAMBRE ...
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ...
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 20;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ...
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 20;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ...
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 20;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
            }
        }
    }
}