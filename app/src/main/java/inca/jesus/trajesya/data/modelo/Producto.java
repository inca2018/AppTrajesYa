package inca.jesus.trajesya.data.modelo;

import java.util.Date;
import java.util.List;

public class Producto {
    int idProducto;
    String nombreProducto;
    String descripcionProducto;
    String imagenProducto;
    String fechaRegistro;
    Date fechaRegistroDate;
    String fechaUpdate;
    Categoria categoriaProducto;
    SubCategoria subCategoriaProducto;
    UnidadTerritorial departamentoProducto;
    UnidadTerritorial provinciaProducto;
    UnidadTerritorial distritoProducto;
    Estado estadoProducto;
    double precioBase;
    double precioUrgencia;
    String verificadoProducto;
    int NumeroVisitas;

    List<Galeria> GaleriaProducto;
    List<Medida> MedidaProducto;
    Grupo grupo;
    int totalVendido;

    double porcentajeDescuento;


    public Producto(){
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
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

    public Categoria getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(Categoria categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public SubCategoria getSubCategoriaProducto() {
        return subCategoriaProducto;
    }

    public void setSubCategoriaProducto(SubCategoria subCategoriaProducto) {
        this.subCategoriaProducto = subCategoriaProducto;
    }

    public UnidadTerritorial getDepartamentoProducto() {
        return departamentoProducto;
    }

    public void setDepartamentoProducto(UnidadTerritorial departamentoProducto) {
        this.departamentoProducto = departamentoProducto;
    }

    public UnidadTerritorial getProvinciaProducto() {
        return provinciaProducto;
    }

    public void setProvinciaProducto(UnidadTerritorial provinciaProducto) {
        this.provinciaProducto = provinciaProducto;
    }

    public UnidadTerritorial getDistritoProducto() {
        return distritoProducto;
    }

    public void setDistritoProducto(UnidadTerritorial distritoProducto) {
        this.distritoProducto = distritoProducto;
    }

    public Estado getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Estado estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getPrecioUrgencia() {
        return precioUrgencia;
    }

    public void setPrecioUrgencia(double precioUrgencia) {
        this.precioUrgencia = precioUrgencia;
    }

    public List<Galeria> getGaleriaProducto() {
        return GaleriaProducto;
    }

    public void setGaleriaProducto(List<Galeria> galeriaProducto) {
        GaleriaProducto = galeriaProducto;
    }

    public List<Medida> getMedidaProducto() {
        return MedidaProducto;
    }

    public void setMedidaProducto(List<Medida> medidaProducto) {
        MedidaProducto = medidaProducto;
    }

    public String getVerificadoProducto() {
        return verificadoProducto;
    }

    public void setVerificadoProducto(String verificadoProducto) {
        this.verificadoProducto = verificadoProducto;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getNumeroVisitas() {
        return NumeroVisitas;
    }

    public void setNumeroVisitas(int numeroVisitas) {
        NumeroVisitas = numeroVisitas;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Date getFechaRegistroDate() {
        return fechaRegistroDate;
    }

    public void setFechaRegistroDate(Date fechaRegistroDate) {
        this.fechaRegistroDate = fechaRegistroDate;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }
}
