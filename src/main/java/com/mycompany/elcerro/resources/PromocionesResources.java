/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elcerro.resources;
import Controller.PromocionesController;
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


/**
 *
 * @author Alison Martinez
 */
@Path("promocionesxproducto")
public class PromocionesResources {

    PromocionesController promoController = new PromocionesController();
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service online").build();
    }
    
    @GET
    @Path("/promocionesxproducto/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromociones(){
        List<Promociones> promo = new ArrayList();
        promo = promoController.consultar();
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(promo)
                .build();
    }
    
    @GET
    @Path("/promocionesxproducto/{id_promocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromocionesId(@PathParam("id_promocion") String id_promocion){
        Promociones promo = new Promociones(id_promocion);
        Promociones promoR = promoController.consultarId(promo);
        return Response 
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(promoR)
                .build();
    }
    
    @POST
    @Path("/promocionesxproducto/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Promociones promo){
        try{
            promoController.crear(promo);
            return Response.status(Response.Status.CREATED).entity(promo).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("/promocionesxproductoCambiar/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Promociones promo){
        try{
            promoController.actualizar(promo);
            return Response.status(Response.Status.CREATED).entity(promo).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/promocionesxproducto/{id_promocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarPromociones(@PathParam("id_promocion") String id_promocion){
        Promociones promo = new Promociones(id_promocion);
        int i = promoController.eliminar(promo);
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
