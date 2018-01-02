// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Canvas;

public final class A extends Canvas
{
    private String a;
    private URL a;
    public boolean a;
    private int a;
    private int b;
    
    public final void resize(final int b, final int n) {
        super.resize(this.b = b, n);
    }
    
    public final Dimension minimumSize() {
        if (this.a) {
            return new Dimension(this.b, this.a(null, this.b));
        }
        return super.minimumSize();
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void a() {
        this.a = 1;
        this.repaint();
    }
    
    public final void a(final String a) {
        this.a = a;
        if (this.a) {
            this.resize(this.minimumSize());
        }
        this.repaint();
    }
    
    public final void a(final URL a) {
        if (a != null) {
            this.setForeground(Color.blue);
        }
        try {
            if (a == null) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
            else {
                this.setCursor(Cursor.getPredefinedCursor(12));
            }
        }
        catch (Throwable t) {}
        this.a = a;
        this.repaint();
    }
    
    private int a(final Graphics graphics, final int n) {
        if (this.a != null && this.a.length() == 0) {
            return 10;
        }
        final FontMetrics fontMetrics;
        if ((fontMetrics = this.getFontMetrics(this.getFont())) == null) {
            return 10;
        }
        final int height = fontMetrics.getHeight();
        int i = 0;
        int n2 = 0;
        while (i != -1) {
            n2 += height;
            int n3 = 0;
            char char1;
            while (i < this.a.length() && ((char1 = this.a.charAt(i)) == ' ' || char1 == '\n')) {
                if (char1 == '\n') {
                    ++n3;
                }
                if (++i >= this.a.length()) {
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
                final int index = this.a.indexOf(10, j);
                j = this.a.indexOf(32, j);
                if (index != -1 && (index < j || j == -1)) {
                    j = index;
                }
                String s2;
                if (j == -1) {
                    s2 = this.a.substring(i);
                }
                else {
                    s2 = this.a.substring(i, j);
                }
                if (fontMetrics.stringWidth(s2) >= n) {
                    break;
                }
                s = s2;
                if ((n4 = j) == -1 || j == index) {
                    break;
                }
                ++j;
            }
            if (s == null) {
                int n5 = 0;
                int k = i;
                while (k < this.a.length()) {
                    final int charWidth = fontMetrics.charWidth(this.a.charAt(k));
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
                    this.a(graphics, this.a.substring(i, k), n2, n, fontMetrics);
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
    
    private void a(final Graphics graphics, final String s, final int n, int n2, final FontMetrics fontMetrics) {
        final int stringWidth = fontMetrics.stringWidth(s);
        switch (this.a) {
            case 2: {
                n2 -= stringWidth;
                break;
            }
            case 1: {
                n2 = (n2 - stringWidth) / 2;
                break;
            }
            default: {
                n2 = 0;
                break;
            }
        }
        if (this.a != null) {
            graphics.setColor(this.getForeground());
            graphics.drawLine(n2, n + 1, n2 + stringWidth, n + 1);
            graphics.setColor(this.getBackground());
            graphics.drawString(this.a, n2 + 1, n);
            graphics.drawString(this.a, n2 - 1, n);
            graphics.drawString(this.a, n2, n + 1);
        }
        graphics.setColor(this.getForeground());
        graphics.drawString(s, n2, n);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && this.a != null && this.a.length() > 0) {
            try {
                this.postEvent(new Event(this, 1001, this.a));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        this.a(graphics, this.size().width);
    }
    
    public A() {
        this("", null);
    }
    
    public A(final String s) {
        this(s, null);
    }
    
    public A(final String a, final URL url) {
        this.a = true;
        this.a = a;
        this.a(url);
        this.a = 0;
        this.resize(250, 20);
    }
}
