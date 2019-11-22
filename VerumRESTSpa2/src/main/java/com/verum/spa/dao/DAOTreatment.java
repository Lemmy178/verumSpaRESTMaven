/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  DAO Model
 |                
 | Deficiencies:  
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Treatment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTreatment {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addTreatment(Treatment treat) throws ClassNotFoundException, SQLException {
        sql = "INSERT INTO TREATMENT (treatName,treatDesc,cost,treatStatus) VALUES (?,?,?,1)";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, treat.getTreatName());
        pst.setString(2, treat.getTreatDesc());
        pst.setDouble(3, treat.getCost());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyTreatment(Treatment treat) throws ClassNotFoundException, SQLException {
        sql = "UPDATE TREATMENT SET treatName = ?, treatDesc=?,cost= ? WHERE treatId = ?";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, treat.getTreatName());
        pst.setString(2, treat.getTreatDesc());
        pst.setDouble(3, treat.getCost());
        pst.setInt(4, treat.getTreatId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteTreatmentt(Treatment treat) throws ClassNotFoundException, SQLException {
        sql = "UPDATE TREATMENT SET treatStatus = ? WHERE treatId = ?";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, treat.getTreatStatus());
        pst.setInt(2, treat.getTreatId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Treatment> roomList() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ArrayList<Treatment> treatData = new ArrayList<>();

        sql = "SELECT * FROM TREATMENT";
        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                treatData.add(new Treatment(rs.getInt("treatId"), rs.getString("treatName"), rs.getString("treatDesc"),
                        rs.getDouble("cost"), rs.getInt("treatStatus")));
            }
            conexion.closeConnection();
            return treatData;
        } else {
            return null;
        }
    }

}
