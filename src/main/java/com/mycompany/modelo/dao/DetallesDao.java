/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Detalles;
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

    private final String sql = "SELECT "
            + "    c.id AS id_carrito, "
            + "    c.fecha, "
            + "    u.nombre AS nombre_usuario, "
            + "    p.nombre AS nombre_producto, "
            + "    ROUND(COALESCE(dp.precio_descuento, p.precio), 2) AS precio, "
            + "    d.cantidad "
            + "FROM "
            + "    detalle d "
            + "    JOIN carrito c ON d.id_carrito = c.id "
            + "    JOIN usuario u ON c.id_usuario = u.cedula "
            + "    JOIN producto p ON d.id_producto = p.id "
            + "    LEFT JOIN detallexpromo dp ON d.id_producto = dp.id_producto";

    private final String SQL_CONSULTAID = "SELECT "
            + "    c.id AS id_carrito, "
            + "    c.fecha, "
            + "    u.nombre AS nombre_usuario, "
            + "    p.nombre AS nombre_producto, "
            + "    ROUND(COALESCE(dp.precio_descuento, p.precio), 2) AS precio, "
            + "    d.cantidad "
            + "FROM "
            + "    detalle d "
            + "    JOIN carrito c ON d.id_carrito = c.id "
            + "    JOIN usuario u ON c.id_usuario = u.cedula "
            + "    JOIN producto p ON d.id_producto = p.id "
            + "    LEFT JOIN detallexpromo dp ON d.id_producto = dp.id_producto WHERE id_carrito = ?";

    private final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM detalle WHERE id_carrito = ? AND id_producto = ?";
    private final String SQL_INSERTAR = "INSERT INTO detalle(id_carrito, id_producto, cantidad) VALUES(?,?,?)";
    private final String SQL_BORRAR_PRODUCTO = "UPDATE detalle SET cantidad = cantidad - 1 WHERE id_carrito = ? AND id_producto = ?";
    private final String SQL_AGREGAR_PORDUCTO = "UPDATE detalle SET cantidad = cantidad + 1 WHERE id_carrito = ? AND id_producto = ?";
    private final String SQL_TIENE_PRODUCTOS = "SELECT * FROM detalle WHERE id_carrito = ?";
    private final String SQL_PRODUCTOS_EN_CERO = "UPDATE detalle SET cantidad = 0 WHERE id_carrito = ?";
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
                int cantidad = rs.getInt("cantidad");
                if (cantidad > 0) {
                    Map<String, Object> detalleMap = new HashMap<>();

                    detalleMap.put("id_carrito", rs.getInt("id_carrito"));
                    detalleMap.put("fecha", rs.getString("fecha"));
                    detalleMap.put("nombre_usuario", rs.getString("nombre_usuario"));
                    detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                    detalleMap.put("precio", rs.getDouble("precio"));
                    detalleMap.put("cantidad", cantidad);

                    detallesList.add(detalleMap);
                }
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
            if (!tieneProductos(detalles.getId_carrito().getId())) {
                CarritoDao carrito = new CarritoDao();
                carrito.activar(new Carrito(detalles.getId_carrito().getId()));
            }
            if (existeProducto(detalles)) {
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

            if (!tieneProductos(detalles.getId_carrito().getId())) {
                CarritoDao carrito = new CarritoDao();
                carrito.desactivar(new Carrito(detalles.getId_carrito().getId()));
            }

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
    
    @Override
    public int productosEnCero(Detalles detalle) {
    int registrosActualizados = 0;

    try {
        BaseDeDatos db = BaseDeDatos.getInstance();
        Connection connec = db.getConnection();
        PreparedStatement stm = connec.prepareStatement(SQL_PRODUCTOS_EN_CERO);
        stm.setInt(1, detalle.getId_carrito().getId());

        registrosActualizados = stm.executeUpdate();
        
        CarritoDao carrito = new CarritoDao();
        
        carrito.desactivar(detalle.getId_carrito());
        
    } catch (SQLException ex) {
        System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    return registrosActualizados;
}

    public boolean existeProducto(Detalles detalle) {
        boolean existe = false;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            try (Connection connec = db.getConnection(); PreparedStatement stm = connec.prepareStatement(SQL_BUSCAR_PRODUCTO)) {

                stm.setInt(1, detalle.getId_carrito().getId());
                stm.setString(2, detalle.getId_producto().getId());

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

    public boolean tieneProductos(int idCarrito) {
        boolean tieneProductos = false;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_TIENE_PRODUCTOS);

            stm.setInt(1, idCarrito);

            try (ResultSet rs = stm.executeQuery()) {
                int sumaCantidades = 0;
                while (rs.next()) {
                    sumaCantidades += rs.getInt("cantidad");
                }

                tieneProductos = sumaCantidades > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return tieneProductos;
    }

    public float totalAPagar(Carrito idCarrito) {
        float total = 0;

        List<Map<String, Object>> detallesList = consultarId(new Detalles(idCarrito));

        for (Map<String, Object> detalle : detallesList) {
            float precio = (float) detalle.get("precio");
            int cantidad = (int) detalle.get("cantidad");
            total += precio * cantidad;
        }

        return total;
    }

}
