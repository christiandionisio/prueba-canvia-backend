package com.prueba.tecnica.canvia.admincore.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "factura_cabecera")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "La fecha de emisión no debe ir vacia")
    @Column(name = "fecha_emision")
    private String fechaEmision;

    @NotEmpty(message = "El estado no debe ir vacio")
    @Column(name = "estado")
    private String estado;

    public Integer getIdFactura() {
        return id;
    }

    public void setIdFactura(Integer id) {
        this.id = id;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura='" + id + '\'' +
                ", fechaEmision='" + fechaEmision + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
