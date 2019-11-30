/*=============================================================================
 |       Author:  Moises Morua Lopez
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Branch Model
 |                
 | Deficiencies:  Metodo listar funcionando

            localhost:8080/VerumRESTSpa2/api/branch/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOBranch;
import com.verum.spa.model.Branch;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("branch")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BranchRest {

    private DAOBranch daoBranch = new DAOBranch();
    private Branch branch = new Branch();
    Gson gson = new Gson();
    @POST
    @Path("add")
    public Response addBranch(
            @QueryParam("branchName") String branchName,
            @QueryParam("branchAddress") String branchAddress,
            @QueryParam("latitude") double latitude,
            @QueryParam("longitude") double longitude,
            @QueryParam("branchStatus") int branchStatus
    ) throws ClassNotFoundException, SQLException {
        if (daoBranch.addBranch(branchName, branchAddress, latitude, longitude, branchStatus)) {
            return Response.ok(gson.toJson(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(false)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyBranch(
            @QueryParam("branchId") int branchId,
            @QueryParam("branchName") String branchName,
            @QueryParam("branchAddress") String branchAddress,
            @QueryParam("latitude") double latitude,
            @QueryParam("longitude") double longitude,
            @QueryParam("branchStatus") int branchStatus) throws ClassNotFoundException, SQLException {
        if (daoBranch.modifyBranch(branchId, branchName, branchAddress, latitude, longitude, branchStatus)) {
            return Response.ok(gson.toJson(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(false)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response delBranch(@QueryParam("idBranch") int idBranch) throws ClassNotFoundException, SQLException {
        if (daoBranch.changeStatusBranch(idBranch)) {
            return Response.ok(gson.toJson(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(false)).build();
        }
    }

    @GET
    @Path("branchList")
    public Response branchList() throws SQLException, ClassNotFoundException {
        ArrayList<Branch> list = new ArrayList<>();
        list = daoBranch.branchList();
        if (list != null) {
            Gson g = new Gson();
            return Response.ok(g.toJson(list)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(null)).build();
        }
    }

}
