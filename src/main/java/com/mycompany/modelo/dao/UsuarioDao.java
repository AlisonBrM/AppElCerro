/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import Red.BaseDeDatos;
import com.mycompany.modelo.entity.Usuario;
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
public class UsuarioDao implements UsuarioServices {

    private final String sql = "SELECT * FROM usuario";
    private final String SQL_CONSULTAID = "SELECT * FROM usuario WHERE cedula = ?";
    private final String SQL_INSERTAR = "INSERT INTO usuario(cedula,nombre,direccion,correo,telefono) VALUES(?,?,?,?,?)";
    private final String SQL_BORRAR = "DELETE FROM usuario WHERE cedula = ?";
    private final String SQL_ACTUALIZAR = "UPDATE usuario SET nombre = ?, direccion = ?, correo = ?, telefono = ? WHERE cedula = ?";
    List<Usuario> destinos = new ArrayList<>();

    @Override
    public List<Usuario> consultar() {
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                Usuario usuario = new Usuario(cedula, nombre, direccion, correo, telefono);
                destinos.add(usuario);
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
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return destinos;
    }

    @Override
    public Usuario consultarId(Usuario usuario) {
        Usuario productoResultado = null;
        
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_CONSULTAID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            
            stm.setString(1, usuario.getCedula());
            
            rs = stm.executeQuery();

            rs.absolute(1);
            String cedula = rs.getString("cedula");

            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            String correo = rs.getString("correo");
            String telefono = rs.getString("telefono");
            productoResultado = new Usuario(cedula, nombre, direccion, correo, telefono);

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally {
            try {
                BaseDeDatos.close(rs);
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productoResultado;
    }

    @Override
    public int crear(Usuario usuario) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_INSERTAR);
           
            stm.setString(1, usuario.getCedula());
            stm.setString(2, usuario.getNombre());
            stm.setString(3, usuario.getDireccion());
            stm.setString(4, usuario.getCorreo());
            stm.setString(5, usuario.getTelefono());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    @Override
    public int eliminar(Usuario usuario) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_BORRAR);
            
            stm.setString(1, usuario.getCedula());
            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;

    }

    @Override
    public int actualizar(Usuario usuario) {
        int registros = 0;
        
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
            BaseDeDatos db = BaseDeDatos.getInstance();
            connection = db.getConnection();
            stm = connection.prepareStatement(SQL_ACTUALIZAR);
            
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getDireccion());
            stm.setString(3, usuario.getCorreo());
            stm.setString(4, usuario.getTelefono());
            stm.setString(5, usuario.getCedula());

            registros = stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Mensaje: " + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }finally {
            try {
                BaseDeDatos.close(stm);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

}
