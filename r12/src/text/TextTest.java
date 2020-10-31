package text;

import javax.swing.*;
import java.awt.*;

public class TextTest
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
        JFrame frame = new TextComponentFrame();
        frame.setTitle("TextTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class TextComponentFrame extends JFrame
{
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;

    public TextComponentFrame()
    {
        final JTextField textField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("Nazwa użytkownika", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Hasło", SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();

        JButton insertButton = new JButton("Wstaw");
        southPanel.add(insertButton);
        insertButton.addActionListener(event ->
                textArea.append("Nazwa użytkownika: " + textField.getText() + " Hasło = " +
                        new String(passwordField.getPassword() + "\n")));
        add(southPanel, BorderLayout.SOUTH);
        pack();
    }
}