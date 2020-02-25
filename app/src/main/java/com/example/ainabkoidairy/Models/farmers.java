package com.example.ainabkoidairy.Models;

public class farmers {
  public  String farmerid, farmername,farmerphonenumber,location;

  public farmers() {
  }

  public farmers(String farmerid, String farmername, String farmerphonenumber,String location) {
    this.farmerid = farmerid;
    this.farmername = farmername;
    this.farmerphonenumber = farmerphonenumber;
    this.location = location;
  }


  public String getFarmerid() {
    return farmerid;
  }

  public void setFarmerid(String farmerid) {
    this.farmerid = farmerid;
  }

  public String getFarmername() {
    return farmername;
  }

  public void setFarmername(String farmername) {
    this.farmername = farmername;
  }

  public String getFarmerphonenumber() {
    return farmerphonenumber;
  }

  public void setFarmerphonenumber(String farmerphonenumber) {
    this.farmerphonenumber = farmerphonenumber;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
