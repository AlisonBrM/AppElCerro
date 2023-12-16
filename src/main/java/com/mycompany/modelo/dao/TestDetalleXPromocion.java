/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.DetalleXPromocion;
import com.mycompany.modelo.entity.Producto;
import com.mycompany.modelo.entity.Promociones;

/**
 *
 * @author forer
 */
public class TestDetalleXPromocion {
    public static void main(String[] args) {
        DetalleXPromocionDao dp = new DetalleXPromocionDao();
        Promociones p = new Promociones("1112233");
        Producto po = new Producto ("18");
        
        int r = 0;
        //float precioDescuento = dp.calcularPrecioConDescuento(new DetalleXPromocion(po,p), 2.1F);
        
        
        r = dp.crear(new DetalleXPromocion(po, p));
        //System.out.println(dp.consultarId(new DetalleXPromocion(p)));
        
        //System.out.println(dp.actualizar(new DetalleXPromocion(precioDescuento,p)));
        
        System.out.println(r);
    }
}
