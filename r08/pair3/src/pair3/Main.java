package pair3;

import java.time.LocalDate;

public class Main
{

    public static void main(String[] args)
    {
        Manager ceo = new Manager("Stanisław Skąpy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Piotr Podstępny", 600000, 2003, 12, 15);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);
        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = { ceo, cfo };

        Pair<Employee> result = new Pair<>();
        minMaxBonus(managers, result);
        System.out.println("pierwszy: " + result.getFirst().getName() + ", drugi: " + result.getSecond().getName());
    }
    public static void printBuddies(Pair<? extends Employee> p)
    {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " i " + second.getName() + " są kumplami.");
    }

    public static void minMaxBonus(Manager[] a, Pair<? super Manager> result)
    {
        if (a == null || a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; ++i)
        {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result)
    {
        minMaxBonus(a, result);
        PairAlg.swapHelper(result);
    }
}

class PairAlg
{
    public static boolean hasNulls(Pair<?> p)
    {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) { swapHelper(p); }

    public static <T> void swapHelper(Pair<T> p)
    {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
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

    public double getBonus()
    {
        return bonus;
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

class Pair<T>
{
    private T first;
    private T second;

    public Pair(T first, T second)
    {
        this.first = first;
        this.second = second;
    }

    public Pair()
    {
        this.first = this.second = null;
    }

    public T getFirst()
    {
        return first;
    }

    public void setFirst(T first)
    {
        this.first = first;
    }

    public T getSecond()
    {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
