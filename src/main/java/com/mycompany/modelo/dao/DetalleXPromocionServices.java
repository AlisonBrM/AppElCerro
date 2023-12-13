/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.DetalleXPromocion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author forer
 */
public interface DetalleXPromocionServices {

    public List<Map<String, Object>> consultar();

    public List<Map<String, Object>> consultarId(DetalleXPromocion detallesX);

    public int crear(DetalleXPromocion detalleX);

    public int actualizar(DetalleXPromocion detalleX);
}
