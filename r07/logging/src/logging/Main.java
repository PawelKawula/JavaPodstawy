package logging;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
	    if (System.getProperty("java.util.logging.class") == null
            && System.getProperty("java.util.logging.config.file") == null)
        {
            try
            {
                Logger.getLogger("com.hortsmann.corejava").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViever.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.hortsmann.corejava").addHandler(handler);
            }
            catch (IOException e)
            {
                Logger.getLogger("com.hortsmann.corejava").log(Level.SEVERE,
                        "Nie można utworzyć obiektu obsługi pliku dziennika", e);
            }
        }

        EventQueue.invokeLater(() ->
        {
            Handler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.hortsmann.corejava").addHandler(windowHandler);

            JFrame frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.hortsmann.corejava").fine("Wyświetlanie ramki");
            frame.setVisible(true);
        });
    }


}


class ImageViewerFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("com.hortsmann.corejava");

    public ImageViewerFrame()
    {
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Otwórz");
        menu.add(menu);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Zamknij");
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                logger.fine("Zamykanie.");
                System.exit(0);
            }
        });

        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }
    private class FileOpenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            logger.log(Level.FINE, "Ścieżka=" + chooser.getSelectedFile().getPath());
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
            {
                public boolean accept(File f)
                {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                public String getDescription()
                {
                    return "Obrazy GIF";
                }
            });

            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            if (r == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Wczytywanie pliku {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.fine("Anulowano okno otwierania pliku");
            logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}

class WindowHandler extends StreamHandler
{
    private JFrame frame;

    public WindowHandler()
    {
        frame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream()
        {
            @Override
            public void write(int b) throws IOException
            {
            }

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));
            }
        });
    }

    public void publish(LogRecord record)
    {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}
