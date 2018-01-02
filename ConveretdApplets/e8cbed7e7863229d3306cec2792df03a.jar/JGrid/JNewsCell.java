// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class JNewsCell extends d
{
    protected static Font try;
    protected Color char;
    protected Color goto;
    protected static Image case;
    private Font byte;
    private Vector new;
    private static int else;
    
    public void setValue(final Object o) {
        final String string = o.toString();
        this.new = new Vector();
        final StringTokenizer stringTokenizer = new StringTokenizer(string, ";");
        while (stringTokenizer.hasMoreTokens()) {
            this.new.addElement(stringTokenizer.nextToken());
        }
    }
    
    public void setFeature(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.char = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.goto = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
    }
    
    public void draw(final Rectangle rectangle, final Graphics graphics, final e e) {
        if (super.for) {
            graphics.setFont(this.byte);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n = rectangle.x + 2;
            final int n2 = rectangle.height + fontMetrics.getHeight() - 6;
            final int n3 = d.int % 12;
            if (n3 == 0) {
                JNewsCell.else = d.int / 12;
            }
            final int n4 = (int)(n3 / 12.0 * n2) + rectangle.y;
            if (super.if) {
                graphics.setColor(Color.blue);
            }
            else {
                graphics.setColor(this.char);
            }
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            int n5 = n;
            final String s = this.new.elementAt(JNewsCell.else % this.new.size());
            if (s.charAt(0) == 'N' && JNewsCell.case != null) {
                graphics.drawImage(JNewsCell.case, n + 2, n4 - 10, e);
                n5 += JNewsCell.case.getWidth(e) + 4;
            }
            super.do = s.substring(1, s.lastIndexOf(94));
            super.a = s.substring(s.lastIndexOf(94) + 1);
            final int stringWidth = fontMetrics.stringWidth(super.do);
            if (super.if) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(this.goto);
            }
            if (super.a != null) {
                if (super.if) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.blue);
                }
                graphics.drawLine(n5, n4 + 1, n5 + stringWidth, n4 + 1);
            }
            graphics.drawString(super.do, (n5 > n) ? n5 : n, n4);
            super.for = false;
        }
    }
    
    public JNewsCell() {
        this.char = Color.white;
        this.goto = Color.black;
        this.byte = JNewsCell.try;
    }
    
    static {
        JNewsCell.try = new Font("Dialog", 0, 12);
    }
}
