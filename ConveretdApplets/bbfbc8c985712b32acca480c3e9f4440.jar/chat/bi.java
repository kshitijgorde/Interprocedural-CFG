// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Canvas;

public final class bi extends Canvas
{
    private String a;
    private boolean a;
    
    public final void resize(final int n, final int n2) {
        super.resize(n, n2);
    }
    
    public final void setFont(final Font font) {
        super.setFont(font);
        this.resize(this.minimumSize());
    }
    
    public final Dimension minimumSize() {
        Dimension minimumSize = null;
        if (this.getFont() != null) {
            final FontMetrics fontMetrics;
            if ((fontMetrics = this.getFontMetrics(this.getFont())) != null) {
                minimumSize = new Dimension(fontMetrics.stringWidth(this.a), fontMetrics.getHeight() + fontMetrics.getMaxDescent());
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
    
    public final void a(final String a) {
        this.a = a;
        if (this.a) {
            this.resize(this.minimumSize());
        }
        this.repaint();
    }
    
    public final String a() {
        if (this.a != null && this.a.length() > 0) {
            return this.a;
        }
        return "";
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
    
    public final void paint(Graphics graphics) {
        final FontMetrics fontMetrics;
        if (((this = this).a == null || this.a.length() != 0) && (fontMetrics = this.getFontMetrics(this.getFont())) != null) {
            final int height = fontMetrics.getHeight();
            String s = null;
            final String substring = this.a.substring(0);
            if (fontMetrics.stringWidth(substring) != -1) {
                s = substring;
            }
            if (graphics != null) {
                final bi bi = this;
                final Graphics graphics2 = graphics;
                final String s2 = s;
                final FontMetrics fontMetrics2 = fontMetrics;
                final String s3 = s2;
                graphics = graphics2;
                this = bi;
                fontMetrics2.stringWidth(s3);
                graphics.setColor(this.getForeground());
                graphics.drawString(s3, 0, height);
            }
            fontMetrics.getMaxDescent();
        }
    }
    
    public bi() {
        this("");
    }
    
    public bi(final String s, final byte b) {
        this(s);
    }
    
    private bi(String a) {
        this.a = true;
        this.a = a;
        a = (String)this;
        try {
            ((Component)a).setCursor(Cursor.getPredefinedCursor(0));
        }
        catch (Throwable t) {}
        ((Component)a).repaint();
        this.resize(250, 20);
    }
}
