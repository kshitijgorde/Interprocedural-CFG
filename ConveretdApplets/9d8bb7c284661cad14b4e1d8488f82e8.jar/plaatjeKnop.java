import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class plaatjeKnop extends Canvas
{
    Color \u015e;
    boolean \u015f;
    boolean \u0160;
    Image \u0161;
    Image \u0162;
    Image \u0163;
    Image \u0164;
    
    public plaatjeKnop(final Color color, final Image \u0161, final Image \u0163, final Image \u01632, final Image \u0165) {
        this.\u015f = false;
        this.\u0160 = false;
        this.\u015e = color;
        this.\u0161 = \u0161;
        this.setBackground(color);
        this.\u0162 = \u0163;
        this.\u0163 = \u01632;
        this.\u0164 = \u0165;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.\u0160) {
            this.postEvent(new Event(this, 1001, ""));
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.\u0160) {
            this.\u015f = true;
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.\u0160) {
            this.\u015f = false;
            this.repaint();
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.\u0161 != null) {
            graphics.drawImage(this.\u0161, 0, 0, width, height, this);
        }
        if (this.\u0160) {
            graphics.drawImage(this.\u0164, 0, 0, width, height, this);
            return;
        }
        if (!this.\u015f) {
            graphics.drawImage(this.\u0162, 0, 0, width, height, this);
            return;
        }
        graphics.drawImage(this.\u0163, 0, 0, width, height, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void disable() {
        this.\u0160 = true;
        this.repaint();
    }
    
    public void enable() {
        this.\u0160 = false;
        this.repaint();
    }
}
