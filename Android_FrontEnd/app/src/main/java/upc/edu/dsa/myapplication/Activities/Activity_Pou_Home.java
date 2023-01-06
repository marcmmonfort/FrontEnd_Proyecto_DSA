package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

public class Activity_Pou_Home extends AppCompatActivity{

    TextView textPou, textLasAventurasDe, textRegistro, textLogin;
    Button botonRegistro, botonLogin, botonTienda, botonLogout;
    PouServices pouServices;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_home_screen);

        textPou = findViewById(R.id.textPou);
        textLasAventurasDe = findViewById(R.id.textLasAventurasDe);
        textRegistro = findViewById(R.id.textRegistro);
        textLogin = findViewById(R.id.textLogin);

        botonRegistro = (Button) findViewById(R.id.botonRegistro);
        botonLogin = (Button) findViewById(R.id.botonLogin);
        botonTienda = (Button) findViewById(R.id.botonTienda);
        botonLogout = (Button) findViewById(R.id.botonLogout);
    }

    public void clickBoton(View view) throws IOException {
        if (view==botonTienda){
            Intent myIntent1 = new Intent(Activity_Pou_Home.this, Activity_Pou_Armario.class);
            // myIntent1.putExtra("key", value); // Optional Parameters
            Activity_Pou_Home.this.startActivity(myIntent1);
        }
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
    }

    public void clickLogout(View view) throws IOException {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putBoolean("isLogged",false);
        Obj_editor.apply();
        Intent i = new Intent(Activity_Pou_Home.this, Activity_Pou_Login.class);
        startActivity(i);
    }
}