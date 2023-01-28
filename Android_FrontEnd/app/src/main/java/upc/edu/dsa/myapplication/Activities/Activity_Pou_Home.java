package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.IOException;

import io.github.muddz.styleabletoast.StyleableToast;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

public class Activity_Pou_Home extends AppCompatActivity{

    TextView textPou, textLasAventurasDe, textRegistro, textLogin, textWeb, home_languageId;
    Button botonRegistro, botonLogin, botonWeb;

    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_home_screen);

        textPou = findViewById(R.id.textPou);

        textLasAventurasDe = findViewById(R.id.textLasAventurasDe);
        textLasAventurasDe.setText(R.string.lasaventurasde);

        textRegistro = findViewById(R.id.textRegistro);
        textRegistro.setText(R.string.home_registrarunpou);

        textLogin = findViewById(R.id.textLogin);
        textLogin.setText(R.string.home_login);

        textWeb = findViewById(R.id.textWeb);
        textWeb.setText(R.string.home_abrirweb);

        botonRegistro = (Button) findViewById(R.id.botonRegistro);
        botonLogin = (Button) findViewById(R.id.botonLogin);
        botonWeb = (Button) findViewById(R.id.botonWeb);
    }

    public void clickBoton(View view) throws IOException {

        if (view==botonRegistro){
            Intent myIntent2 = new Intent(Activity_Pou_Home.this, Activity_Pou_Register.class);
            // myIntent2.putExtra("key", value); // Optional Parameters
            Activity_Pou_Home.this.startActivity(myIntent2);
        }
        if (view==botonLogin){
            Intent myIntent3 = new Intent(Activity_Pou_Home.this, Activity_Pou_Login.class);
            // myIntent3.putExtra("key", value); // Optional Parameters
            Activity_Pou_Home.this.startActivity(myIntent3);
        }
        if (view==botonWeb){
            StyleableToast.makeText(this, "Abriendo la Web", R.style.exampleToast).show();
            Intent abrirWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=BqucGvLxrEk"));
            startActivity(abrirWeb);
        }
    }
}