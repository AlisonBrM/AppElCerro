/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

/**
 *
 * @author Alison Martinez
 */
import Controller.CarritoController;

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




@Path("carrito")
public class CarriroResources {
    CarritoController carritoController = new CarritoController();
    @GET
    @Path("/ping")
    public Response ping(){
    return Response.ok().entity("Service online").build();
    }
    
  /*@GET
    @Path("/productos/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDestinos(){
        List<Producto> productos = new ArrayList();
        productos = productoController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(productos)
                .build();
    }  */
}
