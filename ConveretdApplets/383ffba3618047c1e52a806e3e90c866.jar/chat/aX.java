// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

public abstract class aX extends Panel
{
    public Image a;
    private ao[] a;
    protected int a;
    protected int b;
    private int d;
    int c;
    private int e;
    protected r a;
    protected aY a;
    protected ao a;
    protected bh a;
    protected boolean a;
    protected boolean b;
    private Color a;
    
    public final int a() {
        return this.a.size().width;
    }
    
    public final synchronized void a(final ao ao) {
        try {
            int a = 0;
            int d = -1;
            for (int i = 0; i < this.c; ++i) {
                d += this.a(i).a;
            }
            if (d != this.d) {
                this.d = d;
            }
            if (this.c == this.a.length) {
                a = this.a(0).a;
                this.d -= a;
                final int n;
                if ((n = a - this.a) > 0) {
                    if (this.a.n == 4) {
                        this.a(-n);
                    }
                    else {
                        for (int j = 0; j < n / 4; ++j) {
                            this.a(-4);
                        }
                        if (n % 4 != 0) {
                            this.a(-(n % 4));
                        }
                    }
                    this.a = 0;
                    a = 0;
                }
                this.a -= a;
                ++this.e;
            }
            else {
                ++this.c;
            }
            this.a(this.a[(this.c - 1 + this.e) % this.a.length] = ao, this.a.size().width - 12 - 36, this.getFontMetrics(this.a.a.b()));
            final int height = this.a.size().height;
            this.d += ao.a;
            if (this.isShowing() && this.a == this.a.getMaximum() - a) {
                final int n2;
                final Graphics graphics;
                if ((n2 = this.d - this.a - height - 1) < ao.a && (graphics = this.a.getGraphics()) != null) {
                    this.a(ao, this.d - ao.a - this.a + 1, graphics);
                }
                if (n2 > 0) {
                    if (this.a.n == 4) {
                        this.a(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.a.n));
                        for (int n4 = n2 / n3, k = 0; k < n4; ++k) {
                            if (k != 0) {
                                Thread.sleep(5L);
                            }
                            this.a(-n3);
                        }
                        if (n2 % n3 != 0) {
                            this.a(-(n2 % n3));
                        }
                    }
                }
                this.a.setValues(this.d, height, 0, this.d - 1);
                this.a = this.a.getValue();
                return;
            }
            this.a.setValues(this.a, height, 0, this.d - 1);
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final ao p0, final int p1, final FontMetrics p2);
    
    public abstract void a(final ao p0, final int p1, final Graphics p2);
    
    public final synchronized ao a(final int n) {
        return this.a[(n + this.e) % this.a.length];
    }
    
    public boolean handleEvent(final Event event) {
        Label_0221: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.a.setValue(this.a - this.a.getLineIncrement());
                            break Label_0221;
                        }
                        case 1005: {
                            this.a.setValue(this.a + this.a.getLineIncrement());
                            break Label_0221;
                        }
                        case 1002: {
                            this.a.setValue(this.a - this.a.getPageIncrement());
                            break Label_0221;
                        }
                        case 1003: {
                            this.a.setValue(this.a + this.a.getPageIncrement());
                            break Label_0221;
                        }
                        case 1000: {
                            this.a.setValue(0);
                            break Label_0221;
                        }
                        case 1001: {
                            this.a.setValue(this.a.getMaximum());
                            break Label_0221;
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
                    if ((n = this.a - this.a.getValue()) != 0) {
                        this.a(n);
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    public final ao a(final Event event) {
        try {
            if (event.x >= 36) {
                int n = event.y + this.a;
                final int n2 = event.x - 36;
                for (int i = 0; i < this.c; ++i) {
                    final ao a = this.a(i);
                    if (n < a.a) {
                        a.d = -1;
                        final FontMetrics fontMetrics;
                        final int n4;
                        final int n3 = (n4 = n - (3 + fontMetrics.getAscent())) / (fontMetrics = this.getFontMetrics(this.a.a.b())).getHeight();
                        if (n4 > 0 && n3 < a.b) {
                            int n5;
                            for (n5 = a.a[n3]; n5 < a.a[n3 + 1] && a.a.charAt(n5) == ' '; ++n5) {}
                            final String substring = a.a.substring(n5, a.a[n3 + 1]);
                            int n6 = 0;
                            int j;
                            int n7 = (j = substring.length()) / 2;
                            if (n2 > fontMetrics.stringWidth(substring.substring(0, j))) {
                                return a;
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
                            a.d = n5 + n7;
                        }
                        return a;
                    }
                    n -= a.a;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.a.a.c == 0) {
            graphics.setColor(this.a);
            graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        }
    }
    
    public final synchronized void a() {
        int height = this.a.size().height;
        final boolean b = this.a.getValue() >= this.a.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.d = -1;
        if (this.c == 0) {
            ((r)(this.a = 0)).setValues(0, height, 0, 0);
            return;
        }
        final int n = this.a.size().width - 6 - 36;
        final FontMetrics fontMetrics = this.getFontMetrics(this.a.a.b());
        for (int i = 0; i < this.c; ++i) {
            final ao a = this.a(i);
            this.a(a, n, fontMetrics);
            this.d += a.a;
        }
        this.a.setValues(b ? this.d : this.a, height, 0, this.d);
        this.a = this.a.getValue();
    }
    
    public final void b() {
        this.a = false;
        this.repaint();
        this.a.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.a.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public final synchronized void a(final int n) {
        final Dimension size = this.a.size();
        this.a -= n;
        this.b -= n;
        this.a.a(this.a.getGraphics(), 0, size.height);
    }
    
    public final synchronized void c() {
        final int maximum;
        final int n = (maximum = this.a.getMaximum()) - this.a;
        this.a.setValue(maximum);
        if (n > 0) {
            if (this.a.n == 4) {
                this.a(n);
            }
            else {
                final int n2 = (int)Math.ceil(n / Math.log(n) / 3.0);
                for (int i = 0; i < n / n2; ++i) {
                    this.a(-n2);
                }
                if (n % n2 > 0) {
                    this.a(-(n % n2));
                }
            }
        }
        this.a = maximum;
    }
    
    public aX(final bh a, final boolean b) {
        this.a = Color.black;
        this.a = null;
        this.a = 0;
        this.b = 0;
        this.d = 0;
        this.c = 0;
        this.e = 0;
        this.a = new aY(this);
        this.a = null;
        this.a = a;
        this.a = new ao[75];
        this.b = b;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        this.a.setBackground(Color.white);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(1, 0, 1, 1);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        (this.a = new r()).setLineIncrement(10);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
    }
}
