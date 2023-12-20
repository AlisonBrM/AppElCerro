/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

/**
 *
 * @author Alison Martinez
 */
import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Promociones;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PromocionesDao implements PromocionesServices {

    private final String sql = "SELECT * FROM promocionesxproducto";
    private final String SQL_CONSULTAID = "SELECT * FROM promocionesxproducto WHERE id_promocion = ?";
    private final String SQL_INSERTAR = "INSERT INTO promocionesxproducto(id_promocion, nombre, descuento ,fecha_inicio, fecha_fin, activo) VALUES(?,?,?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM promocionesxproducto WHERE id_promocion = ?";
    private final String SQL_ACTUALIZAR = "UPDATE promocionesxproducto SET nombre = ?, descuento = ?, fecha_inicio = ?, fecha_fin = ? WHERE id_promocion = ?";
    private final String SQL_ACTUALIZAR_ACTIVO = "UPDATE promocionesxproducto SET activo = 1 WHERE fecha_inicio <= ? AND fecha_fin >= ?";
    private final String SQL_ACTUALIZAR_DESACTIVO = "UPDATE promocionesxproducto SET activo = 0 WHERE fecha_inicio > ? AND fecha_fin < ?";
    List<Promociones> promociones = new ArrayList<>();

    @Override
    public List<Promociones> consultar() {
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                String id_promocion = rs.getString("id_promocion");
                String nombre = rs.getString("nombre");
                Float descuento = rs.getFloat("descuento");
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date fecha_fin = rs.getDate("fecha_fin");

                Promociones promocion = new Promociones(id_promocion, nombre, descuento, fecha_inicio, fecha_fin);

                promociones.add(promocion);
            }

            //utilixzar la conexion
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return promociones;
    }

    @Override
    public Promociones consultarId(Promociones promocion) {
        Promociones promocionResultado = null;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            
            stm.setString(1, promocion.getId_promocion());
            
            rs = stm.executeQuery();

            rs.absolute(1);

            String id_promocion = rs.getString("id_promocion");
            String nombre = rs.getString("nombre");
            Float descuento = rs.getFloat("descuento");
            Date fecha_inicio = rs.getDate("fecha_inicio");
            Date fecha_fin = rs.getDate("fecha_fin");

            promocionResultado = new Promociones(id_promocion, nombre, descuento, fecha_inicio, fecha_fin);

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return promocionResultado;
    }

    @Override
    public int crear(Promociones promocion) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_INSERTAR);
            
            stm.setString(1, promocion.getId_promocion());

            stm.setString(2, promocion.getNombre());
            stm.setFloat(3, promocion.getDescuento());
            java.sql.Date fecha_inicio = new java.sql.Date(promocion.getFecha_inicio().getTime());
            java.sql.Date fecha_fin = new java.sql.Date(promocion.getFecha_fin().getTime());
            stm.setDate(4, fecha_inicio);
            stm.setDate(5, fecha_fin);
            registros = stm.executeUpdate();
            activar();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int eliminar(Promociones promocion) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BORRAR);
            
            stm.setString(1, promocion.getId_promocion());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;

    }

    @Override
    public int actualizar(Promociones promocion) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR);

            stm.setString(1, promocion.getNombre());
            stm.setFloat(2, promocion.getDescuento());
            java.sql.Date fecha_inicio = new java.sql.Date(promocion.getFecha_inicio().getTime());
            java.sql.Date fecha_fin = new java.sql.Date(promocion.getFecha_fin().getTime());
            stm.setDate(3, fecha_inicio);
            stm.setDate(4, fecha_fin);
            stm.setString(5, promocion.getId_promocion());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int activar() {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR_ACTIVO);

            LocalDate fechaActual = LocalDate.now();
            java.sql.Date fechaSqlActual = java.sql.Date.valueOf(fechaActual);

            stm.setDate(1, fechaSqlActual);
            stm.setDate(2, fechaSqlActual);

            registros = stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return registros;
    }

    @Override
    public int desactivar() {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR_DESACTIVO);

            java.util.Date fechaActual = new java.util.Date();

            java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());

            stm.setDate(1, fecha);

            registros = stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PromocionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return registros;
    }
}
