/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

import Controller.UsuarioController;
import com.mycompany.modelo.entity.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author forer
 */

@Path("usuario")
public class UsuarioResources {
    UsuarioController usuarioController = new UsuarioController();
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service online").build();
    }
    
    @GET
    @Path("/usuarios/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(){
        List<Usuario> usuario = new ArrayList();
        usuario = usuarioController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(usuario)
                .build();
    }
    
    @GET
    @Path("/usuarios/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDestinosId(@PathParam("cedula") String cedula){
        Usuario usuario = new Usuario(cedula);
        Usuario usuarioR = usuarioController.consultarId(usuario);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(usuarioR)
                .build();
    }
    
    @POST
    @Path("/usuariosCrear/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Usuario usuario){
        try{
            usuarioController.crear(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/usuariosCambiar/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Usuario usuario){
        try{
            usuarioController.actualizar(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/usuarios/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarUsuario(@PathParam("cedula") String cedula){
        Usuario usuario = new Usuario(cedula);
        int i = usuarioController.eliminar(usuario);
        if (i == 0){
            return Response 
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Productos not found")
                    .build();
        }
        else{
            return Response.ok("Correcto").build();
        }
    }
}
