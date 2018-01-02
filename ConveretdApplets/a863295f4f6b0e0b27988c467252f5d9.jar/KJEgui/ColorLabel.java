// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Font;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Panel;

public class ColorLabel extends Panel
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
    public static Color AppBack;
    public Color textColor;
    Rectangle core;
    boolean enabled;
    
    static {
        ColorLabel.plainColor = Color.black;
        ColorLabel.overColor = Color.red;
        ColorLabel.downColor = Color.blue;
        ColorLabel.AppBack = Color.white;
    }
    
    public ColorLabel() {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
        this.textColor = ColorLabel.plainColor;
    }
    
    public ColorLabel(final String text) {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
        this.text = text;
        this.textColor = ColorLabel.plainColor;
    }
    
    public ColorLabel(final String text, final int alignment) {
        this.alignment = 2;
        this.textY = 15;
        this.enabled = true;
        this.text = text;
        this.alignment = alignment;
        this.textColor = ColorLabel.plainColor;
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
        if (this.textColor != ColorLabel.downColor) {
            this.textColor = ColorLabel.downColor;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.textColor != ColorLabel.plainColor) {
            this.textColor = ColorLabel.plainColor;
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
        if (this.textColor != ColorLabel.overColor) {
            this.deliverEvent(new Event(this, 1001, null));
            this.textColor = ColorLabel.overColor;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return true;
        }
        if (this.textColor != ColorLabel.plainColor) {
            this.textColor = ColorLabel.plainColor;
            this.repaint();
            this.deliverEvent(new Event(this, 1001, null));
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(ColorLabel.AppBack);
        final int width = this.size().width;
        final int n = width / 25 * 24;
        graphics.fillPolygon(new Polygon(new int[] { n, width, width, n }, new int[] { 0, 0, this.size().height, 0 }, 4));
        graphics.setColor(this.textColor);
        final int width2 = this.size().width;
        if (this.text == null) {
            for (int i = 2; i < width2 - 2; i += 3) {
                graphics.drawLine(i, this.textY, i, this.textY);
            }
            return;
        }
        final Font font = this.getFont();
        graphics.setFont(font);
        int n2;
        if (this.alignment == 0) {
            n2 = 2;
        }
        else {
            final int stringWidth = this.getFontMetrics(font).stringWidth(this.text);
            if (this.alignment == 1) {
                n2 = width2 - stringWidth - 2;
            }
            else {
                n2 = (width2 - stringWidth) / 2;
            }
        }
        graphics.drawString(this.text, n2, this.textY);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        this.core = new Rectangle(4, 4, n3 - 8, n4 - 8);
        super.reshape(n, n2, n3, n4);
    }
    
    public static void setAttributes(final Color plainColor, final Color overColor, final Color downColor, final Color appBack) {
        ColorLabel.plainColor = plainColor;
        ColorLabel.overColor = overColor;
        ColorLabel.downColor = downColor;
        ColorLabel.AppBack = appBack;
    }
}
