package com.company;

import java.time.LocalDate;

public class Employee{

    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int yr, int m, int d){
        name = n;
        salary = s;
        hireDay = LocalDate.of(yr, m, d);
    }
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }
}