/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Usuario;
import java.sql.Connection;
import java.util.Date;
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
 * @author Alison Martinez
 */
public class CarritoDao implements CarritoServices {

    private final String sql = "SELECT * FROM carrito";
    private final String SQL_CONSULTAID = "SELECT * FROM carrito WHERE id = ?";
    private final String SQL_INSERTAR = "INSERT INTO carrito(id, id_usuario, fecha, activo) VALUES(?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM carrito WHERE id = ?";
    private final String SQL_ACTUALIZAR = "UPDATE carrito SET fecha = ?, activo = ? WHERE id = ?";
    private final String SQL_ACTIVAR = "UPDATE carrito SET fecha = ?, activo = ? WHERE id = ?";
    private final String SQL_DESACTIVAR = "UPDATE carrito SET fecha = ?, activo = ? WHERE id = ?";
    List<Carrito> carritos = new ArrayList<>();

    @Override
    public List<Carrito> consultar() {

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                UsuarioDao usuario = new UsuarioDao();

                int id = rs.getInt("id");
                Usuario id_usuario = usuario.consultarId(new Usuario(rs.getString("id_usuario")));
                java.sql.Date fecha = rs.getDate("fecha");
                int activo = rs.getInt("activo");

                Carrito carrito = new Carrito(id, id_usuario, fecha, activo);

                carritos.add(carrito);
            }

            //utilixzar la conexion
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return carritos;
    }

    @Override
    public Carrito consultarId(Carrito carrito) {
        Carrito carritoResultado = null;

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            stm.setInt(1, carrito.getId());
            rs = stm.executeQuery();

            rs.absolute(1);

            UsuarioDao usuario = new UsuarioDao();

            int id = rs.getInt("id");
            Usuario id_usuario = usuario.consultarId(new Usuario(rs.getString("id_usuario")));
            java.sql.Date fecha = rs.getDate("fecha");
            int activo = rs.getInt("activo");

            carritoResultado = new Carrito(id, id_usuario, fecha, activo);

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return carritoResultado;
    }

    @Override
    public int crear(Carrito carrito) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();

            long fechaNula = java.sql.Date.valueOf("0001-01-01").getTime();

            java.sql.Date fecha = new java.sql.Date(fechaNula);

            stm = connection.prepareStatement(SQL_INSERTAR);

            stm.setInt(1, carrito.getId());
            stm.setString(2, carrito.getId_usuario().getCedula());
            stm.setDate(3, fecha);
            stm.setInt(4, 0);

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int eliminar(Carrito carrito) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BORRAR);
            stm.setInt(1, carrito.getId());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;

    }

    @Override
    public int actualizar(Carrito carrito) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR);

            Date fechaActual = new Date();

            java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());

            stm.setDate(1, fecha);
            stm.setInt(2, carrito.getActivo());
            stm.setInt(3, carrito.getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int activar(Carrito carrito) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTIVAR);

            Date fechaActual = new Date();

            java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());

            stm.setDate(1, fecha);
            stm.setInt(2, 1);
            stm.setInt(3, carrito.getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public List<Carrito> carritoUsuario(Carrito idUsuario) {
        List<Carrito> carritoUser = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();

            String sql = "SELECT * FROM carrito WHERE id_usuario = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, idUsuario.getId_usuario().getCedula());
            rs = stm.executeQuery();

            if (rs.next()) {
                UsuarioDao usuarioDao = new UsuarioDao();

                int idCarrito = rs.getInt("id");
                Usuario usuario = usuarioDao.consultarId(new Usuario(idUsuario.getId_usuario().getCedula()));
                Date fecha = rs.getDate("fecha");
                int activo = rs.getInt("activo");

                Carrito carrito = new Carrito(idCarrito, usuario, fecha, activo);
                carritoUser.add(carrito);
                
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
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return carritoUser;
    }

    @Override
    public int desactivar(Carrito carrito) {
        int registros = 0;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_DESACTIVAR);

            Date fechaActual = new Date();

            java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());

            stm.setDate(1, fecha);
            stm.setInt(2, 0);
            stm.setInt(3, carrito.getId());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

}
