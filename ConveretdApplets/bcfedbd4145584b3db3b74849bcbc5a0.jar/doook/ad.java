// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;

class ad extends Canvas
{
    public Rectangle a;
    public Image h;
    public Graphics b;
    Dimension b;
    Image i;
    public float d;
    int D;
    int E;
    boolean s;
    private bq a;
    
    public ad(final bq a) {
        this.b = null;
        this.D = 100;
        this.E = 0;
        this.s = false;
        this.a = a;
    }
    
    public float a(final int n, final int n2) {
        final int n3 = n2 / (this.D / this.size().height);
        final int height = this.size().height;
        final int n4 = 0;
        final int n5 = this.size().height - 1;
        final int d = this.D;
        final int n6 = 0;
        final int min = Math.min(Math.max(n2, n4), n5);
        double n7;
        if (min != n4) {
            n7 = (min - n4) / (n5 - n4);
        }
        else {
            n7 = 0.0;
        }
        int n8 = (int)Math.round(n7 * (d - n6)) + n6;
        if (!this.s) {
            n8 = d - n8;
        }
        final float n9 = n8 / d;
        this.repaint();
        return n9;
    }
    
    public void d(final float d) {
        this.d = d;
        this.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.b == null || size.width != this.b.width || size.height != this.b.height) {
            this.b = size;
            this.i = this.createImage(size.width, size.height);
            this.b = this.i.getGraphics();
            this.a = new Rectangle(8, 5, 15, 150);
        }
        this.b.setColor(this.getBackground());
        this.b.fillRect(0, 0, size.width, size.height);
        this.b.setColor(Color.black);
        this.b.drawImage(this.h, 8, 5, this);
        final float d = this.d;
        int n;
        if (this.s) {
            n = this.a.y + (int)(this.a.height * d);
        }
        else {
            n = this.a.y + (int)(this.a.height * (1.0 - d));
        }
        this.b.setColor(Color.blue);
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        array[0] = -3;
        array[1] = 3;
        array[2] = -3;
        array2[0] = -12;
        array2[1] = -9;
        array2[2] = -6;
        final int n2 = n - 4 + this.a.x / 2;
        final Polygon polygon = new Polygon();
        polygon.addPoint(0, n2 - 8);
        polygon.addPoint(8, n2);
        polygon.addPoint(0, n2 + 8);
        final Graphics b = this.b;
        b.setColor(Color.black);
        b.fillPolygon(polygon);
        b.setColor(Color.pink);
        b.drawPolygon(polygon);
        this.b.setColor(Color.pink);
        this.b.drawLine(this.a.x, n, this.a.x + this.a.width - 1, n);
        graphics.drawImage(this.i, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final float a = this.a(n, n2);
        final float[] a2 = this.a.a();
        switch (this.a.ay) {
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
    
    public boolean mouseDrag(final Event event, int n, int n2) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (n >= width) {
            n = width - 1;
        }
        if (n2 >= height) {
            n2 = height - 1;
        }
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        final float a = this.a(n, n2);
        final float[] a2 = this.a.a();
        switch (this.a.ay) {
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
    
    public Dimension preferredSize() {
        return new Dimension(25, 160);
    }
    
    public Dimension minimumSize() {
        return new Dimension(20, 20);
    }
}