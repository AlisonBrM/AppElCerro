/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.DetalleXPromocion;
import java.util.List;

/**
 *
 * @author forer
 */
public interface DetalleXPromocionServices {
    public List<DetalleXPromocion> consultar();
    public List<DetalleXPromocion> consultarId(DetalleXPromocion detallesX);
    public int crear(DetalleXPromocion detalleX);
    public int actualizar(DetalleXPromocion detalleX);
}
