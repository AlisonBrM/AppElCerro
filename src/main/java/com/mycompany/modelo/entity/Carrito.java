/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;


import jakarta.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

/**
 *
 * @author Alison Martinez
 */
public class Carrito {
    private int id;
    private Usuario id_usuario;
    private int activo;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date fecha;

    
    public Carrito() {
    }

    public Carrito(int id, Usuario id_usuario, Date fecha, int activo) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.activo = activo;
    }
    
    public Carrito(int id, Usuario id_usuario) {
        this.id = id;
        this.id_usuario = id_usuario;
    }
    
    public Carrito(int id, int activo) {
        this.id = id;
        this.activo = activo;
    }
    
    public Carrito(Usuario id_usuario) {
        this.id_usuario = id_usuario;
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

    @Override
    public String toString() {
        return "Carrito{" + "id=" + id + ", id_usuario=" + id_usuario + ", fecha=" + fecha + '}';
    }

    public static Carrito valueOf(String id) {
        return new Carrito(Integer.parseInt(id));
    }

    /**
     * @return the activo
     */
    public int getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    
    
    
}
