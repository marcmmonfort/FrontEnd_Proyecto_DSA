package upc.edu.dsa.myapplication.Entities;

import java.util.HashMap;
import java.util.Map;

import upc.edu.dsa.myapplication.Activities.*;
import upc.edu.dsa.myapplication.*;
import upc.edu.dsa.myapplication.Entities.VO.*;

public class Pou {

    // ATRIBUTOS

    private String pouId;
    private String nombrePou;
    private String nacimientoPou;
    private Credenciales credencialesPou;
    private Estado estadoPou;
    private Outfit outfitPou;
    private Armario armarioPou;
    private Map<String, Sala> salasPou;

    // CONSTRUCTORES

    public Pou() {}
    public Pou(String pouId, String nombrePou, String nacimientoPou, Credenciales credencialesPou) {
        this.pouId = pouId;
        this.nombrePou = nombrePou;
        this.nacimientoPou = nacimientoPou;
        this.credencialesPou = credencialesPou;
        this.estadoPou = new Estado(100,100,100,100,100);
        this.outfitPou = new Outfit(0,0,0,0);
        this.armarioPou = new Armario();
        this.salasPou = new HashMap<>();
    }

    // GETTERS Y SETTERS


}
