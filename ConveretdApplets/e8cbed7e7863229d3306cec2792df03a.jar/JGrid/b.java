// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Hashtable;

public class b extends f
{
    static boolean c;
    static int b;
    
    public b(final String[] array, final int[] array2) {
        super(array, array2, -1, null);
    }
    
    public void a(final Graphics graphics, final Rectangle rectangle, final e e) {
        if (super.long) {
            final int width = rectangle.width;
            final int height = rectangle.height;
            final int x = rectangle.x;
            final int y = rectangle.y;
            for (int i = 0; i < f.null; ++i) {
                if (f.goto[i] != 0) {
                    final int n = f.goto[i] - 2;
                    final int n2 = height - 2;
                    final int n3 = f.if[i] + 2;
                    final int n4 = y + 2;
                    final int n5 = f.if[i] + 1;
                    final int n6 = y + 1;
                    final int n7 = n5 + f.goto[i] - 1;
                    final int n8 = y + height - 1;
                    graphics.setColor(f.byte);
                    graphics.drawLine(n5, n6, n7, n6);
                    graphics.drawLine(n5, n6, n5, n8);
                    graphics.setColor(f.char);
                    graphics.drawLine(n7, n6, n7, n8);
                    graphics.drawLine(n5, n8, n7, n8);
                    final d d = super.else[i];
                    if (JGrid.b.b != -1 && i == JGrid.b.b) {
                        d.do = "    " + d.do;
                    }
                    d.setFeature("O,C0C0C0,000000,0");
                    d.if = false;
                    d.draw(new Rectangle(n3, n4, n, n2), graphics, e);
                    if (JGrid.b.b != -1 && i == JGrid.b.b) {
                        d.do = d.do.substring(4);
                        final int n9 = n4 + n2 / 3;
                        if (JGrid.b.c) {
                            graphics.setColor(Color.gray);
                            graphics.drawLine(n3 + 6, n9, n3 + 2, n9 + 7);
                            graphics.setColor(Color.white);
                            graphics.drawLine(n3 + 6, n9, n3 + 10, n9 + 7);
                            graphics.drawLine(n3 + 2, n9 + 7, n3 + 10, n9 + 7);
                        }
                        else {
                            graphics.setColor(Color.white);
                            graphics.drawLine(n3 + 10, n9, n3 + 6, n9 + 7);
                            graphics.setColor(Color.gray);
                            graphics.drawLine(n3 + 2, n9, n3 + 6, n9 + 7);
                            graphics.drawLine(n3 + 2, n9, n3 + 10, n9);
                        }
                    }
                }
            }
            final int n10 = y + height;
            graphics.setColor(f.char);
            graphics.drawLine(x, y, x + width, y);
            graphics.drawLine(x, y + height - 1, x + width, y + height - 1);
            graphics.drawLine(x, y, x, n10);
            super.long = false;
        }
    }
}
