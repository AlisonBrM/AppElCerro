/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Producto;
import com.mycompany.modelo.entity.Promociones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Alison Martinez
 */
public class TestPromocionesDao {
    public static void main(String[] args) throws ParseException {
        PromocionesDao promo = new PromocionesDao();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_inicio = formato.parse("2023-12-1");
        Date fecha_fin = formato.parse("2023-12-27");
        promo.crear(new Promociones("1112233", "15% descuento en limpieza", 25 , fecha_inicio, fecha_fin));
    }
}
