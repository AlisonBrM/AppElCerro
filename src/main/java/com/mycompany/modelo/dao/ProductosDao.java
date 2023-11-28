/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author forer
 */
public class ProductosDao implements ProductosServices {
    private final String sql = "SELECT * FROM producto";
    private final String SQL_CONSULTAID =  "SELECT * FROM producto WHERE id = ?";
    private final String SQL_INSERTAR = "INSERT INTO producto(id,nombre,tipo,precio,descripcion,cantidad) VALUES(?,?,?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM producto WHERE id = ?";
    private final String SQL_ACTUALIZAR = "UPDATE producto SET nombre = ?, tipo = ?, precio = ?, descripcion = ?, cantidad = ? WHERE id = ?";
    List<Producto> destinos = new ArrayList<>();

    @Override
    public List<Producto> consultar() {
        
       try {
           BaseDeDatos db = BaseDeDatos.getInstance();
           Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                float precio =  rs.getFloat("precio");
                String descripcion =  rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                Producto producto = new Producto(id,nombre,tipo,precio,descripcion,cantidad);
                destinos.add(producto);
            }
            
            //utilixzar la conexion
        }catch (SQLException ex){
            System.out.println("Mensaje: "+ ex.getStackTrace());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       return destinos;
    }

    @Override
    public Producto consultarId(Producto producto) {
        Producto productoResultado = null;
        try {
           BaseDeDatos db = BaseDeDatos.getInstance();
           Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setString(1, producto.getId());
            ResultSet rs = stm.executeQuery();
            
            rs.absolute(1);
            String id = rs.getString("id");
            
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                float precio =  rs.getFloat("precio");
                String descripcion =  rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                productoResultado = new Producto(id,nombre,tipo,precio,descripcion,cantidad);
            
                
        }catch (SQLException ex){
            System.out.println("Mensaje: "+ ex.getStackTrace());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return productoResultado;
    }     

    @Override
    public int crear(Producto producto) {
        int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            
            PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
            stm.setString(1, producto.getId());
            stm.setString(2, producto.getNombre());
            stm.setString(3, producto.getTipo());
            stm.setFloat(4, producto.getPrecio());
            stm.setString(5, producto.getDescripcion());
            stm.setInt(6, producto.getCantidad());
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }

    @Override
    public int eliminar(Producto producto) {
       int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_BORRAR);
            stm.setString(1, producto.getId());
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ ex.getStackTrace());
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
        
    }

    @Override
    public int actualizar(Producto producto) {
        int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_ACTUALIZAR);
            stm.setString(1, producto.getNombre());
            stm.setString(2, producto.getTipo());
            stm.setFloat(3, producto.getPrecio());
            stm.setString(4, producto.getDescripcion());       
            stm.setInt(5, producto.getCantidad());
            stm.setString(6, producto.getId());
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }
    
}
