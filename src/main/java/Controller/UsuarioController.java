/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.mycompany.modelo.dao.UsuarioDao;
import com.mycompany.modelo.entity.Usuario;
import java.util.List;

/**
 *
 * @author forer
 */
public class UsuarioController {
    UsuarioDao destino = new UsuarioDao();
    
    public List <Usuario> consultar(){
        return destino.consultar();
    }
    
    public Usuario consultarId(Usuario usuarioId){
        return destino.consultarId(usuarioId);
    }
    
    public int crear (Usuario usuarioCrear){
        return destino.crear(usuarioCrear);
    }
    
    public int eliminar(Usuario usuarioEliminar){
        return destino.eliminar(usuarioEliminar);
    }
    
    public int actualizar(Usuario usuarioActualizar){
        return destino.actualizar(usuarioActualizar);
    }
}
