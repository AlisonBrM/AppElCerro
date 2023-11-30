/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Detalles;
import java.util.List;

/**
 *
 * @author forer
 */
public interface DetallesServices {
    public List<Detalles> consultar();
    public List<Detalles> consultarId(Detalles detalles);
    public int crear(Detalles detalles);
    //public int eliminar(Detalles detalles);
    public int restarProducto(Detalles detalles);
    public int sumarProducto(Detalles detalles);
}
