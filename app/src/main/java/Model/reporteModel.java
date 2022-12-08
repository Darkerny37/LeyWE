package Model;

public class reporteModel {

    public int articulo;
    public int cantidadFisica;
    public int cantidadSistema;
    public int usuario;

    /**
     * No args constructor for use in serialization
     *
     */
    public reporteModel() {
    }

    /**
     *
     * @param cantidadFisica
     * @param usuario
     * @param articulo
     * @param cantidadSistema
     */
    public reporteModel(int articulo, int cantidadFisica, int cantidadSistema, int usuario) {
        super();
        this.articulo = articulo;
        this.cantidadFisica = cantidadFisica;
        this.cantidadSistema = cantidadSistema;
        this.usuario = usuario;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public int getCantidadFisica() {
        return cantidadFisica;
    }

    public void setCantidadFisica(int cantidadFisica) {
        this.cantidadFisica = cantidadFisica;
    }

    public int getCantidadSistema() {
        return cantidadSistema;
    }

    public void setCantidadSistema(int cantidadSistema) {
        this.cantidadSistema = cantidadSistema;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
