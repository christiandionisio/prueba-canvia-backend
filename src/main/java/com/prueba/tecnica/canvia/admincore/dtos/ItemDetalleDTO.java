package com.prueba.tecnica.canvia.admincore.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemDetalleDTO {

    private Integer idItem;

    @NotEmpty(message = "El nombre no debe ir vacio")
    private String nombre;

    @NotEmpty(message = "El tipo no debe ir vacio")
    private String tipo;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ItemDetalleDTO{" +
                "idItem=" + idItem +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
