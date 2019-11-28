/*=============================================================================
 |       Author:  Moises Morua Lopez
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Branch Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.Branch;
import com.verum.spa.model.ConexionSpaMYSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOBranch {

    private final ConexionSpaMYSQL connectionMySpa = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addBranch(String branchName, String branchAddress, double latitude, double longitude) throws SQLException, ClassNotFoundException {

        sql = "INSERT INTO BRANCH (branchName,branchAddress,latitude,"
                + "longitude,branchStatus) VALUES (?,?,?,?,1)";

        Class.forName(connectionMySpa.getDRIVER());

        pst = connectionMySpa.startConnection().prepareStatement(sql);

        pst.setString(1, branchName);
        pst.setString(2, branchAddress);
        pst.setDouble(3, latitude);
        pst.setDouble(4, longitude);

        if (pst.executeUpdate() > 0) {
            connectionMySpa.closeConnection();
            return true;
        } else {
            connectionMySpa.closeConnection();
            return false;
        }

    }

    public boolean modifyBranch(int branchId, String branchName, String branchAddress, double latitude, double longitude, int branchStatus) throws ClassNotFoundException, SQLException {

        sql = "CALL MODIFY_BRANCH(?,?,?,?,?,?)";

        Class.forName(connectionMySpa.getDRIVER());

        pst = connectionMySpa.startConnection().prepareStatement(sql);

        pst.setInt(1, branchId);
        pst.setString(2, branchName);
        pst.setString(3, branchAddress);
        pst.setDouble(4, latitude);
        pst.setDouble(5, longitude);
        if (branchStatus == 1) {
            pst.setInt(6, 1);
        } else {
            pst.setInt(6, 2);
        }

        if (pst.executeUpdate() > 0) {
            connectionMySpa.closeConnection();
            return true;
        } else {
            connectionMySpa.closeConnection();
            return false;
        }

    }

    public boolean changeStatusBranch(int branchId) throws ClassNotFoundException, SQLException {

        sql = "CALL CHANGE_STATUS_BRANCH(?,2)";


        Class.forName(connectionMySpa.getDRIVER());
        pst = connectionMySpa.startConnection().prepareStatement(sql);

        pst.setInt(1, branchId);

        if (pst.executeUpdate() > 0) {
            connectionMySpa.closeConnection();
            return true;
        } else {
            connectionMySpa.closeConnection();
            return false;
        }

    }

    public ArrayList<Branch> branchList() throws ClassNotFoundException, SQLException {

        sql = "SELECT * FROM BRANCH";

        ArrayList<Branch> branchData = new ArrayList<>();

        Class.forName(connectionMySpa.getDRIVER());

        pst = connectionMySpa.startConnection().prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                branchData.add(new Branch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getInt("branchStatus") == 1));
            }
            return branchData;
        } else {
            return null;
        }
    }
    
}
