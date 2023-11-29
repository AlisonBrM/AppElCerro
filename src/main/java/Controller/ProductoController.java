/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.ProductosDao;
import com.mycompany.modelo.entity.Producto;
import java.util.List;

/**
 *
 * @author forer
 */
public class ProductoController {
    ProductosDao producto = new ProductosDao();
    
    public List <Producto> consultar(){
        return producto.consultar();
    }
    
    public Producto consultarId(Producto productoId){
        return producto.consultarId(productoId);
    }
    
    public int crear (Producto productoCrear){
        return producto.crear(productoCrear);
    }
    
    public int eliminar(Producto productoEliminar){
        return producto.eliminar(productoEliminar);
    }
    
    public int actualizar(Producto productoActualizar){
        return producto.actualizar(productoActualizar);
    }
}
