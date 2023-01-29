package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.VO.InformacionPou;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;
import upc.edu.dsa.myapplication.R;

import java.util.*;

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Salon extends AppCompatActivity{

    ImageButton btnLeft, btnRight;
    ImageButton save_partida_salon;

    TextView dinero_salon,hambre_salon,titulo_salon,salud_salon,diversion_salon,sueno_salon;
    ImageView estado_salon,camiseta_salon,bambas_salon,blink_salon,gafas_salon,gorra_salon;

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
        save_partida_salon =(ImageButton)findViewById(R.id.save_partida_salon);

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

            recordPou = Integer.parseInt(infoRecibida.getString("pasarRecordPou"));
            activityOrigen =infoRecibida.getString("pasarActividadOrigen");
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        if("Menu".equals(activityOrigen)){
            pouServices = PouRetrofit.getInstance().getPouServices();
            //Petición para rellenar todos los parametros
            Call<InformacionPou> cargarDatos = pouServices.getInfoAndroidPou(data_correoPou, data_passwordPou);
            cargarDatos.enqueue(new Callback<InformacionPou>() {
                @Override
                public void onResponse(Call<InformacionPou> cargarDatos, Response<InformacionPou> respuestaDatos) {
                    switch (respuestaDatos.code()) {
                        case 201:
                            InformacionPou datosPou = respuestaDatos.body();
                            data_pouId = datosPou.getData_pouId();
                            data_nombrePou = datosPou.getData_nombrePou();
                            data_nacimientoPou = datosPou.getData_nacimientoPou();
                            data_correoPou = datosPou.getData_correoPou();
                            data_passwordPou = datosPou.getData_passwordPou();
                            recordPou = datosPou.getRecordPou();
                            lvlHambre = datosPou.getLvlHambre();
                            lvlSalud = datosPou.getLvlSalud();
                            lvlDiversion = datosPou.getLvlDiversion();
                            lvlSueno = datosPou.getLvlSueno();
                            amountDinero = datosPou.getAmountDinero();
                            amountCandy = datosPou.getAmountCandy();
                            amountManzana = datosPou.getAmountManzana();
                            amountPizza = datosPou.getAmountPizza();
                            amountAgua = datosPou.getAmountAgua();
                            amountAquarius = datosPou.getAmountAquarius();
                            amountRoncola = datosPou.getAmountRoncola();
                            amountHambre = datosPou.getAmountHambre();
                            amountSalud = datosPou.getAmountSalud();
                            amountDiversion = datosPou.getAmountDiversion();
                            amountSueno = datosPou.getAmountSueno();
                            pouCamiseta = datosPou.getPouCamiseta();
                            pouBambas = datosPou.getPouBambas();
                            pouGafas = datosPou.getPouGafas();
                            pouGorro = datosPou.getPouGorro();
                            posee_pijama = datosPou.getPosee_pijama();
                            posee_fcb = datosPou.getPosee_fcb();
                            posee_spain = datosPou.getPosee_spain();
                            posee_messi = datosPou.getPosee_messi();
                            posee_rafa = datosPou.getPosee_rafa();
                            posee_veja = datosPou.getPosee_veja();
                            posee_fiesta = datosPou.getPosee_fiesta();
                            posee_rayban = datosPou.getPosee_rayban();
                            posee_ciclismo = datosPou.getPosee_ciclismo();
                            posee_cerveza = datosPou.getPosee_cerveza();
                            posee_boina = datosPou.getPosee_boina();
                            posee_polo = datosPou.getPosee_polo();

                            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                            // DECLARACIÓN INICIAL DE LOS DATOS.
                            hambre_salon.setText(Integer.toString(lvlHambre));
                            salud_salon.setText(Integer.toString(lvlSalud));
                            diversion_salon.setText(Integer.toString(lvlDiversion));
                            sueno_salon.setText(Integer.toString(lvlSueno));
                            dinero_salon.setText(Integer.toString(amountDinero));

                            String refEstado = "outfit_base_"+pouEstado;
                            estado_salon.setImageResource(getResources().getIdentifier(refEstado, "drawable", getPackageName()));
                            String refCamiseta = "outfit_camiseta_"+pouCamiseta;
                            camiseta_salon.setImageResource(getResources().getIdentifier(refCamiseta, "drawable", getPackageName()));
                            String refBambas = "outfit_bambas_"+pouBambas;
                            bambas_salon.setImageResource(getResources().getIdentifier(refBambas, "drawable", getPackageName()));
                            String refGafas = "outfit_gafas_"+pouGafas;
                            gafas_salon.setImageResource(getResources().getIdentifier(refGafas, "drawable", getPackageName()));
                            String refGorro = "outfit_gorra_"+pouGorro;
                            gorra_salon.setImageResource(getResources().getIdentifier(refGorro, "drawable", getPackageName()));
                            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

                    }
                }
                @Override
                public void onFailure(Call<InformacionPou> cargarDatos, Throwable t) {
                    Log.d("POU"," onFailure", t);
                    StyleableToast.makeText(Activity_Pou_Salon.this, "¡Error!", R.style.exampleToast).show();
                }
            });
            activityOrigen = "Juego";
        }else{
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // DECLARACIÓN INICIAL DE LOS DATOS.
            hambre_salon.setText(Integer.toString(lvlHambre));
            salud_salon.setText(Integer.toString(lvlSalud));
            diversion_salon.setText(Integer.toString(lvlDiversion));
            sueno_salon.setText(Integer.toString(lvlSueno));
            dinero_salon.setText(Integer.toString(amountDinero));

            String refEstado = "outfit_base_"+pouEstado;
            estado_salon.setImageResource(getResources().getIdentifier(refEstado, "drawable", getPackageName()));
            String refCamiseta = "outfit_camiseta_"+pouCamiseta;
            camiseta_salon.setImageResource(getResources().getIdentifier(refCamiseta, "drawable", getPackageName()));
            String refBambas = "outfit_bambas_"+pouBambas;
            bambas_salon.setImageResource(getResources().getIdentifier(refBambas, "drawable", getPackageName()));
            String refGafas = "outfit_gafas_"+pouGafas;
            gafas_salon.setImageResource(getResources().getIdentifier(refGafas, "drawable", getPackageName()));
            String refGorro = "outfit_gorra_"+pouGorro;
            gorra_salon.setImageResource(getResources().getIdentifier(refGorro, "drawable", getPackageName()));
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        }


        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Salon.this, Activity_Pou_Info.class);

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

                Activity_Pou_Salon.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Salon.this, Activity_Pou_Tienda.class);

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

                Activity_Pou_Salon.this.startActivity(myIntent2);
            }
        });
    }

    public void guardarPartida(View view) throws IOException {
        if (view == save_partida_salon) {

            // PONER AQUÍ EL MECANISMO PARA QUE GUARDE LA PARTIDA EN LA BASE DE DATOS.

            StyleableToast.makeText(this, "¡Partida Guardada!", R.style.exampleToast).show();
        }
    }
}
