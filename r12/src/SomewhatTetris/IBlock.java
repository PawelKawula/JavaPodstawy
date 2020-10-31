package SomewhatTetris;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class IBlock extends Block
{
    public IBlock(Dimension square, int l)
    {
        super(square, l);
        int i;
        int width = squareDimension.width;
        int height = squareDimension.height;
        Rectangle2D first = getSquare(0);
        setFillColor(Color.GREEN);

        for (i = 1; i < l; ++i)
            addSquare(new Rectangle2D.Double(first.getX(), first.getY() + i * height,
                    width, height));
    }

    public Block rotate()
    {
        Rectangle2D center = getSquare(1);
        int a = (int) center.getWidth();
        Rectangle2D insert;
        int length = getLength();
        int rotation = getRotation();
        int vert = (rotation == NORTH || rotation == SOUTH) ? 1 : 0;
        int horiz = (vert == 0) ? 1 : 0;

        insert = new Rectangle2D.Double(center.getX() - a * vert,
                center.getY() - a * horiz, a , a);
        setSquare(0, insert);
        for (int i = 2; i < length; ++i)
        {
            insert = new Rectangle2D.Double(center.getX() + a * (i - 1) * vert,
                    center.getY() + (i - 1 ) * a * horiz, a, a);
            setSquare(i, insert);
    }

        return this;
    }
}
