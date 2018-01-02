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

public final class aA extends Panel implements MouseListener
{
    public static Image q;
    public static Image w;
    public static Image e;
    public static Image r;
    private as q;
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
    private av q;
    private aI q;
    private ax q;
    private Polygon q;
    
    public aA(final aI q, final as q2, final ax q3) {
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
        this.addFocusListener(new aB(this));
        if (q.q) {
            this.q = new av(q.q, q2, true, null);
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
        return this.e() + (be.w.r() ? 10 : 30);
    }
    
    public final int w() {
        return this.q.t() + 10;
    }
    
    public final void paint(final Graphics graphics) {
        if (be.w.r()) {
            graphics.setFont(this.e);
            graphics.setColor(this.w);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            graphics.setColor(this.y);
            graphics.drawString(this.q, 4, this.r());
            return;
        }
        final int height = this.getSize().height;
        if (this.q.r()) {
            this.q = new Polygon(new int[] { 5, 11, 11, 5 }, new int[] { height / 2 - 3, height / 2 - 3, height / 2 + 3, height / 2 + 3 }, 4);
        }
        else {
            this.q = new Polygon(new int[] { 5, 8, 11 }, new int[] { height / 2 - 3, height / 2 + 3, height / 2 - 3 }, 3);
        }
        graphics.setFont(this.e);
        graphics.setColor(this.e);
        if (this.q) {
            if (this.q.e()) {
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
        final String q = this.q.q;
        final String p = au.p;
        final String q2 = this.q.q;
        final String a = au.a;
        return this.q.q;
    }
    
    private void w() {
        this.q = true;
        this.e = this.w;
        this.y = this.t;
        this.e = this.w;
        this.q.removeAll();
        this.q.w();
        if (this.q.q) {
            final av q = this.q;
            int q2 = this.getLocation().x + 10;
            final int e = this.q.e();
            if (this.q.w()) {
                q2 = this.q.q;
            }
            final int w = this.q.w();
            if (q2 + e > w - 5) {
                q2 = w - e - 5;
            }
            final int n = q2;
            int y = this.getLocation().y + this.getSize().height - 2 + 34;
            if (this.q.w()) {
                y = this.getLocation().y;
            }
            final int n2 = this.q.q() * this.q.r() + this.q.w() * 3 + 4;
            final int e2 = this.q.e();
            if (y + n2 > e2 - 5) {
                y = e2 - n2 - 5;
            }
            q.setLocation(n, y);
            this.q.setSize(0, 0);
            this.q.q((Component)this.q);
            this.q.q(this.q.q() * this.q.r() + this.q.w() * 3 + 4);
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
        this.q.q(this.q);
        if (be.w.t()) {
            this.w();
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.q.w();
        if (!be.w.t()) {
            this.w();
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.q.e();
        if (!be.w.t()) {
            this.e();
            new aC(this).start();
        }
    }
    
    public final void q() {
        this.q.r();
        if (this.q.q) {
            this.q.w = true;
            this.q.q((Component)this.q);
        }
        if (this.q.q) {
            this.q.removeAll();
        }
    }
    
    static as q(final aA aa) {
        return aa.q;
    }
    
    static void q(final aA aa) {
        aa.e();
    }
    
    static {
        aA.q = null;
        aA.w = null;
        aA.e = null;
        aA.r = null;
    }
}
