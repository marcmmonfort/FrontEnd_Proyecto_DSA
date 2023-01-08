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

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE PASAN ENTRE LAS ACTIVITIES.
    String data_pouId = "marc";
    String data_nombrePou = "Marc";
    String data_nacimientoPou = "28/10/2001";
    String data_correoPou = "marc@gmail.com";
    int recordPou = 0;
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
    String posee_pijama = "NO";
    String posee_fcb = "NO";
    String posee_spain = "NO";
    String posee_messi = "NO";
    String posee_rafa = "NO";
    String posee_veja = "NO";
    String posee_fiesta = "NO";
    String posee_rayban = "NO";
    String posee_ciclismo = "NO";
    String posee_cerveza = "NO";
    String posee_boina = "NO";
    String posee_polo = "NO";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

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

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // RECEPCIÓN DE DATOS DESDE OTRA ACTIVIDAD
        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){
            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarNivelHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarNivelSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarNivelDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarNivelSueno"));
            amountDinero = Integer.parseInt(infoRecibida.getString("pasarDinero"));

            amountCandy = Integer.parseInt(infoRecibida.getString("pasarCandy"));
            amountManzana = Integer.parseInt(infoRecibida.getString("pasarManzana"));
            amountPizza = Integer.parseInt(infoRecibida.getString("pasarPizza"));
            amountAgua = Integer.parseInt(infoRecibida.getString("pasarAgua"));
            amountAquarius = Integer.parseInt(infoRecibida.getString("pasarAquarius"));
            amountRoncola = Integer.parseInt(infoRecibida.getString("pasarRoncola"));

            amountHambre = Integer.parseInt(infoRecibida.getString("pasarPocionHambre"));
            amountSalud = Integer.parseInt(infoRecibida.getString("pasarPocionSalud"));
            amountDiversion = Integer.parseInt(infoRecibida.getString("pasarPocionDiversion"));
            amountSueno = Integer.parseInt(infoRecibida.getString("pasarPocionSueno"));

            pouEstado = infoRecibida.getString("pasarPouEstado");
            pouCamiseta = infoRecibida.getString("pasarPouCamiseta");
            pouBambas = infoRecibida.getString("pasarPouBambas");
            pouGafas = infoRecibida.getString("pasarPouGafas");
            pouGorro = infoRecibida.getString("pasarPouGorro");
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // DECLARACIÓN INICIAL DE LOS DATOS.
        hambre_cocina.setText(Integer.toString(lvlHambre));
        salud_cocina.setText(Integer.toString(lvlSalud));
        diversion_cocina.setText(Integer.toString(lvlDiversion));
        sueno_cocina.setText(Integer.toString(lvlSueno));
        dinero_cocina.setText(Integer.toString(amountDinero));

        // Place HERE the Dood, Drinks and Potions declared (if necessary).

        String refEstado = "outfit_base_"+pouEstado;
        estado_cocina.setImageResource(getResources().getIdentifier(refEstado, "drawable", getPackageName()));
        String refCamiseta = "outfit_camiseta_"+pouCamiseta;
        camiseta_cocina.setImageResource(getResources().getIdentifier(refCamiseta, "drawable", getPackageName()));
        String refBambas = "outfit_bambas_"+pouBambas;
        bambas_cocina.setImageResource(getResources().getIdentifier(refBambas, "drawable", getPackageName()));
        String refGafas = "outfit_gafas_"+pouGafas;
        gafas_cocina.setImageResource(getResources().getIdentifier(refGafas, "drawable", getPackageName()));
        String refGorro = "outfit_gorra_"+pouGorro;
        gorra_cocina.setImageResource(getResources().getIdentifier(refGorro, "drawable", getPackageName()));
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Armario.class);

                myIntent1.putExtra("pasarNivelHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarNivelSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarNivelDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarNivelSueno",Integer.toString(lvlSueno));
                myIntent1.putExtra("pasarDinero",Integer.toString(amountDinero));

                myIntent1.putExtra("pasarCandy",Integer.toString(amountCandy));
                myIntent1.putExtra("pasarManzana",Integer.toString(amountManzana));
                myIntent1.putExtra("pasarPizza",Integer.toString(amountPizza));
                myIntent1.putExtra("pasarAgua",Integer.toString(amountAgua));
                myIntent1.putExtra("pasarAquarius",Integer.toString(amountAquarius));
                myIntent1.putExtra("pasarRoncola",Integer.toString(amountRoncola));

                myIntent1.putExtra("pasarPocionHambre",Integer.toString(amountHambre));
                myIntent1.putExtra("pasarPocionSalud",Integer.toString(amountSalud));
                myIntent1.putExtra("pasarPocionDiversion",Integer.toString(amountDiversion));
                myIntent1.putExtra("pasarPocionSueno",Integer.toString(amountSueno));

                myIntent1.putExtra("pasarPouEstado",pouEstado);
                myIntent1.putExtra("pasarPouCamiseta",pouCamiseta);
                myIntent1.putExtra("pasarPouBambas",pouBambas);
                myIntent1.putExtra("pasarPouGafas",pouGafas);
                myIntent1.putExtra("pasarPouGorro",pouGorro);

                Activity_Pou_Cocina.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Cocina.this, Activity_Pou_Lavabo.class);

                myIntent2.putExtra("pasarNivelHambre",Integer.toString(lvlHambre));
                myIntent2.putExtra("pasarNivelSalud",Integer.toString(lvlSalud));
                myIntent2.putExtra("pasarNivelDiversion",Integer.toString(lvlDiversion));
                myIntent2.putExtra("pasarNivelSueno",Integer.toString(lvlSueno));
                myIntent2.putExtra("pasarDinero",Integer.toString(amountDinero));

                myIntent2.putExtra("pasarCandy",Integer.toString(amountCandy));
                myIntent2.putExtra("pasarManzana",Integer.toString(amountManzana));
                myIntent2.putExtra("pasarPizza",Integer.toString(amountPizza));
                myIntent2.putExtra("pasarAgua",Integer.toString(amountAgua));
                myIntent2.putExtra("pasarAquarius",Integer.toString(amountAquarius));
                myIntent2.putExtra("pasarRoncola",Integer.toString(amountRoncola));

                myIntent2.putExtra("pasarPocionHambre",Integer.toString(amountHambre));
                myIntent2.putExtra("pasarPocionSalud",Integer.toString(amountSalud));
                myIntent2.putExtra("pasarPocionDiversion",Integer.toString(amountDiversion));
                myIntent2.putExtra("pasarPocionSueno",Integer.toString(amountSueno));

                myIntent2.putExtra("pasarPouEstado",pouEstado);
                myIntent2.putExtra("pasarPouCamiseta",pouCamiseta);
                myIntent2.putExtra("pasarPouBambas",pouBambas);
                myIntent2.putExtra("pasarPouGafas",pouGafas);
                myIntent2.putExtra("pasarPouGorro",pouGorro);

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