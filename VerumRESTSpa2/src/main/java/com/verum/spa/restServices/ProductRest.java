/*=============================================================================
 |       Author:  Ruben
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  Product Model
 |                
 | Deficiencies:  Falta de acentos en respuestas.

                localhost:8080/VerumRESTSpa2/api/product/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOProduct;
import com.verum.spa.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRest {

    //Global variables
    private DAOProduct daoPro = new DAOProduct();
    private String value = "";
    private boolean flag = false;
    private Product pro = new Product();

    @POST
    @Path("add")
    public Response addProduct(
            @QueryParam("prodName") String prodName,
            @QueryParam("brand") String brand,
            @QueryParam("useCost") double useCost
    ) throws ClassNotFoundException, SQLException {
        pro.setProdName(prodName);
        pro.setBrand(brand);
        pro.setUseCost(useCost);
        if (daoPro.addProduct(pro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyProduct(
            @QueryParam("prodName") String prodName,
            @QueryParam("brand") String brand,
            @QueryParam("useCost") double useCost,
            @QueryParam("prodId") int prodId
    ) throws ClassNotFoundException, SQLException {
        pro.setProdName(prodName);
        pro.setBrand(brand);
        pro.setUseCost(useCost);
        pro.setProdId(prodId);
        if (daoPro.modifyProduct(pro)) {
            flag = true;
            Gson g = new Gson();
            String res = JsonResponses.jsonResponse(flag);
            return Response.ok(g.toJson(res)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteProduct(
            @QueryParam("prodStatus") int prodStatus,
            @QueryParam("prodId") int prodId
    ) throws ClassNotFoundException, SQLException {
        pro.setProdStatus(prodStatus);
        pro.setProdId(prodId);
        if (daoPro.deleteProduct(pro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("productList")
    public Response productList() throws SQLException, ClassNotFoundException {
        /*Se agrega un parametro, para mostrar productos activos o inactivos. Por defecto, siempre se muestran
       solo activos (0).
        Si la aplicacion manda 1 quiere decir que se muestre tanto activos como inactivos
         */
//        value = new Gson().toJson(daoPro.productList(prefVis));
        ArrayList<Product> pro = new ArrayList();
        pro = daoPro.productList();
//        value = daoPro.productList(prefVis);
        if (value != null) {
            Gson s = new Gson();

            return Response.ok(s.toJson(pro)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron productos para mostrar.")).build();
        }
    }
}
