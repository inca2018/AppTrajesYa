package inca.jesus.trajesya.Data.Modelo;

public class Medida {
    int idMedida;
    String nombreMedida;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoMedida;

    public Medida(){


    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

    public String getNombreMedida() {
        return nombreMedida;
    }

    public void setNombreMedida(String nombreMedida) {
        this.nombreMedida = nombreMedida;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(String fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public Estado getEstadoMedida() {
        return estadoMedida;
    }

    public void setEstadoMedida(Estado estadoMedida) {
        this.estadoMedida = estadoMedida;
    }
}
