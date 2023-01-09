package upc.edu.dsa.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.VO.InformacionPou;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class Activity_Pou_SplashScreen extends AppCompatActivity {

    PouServices pouServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_splash_screen);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Boolean isLogged = preferencias.getBoolean("isLogged", false);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLogged) {
                        Intent intent1 = new Intent(Activity_Pou_SplashScreen.this, Activity_Pou_Salon.class);
                        startActivity(intent1);
                    } else {
                        Intent intent2 = new Intent(Activity_Pou_SplashScreen.this, Activity_Pou_Home.class);
                        startActivity(intent2);
                    }
                }
            }

        };
        timerThread.start();
    }
}
