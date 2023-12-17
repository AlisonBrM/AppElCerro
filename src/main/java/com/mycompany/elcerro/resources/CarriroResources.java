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
import com.mycompany.modelo.entity.Carrito;
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
    
  @GET
    @Path("/carritos/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarrito(){
        List<Carrito> carritos = new ArrayList();
        carritos = carritoController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(carritos)
                .build();
    }
    
    @GET
    @Path("/carritos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarritoId(@PathParam("id") int id){
        Carrito carritos = new Carrito(id);
        Carrito carritoR = carritoController.consultarId(carritos);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(carritoR)
                .build();
    }
    
    @POST
    @Path("/carritoCrear/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Carrito carrito){
        try{
            carritoController.crear(carrito);
            return Response.status(Response.Status.CREATED).entity(carrito).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/carritosCambiar/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Carrito carrito){
        try{
            carritoController.actualizar(carrito);
            return Response.status(Response.Status.CREATED).entity(carrito).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/carritoActivo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activar(Carrito carrito){
        try{
            carritoController.activar(carrito);
            return Response.status(Response.Status.CREATED).entity(carrito).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/carritoDesactivo/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response desactivar(Carrito carrito){
        try{
            carritoController.desactivar(carrito);
            return Response.status(Response.Status.CREATED).entity(carrito).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/carritos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarCarrito(@PathParam("id") int id){
        Carrito carritos = new Carrito(id);
        int i = carritoController.eliminar(carritos);
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
