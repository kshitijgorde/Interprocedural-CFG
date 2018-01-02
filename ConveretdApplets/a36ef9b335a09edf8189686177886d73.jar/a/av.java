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

public final class av extends Panel implements Runnable
{
    private as q;
    private Color q;
    private Color w;
    private Vector q;
    private int q;
    private int w;
    private int e;
    private int r;
    private boolean e;
    private Color e;
    private Thread q;
    protected boolean q;
    private boolean r;
    protected boolean w;
    private aD q;
    
    public av(final aH ah, final as q, final boolean e, final aD q2) {
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
        this.q = new Vector(ah.q.size());
        this.r = 0;
        for (int i = 0; i < ah.q.size(); ++i) {
            final Object q3;
            if ((q3 = ah.q()) instanceof aI) {
                final aD ad = new aD((aI)q3, q, this);
                this.q.addElement(ad);
                if (ad.q() > this.r - 40) {
                    this.r = ad.q() + 40;
                }
                ++this.q;
            }
            else if (q3 instanceof aJ) {
                this.q.addElement(new aE(q));
                ++this.w;
            }
        }
        if (!q.q()) {
            this.q(true);
        }
    }
    
    public final void q(int i) {
        this.e = i;
        this.w = false;
        if (this.q.q()) {
            for (i = 0; i < this.q + this.w; ++i) {
                this.remove(this.q.elementAt(i));
            }
            (this.q = new Thread(this)).start();
            return;
        }
        this.setSize(this.r, this.e);
        this.repaint();
    }
    
    public final void run() {
        this.q = true;
        int n = 0;
        if (this.e && !this.q.w()) {
            while (n < this.e || this.w) {
                this.setSize(this.r, n);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex) {}
            }
        }
        else {
            while (n < this.r || this.w) {
                this.setSize(n, this.e);
                n += 10;
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex2) {}
            }
        }
        this.setSize(this.r, this.e);
        this.q = false;
        this.q(this.r);
    }
    
    private void q(final boolean b) {
        final int r = this.q.r();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.q.size(); ++i) {
            final Panel panel;
            if ((panel = this.q.elementAt(i)) instanceof aD) {
                if (b) {
                    panel.setBounds(2, 2 + n * r + n2 * 3, this.r, r);
                }
                ++n;
            }
            else if (panel instanceof aE) {
                panel.setBounds(2, 2 + n * r + n2 * 3, this.r - 10, 3);
                ++n2;
            }
            this.add(panel);
            panel.repaint();
        }
        this.r = false;
    }
    
    public final int q() {
        return this.q;
    }
    
    public final int w() {
        return this.w;
    }
    
    public final int e() {
        return this.r;
    }
    
    public final String toString() {
        return "mis: " + this.q.size() + " width: " + this.getSize();
    }
    
    public final void paint(Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics = graphics;
        if (this.q.e()) {
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
        this.q.r();
        this.q();
    }
    
    public final void q() {
        for (int i = 0; i < this.q.size(); ++i) {
            ((Panel)this.q.elementAt(i)).removeAll();
        }
    }
    
    public final void w() {
        if (this.q != null) {
            final av q = this.q.q;
            for (int i = 0; i < q.q.size(); ++i) {
                final Panel panel;
                if ((panel = q.q.elementAt(i)) instanceof aD) {
                    ((aD)panel).q = false;
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 505: {
                new aw(this).start();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static as q(final av av) {
        return av.q;
    }
}
