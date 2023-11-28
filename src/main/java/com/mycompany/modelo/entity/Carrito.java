/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alison Martinez
 */
public class Carrito {
    private int id;
    private Usuario id_usuario;
    private Producto id_producto;
    private Date fecha;

    
    public Carrito() {
    }

    public Carrito(int id, Usuario id_usuario, Producto id_producto, Date fecha) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
        this.fecha = fecha;
    }

    public Carrito(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_usuario
     */
    public Usuario getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    
    
    
}
