/*=============================================================================
 |       Author:  Moises Morua Lopez
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Branch Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch {

    @SerializedName("branchId")
    @Expose 
    private int branchId;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("branchAddress")
    @Expose
    private String branchAddress;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("branchStatus")
    @Expose
    private boolean branchStatus;

    public Branch() {
    }

    public Branch(int branchId, String branchName, String branchAddress, double latitude, double longitude, boolean branchStatus) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.branchStatus = branchStatus;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(boolean branchStatus) {
        this.branchStatus = branchStatus;
    }

}
