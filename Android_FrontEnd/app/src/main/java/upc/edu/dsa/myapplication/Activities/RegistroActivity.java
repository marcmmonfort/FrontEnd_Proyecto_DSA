package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.*;
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import upc.edu.dsa.myapplication.R;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    TextView registro_textPou, registro_textLasAventurasDe;
    Button registro_botonHacerRegistro, registro_backHome;
    TextInputEditText registro_nacimientoPou, registro_correoPou, registro_correoPouConfirmar, registro_nombrePou, registro_pouId, registro_passwordPou, registro_passwordPouConfirmar;
    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_main);

        registro_textPou = findViewById(R.id.registro_textPou);
        registro_textLasAventurasDe = findViewById(R.id.registro_textLasAventurasDe);

        registro_botonHacerRegistro = (Button) findViewById(R.id.registro_botonHacerRegistro);

        registro_nacimientoPou = findViewById(R.id.registro_nacimientoPou);
        registro_correoPou = findViewById(R.id.registro_correoPou);
        registro_correoPouConfirmar = findViewById(R.id.registro_correoPouConfirmar);
        registro_nombrePou = findViewById(R.id.registro_nombrePou);
        registro_pouId = findViewById(R.id.registro_pouId);
        registro_passwordPou = findViewById(R.id.registro_correoPouConfirmar);
        registro_passwordPouConfirmar = findViewById(R.id.registro_passwordPouConfirmar);
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(RegistroActivity.this, HomeActivity.class);
        RegistroActivity.this.startActivity(myIntent1);
    }

    public void registroPou(View view) {

        if ((registro_correoPou.getText())!=(registro_correoPouConfirmar.getText())){
            // Correos diferentes. Se avisa y no se procede con el registro.
            Toast correosDiferentes = Toast.makeText(RegistroActivity.this, "¡Los correos no coinciden!", Toast.LENGTH_LONG);
            correosDiferentes.show();
        }
        if ((registro_passwordPou.getText())!=(registro_passwordPouConfirmar.getText())){
            // Contraseñas diferentes. Se avisa y no se procede con el registro.
            Toast contrasenasDiferentes = Toast.makeText(RegistroActivity.this, "¡Las contraseñas no coinciden!", Toast.LENGTH_LONG);
            contrasenasDiferentes.show();
        }
        else{
            pouServices = PouRetrofit.getInstance().getPouServices();

            InfoRegistro nuevaInfoRegistro = new InfoRegistro(registro_pouId.getText().toString(), registro_nombrePou.getText().toString(), registro_nacimientoPou.getText().toString(), registro_correoPou.getText().toString(), registro_passwordPou.getText().toString());
            Call<Pou> peticion = pouServices.registro(nuevaInfoRegistro);

            peticion.enqueue(new Callback<Pou>() {
                @Override
                public void onResponse(Call<Pou> peticion, Response<Pou> respuesta) {
                    switch (respuesta.code()){
                        case 200:
                            // Pou creado satisfactoriamente. Nos dirigimos a hacer el Login.
                            Toast registroCorrecto = Toast.makeText(RegistroActivity.this, "¡Pou creado satisfactoriamente!", Toast.LENGTH_LONG);
                            registroCorrecto.show();
                            Intent myIntent3 = new Intent(RegistroActivity.this, LoginActivity.class);
                            RegistroActivity.this.startActivity(myIntent3);
                            break;
                        case 404:
                            // Ya existe el correo. Nos dirigimos a hacer el Login.
                            Toast yaExisteCorreo = Toast.makeText(RegistroActivity.this, "¡Este correo ya está asociado a una cuenta!", Toast.LENGTH_LONG);
                            yaExisteCorreo.show();
                            Intent myIntent2 = new Intent(RegistroActivity.this, LoginActivity.class);
                            RegistroActivity.this.startActivity(myIntent2);
                            break;
                        case 405:
                            //Ya existe el PouID.
                            Toast yaExistePouID = Toast.makeText(RegistroActivity.this, "¡El Username introducido ya está en uso!", Toast.LENGTH_LONG);
                            yaExistePouID.show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Pou> peticion, Throwable t) {
                    Toast error = Toast.makeText(RegistroActivity.this, "¡Error!", Toast.LENGTH_LONG);
                    error.show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {}
}
