/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Usuario;

/**
 *
 * @author forer
 */
public class TestUsuarioDao {
    public static void main(String[] args) {
        UsuarioDao u = new UsuarioDao();
        
        int r = 0;
        
        //r = u.crear(new Usuario("2","Juan", "Cucuta" , "juan@gmail.com" , "..."));
        //r = u.eliminar(new Usuario("1"));
        //r = u.actualizar(new Usuario("0","Fredy", "Boyaca" , "fredy@gmail.com" , "..."));
        
        System.out.println("Cantidad: " + r);
        System.out.println(u.consultarId(new Usuario("0")));
        System.out.println("" + u.consultar());
    }
    
}