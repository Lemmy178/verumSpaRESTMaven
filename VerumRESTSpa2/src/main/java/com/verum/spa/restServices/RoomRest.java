/*=============================================================================
 |       Author:  Richard
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  Product Model
 |                
 | Deficiencies:   

                http://localhost:8080/VerumRESTSpa2/api/room/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAORoom;
import com.verum.spa.model.Branch;
import com.verum.spa.model.Room;
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

@Path("room")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomRest {
    
    private DAORoom daoRoom = new DAORoom();
    private boolean flag = false;
    private Room room = new Room();
    
    @POST
    @Path("add")
    public Response addRoom(
            @QueryParam("roomName") String roomName,
            @QueryParam("roomDesc") String roomDesc,
            @QueryParam("photo") String photo,
            @QueryParam("branchId") int branchId
    ) throws ClassNotFoundException, SQLException {
        room.setRoomName(roomName);
        room.setRoomDesc(roomDesc);
        room.setPhoto(photo);
        room.getBranch().setBranchId(branchId);
        if (daoRoom.addRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }
    
    @PUT
    @Path("modify")
    public Response modifyRoom(
            @QueryParam("roomName") String roomName,
            @QueryParam("roomDesc") String roomDesc,
            @QueryParam("photo") String photo,
            @QueryParam("branchId") int branchId,
            @QueryParam("roomStatus") int roomStatus,
            @QueryParam("roomId") int roomId
    ) throws ClassNotFoundException, SQLException {
        room.setRoomName(roomName);
        room.setRoomDesc(roomDesc);
        room.setPhoto(photo);
        room.getBranch().setBranchId(branchId);
        room.setRoomStatus(roomStatus);
        room.setRoomId(roomId);
        if (daoRoom.modifyRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }
    
    @PUT
    @Path("logDelete")
    public Response deleteRoom(@QueryParam("roomId") int roomId) throws ClassNotFoundException, SQLException {
        room.setRoomId(roomId);
        if (daoRoom.deleteRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }
    
    @GET
    @Path("roomList")
    public Response RoomList() throws SQLException, ClassNotFoundException {
        ArrayList<Room> room;
        room = daoRoom.roomList();
        if (room != null) {
            Gson g = new Gson();
            return Response.ok(g.toJson(room)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron salas para mostrar.")).build();
        }
    }
}
