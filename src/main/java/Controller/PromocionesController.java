/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.PromocionesDao;
import com.mycompany.modelo.entity.Promociones;
import java.util.List;

/**
 *
 * @author Alison Martinez
 */
public class PromocionesController {
    PromocionesDao producto = new  PromocionesDao();
    
    public List <Promociones> consultar(){
        return producto.consultar();
    }
    
    public Promociones consultarId(Promociones promocionesId){
        return producto.consultarId(promocionesId);
    }
    
    public int crear (Promociones promocionesCrear){
        return producto.crear(promocionesCrear);
    }
    
    public int eliminar(Promociones promocionesEliminar){
        return producto.eliminar(promocionesEliminar);
    }
    
    public int actualizar(Promociones promocionesActualizar){
        return producto.actualizar(promocionesActualizar);
    }
    // haga post
    public int activar(){
        return producto.activar();
    }
    
    public int desactivar(){
        return producto.desactivar();
    }
}
