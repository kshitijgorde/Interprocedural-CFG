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
    Color \u00c0;
    boolean \u00c1;
    boolean \u00c2;
    Image \u00c3;
    Image \u00c4;
    Image \u00c5;
    Image \u00c6;
    
    public plaatjeKnop(final Color color, final Image \u00e3, final Image \u00e4, final Image \u00e5, final Image \u00e6) {
        this.\u00c1 = false;
        this.\u00c2 = false;
        this.\u00c0 = color;
        this.\u00c3 = \u00e3;
        this.setBackground(color);
        this.\u00c4 = \u00e4;
        this.\u00c5 = \u00e5;
        this.\u00c6 = \u00e6;
    }
    
    public void disable() {
        super.disable();
        this.\u00c2 = true;
        this.repaint();
    }
    
    public void enable() {
        super.enable();
        this.\u00c2 = false;
        this.repaint();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.\u00c1 = true;
        this.repaint();
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.\u00c1 = false;
        this.repaint();
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return super.mouseMove(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.postEvent(new Event(this, 1001, ""));
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.\u00c3 != null) {
            graphics.drawImage(this.\u00c3, 0, 0, width, height, this);
        }
        if (this.\u00c2) {
            graphics.drawImage(this.\u00c6, 0, 0, width, height, this);
        }
        else if (!this.\u00c1) {
            graphics.drawImage(this.\u00c4, 0, 0, width, height, this);
        }
        else {
            graphics.drawImage(this.\u00c5, 0, 0, width, height, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
