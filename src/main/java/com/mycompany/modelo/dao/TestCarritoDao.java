/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Date;

/**
 *
 * @author Alison Martinez
 */
public class TestCarritoDao {
    public static void main(String[] args) throws ParseException {
        CarritoDao car = new CarritoDao();
        Usuario usuario = new Usuario("2");
        
        int r = 0;
        
        //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        //Date fecha = formato.parse("2323-12-11");
        
        System.out.println(car.carritoUsuario(new Carrito(usuario)));
        
        //int r = car.crear(new Carrito(2, usuario));
        System.out.println(r);
    }
}
