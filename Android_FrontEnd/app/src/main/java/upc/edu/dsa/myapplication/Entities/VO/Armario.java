package upc.edu.dsa.myapplication.Entities.VO;

import java.util.HashMap;
import java.util.Map;
import upc.edu.dsa.myapplication.Entities.*;

public class Armario {

    // ATRIBUTOS

    private Map<String, ObjetoTienda> comidas;
    private Map<String, ObjetoTienda> bebidas;
    private Map<String, ObjetoTienda> pociones;
    private Map<String, ObjetoTienda> ropa;

    // CONSTRUCTOR

    public Armario() {
        this.comidas = new HashMap<>();
        this.bebidas = new HashMap<>();
        this.pociones = new HashMap<>();
        this.ropa = new HashMap<>();
    }

    // GETTER Y SETTERS


    public Map<String, ObjetoTienda> getComidas() {
        return comidas;
    }

    public void setComidas(Map<String, ObjetoTienda> comidas) {
        this.comidas = comidas;
    }

    public Map<String, ObjetoTienda> getBebidas() {
        return bebidas;
    }

    public void setBebidas(Map<String, ObjetoTienda> bebidas) {
        this.bebidas = bebidas;
    }

    public Map<String, ObjetoTienda> getPociones() {
        return pociones;
    }

    public void setPociones(Map<String, ObjetoTienda> pociones) {
        this.pociones = pociones;
    }

    public Map<String, ObjetoTienda> getRopa() {
        return ropa;
    }

    public void setRopa(Map<String, ObjetoTienda> ropa) {
        this.ropa = ropa;
    }

}
