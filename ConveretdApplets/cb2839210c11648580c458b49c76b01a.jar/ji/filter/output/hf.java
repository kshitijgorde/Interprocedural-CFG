// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import java.util.Properties;
import java.awt.Image;
import ji.image.dx;
import ji.filter.tiff.hl;
import ji.filter.tiff.ho;
import ji.filter.tiff.hn;
import ji.filter.tiff.hm;
import java.awt.Component;
import ji.util.d;
import ji.res.ay;
import ji.filter.hk;
import java.awt.image.ImageObserver;
import ji.io.h;
import java.awt.Rectangle;
import ji.v1event.a6;
import ji.util.e;
import ji.encode.fp;
import ji.util.i;
import ji.v1event.af;
import ji.document.ad;
import ji.image.cy;
import ji.io.ac;
import ji.filter.gb;

public class hf
{
    private String a;
    gb b;
    private hg c;
    
    public hf() {
        this.b = null;
    }
    
    public String a() {
        return this.a;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public boolean a(final ac ac, final ac ac2, final String s, final String s2, final cy cy, final ad ad, final boolean b, final boolean b2, final int n, final String s3, final int n2, final int n3, final int n4, final int n5, final boolean b3, final int n6, final int n7, final af af, final boolean b4, final Object o, final boolean b5, final boolean b6, final int n8, final int n9) throws Exception {
        boolean b7 = false;
        boolean b8 = false;
        boolean b9 = false;
        boolean c = i.c(153);
        final boolean c2 = i.c(162);
        int n10 = i.d(5);
        int d = i.d(6);
        boolean c3 = i.c(156);
        boolean c4 = i.c(157);
        boolean a = fp.a(ad);
        if (this.c != null) {
            boolean e = false;
            if (cy != null && cy.d5() == 1) {
                e = cy.cj().e(n);
            }
            int n11;
            if (e) {
                n11 = this.c.b();
            }
            else {
                n11 = this.c.a();
            }
            switch (n11) {
                case 0: {
                    d = 4;
                    c3 = false;
                    c4 = false;
                    a = false;
                    break;
                }
                case 1: {
                    d = 5;
                    c3 = true;
                    c4 = false;
                    a = true;
                    break;
                }
                case 2: {
                    d = 6;
                    c = true;
                    c3 = false;
                    c4 = true;
                    n10 = this.c.c();
                    a = true;
                    break;
                }
                case 3: {
                    d = 6;
                    c = false;
                    c3 = false;
                    c4 = true;
                    n10 = this.c.c();
                    a = true;
                    break;
                }
                default: {
                    throw new jiImageSaveFailedException("Unknown TIFF write type.");
                }
            }
        }
        if (a) {
            cy.cv(false);
        }
        else if (b2 && i.c(160)) {
            if (a) {
                cy.cv(false);
            }
            else {
                cy.cv(true);
            }
        }
        else {
            cy.cv(true);
        }
        if (cy.d5() > 1 && a) {
            cy.cd(true);
        }
        dx dx = cy.p(n);
        final Object ax = dx.ax;
        int width = 0;
        int height = 0;
        boolean b10 = false;
        hk[] array = null;
        int n12 = 0;
        if (!b && n12 == 0 && (ad.bi(2) || ad.cz())) {
            double ef = cy.ef();
            double eg = cy.eg();
            boolean b11 = false;
            if (dx.am > 1 && ad.bi(2) && !ad.bi(0) && !cy.d8()) {
                b11 = true;
            }
            Label_1069: {
                if (b11 || b2 || !cy.d8() || (ad.cz() && cy.cg())) {
                    Image image = null;
                    try {
                        e.ag(s3);
                        if (af != null) {
                            af.a(new a6(this, 9, s3));
                        }
                        cy.ar();
                        image = cy.a(false, false, null, false);
                        if (image == null) {
                            cy.b1();
                            image = cy.a(false, false, null, false);
                            cy.ar();
                        }
                        if (image == null) {
                            h.d(this.a, "Problem burning to tiff (#1b): null image");
                            throw new Exception("Save failed: Unable to obtain image for render");
                        }
                        e.ag(s);
                        if (af != null) {
                            af.a(new a6(this, 9, s));
                        }
                        if (image != null) {
                            final int d2 = cy.d5();
                            dx = cy.r();
                            width = (int)cy.et();
                            height = (int)cy.eq();
                            if (b4) {
                                width = image.getWidth(null);
                                height = image.getHeight(null);
                                dx.m = width;
                                dx.n = height;
                            }
                            else {
                                width = (int)cy.et();
                                height = (int)cy.eq();
                            }
                            boolean b12 = false;
                            if (ef > eg) {
                                width = (int)Math.round((double)width);
                                height = (int)Math.round(height * ef / eg);
                                eg = ef;
                                b12 = true;
                            }
                            else if (eg > ef) {
                                width = (int)Math.round(width * eg / ef);
                                height = (int)Math.round((double)height);
                                ef = eg;
                                b12 = true;
                            }
                            Label_0998: {
                                try {
                                    final hk[] a2 = gb.a(ad, this.a, image, width, height, d2, af, s, s2, dx, b2, ef, eg, b12, c, c2, n10, d, c3, c4, b4);
                                    array = new hk[n4 + 1];
                                    array[n4] = a2[0];
                                    this.b.a(array);
                                    b10 = true;
                                    if (a2[0].d) {
                                        b7 = true;
                                        final boolean f = a2[0].f;
                                    }
                                    if (a2[0].e) {
                                        b8 = true;
                                    }
                                    if (a2[0].g) {
                                        b9 = true;
                                    }
                                    n12 = 1;
                                    break Label_0998;
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                    h.d(this.a, "Problem burning to tiff (#2): ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                                    ji.util.d.a(ex, ay.a(), ad, af, this.a);
                                    throw ex;
                                }
                                throw new Exception("Null image, unable to save");
                            }
                            break Label_1069;
                        }
                        throw new Exception("Null image, unable to save");
                    }
                    finally {
                        try {
                            if (image != null) {
                                image.flush();
                            }
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            if (!cy.d8() && n12 != 0) {
                if (b4) {
                    array[n4].a(new hm(256, 4, width));
                    array[n4].a(new hm(257, 4, height));
                }
                else {
                    array[n4].a(new hm(256, 4, (int)cy.et()));
                    array[n4].a(new hm(257, 4, (int)cy.eq()));
                }
                final hm hm = new hm(282);
                hm.b = 5;
                hm.j = new Double(ef);
                array[n4].a(hm);
                final hm hm2 = new hm(283);
                hm2.b = 5;
                hm2.j = new Double(eg);
                array[n4].a(hm2);
                if (!b7) {
                    array[n4].a(new hm(262, 3, 0));
                }
                final hn ax2 = new hn();
                ax2.d = new ho[dx.v];
                for (int i = 0; i < ax2.d.length; ++i) {
                    ax2.d[i] = new ho();
                    ax2.d[i].b = 0;
                }
                ax2.an = new hl[array[n4].a];
                dx.ax = ax2;
            }
        }
        boolean a3;
        if (cy.d8() || n12 != 0) {
            if (n12 == 0) {
                this.b.a((hk[])null);
            }
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(269, this.a)))).append(" ").append(ji.util.d.c(n6 + 1, n5, this.a)).append("...")));
            if (af != null) {
                af.a(new a6(this, 9, value));
            }
            int n13 = n8;
            if (i.c(248) && cy.r().cu != 0 && cy.r().a4 == 180) {
                n13 = (n13 + cy.r().a4) % 360;
                if (n13 < 0) {
                    n13 += 360;
                }
            }
            int n14 = n9;
            if ((n14 & 0x1) > 0 && (n14 & 0x2) > 0) {
                n14 = 0;
                n13 = (n13 + 180) % 360;
                if (n13 < 0) {
                    n13 += 360;
                }
            }
            if (gb.a(this.a)) {
                return false;
            }
            a3 = this.b.a(ac2, dx, ad, cy.cj(), af, n4, b3, o, b5, b6, n13, n14, b7, b8, b2, b4, b10, b9);
            if (n12 != 0) {
                dx.ax = ax;
            }
        }
        else {
            this.b.a(n4);
            a3 = false;
        }
        return a3;
    }
    
    public void b() {
        try {
            this.b.a();
        }
        catch (Exception ex) {}
        this.b = null;
    }
    
    public String c() {
        return "tif";
    }
    
    public boolean a(final ac ac, final af af, final ad ad, final String s, final boolean[] array) throws Exception {
        return this.b.a(ac, af, ad, s, array);
    }
    
    public boolean a(final int n, final boolean[] array) throws Exception {
        if (this.b == null) {
            this.b = new gb(null, this.a);
        }
        return this.b.a(n, array);
    }
    
    public boolean d() {
        return true;
    }
    
    public void a(final Properties properties) {
        this.c = new hg(this.a, properties);
    }
}
