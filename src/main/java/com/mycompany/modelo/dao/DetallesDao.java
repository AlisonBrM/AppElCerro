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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author forer
 */
public class DetallesDao implements DetallesServices {

    private final String sql = "SELECT c.id AS id_carrito, c.fecha, u.nombre AS nombre_usuario, p.nombre AS nombre_producto, p.precio, d.cantidad "
            + "FROM detalle d "
            + "JOIN carrito c ON d.id_carrito = c.id "
            + "JOIN usuario u ON c.id_usuario = u.cedula "
            + "JOIN producto p ON d.id_producto = p.id";
    private final String SQL_CONSULTAID = "SELECT c.id AS id_carrito, c.fecha, u.nombre AS nombre_usuario, p.nombre AS nombre_producto, p.precio, d.cantidad "
            + "FROM detalle d "
            + "JOIN carrito c ON d.id_carrito = c.id "
            + "JOIN usuario u ON c.id_usuario = u.cedula "
            + "JOIN producto p ON d.id_producto = p.id WHERE id_carrito = ?";
    private final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM detalle WHERE id_carrito IN (SELECT id FROM carrito WHERE id_usuario = ?) AND id_producto = ?";
    private final String SQL_INSERTAR = "INSERT INTO detalle(id_carrito, id_producto, cantidad) VALUES(?,?,?)";
    private final String SQL_BORRAR_PRODUCTO = "UPDATE detalle SET cantidad = cantidad - 1 WHERE id_carrito = ? AND id_producto = ?";
    private final String SQL_AGREGAR_PORDUCTO = "UPDATE detalle SET cantidad = cantidad + 1 WHERE id_carrito = ? AND id_producto = ?";
    List<Map<String, Object>> detalles = new ArrayList<>();

    @Override
    public List<Map<String, Object>> consultar() {

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_carrito", rs.getInt("id_carrito"));
                detalleMap.put("fecha", rs.getString("fecha"));
                detalleMap.put("nombre_usuario", rs.getString("nombre_usuario"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("precio", rs.getDouble("precio"));
                detalleMap.put("cantidad", rs.getInt("cantidad"));

                detalles.add(detalleMap);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la instancia de la base de datos: " + e.getMessage());
        }

        return detalles;
    }

    @Override
    public List<Map<String, Object>> consultarId(Detalles detalles) {
        List<Map<String, Object>> detallesList = new ArrayList<>();

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setInt(1, detalles.getId_carrito().getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_carrito", rs.getInt("id_carrito"));
                detalleMap.put("fecha", rs.getString("fecha"));
                detalleMap.put("nombre_usuario", rs.getString("nombre_usuario"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("precio", rs.getDouble("precio"));
                detalleMap.put("cantidad", rs.getInt("cantidad"));

                detallesList.add(detalleMap);
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
            if (existeProducto(detalles.getId_carrito().getId(),detalles.getId_producto().getId())) {
                sumarProducto(detalles);
            } else {
                BaseDeDatos db = BaseDeDatos.getInstance();
                Connection connec = db.getConnection();

                PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
                stm.setInt(1, detalles.getId_carrito().getId());
                stm.setString(2, detalles.getId_producto().getId());
                stm.setInt(3, detalles.getCantidad());

                registros = stm.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return registros;
    }

    @Override
    public int restarProducto(Detalles detalles) {
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
    public int sumarProducto(Detalles detalles) {
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

    public boolean existeProducto(int idCarrito, String idProducto) {
    boolean existe = false;

    try {
        BaseDeDatos db = BaseDeDatos.getInstance();
        try (Connection connec = db.getConnection();
             PreparedStatement stm = connec.prepareStatement(SQL_BUSCAR_PRODUCTO)) {

            stm.setInt(1, idCarrito);
            stm.setString(2, idProducto);

            try (ResultSet rs = stm.executeQuery()) {
                existe = rs.next();
            }
        }
    } catch (SQLException ex) {
        System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    return existe;
}
}
