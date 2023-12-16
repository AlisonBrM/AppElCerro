/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao;

import com.mycompany.modelo.entity.Promociones;
import java.util.List;

/**
 *
 * @author Alison Martinez
 */
public interface PromocionesServices {
    
    public List<Promociones> consultar();
    public Promociones consultarId(Promociones promociones);
    public int crear(Promociones promociones);
    public int eliminar(Promociones promociones);
    public int actualizar(Promociones promociones);
    public int activar();
    public int desactivar();

}
