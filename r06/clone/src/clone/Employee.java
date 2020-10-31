package clone;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable
{
   private final String name;
   private double salary;
   private Date hireDay;

    public Employee(String name, double salary)
    {
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date();
    }

    public Employee clone() throws CloneNotSupportedException
    {
        Employee cloned = (Employee) super.clone();

        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }

    public void setHireDay(int yr, int m, int d)
    {
        Date newHireDay = new GregorianCalendar(yr, m - 1, d).getTime();

        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double perc)
    {
        salary *= (1.0 + perc / 100);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
