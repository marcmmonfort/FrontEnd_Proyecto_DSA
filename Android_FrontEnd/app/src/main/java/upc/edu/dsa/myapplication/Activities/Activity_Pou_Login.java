package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.android.material.textfield.TextInputEditText;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.Entities.VO.InformacionPou;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;
import android.content.SharedPreferences;

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Login extends AppCompatActivity implements View.OnClickListener{

    TextView login_textLasAventurasDe;
    Button login_botonHacerLogin, login_backHome;
    TextInputEditText login_correoPou, login_passwordPou;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE VAN A PASAR AL SALÓN SI EL LOGIN ES CORRECTO.
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
    String activityOrigen = "Menu";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_login_screen);

        login_textLasAventurasDe = findViewById(R.id.login_textLasAventurasDe);

        login_botonHacerLogin = (Button) findViewById(R.id.login_botonHacerLogin);

        login_correoPou = findViewById(R.id.login_correoPou);
        login_passwordPou = findViewById(R.id.login_passwordPou);

        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
        login_correoPou.setText(preferences.getString("mail",""));
        login_passwordPou.setText(preferences.getString("password",""));
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(Activity_Pou_Login.this, Activity_Pou_Home.class);
        Activity_Pou_Login.this.startActivity(myIntent1);
    }

    public void loginPou(View view) {
        pouServices = PouRetrofit.getInstance().getPouServices();

        Credenciales nuevasCredenciales = new Credenciales(login_correoPou.getText().toString(), login_passwordPou.getText().toString());
        Call<Void> peticion = pouServices.login(nuevasCredenciales);

        peticion.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> peticion, Response<Void> respuesta) {
                switch (respuesta.code()){
                    case 200:
                        // Guardamos esta información de login ...
                        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
                        SharedPreferences.Editor Obj_editor=preferencias.edit();
                        Obj_editor.putString("mail",login_correoPou.getText().toString());
                        Obj_editor.putString("password",login_passwordPou.getText().toString());
                        Obj_editor.putBoolean("isLogged",true);
                        Obj_editor.apply();

                        data_correoPou = login_correoPou.getText().toString();
                        data_passwordPou = login_passwordPou.getText().toString();

                        // Login del Pou satisfactorio. Nos dirigimos al menú principal.
                        StyleableToast.makeText(Activity_Pou_Login.this, "¡Has accedido correctamente a tu Pou!", R.style.exampleToast).show();
                        // Nos vamos al Home.
                        Intent myIntent1 = new Intent(Activity_Pou_Login.this, Activity_Pou_Salon.class);


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
                        myIntent1.putExtra("pasarDataPasswordPou",data_passwordPou);

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
                        myIntent1.putExtra("pasarActividadOrigen", activityOrigen);



                        Activity_Pou_Login.this.startActivity(myIntent1);

                        break;
                    case 404:
                        // El correo no existe. Nos dirigimos al registro.
                        StyleableToast.makeText(Activity_Pou_Login.this, "¡El 'correo' introducido no tiene ningún Pou asociado!", R.style.exampleToast).show();
                        Intent myIntent2 = new Intent(Activity_Pou_Login.this, Activity_Pou_Register.class);
                        Activity_Pou_Login.this.startActivity(myIntent2);
                        break;
                    case 405:
                        // Contraseña incorrecta.
                        StyleableToast.makeText(Activity_Pou_Login.this, "¡La 'contraseña' introducida no es la correcta!", R.style.exampleToast).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> peticion, Throwable t) {
                Log.d("POU"," onFailure", t);
                StyleableToast.makeText(Activity_Pou_Login.this, "¡Error!", R.style.exampleToast).show();
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
