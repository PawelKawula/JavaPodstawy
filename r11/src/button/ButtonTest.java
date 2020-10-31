package button;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(()->
        {
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("Button");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ButtonFrame extends JFrame
{
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ButtonFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JButton yellowButton = new JButton("Zółty");
        JButton blueButton = new JButton("Niebieski");
        JButton redButton = new JButton("Czerwony");

        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);

        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);

        buttonPanel = new JPanel();

        buttonPanel.add(yellowButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);

        add(buttonPanel);
   }

    private class ColorAction implements ActionListener
    {
        private Color backgroundColor;

        public ColorAction(Color c)
        {
            backgroundColor = c;
        }

        public void actionPerformed(ActionEvent event)
        {
            buttonPanel.setBackground(backgroundColor);
        }
    }
}


