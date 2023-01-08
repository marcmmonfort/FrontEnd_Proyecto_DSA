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
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;
import android.content.SharedPreferences;

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Login extends AppCompatActivity implements View.OnClickListener{

    TextView login_textLasAventurasDe;
    Button login_botonHacerLogin, login_backHome;
    TextInputEditText login_correoPou, login_passwordPou;

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

                        // Login del Pou satisfactorio. Nos dirigimos al menú principal.
                        StyleableToast.makeText(Activity_Pou_Login.this, "¡Has accedido correctamente a tu Pou!", R.style.exampleToast).show();
                        // Nos vamos al Home.
                        Intent myIntent1 = new Intent(Activity_Pou_Login.this, Activity_Pou_Salon.class);
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
