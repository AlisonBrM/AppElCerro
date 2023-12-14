/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

import Controller.DetallesController;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.Detalles;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alison Martinez
 */
@Path("detalle")
public class DetallesResources {

    DetallesController detallesController = new DetallesController();

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/detalles/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalles() {
        List<Map<String, Object>> detalles = new ArrayList();
        detalles = detallesController.consultar();
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detalles)
                .build();
    }

    @GET
    @Path("/detalles/{id_carrito}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetallesLlaves(@PathParam("id_carrito") Carrito id_carrito) {
        List<Map<String, Object>> detallesR = new ArrayList();
        Detalles detalles = new Detalles(id_carrito);
        detallesR = detallesController.consultarId(detalles);

        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detallesR)
                .build();
    }

    @POST
    @Path("/detalles/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Detalles detalle) {
        try {
            detallesController.crear(detalle);
            return Response.status(Response.Status.CREATED).entity(detalle).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("/restarProducto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response restarProducto(Detalles detalles) {
        int registros = detallesController.restarProducto(detalles);

        if (registros > 0) {
            return Response.status(Response.Status.OK).entity("Producto restado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("No se realizó ninguna modificación").build();
        }
    }

    @POST
    @Path("/sumarProducto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sumarProducto(Detalles detalles) {
        int registros = detallesController.sumarProducto(detalles);

        if (registros > 0) {
            return Response.status(Response.Status.OK).entity("Producto sumado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("No se realizó ninguna modificación").build();
        }
    }

}
