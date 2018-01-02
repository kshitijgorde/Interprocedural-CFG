// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

public class JChartCell extends d
{
    private static int long;
    Vector null;
    
    public void setValue(final Object o) {
        this.null = (Vector)o;
        while (this.null.size() > JChartCell.long) {
            this.null.removeElementAt(0);
        }
    }
    
    public Object getObject() {
        return new Vector();
    }
    
    public void draw(final Rectangle rectangle, final Graphics graphics, final e e) {
        if (super.for) {
            if (super.if) {
                graphics.setColor(Color.blue);
            }
            else {
                graphics.setColor(Color.white);
            }
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            final int size = this.null.size();
            final int n = (size > rectangle.width / 2) ? (size - rectangle.width / 2) : 0;
            int x = rectangle.x;
            for (int i = n; i < size; ++i) {
                final double doubleValue = new Double(this.null.elementAt(i).toString());
                if (doubleValue > 0.0) {
                    graphics.setColor(Color.green);
                }
                else {
                    graphics.setColor(Color.red);
                }
                graphics.drawLine(x, rectangle.y + rectangle.height / 2, x, (int)(rectangle.y + rectangle.height / 2 - doubleValue * rectangle.height / 2.0));
                x += 2;
            }
            super.for = false;
        }
    }
    
    static {
        JChartCell.long = 60;
    }
}
