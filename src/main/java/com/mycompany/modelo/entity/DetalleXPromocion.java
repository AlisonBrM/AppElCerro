/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

/**
 *
 * @author forer
 */
public class DetalleXPromocion {
    private Producto Id_producto;
    private Promociones Id_promocion;

    public DetalleXPromocion(Producto Id_producto, Promociones Id_promocion) {
        this.Id_producto = Id_producto;
        this.Id_promocion = Id_promocion;
    }

    public DetalleXPromocion(Promociones Id_promocion) {
        this.Id_promocion = Id_promocion;
    }

    public DetalleXPromocion() {
    }

    /**
     * @return the Id_producto
     */
    public Producto getId_producto() {
        return Id_producto;
    }

    /**
     * @param Id_producto the Id_producto to set
     */
    public void setId_producto(Producto Id_producto) {
        this.Id_producto = Id_producto;
    }

    /**
     * @return the Id_promocion
     */
    public Promociones getId_promocion() {
        return Id_promocion;
    }

    /**
     * @param Id_promocion the Id_promocion to set
     */
    public void setId_promocion(Promociones Id_promocion) {
        this.Id_promocion = Id_promocion;
    }
    
}
