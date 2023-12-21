/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Carrito;
import java.util.List;

/**
 *
 * @author Alison Martinez
 */
public interface CarritoServices {
    public List<Carrito> consultar();
    public Carrito consultarId(Carrito carrito);
    public int crear(Carrito carrito);
    public int eliminar(Carrito carrito);
    public int actualizar(Carrito carrito);
    public int activar(Carrito carrito);
    public int desactivar(Carrito carrito);
    public List<Carrito> carritoUsuario(Carrito idUsuario);
}
