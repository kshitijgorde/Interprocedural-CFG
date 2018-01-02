// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public final class Q extends Panel implements Runnable
{
    bt q;
    private Color q;
    private Color w;
    Vector q;
    public int q;
    public int w;
    private int r;
    public int e;
    private boolean e;
    private Color e;
    private Thread q;
    protected boolean q;
    private boolean r;
    protected boolean w;
    cc q;
    
    public Q(final aW aw, final bt q, final boolean e, final cc q2) {
        this.q = q2;
        this.e = true;
        this.q = false;
        this.r = true;
        this.e = e;
        this.setLayout(null);
        this.q = q;
        this.setBackground(this.e = q.q("mi", 0, 0));
        final Color e2;
        int n = (e2 = this.e).getRed() + 40;
        int n2 = e2.getGreen() + 40;
        int n3 = e2.getBlue() + 40;
        if (n > 255) {
            n = 255;
        }
        if (n2 > 255) {
            n2 = 255;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        this.q = new Color(n, n2, n3);
        final Color e3;
        int n4 = (e3 = this.e).getRed() - 40;
        int n5 = e3.getGreen() - 40;
        int n6 = e3.getBlue() - 40;
        if (n4 < 255) {
            n4 = 0;
        }
        if (n5 < 255) {
            n5 = 0;
        }
        if (n6 < 255) {
            n6 = 0;
        }
        this.w = new Color(n4, n5, n6);
        this.q = new Vector(aw.q.size());
        this.e = 0;
        for (int i = 0; i < aw.q.size(); ++i) {
            final Object q3;
            if ((q3 = aw.q()) instanceof aa) {
                final cc cc = new cc((aa)q3, q, this);
                this.q.addElement(cc);
                if (cc.q() > this.e - 40) {
                    this.e = cc.q() + 40;
                }
                ++this.q;
            }
            else if (q3 instanceof y) {
                this.q.addElement(new H(q));
                ++this.w;
            }
        }
        if (!q.q()) {
            this.q(true);
        }
    }
    
    public final void q(int i) {
        this.r = i;
        this.w = false;
        if (this.q.q()) {
            for (i = 0; i < this.q + this.w; ++i) {
                this.remove(this.q.elementAt(i));
            }
            (this.q = new Thread(this)).start();
            return;
        }
        this.setSize(this.e, this.r);
        this.repaint();
    }
    
    public final void run() {
        this.q = true;
        int n = 0;
        if (this.e && !this.q.r()) {
            while (n < this.r || this.w) {
                this.setSize(this.e, n);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex) {}
            }
        }
        else {
            while (n < this.e || this.w) {
                this.setSize(n, this.r);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex2) {}
            }
        }
        this.setSize(this.e, this.r);
        this.q = false;
        this.q(this.r);
    }
    
    private void q(final boolean b) {
        final int e = this.q.e();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.q.size(); ++i) {
            final Panel panel;
            if ((panel = this.q.elementAt(i)) instanceof cc) {
                if (b) {
                    panel.setBounds(2, 2 + n * e + n2 * 3, this.e, e);
                }
                ++n;
            }
            else if (panel instanceof H) {
                panel.setBounds(2, 2 + n * e + n2 * 3, this.e - 10, 3);
                ++n2;
            }
            this.add(panel);
            panel.repaint();
        }
        this.r = false;
    }
    
    public final String toString() {
        return "mis: " + this.q.size() + " width: " + this.getSize();
    }
    
    public final void paint(Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics = graphics;
        if (this.q.w()) {
            graphics.setColor(this.q);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(this.w);
            graphics.fillRect(2, 2, width, height);
            graphics.setColor(this.e);
            graphics.fillRect(2, 2, width - 4, height - 4);
            return;
        }
        graphics.setColor(this.e);
        graphics.fillRect(0, 0, width, height);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void removeAll() {
        this.q.y();
        this.q();
    }
    
    public final void q() {
        for (int i = 0; i < this.q.size(); ++i) {
            ((Panel)this.q.elementAt(i)).removeAll();
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 505: {
                new o(this).start();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
