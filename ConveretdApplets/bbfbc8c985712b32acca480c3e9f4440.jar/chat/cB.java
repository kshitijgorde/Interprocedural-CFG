// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

final class cB extends Panel
{
    Image a;
    private Image b;
    private Graphics a;
    private J a;
    
    public cB(final J a) {
        this.a = null;
        this.a = a;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final float[] array = new float[3];
        this.a.a.a(n, n2, array);
        this.a.a(array[0], array[1], array[2]);
        return false;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        final int width = this.size().width;
        final int height = this.size().height;
        int n3 = n;
        int n4 = n2;
        if (n >= width) {
            n3 = width;
        }
        if (n2 >= height) {
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
    
    public final Dimension preferredSize() {
        return new Dimension(150, 150);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(150, 150);
    }
    
    public final synchronized void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final synchronized void update(final Graphics graphics) {
        if (this.b == null) {
            this.b = this.createImage(this.size().width, this.size().height);
            this.a = this.b.getGraphics();
        }
        new Rectangle(0, 0, Math.min(this.size().height, 150), Math.min(this.size().height, 150));
        this.a.drawImage(this.a, 0, 0, this);
        this.a.setColor(Color.white);
        this.a.drawOval(this.a.a.x - 4, this.a.a.y - 4, 8, 8);
        graphics.drawImage(this.b, 0, 0, this);
    }
}
