package linkedlist;

import java.util.*;

public class LinkedListTest
{
    public static void main(String[] args)
    {
        List<String> a = new LinkedList<>();
        a.add("Ania");
        a.add("Karol");
        a.add("Eryk");

        List<String> b = new LinkedList<>();
        b.add("Bartek");
        b.add("Daniel");
        b.add("Franek");
        b.add("Gosia");


        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while(bIter.hasNext())
        {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);

        bIter = b.iterator();
        while (bIter.hasNext())
        {
            bIter.next();
            if (bIter.hasNext())
            {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);

        a.removeAll(b);

        System.out.println(a);
    }
}
