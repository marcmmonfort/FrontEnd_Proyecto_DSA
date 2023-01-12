package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
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

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Cocina extends AppCompatActivity{

    ImageButton btnLeft, btnRight, btn_info_candy, btn_info_manzana, btn_info_pizza, btn_info_agua, btn_info_aquarius, btn_info_roncola;

    TextView txt_cantidad_apple,txt_cantidad_roncola,txt_cantidad_aquarius,txt_cantidad_agua,txt_cantidad_pizza,txt_cantidad_candy;
    TextView dinero_cocina,hambre_cocina,salud_cocina,diversion_cocina,sueno_cocina,titulo_cocina;
    ImageView estado_cocina,camiseta_cocina,bambas_cocina,blink_cocina,gafas_cocina,gorra_cocina;
    ImageButton btn_consumir_roncola,btn_consumir_aquarius,btn_consumir_agua,btn_consumir_pizza,btn_consumir_candy,btn_consumir_manzana;

    PouServices pouServices;

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
    String data_passwordPou = "Calella";
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
    String pouGafas = "nada";
    String pouGorro = "cerveza";
    String posee_pijama = "NO";
    String posee_fcb = "NO";
    String posee_spain = "YES";
    String posee_messi = "NO";
    String posee_rafa = "YES";
    String posee_veja = "NO";
    String posee_fiesta = "NO";
    String posee_rayban = "YES";
    String posee_ciclismo = "NO";
    String posee_cerveza = "YES";
    String posee_boina = "NO";
    String posee_polo = "YES";
    String activityOrigen = "Juego";
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

        btnLeft = (ImageButton)findViewById(R.id.btn_izquierda_cocina);
        btnRight = (ImageButton)findViewById(R.id.btn_derecha_cocina);
        btn_info_candy = (ImageButton)findViewById(R.id.btn_info_candy);
        btn_info_manzana  = (ImageButton)findViewById(R.id.btn_info_manzana);
        btn_info_pizza = (ImageButton)findViewById(R.id.btn_info_pizza);
        btn_info_agua = (ImageButton)findViewById(R.id.btn_info_agua);
        btn_info_aquarius = (ImageButton)findViewById(R.id.btn_info_aquarius);
        btn_info_roncola = (ImageButton)findViewById(R.id.btn_info_roncola);

        btn_consumir_roncola = (ImageButton)findViewById(R.id.btn_consumir_roncola);
        btn_consumir_aquarius =( ImageButton)findViewById(R.id.btn_consumir_aquarius);
        btn_consumir_agua = (ImageButton)findViewById(R.id.btn_consumir_agua);
        btn_consumir_pizza = (ImageButton)findViewById(R.id.btn_consumir_pizza);
        btn_consumir_candy = (ImageButton)findViewById(R.id.btn_consumir_candy);
        btn_consumir_manzana = (ImageButton)findViewById(R.id.btn_consumir_manzana);

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

            data_pouId = infoRecibida.getString("pasarDataPouId");
            data_nombrePou = infoRecibida.getString("pasarDataNombrePou");
            data_nacimientoPou = infoRecibida.getString("pasarDataNacimientoPou");
            data_correoPou = infoRecibida.getString("pasarDataCorreoPou");
            data_passwordPou = infoRecibida.getString("pasarDataPasswordPou");

            posee_pijama = infoRecibida.getString("pasarPoseePijama");
            posee_fcb = infoRecibida.getString("pasarPoseeFcb");
            posee_spain = infoRecibida.getString("pasarPoseeSpain");
            posee_messi = infoRecibida.getString("pasarPoseeMessi");
            posee_rafa = infoRecibida.getString("pasarPoseeRafa");
            posee_veja = infoRecibida.getString("pasarPoseeVeja");
            posee_fiesta = infoRecibida.getString("pasarPoseeFiesta");
            posee_rayban = infoRecibida.getString("pasarPoseeRayban");
            posee_ciclismo = infoRecibida.getString("pasarPoseeCiclismo");
            posee_cerveza = infoRecibida.getString("pasarPoseeCerveza");
            posee_boina = infoRecibida.getString("pasarPoseeBoina");
            posee_polo = infoRecibida.getString("pasarPoseePolo");

            recordPou= Integer.parseInt(infoRecibida.getString("pasarRecordPou"));
            activityOrigen =infoRecibida.getString("pasarActividadOrigen");
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // DECLARACIÓN INICIAL DE LOS DATOS.
        hambre_cocina.setText(Integer.toString(lvlHambre));
        salud_cocina.setText(Integer.toString(lvlSalud));
        diversion_cocina.setText(Integer.toString(lvlDiversion));
        sueno_cocina.setText(Integer.toString(lvlSueno));
        dinero_cocina.setText(Integer.toString(amountDinero));

        txt_cantidad_apple.setText(Integer.toString(amountManzana));
        txt_cantidad_candy.setText(Integer.toString(amountCandy));
        txt_cantidad_pizza.setText(Integer.toString(amountPizza));
        txt_cantidad_roncola.setText(Integer.toString(amountRoncola));
        txt_cantidad_agua.setText(Integer.toString(amountAgua));
        txt_cantidad_aquarius.setText(Integer.toString(amountAquarius));

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

                myIntent1.putExtra("pasarDataPouId",data_pouId);
                myIntent1.putExtra("pasarDataNombrePou",data_nombrePou);
                myIntent1.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
                myIntent1.putExtra("pasarDataCorreoPou",data_correoPou);
                myIntent1.putExtra("pasarDataPasswordPou", data_passwordPou);


                myIntent1.putExtra("pasarPoseePijama",posee_pijama);
                myIntent1.putExtra("pasarPoseeFcb",posee_fcb);
                myIntent1.putExtra("pasarPoseeSpain",posee_spain);
                myIntent1.putExtra("pasarPoseeMessi",posee_messi);
                myIntent1.putExtra("pasarPoseeRafa",posee_rafa);
                myIntent1.putExtra("pasarPoseeVeja",posee_veja);
                myIntent1.putExtra("pasarPoseeFiesta",posee_fiesta);
                myIntent1.putExtra("pasarPoseeRayban",posee_rayban);
                myIntent1.putExtra("pasarPoseeCiclismo",posee_ciclismo);
                myIntent1.putExtra("pasarPoseeCerveza",posee_cerveza);
                myIntent1.putExtra("pasarPoseeBoina",posee_boina);
                myIntent1.putExtra("pasarPoseePolo",posee_polo);

                myIntent1.putExtra("pasarRecordPou",Integer.toString(recordPou));
                myIntent1.putExtra("pasarActividadOrigen",activityOrigen);

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

                myIntent2.putExtra("pasarDataPouId",data_pouId);
                myIntent2.putExtra("pasarDataNombrePou",data_nombrePou);
                myIntent2.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
                myIntent2.putExtra("pasarDataCorreoPou",data_correoPou);
                myIntent2.putExtra("pasarDataPasswordPou", data_passwordPou);

                myIntent2.putExtra("pasarPoseePijama",posee_pijama);
                myIntent2.putExtra("pasarPoseeFcb",posee_fcb);
                myIntent2.putExtra("pasarPoseeSpain",posee_spain);
                myIntent2.putExtra("pasarPoseeMessi",posee_messi);
                myIntent2.putExtra("pasarPoseeRafa",posee_rafa);
                myIntent2.putExtra("pasarPoseeVeja",posee_veja);
                myIntent2.putExtra("pasarPoseeFiesta",posee_fiesta);
                myIntent2.putExtra("pasarPoseeRayban",posee_rayban);
                myIntent2.putExtra("pasarPoseeCiclismo",posee_ciclismo);
                myIntent2.putExtra("pasarPoseeCerveza",posee_cerveza);
                myIntent2.putExtra("pasarPoseeBoina",posee_boina);
                myIntent2.putExtra("pasarPoseePolo",posee_polo);

                myIntent2.putExtra("pasarRecordPou",Integer.toString(recordPou));
                myIntent2.putExtra("pasarActividadOrigen",activityOrigen);

                Activity_Pou_Cocina.this.startActivity(myIntent2);
            }
        });
    }

    public void clicarConsumible(View view) throws IOException {

        if (view==btn_consumir_roncola){
            int cantidadInt = Integer.parseInt(txt_cantidad_roncola.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Roncola!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_roncola.setText(cantidadString);
                amountRoncola = cantidadInt;
                // (1) HAMBRE ... +0
                // (2) SALUD ... +0
                // (3) DIVERSIÓN ... +10
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 10;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ... +0
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }
        if (view==btn_consumir_aquarius){
            int cantidadInt = Integer.parseInt(txt_cantidad_aquarius.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Aquarius!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_aquarius.setText(cantidadString);
                amountAquarius = cantidadInt;
                // (1) HAMBRE ... +0
                // (2) SALUD ... +5
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 5;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ... +0
                // (4) SUEÑO ... +10
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 10;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }
        if (view==btn_consumir_agua){
            int cantidadInt = Integer.parseInt(txt_cantidad_agua.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Agua!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_agua.setText(cantidadString);
                amountAgua = cantidadInt;
                // (1) HAMBRE ... +0
                // (2) SALUD ... +10
                int cantidadSaludInt = Integer.parseInt(salud_cocina.getText().toString());
                cantidadSaludInt = cantidadSaludInt + 10;
                if (cantidadSaludInt>100){ cantidadSaludInt = 100; }
                String cantidadSaludString = Integer.toString(cantidadSaludInt);
                salud_cocina.setText(cantidadSaludString);
                lvlSalud = cantidadSaludInt;
                // (3) DIVERSIÓN ... +0
                // (4) SUEÑO ... +0
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }
        if (view==btn_consumir_pizza){
            int cantidadInt = Integer.parseInt(txt_cantidad_pizza.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Pizzas!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_pizza.setText(cantidadString);
                amountPizza = cantidadInt;
                // (1) HAMBRE ... +40
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 40;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ... +0
                // (3) DIVERSIÓN ... +5
                int cantidadDiversionInt = Integer.parseInt(diversion_cocina.getText().toString());
                cantidadDiversionInt = cantidadDiversionInt + 5;
                if (cantidadDiversionInt>100){ cantidadDiversionInt = 100; }
                String cantidadDiversionString = Integer.toString(cantidadDiversionInt);
                diversion_cocina.setText(cantidadDiversionString);
                lvlDiversion = cantidadDiversionInt;
                // (4) SUEÑO ... +0
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }
        if (view==btn_consumir_candy){
            int cantidadInt = Integer.parseInt(txt_cantidad_candy.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Candy!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_candy.setText(cantidadString);
                amountCandy = cantidadInt;
                // (1) HAMBRE ... +10
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 10;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ... +0
                // (3) DIVERSIÓN ... +0
                // (4) SUEÑO ... +5
                int cantidadSuenoInt = Integer.parseInt(sueno_cocina.getText().toString());
                cantidadSuenoInt = cantidadSuenoInt + 5;
                if (cantidadSuenoInt>100){ cantidadSuenoInt = 100; }
                String cantidadSuenoString = Integer.toString(cantidadSuenoInt);
                sueno_cocina.setText(cantidadSuenoString);
                lvlSueno = cantidadSuenoInt;
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }
        if (view==btn_consumir_manzana){
            int cantidadInt = Integer.parseInt(txt_cantidad_apple.getText().toString());
            cantidadInt = cantidadInt - 1;
            if (cantidadInt<0){ // Si no hay más.
                StyleableToast.makeText(this, "¡Debes comprar Manzanas!", R.style.exampleToast).show();
            }
            else{ // Si hay suficientes ...
                String cantidadString = Integer.toString(cantidadInt);
                txt_cantidad_apple.setText(cantidadString);
                amountManzana = cantidadInt;
                // (1) HAMBRE ... +20
                int cantidadHambreInt = Integer.parseInt(hambre_cocina.getText().toString());
                cantidadHambreInt = cantidadHambreInt + 20;
                if (cantidadHambreInt>100){ cantidadHambreInt = 100; }
                String cantidadHambreString = Integer.toString(cantidadHambreInt);
                hambre_cocina.setText(cantidadHambreString);
                lvlHambre = cantidadHambreInt;
                // (2) SALUD ... +0
                // (3) DIVERSIÓN ... +0
                // (4) SUEÑO ... +0
                setCaraPouSegunEstados (lvlHambre, lvlSalud, lvlDiversion, lvlSueno);
            }
        }

    }

    public void setCaraPouSegunEstados (int h, int sa, int d, int su){
        if (d < 50){
            pouEstado = "aburrido";
        }
        if (su < 50){
            pouEstado = "cansado";
        }
        if (h < 50){
            pouEstado = "hambriento";
        }
        if (sa < 50){
            pouEstado = "enfermo";
        }
        if ((sa >= 50)&&(h >= 50)&&(su >= 50)&&(d >= 50)){
            pouEstado = "normal";
        }
        String refEstado = "outfit_base_"+pouEstado;
        estado_cocina.setImageResource(getResources().getIdentifier(refEstado, "drawable", getPackageName()));
    }

    public void darInfoArticulo(View view){

        String articuloID = null;
        if (view==btn_info_candy){
            articuloID = "candy";
        }
        if(view==btn_info_manzana){
            articuloID = "manzana";
        }
        if(view==btn_info_pizza){
            articuloID = "pizza";
        }
        if(view==btn_info_agua){
            articuloID = "agua";
        }
        if(view==btn_info_aquarius){
            articuloID = "aquarius";
        }
        if(view==btn_info_roncola){
            articuloID = "roncola";
        }

        Intent myIntent1 = new Intent(Activity_Pou_Cocina.this, Extra_Popup.class);

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

        myIntent1.putExtra("pasarDataPouId",data_pouId);
        myIntent1.putExtra("pasarDataNombrePou",data_nombrePou);
        myIntent1.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
        myIntent1.putExtra("pasarDataCorreoPou",data_correoPou);
        myIntent1.putExtra("pasarDataPasswordPou", data_passwordPou);


        myIntent1.putExtra("pasarPoseePijama",posee_pijama);
        myIntent1.putExtra("pasarPoseeFcb",posee_fcb);
        myIntent1.putExtra("pasarPoseeSpain",posee_spain);
        myIntent1.putExtra("pasarPoseeMessi",posee_messi);
        myIntent1.putExtra("pasarPoseeRafa",posee_rafa);
        myIntent1.putExtra("pasarPoseeVeja",posee_veja);
        myIntent1.putExtra("pasarPoseeFiesta",posee_fiesta);
        myIntent1.putExtra("pasarPoseeRayban",posee_rayban);
        myIntent1.putExtra("pasarPoseeCiclismo",posee_ciclismo);
        myIntent1.putExtra("pasarPoseeCerveza",posee_cerveza);
        myIntent1.putExtra("pasarPoseeBoina",posee_boina);
        myIntent1.putExtra("pasarPoseePolo",posee_polo);

        myIntent1.putExtra("pasarRecordPou",Integer.toString(recordPou));
        myIntent1.putExtra("pasarActividadOrigen",activityOrigen);

        myIntent1.putExtra("articuloID",articuloID);

        Activity_Pou_Cocina.this.startActivity(myIntent1);

    }


}