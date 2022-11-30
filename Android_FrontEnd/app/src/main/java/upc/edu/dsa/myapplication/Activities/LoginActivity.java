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
import upc.edu.dsa.myapplication.Entities.Pou;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView login_textPou, login_textLasAventurasDe;
    Button login_botonHacerLogin;
    TextInputEditText login_correoPou, login_passwordPou;
    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        login_textPou = findViewById(R.id.tienda_textPou);
        login_textLasAventurasDe = findViewById(R.id.tienda_textLasAventurasDe);

        assignId(login_botonHacerLogin,R.id.tienda_botonVerArticulos);

        login_correoPou = findViewById(R.id.login_correoPou);
        login_passwordPou = findViewById(R.id.login_passwordPou);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
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
                        // Login del Pou satisfactorio. Nos dirigimos al menú principal.
                        Intent myIntent1 = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(myIntent1);
                        break;
                    case 404:
                        // El correo no existe. Nos dirigimos al registro.
                        Snackbar noExisteCorreo = Snackbar.make(view, "¡No hay ninguna cuenta asociada a ese correo!", 5000);
                        noExisteCorreo.show();
                        Intent myIntent2 = new Intent(LoginActivity.this, RegistroActivity.class);
                        LoginActivity.this.startActivity(myIntent2);
                        break;
                    case 405:
                        // Contraseña incorrecta.
                        Snackbar passwordIncorrecta = Snackbar.make(view, "¡La contraseña introducida es incorrecta!", 5000);
                        passwordIncorrecta.show();
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
