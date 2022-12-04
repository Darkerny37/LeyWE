package Model;

public class loadModel {

    public int idLoad;
    public String codigoBarras;
    public Integer reserva;
    public int usuario;

    /**
     * No args constructor for use in serialization
     *
     */
    public loadModel() {
    }

    /**
     *
     * @param idLoad
     * @param usuario
     * @param reserva
     * @param codigoBarras
     */
    public loadModel(int idLoad, String codigoBarras, Integer reserva, int usuario ) {
        super();
        this.idLoad = idLoad;
        this.codigoBarras = codigoBarras;
        this.reserva = reserva;
        this.usuario = usuario;
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

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }


}
