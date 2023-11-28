/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Producto;
import com.mycompany.modelo.entity.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Alison Martinez
 */
public class CarritoDao implements CarritoServices {
    private final String sql = "SELECT * FROM carrito";
    private final String SQL_CONSULTAID =  "SELECT * FROM carrito WHERE id = ?";
    private final String SQL_INSERTAR = "INSERT INTO carrito(id, id_usuario, id_producto, fecha) VALUES(?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM carrito WHERE id = ?";
    private final String SQL_ACTUALIZAR = "UPDATE carrito SET id_usuario = ?, id_producto = ?, fecha = ? WHERE id = ?";
    List<Carrito> carritos = new ArrayList<>();
    
    @Override
    public List<Carrito> consultar() {
        
       try {
           BaseDeDatos db = BaseDeDatos.getInstance();
           Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("id");
                Usuario id_usuario = new Usuario(rs.getString("id_usuario"));
                Producto id_producto = new Producto(rs.getString("id_producto"));
                Date fecha = rs.getDate("fecha");
                
                Carrito carrito = new Carrito(id, id_usuario,id_producto,fecha);
                
                carritos.add(carrito);
            }
            
            //utilixzar la conexion
        }catch (SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       return carritos;
    }
    
    @Override
    public Carrito consultarId(Carrito carrito) {
        Carrito carritoResultado = null;
        try {
           BaseDeDatos db = BaseDeDatos.getInstance();
           Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setInt(1, carrito.getId());
            ResultSet rs = stm.executeQuery();
            
            rs.absolute(1);
            
                int id = rs.getInt("id");
                Usuario id_usuario = new Usuario(rs.getString("id_usuario"));
                Producto id_producto = new Producto(rs.getString("id_producto"));
                Date fecha = rs.getDate("fecha");
                
                carritoResultado = new Carrito(id, id_usuario,id_producto,fecha);
               
                
        }catch (SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return carritoResultado;
    } 
    
    @Override
    public int crear(Carrito carrito) {
        int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            
            PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
            stm.setInt(1, carrito.getId());
            stm.setString(2, carrito.getId_usuario().getCedula());
            stm.setString(3, carrito.getId_producto().getId());
            java.sql.Date fecha = new java.sql.Date(carrito.getFecha().getTime());
            stm.setDate(4,  fecha);
            
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }
    
    @Override
    public int eliminar(Carrito carrito) {
       int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_BORRAR);
            stm.setInt(1, carrito.getId());
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
        
    }
    
    @Override
    public int actualizar(Carrito carrito) {
        int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_ACTUALIZAR);
            
            stm.setInt(1, carrito.getId());
            stm.setString(2, carrito.getId_usuario().getCedula());
            stm.setString(3, carrito.getId_producto().getId());
            stm.setDate(4, (java.sql.Date) carrito.getFecha());
            
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }
    
}
