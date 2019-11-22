/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  DAO Model
 |                
 | Deficiencies:  No detected
                  http://localhost:8080/VerumRESTSpa2/api/room/
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAORoom {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "INSERT INTO ROOM(roomName,roomDesc,photo,roomStatus,branchId) VALUES (?,?,?,1,?)";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, room.getRoomName());
        pst.setString(2, room.getRoomDesc());
        pst.setString(3, room.getPhoto());
        pst.setInt(4, room.getBranchId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomName = ?, roomDesc=?,photo= ? WHERE roomId = ?";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, room.getRoomName());
        pst.setString(3, room.getRoomDesc());
        pst.setString(2, room.getPhoto());
        pst.setInt(4, room.getRoomId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomStatus = ? WHERE roomId = ?";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, room.getRoomStatus());
        pst.setInt(2, room.getRoomId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Room> roomList() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ArrayList<Room> roomData = new ArrayList<>();

        sql = "SELECT * FROM ROOM ";
        pst = conexion.startConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                roomData.add(new Room(rs.getInt("roomId"), rs.getString("roomName"), rs.getString("roomDesc"),
                        rs.getString("photo"), rs.getInt("roomStatus"), rs.getInt("branchId")));
            }
            conexion.closeConnection();
            return roomData;
        } else {
            return null;
        }
    }

}
