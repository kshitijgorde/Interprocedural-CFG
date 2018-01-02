// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public abstract class bC extends Panel
{
    public static int q;
    public Image q;
    private bF[] q;
    protected int w;
    protected int e;
    private int r;
    private int t;
    private int y;
    protected p q;
    protected bE q;
    protected bF q;
    protected bH q;
    protected aQ q;
    protected long q;
    protected boolean q;
    protected boolean w;
    
    public final int q() {
        return this.q.size().width;
    }
    
    public final synchronized void q(final bF bf) {
        try {
            int q = 0;
            int r = -1;
            for (int i = 0; i < this.t; ++i) {
                r += this.q(i).q;
            }
            if (r != this.r) {
                this.r = r;
            }
            if (this.t == this.q.length) {
                q = this.q(0).q;
                this.r -= q;
                final int n;
                if ((n = q - this.w) > 0) {
                    if (this.q.o() == 4) {
                        this.q(-n);
                    }
                    else {
                        for (int j = 0; j < n / 4; ++j) {
                            this.q(-4);
                        }
                        if (n % 4 != 0) {
                            this.q(-(n % 4));
                        }
                    }
                    this.w = 0;
                    q = 0;
                }
                this.w -= q;
                ++this.y;
            }
            else {
                ++this.t;
            }
            this.q(this.q[(this.t - 1 + this.y) % this.q.length] = bf, this.q.size().width - 12 - bC.q, this.getFontMetrics(be.w.w()));
            final int height = this.q.size().height;
            this.r += bf.q;
            if (!this.isShowing() || this.w != this.q.getMaximum() - q) {
                this.q.setValues(this.w, height, 0, this.r - 1);
                return;
            }
            final int n2;
            final Graphics graphics;
            if ((n2 = this.r - this.w - height - 1) < bf.q && (graphics = this.q.getGraphics()) != null) {
                this.q(bf, this.r - bf.q - this.w + 1, graphics);
            }
            if (n2 > 0) {
                if (this.q.o() == 4) {
                    this.q(-n2);
                }
                else {
                    int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.q.o()));
                    int n4 = n2 / n3;
                    long n5 = 5L;
                    for (int k = 0; k < n4; ++k) {
                        if (k != 0) {
                            Thread.sleep(n5);
                        }
                        final int q2;
                        if ((q2 = this.q(-n3)) > 33 && n5 > 0L) {
                            final int n6;
                            if ((n6 = 100 / q2) == 0) {
                                final int n7;
                                if ((n7 = (n4 - k) * n3) > 0) {
                                    this.q(-n7);
                                }
                                n4 = n6;
                            }
                            else if (n6 < n4) {
                                n5 = 0L;
                                n4 = n6;
                                final int n8 = n2 / n4;
                                final int n10;
                                final int n9;
                                if ((n9 = ((n10 = k * n3 / n8) + 1) * n8 - k * n3) > 0) {
                                    this.q(-n9);
                                }
                                n3 = n8;
                                k = n10;
                            }
                        }
                    }
                    if (n2 % n3 != 0) {
                        this.q(-(n2 % n3));
                    }
                }
            }
            this.q.setValues(this.r, height, 0, this.r - 1);
            this.w = this.q.getValue();
        }
        catch (Exception ex) {}
    }
    
    public abstract void q(final bF p0, final int p1, final FontMetrics p2);
    
    protected abstract void q(final bF p0, final int p1, final Graphics p2);
    
    public final synchronized bF q(final int n) {
        return this.q[(n + this.y) % this.q.length];
    }
    
    public final int w() {
        return this.t;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0229: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.q.setValue(this.w - this.q.getLineIncrement());
                            break Label_0229;
                        }
                        case 1005: {
                            this.q.setValue(this.w + this.q.getLineIncrement());
                            break Label_0229;
                        }
                        case 1002: {
                            this.q.setValue(this.w - this.q.getPageIncrement());
                            break Label_0229;
                        }
                        case 1003: {
                            this.q.setValue(this.w + this.q.getPageIncrement());
                            break Label_0229;
                        }
                        case 1000: {
                            this.q.setValue(0);
                            break Label_0229;
                        }
                        case 1001: {
                            this.q.setValue(this.q.getMaximum());
                            break Label_0229;
                        }
                    }
                    break;
                }
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n;
                    if ((n = this.w - this.q.getValue()) != 0) {
                        this.q(n);
                    }
                    return true;
                }
                case 1005: {
                    if (this.q != null && System.currentTimeMillis() - this.q > 2000L) {
                        new bD(this).start();
                        break;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public final bF q(final Event event) {
        synchronized (this) {
            if (event.x >= bC.q) {
                int n = event.y + this.w;
                final int n2 = event.x - bC.q;
                for (int i = 0; i < this.t; ++i) {
                    final bF q = this.q(i);
                    if (n < q.q) {
                        q.r = -1;
                        final FontMetrics fontMetrics;
                        final int n4;
                        final int n3 = (n4 = n - (3 + fontMetrics.getAscent())) / (fontMetrics = this.getFontMetrics(be.w.w())).getHeight();
                        if (n4 > 0 && n3 < q.w) {
                            int n5;
                            for (n5 = q.q[n3]; n5 < q.q[n3 + 1] && q.q.charAt(n5) == ' '; ++n5) {}
                            final String substring = q.q.substring(n5, q.q[n3 + 1]);
                            int n6 = 0;
                            int j = substring.length();
                            int n7 = (j + 0) / 2;
                            if (n2 > fontMetrics.stringWidth(substring.substring(0, j))) {
                                return q;
                            }
                            while (j > n6 + 1) {
                                if (fontMetrics.stringWidth(substring.substring(0, n7)) < n2) {
                                    n6 = n7;
                                }
                                else {
                                    j = n7;
                                }
                                n7 = (n6 + j) / 2;
                            }
                            q.r = n5 + n7;
                        }
                        return q;
                    }
                    n -= q.q;
                }
            }
            return null;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (be.w.u == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public final synchronized void q() {
        int height = this.q.size().height;
        final boolean b = this.q.getValue() >= this.q.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.r = -1;
        if (this.t == 0) {
            this.w = 0;
            this.q.setValues(0, height, 0, 0);
            return;
        }
        final int n = this.q.size().width - 6 - bC.q;
        final FontMetrics fontMetrics = this.getFontMetrics(be.w.w());
        for (int i = 0; i < this.t; ++i) {
            final bF q = this.q(i);
            this.q(q, n, fontMetrics);
            this.r += q.q;
        }
        this.q.setValues(b ? this.r : this.w, height, 0, this.r);
        this.w = this.q.getValue();
    }
    
    public final void w() {
        this.q = false;
        this.repaint();
        this.q.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.q.resize(n - this.q.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public final synchronized int q(final int n) {
        final Dimension size = this.q.size();
        this.w -= n;
        this.e -= n;
        return this.q.q(this.q.getGraphics(), 0, size.height);
    }
    
    public final synchronized void e() {
        final int maximum;
        final int n = (maximum = this.q.getMaximum()) - this.w;
        this.q.setValue(maximum);
        if (n > 0) {
            if (this.q.o() == 4) {
                this.q(n);
            }
            else {
                final int n2 = (int)Math.ceil(n / Math.log(n) / 3.0);
                for (int i = 0; i < n / n2; ++i) {
                    this.q(-n2);
                }
                if (n % n2 > 0) {
                    this.q(-(n % n2));
                }
            }
        }
        this.w = maximum;
    }
    
    public bC(final bz bz, final boolean w) {
        this.q = 0L;
        this.q = null;
        this.w = 0;
        this.e = 0;
        this.r = 0;
        this.t = 0;
        this.y = 0;
        this.q = null;
        this.q = (bH)bz;
        this.q = new bE(this, this);
        this.q = new bF[75];
        this.w = w;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        this.q.setBackground(Color.white);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        (this.q = new p(1)).setLineIncrement(10);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
    }
}
