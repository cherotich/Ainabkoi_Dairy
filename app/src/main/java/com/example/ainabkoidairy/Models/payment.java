package com.example.ainabkoidairy.Models;

public class payment
{
    String paidalready, amountpayable, milkunpaid, totalmilk, totalpaid, formonth, foryear, paidnow, forday, time, farmeridno;

    public payment() {
    }

    public payment(String farmeridno, String time, String forday, String paidalready, String amountpayable, String milkunpaid, String totalmilk, String totalpaid, String formonth, String foryear, String paidnow) {
        this.paidalready = paidalready;
        this.amountpayable = amountpayable;
        this.milkunpaid =milkunpaid;
        this.totalmilk = totalmilk;
        this.totalpaid = totalpaid;
        this.formonth = formonth;
        this.foryear = foryear;
        this.paidnow = paidnow;
        this.forday = forday;
        this.time = time;
        this.farmeridno = farmeridno;

    }

    public String getFarmeridno() {
        return farmeridno;
    }

    public void setFarmeridno(String farmeridno) {
        this.farmeridno = farmeridno;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getForday() {
        return forday;
    }

    public void setForday(String forday) {
        this.forday = forday;
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


    public String getFormonth() {
        return formonth;
    }

    public void setFormonth(String formonth) {
        this.formonth = formonth;
    }

    public String getForyear() {
        return foryear;
    }

    public void setForyear(String foryear) {
        this.foryear = foryear;
    }

    public String getPaidnow() {
        return paidnow;
    }

    public void setPaidnow(String paidnow) {
        this.paidnow = paidnow;
    }
}
