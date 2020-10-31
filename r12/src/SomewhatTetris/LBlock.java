package SomewhatTetris;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LBlock extends Block
{
    public LBlock(Dimension square, int length)
    {
        super(square, length);
        int i;
        int width = squareDimension.width;
        int height = squareDimension.height;
        Rectangle2D first = getSquare(0);
        setFillColor(Color.RED);

        for (i = 1; i < length; ++i)
            addSquare(new Rectangle2D.Double(first.getX(), first.getY() + i * height,
                    width, height));
        addSquare(new Rectangle2D.Double(first.getX() - width, first.getY() + (i - 1) * height,
                width, height));
    }

    /*
     * When rotating always check collision
     * @params void
     * @returns void
     */
    public Block rotate()
    {
        Rectangle2D first = getSquare(0);
        int i;
        int xMult = mult().width;
        int yMult = mult().height;
        int length = getLength();
        Rectangle2D insert = new Rectangle2D.Double(0, 0, 0, 0);

        first = new Rectangle2D.Double(first.getX() - first.getWidth() * xMult,
                first.getY() - first.getHeight() * yMult, first.getWidth(), first.getHeight());
        setSquare(0, first);

        for (i = 1; i < length; ++i)
        {
            insert = new Rectangle2D.Double(first.getX() + first.getWidth() * i * xMult,
                    first.getY() + first.getHeight() * i * yMult, first.getWidth(), first.getHeight());
            setSquare(i, insert);
        }

        int rotation = getRotation();

        if (rotation == NORTH || rotation == SOUTH)
            insert = new Rectangle2D.Double(insert.getX() + insert.getWidth() * -yMult,
                    insert.getY() + insert.getHeight() * -xMult, insert.getWidth(), insert.getHeight());
        else if (rotation == EAST || rotation == WEST)
            insert = new Rectangle2D.Double(insert.getX() + insert.getWidth() * yMult,
                    insert.getY() + insert.getHeight() * xMult, insert.getWidth(), insert.getHeight());
        else
            try
            {
                throw new Exception("ZJEBALES ROTACJE");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        setSquare(i, insert);
        return this;
    }
}
