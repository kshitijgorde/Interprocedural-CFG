// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import ji.awt.da;
import java.awt.Shape;
import ji.render.dt;
import ji.io.h;
import ji.util.d;
import java.awt.Rectangle;
import ji.annotate.dg;
import java.awt.Graphics;

public class dd
{
    cy a;
    public boolean b;
    private String c;
    
    public dd(final cy a, final String c) {
        this.a = null;
        this.b = false;
        this.c = null;
        this.a = a;
        this.c = c;
    }
    
    public final void a(final Graphics graphics, final dg dg) {
        this.a(graphics, dg, null, 0, 0, 0, null);
    }
    
    public final void a(final Graphics graphics, final dg dg, final int n, final int n2) {
        this.a(graphics, dg, null, 0, n, n2, null);
    }
    
    public final void a(final Graphics graphics, final dg dg, final Rectangle rectangle, final int n) {
        this.a(graphics, dg, rectangle, n, 0, 0, null);
    }
    
    public final void a(final Graphics graphics, final dg dg, final Rectangle rectangle, final int n, final int n2, final int n3, Rectangle rectangle2) {
        boolean b = false;
        final boolean fv = d.fv;
        boolean b2 = false;
        if (!this.a.q0) {
            try {
                this.a.dz = true;
                d.fv = false;
                if (!d.ls && dg.b(this.a.f8())) {
                    rectangle2 = null;
                }
                if (dg != null && this.a.m9) {
                    if (this.a.o() && !this.a.q0) {
                        if (d.dt()) {
                            h.d(this.c, "ia1");
                        }
                        this.a.gv();
                        this.a.at();
                    }
                    boolean b3 = false;
                    if (this.a.om == null) {
                        b3 = true;
                    }
                    if (dg.d0() != this.a.on) {
                        b3 = true;
                    }
                    if (b3) {
                        this.a.e(dg);
                    }
                    da da = this.a.a(dg, this.a.b8(), null);
                    if (this.a.d(dg) && this.a.a(dg) != null) {
                        da = this.a.a(dg);
                    }
                    else {
                        final da da2 = da;
                        da2.a -= 2;
                        final da da3 = da;
                        da3.b -= 2;
                        final da da4 = da;
                        da4.c += 4;
                        final da da5 = da;
                        da5.d += 4;
                    }
                    final da da6 = da;
                    da6.a -= n;
                    final da da7 = da;
                    da7.b -= n;
                    final da da8 = da;
                    da8.c += 2 * n;
                    final da da9 = da;
                    da9.d += 2 * n;
                    if (graphics != null) {
                        graphics.setClip(null);
                    }
                    if (graphics != null && da != null && this.a.om != null) {
                        final Dimension size = this.a.getSize();
                        final int n4 = 2;
                        if (this.a.ba.x + this.a.ba.width <= size.width && this.a.op + this.a.or >= this.a.ge.x + this.a.ge.width) {
                            b2 = true;
                            this.a.a(graphics, new Rectangle(this.a.ge.x + this.a.ge.width - n4, this.a.oq, n4, this.a.os), false);
                        }
                        if (this.a.ba.y + this.a.ba.height <= size.height && this.a.oq + this.a.os >= this.a.ge.y + this.a.ge.height) {
                            b2 = true;
                            this.a.a(graphics, new Rectangle(this.a.op, this.a.ge.y + this.a.ge.height - n4, this.a.or, n4), false);
                        }
                        final boolean b4 = true;
                        if (rectangle2 == null) {
                            graphics.setClip(null);
                        }
                        else {
                            graphics.setClip(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                        }
                        final Rectangle clipBounds = graphics.getClipBounds();
                        if (b4) {
                            if (d.ls) {
                                boolean b5 = false;
                                if (clipBounds == null) {
                                    b5 = true;
                                }
                                else {
                                    final da da10 = new da(clipBounds.intersection(new Rectangle(this.a.op, this.a.oq, this.a.or, this.a.os)));
                                    if (da10 != null && (da10.c > 0 || da10.d > 0)) {
                                        b5 = true;
                                    }
                                }
                                if (b5) {
                                    graphics.drawImage(this.a.om, this.a.op, this.a.oq, null);
                                }
                            }
                            else {
                                graphics.drawImage(this.a.om, this.a.op, this.a.oq, null);
                            }
                        }
                        final da m = this.a.rh.m();
                        if (clipBounds == null) {
                            b = true;
                        }
                        else if (m != null) {
                            final da da11 = new da(clipBounds.intersection(m.a()));
                            if (da11 != null && da11.a(m)) {
                                b = true;
                            }
                        }
                        if (b) {
                            this.a.rh.j();
                        }
                        if (b4 && !b && this.a.rh.k() && dg.m()) {
                            final Rectangle a = m.a();
                            if (a != null) {
                                final Rectangle rectangle3 = a;
                                ++rectangle3.width;
                                final Rectangle rectangle4 = a;
                                ++rectangle4.height;
                                graphics.setClip(a);
                                graphics.drawImage(this.a.om, this.a.op, this.a.oq, null);
                            }
                            this.a.rh.j();
                        }
                        dg.b(new da(this.a.op, this.a.oq, this.a.or, this.a.os));
                        graphics.setClip(null);
                    }
                }
            }
            catch (Exception ex) {}
            finally {
                this.a.dz = false;
                d.fv = fv;
                try {
                    if (b2) {
                        if (this.a.tb != null) {
                            this.a.tb.c(8);
                            this.a.tb = null;
                        }
                        if (this.a.ta != null) {
                            this.a.ta.p();
                            this.a.ta = null;
                        }
                    }
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public final void a() {
        this.a = null;
    }
}
