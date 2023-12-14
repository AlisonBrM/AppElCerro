/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

import Controller.DetalleXPromocionController;
import com.mycompany.modelo.entity.DetalleXPromocion;
import com.mycompany.modelo.entity.Producto;
import com.mycompany.modelo.entity.Promociones;
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
import java.util.Map;

/**
 *
 * @author Alison Martinez
 */

@Path("detallexpromo")
public class DetalleXPromocionResources {

    DetalleXPromocionController detalleXPromocionController = new DetalleXPromocionController();

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/detallesxpromos/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleXPromocion() {
        List<Map<String, Object>> detalleXPromo = new ArrayList();
        detalleXPromo = detalleXPromocionController.consultar();
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detalleXPromo)
                .build();
    }

    @GET
    @Path("/detallesxpromos/{id_promocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleXLlaves(@PathParam("id_promocion") Promociones id_promocion) {
        List<Map<String, Object>> detallesR = new ArrayList();
        DetalleXPromocion detalles = new DetalleXPromocion(id_promocion);
        detallesR = detalleXPromocionController.consultarId(detalles);

        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detallesR)
                .build();
    }

    @POST
    @Path("/detallexpromo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(DetalleXPromocion detalleXPromocion) {
        try {
            detalleXPromocionController.crear(detalleXPromocion);
            return Response.status(Response.Status.CREATED).entity(detalleXPromocion).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/detallexpromo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(DetalleXPromocion detalleXPromocion) {
        try {
            detalleXPromocionController.actualizar(detalleXPromocion);
            return Response.status(Response.Status.CREATED).entity(detalleXPromocion).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/detallexpromo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response existeProducto(String IdProducto) {
        try {
            boolean existe = detalleXPromocionController.existeProducto(IdProducto);
            if(existe){
                return Response.status(Response.Status.OK).entity("Hay producto disponible").build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Este producto se ha agotado").build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

}
