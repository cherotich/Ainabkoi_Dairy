package com.example.ainabkoidairy.Models;

public class milk
{
    public String totalmilk,amount,date,day,milk_id,month,monthstring,time,year;

    public milk() {
    }

    public milk(String totalmilk, String amount, String date, String day, String milk_id, String month, String monthstring, String time, String year) {
        this.totalmilk = totalmilk;
        this.amount = amount;
        this.date = date;
        this.day = day;
        this.milk_id = milk_id;
        this.month = month;
        this.monthstring = monthstring;
        this.time = time;
        this.year = year;
    }

    public String getTotalmilk() {
        return totalmilk;
    }

    public void setTotalmilk(String totalmilk) {
        this.totalmilk = totalmilk;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMilk_id() {
        return milk_id;
    }

    public void setMilk_id(String milk_id) {
        this.milk_id = milk_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthstring() {
        return monthstring;
    }

    public void setMonthstring(String monthstring) {
        this.monthstring = monthstring;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
