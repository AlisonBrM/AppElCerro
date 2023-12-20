/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.DetalleXPromocion;
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
public class DetalleXPromocionDao implements DetalleXPromocionServices {

    private final String sql = "SELECT pr.id AS id_producto, dp.id_promocion, p.nombre AS nombre_promocion, pr.nombre AS nombre_producto, p.descuento, p.fecha_inicio, p.fecha_fin, dp.precio_descuento, pr.precio AS precio_sin_descuento, pr.descripcion, pr.img "
            + "FROM detallexpromo dp "
            + "JOIN producto pr ON dp.id_producto = pr.id "
            + "JOIN promocionesxproducto p ON dp.id_promocion = p.id_promocion";
    private final String SQL_CONSULTAID = "SELECT pr.id AS id_producto, dp.id_promocion, p.nombre AS nombre_promocion, pr.nombre AS nombre_producto, p.descuento, p.fecha_inicio, p.fecha_fin, dp.precio_descuento, pr.precio AS precio_sin_descuento, pr.descripcion, pr.img "
            + "FROM detallexpromo dp "
            + "JOIN producto pr ON dp.id_producto = pr.id "
            + "JOIN promocionesxproducto p ON dp.id_promocion = p.id_promocion "
            + "WHERE dp.id_promocion = ?";
    private final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM detallexpromo WHERE id_producto = ?";
    private final String SQL_INSERTAR = "INSERT INTO detallexpromo(id_producto,id_promocion, precio_descuento) VALUES(?,?,?)";
    private final String SQL_ACTUALIZAR = "UPDATE detallexpromo SET precio_descuento = ? WHERE id_promocion = ?";
    private final String SQL_CONSULTAR_DESCUENTO = "SELECT descuento FROM promocionesxproducto WHERE id_promocion = ?";
    private final String SQL_CONSULTAR_PRECIO_PRODUCTO = "SELECT precio FROM producto WHERE id = ?";
    List<Map<String, Object>> promociones = new ArrayList<>();

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
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_producto", rs.getString("id_producto"));
                detalleMap.put("id_promocion", rs.getString("id_promocion"));
                detalleMap.put("nombre_promocion", rs.getString("nombre_promocion"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("descuento", rs.getInt("descuento"));
                detalleMap.put("fecha_inicio", rs.getString("fecha_inicio"));
                detalleMap.put("fecha_fin", rs.getString("fecha_fin"));
                detalleMap.put("precio_descuento", rs.getString("precio_descuento"));
                detalleMap.put("precio_sin_descuento", rs.getString("precio_sin_descuento"));
                detalleMap.put("descripcion", rs.getString("descripcion"));
                detalleMap.put("img", rs.getString("img"));

                promociones.add(detalleMap);
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
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return promociones;
    }

    @Override
    public List<Map<String, Object>> consultarId(DetalleXPromocion detallesX) {
        List<Map<String, Object>> detallesList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            rs = stm.executeQuery();

            stm.setString(1, detallesX.getId_promocion().getId_promocion());

            while (rs.next()) {
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_producto", rs.getString("id_producto"));
                detalleMap.put("id_promocion", rs.getString("id_promocion"));
                detalleMap.put("nombre_promocion", rs.getString("nombre_promocion"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("descuento", rs.getInt("descuento"));
                detalleMap.put("fecha_inicio", rs.getString("fecha_inicio"));
                detalleMap.put("fecha_fin", rs.getString("fecha_fin"));
                detalleMap.put("precio_descuento", rs.getString("precio_descuento"));
                detalleMap.put("precio_sin_descuento", rs.getString("precio_sin_descuento"));
                detalleMap.put("descripcion", rs.getString("descripcion"));
                detalleMap.put("img", rs.getString("img"));

                detallesList.add(detalleMap);
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
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return detallesList;
    }

    @Override
    public int crear(DetalleXPromocion detalleX) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            if (existeProducto(detalleX.getId_producto().getId())) {
                return 0;
            } else {
                BaseDeDatos db = BaseDeDatos.getInstance();
                connection = db.getConnection();

                float precioProducto = obtenerPrecioProducto(detalleX.getId_producto().getId());

                float precioDescuento = calcularPrecioConDescuento(detalleX, precioProducto);

                stm = connection.prepareStatement(SQL_INSERTAR);
                stm.setString(2, detalleX.getId_promocion().getId_promocion());
                stm.setString(1, detalleX.getId_producto().getId());
                stm.setFloat(3, precioDescuento);

                registros = stm.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int actualizar(DetalleXPromocion promocion) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR);

            stm.setFloat(1, promocion.getPrecio_descuento());
            stm.setString(2, promocion.getId_promocion().getId_promocion());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    public boolean existeProducto(String idProducto) {
        boolean existe = false;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BUSCAR_PRODUCTO);
            rs = stm.executeQuery();
            
            stm.setString(1, idProducto);  

            existe = rs.next();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return existe;
    }

    public float calcularPrecioConDescuento(DetalleXPromocion promocion, float precioOriginal) {
        float precioConDescuento = precioOriginal;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAR_DESCUENTO);
            
            stm.setString(1, promocion.getId_promocion().getId_promocion());

            rs = stm.executeQuery();

            if (rs.next()) {
                int descuento = rs.getInt("descuento");

                precioConDescuento = precioOriginal - (precioOriginal * descuento / 100);
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
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(precioConDescuento);
        return precioConDescuento;
    }

    private float obtenerPrecioProducto(String idProducto) {
        float precioProducto = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAR_PRECIO_PRODUCTO);
            
            stm.setString(1, idProducto);
            
            rs = stm.executeQuery();

            if (rs.next()) {
                precioProducto = rs.getFloat("precio");
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
                Logger.getLogger(DetalleXPromocionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return precioProducto;
    }
}
