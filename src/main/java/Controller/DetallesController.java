/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.DetallesDao;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Detalles;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Alison Martinez
 */
public class DetallesController {
     DetallesDao detalles = new DetallesDao();
     Carrito carrito = new Carrito();
    public List<Map<String, Object>> consultar(){
        return detalles.consultar();
    }
    
    public List<Map<String, Object>> consultarId(Detalles detalle){
        return detalles.consultarId(detalle);
    }
    
    public int crear (Detalles detallesCrear){
        return detalles.crear(detallesCrear);
    }
    
    public int restarProducto(Detalles detallesEliminar){
        return detalles.restarProducto(detallesEliminar);
    }
    
    public int sumarProducto(Detalles detalle){
        return detalles.sumarProducto(detalle);
    }
}
