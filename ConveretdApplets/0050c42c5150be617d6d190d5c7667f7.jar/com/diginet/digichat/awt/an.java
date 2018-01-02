// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Canvas;

public class an extends Canvas
{
    private String a;
    private URL b;
    private boolean c;
    private int d;
    private int e;
    
    public final void resize(final int e, final int n) {
        super.resize(this.e = e, n);
    }
    
    public final Dimension minimumSize() {
        if (this.c) {
            return new Dimension(this.e, this.a(null, this.e));
        }
        return super.minimumSize();
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void a(final int d) {
        this.d = d;
        this.repaint();
    }
    
    public final void a(final boolean c) {
        this.c = c;
    }
    
    public final void a(final String a) {
        this.a = a;
        if (this.c) {
            this.resize(this.minimumSize());
        }
        this.repaint();
    }
    
    public final void a(final URL b) {
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
        if (this.a != null && this.a.length() == 0) {
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
    
    private final void a(final Graphics graphics, final String s, final int n, final int n2, final FontMetrics fontMetrics) {
        final int stringWidth = fontMetrics.stringWidth(s);
        int n3 = 0;
        switch (this.d) {
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
            graphics.drawString(this.a, n3 + 1, n);
            graphics.drawString(this.a, n3 - 1, n);
            graphics.drawString(this.a, n3, n + 1);
        }
        graphics.setColor(this.getForeground());
        graphics.drawString(s, n3, n);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && this.a != null && this.a.length() > 0) {
            try {
                this.postEvent(new Event(this, 1001, this.b));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        this.a(graphics, this.size().width);
    }
    
    public an() {
        this("", null);
    }
    
    public an(final String s) {
        this(s, null);
    }
    
    public an(final String a, final URL url) {
        this.c = true;
        this.a = a;
        this.a(url);
        this.d = 0;
        this.resize(250, 20);
    }
}