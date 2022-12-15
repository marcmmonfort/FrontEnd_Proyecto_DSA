package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;
import android.content.SharedPreferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView login_textPou, login_textLasAventurasDe;
    Button login_botonHacerLogin, login_backHome;
    TextInputEditText login_correoPou, login_passwordPou;
    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        login_textPou = findViewById(R.id.login_textPou);
        login_textLasAventurasDe = findViewById(R.id.login_textLasAventurasDe);

        login_botonHacerLogin = (Button) findViewById(R.id.login_botonHacerLogin);

        login_correoPou = findViewById(R.id.login_correoPou);
        login_passwordPou = findViewById(R.id.login_passwordPou);

        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
        login_correoPou.setText(preferences.getString("mail",""));
        login_passwordPou.setText(preferences.getString("password",""));
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(LoginActivity.this, HomeActivity.class);
        LoginActivity.this.startActivity(myIntent1);
    }

    public void loginPou(View view) {
        pouServices = PouRetrofit.getInstance().getPouServices();

        Credenciales nuevasCredenciales = new Credenciales(login_correoPou.getText().toString(), login_passwordPou.getText().toString());
        Call<Pou> peticion = pouServices.login(nuevasCredenciales);

        peticion.enqueue(new Callback<Pou>() {
            @Override
            public void onResponse(Call<Pou> peticion, Response<Pou> respuesta) {
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
                        Toast loginSatisfactorio = Toast.makeText(LoginActivity.this, "¡Login completado satisfactoriamente!", Toast.LENGTH_LONG);
                        loginSatisfactorio.show();

                        // Nos vamos al Home.
                        Intent myIntent1 = new Intent(LoginActivity.this, HomeActivity.class);
                        LoginActivity.this.startActivity(myIntent1);
                        break;
                    case 404:
                        // El correo no existe. Nos dirigimos al registro.
                        Toast noExisteCorreo = Toast.makeText(LoginActivity.this, "¡No hay ninguna cuenta asociada a ese correo!", Toast.LENGTH_LONG);
                        noExisteCorreo.show();
                        Intent myIntent2 = new Intent(LoginActivity.this, RegistroActivity.class);
                        LoginActivity.this.startActivity(myIntent2);
                        break;
                    case 405:
                        // Contraseña incorrecta.
                        Toast passwordIncorrecta = Toast.makeText(LoginActivity.this, "¡La contraseña introducida es incorrecta!", Toast.LENGTH_LONG);
                        passwordIncorrecta.show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Pou> peticion, Throwable t) {
                Toast error = Toast.makeText(LoginActivity.this, "¡Error!", Toast.LENGTH_LONG);
                error.show();
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
