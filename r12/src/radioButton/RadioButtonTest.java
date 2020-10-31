package radioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RadioButtonTest
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JFrame frame = new RadioButtonFrame();
        frame.setTitle("RadioButtonTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class RadioButtonFrame extends JFrame
{
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;

    public RadioButtonFrame()
    {
        label = new JLabel("Koń i żółw grali w kości z piękną ćmą u żródła.");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Mała", 8);
        addRadioButton("Średnia", 12);
        addRadioButton("Duża", 18);
        addRadioButton("Bardzo duża", 36);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();

    }

    public void addRadioButton(String name, final int size)
    {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton radioButton = new JRadioButton(name, selected);
        group.add(radioButton);
        buttonPanel.add(radioButton);

        ActionListener listener = event ->
                label.setFont(new Font("Serif", Font.PLAIN, size));
        radioButton.addActionListener(listener);
    }
}
