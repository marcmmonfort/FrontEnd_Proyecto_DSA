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
import upc.edu.dsa.myapplication.R;

public class Activity_Pou_Salon extends AppCompatActivity{
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_salon_screen);
    }
}
