// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.io.h;
import java.awt.Graphics;
import ji.util.e;
import ji.v1event.af;
import java.awt.event.AdjustmentListener;
import java.awt.Color;
import ji.util.d;
import java.awt.event.ActionListener;
import java.awt.Component;
import ji.v1event.c9;
import java.awt.LayoutManager;
import ji.document.ad;
import java.awt.Dimension;
import ji.v1base.jiPanel;

public class cu extends jiPanel
{
    static int a;
    static int b;
    static int c;
    static int d;
    private boolean e;
    int f;
    int g;
    int h;
    int i;
    String j;
    by k;
    by l;
    by m;
    String n;
    int o;
    Dimension p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    String u;
    String v;
    String w;
    jiImageButton x;
    jiImageButton y;
    jiImageButton z;
    ad aa;
    boolean ab;
    
    public cu(final String j, final Dimension dimension, final int o, final ad aa, final Dimension dimension2) {
        super(j);
        this.e = false;
        this.f = 50;
        this.g = 22;
        this.h = 22;
        this.i = cu.a + cu.d + this.h + cu.c;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = 0;
        this.p = new Dimension(0, 0);
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = "resetBrightness";
        this.v = "resetContrast";
        this.w = "resetLuminance";
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = false;
        this.setName(String.valueOf(String.valueOf(this.getName())).concat(String.valueOf(String.valueOf(j))));
        try {
            this.aa = aa;
            this.j = j;
            this.p.width = dimension.width;
            this.p.height = dimension.height;
            this.o = o;
            this.setLayout(null);
            this.setBorderStyle(2);
            this.k = new by(1, 2, j);
            this.l = new by(1, 2, j);
            this.m = new by(1, 2, j);
            this.k.a("142");
            this.k.addToolTipListener(aa.gs());
            this.l.a("143");
            this.l.addToolTipListener(aa.gs());
            this.m.a("144");
            this.m.addToolTipListener(aa.gs());
            this.k.setVisible(false);
            this.l.setVisible(false);
            this.m.setVisible(false);
            this.add(this.k);
            this.add(this.l);
            this.add(this.m);
            this.x = new jiImageButton(this.u, 33, dimension2.width, dimension2.height, false, true, true, true, 2, "145", "145", 1, false, null, null, true, null, -1, j);
            this.y = new jiImageButton(this.v, 34, dimension2.width, dimension2.height, false, true, true, true, 2, "146", "146", 1, false, null, null, true, null, -1, j);
            this.z = new jiImageButton(this.w, 35, dimension2.width, dimension2.height, false, true, true, true, 2, "147", "147", 1, false, null, null, true, null, -1, j);
            this.x.a(aa.gs());
            this.x.addActionListener(aa);
            this.y.a(aa.gs());
            this.y.addActionListener(aa);
            this.z.a(aa.gs());
            this.z.addActionListener(aa);
            this.x.setVisible(false);
            this.y.setVisible(false);
            this.z.setVisible(false);
            this.add(this.x);
            this.add(this.y);
            this.add(this.z);
            this.n = ji.util.d.ai(1);
        }
        catch (Exception ex) {}
    }
    
    public final void setBackground(final Color background) {
        try {
            if (this.k != null) {
                this.k.setBackground(background);
            }
            if (this.l != null) {
                this.l.setBackground(background);
            }
            if (this.m != null) {
                this.m.setBackground(background);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int a() {
        return this.k.j();
    }
    
    private final int a(final int n, final int n2) {
        return n2 - n;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (!this.k.c() && !this.l.c() && !this.m.c()) {
                this.k.a(this.a(n, n2), 1, 0, n2);
                this.l.a(this.a(n3, n4), 1, 0, n4);
                this.m.a(this.a(n5, n6), 1, 0, n6);
                this.k.setBlockIncrement(ji.util.d.d0 / this.k.g() / 2);
                this.l.setBlockIncrement(ji.util.d.d1 / this.l.g() / 2);
                this.m.setBlockIncrement(ji.util.d.d2 / this.m.g() / 2);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n) {
        try {
            final int a = this.a(n, this.k.getMaximum());
            if (this.k.getValue() != a) {
                this.k.setValue(a);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final int n) {
        try {
            final int a = this.a(n, this.l.getMaximum());
            if (this.l.getValue() != a) {
                this.l.setValue(a);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final int n) {
        try {
            final int a = this.a(n, this.l.getMaximum());
            if (this.m.getValue() != a) {
                this.m.setValue(a);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int b() {
        return this.a(this.k.getValue(), this.k.getMaximum());
    }
    
    public final int c() {
        return this.a(this.l.getValue(), this.l.getMaximum());
    }
    
    public final int d() {
        return this.a(this.m.getValue(), this.m.getMaximum());
    }
    
    public final void a(final AdjustmentListener adjustmentListener) {
        try {
            this.k.addAdjustmentListener(adjustmentListener);
            this.l.addAdjustmentListener(adjustmentListener);
            this.m.addAdjustmentListener(adjustmentListener);
        }
        catch (Exception ex) {}
    }
    
    public final void b(final AdjustmentListener adjustmentListener) {
        try {
            if (this.k != null) {
                this.k.removeAdjustmentListener(adjustmentListener);
            }
            if (this.l != null) {
                this.l.removeAdjustmentListener(adjustmentListener);
            }
            if (this.m != null) {
                this.m.removeAdjustmentListener(adjustmentListener);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final boolean e() {
        return this.q || this.r || this.s;
    }
    
    public final boolean f() {
        return this.k.h() || this.l.h() || this.m.h();
    }
    
    public final void a(final boolean q, final boolean r, final boolean s, final af af) {
        if (this.e) {
            return;
        }
        try {
            this.q = q;
            this.r = r;
            this.s = s;
            this.i = cu.c + cu.a + cu.d + this.h + cu.c;
            final int width = this.k.getMinimumSize().width;
            final int max = Math.max(width, this.g);
            this.f = 1 + cu.b * 2;
            int n = 0;
            int n2 = 0;
            if (q) {
                this.f += 1 + max;
                n2 += width;
                ++n;
            }
            if (r) {
                this.f += 1 + max;
                n2 += width;
                if (n > 0) {
                    this.f += cu.d;
                }
                ++n;
            }
            if (s) {
                this.f += 1 + max;
                if (n > 0) {
                    this.f += cu.d;
                }
                ++n;
            }
            final int n3 = this.f / n;
            int n4 = this.f - n3 + (n3 - width) / 2;
            final int n5 = cu.c + this.h + cu.d;
            final int c = cu.c;
            if (q) {
                final Dimension preferredSize = this.x.getPreferredSize();
                ji.util.e.a(this.k, n4, n5, width, cu.a);
                ji.util.e.a(this.x, n4 - 4, c, preferredSize.width, preferredSize.height);
                n4 -= n3;
                if (!this.k.isVisible()) {
                    this.k.setVisible(true);
                }
                if (!this.x.isVisible()) {
                    this.x.setVisible(true);
                }
            }
            else {
                this.k.setVisible(false);
                this.x.setVisible(false);
            }
            if (r) {
                final Dimension preferredSize2 = this.y.getPreferredSize();
                ji.util.e.a(this.l, n4, n5, width, cu.a);
                ji.util.e.a(this.y, n4 - 4, c, preferredSize2.width, preferredSize2.height);
                n4 -= n3;
                if (!this.l.isVisible()) {
                    this.l.setVisible(true);
                }
                if (!this.y.isVisible()) {
                    this.y.setVisible(true);
                }
            }
            else {
                this.l.setVisible(false);
                this.y.setVisible(false);
            }
            if (s) {
                final Dimension preferredSize3 = this.z.getPreferredSize();
                ji.util.e.a(this.m, n4, n5, width, cu.a);
                ji.util.e.a(this.z, n4 - 4, c, preferredSize3.width, preferredSize3.height);
                if (!this.m.isVisible()) {
                    this.m.setVisible(true);
                }
                if (!this.z.isVisible()) {
                    this.z.setVisible(true);
                }
            }
            else {
                this.m.setVisible(false);
                this.z.setVisible(false);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final Dimension getSize() {
        return new Dimension(this.f, this.i);
    }
    
    public final void a(final boolean b) {
        try {
            if (b != this.t) {
                this.t = b;
                this.x.setEnabled(b);
                this.y.setEnabled(b);
                this.z.setEnabled(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean g() {
        return this.x.isEnabled();
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        try {
            if (!this.ab) {
                this.x.setImageFileNow();
                this.x.setImageFileDisabledNow();
                this.y.setImageFileNow();
                this.y.setImageFileDisabledNow();
                this.z.setImageFileNow();
                this.z.setImageFileDisabledNow();
                this.ab = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void releaseResources() {
        this.e = true;
        if (ji.util.d.ao) {
            ji.io.h.d(this.j, String.valueOf(String.valueOf(this.getName())).concat(" rel 1"));
        }
        try {
            if (this.k != null) {
                this.remove(this.k);
                this.k.removeToolTipListener(this.aa.gs());
                this.k.releaseResources();
                this.k = null;
            }
            if (this.l != null) {
                this.remove(this.l);
                this.l.removeToolTipListener(this.aa.gs());
                this.l.releaseResources();
                this.l = null;
            }
            if (this.m != null) {
                this.remove(this.m);
                this.m.removeToolTipListener(this.aa.gs());
                this.m.releaseResources();
                this.m = null;
            }
            if (this.x != null) {
                this.remove(this.x);
                this.x.b(this.aa.gs());
                this.x.removeActionListener(this.aa);
                this.x.releaseResources();
                this.x = null;
            }
            if (this.y != null) {
                this.remove(this.y);
                this.y.b(this.aa.gs());
                this.y.removeActionListener(this.aa);
                this.y.releaseResources();
                this.y = null;
            }
            if (this.z != null) {
                this.remove(this.z);
                this.z.b(this.aa.gs());
                this.z.removeActionListener(this.aa);
                this.z.releaseResources();
                this.z = null;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        finally {
            this.aa = null;
        }
        if (ji.util.d.ao) {
            ji.io.h.d(this.j, String.valueOf(String.valueOf(this.getName())).concat(" rel 2"));
        }
        super.releaseResources();
        if (ji.util.d.ao) {
            ji.io.h.d(this.j, String.valueOf(String.valueOf(this.getName())).concat(" rel 3"));
        }
    }
    
    static {
        cu.a = 150;
        cu.b = 10;
        cu.c = 8;
        cu.d = 6;
    }
}
