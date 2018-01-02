// 
// Decompiled by Procyon v0.5.30
// 

package IntegroGeneral;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class ImageCanvas extends Canvas
{
    public Image Image;
    protected int X;
    protected int Y;
    private boolean Refresh;
    
    public Image getImage() {
        return this.Image;
    }
    
    public void getNextImage() {
    }
    
    protected boolean getRefresh() {
        return this.Refresh;
    }
    
    public int getX() {
        return this.X;
    }
    
    public int getY() {
        return this.Y;
    }
    
    public void paint(final Graphics g) {
        try {
            if (this.Image != null) {
                g.drawImage(this.Image, this.getX(), this.getY(), null);
                g.setColor(this.getForeground());
                g.drawLine(0, this.getSize().height - 10, 10, this.getSize().height - 10);
                g.drawLine(0, this.getSize().height - 10, 10, this.getSize().height);
                g.drawLine(0, this.getSize().height - 10, 0, this.getSize().height);
                g.drawLine(0, this.getSize().height - 1, 10, this.getSize().height - 1);
                g.drawLine(0, this.getSize().height, 10, this.getSize().height - 10);
                g.drawLine(10, this.getSize().height - 10, 10, this.getSize().height);
            }
        }
        catch (Exception e) {
            System.out.println("there is an exception why@@!!!" + e);
        }
    }
    
    public void setImage(final Image newValue) {
        this.Image = newValue;
    }
    
    protected void setRefresh(final boolean newValue) {
        this.Refresh = newValue;
    }
    
    public void setX(final int newValue) {
        this.X = newValue;
    }
    
    public void setY(final int newValue) {
        this.Y = newValue;
    }
    
    public void update(final Graphics g) {
        if (this.getRefresh()) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        }
        this.paint(g);
    }
}
