package anonymousInnerClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        TalkingClock tClock = new TalkingClock();
        tClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Zamknąć program?");
        System.exit(0);
    }
}

class TalkingClock
{
    public void start(int interval, boolean beep)
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Kiedy usłyszysz dżwięk, będzie godzina " + new Date());
                if (beep) System.out.println(" BEEP");
            }
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }
}