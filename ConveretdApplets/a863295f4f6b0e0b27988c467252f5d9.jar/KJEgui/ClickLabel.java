// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Panel;

public class ClickLabel extends Panel
{
    public static final int FLUSH_LEFT = 0;
    public static final int FLUSH_RIGHT = 1;
    public static final int CENTER = 2;
    public int alignment;
    public int textY;
    public String text;
    public static Color plainColor;
    public static Color overColor;
    public static Color downColor;
    public Color textColor;
    Rectangle core;
    boolean enabled;
    
    static {
        ClickLabel.plainColor = Color.black;
        ClickLabel.overColor = Color.red;
        ClickLabel.downColor = Color.blue;
    }
    
    public ClickLabel() {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
    }
    
    public ClickLabel(final String text) {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
        this.text = text;
    }
    
    public ClickLabel(final String text, final int alignment) {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
        this.text = text;
        this.alignment = alignment;
    }
    
    public void disable() {
        this.enabled = false;
        super.disable();
    }
    
    public void enable() {
        this.enabled = true;
        super.enable();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return true;
        }
        if (this.textColor != ClickLabel.downColor) {
            this.textColor = ClickLabel.downColor;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.textColor != ClickLabel.plainColor) {
            this.textColor = ClickLabel.plainColor;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return true;
        }
        if (this.core != null && !this.core.inside(n, n2)) {
            return this.mouseExit(event, n, n2);
        }
        if (this.textColor != ClickLabel.overColor) {
            this.deliverEvent(new Event(this, 1001, null));
            this.textColor = ClickLabel.overColor;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return true;
        }
        if (this.textColor != ClickLabel.plainColor) {
            this.textColor = ClickLabel.plainColor;
            this.repaint();
            this.deliverEvent(new Event(this, 1001, null));
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.textColor);
        final int width = this.size().width;
        if (this.text == null) {
            for (int i = 2; i < width - 2; i += 3) {
                graphics.drawLine(i, this.textY, i, this.textY);
            }
            return;
        }
        final Font font = this.getFont();
        graphics.setFont(font);
        int n;
        if (this.alignment == 0) {
            n = 2;
        }
        else {
            final int stringWidth = this.getFontMetrics(font).stringWidth(this.text);
            if (this.alignment == 1) {
                n = width - stringWidth - 2;
            }
            else {
                n = (width - stringWidth) / 2;
            }
        }
        graphics.drawString(this.text, n, this.textY);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        this.core = new Rectangle(4, 4, n3 - 8, n4 - 8);
        super.reshape(n, n2, n3, n4);
    }
    
    public static void setAttributes(final Color plainColor, final Color overColor, final Color downColor) {
        ClickLabel.plainColor = plainColor;
        ClickLabel.overColor = overColor;
        ClickLabel.downColor = downColor;
    }
}
