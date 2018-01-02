import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class FancyLabel extends Canvas
{
    protected Panel panel;
    private Image image;
    private String text;
    private boolean mousePushed;
    private MediaTracker tracker;
    private int width;
    private int height;
    
    FancyLabel(final Panel panel, final Image image, final int width, final int height) {
        this.mousePushed = false;
        this.panel = panel;
        this.image = image;
        this.text = this.text;
        (this.tracker = new MediaTracker(this)).addImage(this.image, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.setBackground(Color.lightGray);
        this.width = width;
        this.height = height;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        final Dimension dim = this.size();
        final int xSiz = this.image.getWidth(this);
        final int ySiz = this.image.getHeight(this);
        final int xPos = (dim.width - xSiz) / 2;
        final int yPos = (dim.height - ySiz) / 2;
        g.setColor(Color.lightGray);
        g.fillRect(xPos, yPos, xSiz + 2, ySiz + 2);
        g.setColor(Color.white);
        g.drawLine(0, 0, dim.width - 1, 0);
        g.drawLine(0, 0, 0, dim.height - 1);
        g.drawLine(1, 1, dim.width - 2, 1);
        g.drawLine(1, 1, 1, dim.height - 2);
        g.setColor(Color.black);
        g.drawLine(0, dim.height - 1, dim.width - 1, dim.height - 1);
        g.drawLine(dim.width - 1, dim.height - 1, dim.width - 1, 0);
        g.setColor(Color.gray);
        g.drawLine(1, dim.height - 2, dim.width - 2, dim.height - 2);
        g.drawLine(dim.width - 2, dim.height - 2, dim.width - 2, 1);
        g.clipRect(2, 2, dim.width - 4, dim.height - 4);
        g.drawImage(this.image, xPos, yPos, this);
    }
}
