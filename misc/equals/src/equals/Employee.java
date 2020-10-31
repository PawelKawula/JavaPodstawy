package equals;

import java.time.*;
import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int yr, int m, int d)
    {
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

    public void raiseSalary(double wt){
        salary *= (1.0 + wt / 100);
    }

    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Employee other = (Employee) otherObject;

        return Objects.equals(name, other.name)
                && salary == other.salary
                && Objects.equals(hireDay, other.hireDay);
    }

    public int hashCode(){
        return Objects.hash(name, salary, hireDay);
    }

    public String toString(){
        return getClass().getName() + "[name=" + name
        + ", salary=" + salary + ",hireDay=" + hireDay + "]";
    }

}
