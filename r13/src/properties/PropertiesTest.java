package properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Properties;

public class PropertiesTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            PropertiesFrame frame = new PropertiesFrame();
            frame.setVisible(true);
        });
    }
}

class PropertiesFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private File propertiesFile;
    private Properties settings;

    public PropertiesFrame()
    {
        String userDir = System.getProperty("user.home");
        File propertiesDir = new File(userDir, ".corejava");
        if (!propertiesDir.exists()) propertiesDir.mkdir();
        propertiesFile = new File(propertiesDir, "program.properties");
        System.out.println(propertiesFile.getAbsolutePath());

        Properties defaultSettings = new Properties();
        defaultSettings.put("left", "0");
        defaultSettings.put("top", "0");
        defaultSettings.put("width", "" + DEFAULT_WIDTH);
        defaultSettings.put("height", "" + DEFAULT_HEIGHT);
        defaultSettings.put("title", "");

        settings = new Properties(defaultSettings);

        if (propertiesFile.exists())
            try (InputStream in = new FileInputStream(propertiesFile))
            {
                settings.load(in);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

            int left = Integer.parseInt(settings.getProperty("left"));
            int top = Integer.parseInt(settings.getProperty("top"));
            int width = Integer.parseInt(settings.getProperty("width"));
            int height = Integer.parseInt(settings.getProperty("height"));
            setBounds(left, top, width, height);

            String title = settings.getProperty("title");
            if (title.equals("")) title = JOptionPane.showInputDialog("Wpisz tytul ramki:");
            if (title == null) title = "";
            setTitle(title);

            addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    settings.put("left", "" + getX());
                    settings.put("top", "" + getY());
                    settings.put("width", "" + getWidth());
                    settings.put("height", "" + getHeight());
                    settings.put("title", "" + getTitle());

                    try (OutputStream out = new FileOutputStream(propertiesFile))
                    {
                        settings.store(out, "Program Properties");
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }
            });
    }
}
