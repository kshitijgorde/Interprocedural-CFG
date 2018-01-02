// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;

final class aC extends Canvas
{
    private Rectangle a;
    public Image a;
    private Graphics a;
    private Dimension a;
    private Image b;
    public float a;
    int a;
    boolean a;
    private x a;
    
    public aC(final x a) {
        this.a = null;
        this.a = 100;
        this.a = false;
        this.a = a;
    }
    
    private float a(int min) {
        this.size();
        this.size();
        final int n = this.size().height - 1;
        final int a = this.a;
        double n2;
        if ((min = Math.min(Math.max(min, 0), n)) != 0) {
            n2 = min / n;
        }
        else {
            n2 = 0.0;
        }
        min = (int)Math.round(n2 * a);
        if (!this.a) {
            min = a - min;
        }
        final float n3 = min / a;
        this.repaint();
        return n3;
    }
    
    public final synchronized void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.a == null || size.width != this.a.width || size.height != this.a.height) {
            this.a = size;
            if (this.b != null) {
                try {
                    this.b.flush();
                }
                catch (SecurityException ex) {}
            }
            this.b = this.createImage(size.width, size.height);
            this.a = this.b.getGraphics();
            this.a = new Rectangle(8, 5, 15, 150);
        }
        this.a.setColor(this.getBackground());
        this.a.fillRect(0, 0, size.width, size.height);
        this.a.setColor(Color.black);
        this.a.drawImage(this.a, 8, 5, this);
        final float a = this.a;
        int n;
        if (this.a) {
            n = this.a.y + (int)(this.a.height * a);
        }
        else {
            n = this.a.y + (int)(this.a.height * (1.0 - a));
        }
        this.a.setColor(Color.blue);
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        array[0] = -3;
        array[1] = 3;
        array[2] = -3;
        array2[0] = -12;
        array2[1] = -9;
        array2[2] = -6;
        final int n2 = n - 4 + this.a.x / 2;
        final Polygon polygon;
        (polygon = new Polygon()).addPoint(0, n2 - 8);
        polygon.addPoint(8, n2);
        polygon.addPoint(0, n2 + 8);
        final Graphics a2;
        (a2 = this.a).setColor(Color.black);
        a2.fillPolygon(polygon);
        a2.setColor(Color.pink);
        a2.drawPolygon(polygon);
        this.a.setColor(Color.pink);
        this.a.drawLine(this.a.x, n, this.a.x + this.a.width - 1, n);
        graphics.drawImage(this.b, 0, 0, this);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final float a = this.a(n2);
        final float[] a2 = this.a.a();
        switch (this.a.a) {
            case 0: {
                this.a.a(a, a2[1], a2[2]);
                break;
            }
            case 1: {
                this.a.a(a2[0], a, a2[2]);
                break;
            }
            case 2: {
                this.a.a(a2[0], a2[1], a);
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
        final float a = this.a(n2);
        final float[] a2 = this.a.a();
        switch (this.a.a) {
            case 0: {
                this.a.a(a, a2[1], a2[2]);
                break;
            }
            case 1: {
                this.a.a(a2[0], a, a2[2]);
                break;
            }
            case 2: {
                this.a.a(a2[0], a2[1], a);
                break;
            }
        }
        return false;
    }
    
    public final Dimension preferredSize() {
        return new Dimension(25, 160);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(20, 20);
    }
}
