package interfaces;

public class Employee implements Comparable<Employee>
{
    private String name;
    private double salary;

    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double perc)
    {
        salary *= (1.0 + perc / 100);
    }

    public int compareTo(Employee other)
    {
        return Double.compare(salary, other.salary);
    }
}