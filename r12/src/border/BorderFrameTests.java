package border;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BorderFrameTests
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            JFrame frame = new BorderFrame();
            frame.setTitle("BorderFrameTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BorderFrame extends JFrame
{
    private JPanel demoPanel;
    private JPanel buttonPanel;
    private ButtonGroup group;

    public BorderFrame()
    {
        demoPanel = new JPanel();
        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("LoweredBevel", BorderFactory.createLoweredBevelBorder());
        addRadioButton("RaisedBevel", BorderFactory.createRaisedBevelBorder());
        addRadioButton("Etched", BorderFactory.createEtchedBorder());
        addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
        addRadioButton("Matte", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
        addRadioButton("Empty", BorderFactory.createEmptyBorder());

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Rodzaje obramowań");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(demoPanel);
        pack();
    }

    public void addRadioButton(String buttonName, Border b)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(event -> demoPanel.setBorder(b));
        group.add(button);
        buttonPanel.add(button);
    }
}
