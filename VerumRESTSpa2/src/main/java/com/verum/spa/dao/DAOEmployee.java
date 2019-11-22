/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmployee {

    static private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    static private String sql = "";
    static private PreparedStatement pst;

    public static boolean addEmployee(Employee emp, String conName, String pass, String charge) throws ClassNotFoundException, SQLException {
        sql = "CALL ADD_EMPLOYEE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, emp.getFirstName());//
        pst.setString(2, emp.getLastName1());//
        pst.setString(3, emp.getLastName2());//
        pst.setString(4, emp.getGender());//
        pst.setString(5, emp.getPerAddress());//
        pst.setString(6, emp.getTelephone());//
        pst.setString(7, emp.getRfc());//
        pst.setString(8, emp.getEmpNumber());//
        pst.setString(9, emp.getEmpPosition());//
        pst.setInt(10, emp.getEmpStatus());//
        pst.setString(11, emp.getPhoto());//
        pst.setString(12, conName);//
        pst.setString(13, pass);//
        pst.setString(14, charge);//

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyEmployee(String firstName, String lastName1, String lastName2, String gender, String perAddress,
            String telephone, String pass, String empPosition, int empStatus,
            String photo, String charge, int empId, int conId, int perId) throws ClassNotFoundException, SQLException {
        sql = "CALL MODIFY_EMPLOYEE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = conexion.startConnection2().prepareStatement(sql);

        pst.setString(1, firstName);
        pst.setString(2, lastName1);
        pst.setString(3, lastName2);
        pst.setString(4, gender);
        pst.setString(5, perAddress);
        pst.setString(6, telephone);
        pst.setString(7, pass);
        pst.setString(8, empPosition);
        pst.setInt(9, empStatus);
        pst.setString(10, photo);
        pst.setString(11, charge);
        pst.setInt(12, empId);
        pst.setInt(13, conId);
        pst.setInt(14, perId);

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteEmployee(int empId) throws ClassNotFoundException, SQLException {
        sql = "UPDATE EMPLOYEE SET empStatus = 2 WHERE empId=?";

        pst = conexion.startConnection().prepareStatement(sql);

        pst.setInt(1, empId);

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Employee> employeeList() throws ClassNotFoundException, SQLException {
        ResultSet rs;
        ArrayList<Employee> employeeData = new ArrayList<>();
        sql = "SELECT * FROM LIST_EMPLOYEE";

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//            int empId, String empNumber, String empPosition, int empStatus, String photo, int conId,
//            String conName , String pass , String charge , String firstName , String lastName1 , String lastName2 ,
//            String gender , String perAddress , String telephone , String rfc
                employeeData.add(new Employee(rs.getInt("empId"), rs.getString("empNumber"), rs.getString("empPosition"),
                        rs.getInt("empStatus"), rs.getString("photo"), rs.getInt("conId"), rs.getString("conName"), rs.getString("pass"),
                        rs.getString("charge"), rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc")));
            }
            conexion.closeConnection();
            return employeeData;
        } else {
            return null;
        }
    }

    public Employee employeeSearch(Employee emp) throws SQLException, ClassNotFoundException {
        Employee emp1 = null;
        PreparedStatement pst;
        ResultSet rs;
        sql = "SELECT * FROM LIST_EMPLOYEE WHERE empId = " + emp.getEmpId();

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//            int empId, String empNumber, String empPosition, int empStatus, String photo, int conId,
//            String conName , String pass , String role , String firstName , String lastName1 , String lastName2 ,
//            String gender , String perAddress , String telephone , String rfc
                emp1 = new Employee(rs.getInt("empId"), rs.getString("empNumber"), rs.getString("empPosition"),
                        rs.getInt("empStatus"), rs.getString("photo"), rs.getInt("conId"), rs.getString("conName"), rs.getString("pass"),
                        rs.getString("role"), rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc"));
            }
            conexion.closeConnection();
            return emp1;
        } else {
            conexion.closeConnection();
            return null;
        }
    }

}
