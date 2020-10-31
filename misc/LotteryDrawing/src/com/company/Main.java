package com.company;
import java.util.*;

public class Main{

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);

       System.out.println("Wybierz liczbe losowanych liczb?: ");
       int k = in.nextInt();

       System.out.println("Ile jest mozliwych liczb do wyboru?: ");
       int n = in.nextInt();

       int arr[] = new int [n];
       int res[] = new int [k];

       for (int i = 0; i < n; ++i)
           arr[i] = i + 1;

       for (int i = 0; i < k; ++i)
       {
           int r = (int)(Math.random() * n);
           res[i] = arr[r];
           arr[r] = n--;
       }
       Arrays.sort(res);
       System.out.println("Oto wylosowane liczby: ");
       for (int x : res)
           System.out.println(x);
       for (int x : arr)
       {
           System.out.print(x);
           System.out.print(' ');
       }
    }
}
