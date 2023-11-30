/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Detalles;
import com.mycompany.modelo.entity.Producto;


/**
 *
 * @author forer
 */
public class TestDetallesDao {
    public static void main(String[] args) {
        DetallesDao d = new DetallesDao();
        Carrito car = new Carrito(2);
        Producto p = new Producto("0");
        int r = 0;
        //r = d.crear(new Detalles(car, p, 4));
        //r = d.eliminar(new Detalles(car), p.getId());
        //r = d.agregarProducto(new Detalles(car, p));
        
        //System.out.println(d.consultarId(new Detalles(car)));
        //System.out.println(d.consultar());
        System.out.println(r);
    }
}
