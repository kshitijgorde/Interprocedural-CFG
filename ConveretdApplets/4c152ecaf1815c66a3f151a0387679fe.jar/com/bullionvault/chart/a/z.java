// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.f.c;
import com.bullionvault.chart.c.k;
import com.bullionvault.chart.g.b;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import com.bullionvault.chart.c.i;
import com.bullionvault.chart.b.a;
import java.awt.Dimension;
import com.bullionvault.chart.c.j;
import com.bullionvault.chart.e.e;
import com.bullionvault.chart.b.d;
import com.bullionvault.chart.e.h;
import javax.swing.JPanel;

final class z extends JPanel implements h
{
    private t f;
    d a;
    private String g;
    com.bullionvault.chart.e.d b;
    e c;
    boolean d;
    j e;
    private Dimension h;
    private Dimension i;
    private Dimension j;
    
    public z(final t f, final String g, final int n, final int n2, final int n3, final a a, final String s) {
        this.d = false;
        this.j = new Dimension(800, 200);
        this.f = f;
        this.g = g;
        (this.a = new d(2, 800, 200)).a(a);
        if (s != null) {
            this.a.a().addAll(com.bullionvault.chart.c.i.a(s));
        }
        this.j = new Dimension(800, 200);
        this.setBackground(this.a.a);
        this.a.a(2);
        this.addMouseMotionListener(new y(this));
        this.addMouseListener(new x(this));
    }
    
    public final void a(final String s) {
        this.a.b(s);
    }
    
    public final void d() {
        try {
            com.bullionvault.chart.g.b.a.a(this.g);
        }
        catch (com.bullionvault.chart.e.i i) {
            com.bullionvault.chart.c.h.d("" + i);
        }
        this.f.b();
    }
    
    public final void a(final j j) {
        final com.bullionvault.chart.e.d d = new com.bullionvault.chart.e.d(this, this.f, j.f);
        com.bullionvault.chart.c.h.f("Reading HLC...");
        try {
            d.a("/Full");
        }
        catch (s s) {
            throw new RuntimeException(s);
        }
        this.a(j.d);
        this.a(d, true);
        this.repaint();
    }
    
    public final void a(final com.bullionvault.chart.e.d b, final boolean b2) {
        com.bullionvault.chart.c.h.e("ChartPanel - setReader()");
        if (this.b != null) {
            com.bullionvault.chart.c.h.e("Stopping existing Reader Thread.");
            this.b.b();
        }
        this.b = b;
        this.a.a(b);
        this = this;
        com.bullionvault.chart.c.h.e("ChartPanel - startReader()");
        this.b.a();
        com.bullionvault.chart.c.h.e("ChartPanel - startReader() done");
    }
    
    public final void e() {
        if (this.c != null && k.c(this.c)) {
            com.bullionvault.chart.c.h.e("ChartPanel - action() - re-starting realtime with different currency.");
            this.c.b();
            this.c = null;
            this.a((c)null);
            return;
        }
        com.bullionvault.chart.c.h.e("ChartPanel - action() - no real-time reader to restart.");
    }
    
    final void b(String string) {
        com.bullionvault.chart.c.h.e("Trying to enable Real Time! ...");
        if (this.c != null && k.c(this.c)) {
            com.bullionvault.chart.c.h.e("Real Time thread already running - leave alone.");
            this.f.b(Resources.b("realtime.already_active"));
            return;
        }
        com.bullionvault.chart.c.h.e("Started new real-time session.");
        this.f.b(Resources.b("realtime.started"));
        string = "/CHART_REALTIME/" + com.bullionvault.chart.c.j.a(this.e.a) + "/" + com.bullionvault.chart.c.j.a(string);
        (this.c = new e(this, this.f, string)).a();
    }
    
    public final void a(final c c) {
        this.a.a(c);
        this.repaint();
    }
    
    public final void addNotify() {
        super.addNotify();
        this.a.a(this.getGraphics());
    }
    
    public final Dimension getMinimumSize() {
        return this.j;
    }
    
    public final Dimension getPreferredSize() {
        return this.j;
    }
    
    public final void f() {
        this.i = this.h;
        this.h = this.getSize();
        this.a.a(this.h);
    }
    
    public final void a() {
        this.f();
        this.repaint();
    }
    
    public final void b() {
        this.setCursor(Cursor.getPredefinedCursor(3));
    }
    
    public final void c() {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        this.h = this.getSize();
        if (this.i != null && this.h.equals(this.i)) {
            com.bullionvault.chart.c.h.e("No change in panel size - not rescaling.");
        }
        else {
            this.f();
        }
        final Image image;
        final Graphics graphics2;
        (graphics2 = (image = this.createImage(this.getSize().width, this.getSize().height)).getGraphics()).setClip(0, 0, this.getSize().width, this.getSize().height);
        this.a.b(graphics2);
        graphics.drawImage(image, 0, 0, null);
        graphics2.dispose();
    }
}
