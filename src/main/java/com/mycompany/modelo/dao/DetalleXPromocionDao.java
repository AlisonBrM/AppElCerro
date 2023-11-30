/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.DetalleXPromocion;
import com.mycompany.modelo.entity.Producto;
import com.mycompany.modelo.entity.Promociones;
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
public class DetalleXPromocionDao implements DetalleXPromocionServices {
    private final String sql = "SELECT * FROM detallexpromo";
    private final String SQL_CONSULTAID =  "SELECT * FROM detallexpromo WHERE id_promocion = ?";
    private final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM detallexpromo WHERE id_producto = ?";
    private final String SQL_INSERTAR = "INSERT INTO detallexpromo(id_producto,id_promocion) VALUES(?,?)";
    private final String SQL_ACTUALIZAR = "UPDATE detallexpromo SET id_producto = ? WHERE id_promocion = ?";
    List<DetalleXPromocion> promociones = new ArrayList<>();
    
    @Override
    public List<DetalleXPromocion> consultar() {
        
       try {
           BaseDeDatos db = BaseDeDatos.getInstance();
           Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                ProductosDao producto = new ProductosDao();
                PromocionesDao promocion = new PromocionesDao();
                
                Producto id_producto = producto.consultarId(new Producto(rs.getString("id_producto")));
                Promociones id_promocion = promocion.consultarId(new Promociones (rs.getString("id_promocion")));

                DetalleXPromocion detalle = new DetalleXPromocion(id_producto, id_promocion);
               
                
                promociones.add(detalle);
            }
            
            //utilixzar la conexion
        }catch (SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       return promociones;
    }
    
    @Override
    public List<DetalleXPromocion> consultarId(DetalleXPromocion detallesX) {
        List<DetalleXPromocion> detallesList = new ArrayList<>();

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setString(1, detallesX.getId_promocion().getId_promocion());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ProductosDao producto = new ProductosDao();
                PromocionesDao promocion = new PromocionesDao();
                
                Producto id_producto = producto.consultarId(new Producto(rs.getString("id_producto")));
                Promociones id_promocion = promocion.consultarId(new Promociones (rs.getString("id_promocion")));

                DetalleXPromocion detalle = new DetalleXPromocion(id_producto, id_promocion);
               
                
                detallesList.add(detalle);
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return detallesList;
    }
    
    @Override
    public int crear(DetalleXPromocion detalleX) {
        int registros = 0;
        try{
            if(existeProducto(detalleX.getId_producto().getId())){
                return 0;
            } else {
                BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            
            PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
            stm.setString(2, detalleX.getId_promocion().getId_promocion());
            stm.setString(1, detalleX.getId_producto().getId());
            
            registros = stm.executeUpdate();
            }
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }
    
    @Override
    public int actualizar(DetalleXPromocion promocion) {
        int registros = 0;
        try{
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection  connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_ACTUALIZAR);
            
            stm.setString(1, promocion.getId_producto().getId());
            stm.setString(2, promocion.getId_promocion().getId_promocion());
            registros = stm.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Mensaje: "+ Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
         
        }
        return registros;
    }
     public boolean existeProducto(String idProducto) {
        boolean existe = false;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_BUSCAR_PRODUCTO);
            stm.setString(1, idProducto);
            ResultSet rs = stm.executeQuery();

            existe = rs.next();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return existe;
     }
}
