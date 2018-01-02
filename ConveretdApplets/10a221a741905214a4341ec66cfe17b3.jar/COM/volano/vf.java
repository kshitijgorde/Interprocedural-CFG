// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Frame;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Canvas;

public class vf extends Canvas
{
    private static final Color a;
    private static final Color b;
    private static final Color c;
    private AppletContext d;
    private boolean e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private Image o;
    private Image p;
    private Image q;
    private Frame r;
    
    public vf(final AppletContext d, final URL url, final URL url2, final boolean e, final String f, final int g, final int h) {
        this.n = true;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = g - 1;
        this.j = h - 1;
        this.k = g - 3;
        this.l = h - 3;
        this.o = d.getImage(url);
        this.p = d.getImage(url2);
        this.setBackground(vf.c);
    }
    
    public void addNotify() {
        super.addNotify();
        Container container;
        for (container = this.getParent(); !(container instanceof Frame) && container != null; container = container.getParent()) {}
        if (container instanceof Frame) {
            this.r = (Frame)container;
        }
        this.prepareImage(this.o, this.g, this.h, this);
        this.prepareImage(this.p, this.g, this.h, this);
        this.q = this.o;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.g, this.h);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.g, this.h);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.m = true;
        this.q = this.p;
        if (this.r != null) {
            this.r.setCursor(12);
            this.d.showStatus(this.f);
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.n = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.n = true;
        this.repaint();
        this.deliverEvent(new Event(this, 1001, ""));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.m = true;
        this.q = this.o;
        if (this.r != null) {
            this.r.setCursor(0);
            this.d.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        if (this.m) {
            this.m = false;
            graphics.setColor(vf.c);
            graphics.fillRect(0, 0, this.g, this.h);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.q, 0, 0, this.g, this.h, this);
        if (this.e) {
            graphics.setColor(vf.a);
            graphics.draw3DRect(0, 0, this.i, this.j, this.n);
            graphics.setColor(vf.b);
            graphics.draw3DRect(1, 1, this.k, this.l, this.n);
        }
    }
    
    static {
        a = new Color(160, 160, 160);
        b = Color.lightGray;
        c = Color.lightGray;
    }
}
