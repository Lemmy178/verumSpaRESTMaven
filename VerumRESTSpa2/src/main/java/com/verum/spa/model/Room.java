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
    @SerializedName("branchId")
    @Expose
    private int branchId;

    public Room() {
    }

    //add
    public Room(String roomName, String roomDesc, String photo, int roomStatus, int branchId) {
        this.roomName=roomName;
        this.roomDesc=roomDesc;
        this.photo=photo;
        this.roomStatus=roomStatus;
        this.branchId=branchId;
    }

    public Room(int roomId, String roomName, String roomDesc, String photo, int roomStatus, int branchId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.photo = photo;
        this.roomStatus = roomStatus;
        this.branchId = branchId;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

}
