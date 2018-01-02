// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.awt.Canvas;

public class z extends Canvas
{
    private String a;
    private URL b;
    private boolean c;
    private int d;
    private int e;
    
    public final void resize(final int e, final int n) {
        super.resize(this.e = e, n);
    }
    
    public final void setFont(final Font font) {
        super.setFont(font);
        this.resize(this.minimumSize());
    }
    
    public final Dimension minimumSize() {
        Dimension minimumSize = null;
        if (this.getFont() != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            if (fontMetrics != null) {
                minimumSize = new Dimension(fontMetrics.stringWidth(this.a), fontMetrics.getHeight() + fontMetrics.getMaxAscent());
            }
        }
        else {
            minimumSize = super.minimumSize();
        }
        return minimumSize;
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void a(final int d) {
        this.d = d;
        this.repaint();
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
    
    private final int a(final Graphics graphics) {
        if (this.a != null && this.a.length() == 0) {
            return 10;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (fontMetrics == null) {
            return 10;
        }
        final int height = fontMetrics.getHeight();
        final int n = 0;
        final int n2 = 0 + height;
        final int n3 = n;
        String s = null;
        String s2;
        if (n3 == -1) {
            s2 = this.a.substring(n);
        }
        else {
            s2 = this.a.substring(n);
        }
        if (fontMetrics.stringWidth(s2) != -1) {
            s = s2;
        }
        if (graphics != null) {
            this.a(graphics, s, n2, this.e, fontMetrics);
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
    
    public final void paint(final Graphics graphics) {
        this.a(graphics);
    }
    
    public z() {
        this("", null);
    }
    
    public z(final String s) {
        this(s, null);
    }
    
    public z(final String a, final URL url) {
        this.c = true;
        this.a = a;
        this.a(url);
        this.d = 0;
        this.resize(250, 20);
    }
}
