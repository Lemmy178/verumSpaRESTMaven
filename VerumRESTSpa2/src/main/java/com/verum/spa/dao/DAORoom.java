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

import com.verum.spa.model.Branch;
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
        Class.forName(conexion.getDRIVER());
        sql = "INSERT INTO ROOM(roomName,roomDesc,photo,roomStatus,branchId) VALUES (?,?,?,1,?);";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, room.getRoomName());
        pst.setString(2, room.getRoomDesc());
        pst.setString(3, room.getPhoto());
        pst.setInt(4, room.getBranch().getBranchId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomName = ?, roomDesc = ?, photo = ?, branchId = ?, roomStatus = ? WHERE roomId = ?;";
        pst = conexion.startConnection().prepareStatement(sql);
        Class.forName(conexion.getDRIVER());
        pst.setString(1, room.getRoomName());
        pst.setString(2, room.getRoomDesc());
        pst.setString(3, room.getPhoto());
        pst.setInt(4, room.getBranch().getBranchId());
        pst.setInt(5, room.getRoomStatus());
        pst.setInt(6, room.getRoomId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomStatus = 2 WHERE roomId = ?;";
        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, room.getRoomId());
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
        Class.forName(conexion.getDRIVER());
        sql = "SELECT * FROM LIST_ROOM;";
        pst = conexion.startConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                roomData.add(new Room(rs.getInt("roomId"), rs.getString("roomName"), rs.getString("roomDesc"),
                        rs.getString("photo"), rs.getInt("roomStatus"), 
                        new Branch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress"), 
                                rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getInt("branchStatus") == 1)));
            }
            conexion.closeConnection();
            return roomData;
        } else {
            return null;
        }
    }
    
}
