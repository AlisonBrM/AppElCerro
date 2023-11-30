/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Alison Martinez
 */
public class ElCerro {
    

    public ElCerro() {
    }
    
    public List<Promociones> mostrarPromociones(Promociones promociones){
      List<Promociones> promocionesVigentes = new ArrayList<Promociones>();
      return promocionesVigentes;
    }
    public Producto buscarProducto(String nombre){
        Producto producto = new Producto();
        if(producto.getNombre().equals(nombre)){
            return producto;
        }
        return null;
    }
    public boolean compraRealizada(Carrito carrito){
        return true;
    }
}
