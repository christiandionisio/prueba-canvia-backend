package com.prueba.tecnica.canvia.admincore.models;

import javax.persistence.*;

@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false, foreignKey = @ForeignKey(name = "FK_FD_factura"))
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false, foreignKey = @ForeignKey(name = "FK_CF_item"))
    private ItemDetalle itemDetalle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ItemDetalle getItemDetalle() {
        return itemDetalle;
    }

    public void setItemDetalle(ItemDetalle itemDetalle) {
        this.itemDetalle = itemDetalle;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "id=" + id +
                ", factura=" + factura +
                ", itemDetalle=" + itemDetalle +
                '}';
    }
}
