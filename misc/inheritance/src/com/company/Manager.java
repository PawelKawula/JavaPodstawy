package com.company;

public class Manager extends Employee{

    private double bonus;

    public Manager(String n, double s, int yr, int m, int d){
        super(n, s, yr, m, d);
        bonus = 0;
    }

    public double getSalary(){
        return bonus + super.getSalary();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}