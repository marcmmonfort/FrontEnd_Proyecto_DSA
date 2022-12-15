package upc.edu.dsa.myapplication.Entities.VO;

public class DataModel {

    String articuloId;
    String nombreArticulo;
    String precioArticulo;
    String tipoArticulo;
    String recargaHambre;
    String recargaSalud;
    String recargaDiversion;
    String recargaSueno;

    public DataModel(){}

    public DataModel(String articuloId, String nombreArticulo, String precioArticulo, String tipoArticulo, String recargaHambre, String recargaSalud, String recargaDiversion, String recargaSueno) {
        this.articuloId = articuloId;
        this.nombreArticulo = nombreArticulo;
        this.precioArticulo = precioArticulo;
        this.tipoArticulo = tipoArticulo;
        this.recargaHambre = recargaHambre;
        this.recargaSalud = recargaSalud;
        this.recargaDiversion = recargaDiversion;
        this.recargaSueno = recargaSueno;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(String precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getRecargaHambre() {
        return recargaHambre;
    }

    public void setRecargaHambre(String recargaHambre) {
        this.recargaHambre = recargaHambre;
    }

    public String getRecargaSalud() {
        return recargaSalud;
    }

    public void setRecargaSalud(String recargaSalud) {
        this.recargaSalud = recargaSalud;
    }

    public String getRecargaDiversion() {
        return recargaDiversion;
    }

    public void setRecargaDiversion(String recargaDiversion) { this.recargaDiversion = recargaDiversion; }

    public String getRecargaSueno() {
        return recargaSueno;
    }

    public void setRecargaSueno(String recargaSueno) {
        this.recargaSueno = recargaSueno;
    }
}
