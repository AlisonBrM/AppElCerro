/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.DetallesDao;
import com.mycompany.modelo.dao.ProductosDao;
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
    
    public int productosEnCero(Detalles detalle){
        return detalles.productosEnCero(detalle);
    }
    
    public float totalAPagar(Carrito idCarrito) {
        float total = 0;

        List<Map<String, Object>> detallesList = consultarId(new Detalles(idCarrito));

        for (Map<String, Object> detalle : detallesList) {
            float precio = (float) detalle.get("precio");
            int cantidad = (int) detalle.get("cantidad_comprada");
            total += precio * cantidad;
        }

        return total;
    }
    
    public boolean puedeComprar(Detalles detalle) {
        List<Map<String, Object>> detallesList = consultarId(new Detalles(detalle.getId_carrito()));
        
        if(!detalles.tieneProductos(detalle)){
            System.out.println("No hay productos agregados al carrito");
            return false;
        }

        for (Map<String, Object> detalleMap : detallesList) {
            int cantidad_disponible = (int) detalleMap.get("cantidad_disponible");
            int cantidad_comprada = (int) detalleMap.get("cantidad_comprada");

            if (cantidad_comprada > cantidad_disponible) {
                String nombre_producto = (String) detalleMap.get("nombre_producto");
                System.out.println("La cantidad de compra del producto " + nombre_producto + " excede la cantidad disponible");
                return false;
            }
        }

        return true;
    }
    
    public void comprar(Detalles detalle){
        if(!puedeComprar(detalle)){
            
        }
        else{
            float totalApagar = totalAPagar(detalle.getId_carrito());
            
            ProductosDao restar = new ProductosDao();
            
            int actualizarProductosEnBD = restar.restarProductos(detalle.getId_carrito());
            
            if(actualizarProductosEnBD >= 1){
                productosEnCero(detalle);
            
            System.out.println("Compra realizada correctamente, has pagado " + totalApagar);
            } 
        }
    }
}
