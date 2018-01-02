// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Component;

public abstract class aI
{
    protected Component b;
    protected Rectangle c;
    private boolean a;
    private boolean i;
    i d;
    private boolean j;
    private Image k;
    private Graphics l;
    private boolean m;
    Color e;
    Color f;
    Font g;
    static Hashtable h;
    private String n;
    private boolean o;
    
    public void l() {
        if (this.b != null) {
            this.b.repaint(this.c.x, this.c.y, this.c.width, this.c.height);
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    private void a(final MouseEvent mouseEvent) {
        try {
            this.c(mouseEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void b(final MouseEvent mouseEvent) {
        try {
            this.d(mouseEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void f(final MouseEvent mouseEvent) {
        try {
            this.e(mouseEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    public abstract void a(final Graphics p0);
    
    private void a(final Throwable t) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        t.printStackTrace(System.out);
    }
    
    private void a() {
        this.b.addMouseListener(this.d);
    }
    
    private void b() {
        try {
            this.a();
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    public void m() {
        this.k = null;
    }
    
    public void c(final MouseEvent mouseEvent) {
        mouseEvent.getComponent().getLocationOnScreen();
    }
    
    public void d(final MouseEvent mouseEvent) {
    }
    
    public void e(final MouseEvent mouseEvent) {
    }
    
    public synchronized void d(final Graphics graphics) {
        if (this.i) {
            final Graphics create = graphics.create(this.c.x, this.c.y, this.c.width, this.c.height);
            try {
                if (this.p().width < 1 || this.p().height < 1) {
                    System.out.println("Trying to paint area with no size.");
                    return;
                }
                if (this.m) {
                    if (this.k == null || this.k.getWidth(this.b) != this.p().width || this.k.getHeight(this.b) != this.p().height) {
                        if (this.k != null && this.l != null) {
                            this.k.flush();
                            this.l.dispose();
                        }
                        this.k = y.a(this.p().width, this.p().height);
                        if (this.k == null) {
                            return;
                        }
                        this.l = this.k.getGraphics();
                    }
                    this.l.setClip(0, 0, this.p().width, this.p().height);
                    this.a(this.l);
                    if (this.k == null) {
                        System.err.println("_bufferedImage in NiceAbstractCanvas is null width=" + this.p().width + " height=" + this.p().height);
                        return;
                    }
                    create.drawImage(this.k, 0, 0, null);
                    if (y.a) {
                        Toolkit.getDefaultToolkit().sync();
                    }
                }
                else {
                    this.a(create);
                    if (y.a) {
                        Toolkit.getDefaultToolkit().sync();
                    }
                }
            }
            finally {
                create.dispose();
                if (y.a) {
                    Toolkit.getDefaultToolkit().sync();
                }
            }
        }
    }
    
    public void b(final boolean a) {
        this.a = a;
        this.l();
    }
    
    public void c(final boolean i) {
        this.i = i;
        this.l();
    }
    
    public boolean n() {
        return this.i;
    }
    
    public aI(final Component b, final Rectangle c) {
        this.a = true;
        this.i = true;
        this.d = new i(this);
        this.j = true;
        this.k = null;
        this.m = true;
        this.o = false;
        this.b = b;
        this.c = c;
        this.b();
    }
    
    public void k() {
        this.b.removeMouseListener(this.d);
    }
    
    public boolean o() {
        return this.a;
    }
    
    public Dimension p() {
        return new Dimension(this.c.width, this.c.height);
    }
    
    public Color q() {
        final Color e = this.e;
        if (e != null) {
            return e;
        }
        final Component b = this.b;
        return (b != null) ? b.getForeground() : null;
    }
    
    public void a(final Color e) {
        this.e = e;
    }
    
    public Color r() {
        final Color f = this.f;
        if (f != null) {
            return f;
        }
        final Component b = this.b;
        return (b != null) ? b.getBackground() : null;
    }
    
    public void b(final Color f) {
        this.f = f;
    }
    
    public Font s() {
        return this.t();
    }
    
    final Font t() {
        final Font g = this.g;
        if (g != null) {
            return g;
        }
        final Component b = this.b;
        return (b != null) ? b.getFont() : null;
    }
    
    public void a(final Font g) {
        this.g = g;
        this.m();
    }
    
    public FontMetrics b(final Font font) {
        final FontMetrics fontMetrics = aI.h.get(font);
        if (fontMetrics != null) {
            return fontMetrics;
        }
        if (this.b != null) {
            final Graphics graphics = this.b.getGraphics();
            if (graphics != null) {
                try {
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics(font);
                    aI.h.put(font, fontMetrics2);
                    return fontMetrics2;
                }
                finally {
                    graphics.dispose();
                }
            }
        }
        final FontMetrics fontMetrics3 = this.b.getToolkit().getFontMetrics(font);
        aI.h.put(font, fontMetrics3);
        return fontMetrics3;
    }
    
    public synchronized void b(final String n) {
        this.n = n;
        this.o = true;
    }
    
    public Rectangle u() {
        return this.v();
    }
    
    public Rectangle v() {
        return this.c;
    }
    
    static {
        aI.h = new Hashtable();
    }
}
