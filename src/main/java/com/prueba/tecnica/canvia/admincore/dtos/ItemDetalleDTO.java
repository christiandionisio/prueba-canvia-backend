package com.prueba.tecnica.canvia.admincore.dtos;

import javax.validation.constraints.NotEmpty;

public class ItemDetalleDTO {

    private Integer id;

    @NotEmpty(message = "El nombre no debe ir vacio")
    private String nombre;

    @NotEmpty(message = "El tipo no debe ir vacio")
    private String tipo;

    @NotEmpty(message = "El precio no debe ir vacio")
    private Double precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
