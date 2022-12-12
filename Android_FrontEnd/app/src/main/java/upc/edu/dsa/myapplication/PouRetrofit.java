package upc.edu.dsa.myapplication;

import upc.edu.dsa.myapplication.Activities.*;
import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.*;

import android.os.StrictMode;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PouRetrofit {
    public static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    // "http://147.83.7.203:80/dsaApp/";
    // "http://10.0.2.2:8080/dsaApp/";

    private static PouRetrofit instance;
    private PouServices pouServices;

    private PouRetrofit() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pouServices = retrofit.create(PouServices.class);
    }

    public static synchronized PouRetrofit getInstance() {
        if (instance == null) {
            instance = new PouRetrofit();
        }
        return instance;
    }

    public PouServices getPouServices() {
        return pouServices;
    }

}
