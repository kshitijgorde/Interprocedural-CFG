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

public final class aw extends Canvas
{
    private String a;
    
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
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && this.a != null && this.a.length() > 0) {
            try {
                this.postEvent(new Event(this, 1001, null));
            }
            catch (Exception ex) {}
        }
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
                final aw aw = this;
                final Graphics graphics2 = graphics;
                final String s2 = s;
                final FontMetrics fontMetrics2 = fontMetrics;
                final String s3 = s2;
                graphics = graphics2;
                this = aw;
                fontMetrics2.stringWidth(s3);
                graphics.setColor(this.getForeground());
                graphics.drawString(s3, 0, height);
            }
            fontMetrics.getMaxDescent();
        }
    }
    
    public aw() {
        this("");
    }
    
    public aw(final String s, final byte b) {
        this(s);
    }
    
    private aw(String a) {
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
