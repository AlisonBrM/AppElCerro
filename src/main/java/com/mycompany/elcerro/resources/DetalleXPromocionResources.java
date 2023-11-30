/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

import Controller.DetalleXPromocionController;
import com.mycompany.modelo.entity.DetalleXPromocion;
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

    @Path("detallexpromo")
public class DetalleXPromocionResources {
    DetalleXPromocionController detalleXPromocionController = new DetalleXPromocionController();
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service online").build();
    }
    
    @GET
    @Path("/detallexpromo/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleXPromocion(){
        List<DetalleXPromocion> detalleXPromo = new ArrayList();
        detalleXPromo = detalleXPromocionController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(detalleXPromo)
                .build();
    }
    
    
    
    @POST
    @Path("/detallexpromo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(DetalleXPromocion detalleXPromocion){
        try{
           detalleXPromocionController.crear(detalleXPromocion);
            return Response.status(Response.Status.CREATED).entity(detalleXPromocion).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
   
}
