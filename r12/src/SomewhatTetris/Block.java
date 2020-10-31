package SomewhatTetris;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Block
{
    protected static final Dimension squareDimension= new Dimension(20, 20);

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int ERROR = 999;

    private int length;
    private ArrayList<Rectangle2D> squares;
    private Color fillColor;
    private Color drawColor;

    public Color getFillColor()
    {
        return fillColor;
    }

    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
    }

    public Color getDrawColor()
    {
        return drawColor;
    }

    public void setDrawColor(Color drawColor)
    {
        this.drawColor = drawColor;
    }

    public Block(Dimension firstPos, int l)
    {
        length = l;
        squares = new ArrayList<>(6);
        squares.add(new Rectangle2D.Double(firstPos.width, firstPos.height,
                squareDimension.width, squareDimension.height));
        drawColor = Color.BLACK;
    }

    public void draw(Graphics2D g2)
    {
        Color prevColor = g2.getColor();
        g2.setColor(drawColor);
        for (Rectangle2D square : squares)
            g2.draw(square);
        g2.setColor(prevColor);
    }

    public void fill(Graphics2D g2)
    {
        Color prevColor = g2.getColor();
        g2.setColor(fillColor);
        for (Rectangle2D square : squares)
            g2.fill(square);
        g2.setColor(prevColor);
    }

    public int getLength()
    {
        return length;
    }

    protected void addSquare(Rectangle2D square)
    {
        squares.add(square);
    }

    protected Rectangle2D getSquare(int i)
    {
        return squares.get(i);
    }

    protected void setSquare(int i, Rectangle2D square)
    {
        squares.set(i, square);
    }

    public ArrayList<Rectangle2D> getAllSquares()
    {
        return squares;
    }

    protected int getRotation()
    {
        Dimension first = new Dimension((int) squares.get(0).getX(), (int) squares.get(0).getY());
        Dimension second = new Dimension((int) squares.get(1).getX(), (int) squares.get(1).getY());
        if (first.width == second.width && first.height > second.height)
            return NORTH;
        if (first.width < second.width && first.height == second.height)
            return EAST;
        if (first.width == second.width && first.height < second.height)
            return SOUTH;
        if (first.width > second.width && first.height == first.height)
            return WEST;
        return ERROR;
    }

    protected Dimension mult()
    {
        int rotation = getRotation();
        int xMult = 0, yMult = 0;
        if (rotation == NORTH)
        {
            xMult = 1;
            yMult = 0;
        }
        else if (rotation == EAST)
        {
            xMult = 0;
            yMult = 1;
        }
        else if (rotation == SOUTH)
        {
            xMult = -1;
            yMult = 0;
        }
        else if (rotation == WEST)
        {
            xMult = 0;
            yMult = -1;
        }
        return new Dimension(xMult, yMult);
    }
    public abstract Block rotate();
    public boolean checkCollision(Rectangle2D square)
    {
        for (Rectangle2D sq : squares)
            if (sq.intersects(square))
                return true;
        return false;
    }

    public boolean move(int x, int y)
    {
        for (Rectangle2D square : squares)
            square.setFrame(square.getX() + x, square.getY() + y,
                    square.getWidth(), square.getHeight());
        return true;
    }
}
