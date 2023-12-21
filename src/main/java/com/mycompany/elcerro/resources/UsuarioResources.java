package com.mycompany.elcerro.resources;

import Controller.UsuarioController;
import com.mycompany.modelo.entity.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("usuario")
public class UsuarioResources {

    private final UsuarioController usuarioController = new UsuarioController();

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/usuarios/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario() {
        List<Usuario> usuario = new ArrayList<>();
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
    public Response getDestinosId(@PathParam("cedula") String cedula) {
        Usuario usuario = new Usuario(cedula);
        Usuario usuarioR = usuarioController.consultarId(usuario);

        if (usuarioR != null) {
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(usuarioR)
                    .build();
        } else {
            return Response
                    .status(404) // 404 Not Found
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Usuario no encontrado")
                    .build();
        }
    }

    @POST
    @Path("/usuariosCrear/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Usuario usuario) {
        try {
            usuarioController.crear(usuario);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .entity(usuario)
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("/usuariosCambiar/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Usuario usuario) {
        try {
            usuarioController.actualizar(usuario);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .entity(usuario)
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @OPTIONS
    @Path("/usuariosCambiar/")
    public Response handleOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }

    @DELETE
    @Path("/usuarios/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarUsuario(@PathParam("cedula") String cedula) {
        Usuario usuario = new Usuario(cedula);
        int i = usuarioController.eliminar(usuario);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Productos not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
}
