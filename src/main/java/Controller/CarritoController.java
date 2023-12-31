/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.CarritoDao;
import com.mycompany.modelo.entity.Carrito;
import java.util.List;

/**
 *
 * @author Alison Martinez
 */
public class CarritoController {
     CarritoDao carrito = new CarritoDao();
    
    public List <Carrito> consultar(){
        return carrito.consultar();
    }
    
    public Carrito consultarId(Carrito carritoId){
        return carrito.consultarId(carritoId);
    }
    
    public int crear (Carrito carritoCrear){
        return carrito.crear(carritoCrear);
    }
    
    public int eliminar(Carrito carritoEliminar){
        return carrito.eliminar(carritoEliminar);
    }
    
    public int actualizar(Carrito carritoActualizar){
        return carrito.actualizar(carritoActualizar);
    }
    //post activar y desactivar()
    public int activar(Carrito carritoActualizar){
        return carrito.activar(carritoActualizar);
    }
    
    public int desactivar(Carrito carritoActualizar){
        return carrito.desactivar(carritoActualizar);
    }
    
    public List<Carrito> carritoUsuario(Carrito idUsuario){
        return carrito.carritoUsuario(idUsuario);
    }
}
