package com.example.niloychowdhury.cgpacalculator.Model;

/**
 * Created by Niloy Chowdhury on 2017-07-24.
 */

public class Result {
    private double credit;
    private double gpa;
    private double total;


    public Result(double credit, double gpa, double total) {
        this.credit = credit;
        this.gpa = gpa;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public double getCredit() {
        return credit;
    }

    public double getGpa() {
        return gpa;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;

    }
}
