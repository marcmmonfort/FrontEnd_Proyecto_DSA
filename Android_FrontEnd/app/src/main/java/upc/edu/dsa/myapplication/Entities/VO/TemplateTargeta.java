package upc.edu.dsa.myapplication.Entities.VO;

public class TemplateTargeta {

    private String nombreArticulo, idArticulo, precioArticulo, tipoArticulo, recargaHambre, recargaSalud, recargaDiversion, recargaSueno;
    private int imgArticulo;

    public TemplateTargeta() {}

    public TemplateTargeta(String nombreArticulo, String idArticulo, String precioArticulo, String tipoArticulo, String recargaHambre, String recargaSalud, String recargaDiversion, String recargaSueno, int imgArticulo) {
        this.nombreArticulo = nombreArticulo;
        this.idArticulo = idArticulo;
        this.precioArticulo = precioArticulo;
        this.tipoArticulo = tipoArticulo;
        this.recargaHambre = recargaHambre;
        this.recargaSalud = recargaSalud;
        this.recargaDiversion = recargaDiversion;
        this.recargaSueno = recargaSueno;
        this.imgArticulo = imgArticulo;
    }
}
