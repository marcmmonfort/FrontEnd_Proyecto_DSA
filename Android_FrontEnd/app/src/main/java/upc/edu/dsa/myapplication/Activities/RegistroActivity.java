package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.*;
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import upc.edu.dsa.myapplication.R;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    TextView registro_textPou, registro_textLasAventurasDe;
    Button registro_botonHacerRegistro, registro_backHome;
    TextInputEditText registro_nacimientoPou, registro_correoPou, registro_nombrePou, registro_pouId, registro_passwordPou;
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
        registro_nombrePou = findViewById(R.id.registro_nombrePou);
        registro_pouId = findViewById(R.id.registro_pouId);
        registro_passwordPou = findViewById(R.id.registro_passwordPou);
    }

    public void backHome(View view) {
        Intent myIntent1 = new Intent(RegistroActivity.this, HomeActivity.class);
        RegistroActivity.this.startActivity(myIntent1);
    }

    public void registroPou(View view) {
        pouServices = PouRetrofit.getInstance().getPouServices();

        InfoRegistro nuevaInfoRegistro = new InfoRegistro(registro_pouId.getText().toString(), registro_nombrePou.getText().toString(), registro_nacimientoPou.getText().toString(), registro_correoPou.getText().toString(), registro_passwordPou.getText().toString());
        Call<Pou> peticion = pouServices.registro(nuevaInfoRegistro);

        peticion.enqueue(new Callback<Pou>() {
            @Override
            public void onResponse(Call<Pou> peticion, Response<Pou> respuesta) {
                switch (respuesta.code()){
                    case 200:
                        // Pou creado satisfactoriamente. Nos dirigimos a hacer el Login.
                        Snackbar registroCorrecto = Snackbar.make(view, "¡Pou creado satisfactoriamente!", 5000);
                        registroCorrecto.show();
                        break;
                    case 404:
                        // Ya existe el correo. Nos dirigimos a hacer el Login.
                        Snackbar yaExisteCorreo = Snackbar.make(view, "¡Este correo ya está asociado a una cuenta!", 5000);
                        yaExisteCorreo.show();
                        Intent myIntent2 = new Intent(RegistroActivity.this, LoginActivity.class);
                        RegistroActivity.this.startActivity(myIntent2);
                        break;
                    case 405:
                        // Ya existe el PouID.
                        Snackbar yaExistePouID = Snackbar.make(view, "¡El Username introducido ya está en uso!", 5000);
                        yaExistePouID.show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Pou> peticion, Throwable t) {
                Snackbar error = Snackbar.make(view, "¡Error!", 5000);
                error.show();
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
