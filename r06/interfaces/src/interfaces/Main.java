package interfaces;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Henryk kwiatek", 35000);
        staff[1] = new Employee("Karol Kowalski", 75000);
        staff[2] = new Employee("Tadeusz Nowak", 38000);

        Arrays.sort(staff);

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + "salary=" + e.getSalary());
    }
}
