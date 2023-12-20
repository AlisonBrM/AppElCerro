/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author forer
 */
public class ProductosDao implements ProductosServices {

    private final String sql = "SELECT * FROM producto";
    private final String SQL_CONSULTAID = "SELECT * FROM producto WHERE id = ?";
    private final String SQL_INSERTAR = "INSERT INTO producto(id,nombre,tipo,precio,descripcion,cantidad,img) VALUES(?,?,?,?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM producto WHERE id = ?";
    private final String SQL_ACTUALIZAR = "UPDATE producto SET nombre = ?, tipo = ?, precio = ?, descripcion = ?, cantidad = ?, img = ? WHERE id = ?";
    private final String SQL_RESTAR_PRODUCTOS = "UPDATE producto p "
            + "JOIN (SELECT id_producto, cantidad AS cantidad_comprada "
            + "FROM detalle WHERE id_carrito = ? GROUP BY id_producto) d "
            + "ON p.id = d.id_producto "
            + "SET p.cantidad = p.cantidad - d.cantidad_comprada";
    List<Producto> destinos = new ArrayList<>();

    @Override
    public List<Producto> consultar() {
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                float precio = rs.getFloat("precio");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                String img = rs.getString("img");
                Producto producto = new Producto(id, nombre, tipo, precio, descripcion, cantidad, img);
                destinos.add(producto);
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return destinos;
    }

    @Override
    public Producto consultarId(Producto producto) {
        Producto productoResultado = null;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            
            stm.setString(1, producto.getId());
            
            rs = stm.executeQuery();
 
            rs.absolute(1);
            String id = rs.getString("id");

            String nombre = rs.getString("nombre");
            String tipo = rs.getString("tipo");
            float precio = rs.getFloat("precio");
            String descripcion = rs.getString("descripcion");
            int cantidad = rs.getInt("cantidad");
            String img = rs.getString("img");
            productoResultado = new Producto(id, nombre, tipo, precio, descripcion, cantidad, img);

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productoResultado;
    }

    @Override
    public int crear(Producto producto) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_INSERTAR);
            
            stm.setString(1, producto.getId());
            stm.setString(2, producto.getNombre());
            stm.setString(3, producto.getTipo());
            stm.setFloat(4, producto.getPrecio());
            stm.setString(5, producto.getDescripcion());
            stm.setInt(6, producto.getCantidad());
            stm.setString(7, producto.getImg());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int eliminar(Producto producto) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BORRAR);
            
            stm.setString(1, producto.getId());
            
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;

    }

    @Override
    public int actualizar(Producto producto) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR);
            
            stm.setString(1, producto.getNombre());
            stm.setString(2, producto.getTipo());
            stm.setFloat(3, producto.getPrecio());
            stm.setString(4, producto.getDescripcion());
            stm.setInt(5, producto.getCantidad());
            stm.setString(6, producto.getImg());
            stm.setString(7, producto.getId());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    public int restarProductos(Carrito idCarrito) {
        int registro = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_RESTAR_PRODUCTOS);
            
            stm.setInt(1, idCarrito.getId());
            registro = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar cantidades: " + ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
    }

}
