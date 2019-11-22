/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.dao.DAOEmployee;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.verum.spa.model.Employee;
import com.verum.spa.core.JsonResponses;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeREST extends Application {

    private DAOEmployee daoEmployee = new DAOEmployee();
    private String value = "";
    private boolean flag = false;
    private Employee emp = new Employee();

    @POST
    @Path("add")
    public Response addEmploye(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName1") String lastName1,
            @QueryParam("lastName2") String lastName2,
            @QueryParam("gender") String gender,
            @QueryParam("perAddress") String perAddress,
            @QueryParam("telephone") String telephone,
            @QueryParam("rfc") String rfc,
            @QueryParam("photo") String photo,
            @QueryParam("empNumber") String empNumber,
            @QueryParam("empPosition") String empPosition,
            @QueryParam("empStatus") int empStatus,
            @QueryParam("conName") String conName,
            @QueryParam("pass") String pass,
            @QueryParam("charge") String charge
    ) throws ClassNotFoundException, SQLException {
        emp.setFirstName(firstName);
        emp.setLastName1(lastName1);
        emp.setLastName2(lastName2);
        emp.setGender(gender);
        emp.setPerAddress(perAddress);
        emp.setTelephone(telephone);
        emp.setRfc(rfc);
        emp.setEmpNumber(empNumber);
        emp.setEmpPosition(empPosition);
        emp.setEmpStatus(empStatus);
        emp.setPhoto(rfc);
        if (daoEmployee.addEmployee(emp, conName, pass, charge)) {
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
            @QueryParam("empPosition") String empPosition,
            @QueryParam("empStatus") int empStatus,
            @QueryParam("photo") String photo,
            @QueryParam("charge") String charge,
            @QueryParam("empId") int empId,
            @QueryParam("conId") int conId,
            @QueryParam("perId") int perId
    ) throws ClassNotFoundException, SQLException {
        if (daoEmployee.modifyEmployee(firstName, lastName1, lastName2, gender, perAddress, telephone, pass, empPosition, empStatus,
                photo, charge, empId, conId, perId)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteCustomer(
            @QueryParam("empId") int empId
    )
            throws ClassNotFoundException, SQLException {
        if (daoEmployee.deleteEmployee(empId)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("listEmployee")
    public Response customerListAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList();
        employees = daoEmployee.employeeList();
        if (value != null) {
            Gson s = new Gson();
            return Response.ok(s.toJson(employees)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron empleados para mostrar!")).build();
        }
    }
}
