package Model;

public class pasilloModel {

    public int idPasillo;
    public String codigoBarras;
    public String nombrePasillo;
    public int reserva;
    public int domicilio;

    /**
     * No args constructor for use in serialization
     *
     */
    public pasilloModel() {
    }

    /**
     *
     * @param nombrePasillo
     * @param domicilio
     //* @param reservas
     * @param idPasillo
     * @param reserva
     * @param codigoBarras
     */
    public pasilloModel(int idPasillo, String codigoBarras, String nombrePasillo, int reserva, int domicilio) {
        super();
        this.idPasillo = idPasillo;
        this.codigoBarras = codigoBarras;
        this.nombrePasillo = nombrePasillo;
        this.reserva = reserva;
        this.domicilio = domicilio;
    }

    public int getIdPasillo() {
        return idPasillo;
    }

    public void setIdPasillo(int idPasillo) {
        this.idPasillo = idPasillo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombrePasillo() {
        return nombrePasillo;
    }

    public void setNombrePasillo(String nombrePasillo) {
        this.nombrePasillo = nombrePasillo;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public int getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(int domicilio) {
        this.domicilio = domicilio;
    }

}


