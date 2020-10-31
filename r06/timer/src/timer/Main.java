package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;;
import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(1000, listener);
        t.start();

        JOptionPane.showMessageDialog(null, "Zamknąć program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        System.out.println("Godzina: " + new Date());
    }
}
