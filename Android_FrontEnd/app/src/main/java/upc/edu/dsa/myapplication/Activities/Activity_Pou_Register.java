package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.*;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import upc.edu.dsa.myapplication.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_Register extends AppCompatActivity implements View.OnClickListener{

    TextView registro_textLasAventurasDe;
    Button registro_botonHacerRegistro, registro_backHome;
    TextInputEditText registro_nacimientoPou, registro_correoPou, registro_nombrePou, registro_pouId, registro_passwordPou, registro_passwordPouConfirmar;

    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_register_screen);

        registro_textLasAventurasDe = findViewById(R.id.registro_textLasAventurasDe);

        registro_botonHacerRegistro = (Button) findViewById(R.id.registro_botonHacerRegistro);
        registro_backHome = (Button) findViewById(R.id.registro_backHome);

        registro_nacimientoPou = findViewById(R.id.registro_nacimientoPou);
        registro_correoPou = findViewById(R.id.registro_correoPou);
        registro_nombrePou = findViewById(R.id.registro_nombrePou);
        registro_pouId = findViewById(R.id.registro_pouId);
        registro_passwordPou = findViewById(R.id.registro_passwordPou);
        registro_passwordPouConfirmar = findViewById(R.id.registro_passwordPouConfirmar);
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(Activity_Pou_Register.this, Activity_Pou_Home.class);
        Activity_Pou_Register.this.startActivity(myIntent1);
    }

    public void registroPou(View view) {

        if (!registro_passwordPou.getText().toString().equals(registro_passwordPouConfirmar.getText().toString())){
            // Contraseñas diferentes. Se avisa y no se procede con el registro.
            StyleableToast.makeText(Activity_Pou_Register.this, "¡Las contraseñas no coinciden!", R.style.exampleToast).show();

        }
        else{
            pouServices = PouRetrofit.getInstance().getPouServices();

            InfoRegistro nuevaInfoRegistro = new InfoRegistro(registro_pouId.getText().toString(), registro_nombrePou.getText().toString(), registro_nacimientoPou.getText().toString(), registro_correoPou.getText().toString(), registro_passwordPou.getText().toString());
            Call<Void> peticion = pouServices.registro(nuevaInfoRegistro);

            peticion.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> peticion, Response<Void> respuesta) {
                    switch (respuesta.code()){
                        case 200:
                            // Pou creado satisfactoriamente. Nos dirigimos a hacer el Login.
                            StyleableToast.makeText(Activity_Pou_Register.this, "¡Enhorabuena, ha nacido tu Pou!", R.style.exampleToast).show();
                            Intent myIntent3 = new Intent(Activity_Pou_Register.this, Activity_Pou_Login.class);
                            Activity_Pou_Register.this.startActivity(myIntent3);
                            break;
                        case 404:
                            // Ya existe el correo. Nos dirigimos a hacer el Login.
                            StyleableToast.makeText(Activity_Pou_Register.this, "¡El 'correo' introducido ya está asociado a una cuenta!", R.style.exampleToast).show();
                            Intent myIntent2 = new Intent(Activity_Pou_Register.this, Activity_Pou_Login.class);
                            Activity_Pou_Register.this.startActivity(myIntent2);
                            break;
                        case 405:
                            // Ya existe el PouID.
                            StyleableToast.makeText(Activity_Pou_Register.this, "¡El 'username' introducido ya está en uso!", R.style.exampleToast).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Void> peticion, Throwable t) {
                    StyleableToast.makeText(Activity_Pou_Register.this, "¡Error!", R.style.exampleToast).show();

                }
            });
        }
    }

    @Override
    public void onClick(View view) {

    }
}
