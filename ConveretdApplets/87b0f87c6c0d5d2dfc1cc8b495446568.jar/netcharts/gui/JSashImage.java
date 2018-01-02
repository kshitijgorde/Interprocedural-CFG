// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import netcharts.graphics.NFRegion;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import javax.swing.JComponent;

public class JSashImage extends JComponent
{
    private Image a;
    private Image b;
    private Point c;
    private Color d;
    private Graphics e;
    private Image f;
    private Point g;
    private boolean h;
    
    public JSashImage() {
        this.g = null;
        this.h = false;
    }
    
    public JSashImage(final Image image) {
        this.g = null;
        this.h = false;
        this.setImage(image);
    }
    
    public void setImage(final Image a) {
        this.a = a;
    }
    
    public void setDragTarget(final Image b) {
        this.b = b;
        final Dimension size = this.size();
        this.f = this.createImage(size.width, size.height);
        (this.e = this.f.getGraphics()).drawImage(this.a, 0, 0, this);
        this.e.dispose();
    }
    
    public void hiliteSash(final int n, final int n2, final int n3, final int n4, final Color color) {
        (this.e = this.f.getGraphics()).setXORMode(this.getBackground());
        final Graphics graphics = this.getGraphics();
        NFRegion.draw(this.e, n, n2, n3, n4, color, 3, 2, Color.black, null, 0);
        if (graphics == null) {
            return;
        }
        graphics.drawImage(this.f, 0, 0, this);
        this.e.dispose();
        graphics.dispose();
    }
    
    public void drawDragImage(final int x, final int y) {
        (this.e = this.f.getGraphics()).setXORMode(this.getBackground());
        if (this.g != null) {
            this.e.drawImage(this.b, this.g.x, this.g.y, this);
        }
        else {
            this.g = new Point(0, 0);
        }
        this.e.drawImage(this.b, x, y, this);
        this.g.x = x;
        this.g.y = y;
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.drawImage(this.f, 0, 0, this);
        this.e.dispose();
        graphics.dispose();
        this.h = false;
    }
    
    public void flushDragTarget() {
        this.f.flush();
        this.g = null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this.a.getWidth(this), this.a.getHeight(this), this);
    }
}
