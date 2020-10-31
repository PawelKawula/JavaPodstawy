package SomewhatTetris;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TBlock extends Block
{
    public TBlock(Dimension square, int l)
    {
        super(square, l);
        Rectangle2D first = getSquare(0);
        int i;
        int width = squareDimension.width;
        int height = squareDimension.height;
        setFillColor(Color.GREEN);

        for (i = 1; i < l; ++i)
            addSquare(new Rectangle2D.Double(first.getX(), first.getY() + i * height,
                    width, height));
        addSquare(new Rectangle2D.Double(first.getX() + width, first.getY() + (i - 1) * height,
                width, height));
        addSquare(new Rectangle2D.Double(first.getX() - width, first.getY() + (i - 1) * height,
                width, height));
    }

    public Block rotate()
    {
        Rectangle2D first = getSquare(1);
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
        insert = new Rectangle2D.Double(insert.getX() + insert.getWidth() * -yMult,
                insert.getY() + insert.getHeight() * -xMult, insert.getWidth(), insert.getHeight());
        setSquare(i++, insert);
        insert = new Rectangle2D.Double(insert.getX() + insert.getWidth() * yMult * 2,
                insert.getY() + insert.getHeight() * xMult * 2, insert.getWidth(), insert.getHeight());
        setSquare(i, insert);
        return this;
    }
}
