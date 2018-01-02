// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Canvas;

public class R extends Canvas
{
    private String m;
    private URL b;
    private boolean j;
    private int h;
    private int i;
    public boolean l;
    
    public void resize(final int i, final int n) {
        super.resize(this.i = i, n);
    }
    
    public Dimension minimumSize() {
        if (this.j) {
            return new Dimension(this.i, this.a(null, this.i));
        }
        return super.minimumSize();
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void a(final int h) {
        this.h = h;
        this.repaint();
    }
    
    public void a(final boolean j) {
        this.j = j;
    }
    
    public void a(final String m) {
        this.m = m;
        if (this.j) {
            this.resize(this.minimumSize());
        }
        this.repaint();
    }
    
    public void a(final URL b) {
        if (b != null) {
            this.setForeground(Color.blue);
        }
        try {
            if (b == null) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
            else {
                this.setCursor(Cursor.getPredefinedCursor(12));
            }
        }
        catch (Throwable t) {}
        this.b = b;
        this.repaint();
    }
    
    private final int a(final Graphics graphics, final int n) {
        if (this.l) {
            graphics.setColor(Color.white);
            graphics.drawImage(aX.m, this.getSize().width - 34, this.getSize().height / 2 - 16, null);
        }
        if (this.m != null && this.m.length() == 0) {
            return 10;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (fontMetrics == null) {
            return 10;
        }
        final int height = fontMetrics.getHeight();
        int i = 0;
        int n2 = 0;
        while (i != -1) {
            n2 += height;
            int n3 = 0;
            char char1;
            while (i < this.m.length() && ((char1 = this.m.charAt(i)) == ' ' || char1 == '\n')) {
                if (char1 == '\n') {
                    ++n3;
                }
                if (++i >= this.m.length()) {
                    break;
                }
            }
            if (n3 > 1) {
                n2 += height * (n3 - 1);
            }
            int j = i;
            int n4 = -1;
            String s = null;
            while (j >= i) {
                final int index = this.m.indexOf(10, j);
                j = this.m.indexOf(32, j);
                if (index != -1 && (index < j || j == -1)) {
                    j = index;
                }
                String s2;
                if (j == -1) {
                    s2 = this.m.substring(i);
                }
                else {
                    s2 = this.m.substring(i, j);
                }
                if (fontMetrics.stringWidth(s2) >= n) {
                    break;
                }
                s = s2;
                if ((n4 = j) == -1) {
                    break;
                }
                if (j == index) {
                    ++j;
                    break;
                }
                ++j;
            }
            if (s == null) {
                int n5 = 0;
                int k = i;
                while (k < this.m.length()) {
                    final int charWidth = fontMetrics.charWidth(this.m.charAt(k));
                    if (n5 + charWidth >= n) {
                        if (k == i) {
                            return 10;
                        }
                        break;
                    }
                    else {
                        n5 += charWidth;
                        ++k;
                    }
                }
                if (graphics != null) {
                    this.a(graphics, this.m.substring(i, k), n2, n, fontMetrics);
                }
                i = k;
            }
            else {
                if (graphics != null) {
                    this.a(graphics, s, n2, n, fontMetrics);
                }
                i = n4;
            }
        }
        return n2 + fontMetrics.getMaxDescent();
    }
    
    private final void a(final Graphics graphics, final String s, final int n, final int n2, final FontMetrics fontMetrics) {
        if (this.l) {
            graphics.setColor(Color.white);
            graphics.drawImage(aX.m, this.getSize().width - 34, this.getSize().height / 2 - 16, null);
        }
        final int stringWidth = fontMetrics.stringWidth(s);
        int n3 = 0;
        switch (this.h) {
            case 2: {
                n3 = n2 - stringWidth;
                break;
            }
            case 1: {
                n3 = (n2 - stringWidth) / 2;
                break;
            }
            default: {
                n3 = 0;
                break;
            }
        }
        if (this.b != null) {
            graphics.setColor(this.getForeground());
            graphics.drawLine(n3, n + 1, n3 + stringWidth, n + 1);
            graphics.setColor(this.getBackground());
            graphics.drawString(this.m, n3 + 1, n);
            graphics.drawString(this.m, n3 - 1, n);
            graphics.drawString(this.m, n3, n + 1);
        }
        graphics.setColor(this.getForeground());
        graphics.drawString(s, n3, n);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && this.m != null && this.m.length() > 0) {
            try {
                this.postEvent(new Event(this, 1001, this.b));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics, this.size().width);
    }
    
    public R() {
        this("", null);
    }
    
    public R(final String s) {
        this(s, null);
    }
    
    public R(final String m, final URL url) {
        this.l = false;
        this.j = true;
        this.m = m;
        this.a(url);
        this.h = 0;
        this.resize(250, 20);
    }
}
