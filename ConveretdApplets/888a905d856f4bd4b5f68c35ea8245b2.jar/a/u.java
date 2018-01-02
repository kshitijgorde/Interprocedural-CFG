// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Canvas;

public final class u extends Canvas
{
    private String q;
    private URL q;
    boolean q;
    private int q;
    private int w;
    public boolean w;
    
    public final void resize(final int w, final int n) {
        super.resize(this.w = w, n);
    }
    
    public final Dimension minimumSize() {
        if (this.q) {
            return new Dimension(this.w, this.q(null, this.w));
        }
        return super.minimumSize();
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void q(final int n) {
        this.q = 1;
        this.repaint();
    }
    
    public final void q(final String q) {
        this.q = q;
        if (this.q) {
            this.resize(this.minimumSize());
        }
        this.repaint();
    }
    
    public final void q(final URL q) {
        if (q != null) {
            this.setForeground(Color.blue);
        }
        try {
            if (q == null) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
            else {
                this.setCursor(Cursor.getPredefinedCursor(12));
            }
        }
        catch (Throwable t) {}
        this.q = q;
        this.repaint();
    }
    
    private final int q(final Graphics graphics, final int n) {
        if (this.w) {
            graphics.setColor(Color.white);
            graphics.drawImage(m.q(0), this.getSize().width - 34, this.getSize().height / 2 - 16, null);
        }
        if (this.q != null && this.q.length() == 0) {
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
            while (i < this.q.length() && ((char1 = this.q.charAt(i)) == ' ' || char1 == '\n')) {
                if (char1 == '\n') {
                    ++n3;
                }
                if (++i >= this.q.length()) {
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
                final int index = this.q.indexOf(10, j);
                j = this.q.indexOf(32, j);
                if (index != -1 && (index < j || j == -1)) {
                    j = index;
                }
                String s2;
                if (j == -1) {
                    s2 = this.q.substring(i);
                }
                else {
                    s2 = this.q.substring(i, j);
                }
                if (fontMetrics.stringWidth(s2) >= n) {
                    break;
                }
                s = s2;
                if ((n4 = j) == -1) {
                    break;
                }
                if (j == index) {
                    break;
                }
                ++j;
            }
            if (s == null) {
                int n5 = 0;
                int k = i;
                while (k < this.q.length()) {
                    final int charWidth = fontMetrics.charWidth(this.q.charAt(k));
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
                    this.q(graphics, this.q.substring(i, k), n2, n, fontMetrics);
                }
                i = k;
            }
            else {
                if (graphics != null) {
                    this.q(graphics, s, n2, n, fontMetrics);
                }
                i = n4;
            }
        }
        return n2 + fontMetrics.getMaxDescent();
    }
    
    private final void q(final Graphics graphics, final String s, final int n, int n2, final FontMetrics fontMetrics) {
        if (this.w) {
            graphics.setColor(Color.white);
            graphics.drawImage(m.q(0), this.getSize().width - 34, this.getSize().height / 2 - 16, null);
        }
        final int stringWidth = fontMetrics.stringWidth(s);
        switch (this.q) {
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
        if (this.q != null) {
            graphics.setColor(this.getForeground());
            graphics.drawLine(n2, n + 1, n2 + stringWidth, n + 1);
            graphics.setColor(this.getBackground());
            graphics.drawString(this.q, n2 + 1, n);
            graphics.drawString(this.q, n2 - 1, n);
            graphics.drawString(this.q, n2, n + 1);
        }
        graphics.setColor(this.getForeground());
        graphics.drawString(s, n2, n);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && this.q != null && this.q.length() > 0) {
            try {
                this.postEvent(new Event(this, 1001, this.q));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        this.q(graphics, this.size().width);
    }
    
    public u() {
        this("", null);
    }
    
    public u(final String s) {
        this(s, null);
    }
    
    public u(final String q, final URL url) {
        this.w = false;
        this.q = true;
        this.q = q;
        this.q(url);
        this.q = 0;
        this.resize(250, 20);
    }
}
