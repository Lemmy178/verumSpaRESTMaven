/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  Metodo listar funcionando
                  1 activo, 2 inactivo

         http://localhost:8080/VerumRESTSpa2/api/customer
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.dao.DAOCustomer;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.verum.spa.model.Customer;
import com.verum.spa.core.JsonResponses;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerREST extends Application {

    private DAOCustomer daoCustomer = new DAOCustomer();
    private String value = "";
    private boolean flag = false;
    private Customer customer = new Customer();

    @POST
    @Path("add")
    public Response addCustomer(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName1") String lastName1,
            @QueryParam("lastName2") String lastName2,
            @QueryParam("gender") String gender,
            @QueryParam("perAddress") String perAddress,
            @QueryParam("telephone") String telephone,
            @QueryParam("rfc") String rfc,
            @QueryParam("email") String email,
            @QueryParam("uniqueNumber") String uniqueNumber,
            @QueryParam("cusStatus") int cusStatus,
            @QueryParam("conName") String conName,
            @QueryParam("pass") String pass,
            @QueryParam("role") String role
    ) throws ClassNotFoundException, SQLException {
        Customer cus = new Customer(uniqueNumber, email, cusStatus, conName, pass,
                role, firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        if (daoCustomer.addCustomer(cus)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyCustomer(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName1") String lastName1,
            @QueryParam("lastName2") String lastName2,
            @QueryParam("gender") String gender,
            @QueryParam("perAddress") String perAddress,
            @QueryParam("telephone") String telephone,
            @QueryParam("pass") String pass,
            @QueryParam("email") String email,
            @QueryParam("cusStatus") int cusStatus,
            @QueryParam("charge") String charge,
            @QueryParam("cusId") int cusId,
            @QueryParam("conId") int conId,
            @QueryParam("perId") int perId
    ) throws ClassNotFoundException, SQLException {
        if (daoCustomer.modifyCustomer(firstName, lastName1,lastName2,gender,perAddress,telephone,pass,email,
                cusStatus,charge,cusId,conId,perId)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteCustomer(
            @QueryParam("cusId") int cusId
    ) throws ClassNotFoundException, SQLException {
        if (daoCustomer.deleteCustomer(cusId)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("customerList")
    public Response customerListAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList();
        customers = daoCustomer.customerList();
        if (value != null) {
            Gson g = new Gson();
            return Response.ok(g.toJson(customers)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron empleados para mostrar!")).build();
        }
    }

}
