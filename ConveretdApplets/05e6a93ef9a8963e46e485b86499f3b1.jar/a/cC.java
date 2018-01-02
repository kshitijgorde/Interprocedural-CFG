// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;

final class cC extends Canvas
{
    private Rectangle q;
    cn q;
    private static Image q;
    public float q;
    int q;
    boolean q;
    private ad q;
    
    public cC(final ad q) {
        this.q = 100;
        this.q = false;
        this.q = q;
    }
    
    private float q(int n) {
        this.size();
        this.size();
        final int n2 = this.size().height - 1;
        final int q = this.q;
        double n3;
        if ((n = Math.min(n = Math.max(n, 0), n2)) != 0) {
            n3 = n / n2;
        }
        else {
            n3 = 0.0;
        }
        n = (int)Math.round(n3 * q);
        if (!this.q) {
            n = q - n;
        }
        final float n4 = n / q;
        this.repaint();
        return n4;
    }
    
    public final synchronized void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final synchronized void update(final Graphics graphics) {
        this.getSize();
        if (cC.q == null) {
            cC.q = cs.q(this, this.getSize().width, this.getSize().height, "vcr.u");
        }
        final Graphics graphics2 = cC.q.getGraphics();
        this.q = new Rectangle(8, 0, 8, 150);
        graphics2.setColor(Color.black);
        graphics2.drawImage(this.q.q(cC.q), 0, 0, this);
        final float q = this.q;
        int n;
        if (this.q) {
            n = this.q.y + (int)(this.q.height * q);
        }
        else {
            n = this.q.y + (int)(this.q.height * (1.0 - q));
        }
        graphics2.setColor(Color.blue);
        final int n2 = n - 4 + this.q.x / 2;
        final Polygon polygon;
        (polygon = new Polygon()).addPoint(0, n2 - 8);
        polygon.addPoint(8, n2);
        polygon.addPoint(0, n2 + 8);
        graphics2.setColor(Color.black);
        graphics2.fillPolygon(polygon);
        graphics2.setColor(Color.pink);
        graphics2.drawPolygon(polygon);
        graphics2.setColor(Color.pink);
        graphics2.drawLine(this.q.x, n, this.q.x + this.q.width - 1, n);
        graphics2.dispose();
        graphics.drawImage(cC.q, 0, 0, this);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final float q = this.q(n2);
        final float[] q2 = this.q.q();
        switch (this.q.q) {
            case 0: {
                this.q.q(q, q2[1], q2[2]);
                break;
            }
            case 1: {
                this.q.q(q2[0], q, q2[2]);
                break;
            }
            case 2: {
                this.q.q(q2[0], q2[1], q);
                break;
            }
        }
        return false;
    }
    
    public final boolean mouseDrag(final Event event, final int n, int n2) {
        this.size();
        final int height = this.size().height;
        if (n2 >= height) {
            n2 = height - 1;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        final float q = this.q(n2);
        final float[] q2 = this.q.q();
        switch (this.q.q) {
            case 0: {
                this.q.q(q, q2[1], q2[2]);
                break;
            }
            case 1: {
                this.q.q(q2[0], q, q2[2]);
                break;
            }
            case 2: {
                this.q.q(q2[0], q2[1], q);
                break;
            }
        }
        return false;
    }
    
    public final Dimension preferredSize() {
        return new Dimension(20, 150);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(25, 150);
    }
}
