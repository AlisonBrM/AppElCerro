/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;
import Controller.DetalleXPromocionController;
import Controller.DetallesController;
import com.mycompany.modelo.entity.Carrito;
import com.mycompany.modelo.entity.DetalleXPromocion;
import com.mycompany.modelo.entity.Detalles;
import com.mycompany.modelo.entity.Producto;
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
 * @author Alison Martinez
 */

    @Path("detalles")
public class DetallesResources {
    DetallesController detallesController = new DetallesController();
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service online").build();
    }
    
    @GET
    @Path("/detalles/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalles(){
        List<Detalles> detalles = new ArrayList();
        detalles = detallesController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detalles)
                .build();
    }
    
    @GET
    @Path("/detalles/{id_carrito}/{id_carrito}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetallesLlaves(@PathParam("id_carrito") Carrito id_carrito,
                                      @PathParam("id_producto") Producto id_producto){
        Detalles detalle = new Detalles(id_carrito, id_producto);
        List<Detalles> detalleR = detallesController.consultarId(detalle);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detalleR)
                .build();
    }
    
    @POST
    @Path("/detalles/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Detalles detalle){
        try{
            detallesController.crear(detalle);
            return Response.status(Response.Status.CREATED).entity(detalle).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
   
}
