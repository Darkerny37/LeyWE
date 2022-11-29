package Model;

public class loadModel {

    public int idLoad;
    public String codigoBarras;
    public int reserva;
    public int usuario;
    public int domicilio;

    /**
     * No args constructor for use in serialization
     *
     */
    public loadModel() {
    }

    /**
     *
     * @param idLoad
     * @param domicilio
     * @param usuario
     * @param reserva
     * @param codigoBarras
     */
    public loadModel(int idLoad, String codigoBarras, int reserva, int usuario, int domicilio) {
        super();
        this.idLoad = idLoad;
        this.codigoBarras = codigoBarras;
        this.reserva = reserva;
        this.usuario = usuario;
        this.domicilio = domicilio;
    }

    public int getIdLoad() {
        return idLoad;
    }

    public void setIdLoad(int idLoad) {
        this.idLoad = idLoad;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(int domicilio) {
        this.domicilio = domicilio;
    }

}
