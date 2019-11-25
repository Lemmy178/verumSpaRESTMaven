/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  10/31/2019
 |  Description:  Room Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.verum.spa.dao.DAOBranch;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room {

    @SerializedName("roomId")
    @Expose
    private int roomId;
    @SerializedName("roomName")
    @Expose
    private String roomName;
    @SerializedName("roomDesc")
    @Expose
    private String roomDesc;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("roomStatus")
    @Expose
    private int roomStatus;
    private Branch branch;

    public Room() {
    }

    //add
    public Room(String roomName, String roomDesc, String photo, int roomStatus, int branchId) {
        DAOBranch daoBranch = new DAOBranch();
        this.roomName=roomName;
        this.roomDesc=roomDesc;
        this.photo=photo;
        this.roomStatus=roomStatus;
        try {
            this.branch = daoBranch.searchBranch(branchId);
        } catch (Exception e){
            e.printStackTrace();
            this.branch = null;
        }
    }

    public Room(int roomId, String roomName, String roomDesc, String photo, int roomStatus, int branchId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.photo = photo;
        this.roomStatus = roomStatus;
        DAOBranch daoBranch = new DAOBranch();
        try {
            this.branch = daoBranch.searchBranch(branchId);
        } catch (Exception e){
            e.printStackTrace();
            this.branch = null;
        }
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranchId(int branchId) {
        DAOBranch daoBranch = new DAOBranch();
        try {
            this.branch = daoBranch.searchBranch(branchId);
        } catch (Exception e){
            e.printStackTrace();
            this.branch = null;
        }
    }

}
