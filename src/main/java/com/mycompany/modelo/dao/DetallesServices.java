/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Detalles;
import java.util.List;
import java.util.Map;

/**
 *
 * @author forer
 */
public interface DetallesServices {
     public List<Map<String, Object>> consultar();
    public List<Map<String, Object>> consultarId(Detalles detalles);
    public int crear(Detalles detalles);
    //public int eliminar(Detalles detalles);
    public int restarProducto(Detalles detalles);
    public int sumarProducto(Detalles detalles);
}
