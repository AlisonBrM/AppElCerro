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
    UsuarioDao usuario = new UsuarioDao();
    
    public List <Usuario> consultar(){
        return usuario.consultar();
    }
    
    public Usuario consultarId(Usuario usuarioId){
        return usuario.consultarId(usuarioId);
    }
    
    public int crear (Usuario usuarioCrear){
        return usuario.crear(usuarioCrear);
    }
    
    public int eliminar(Usuario usuarioEliminar){
        return usuario.eliminar(usuarioEliminar);
    }
    
    public int actualizar(Usuario usuarioActualizar){
        return usuario.actualizar(usuarioActualizar);
    }
}
