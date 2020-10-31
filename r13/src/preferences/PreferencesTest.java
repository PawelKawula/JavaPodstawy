package preferences;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.prefs.Preferences;

public class PreferencesTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            PreferencesFrame frame = new PreferencesFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class PreferencesFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private Preferences root = Preferences.userRoot();
    private Preferences node = root.node("/com/hortsmann/corejava");

    public PreferencesFrame()
    {
        int left = node.getInt("left", 0);
        int top = node.getInt("top", 0);
        int width = node.getInt("width", 0);
        int height = node.getInt("height", 0);
        setBounds(left, top, width, height);

        String title = node.get("title", "");
        if (title.equals(""))
            title = JOptionPane.showInputDialog("Podaj tytul ramki:");
        if (title == null) title = "";
        setTitle(title);

        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new FileNameExtensionFilter("Pliki XML", "xml"));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

        JMenuItem exportItem = new JMenuItem("Eksportuj preferencje");
        menu.add(exportItem);
        exportItem.addActionListener(event ->
        {
            if (chooser.showSaveDialog(PreferencesFrame.this) ==
                JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    savePreferences();
                    OutputStream out = new FileOutputStream(chooser.getSelectedFile());
                    node.exportSubtree(out);
                    out.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        JMenuItem importItem = new JMenuItem("Importuj preferencje");
        menu.add(importItem);
        importItem.addActionListener(event ->
        {
            if (chooser.showOpenDialog(PreferencesFrame.this) ==
                    JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    InputStream in = new FileInputStream(chooser.getSelectedFile());
                    Preferences.importPreferences(in);
                    in.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Zamknij");
        menu.add(exitItem);
        exitItem.addActionListener(event ->
        {
            savePreferences();
            System.exit(0);
        });

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
               savePreferences();
               System.exit(0);
            }
        });
    }

    public void savePreferences()
    {
        node.putInt("left", getX());
        node.putInt("top", getY());
        node.putInt("width", getWidth());
        node.putInt("height", getHeight());
        node.put("title", getTitle());
    }
}
