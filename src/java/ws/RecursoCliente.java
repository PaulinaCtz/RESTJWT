/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import entidades.Cliente;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Paulina Cortez Alamilla.
 */
@Path("cliente")
public class RecursoCliente {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            clientes.add(new Cliente(i, "cliente" + i, "123123123" + i));
        }
        return clientes;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cliente getCliente(@PathParam("id") int id) {
        return new Cliente(123, "Yo mero", "QWER122334");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{nombre}")
    public Response getClienteNombre(@PathParam("id") int id,
                                     @PathParam("nombre") String nombre) {
        Cliente c = new Cliente(123, "Yo mero", "QWER122334");
        return Response.ok(c).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("query")
    public Response getClienteQuery(@QueryParam("id") int id,
                                    @QueryParam("nombre") String nombre) {
        Cliente c = new Cliente(435, "Yo vengo del query param", "QWER122334");
        return Response.status(200).entity(c).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCliente(Cliente content) {
        try {
            System.out.println("Esto me llegó " + content);
            return Response.status(200).entity("Todo bien").build();
        } catch (Exception ex) {
            return Response.status(500).entity("Ocurrió un error en el server").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateCliente(@PathParam("id") int id, Cliente content) {
        try {
            System.out.println("Esto me llegó " + content);
            return Response.status(200).entity("Todo bien").build();
        } catch (Exception ex) {
            return Response.status(500).entity("Ocurrió un error en el server").build();
        }
    }
}