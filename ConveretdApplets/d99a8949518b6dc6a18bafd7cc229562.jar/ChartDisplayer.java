import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChartDisplayer extends StockDisplayer
{
    public Rectangle drawPoint(final Graphics graphics, final int n, final Point point, final double n2) {
        final StockValue stockValue = super.values.elementAt(n);
        if (stockValue == null) {
            return null;
        }
        graphics.setColor(Color.blue);
        final int n3 = point.y + (int)(stockValue.min * n2);
        final int n4 = point.y + (int)(stockValue.max * n2);
        final int n5 = point.y + (int)(stockValue.last * n2);
        graphics.drawLine(point.x - 1, n3, point.x + 1, n3);
        graphics.drawLine(point.x - 1, n4, point.x + 1, n4);
        graphics.drawLine(point.x, n3, point.x, n4);
        graphics.setColor(Color.red);
        graphics.drawLine(point.x - 1, n5, point.x + 1, n5);
        return new Rectangle(point.x - 4, n5 - 4, 7, 7);
    }
}
