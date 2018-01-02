// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public final class aD extends Panel
{
    private as q;
    private String q;
    private Color q;
    private Color w;
    private Color e;
    private Color r;
    private Color t;
    private Color y;
    private Font q;
    private Font w;
    private Font e;
    private av w;
    private boolean w;
    private boolean e;
    av q;
    private boolean r;
    private aI q;
    private Polygon q;
    private int q;
    boolean q;
    
    public aD(final aI q, final as q2, final av w) {
        this.e = true;
        this.q = 20;
        this.w = w;
        this.q = q;
        this.setLayout(null);
        this.q = q2.q("mi", 0, 0);
        this.w = q2.q("mi", 1, 0);
        this.r = q2.q("mi", 0, 1);
        this.t = q2.q("mi", 1, 1);
        this.q = q2.q("mi", 0);
        this.w = q2.q("mi", 1);
        this.e = this.q;
        this.e = this.q;
        this.y = this.r;
        this.setBackground(this.e);
        this.q = q.q;
        this.q = q2;
        if (q.q) {
            this.r = true;
            this.q = new av(q.q, this.q, false, this);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                this.w.q();
                this.w = true;
                this.e = this.w;
                this.y = this.t;
                this.e = this.w;
                this.repaint();
                if (this.r) {
                    final av q = this.q;
                    int n = this.getLocation().x + this.w.getSize().width + this.w.getLocation().x - 10;
                    final int e = this.q.e();
                    if (n + e > this.q.w() - 5) {
                        n = this.getLocation().x + this.w.getLocation().x - e;
                    }
                    final int n2 = n;
                    int n3 = this.getLocation().y + this.w.getLocation().y - 2;
                    final int n4 = this.q.q() * this.q.r() + this.q.w() * 3 + 4;
                    final int e2 = this.q.e();
                    if (n3 + n4 > e2 - 5) {
                        n3 = e2 - n4 - 5;
                    }
                    q.setLocation(n2, n3);
                    this.q.setSize(0, 0);
                    this.q.q((Component)this.q);
                    this.q.q(this.q.q() * this.q.r() + this.q.w() * 3 + 4);
                }
                this.q.w();
                return true;
            }
            case 505: {
                this.w = false;
                this.e = this.q;
                this.y = this.r;
                this.e = this.q;
                this.repaint();
                this.q.e();
                return true;
            }
            case 502: {
                if (!this.e) {
                    return true;
                }
                this.w = false;
                this.e = this.q;
                this.y = this.r;
                this.e = this.q;
                this.repaint();
                this.q.q(this.q);
                this.w.removeAll();
                this.q.q();
                this.w.w();
                if (this.q.w) {
                    this.q = true;
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final int q() {
        return this.q.q(this.e).stringWidth(this.q);
    }
    
    public final void paint(Graphics graphics) {
        if (!this.w.q) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            graphics = graphics;
            this.q = new Polygon(new int[] { width - 10, width - 10, width - 6 }, new int[] { height / 2 - 3, height / 2 + 3, height / 2 }, 3);
            graphics.setFont(this.e);
            final boolean w = this.w;
            graphics.setColor(this.e);
            graphics.fillRect(0, 0, width, height);
            if (this.e) {
                graphics.setColor(this.y);
            }
            else {
                graphics.setColor(Color.gray.brighter());
            }
            if (this.r) {
                graphics.fillPolygon(this.q);
            }
            if (this.q) {
                graphics.fillArc(this.q / 2, (height - 7) / 2, 7, 7, 0, 360);
            }
            if (this.q() != null) {
                graphics.drawImage(this.q(), 0, height / 2 - 8, this);
            }
            final Graphics graphics2 = graphics;
            final String q = this.q;
            final int q2 = this.q;
            final FontMetrics q3 = this.q.q(this.e);
            graphics2.drawString(q, q2, (this.getSize().height - q3.getMaxDescent() + q3.getMaxAscent()) / 2);
        }
    }
    
    private Image q() {
        final String q = this.q.q;
        final String p = au.p;
        final String q2 = this.q.q;
        final String a = au.a;
        return this.q.q;
    }
    
    public final void update(final Graphics graphics) {
        if (!this.w.q) {
            this.paint(graphics);
        }
    }
    
    public final String toString() {
        return "Label: " + this.q + " Size: " + this.getBounds();
    }
    
    public final void removeAll() {
        this.q.r();
        if (this.q != null) {
            this.q.removeAll();
            this.q.q((Component)this.q);
        }
    }
}
