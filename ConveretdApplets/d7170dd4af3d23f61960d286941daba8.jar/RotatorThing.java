import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class RotatorThing extends Canvas
{
    Image rotator;
    Image rotBuffer;
    int width;
    int height;
    int diameter;
    Dimension dim;
    boolean init;
    int xm;
    int ym;
    int xc;
    int yc;
    Color backcolor;
    Color forecolor;
    String[] labels;
    double angle;
    Font labelFont;
    FontMetrics fm;
    int fmHeight;
    double[] xoff;
    double[] yoff;
    int oldWhich;
    int numItems;
    AniS parent;
    boolean mouseActive;
    
    public RotatorThing(final AniS parent, final String[] labels, final int numItems, final Color backcolor, final Color forecolor) {
        this.width = 80;
        this.height = 40;
        this.diameter = 20;
        this.oldWhich = 0;
        this.numItems = 0;
        this.mouseActive = false;
        this.init = true;
        this.dim = new Dimension(this.width, this.height);
        this.backcolor = backcolor;
        this.forecolor = forecolor;
        this.xc = this.width / 2;
        this.yc = this.height / 2;
        this.labels = null;
        this.angle = 0.0;
        this.parent = parent;
        this.labelFont = new Font("sans", 0, 10);
        this.fm = this.getFontMetrics(this.labelFont);
        this.fmHeight = this.fm.getMaxAscent() + this.fm.getMaxDescent();
        this.numItems = numItems;
        this.labels = labels;
        if (this.numItems <= 0) {
            this.numItems = this.labels.length;
        }
        this.xoff = new double[] { -0.5, 0.0, 0.05, 0.0, -0.5, -1.0, -1.1, -1.0 };
        this.yoff = new double[] { -0.1, 0.0, 0.35, 0.6, 0.65, 0.6, 0.35, 0.0 };
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.dim;
    }
    
    public Dimension getMaximumSize() {
        return this.dim;
    }
    
    public Dimension getMinimumSize() {
        return this.dim;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 506 || event.id == 501) {
            this.angle = Math.atan2(event.y - this.yc, event.x - this.xc) + 1.571;
            if (this.angle < 0.0) {
                this.angle += 6.28318;
            }
            int oldWhich = (int)Math.round(this.angle / 6.28318 * this.numItems);
            if (oldWhich == this.numItems) {
                oldWhich = 0;
            }
            if (oldWhich != this.oldWhich) {
                this.parent.setCurrentFrame(true, oldWhich);
                this.oldWhich = oldWhich;
            }
            else {
                this.repaint();
            }
            return this.mouseActive = true;
        }
        if (event.id == 502) {
            this.mouseActive = false;
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void setCurrentFrame(final int n) {
        if (this.mouseActive) {
            return;
        }
        if (this.numItems > 0) {
            this.angle = 6.28318 * n / this.numItems;
        }
        this.repaint();
    }
    
    public void setLabels(final String[] labels) {
        this.labels = labels;
        this.init = true;
        this.repaint();
    }
    
    public void setNumFrames(final int numItems) {
        this.numItems = numItems;
        if (this.numItems <= 0) {
            this.numItems = this.labels.length;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.init) {
            this.rotator = this.createImage(this.width, this.height);
            this.rotBuffer = this.createImage(this.width, this.height);
            final Graphics graphics2 = this.rotator.getGraphics();
            graphics2.setColor(this.backcolor);
            graphics2.fillOval(this.xc - this.diameter / 2, this.yc - this.diameter / 2, this.diameter, this.diameter);
            this.init = false;
            graphics2.setFont(this.labelFont);
            final double n = this.diameter / 2;
            if (this.labels == null) {
                return;
            }
            for (int i = 0; i < this.labels.length; ++i) {
                final double n2 = i * 360.0 * 0.0174533 / this.labels.length;
                final int n3 = this.xc + (int)Math.round(n * Math.sin(n2));
                final int n4 = this.yc - (int)Math.round(n * Math.cos(n2));
                final int stringWidth = this.fm.stringWidth(this.labels[i]);
                final int n5 = (int)Math.round(i * 8.0 / this.labels.length);
                graphics2.drawString(this.labels[i], n3 + (int)Math.round(stringWidth * this.xoff[n5]), n4 + (int)Math.round(this.fmHeight * this.yoff[n5]));
            }
            graphics2.dispose();
        }
        final Graphics graphics3 = this.rotBuffer.getGraphics();
        graphics3.drawImage(this.rotator, 0, 0, this);
        graphics3.setColor(this.forecolor);
        graphics3.drawLine(this.xc, this.yc, this.xc + (int)Math.round(this.diameter * Math.sin(this.angle)), this.yc - (int)Math.round(this.diameter * Math.cos(this.angle)));
        graphics.drawImage(this.rotBuffer, 0, 0, this);
        graphics3.dispose();
    }
}
