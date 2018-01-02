// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.event.FocusListener;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Panel;

public final class bP extends Panel implements MouseListener
{
    public static Image q;
    public static Image w;
    public static Image e;
    public static Image r;
    private bu q;
    private boolean q;
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
    private P q;
    private aa q;
    private j q;
    private Polygon q;
    
    public bP(final aa q, final bu q2, final j q3) {
        this.setLayout(null);
        this.q = q;
        this.q = q3;
        this.q = q2.q("mb", 0, 0);
        this.w = q2.q("mb", 1, 0);
        this.r = q2.q("mb", 0, 1);
        this.t = q2.q("mb", 1, 1);
        this.q = q2.q("mb", 0);
        this.w = q2.q("mb", 1);
        this.e = this.q;
        this.e = this.q;
        this.y = this.r;
        this.setBackground(this.e);
        this.q = q.q;
        this.q = q2;
        this.addMouseListener(this);
        this.addFocusListener(new aw(this));
        if (q.q) {
            this.q = new P(q.q, q2, true, null);
        }
    }
    
    private int e() {
        return this.q.q(this.e).stringWidth(this.q);
    }
    
    private int r() {
        final FontMetrics q = this.q.q(this.e);
        return (this.getSize().height - q.getMaxDescent() + q.getMaxAscent()) / 2;
    }
    
    public final int q() {
        return this.e() + (aU.w.r() ? 10 : 30);
    }
    
    public final int w() {
        return this.q.w() + 10;
    }
    
    public final void paint(final Graphics graphics) {
        if (aU.w.r()) {
            graphics.setFont(this.e);
            graphics.setColor(this.w);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            graphics.setColor(this.y);
            graphics.drawString(this.q, 4, this.r());
            return;
        }
        final int height = this.getSize().height;
        if (this.q.t()) {
            this.q = new Polygon(new int[] { 5, 11, 11, 5 }, new int[] { height / 2 - 3, height / 2 - 3, height / 2 + 3, height / 2 + 3 }, 4);
        }
        else {
            this.q = new Polygon(new int[] { 5, 8, 11 }, new int[] { height / 2 - 3, height / 2 + 3, height / 2 - 3 }, 3);
        }
        graphics.setFont(this.e);
        graphics.setColor(this.e);
        if (this.q) {
            if (this.q.w()) {
                graphics.setColor(this.e.darker());
                graphics.fillRect(0, 0, this.getSize().width, height);
                graphics.setColor(this.e);
                graphics.fillRect(2, 2, this.getSize().width, height);
            }
            else {
                graphics.setColor(this.e);
                graphics.fillRect(0, 0, this.getSize().width, height);
            }
        }
        graphics.setColor(this.y);
        if (this.q() != null) {
            graphics.drawImage(this.q(), 0, this.getSize().height / 2 - 6, this);
        }
        else if (this.q != null) {
            graphics.fillPolygon(this.q);
        }
        graphics.drawString(this.q, 7 + (this.getSize().width / 2 - this.e() / 2), this.r());
    }
    
    private Image q() {
        return this.q.q;
    }
    
    private void w() {
        this.q = true;
        this.e = this.w;
        this.y = this.t;
        this.e = this.w;
        this.q.removeAll();
        this.q.r();
        if (this.q.q) {
            final P q = this.q;
            int q2 = this.getLocation().x + 10;
            final int e = this.q.e;
            if (this.q.r()) {
                q2 = this.q.q;
            }
            final int r = this.q.r();
            if (q2 + e > r - 5) {
                q2 = r - e - 5;
            }
            final int n = q2;
            int y = this.getLocation().y + this.getSize().height - 2 + 34;
            if (this.q.r()) {
                y = this.getLocation().y;
            }
            final int n2 = this.q.q * this.q.e() + this.q.w * 3 + 4;
            final int t = this.q.t();
            if (y + n2 > t - 5) {
                y = t - n2 - 5;
            }
            q.setLocation(n, y);
            this.q.setSize(0, 0);
            this.q.q((Component)this.q);
            this.q.q(this.q.q * this.q.e() + this.q.w * 3 + 4);
        }
        this.repaint();
    }
    
    private void e() {
        this.q = false;
        this.e = this.w;
        this.y = this.r;
        this.e = this.q;
        this.repaint();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.q.w(this.q);
        if (aU.w.t()) {
            this.w();
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.q.r();
        if (!aU.w.t()) {
            this.w();
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.q.t();
        if (!aU.w.t()) {
            this.e();
            new aB(this).start();
        }
    }
    
    public final void q() {
        this.q.y();
        if (this.q.q) {
            this.q.w = true;
            this.q.q((Component)this.q);
        }
        if (this.q.q) {
            this.q.removeAll();
        }
    }
    
    static bu q(final bP bp) {
        return bp.q;
    }
    
    static void q(final bP bp) {
        bp.e();
    }
    
    static {
        bP.q = null;
        bP.w = null;
        bP.e = null;
        bP.r = null;
    }
}
