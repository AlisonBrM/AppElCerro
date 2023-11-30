/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Detalles;
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
public class DetallesDao implements DetallesServices {

    private final String sql = "SELECT * FROM detalle";
    private final String SQL_CONSULTAID = "SELECT * FROM detalle WHERE id_carrito = ?";
    private final String SQL_INSERTAR = "INSERT INTO detalle(id_carrito, id_producto, cantidad) VALUES(?,?,?)";
    //private final String SQL_BORRAR = "DELETE FROM detalle WHERE id = ?";
    private final String SQL_BORRAR_PRODUCTO = "UPDATE detalle SET cantidad = cantidad - 1 WHERE id_carrito = ? AND id_producto = ?";
    private final String SQL_AGREGAR_PORDUCTO = "UPDATE detalle SET cantidad = cantidad + 1 WHERE id_carrito = ? AND id_producto = ?";
    List<Detalles> detalles = new ArrayList<>();

    @Override
    public List<Detalles> consultar() {

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                CarritoDao carrito = new CarritoDao();
                ProductosDao producto = new ProductosDao();

                Carrito id_carrito = carrito.consultarId(new Carrito(rs.getInt("id_carrito")));
                Producto id_producto = producto.consultarId(new Producto(rs.getString("id_producto")));
                int cantidad = rs.getInt("cantidad");

                Detalles detalle = new Detalles(id_carrito, id_producto, cantidad);

                detalles.add(detalle);
            }

            //utilixzar la conexion
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return detalles;
    }

    @Override
   public List<Detalles> consultarId(Detalles detalles) {
    List<Detalles> detallesList = new ArrayList<>();

    try {
        BaseDeDatos db = BaseDeDatos.getInstance();
        Connection connec = db.getConnection();
        PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
        stm.setInt(1, detalles.getId_carrito().getId());
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            CarritoDao carritoDao = new CarritoDao();
            ProductosDao productoDao = new ProductosDao();

            Carrito id_carrito = carritoDao.consultarId(new Carrito(rs.getInt("id_carrito")));
            Producto id_producto = productoDao.consultarId(new Producto(rs.getString("id_producto")));
            int cantidad = rs.getInt("cantidad");

            Detalles detallesL = new Detalles(id_carrito, id_producto, cantidad);
            detallesList.add(detallesL);
        }

    } catch (SQLException ex) {
        System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    return detallesList;
}

    @Override
    public int crear(Detalles detalles) {
        int registros = 0;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();

            PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
            stm.setInt(1, detalles.getId_carrito().getId());
            stm.setString(2, detalles.getId_producto().getId());
            stm.setInt(3, detalles.getCantidad());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return registros;
    }

    /*@Override
    public int eliminar(Detalles detalles) {
        int registros = 0;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_BORRAR);
            stm.setInt(1, detalles.getId_carrito().getId());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return registros;

    }*/
    @Override
    public int eliminarProducto(Detalles detalles) {
        int registros = 0;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();

            PreparedStatement stm = connec.prepareStatement(SQL_BORRAR_PRODUCTO);

            stm.setInt(1, detalles.getId_carrito().getId());
            stm.setString(2, detalles.getId_producto().getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return registros;
    }

    @Override
    public int agregarProducto(Detalles detalles) {
        int registros = 0;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_AGREGAR_PORDUCTO);

            stm.setInt(1, detalles.getId_carrito().getId());
            stm.setString(2, detalles.getId_producto().getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return registros;
    }
}
