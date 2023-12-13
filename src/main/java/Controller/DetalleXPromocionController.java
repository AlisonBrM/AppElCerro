/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import com.mycompany.modelo.dao.DetalleXPromocionDao;
import com.mycompany.modelo.entity.DetalleXPromocion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alison Martinez
 */
public class DetalleXPromocionController {
    DetalleXPromocionDao detalles = new DetalleXPromocionDao();
     
    public List<Map<String, Object>> consultar(){
        return detalles.consultar();
    }
    
    public List<Map<String, Object>> consultarId(DetalleXPromocion detalle){
        return detalles.consultarId(detalle);
    }
    
    public int crear (DetalleXPromocion detallesCrear){
        return detalles.crear(detallesCrear);
    }
    
    public int actualizar(DetalleXPromocion detallesCrear){
        return detalles.actualizar(detallesCrear);
    }
    
    public boolean existeProducto(String idProducto) {
        return detalles.existeProducto(idProducto);
    }
    
}
