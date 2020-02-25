package com.example.ainabkoidairy.Models;

public class payment
{
   String paidalready,amountpayable,milkunpaid,totalmilk,totalpaid;

    public payment() {
    }

    public payment(String paidalready,String amountpayable,String milkunpaid,String totalmilk,String totalpaid) {
        this.paidalready = paidalready;
        this.amountpayable = amountpayable;
        this.milkunpaid =milkunpaid;
        this.totalmilk = totalmilk;
        this.totalpaid = totalpaid;
    }


    public String getTotalpaid() {
        return totalpaid;
    }

    public void setTotalpaid(String totalpaid) {
        this.totalpaid = totalpaid;
    }

    public String getTotalmilk() {
        return totalmilk;
    }

    public void setTotalmilk(String totalmilk) {
        this.totalmilk = totalmilk;
    }

    public String getMilkunpaid() {
        return milkunpaid;
    }

    public void setMilkunpaid(String milkunpaid) {
        this.milkunpaid = milkunpaid;
    }

    public String getPaidalready() {
        return paidalready;
    }

    public void setPaidalready(String paidalready) {
        this.paidalready = paidalready;
    }

    public String getAmountpayable() {
        return amountpayable;
    }

    public void setAmountpayable(String amountpayable) {
        this.amountpayable = amountpayable;
    }
}
