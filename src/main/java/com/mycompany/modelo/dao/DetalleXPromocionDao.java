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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author forer
 */
public class DetalleXPromocionDao implements DetalleXPromocionServices {

    private final String sql = "SELECT dp.id_promocion, p.nombre AS nombre_promocion, pr.nombre AS nombre_producto, p.descuento, p.fecha_inicio, p.fecha_fin "
            + "FROM detallexpromo dp "
            + "JOIN producto pr ON dp.id_producto = pr.id "
            + "JOIN promocionesxproducto p ON dp.id_promocion = p.id_promocion";
    private final String SQL_CONSULTAID = "SELECT dp.id_promocion, p.nombre AS nombre_promocion, pr.nombre AS nombre_producto, p.descuento, p.fecha_inicio, p.fecha_fin "
            + "FROM detallexpromo dp "
            + "JOIN producto pr ON dp.id_producto = pr.id "
            + "JOIN promocionesxproducto p ON dp.id_promocion = p.id_promocion "
            + "WHERE dp.id_promocion = ?";
    private final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM detallexpromo WHERE id_producto = ?";
    private final String SQL_INSERTAR = "INSERT INTO detallexpromo(id_producto,id_promocion) VALUES(?,?)";
    private final String SQL_ACTUALIZAR = "UPDATE detallexpromo SET id_producto = ? WHERE id_promocion = ?";
    List<Map<String, Object>> promociones = new ArrayList<>();

    @Override
    public List<Map<String, Object>> consultar() {

        try {

            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_promocion", rs.getString("id_promocion"));
                detalleMap.put("nombre_promocion", rs.getString("nombre_promocion"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("descuento", rs.getInt("descuento"));
                detalleMap.put("fecha_inicio", rs.getString("fecha_inicio"));
                detalleMap.put("fecha_fin", rs.getString("fecha_fin"));

                promociones.add(detalleMap);
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return promociones;
    }

    @Override
    public List<Map<String, Object>> consultarId(DetalleXPromocion detallesX) {
        List<Map<String, Object>> detallesList = new ArrayList<>();

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setString(1, detallesX.getId_promocion().getId_promocion());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Map<String, Object> detalleMap = new HashMap<>();

                detalleMap.put("id_promocion", rs.getString("id_promocion"));
                detalleMap.put("nombre_promocion", rs.getString("nombre_promocion"));
                detalleMap.put("nombre_producto", rs.getString("nombre_producto"));
                detalleMap.put("descuento", rs.getInt("descuento"));
                detalleMap.put("fecha_inicio", rs.getString("fecha_inicio"));
                detalleMap.put("fecha_fin", rs.getString("fecha_fin"));

                detallesList.add(detalleMap);
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
        try {
            if (existeProducto(detalleX.getId_producto().getId())) {
                return 0;
            } else {
                BaseDeDatos db = BaseDeDatos.getInstance();
                Connection connec = db.getConnection();

                PreparedStatement stm = connec.prepareStatement(SQL_INSERTAR);
                stm.setString(2, detalleX.getId_promocion().getId_promocion());
                stm.setString(1, detalleX.getId_producto().getId());

                registros = stm.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return registros;
    }

    @Override
    public int actualizar(DetalleXPromocion promocion) {
        int registros = 0;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            Connection connec = db.getConnection();
            PreparedStatement stm = connec.prepareStatement(SQL_ACTUALIZAR);

            stm.setString(1, promocion.getId_producto().getId());
            stm.setString(2, promocion.getId_promocion().getId_promocion());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
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
