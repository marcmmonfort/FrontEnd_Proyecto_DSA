package upc.edu.dsa.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import androidx.appcompat.app.AppCompatActivity;

import upc.edu.dsa.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_main);

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
                        Intent intent1 = new Intent(SplashScreenActivity.this, HomeActivity.class);
                        startActivity(intent1);
                    } else {
                        Intent intent2 = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent2);
                    }
                }
            }

        };
        timerThread.start();
    }
}
