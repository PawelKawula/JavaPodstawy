package SomewhatTetris;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class SomewhatTetris
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
        JFrame frame = new SomehatTetrisFrame();
        frame.setTitle("SomewhatTetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class SomehatTetrisFrame extends JFrame
{
    public SomehatTetrisFrame()
    {
        add(new SomewhatTetrisPanel());
        pack();
    }
}

class SomewhatTetrisPanel extends JComponent
{
    public enum ShapeEnum
    {
        I, L, T;

        private static final List<ShapeEnum> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random random = new Random();

        public static ShapeEnum randomBlock()
        {
            return VALUES.get(random.nextInt(SIZE));
        }
    }

    private JPanel drawPanel;
    private Block current;
    private ArrayList<ArrayList<Rectangle2D>> rows;
    private int topRow;

    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 200;

    public SomewhatTetrisPanel()
    {
        topRow = 0;
        rows = new ArrayList<>(DEFAULT_HEIGHT / Block.squareDimension.height);

        drawPanel = new JPanel();
        for (int i = 0; i < DEFAULT_HEIGHT; i += Block.squareDimension.height)
            rows.add(new ArrayList<>(DEFAULT_WIDTH / Block.squareDimension.width));
       newBlock();

        InputMap imap = drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("A"), "left");
        imap.put(KeyStroke.getKeyStroke("S"), "down");
        imap.put(KeyStroke.getKeyStroke("D"), "right");
        imap.put(KeyStroke.getKeyStroke("R"), "rotate");

        ActionMap amap = drawPanel.getActionMap();
        Action movementKey = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String ac = e.getActionCommand();
                int x = 0, y = 0;
                switch (ac)
                {
                    case "a":
                        x = -20;
                        break;
                    case "s":
                        nextTurn();
                        return;
                    case "d":
                        x = 20;
                        break;
                    case "r":
                        current.rotate();
                        break;
                }
                Rectangle2D screenBorder = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                current.move(x, 0);
                if (checkCollision())
                {
                    if (ac.equals("r"))
                        for (int i = 0; i < 3; ++i)
                            current.rotate();
                    else
                        current.move(-x, 0);
                }
                repaint();
            }
        };
        amap.put("rotate", movementKey);
        amap.put("up", movementKey);
        amap.put("right", movementKey);
        amap.put("left", movementKey);
        amap.put("down", movementKey);

        Timer nextTurnTimer = new Timer(200, new TurnAction());
//        nextTurnTimer.start();

        add(drawPanel);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        for (ArrayList<Rectangle2D> row : rows)
            for (Rectangle2D square : row)
                g2.fill(square);
        current.fill(g2);
    }

    public void newBlock()
    {
        ShapeEnum block = ShapeEnum.randomBlock();

        Random random = new Random();
        int length = random.nextInt(1) + 3;
        Dimension pos = new Dimension(DEFAULT_WIDTH / 2 - Block.squareDimension.width / 2, 1);
        switch (block)
        {
            case L:
                current = new LBlock(pos, length);
                break;
            case T:
                current = new TBlock(pos, length);
                break;
            case I:
                current = new IBlock(pos, length);
                break;
        }
    }

    public boolean checkCollision()
    {
        Rectangle2D screenBox = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        for (Rectangle2D square : current.getAllSquares())
            if (!screenBox.contains(square))
                return true;
        for (ArrayList<Rectangle2D> row : rows)
            for (Rectangle2D square : row)
                if (current.checkCollision(square))
                    return true;
        return false;
    }

    public void nextTurn()
    {
        current.move(0, Block.squareDimension.height);
        if (checkCollision())
        {
            current.move(0, -Block.squareDimension.height);
            for (Rectangle2D square : current.getAllSquares())
            {
                int rowIndex = (DEFAULT_HEIGHT - (int) square.getY()) / Block.squareDimension.height;

                if (topRow < rowIndex)
                    topRow = rowIndex;
                rows.get(rowIndex).add(square);
                int squaresInRow = rows.get(rowIndex).size();
                int maxSquaresInRow = DEFAULT_WIDTH / Block.squareDimension.width;
                if (squaresInRow >= maxSquaresInRow)
                {
                    for (int i = rowIndex; i < topRow; ++i)
                    {
                        rows.get(i).clear();
                        rows.get(i).addAll(rows.get(i + 1));
                    }
                    rows.get(topRow--).clear();
                    for (int i = topRow; i >= rowIndex; --i)
                    {
                        ArrayList<Rectangle2D> row = rows.get(i);
                        ArrayList<Rectangle2D> insert = new ArrayList<>(row.size());
                        for (Rectangle2D sq : row)
                            insert.add(new Rectangle2D.Double(sq.getX(), sq.getY() + sq.getHeight(),
                                    sq.getWidth(), sq.getHeight()));
                        rows.set(i, insert);
                    }
                }
            }
            newBlock();
            if (checkCollision())
                System.exit(0);
        }
        repaint();
    }

    class TurnAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           nextTurn();
        }
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(DEFAULT_WIDTH + 1, DEFAULT_HEIGHT + 202);
    }

}

