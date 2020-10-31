package comboBox;

import javax.swing.*;
import java.awt.*;

public class ComboBoxTest
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
            JFrame frame = new ComboBoxFrame();
            frame.setTitle("ComboBoxTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ComboBoxFrame extends JFrame
{
    private JComboBox<String> faceCombo;
    private JLabel label;
    private static final int DEFAULT_SIZE = 24;

    public ComboBoxFrame()
    {
        label = new JLabel("Koń i pies grali w kości z piękną ćmą u źródła.");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        faceCombo = new JComboBox<>();
        faceCombo.addItem("Serif");
        faceCombo.addItem("SansSerif");
        faceCombo.addItem("Monospaced");
        faceCombo.addItem("Dialog");
        faceCombo.addItem("DialogInput");

        faceCombo.addActionListener(event ->
                label.setFont(
                        new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()),
                                Font.PLAIN, DEFAULT_SIZE)));

        JPanel comboPanel = new JPanel();
        comboPanel.add(faceCombo);
        add(comboPanel, BorderLayout.SOUTH);
        pack();
    }
}
