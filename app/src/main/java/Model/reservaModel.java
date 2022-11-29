package Model;

public class reservaModel {

    public int idReserva;
    public String nombreReserva;
    public int cantidad;
    public int disponible;
    public int pasillo;

    /**
     * No args constructor for use in serialization
     *
     */
    public reservaModel() {
    }

    /**
     *
     * @param nombreReserva
     * @param cantidad
     * @param pasillo
     * @param idReserva
     * @param disponible
     */
    public reservaModel(int idReserva, String nombreReserva, int cantidad, int disponible, int pasillo) {
        super();
        this.idReserva = idReserva;
        this.nombreReserva = nombreReserva;
        this.cantidad = cantidad;
        this.disponible = disponible;
        this.pasillo = pasillo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombreReserva() {
        return nombreReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getPasillo() {
        return pasillo;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }

}
