package upc.edu.dsa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import android.os.Bundle;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultados, operaciones;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bMult,bDiv,bRes,bSum,bEqu,bLimp;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultados = findViewById(R.id.resultadoText);
        operaciones = findViewById(R.id.comandosTxt);

        assignId(b0,R.id.numeroCeroBtn);
        assignId(b1,R.id.numeroUnoBtn);
        assignId(b2,R.id.numeroDosBtn);
        assignId(b3,R.id.numeroTresBtn);
        assignId(b4,R.id.numeroCuatroBtn);
        assignId(b5,R.id.numeroCincoBtn);
        assignId(b6,R.id.numeroSeisBtn);
        assignId(b7,R.id.numeroSieteBtn);
        assignId(b8,R.id.numeroOchoBtn);
        assignId(b9,R.id.numeroNueveBtn);
        assignId(bMult,R.id.multiplicarBtn);
        assignId(bDiv,R.id.dividirBtn);
        assignId(bRes,R.id.restarBtn);
        assignId(bSum,R.id.sumarBtn);
        assignId(bEqu,R.id.resultadobtn);
        assignId(bLimp,R.id.ACBtn);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button boton = (Button) v;
        String botonText = boton.getText().toString();
        String dataToCalculate = operaciones.getText().toString();

        if (botonText.equals("AC")){
            operaciones.setText("");
            resultados.setText("0");
            return;
        }
        if (botonText.equals("=")){
            String finalResult = getResult(dataToCalculate);
            if(!finalResult.equals("Error")){
                resultados.setText(finalResult);
            } else {
                String aviso = "Err";
                resultados.setText(aviso);
            }
        } else {
            dataToCalculate = dataToCalculate + botonText;
            operaciones.setText(dataToCalculate);
        }
    }
    String getResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}