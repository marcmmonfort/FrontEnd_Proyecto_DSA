package upc.edu.dsa.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import android.os.Bundle;

import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.*;
import upc.edu.dsa.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textoCorreo, textoPassword;
    Button botonRegistrar;
    PouServices pouServices;

    public void onClick(View view) {
    }
    @SuppressLint({"CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
    }

    public void crearPou(View view){

        textoCorreo = findViewById(R.id.textoRegistroCorreo);
        textoPassword = findViewById(R.id.textoRegistroPassword);
        //Pou pou = new Pou();
        assignId(botonRegistrar,R.id.botonRegistro);

        PouServices pouServices = PouRetrofit.getInstance().getPouServices();
        //Call<Pou> call = pouServices.registro(pou);
        //botonRegistrar.setOnClickListener() ;
    }


    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

}
