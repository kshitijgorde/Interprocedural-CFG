// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Panel;

public final class j extends Panel
{
    private bu q;
    private Color q;
    protected int q;
    private int w;
    private bP[] q;
    private int e;
    
    public j(int q, final int w, final aX ax, final bu q2) {
        this.setLayout(null);
        this.q = q2;
        this.q = q;
        this.w = w;
        this.q = q2.q("mb", 0, 0);
        this.setBackground(new Color(0, 0, 122));
        this.e = ax.q.size();
        this.q(ax);
        q = 0;
        for (int i = 0; i < ax.q.size(); ++i) {
            if (!q2.r()) {
                this.q[i].setBounds(q, 0, this.q[i].q(), w);
                q += this.q[i].q() + q2.q();
            }
            else {
                this.q[i].setBounds(2, q, this.q[i].q(), this.q[i].w());
                q += this.q[i].w() + q2.q();
            }
            this.add(this.q[i], 0);
            this.q[i].repaint();
        }
        this.repaint();
    }
    
    private void q(final aX ax) {
        this.q = new bP[this.e];
        while (ax.q < ax.q.size()) {
            final Object q;
            if ((q = ax.q()) instanceof aa) {
                this.q[ax.q - 1] = new bP((aa)q, this.q, this);
            }
        }
    }
    
    public final void removeAll() {
        this.q.y();
        for (int i = 0; i < this.e; ++i) {
            this.q[i].q();
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.setColor(this.q);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        final String q;
        if ((q = cu.q) != null && q.length() > 0) {
            final int n = ((this.getSize().width != 0) ? this.getSize().width : this.q) - (this.q.q(this.q.q("mb", 0)).stringWidth(q) + 10);
            graphics.setColor(this.q.q("mb", 1, 1));
            graphics.drawString(q, n, this.w - 4);
        }
    }
    
    public final void repaint() {
        this.removeAll();
        for (int i = 0; i < this.e; ++i) {
            this.q[i].repaint();
        }
        super.repaint();
    }
}
