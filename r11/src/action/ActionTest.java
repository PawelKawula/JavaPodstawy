package action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionTest
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
        JFrame frame = new ActionFrame();
        frame.setTitle("ActionTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ActionFrame extends JFrame
{
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ActionFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        buttonPanel = new JPanel();

        Action yellowAction = new ColorAction("Żółty",
                new ImageIcon("yellow-ball.gif"),
                Color.YELLOW);
        Action blueAction = new ColorAction("Niebieski",
                new ImageIcon("blue-ball.gif"), Color.BLUE);
        Action redAction = new ColorAction("Czerwony",
                new ImageIcon("red-ball.gif"), Color.RED);

        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        add(buttonPanel);

        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Z"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl N"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl C"), "panel.red");
        imap.put(KeyStroke.getKeyStroke("esc"), "esc");

        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
        amap.put("esc", redAction);
//        amap.put("esc", new AbstractAction()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                dispatchEvent(new WindowEvent(getWindows()[0], WindowEvent.WINDOW_CLOSED));
//            }
//        });
    }

    class ColorAction extends AbstractAction
    {
        public ColorAction(String name, Icon icon, Color c)
        {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Ustaw kolor panelu na " +
                    name.toLowerCase());
            putValue("color", c);
        }

        public void actionPerformed(ActionEvent event)
        {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}