/*=============================================================================
 |       Author:  Richard
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  Product Model
 |                
 | Deficiencies:   

               http://localhost:8080/VerumRESTSpa2/api/treatment/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOTreatment;
import com.verum.spa.model.Treatment;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("treatment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreatmentRest {

    private DAOTreatment daoTreat = new DAOTreatment();
    private String value = "";
    private boolean flag = false;
    private Treatment treatment = new Treatment();

    @POST
    @Path("add")
    public Response addTreatment(
            @QueryParam("treatName") String treatName,
            @QueryParam("treatDesc") String treatDesc,
            @QueryParam("cost") double cost
    ) throws ClassNotFoundException, SQLException {
        treatment.setTreatName(treatName);
        treatment.setTreatDesc(treatDesc);
        treatment.setCost(cost);
        if (daoTreat.addTreatment(treatment)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyTreatment(
            @QueryParam("treatName") String treatName,
            @QueryParam("treatDesc") String treatDesc,
            @QueryParam("cost") double cost,
            @QueryParam("treatId") int treatId
    ) throws ClassNotFoundException, SQLException {
        treatment.setTreatName(treatName);
        treatment.setTreatDesc(treatDesc);
        treatment.setCost(cost);
        treatment.setTreatId(treatId);
        if (daoTreat.modifyTreatment(treatment)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteTreatment(
            @QueryParam("treatId") int treatId,
            @QueryParam("treatStatus") int treatStatus
    ) throws ClassNotFoundException, SQLException {
        treatment.setTreatId(treatId);
        treatment.setTreatStatus(treatStatus);
        if (daoTreat.deleteTreatmentt(treatment)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("treatmentList")
    public Response TreatmentList() throws SQLException, ClassNotFoundException {
        ArrayList<Treatment> treat = daoTreat.roomList();
        if (treat != null) {
            Gson g = new Gson();
            return Response.ok(g.toJson(treat)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron salas para mostrar.")).build();
        }
    }
}
