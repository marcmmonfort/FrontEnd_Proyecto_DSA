package upc.edu.dsa.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import upc.edu.dsa.myapplication.R;

public class Activity_Pou_SplashScreen extends AppCompatActivity {

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
                        Intent intent1 = new Intent(Activity_Pou_SplashScreen.this, Activity_Pou_Home.class);
                        startActivity(intent1);
                    } else {
                        Intent intent2 = new Intent(Activity_Pou_SplashScreen.this, Activity_Pou_Login.class);
                        startActivity(intent2);
                    }
                }
            }

        };
        timerThread.start();
    }
}
