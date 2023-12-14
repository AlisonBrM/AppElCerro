/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;

import Controller.ProductoController;
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
 * @author forer
 */

@Path("producto")
public class ProductoResources {
    ProductoController productoController = new ProductoController();
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service online").build();
    }
    
    @GET
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
    }
    
    @GET
    @Path("/productos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getproductoId(@PathParam("id") String id){
        Producto productos = new Producto(id);
        Producto productosR = productoController.consultarId(productos);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(productosR)
                .build();
    }
    
    @GET
    @Path("/productostipo/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getproductoTipo(@PathParam("tipo") String tipo){
        List<Producto> productos = new ArrayList();
        productos = productoController.consultaTipo(tipo);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(productos)
                .build();
    }
    
    @POST
    @Path("/productos/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Producto productos){
        try{
            productoController.crear(productos);
            return Response.status(Response.Status.CREATED).entity(productos).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/productos/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto productos){
        try{
            productoController.actualizar(productos);
            return Response.status(Response.Status.CREATED).entity(productos).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/productos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarProducto(@PathParam("id") String id){
        Producto productos = new Producto(id);
        int i = productoController.eliminar(productos);
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

