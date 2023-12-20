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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            + "    p.cantidad AS cantidad_disponible, "
            + "    COALESCE(d.cantidad, 0) AS cantidad_comprada "
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
            + "    p.cantidad AS cantidad_disponible, "
            + "    COALESCE(d.cantidad, 0) AS cantidad_comprada "
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

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int cantidad = rs.getInt("cantidad_comprada");
                if (cantidad > 0) {
                    Map<String, Object> detalleMap = new HashMap<>();

                    detalleMap.put("id_carrito", rs.getInt("id_carrito"));
                    detalleMap.put("fecha", rs.getString("fecha"));
                    detalleMap.put("nombre_usuario", rs.getString("nombre_usuario"));
                    detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                    detalleMap.put("precio", rs.getFloat("precio"));
                    detalleMap.put("cantidad_disponible", rs.getInt("cantidad_disponible"));
                    detalleMap.put("cantidad_comprada", rs.getInt("cantidad_comprada"));

                    detalles.add(detalleMap);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la instancia de la base de datos: " + e.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return detalles;
    }

    @Override
    public List<Map<String, Object>> consultarId(Detalles detalles) {
        List<Map<String, Object>> detallesList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setInt(1, detalles.getId_carrito().getId());
            rs = stm.executeQuery();

            while (rs.next()) {
                int cantidad = rs.getInt("cantidad_comprada");
                if (cantidad > 0) {
                    Map<String, Object> detalleMap = new HashMap<>();

                    detalleMap.put("id_carrito", rs.getInt("id_carrito"));
                    detalleMap.put("fecha", rs.getString("fecha"));
                    detalleMap.put("nombre_usuario", rs.getString("nombre_usuario"));
                    detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                    detalleMap.put("precio", rs.getFloat("precio"));
                    detalleMap.put("cantidad_disponible", rs.getInt("cantidad_disponible"));
                    detalleMap.put("cantidad_comprada", rs.getInt("cantidad_comprada"));

                    detallesList.add(detalleMap);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return detallesList;
    }

    @Override
    public int crear(Detalles detalles) {
        int registros = 0;
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            if (!tieneProductos(detalles)) {
                CarritoDao carrito = new CarritoDao();
                carrito.activar(new Carrito(detalles.getId_carrito().getId()));
            }
            if (existeProducto(detalles)) {
                sumarProducto(detalles);
            } else {
                BaseDeDatos db = BaseDeDatos.getInstance();
                connection = db.getConnection();
                stm = connection.prepareStatement(SQL_INSERTAR);

                stm.setInt(1, detalles.getId_carrito().getId());
                stm.setString(2, detalles.getId_producto().getId());
                stm.setInt(3, detalles.getCantidad());

                registros = stm.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int restarProducto(Detalles detalles) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BORRAR_PRODUCTO);

            stm.setInt(1, detalles.getId_carrito().getId());
            stm.setString(2, detalles.getId_producto().getId());

            registros = stm.executeUpdate();

            if (!tieneProductos(detalles)) {
                CarritoDao carrito = new CarritoDao();
                carrito.desactivar(new Carrito(detalles.getId_carrito().getId()));
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int sumarProducto(Detalles detalles) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_AGREGAR_PORDUCTO);

            stm.setInt(1, detalles.getId_carrito().getId());
            stm.setString(2, detalles.getId_producto().getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int productosEnCero(Detalles detalle) {
        int registrosActualizados = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_PRODUCTOS_EN_CERO);

            stm.setInt(1, detalle.getId_carrito().getId());

            registrosActualizados = stm.executeUpdate();

            CarritoDao carrito = new CarritoDao();

            carrito.desactivar(detalle.getId_carrito());

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return registrosActualizados;
    }

    public boolean existeProducto(Detalles detalle) {
        boolean existe = false;

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();

            stm = connection.prepareStatement(SQL_BUSCAR_PRODUCTO);
            stm.setInt(1, detalle.getId_carrito().getId());
            stm.setString(2, detalle.getId_producto().getId());
            
            rs = stm.executeQuery();

            existe = rs.next();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return existe;
    }

    public boolean tieneProductos(Detalles detalle) {
        boolean tieneProductos = false;

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_TIENE_PRODUCTOS);
            rs = stm.executeQuery();

            stm.setInt(1, detalle.getId_carrito().getId());

            int sumaCantidades = 0;
            while (rs.next()) {
                sumaCantidades += rs.getInt("cantidad");

                tieneProductos = sumaCantidades > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetallesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return tieneProductos;
    }

}
