// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.Panel;

final class J extends Panel
{
    cn q;
    private static Image q;
    private ad q;
    
    public J(final ad q) {
        this.q = q;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final float[] array = new float[3];
        this.q.q.q(n, n2, array);
        this.q.q(array[0], array[1], array[2]);
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
        this.q.q.q(n3, n4, array);
        this.q.q(array[0], array[1], array[2]);
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
        if (J.q == null) {
            J.q = cs.q(this, this.getSize().width, this.getSize().height, "cpp.u");
        }
        final Graphics graphics2;
        (graphics2 = J.q.getGraphics()).drawImage(this.q.q(J.q), 0, 0, this);
        graphics2.setColor(Color.white);
        graphics2.drawOval(this.q.q.x - 4, this.q.q.y - 4, 8, 8);
        graphics2.dispose();
        graphics.drawImage(J.q, 0, 0, this);
    }
}
