/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Customer Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer extends Person {

    @SerializedName("cusId")
    @Expose
    private int cusId;
    @SerializedName("uniqueNumber")
    @Expose
    private String uniqueNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cusStatus")
    @Expose
    private int cusStatus;
    private Consumer consumer;

    public Customer() {
    }

    public Customer(int cusId) {
        this.cusId=cusId;
    }

    //list method
    public Customer(int cusId, String uniqueNumber, String email, int cusStatus, int conId, String conName, String pass, String role, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.cusId = cusId;
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conId, conName, pass, role);//conName, String pass, String role
    }

    //create method
    public Customer(String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conName, pass, role);//conName, String pass, String role
    }
    
    //modify
    public Customer(int conId,String uniqueNumber, String email, int cusStatus, String conName, 
            String pass, String role, String firstName, String lastName1, 
            String lastName2, String gender, String perAddress, String telephone, 
            String rfc, String charge) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conId, charge);//conName, String pass, String role
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(int cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
