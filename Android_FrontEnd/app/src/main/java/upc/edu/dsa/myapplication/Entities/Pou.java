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
    private String correoPou;
    private String passwordPou;
    private int dineroPou;
    private Integer nivelHambrePou;
    private Integer nivelSaludPou;
    private Integer nivelDiversionPou;
    private Integer nivelSuenoPou;
    private String camisetaId;
    private String zapatosId;
    private String gorraId;
    private String gafasId;
    private int record;

    // CONSTRUCTORES

    public Pou() {}

    public Pou(String pouId, String nombrePou, String nacimientoPou, String correoPou, String passwordPou) {
        this.pouId = pouId;
        this.nombrePou = nombrePou;
        this.nacimientoPou = nacimientoPou;
        this.correoPou = correoPou;
        this.passwordPou = passwordPou;
        this.dineroPou = 100;
        this.nivelDiversionPou = 100;
        this.nivelHambrePou = 100;
        this.nivelSaludPou = 100;
        this.nivelSuenoPou = 100;
        this.camisetaId = "nada";
        this.zapatosId = "nada";
        this.gorraId = "nada";
        this.gafasId = "nada";
        this.record = 0;
    }


    // GETTERS Y SETTERS

    public String getPouId() {
        return pouId;
    }

    public void setPouId(String pouId) {
        this.pouId = pouId;
    }

    public String getNombrePou() {
        return nombrePou;
    }

    public void setNombrePou(String nombrePou) {
        this.nombrePou = nombrePou;
    }

    public String getNacimientoPou() {
        return nacimientoPou;
    }

    public void setNacimientoPou(String nacimientoPou) {
        this.nacimientoPou = nacimientoPou;
    }

    public String getCorreoPou() {
        return correoPou;
    }

    public void setCorreoPou(String correoPou) {
        this.correoPou = correoPou;
    }

    public String getPasswordPou() {
        return passwordPou;
    }

    public void setPasswordPou(String passwordPou) {
        this.passwordPou = passwordPou;
    }

    public int getDineroPou() {
        return dineroPou;
    }

    public void setDineroPou(int dineroPou) {
        this.dineroPou = dineroPou;
    }

    public Integer getNivelHambrePou() {
        return nivelHambrePou;
    }

    public void setNivelHambrePou(Integer nivelHambrePou) {
        this.nivelHambrePou = nivelHambrePou;
    }

    public Integer getNivelSaludPou() {
        return nivelSaludPou;
    }

    public void setNivelSaludPou(Integer nivelSaludPou) {
        this.nivelSaludPou = nivelSaludPou;
    }

    public Integer getNivelDiversionPou() {
        return nivelDiversionPou;
    }

    public void setNivelDiversionPou(Integer nivelDiversionPou) {
        this.nivelDiversionPou = nivelDiversionPou;
    }

    public Integer getNivelSuenoPou() {
        return nivelSuenoPou;
    }

    public void setNivelSuenoPou(Integer nivelSuenoPou) {
        this.nivelSuenoPou = nivelSuenoPou;
    }

    public String getCamisetaId() {
        return camisetaId;
    }

    public void setCamisetaId(String camisetaId) {
        this.camisetaId = camisetaId;
    }

    public String getZapatosId() {
        return zapatosId;
    }

    public void setZapatosId(String zapatosId) {
        this.zapatosId = zapatosId;
    }

    public String getGorraId() {
        return gorraId;
    }

    public void setGorraId(String gorraId) {
        this.gorraId = gorraId;
    }

    public String getGafasId() {
        return gafasId;
    }

    public void setGafasId(String gafasId) {
        this.gafasId = gafasId;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}
