// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.image.ImageObserver;
import java.awt.Dimension;
import com.bullionvault.chart.c.h;
import com.bullionvault.chart.c.k;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import javax.swing.JLabel;

final class n extends JLabel implements Runnable
{
    private String a;
    private int b;
    private Font c;
    private FontMetrics d;
    private int e;
    private int f;
    private int g;
    private float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private Image o;
    private boolean p;
    private Graphics q;
    private boolean r;
    
    public n() {
        this.b = 10;
        this.g = 20;
        this.h = 0.0f;
        this.i = -1;
        this.j = 0;
        this.p = false;
    }
    
    public final void addNotify() {
        super.addNotify();
        this.b();
        this.setFont(this.getFont());
        this.setBackground(Color.white);
        this.p = true;
        this.c();
        this.r = true;
        com.bullionvault.chart.c.k.a(this, "TickerPanel");
    }
    
    public final void removeNotify() {
        com.bullionvault.chart.c.h.d("Removing Ticker Label");
        this.a();
    }
    
    public final void setFont(final Font c) {
        super.setFont(this.c = c);
        this.d = this.getFontMetrics(this.c);
        this.e = this.d.getHeight();
        this.d.getAscent();
        this.f = this.d.getDescent();
        this.c();
    }
    
    public final void setText(final String a) {
        com.bullionvault.chart.c.h.e("Setting Ticker Text to: " + a);
        this.a = a;
        this.j = this.d.stringWidth(this.a);
        this.c();
    }
    
    private void b() {
        final Dimension size;
        if ((size = this.getSize()).width != this.k || size.height != this.l) {
            this.k = size.width;
            this.l = size.height;
            if (this.q != null) {
                this.q.dispose();
            }
            this.q = this.getGraphics();
        }
    }
    
    private void c() {
        if (this.a == null || !this.p) {
            return;
        }
        this.m = this.e + this.f;
        this.n = this.j + 4;
        this.o = this.createImage(this.n, this.m);
        final Graphics graphics;
        (graphics = this.o.getGraphics()).setClip(0, 0, this.n, this.m);
        graphics.drawString(this.a, 2, this.e);
        graphics.dispose();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        this.b();
        if (this.o != null) {
            graphics.setClip(0, 0, this.k, this.l);
            graphics.setColor(this.getForeground());
            graphics.drawRect(0, 0, this.k - 1, this.l - 1);
            graphics.setClip(1, 1, this.k - 2, this.l - 2);
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, this.b - 1, this.l - 2);
            if (this.m < this.l - 2) {
                graphics.fillRect(this.m, 1, this.k - 2, this.l - this.m - 1);
            }
            final int n = this.b + this.n + 1;
            graphics.fillRect(n, 1, this.k - n - 3, this.l - 2);
            graphics.drawImage(this.o, this.b, 1, null);
        }
    }
    
    public final void run() {
        try {
            while (this.r && !Thread.interrupted()) {
                if (this.q != null) {
                    this.paint(this.q);
                }
                this.b += this.i;
                if (this.b > this.k) {
                    try {
                        Thread.sleep(0L);
                    }
                    catch (InterruptedException ex) {}
                    this.b();
                    this.b = 0 - this.n;
                }
                if (this.b + this.n < 0) {
                    try {
                        Thread.sleep(0L);
                    }
                    catch (InterruptedException ex2) {}
                    this.b();
                    this.b = this.k;
                }
                Thread.sleep(this.g);
            }
        }
        catch (InterruptedException ex3) {}
        if (this.q != null) {
            this.q.dispose();
        }
        com.bullionvault.chart.c.h.e("Ticker Stopped.");
    }
    
    public final void a() {
        if (!this.r) {
            return;
        }
        com.bullionvault.chart.c.h.e("Stopping Ticker Thread");
        this.r = false;
        this.q.dispose();
        this.q = null;
        com.bullionvault.chart.c.k.a(this);
    }
}
