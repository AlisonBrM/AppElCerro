/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

import java.util.Date;



/**
 *
 * @author Alison Martinez
 */
public class Promociones {
    private String id_promocion;
    private String nombre;
    private float descuento;
    private Date fecha_inicio;
    private Date fecha_fin;

    
    public Promociones() {
    }


    public Promociones(String id_promocion) {
        this.id_promocion = id_promocion;
    }

    public Promociones(String id_promocion, String nombre, float descuento, Date fecha_inicio, Date fecha_fin) {
        this.id_promocion = id_promocion;
        this.nombre = nombre;
        this.descuento = descuento;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    /**
     * @return the id_promocion
     */
    public String getId_promocion() {
        return id_promocion;
    }

    /**
     * @param id_promocion the id_promocion to set
     */
    public void setId_promocion(String id_promocion) {
        this.id_promocion = id_promocion;
    }

    /**
     * @return the id_producto
     */
   

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descuento
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the fecha_inicio
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the fecha_fin
     */
    public Date getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
    
}
