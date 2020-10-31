package map;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MapTest
{
    public static void main(String[] args)
    {
        Map<String, Employee> staff = new HashMap<>();
        staff.put("144-25-5464", new Employee("Anna Kowalska"));
        staff.put("567-24-2546", new Employee("Henryk Kwiatek"));
        staff.put("157-62-7935", new Employee("Marcin Nowak"));
        staff.put("456-62-5527", new Employee("Franciszek Frankowski"));

        System.out.println(staff);

        staff.remove("567-24-2546");

        staff.put("456-62-5527", new Employee("Weronika Kowalska"));

        System.out.println(staff.get("157-62-7935"));

        staff.forEach((k, v) ->
            System.out.println("klucz=" + k + ", wartość=" + v));
    }
}

class Manager extends Employee
{

    private double bonus;

    public Manager(String n, double s, int yr, int m, int d)
    {
        super(n, s, yr, m, d);
        bonus = 0;
    }

    public double getSalary()
    {
        double sal = super.getSalary();
        return sal + bonus;
    }

    public void setBonus(double b)
    {
        bonus = b;
    }
}

class Employee
{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int yr, int m, int d)
    {
        name = n;
        salary = s;
        hireDay = LocalDate.of(yr, m, d);
    }

    public Employee(String n)
    {
        name = n;
        salary = 0;
        hireDay = LocalDate.of(1970, 1, 1);
    }

    public String toString()
    {
        return "[name=" + name + "]";
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public LocalDate getHireDay()
    {
        return hireDay;
    }

    public void raiseSalary(double pr)
    {
        salary *= (1.0 + pr / 100);
    }
}


