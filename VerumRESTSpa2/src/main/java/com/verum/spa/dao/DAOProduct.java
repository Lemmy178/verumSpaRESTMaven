/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  DAO Model
 |                
 | Deficiencies:  Sentencias de my sql sin cambiar...
                  solo funciona insert y list
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduct {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addProduct(Product pro) {
        try {
            sql = "INSERT INTO PRODUCT(prodName,brand,prodStatus,useCost) VALUES (?,?,1,?)";

            pst = conexion.startConnection().prepareStatement(sql);

            pst.setString(1, pro.getProdName());
            pst.setString(2, pro.getBrand());
            pst.setDouble(3, pro.getUseCost());

            if (pst.executeUpdate() > 0) {
                conexion.closeConnection();
                return true;
            } else {
                conexion.closeConnection();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean modifyProduct(Product pro) throws ClassNotFoundException, SQLException {
        sql = "UPDATE PRODUCT SET prodName = ? ,brand= ?, useCost= ? WHERE prodId = ?";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, pro.getProdName());
        pst.setString(2, pro.getBrand());
        pst.setDouble(3, pro.getUseCost());
        pst.setDouble(4, pro.getProdId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteProduct(Product pro) throws ClassNotFoundException, SQLException {
        sql = "UPDATE PRODUCT SET prodStatus = ? WHERE prodId=?";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, pro.getProdStatus());
        pst.setInt(2, pro.getProdId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Product> productList() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ArrayList<Product> productData = new ArrayList<>();
        sql = "SELECT * FROM PRODUCT";

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            productData.add(new Product(rs.getInt("prodId"), rs.getString("prodName"), rs.getString("brand"),
                    rs.getDouble("useCost"), rs.getInt("prodStatus")));
        }
        conexion.closeConnection();
        return productData;
    }
}
