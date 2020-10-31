package notHelloWorld;

import javax.swing.*;
import java.awt.*;

public class NotHelloWorld
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new NotHelloWorldFrame();
            frame.setTitle("NotHelloWorld");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class NotHelloWorldFrame extends JFrame
{
    public NotHelloWorldFrame()
    {
        add(new NotHelloWorldPanel());
        pack();
    }
}

class NotHelloWorldPanel extends JComponent
{
    public static final int MESSAGE_X = 15;
    public static final int MESSAGE_Y = 100;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public void paintComponent(Graphics g)
    {
        g.drawString("To nie jest program \"Witaj w świecie\".", MESSAGE_X, MESSAGE_Y);
    }
    public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}