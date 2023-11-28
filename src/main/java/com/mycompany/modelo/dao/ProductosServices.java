/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Producto;
import java.util.List;

/**
 *
 * @author forer
 */
public interface ProductosServices {
    public List<Producto> consultar();
    public Producto consultarId(Producto producto);
    public int crear(Producto producto);
    public int eliminar(Producto producto);
    public int actualizar(Producto producto);
}
