/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Usuario;
import java.util.List;

/**
 *
 * @author forer
 */
public interface UsuarioServices {
    public List<Usuario> consultar();
    public Usuario consultarId(Usuario usuario);
    public int crear(Usuario usuario);
    public int eliminar(Usuario usuario);
    public int actualizar(Usuario usuario);
}
