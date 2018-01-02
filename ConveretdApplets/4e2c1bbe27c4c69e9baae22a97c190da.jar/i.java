import java.awt.Event;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends Canvas
{
    public boolean a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public String h;
    public static Color i;
    public static Color j;
    public Image k;
    public Image l;
    public Image m;
    public Image n;
    public boolean o;
    public boolean p;
    public String q;
    
    public void a() {
        this.b = true;
    }
    
    public void b() {
        this.b = false;
    }
    
    public void a(final boolean a) {
        this.a = a;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public i() {
        this.b = false;
        this.c = true;
        this.f = false;
        this.g = true;
        this.h = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
    }
    
    public i(final Image n) {
        this.b = false;
        this.c = true;
        this.f = false;
        this.g = true;
        this.h = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.k = n;
        this.l = this.k;
        this.m = n;
        this.n = n;
    }
    
    public i(final Image k, final Image image, final Image m) {
        this.b = false;
        this.c = true;
        this.f = false;
        this.g = true;
        this.h = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.k = k;
        this.l = image;
        this.m = m;
        this.n = image;
    }
    
    public i(final Image k, final Image l, final Image m, final Image n) {
        this.b = false;
        this.c = true;
        this.f = false;
        this.g = true;
        this.h = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
    }
    
    public void paint(final Graphics graphics) {
        if (this.k == null) {
            return;
        }
        if (!this.c) {
            if (this.p || (this.b && this.a)) {
                graphics.drawImage(this.n, 0, 0, null);
            }
            else {
                graphics.drawImage(this.l, 0, 0, null);
            }
        }
        else if (this.p || (this.b && this.a)) {
            graphics.drawImage(this.m, 0, 0, null);
        }
        else {
            graphics.drawImage(this.k, 0, 0, null);
        }
        if (this.f && this.h != null) {
            this.setBackground(i.j);
            final Color color = graphics.getColor();
            final Rectangle bounds = this.bounds();
            if (bounds != null) {
                if (this.g) {
                    graphics.clearRect(0, 0, bounds.width - 1, bounds.height - 1);
                    graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
                }
                final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
                if (fontMetrics != null) {
                    graphics.drawString(this.h, (bounds.width - fontMetrics.stringWidth(this.h)) / 2, (bounds.height - fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()) / 2);
                }
            }
            graphics.setColor(color);
        }
    }
    
    public void setName(final String q) {
        this.q = q;
    }
    
    public String getName() {
        return this.q;
    }
    
    public void enable() {
        this.c = true;
        this.repaint();
    }
    
    public void disable() {
        this.c = false;
        this.repaint();
    }
    
    public Dimension size() {
        return new Dimension(this.k.getWidth(this), this.k.getHeight(this));
    }
    
    public synchronized boolean mouseDown(final Event event, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.o = true;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.postEvent(new Event(this, 1001, this.q));
        this.o = false;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseEnter(final Event event, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.p = true;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseExit(final Event event, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.p = false;
        this.repaint();
        return true;
    }
    
    static {
        i.i = Color.black;
        i.j = Color.lightGray;
    }
}
