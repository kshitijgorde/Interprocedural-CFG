// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Canvas;

public class gu extends Canvas
{
    int a;
    int b;
    int c;
    String d;
    String e;
    Rectangle f;
    String g;
    boolean h;
    Color i;
    
    public gu(final String g) {
        this.a = 330;
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = null;
        this.g = g;
        this.setSize(this.a, this.b);
        this.addMouseListener(new xv());
    }
    
    public Dimension getSize() {
        return new Dimension(this.a, this.b);
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public void b(final int b) {
        this.b = b;
    }
    
    public final void a(final String d, final boolean b) {
        this.d = d;
        if (b) {
            this.a();
        }
    }
    
    public final void b(final String e, final boolean b) {
        this.e = e;
        if (e != null && e.toLowerCase().indexOf("null") >= 0) {}
        if (b) {
            this.a();
        }
    }
    
    private final void a() {
        try {
            this.paint(this.getGraphics());
            Toolkit.getDefaultToolkit().sync();
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.c * (this.a - 1) / 100;
        if (this.d != null || this.e != null) {
            if (this.e != null && this.d == null) {
                this.d = "X";
            }
            final int height = graphics.getFontMetrics().getHeight();
            graphics.getFontMetrics().stringWidth(this.d);
            final Dimension size = this.getSize();
            if (this.d != null && this.d.toLowerCase().indexOf("null") >= 0) {
                this.d = null;
            }
            try {
                if (this.b > 0) {
                    this.b = size.height - 1;
                    final int min = Math.min(100, size.width - 12);
                    final int n2 = (size.width - min) / 2;
                    final int n3 = size.height - height - 10;
                    final int n4 = height + 4;
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, size.width, n3 - 4);
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(0, n3 - 3, size.width, size.height - n3 - 3);
                    graphics.setColor(Color.black);
                    graphics.drawLine(0, n3 - 4, size.width - 1, n3 - 4);
                    graphics.drawRect(0, 0, size.width - 1, size.height - 1);
                    if (this.e != null && this.e.toLowerCase().indexOf("null") >= 0) {
                        this.e = null;
                    }
                    if (this.e != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(this.e, "\n");
                        final int countTokens = stringTokenizer.countTokens();
                        final int height2 = graphics.getFontMetrics().getHeight();
                        int n5 = (this.b - countTokens * height2) / 2 - height2 / countTokens - height;
                        for (int i = 0; i < countTokens; ++i) {
                            final String nextToken = stringTokenizer.nextToken();
                            n5 += height;
                            graphics.drawString(nextToken, (size.width - graphics.getFontMetrics().stringWidth(nextToken)) / 2, n5);
                        }
                        this.f = new Rectangle(n2, n3, min, n4);
                        if (this.i == null) {
                            this.i = this.getBackground();
                        }
                        this.a(graphics, this.i, this.h);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void a(final Graphics graphics, final Color color, final boolean h) {
        try {
            this.h = h;
            this.i = color;
            final int height = graphics.getFontMetrics().getHeight();
            int x = this.f.x;
            int y = this.f.y;
            final int width = this.f.width;
            final int height2 = this.f.height;
            graphics.setColor(color);
            graphics.fillRect(x + 1, y + 1, width - 1, height2 - 1);
            if (h) {
                graphics.setColor(color.darker().darker());
            }
            else {
                graphics.setColor(color.brighter());
            }
            graphics.drawLine(x, y, x + width, y);
            graphics.drawLine(x, y, x, y + height2);
            if (h) {
                graphics.setColor(color.brighter());
            }
            else {
                graphics.setColor(color.darker().darker());
            }
            graphics.drawLine(x + width, y + 1, x + width, y + height2);
            graphics.drawLine(x + 1, y + height2, x + width, y + height2);
            graphics.setColor(Color.black);
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.g);
            if (h) {
                ++x;
                ++y;
            }
            graphics.drawString(this.g, x + (width - stringWidth) / 2, y + height - height / 8);
        }
        catch (Exception ex) {}
    }
    
    class xv extends MouseAdapter
    {
        public void mouseReleased(final MouseEvent mouseEvent) {
            gu.this.a(gu.this.getGraphics(), gu.this.i, false);
            Toolkit.getDefaultToolkit().sync();
            final Point point = mouseEvent.getPoint();
            if (gu.this.f.inside(point.x, point.y)) {
                System.exit(0);
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            final Point point = mouseEvent.getPoint();
            if (gu.this.f.inside(point.x, point.y)) {
                gu.this.a(gu.this.getGraphics(), gu.this.i, true);
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }
}
