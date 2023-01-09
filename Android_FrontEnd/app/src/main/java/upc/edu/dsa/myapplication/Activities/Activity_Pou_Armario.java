package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import io.github.muddz.styleabletoast.StyleableToast;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

import androidx.recyclerview.widget.RecyclerView;
import upc.edu.dsa.myapplication.Entities.VO.*;
import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Armario extends AppCompatActivity {

    ImageButton btnLeft, btnRight;
    TextView dinero_armario,hambre_armario,salud_armario,diversion_armario,sueno_armario,titulo_armario;
    ImageButton btn_Armario_Camiseta_pijama,btn_Armario_Camiseta_fcb,btn_Armario_Camiseta_spain;
    ImageButton btn_Armario_Bambas_messi,btn_Armario_Bambas_rafa,btn_Armario_Bambas_veja;
    ImageButton btn_Armario_Gafas_fiesta,btn_Armario_Gafas_rayban,btn_Armario_Gafas_ciclismo;
    ImageButton btn_Armario_Gorra_cerveza,btn_Armario_Gorra_boina,btn_Armario_Gorra_polo;
    ImageButton btn_Armario_Camiseta_nada,btn_Armario_Bambas_nada,btn_Armario_Gafas_nada,btn_Armario_Gorra_nada;

    ImageView img_Armario_Camiseta_pijama,img_Armario_Camiseta_fcb,img_Armario_Camiseta_spain;
    ImageView img_Armario_Bambas_messi,img_Armario_Bambas_rafa,img_Armario_Bambas_veja;
    ImageView img_Armario_Gafas_fiesta,img_Armario_Gafas_rayban,img_Armario_Gafas_ciclismo;
    ImageView img_Armario_Gorra_cerveza,img_Armario_Gorra_boina,img_Armario_Gorra_polo;
    ImageView img_Armario_Camiseta_nada,img_Armario_Bambas_nada,img_Armario_Gafas_nada,img_Armario_Gorra_nada;

    RecyclerView rv_articuloTienda;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;

    PouServices pouServices;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_armario_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_armario);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_armario);

        dinero_armario = findViewById(R.id.dinero_armario);
        hambre_armario = findViewById(R.id.hambre_armario);
        salud_armario = findViewById(R.id.salud_armario);
        diversion_armario = findViewById(R.id.diversion_armario);
        sueno_armario = findViewById(R.id.sueno_armario);
        titulo_armario = findViewById(R.id.titulo_armario);

        btn_Armario_Camiseta_pijama =(ImageButton)findViewById(R.id.btn_Armario_Camiseta_pijama);
        btn_Armario_Camiseta_fcb =(ImageButton)findViewById(R.id.btn_Armario_Camiseta_fcb);
        btn_Armario_Camiseta_spain =(ImageButton)findViewById(R.id.btn_Armario_Camiseta_spain);
        btn_Armario_Bambas_messi =(ImageButton)findViewById(R.id.btn_Armario_Bambas_messi);
        btn_Armario_Bambas_rafa =(ImageButton)findViewById(R.id.btn_Armario_Bambas_rafa);
        btn_Armario_Bambas_veja =(ImageButton)findViewById(R.id.btn_Armario_Bambas_veja);
        btn_Armario_Gafas_fiesta =(ImageButton)findViewById(R.id.btn_Armario_Gafas_fiesta);
        btn_Armario_Gafas_rayban =(ImageButton)findViewById(R.id.btn_Armario_Gafas_rayban);
        btn_Armario_Gafas_ciclismo =(ImageButton)findViewById(R.id.btn_Armario_Gafas_ciclismo);
        btn_Armario_Gorra_cerveza =(ImageButton)findViewById(R.id.btn_Armario_Gorra_cerveza);
        btn_Armario_Gorra_boina =(ImageButton)findViewById(R.id.btn_Armario_Gorra_boina);
        btn_Armario_Gorra_polo =(ImageButton)findViewById(R.id.btn_Armario_Gorra_polo);

        btn_Armario_Camiseta_nada =(ImageButton)findViewById(R.id.btn_Armario_Camiseta_nada);
        btn_Armario_Camiseta_nada.setVisibility(View.INVISIBLE);
        btn_Armario_Bambas_nada =(ImageButton)findViewById(R.id.btn_Armario_Bambas_nada);
        btn_Armario_Bambas_nada.setVisibility(View.INVISIBLE);
        btn_Armario_Gafas_nada =(ImageButton)findViewById(R.id.btn_Armario_Gafas_nada);
        btn_Armario_Gafas_nada.setVisibility(View.INVISIBLE);
        btn_Armario_Gorra_nada =(ImageButton)findViewById(R.id.btn_Armario_Gorra_nada);
        btn_Armario_Gorra_nada.setVisibility(View.INVISIBLE);

        img_Armario_Camiseta_pijama =(ImageView) findViewById(R.id.img_Armario_Camiseta_pijama);
        img_Armario_Camiseta_fcb =(ImageView)findViewById(R.id.img_Armario_Camiseta_fcb);
        img_Armario_Camiseta_spain =(ImageView)findViewById(R.id.img_Armario_Camiseta_spain);
        img_Armario_Bambas_messi =(ImageView)findViewById(R.id.img_Armario_Bambas_messi);
        img_Armario_Bambas_rafa =(ImageView)findViewById(R.id.img_Armario_Bambas_rafa);
        img_Armario_Bambas_veja =(ImageView)findViewById(R.id.img_Armario_Bambas_veja);
        img_Armario_Gafas_fiesta =(ImageView)findViewById(R.id.img_Armario_Gafas_fiesta);
        img_Armario_Gafas_rayban =(ImageView)findViewById(R.id.img_Armario_Gafas_rayban);
        img_Armario_Gafas_ciclismo =(ImageView)findViewById(R.id.img_Armario_Gafas_ciclismo);
        img_Armario_Gorra_cerveza =(ImageView)findViewById(R.id.img_Armario_Gorra_cerveza);
        img_Armario_Gorra_boina =(ImageView)findViewById(R.id.img_Armario_Gorra_boina);
        img_Armario_Gorra_polo =(ImageView)findViewById(R.id.img_Armario_Gorra_polo);
        img_Armario_Camiseta_nada =(ImageView)findViewById(R.id.img_Armario_Camiseta_nada);
        img_Armario_Bambas_nada =(ImageView)findViewById(R.id.img_Armario_Bambas_nada);
        img_Armario_Gafas_nada =(ImageView)findViewById(R.id.img_Armario_Gafas_nada);
        img_Armario_Gorra_nada =(ImageView)findViewById(R.id.img_Armario_Gorra_nada);

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
        hambre_armario.setText(Integer.toString(lvlHambre));
        salud_armario.setText(Integer.toString(lvlSalud));
        diversion_armario.setText(Integer.toString(lvlDiversion));
        sueno_armario.setText(Integer.toString(lvlSueno));
        dinero_armario.setText(Integer.toString(amountDinero));

        if (Objects.equals(posee_pijama,"NO")){ // No lo tiene comprado ...
            btn_Armario_Camiseta_pijama.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Camiseta_pijama.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_fcb,"NO")){ // No lo tiene comprado ...
            btn_Armario_Camiseta_fcb.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Camiseta_fcb.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_spain,"NO")){ // No lo tiene comprado ...
            btn_Armario_Camiseta_spain.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Camiseta_spain.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_messi,"NO")){ // No lo tiene comprado ...
            btn_Armario_Bambas_messi.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Bambas_messi.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_rafa,"NO")){ // No lo tiene comprado ...
            btn_Armario_Bambas_rafa.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Bambas_rafa.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_veja,"NO")){ // No lo tiene comprado ...
            btn_Armario_Bambas_veja.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Bambas_veja.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_fiesta,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gafas_fiesta.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gafas_fiesta.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_rayban,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gafas_rayban.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gafas_rayban.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_ciclismo,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gafas_ciclismo.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gafas_ciclismo.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_cerveza,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gorra_cerveza.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gorra_cerveza.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_boina,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gorra_boina.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gorra_boina.setVisibility(View.INVISIBLE);
        }
        if (Objects.equals(posee_polo,"NO")){ // No lo tiene comprado ...
            btn_Armario_Gorra_polo.setVisibility(View.VISIBLE);
        } else { // Lo tiene comprado ...
            btn_Armario_Gorra_polo.setVisibility(View.INVISIBLE);
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Armario.this, Activity_Pou_Tienda.class);

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

                Activity_Pou_Armario.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Armario.this, Activity_Pou_Cocina.class);

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

                Activity_Pou_Armario.this.startActivity(myIntent2);
            }
        });

        /*
        layoutManager = new LinearLayoutManager(this);

        rv_articuloTienda = (RecyclerView) findViewById(R.id.rv_articuloTienda);
        rv_articuloTienda.setLayoutManager(layoutManager);
        rv_articuloTienda.setItemAnimator(new DefaultItemAnimator());

        pouServices = PouRetrofit.getInstance().getPouServices();
        Call<List<ObjetoTienda>> call = pouServices.obtenerObjetosTienda();

        // Probamos la construcción de la tabla ...
        try {
            data = new ArrayList<DataModel>(); // Lista que llenaremos con la información de las diferentes Cards (Data Models).
            data = answersToData(call); // Llamamos a la construcción del vector "data".
            adapter = new Extra_CardAdapter(data); // Pasamos esta información al CardAdapter.
            rv_articuloTienda.setAdapter(adapter); // Llenamos el RecyclerView con la información.
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void clicarArmario(View view) throws IOException{
        // CAMISETAS ...
        if (view==img_Armario_Camiseta_pijama){
            StyleableToast.makeText(this, "Te has puesto el Pijama.", R.style.exampleToast).show();
            pouCamiseta = "pijama";
        }
        if (view==img_Armario_Camiseta_fcb){
            StyleableToast.makeText(this, "Te has puesto la camiseta del Barça.", R.style.exampleToast).show();
            pouCamiseta = "fcb";
        }
        if (view==img_Armario_Camiseta_spain){
            StyleableToast.makeText(this, "Te has puesto la camiseta de España.", R.style.exampleToast).show();
            pouCamiseta = "spain";
        }
        // BAMBAS ...
        if (view==img_Armario_Bambas_messi){
            StyleableToast.makeText(this, "Te has puesto las bambas de Messi.", R.style.exampleToast).show();
            pouBambas = "messi";
        }
        if (view==img_Armario_Bambas_rafa){
            StyleableToast.makeText(this, "Te has puesto las bambas de Rafa Nadal.", R.style.exampleToast).show();
            pouBambas = "rafa";
        }
        if (view==img_Armario_Bambas_veja){
            StyleableToast.makeText(this, "Te has puesto las zapatillas Veja.", R.style.exampleToast).show();
            pouBambas = "veja";
        }
        // GAFAS ...
        if (view==img_Armario_Gafas_fiesta){
            StyleableToast.makeText(this, "Te has puesto las gafas de Fiesta.", R.style.exampleToast).show();
            pouGafas = "fiesta";
        }
        if (view==img_Armario_Gafas_rayban){
            StyleableToast.makeText(this, "Te has puesto las gafas Rayban.", R.style.exampleToast).show();
            pouGafas = "rayban";
        }
        if (view==img_Armario_Gafas_ciclismo){
            StyleableToast.makeText(this, "Te has puesto las gafas de Enric Mas.", R.style.exampleToast).show();
            pouGafas = "ciclismo";
        }
        // GORRAS ...
        if (view==img_Armario_Gorra_cerveza){
            StyleableToast.makeText(this, "Te has puesto el gorro de Estrella Damm.", R.style.exampleToast).show();
            pouGorro = "cerveza";
        }
        if (view==img_Armario_Gorra_boina){
            StyleableToast.makeText(this, "Te has puesto una Boina.", R.style.exampleToast).show();
            pouGorro = "boina";
        }
        if (view==img_Armario_Gorra_polo){
            StyleableToast.makeText(this, "Te has puesto la gorra Polo Ralph Lauren.", R.style.exampleToast).show();
            pouGorro = "polo";
        }
        // NADA ...
        if (view==img_Armario_Camiseta_nada){
            StyleableToast.makeText(this, "Te has quitado la camiseta.", R.style.exampleToast).show();
            pouCamiseta = "nada";
        }
        if (view==img_Armario_Bambas_nada){
            StyleableToast.makeText(this, "Tu Pou ahora está descalzo.", R.style.exampleToast).show();
            pouBambas = "nada";
        }
        if (view==img_Armario_Gafas_nada){
            StyleableToast.makeText(this, "Te has quitado las gafas.", R.style.exampleToast).show();
            pouGafas = "nada";
        }
        if (view==img_Armario_Gorra_nada){
            StyleableToast.makeText(this, "Te has quitado la gorra.", R.style.exampleToast).show();
            pouGorro = "nada";
        }
    }

    /*
    @SuppressLint("SetTextI18n")
    private ArrayList<DataModel> answersToData(Call<List<ObjetoTienda>> call) throws IOException {
        List<ObjetoTienda> objetosTienda = call.execute().body();
        assert objetosTienda != null;
        data = new ArrayList<DataModel>();
        for (ObjetoTienda objetoTienda : objetosTienda) {

            // Se añaden los datos de un artículo a la lista "data".
            data.add(new DataModel(objetoTienda.getArticuloId(),
                    objetoTienda.getNombreArticulo(),
                    Integer.toString((int) objetoTienda.getPrecioArticulo()),
                    objetoTienda.getTipoArticulo(),
                    Integer.toString((int) objetoTienda.getRecargaHambre()),
                    Integer.toString((int) objetoTienda.getRecargaSalud()),
                    Integer.toString((int) objetoTienda.getRecargaDiversion()),
                    Integer.toString((int) objetoTienda.getRecargaSueno())));
        }
        return data;
    }
    */
}
