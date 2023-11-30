/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

/**
 *
 * @author forer
 */
public class Detalles {
    private Carrito id_carrito;
    private Producto id_producto;
    private int cantidad;

    public Detalles(Carrito id_carrito, Producto id_producto, int cantidad) {
        this.id_carrito = id_carrito;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public Detalles(Carrito id_carrito) {
        this.id_carrito = id_carrito;
    }

    public Detalles(Carrito id_carrito, Producto id_producto) {
        this.id_carrito = id_carrito;
        this.id_producto = id_producto;
    }
    
    

    public Detalles() {
    }

    /**
     * @return the id_carrito
     */
    public Carrito getId_carrito() {
        return id_carrito;
    }

    /**
     * @param id_carrito the id_carrito to set
     */
    public void setId_carrito(Carrito id_carrito) {
        this.id_carrito = id_carrito;
    }

    /**
     * @return the id_producto
     */
    public Producto getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Detalles{" + "id_carrito=" + id_carrito + ", id_producto=" + id_producto + ", cantidad=" + cantidad + '}';
    }
    
}
