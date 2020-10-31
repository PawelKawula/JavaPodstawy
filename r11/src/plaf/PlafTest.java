package plaf;

import javax.swing.*;

public class PlafTest
{
    public static void main(String[] args)
    {
        PlafFrame frame = new PlafFrame();
        frame.setTitle("PlafFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class PlafFrame extends JFrame
{
    private JPanel buttonPanel;

    public PlafFrame()
    {
        buttonPanel = new JPanel();
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        System.out.println("Motywy:");
        for (UIManager.LookAndFeelInfo info : infos)
            makeButton(info.getName(), info.getClassName());
        add(buttonPanel);
        pack();
    }

    private void makeButton(String name, String className)
    {
        JButton button = new JButton(name);
        buttonPanel.add(button);

        System.out.println(name + " : " + className);

        button.addActionListener(event ->
        {
            try
            {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
                pack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}

