/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Producto;

/**
 *
 * @author forer
 */
public class TestProductoDao {
    public static void main(String[] args) {
        ProductosDao p = new ProductosDao();
        
        int r = 0;
        
        //r = p.crear(new Producto("1","Carne", "Comida" , 25000 , "...",20));
        //r = p.eliminar(new Producto("1"));
        //r = p.actualizar(new Producto("1","Pollo", "Comida" , 15000 , "...",20));
        
        System.out.println("Cantidad: " + r);
        System.out.println(p.consultarId(new Producto("0")));
        System.out.println("" + p.consultar());
    }
}
