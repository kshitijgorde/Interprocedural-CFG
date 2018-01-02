// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class c extends d
{
    protected static Font c;
    protected Color g;
    protected Color h;
    protected static Image e;
    protected static Image[] void;
    private char i;
    private NumberFormat f;
    protected int b;
    private Font d;
    
    public c(final String s, final String a) {
        this.g = Color.white;
        this.h = Color.black;
        this.i = 'O';
        this.d = JGrid.c.c;
        super.do = ((s == null) ? "" : s);
        super.a = a;
    }
    
    public void setFeature(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.i = stringTokenizer.nextToken().charAt(0);
        this.g = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.h = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.b = Integer.parseInt(stringTokenizer.nextToken());
        try {
            if (this.i == 'S') {
                (this.f = NumberFormat.getInstance()).setMinimumFractionDigits(2);
                return;
            }
            if (this.i == 'C') {
                this.f = NumberFormat.getCurrencyInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    public void draw(final Rectangle rectangle, final Graphics graphics, final e e) {
        if (super.for) {
            graphics.setFont(this.d);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n = rectangle.x + 2;
            final int n2 = rectangle.y + (rectangle.height + fontMetrics.getHeight()) / 2 - fontMetrics.getDescent();
            if (super.if) {
                graphics.setColor(Color.blue);
            }
            else {
                graphics.setColor(this.g);
            }
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            int n3 = n + rectangle.width - 4;
            int n4 = n;
            if (super.if) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(this.h);
            }
            String s = super.do;
            if (this.i == 'S') {
                final float floatValue = new Float(super.do);
                final int n5 = n2 - fontMetrics.getHeight() + fontMetrics.getDescent();
                if (floatValue > 0.0f) {
                    if (JGrid.c.void[0] != null && rectangle.width - 14 > 0) {
                        graphics.drawImage(JGrid.c.void[0], n + rectangle.width - 14, n5, e);
                        graphics.setColor(Color.green);
                        n3 -= JGrid.c.void[0].getWidth(e);
                    }
                }
                else if (floatValue < 0.0f) {
                    if (JGrid.c.void[1] != null && rectangle.width - 14 > 0) {
                        graphics.drawImage(JGrid.c.void[1], n + rectangle.width - 14, n5, e);
                        graphics.setColor(Color.red);
                        n3 -= JGrid.c.void[1].getWidth(e);
                    }
                }
                else {
                    graphics.setColor(Color.black);
                }
                s = this.f.format(new Double(super.do));
            }
            else if (this.i == 'C') {
                s = this.f.format(new Double(super.do));
            }
            final int stringWidth = fontMetrics.stringWidth(s);
            switch (this.b) {
                case 0: {
                    n4 = (n3 - n - stringWidth) / 2 + n;
                    break;
                }
                case 2: {
                    n4 = n3 - stringWidth;
                    break;
                }
            }
            final int n6 = (n4 > n) ? n4 : n;
            if (super.a != null) {
                if (super.if) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.blue);
                }
                graphics.drawLine(n6, n2 + 1, n6 + stringWidth, n2 + 1);
            }
            graphics.drawString(s, n6, n2);
            super.for = false;
        }
    }
    
    static {
        JGrid.c.c = new Font("Dialog", 0, 12);
        JGrid.c.void = new Image[2];
    }
}
