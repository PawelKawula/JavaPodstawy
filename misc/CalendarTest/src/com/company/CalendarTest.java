package com.company;
import java.time.*;
import java.util.*;

public class CalendarTest {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(9);

        int month = date.getMonthValue();
        DayOfWeek day = date.getDayOfWeek();
        int today = date.getDayOfMonth();
        date = date.minusDays(today - 1);
        System.out.println(" Pn  Wt  Śr  Cz  Pt  So  Nd");
        for (int i = 0; i < day.getValue(); ++i)
            System.out.print(" ");
        while (date.getMonthValue() == month)
        {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today)
                System.out.print("*");
            else
                System.out.print(" ");
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1)
                System.out.println();
        }
        if (date.getDayOfWeek().getValue() != 1)
            System.out.println();
    }
}
