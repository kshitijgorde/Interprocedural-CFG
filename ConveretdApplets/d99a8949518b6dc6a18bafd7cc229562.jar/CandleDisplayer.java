import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CandleDisplayer extends StockDisplayer
{
    public Rectangle drawPoint(final Graphics graphics, final int n, final Point point, final double n2) {
        final StockValue stockValue = super.values.elementAt(n);
        if (stockValue == null) {
            return null;
        }
        graphics.setColor(Color.black);
        final int n3 = point.y + (int)(stockValue.min * n2);
        final int n4 = point.y + (int)(stockValue.max * n2);
        final int n5 = point.y + (int)(stockValue.open * n2);
        final int n6 = point.y + (int)(stockValue.last * n2);
        graphics.drawLine(point.x - 1, n3, point.x + 1, n3);
        graphics.drawLine(point.x - 1, n4, point.x + 1, n4);
        graphics.drawLine(point.x, n3, point.x, n4);
        int n7;
        Color color;
        if (stockValue.open < stockValue.last) {
            n7 = n5;
            color = Color.white;
        }
        else {
            n7 = n6;
            color = Color.black;
        }
        graphics.drawRect(point.x - 3 - 1, n7 - 1, 8, Math.abs(n5 - n6));
        graphics.setColor(color);
        graphics.fillRect(point.x - 3, n7, 7, Math.abs(n5 - n6) - 1);
        return new Rectangle(point.x - 4, n6 - 4, 7, 7);
    }
}
