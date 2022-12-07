package Model;

public class articuloAuxModel {

    public int idArticuloAux;
    public String nombreArticuloAux;
    public String numeroSerie;
    public int cantidad;
    public int pasillo;

    /**
     * No args constructor for use in serialization
     *
     */
    public articuloAuxModel() {
    }

    /**
     *
     * @param numeroSerie
     * @param load
     * @param idArticuloAux
     * @param nombreArticuloAux
     * @param cantidad
     * @param pasillo
     */
    public articuloAuxModel(int idArticuloAux, String nombreArticuloAux, String numeroSerie, int cantidad, Integer load, int pasillo) {
        super();
        this.idArticuloAux = idArticuloAux;
        this.nombreArticuloAux = nombreArticuloAux;
        this.numeroSerie = numeroSerie;
        this.cantidad = cantidad;
        this.pasillo = pasillo;
    }

    public int getIdArticuloAux() {
        return idArticuloAux;
    }

    public void setIdArticuloAux(int idArticuloAux) {
        this.idArticuloAux = idArticuloAux;
    }

    public String getNombreArticuloAux() {
        return nombreArticuloAux;
    }

    public void setNombreArticuloAux(String nombreArticuloAux) {
        this.nombreArticuloAux = nombreArticuloAux;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPasillo() {
        return pasillo;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }


}
