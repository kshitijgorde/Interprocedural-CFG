// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class I extends Panel
{
    Image i;
    Image j;
    Graphics b;
    private aZ a;
    
    public I(final aZ a) {
        this.b = null;
        this.a = a;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final float[] array = new float[3];
        this.a.a.a(n, n2, array);
        this.a.a(array[0], array[1], array[2]);
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        final int width = this.size().width;
        final int height = this.size().height;
        int n3 = n;
        int n4 = n2;
        if (n3 >= width) {
            n3 = width;
        }
        if (n4 >= height) {
            n4 = height;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        final float[] array = new float[3];
        this.a.a.a(n3, n4, array);
        this.a.a(array[0], array[1], array[2]);
        return false;
    }
    
    public Dimension preferredSize() {
        return new Dimension(150, 150);
    }
    
    public Dimension minimumSize() {
        return new Dimension(150, 150);
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.j == null) {
            this.j = this.createImage(this.size().width, this.size().height);
            this.b = this.j.getGraphics();
        }
        final Rectangle rectangle = new Rectangle(0, 0, Math.min(this.size().height, 150), Math.min(this.size().height, 150));
        this.b.drawImage(this.i, 0, 0, this);
        this.b.setColor(Color.white);
        this.b.drawOval(this.a.b.x - 4, this.a.b.y - 4, 8, 8);
        graphics.drawImage(this.j, 0, 0, this);
    }
}
