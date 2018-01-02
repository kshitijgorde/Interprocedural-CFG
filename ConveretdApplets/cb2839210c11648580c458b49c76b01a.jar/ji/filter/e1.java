// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.util.c3;
import ji.io.fg;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import ji.decode.ou;
import ji.image.ev;
import ji.image.c2;
import java.awt.Toolkit;
import ji.image.ds;
import java.awt.Image;
import ji.v1event.a6;
import java.awt.Dimension;
import java.awt.Point;
import java.io.EOFException;
import java.io.Serializable;
import ji.filter.tiff.sa;
import ji.filter.tiff.hm;
import ji.res.s;
import ji.util.e;
import ji.io.gn;
import java.awt.Component;
import ji.filter.tiff.hl;
import ji.filter.tiff.ho;
import ji.util.i;
import java.util.Hashtable;
import ji.io.h;
import ji.wang.ej;
import ji.util.d;
import ji.document.ad;
import ji.v1event.af;
import ji.image.dx;
import ji.annotate.df;
import ji.io.ac;
import ji.awt.c;
import ji.decode.gg;
import ji.decode.gf;
import ji.decode.ge;
import ji.decode.gd;
import ji.decode.gc;
import ji.io.q;
import ji.io.hp;
import ji.filter.tiff.hn;

public class e1 extends cj
{
    private hn a;
    private String b;
    hp c;
    boolean d;
    public static boolean e;
    private q f;
    private gc g;
    private gd h;
    private ge i;
    private gf j;
    private gg k;
    private static boolean l;
    private boolean m;
    private boolean n;
    private String o;
    private Object p;
    private String q;
    private int r;
    String s;
    String t;
    private c u;
    int v;
    
    public e1() throws Exception {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = false;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = 0;
        this.s = null;
        this.t = null;
        this.u = new c("jiFilterTiffTempFiles");
        this.v = 0;
    }
    
    public final String getFilterName() {
        return "jiFilterTIFF";
    }
    
    public void a(final String o) {
        this.o = o;
    }
    
    public df a(final ac ac, final df df, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final int n2) throws Exception {
        try {
            if (df != null) {
                df.a(true);
            }
            final boolean aa = ad.aa();
            if (ji.util.d.b2(aa) == 1 || ji.util.d.b2(aa) == 3) {
                final hp hp = new hp(ac, dx.an);
                if (hp != null) {
                    final hn a = ((hn)dx.ax).a();
                    this.a(hp, Math.max(Math.min(n, this.a.y), 1), a, dx, ad);
                    ej.a(df, ac, a, dx, af, ad, s, n, ji.util.d.b3(aa) == 0 || ji.util.d.b2(aa) == 3, b, n2);
                    hp.a();
                }
            }
        }
        catch (Exception ex) {
            ji.io.h.d(s, "getAnnotations error: ");
            ex.printStackTrace();
        }
        finally {
            if (df != null) {
                try {
                    final boolean aa2 = ad.aa();
                    if (ji.util.d.b2(aa2) == 1 || ji.util.d.b2(aa2) == 3) {
                        df.a(false);
                    }
                }
                catch (Exception ex2) {}
            }
        }
        return df;
    }
    
    public boolean f() {
        return true;
    }
    
    private final void a(final Hashtable hashtable, final String s, final Object o) {
        try {
            if (hashtable.get(s) == null) {
                hashtable.put(s, o);
            }
            else {
                hashtable.remove(s);
                hashtable.put(s, o);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final Hashtable hashtable, final String s, final Object o) {
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" = ").append(o))));
        }
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        final dx a = this.a(fh);
        try {
            if (a != null) {
                for (int i = 0; i < this.u.b(); ++i) {
                    a.cv.c(this.u.b(i));
                }
            }
        }
        catch (Exception ex) {}
        try {
            while (this.u.b() > 0) {
                this.u.d(0);
            }
        }
        catch (Exception ex2) {}
        return a;
    }
    
    private final dx a(final fh fh) throws Exception {
        final long currentTimeMillis = System.currentTimeMillis();
        this.r = fh.h;
        this.p = fh.a;
        this.q = fh.f;
        this.m = fh.i;
        this.s = fh.m;
        this.t = fh.m;
        boolean cs = false;
        dx dx;
        try {
            this.n = true;
            if (this.p != null) {
                if (this.p.toString().indexOf("#") >= 0) {
                    this.r = ji.util.d.d(this.p);
                    fh.h = this.r;
                    cs = true;
                }
                else {
                    try {
                        if (fh.o.ez()) {
                            this.r = 1;
                            cs = true;
                        }
                    }
                    catch (Exception ex4) {}
                }
            }
            if (this.a != null) {
                if (this.b != null && this.b != null && !this.b.equals(this.b)) {}
            }
            this.c = new hp(fh.b);
            if (true) {
                this.b = fh.e;
                this.a = null;
                this.a = new hn();
                fh.b.a(0L);
                this.a.f = 0;
                this.a.e = 0;
                this.a.a = fh.b.l();
                if (this.a.a != 19789 && this.a.a != 18761) {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer("Invalid TIFF marker (").append(Integer.toHexString(this.a.a)).append(")"))));
                    }
                    return null;
                }
                if (this.a.a == 18761) {
                    this.a.e = 1;
                }
                this.a.f = this.a.e;
                this.c.a(this.a.e);
                this.a.g = this.c.g();
                this.a.b = this.c.d();
                if (this.a.b != 42) {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer("Invalid TIFF version (").append(this.a.a).append(")"))));
                    }
                    return null;
                }
                this.a.c = this.c.e();
                int i = this.a.c;
                int n = 0;
                boolean b = false;
                try {
                    while (i > 0) {
                        b = false;
                        this.c.a((long)i);
                        this.c.a((long)(i + 2 + this.c.d() * 12));
                        ++n;
                        b = true;
                        i = this.c.e();
                    }
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                    }
                    if (b) {
                        --n;
                    }
                }
                final int y = n;
                this.a.d = new ho[n];
                int a = this.a.c;
                int n2 = 0;
                try {
                    while (a > 0 && y > n2) {
                        this.c.a((long)a);
                        this.a.d[n2] = new ho();
                        this.a.d[n2].a = a;
                        this.a.d[n2].b = this.c.d();
                        this.c.a((long)(a + 2 + this.a.d[n2].b * 12));
                        ++n2;
                        a = this.c.e();
                    }
                }
                catch (Exception ex5) {}
                this.a.y = y;
            }
            else {
                this.c.a(this.a.e);
                (this.a.an = new hl[1])[0] = new hl();
            }
            this.r = Math.min(this.r, this.a.y);
            this.r = Math.max(this.r, 1);
            dx = new dx();
            dx.bk = new Hashtable();
            dx.bl = new Hashtable();
            this.a.q = 1;
            this.a.v = 1;
            this.a.x = 1;
            this.a.n = 0;
            this.a.o = 0;
            this.a.p = 1;
            this.a.l = false;
            this.a.j = 0;
            this.a.z = ji.util.d.di();
            this.a.aa = 0;
            dx.ay = false;
            try {
                this.a(this.c, this.r, this.a, dx, fh.o);
            }
            catch (Exception ex2) {
                ji.util.d.a(ex2, ji.util.d.b(316, this.o), fh.o, fh.g, this.o);
                this.c.a();
                this.c = null;
                return null;
            }
            if (this.r > this.a.y) {
                this.c.a();
                this.c = null;
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(316, this.o)))).append(" ").append(this.r).append(" ").append(ji.util.d.b(317, this.o)).append(" ").append(this.a.y).append(" ").append(ji.util.d.b(318, this.o)))));
            }
            if (this.a.r <= 0 || this.a.s <= 0 || this.a.an.length <= 0) {
                this.c.a();
                this.c = null;
                if (ji.util.i.c(5)) {
                    ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer("Invalid TIFF sizes (").append(this.a.r).append(", ").append(this.a.s).append(", ").append(this.a.an.length).append(")"))));
                }
                return null;
            }
            if (this.a.j == 7) {
                if (ji.util.i.c(5)) {
                    ji.io.h.d(this.o, "JPEG in TIFF Compression group 7 image detected");
                }
                this.a.j = 6;
                this.a.i = true;
            }
            dx.i = this.a.g;
            if (cs) {
                dx.u = 1;
            }
            else {
                dx.u = this.a.y;
            }
            this.a(dx.bk, this.a(815), "".concat(String.valueOf(String.valueOf(this.a.b))));
            this.b(dx.bl, this.a(816), "".concat(String.valueOf(String.valueOf(this.a.b))));
            if (this.a.an != null && this.a.an.length > 1) {
                this.a(dx.bk, this.a(822), new Integer(this.a.an.length));
            }
            this.a.f = this.a.e;
            this.c.a(this.a.f);
            dx.ap = this.a.j;
            dx.aq = this.a.i;
            dx.k = this.a.m;
            dx.n = this.a.s;
            dx.m = this.a.r;
            dx.x = this.a.n;
            dx.av = this.a.o;
            dx.aw = this.a.p;
            dx.y = this.a.q;
            dx.at = this.a.k;
            dx.ar = this.a.l;
            dx.bh = this.a.z;
            dx.bi = this.a.aa;
            if (this.a.aj != 0 && this.a.ak == 0) {
                dx.b8 = true;
                dx.b3 = this.a.aj;
                dx.b4 = this.a.aj;
            }
            else if (this.a.aj == 0 && this.a.ak != 0) {
                dx.b8 = true;
                dx.b3 = this.a.ak;
                dx.b4 = this.a.ak;
            }
            else if (this.a.aj != 0 && this.a.ak != 0) {
                dx.b8 = true;
                dx.b3 = this.a.aj;
                dx.b4 = this.a.ak;
            }
            if (dx.b8) {
                if (dx.b3 > 0) {
                    dx.b5 = (dx.m + dx.b3 - 1) / dx.b3;
                    this.a(dx.bk, this.a(817), "".concat(String.valueOf(String.valueOf(dx.b5))));
                }
                if (dx.b4 > 0) {
                    dx.b6 = (dx.n + dx.b4 - 1) / dx.b4;
                    this.a(dx.bk, this.a(818), "".concat(String.valueOf(String.valueOf(dx.b6))));
                }
                dx.b7 = dx.b5 * dx.b6;
                this.a(dx.bk, this.a(819), "".concat(String.valueOf(String.valueOf(dx.b7))));
            }
            if (this.a.j == 6 && !ji.util.i.c(87)) {
                this.a.v = 8;
                this.a.x = 3;
            }
            dx.z = this.a.v;
            dx.aa = this.a.x;
            dx.an = this.a.f;
            dx.an = this.a.f;
            dx.ao = dx.an;
            if (this.a.m == 2) {
                dx.ao = (this.a.e | 0x2);
            }
            dx.au = this.a.h;
            switch (this.a.j) {
                case 3: {
                    if ((this.a.k & 0x1) == 0x0 && (this.a.k & 0x5) == 0x0) {
                        dx.l = this.a(862);
                        dx.ap = 2;
                        this.a.j = 2;
                        break;
                    }
                    dx.l = this.a(863);
                    break;
                }
                case 2: {
                    dx.l = this.a(864);
                    break;
                }
                case 4: {
                    dx.l = this.a(865);
                    break;
                }
                case 1: {
                    dx.l = this.a(866);
                    break;
                }
                case 32773: {
                    dx.l = this.a(867);
                    break;
                }
                case 6: {
                    dx.l = this.a(868);
                    break;
                }
                case 5: {
                    dx.l = this.a(869);
                    break;
                }
                default: {
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(837)))).append(" ").append(this.a.j).append(" ").append(ji.util.d.b(319, this.o)))));
                }
            }
            dx.ax = this.a.a();
            String s;
            if (dx.b8) {
                s = this.a(871);
            }
            else {
                s = this.a(872);
            }
            try {
                if (this.a.i) {
                    s = String.valueOf(String.valueOf(s)).concat("7");
                }
                else {
                    s = String.valueOf(String.valueOf(s)).concat("6");
                }
            }
            catch (Exception ex6) {}
            if (dx.b8) {
                dx.l = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" ").append(dx.l)));
            }
            else {
                dx.l = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" ").append(dx.l)));
            }
            dx.am = Math.min(this.a.v * this.a.x, 24);
            if (this.a.v > 1 || this.a.x > 1) {
                if (this.a.v == 16) {
                    ji.io.h.d(this.o, "TIFF-JPEG: 16: fhCompNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" (").append(this.a.v).append("/").append(this.a.x).append(") ").append(ji.util.d.b(319, this.o)))));
                }
                if (this.a.v > 32 && this.a.j != 1) {
                    ji.io.h.d(this.o, "TIFF-JPEG: 24: fhCompNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" (").append(this.a.v).append("/").append(this.a.x).append(") ").append(ji.util.d.b(319, this.o)))));
                }
                if (this.a.n == 3 && this.a.j == 6) {
                    ji.io.h.d(this.o, "TIFF-JPEG: 3: fhPalleteNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" ").append(ji.util.d.b(328, this.o)))));
                }
                if (this.a.n == 4) {
                    ji.io.h.d(this.o, "TIFF-COLOR: 4: fhMaskNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" ").append(ji.util.d.b(329, this.o)))));
                }
                if (this.a.x > 3 && this.a.j != 1 && this.a.j != 5 && this.a.j != 32773) {
                    ji.io.h.d(this.o, "TIFF-COLOR: >3 fhExtraNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" ").append(ji.util.d.b(331, this.o)))));
                }
                if (this.a.o > 0 && this.a.j != 5) {
                    ji.io.h.d(this.o, "TIFF-COLOR: >0 fhExtraNotSupported");
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" ").append(ji.util.d.b(331, this.o)))));
                }
                if (this.a.w != null) {
                    for (int j = 0; j < this.a.w.length; ++j) {
                        if (this.a.w[j] != 8) {
                            throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dx.l))).append(" (").append(this.a.w[j]).append("/").append(this.a.x).append(") ").append(ji.util.d.b(332, this.o)))));
                        }
                    }
                }
            }
            this.c.a();
            this.c = null;
            if (dx.l.toLowerCase().indexOf(this.a(869).toLowerCase()) >= 0) {
                ji.util.d.a0(true);
                if (!ji.util.i.c(3)) {
                    throw new gn(String.valueOf(String.valueOf(new StringBuffer("TIFF_LZW - ").append(ji.util.d.b(630, this.o)).append("\n\n ").append(ji.util.d.b(631, this.o)))));
                }
                if (!ji.util.d.dx()) {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.o, "Unable to load image due to no disc access (certificate denied?)");
                    }
                    ji.util.d.b(ji.util.d.b(361, this.o), ji.util.d.b(362, this.o), fh.g, this.o);
                    ji.util.e.e(this.a(873));
                    return null;
                }
            }
            else if (dx.l.toLowerCase().equals(this.a(867).toLowerCase())) {
                if (!ji.util.d.dx()) {
                    ji.util.d.b(ji.util.d.b(363, this.o), String.valueOf(String.valueOf(ji.util.d.b(364, this.o))).concat(String.valueOf(String.valueOf(ji.res.s.b(this.o, true)))), fh.g, this.o);
                    dx = null;
                }
            }
            else if (dx.l.toLowerCase().equals(this.a(866).toLowerCase()) && !ji.util.d.dx()) {
                ji.util.d.b(ji.util.d.b(365, this.o), String.valueOf(String.valueOf(ji.util.d.b(366, this.o))).concat(String.valueOf(String.valueOf(ji.res.s.b(this.o, true)))), fh.g, this.o);
                dx = null;
            }
            try {
                if (dx.ab == 3) {
                    dx.ac /= 2.54;
                    dx.ad /= 2.54;
                }
            }
            catch (Exception ex7) {}
            try {
                if (ji.util.d.bn()) {
                    if (dx.ac > 0 && dx.ad > 0 && (dx.ad / dx.ac > 500 || dx.ac / dx.ad > 500)) {
                        dx.ac = 100.0;
                        dx.ad = 100.0;
                    }
                    if (dx.ac <= 1.0) {
                        dx.ac = 100.0;
                    }
                    if (dx.ad <= 1.0) {
                        dx.ad = 100.0;
                    }
                }
            }
            catch (Exception ex8) {}
            try {
                if (dx.ac > 100000) {
                    dx.ac = 100.0;
                }
                if (dx.ad > 100000) {
                    dx.ad = 100.0;
                }
            }
            catch (Exception ex9) {}
            boolean b2 = false;
            boolean b3 = false;
            if (dx.bk.get(this.a(825)) != null) {
                b2 = true;
            }
            if (dx.bk.get(this.a(824)) != null) {
                b3 = true;
            }
            if (!b2 || !b3) {
                if (b2) {
                    dx.ac = dx.ad;
                }
                if (b3) {
                    dx.ad = dx.ac;
                }
            }
            dx.a = 1;
            dx.az = (this.a.y > 1);
            dx.cs = cs;
            if (dx != null && ji.util.d.dv()) {
                ji.io.h.e(this.o, "Tiff header analysed in ".concat(String.valueOf(String.valueOf(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
            throw ex3;
        }
        if (!ji.util.d.an(fh.o.ak()) && ji.util.i.c(248) && dx.a4 > 0) {
            dx.cu = -dx.a4;
        }
        if (dx == null) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.o, "Returning null header");
            }
        }
        else {
            boolean b4 = true;
            if (fh.ab && !fh.o.kn().g()) {
                b4 = false;
            }
            if (b4 && !fh.w) {
                if (!fh.w && fh.o.kq() && dx.am == 1) {
                    dx.a(fh.o.kr());
                    dx.ae = dx.ac;
                    dx.af = dx.ad;
                    dx.o = dx.m;
                    dx.p = dx.n;
                    final dx dx2 = dx;
                    dx2.m *= dx.d();
                    final dx dx3 = dx;
                    dx3.n *= dx.d();
                }
                else if (dx.am == 1 && ji.util.i.c(209) && ji.util.i.c(224) && (dx.ac < 150 || dx.ad < 150) && fh.o.el() && fh.o.ek() == 0) {
                    dx.a(2);
                    dx.ae = dx.ac;
                    dx.af = dx.ad;
                    dx.o = dx.m;
                    dx.p = dx.n;
                    final dx dx4 = dx;
                    dx4.m *= dx.d();
                    final dx dx5 = dx;
                    dx5.n *= dx.d();
                }
            }
        }
        if (ji.util.d.b() && dx != null) {
            dx.a6 = "tiff";
            dx.a7 = "image/tiff";
        }
        return dx;
    }
    
    private final String a(final int n) {
        return ji.util.d.b(n, this.o);
    }
    
    public final boolean e() {
        return true;
    }
    
    private final void a(final hp hp, final hm hm, final hn hn, final Object o) throws Exception {
        Label_0522: {
            if (hm.c * sa.a[hm.b] <= 4) {
                switch (hm.b) {
                    case 1: {
                        if ((hn.f & 0x1) > 0) {
                            switch (hm.c) {
                                case 4: {
                                    hm.e = (hm.d & 0xFF);
                                    break Label_0522;
                                }
                                case 3: {
                                    hm.e = (hm.d >> 8 & 0xFF);
                                    break Label_0522;
                                }
                                case 2: {
                                    hm.e = (hm.d >> 16 & 0xFF);
                                    break Label_0522;
                                }
                                default: {
                                    hm.e = (hm.d >> 24 & 0xFF);
                                    break Label_0522;
                                }
                            }
                        }
                        else {
                            switch (hm.c) {
                                case 4: {
                                    hm.e = (hm.d >> 24 & 0xFF);
                                    break Label_0522;
                                }
                                case 3: {
                                    hm.e = (hm.d >> 16 & 0xFF);
                                    break Label_0522;
                                }
                                case 2: {
                                    hm.e = (hm.d >> 8 & 0xFF);
                                    break Label_0522;
                                }
                                default: {
                                    hm.e = (hm.d & 0xFF);
                                    break Label_0522;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                        if ((hn.f & 0x1) > 0) {
                            switch (hm.c) {
                                case 2: {
                                    hm.j = new Integer[2];
                                    ((Integer[])hm.j)[0] = new Integer(hm.d & 0xFFFF);
                                    ((Integer[])hm.j)[1] = new Integer(hm.d >> 16 & 0xFFFF);
                                    break Label_0522;
                                }
                                default: {
                                    hm.e = (hm.d & 0xFFFF);
                                    break Label_0522;
                                }
                            }
                        }
                        else {
                            switch (hm.c) {
                                case 2: {
                                    hm.j = new Integer[2];
                                    ((Integer[])hm.j)[0] = new Integer(hm.d >> 16 & 0xFFFF);
                                    ((Integer[])hm.j)[1] = new Integer(hm.d & 0xFFFF);
                                    break Label_0522;
                                }
                                default: {
                                    hm.e = (hm.d >> 16 & 0xFFFF);
                                    break Label_0522;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        hm.e = hm.d;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < sa.b.length; ++i) {
                    if (sa.b[i] == hm.a) {
                        hm.j = this.a(hp, hm, o);
                        break;
                    }
                }
            }
        }
    }
    
    private final Object a(final hp hp, final hm hm, final Object o) throws Exception {
        Object trim = null;
        final long f = hp.f();
        if (hm.d < 0) {
            return null;
        }
        hp.a((long)hm.d);
        switch (hm.b) {
            case 1: {
                Serializable[] array;
                if (hm.a == 32932) {
                    array = new String[] { null };
                    final byte[] array2 = new byte[hm.c];
                    hp.a(array2);
                    if (this.f == null) {
                        this.f = ji.io.q.a(o, this.o);
                    }
                    final String n = this.f.n();
                    final ac ac = new ac(n, true, false, 0, o, this.o);
                    ac.b(array2);
                    ac.a(o);
                    array[0] = n;
                    this.u.c(n);
                }
                else {
                    array = new Integer[hm.c];
                    for (int i = 0; i < hm.c; ++i) {
                        array[i] = new Integer(hp.c());
                    }
                }
                trim = array;
                break;
            }
            case 2: {
                final int n2 = hm.c * sa.a[hm.b];
                final byte[] array3 = new byte[n2];
                hp.a(array3, 0, n2);
                trim = new String(array3).trim();
                break;
            }
            case 3: {
                final Integer[] array4 = new Integer[hm.c];
                for (int j = 0; j < hm.c; ++j) {
                    array4[j] = new Integer(hp.d());
                }
                trim = array4;
                break;
            }
            case 4: {
                boolean b = true;
                if (hm.c > 100) {
                    try {
                        if (this.f == null) {
                            this.f = ji.io.q.a(o, this.o);
                        }
                        final String n3 = this.f.n();
                        final byte[] array5 = new byte[hm.c * 4];
                        final ac ac2 = new ac(n3, true, false, 0, o, this.o);
                        hp.a(array5);
                        ac2.b(array5);
                        ac2.a(o);
                        final ac ac3 = new ac(n3, false, true, array5.length, o, this.o);
                        final hp hp2 = new hp(ac3);
                        hp2.a(hp.b());
                        final Integer[] array6 = new Integer[hm.c];
                        for (int k = 0; k < hm.c; ++k) {
                            array6[k] = new Integer(hp2.e());
                        }
                        trim = array6;
                        hp2.a();
                        ac3.a(o);
                        ac.c(n3, this.o);
                        b = false;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (b) {
                    final Integer[] array7 = new Integer[hm.c];
                    for (int l = 0; l < hm.c; ++l) {
                        array7[l] = new Integer(hp.e());
                    }
                    trim = array7;
                    break;
                }
                break;
            }
            default: {
                if (hm.c > 1) {
                    double n4 = hp.e();
                    final int e = hp.e();
                    if (e > 0) {
                        n4 /= e;
                    }
                    trim = new Double(n4);
                    break;
                }
                double n5 = hp.e();
                final int e2 = hp.e();
                if (e2 > 0) {
                    n5 /= e2;
                }
                trim = new Double(n5);
                break;
            }
        }
        hp.a(f);
        return trim;
    }
    
    private final void a(final hp hp, final int n, final hn hn, final dx dx, final Object o) throws Exception {
        if (hn.d.length == 0) {
            return;
        }
        hn.ao = null;
        final ho ho = hn.d[n - 1];
        hp.a((long)(ho.a + 2));
        for (int i = 0; i < ho.b; ++i) {
            try {
                final hm hm = new hm();
                hm.a = hp.d();
                hm.b = hp.d();
                if (hm.b == 7) {
                    hm.b = 1;
                }
                else if (hm.b > 5) {
                    hm.b = 0;
                }
                hm.c = hp.e();
                hm.d = hp.e();
                try {
                    this.a(hp, hm, hn, o);
                    this.a(hm, hn, dx, (int)hp.g(), hp);
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                    }
                }
            }
            catch (Exception ex2) {
                if (!(ex2 instanceof EOFException)) {
                    throw ex2;
                }
            }
        }
        if (hn.am <= 0) {
            hn.am = hn.s;
        }
        int n2 = 0;
        for (int j = 0; j < hn.an.length; ++j) {
            hn.an[j].c = Math.min(hn.am, hn.s - n2);
            n2 += hn.am;
        }
    }
    
    private void a(final dx dx, final hn hn) {
        int i = 1;
        int length = hn.an.length;
        if (!dx.b8) {
            while (i != 0) {
                i = 0;
                for (int j = 1; j < length; ++j) {
                    if (hn.an[j].a == hn.an[j - 1].a + hn.an[j - 1].b) {
                        final hl hl = hn.an[j - 1];
                        hl.b += hn.an[j].b;
                        final hl hl2 = hn.an[j - 1];
                        hl2.c += hn.an[j].c;
                        if (hn.an[j - 1].c > dx.n) {
                            hn.an[j - 1].c = dx.n;
                        }
                        for (int k = j; k < length - 1; ++k) {
                            hn.an[k].b = hn.an[k + 1].b;
                            hn.an[k].a = hn.an[k + 1].a;
                            hn.an[k].c = hn.an[k + 1].c;
                        }
                        --length;
                        i = 1;
                        break;
                    }
                }
            }
            if (length != hn.an.length) {
                final hl[] array = new hl[length];
                for (int l = 0; l < length; ++l) {
                    array[l] = new hl();
                    array[l].b = hn.an[l].b;
                    array[l].a = hn.an[l].a;
                    array[l].c = hn.an[l].c;
                }
                hn.an = null;
                hn.an = new hl[length];
                for (int n = 0; n < length; ++n) {
                    hn.an[n] = new hl();
                    hn.an[n].b = array[n].b;
                    hn.an[n].a = array[n].a;
                    hn.an[n].c = array[n].c;
                }
            }
        }
    }
    
    private final void a(final hm hm, final hn hn, final dx dx, final int n, final hp hp) throws Exception {
        if (hm.j == null) {
            try {
                String a = hm.a(this.o);
                if (a == null) {
                    a = "Undefined";
                }
                this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer("TIFF: (").append(hm.a).append(") ").append(a))), new Integer(hm.e));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            switch (hm.a) {
                case 296: {
                    dx.ab = hm.e;
                    break;
                }
                case 282: {
                    if (hm.e > 0) {
                        if (ji.util.d.bn()) {
                            dx.ac = hm.e;
                            if (dx.ac <= 1.0) {
                                dx.ac = 100.0;
                            }
                        }
                        this.a(dx.bk, this.a(824), new Integer(hm.e));
                        break;
                    }
                    break;
                }
                case 513: {
                    if (hm.e >= 0) {
                        hn.ab = hm.e;
                        break;
                    }
                    break;
                }
                case 514: {
                    if (hm.e >= 0) {
                        hn.ac = hm.e;
                        break;
                    }
                    break;
                }
                case 519: {
                    if (hm.e >= 0) {
                        hn.ad = hm.e;
                        break;
                    }
                    break;
                }
                case 520: {
                    if (hm.e >= 0) {
                        hn.ae = hm.e;
                        break;
                    }
                    break;
                }
                case 521: {
                    if (hm.e >= 0) {
                        hn.af = hm.e;
                        break;
                    }
                    break;
                }
                case 283: {
                    if (hm.e > 0) {
                        if (ji.util.d.bn()) {
                            dx.ad = hm.e;
                            if (dx.ac <= 1.0) {
                                dx.ad = 100.0;
                            }
                        }
                        this.a(dx.bk, this.a(825), new Integer(hm.e));
                        break;
                    }
                    break;
                }
                case 1: {
                    this.a(dx.bk, this.a(826), this.a(827));
                    dx.a4 = 0;
                    dx.a5 = 0;
                    break;
                }
                case 2: {
                    this.a(dx.bk, this.a(826), this.a(828));
                    dx.a4 = 0;
                    dx.a5 = 1;
                    break;
                }
                case 3: {
                    this.a(dx.bk, this.a(826), this.a(829));
                    dx.a4 = 180;
                    dx.a5 = 0;
                    break;
                }
                case 4: {
                    this.a(dx.bk, this.a(826), this.a(830));
                    dx.a4 = 0;
                    dx.a5 = 2;
                    break;
                }
                case 5: {
                    this.a(dx.bk, this.a(826), this.a(831));
                    dx.a4 = 90;
                    dx.a5 = 1;
                    break;
                }
                case 6: {
                    this.a(dx.bk, this.a(826), this.a(832));
                    dx.a4 = 270;
                    break;
                }
                case 7: {
                    this.a(dx.bk, this.a(826), this.a(833));
                    dx.a4 = 270;
                    dx.a5 = 1;
                    break;
                }
                case 8: {
                    this.a(dx.bk, this.a(826), this.a(834));
                    dx.a4 = 90;
                    dx.a5 = 0;
                    break;
                }
                case 274: {
                    if (hm.e >= 0) {
                        int a2 = 0;
                        int a3 = 0;
                        switch (hm.e) {
                            case 1: {
                                this.a(dx.bk, this.a(826), this.a(827));
                                a3 = 0;
                                a2 = 0;
                                break;
                            }
                            case 2: {
                                this.a(dx.bk, this.a(826), this.a(828));
                                a3 = 0;
                                a2 = 1;
                                break;
                            }
                            case 3: {
                                this.a(dx.bk, this.a(826), this.a(829));
                                a3 = 180;
                                a2 = 0;
                                break;
                            }
                            case 4: {
                                this.a(dx.bk, this.a(826), this.a(830));
                                a3 = 0;
                                a2 = 2;
                                break;
                            }
                            case 5: {
                                this.a(dx.bk, this.a(826), this.a(831));
                                a3 = 270;
                                a2 = 1;
                                break;
                            }
                            case 6: {
                                this.a(dx.bk, this.a(826), this.a(832));
                                a3 = 90;
                                a2 = 0;
                                break;
                            }
                            case 7: {
                                this.a(dx.bk, this.a(826), this.a(833));
                                a3 = 90;
                                a2 = 1;
                                break;
                            }
                            case 8: {
                                this.a(dx.bk, this.a(826), this.a(834));
                                a3 = 270;
                                a2 = 0;
                                break;
                            }
                            default: {
                                if (hm.e < 90) {
                                    a3 = 0;
                                    break;
                                }
                                if (hm.e < 180) {
                                    a3 = 90;
                                    break;
                                }
                                if (hm.e < 270) {
                                    a3 = 180;
                                    break;
                                }
                                if (hm.e < 360) {
                                    a3 = 270;
                                    break;
                                }
                                a3 = 0;
                                break;
                            }
                        }
                        dx.a4 = a3;
                        dx.a5 = a2;
                        break;
                    }
                    break;
                }
                case 256: {
                    this.a(dx.bk, this.a(835), new Integer(hm.e));
                    hn.r = hm.e;
                    hn.t = hm.e;
                    break;
                }
                case 257: {
                    this.a(dx.bk, this.a(836), new Integer(hm.e));
                    hn.s = hm.e;
                    hn.u = hm.e;
                    break;
                }
                case 259: {
                    hn.j = hm.e;
                    break;
                }
                case 278: {
                    if (hm.e <= 0) {
                        hn.am = dx.n;
                    }
                    else {
                        hn.am = hm.e;
                    }
                    if (hn.an != null && hn.am > 0 && hn.an.length > 1) {
                        this.a(dx.bk, this.a(821), new Integer(hn.am));
                        break;
                    }
                    break;
                }
                case 266: {
                    this.a(dx.bk, this.a(838), new Integer(hm.e));
                    hn.m = hm.e;
                    break;
                }
                case 262: {
                    switch (hm.e) {
                        case 1: {
                            this.a(dx.bk, this.a(839), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(hm.e))).append(" (").append(this.a(840)).append(")"))));
                            break;
                        }
                        case 2: {
                            this.a(dx.bk, this.a(839), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(hm.e))).append(" (").append(this.a(841)).append(")"))));
                            break;
                        }
                        case 3: {
                            this.a(dx.bk, this.a(839), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(hm.e))).append(" (").append(this.a(842)).append(")"))));
                            break;
                        }
                        default: {
                            this.a(dx.bk, this.a(839), new Integer(hm.e));
                            break;
                        }
                    }
                    this.a(dx.bk, this.a(839), new Integer(hm.e));
                    hn.n = hm.e;
                    break;
                }
                case 338: {
                    this.a(dx.bk, this.a(843), new Integer(hm.e));
                    hn.o = hm.e;
                    break;
                }
                case 317: {
                    this.a(dx.bk, this.a(844), new Integer(hm.e));
                    hn.p = hm.e;
                    break;
                }
                case 291: {
                    if (this.d) {
                        this.a(dx.bk, this.a(845), new Integer(hm.e));
                        break;
                    }
                    break;
                }
                case 290: {
                    if (this.d) {
                        this.a(dx.bk, this.a(846), new Integer(hm.e));
                        break;
                    }
                    break;
                }
                case 284: {
                    this.a(dx.bk, this.a(847), new Integer(hm.e));
                    hn.q = hm.e;
                    break;
                }
                case 258: {
                    this.a(dx.bk, this.a(848), new Integer(hm.e));
                    hn.v = hm.e;
                    break;
                }
                case 277: {
                    this.a(dx.bk, this.a(849), new Integer(hm.e));
                    hn.x = hm.e;
                    break;
                }
                case 279:
                case 325: {
                    hn.an[0].b = hm.e;
                    hn.an[0].b = Math.min(hn.an[0].b, n);
                    break;
                }
                case 322: {
                    this.a(dx.bk, this.a(851), new Integer(hm.e));
                    hn.aj = hm.e;
                    hn.t = hm.e;
                    break;
                }
                case 323: {
                    this.a(dx.bk, this.a(852), new Integer(hm.e));
                    hn.ak = hm.e;
                    hn.t = hm.e;
                    break;
                }
                case 273:
                case 324: {
                    this.a(dx.bk, this.a(853), new Integer(hm.e));
                    hn.an[0].a = hm.e;
                    break;
                }
                case 292: {
                    this.a(dx.bk, this.a(854), new Integer(hm.e));
                    hn.k = hm.e;
                    hn.l = true;
                    break;
                }
                case 293: {
                    this.a(dx.bk, this.a(855), new Integer(hm.e));
                    hn.k = hm.e;
                    hn.l = true;
                    break;
                }
                default: {
                    String s = hm.a(this.o);
                    if (s == null) {
                        s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(856)))).append(" (").append(hm.a).append(")")));
                    }
                    this.a(dx.bk, s, "".concat(String.valueOf(String.valueOf(hm.e))));
                    break;
                }
            }
        }
        else {
            String s2 = null;
            try {
                s2 = hm.a(this.o);
                if (s2 == null) {
                    s2 = this.a(857);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            Label_2845: {
                switch (hm.a) {
                    case 32768: {
                        break;
                    }
                    case 270: {
                        try {
                            final String s3 = (String)hm.j;
                            this.a(dx.bk, this.a(858), s3);
                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer("TIFF: (").append(hm.a).append(") ").append(s2))), s3);
                            hn.aa = ji.util.d.di();
                            if (hn.aa == 1) {
                                if (s3.toLowerCase().indexOf("black") >= 0) {
                                    hn.aa = 0;
                                }
                                else if (s3.toLowerCase().indexOf("cyan") >= 0) {
                                    hn.aa = 1;
                                }
                                else if (s3.toLowerCase().indexOf("magenta") >= 0) {
                                    hn.aa = 2;
                                }
                                else {
                                    hn.aa = 3;
                                }
                            }
                        }
                        catch (Exception ex3) {}
                        break;
                    }
                    case 32932: {
                        if (hn.ap == null && hn.ao == null) {
                            try {
                                Block_84: {
                                    for (int i = 0; i < hm.c; ++i) {
                                        if (((Object[])hm.j)[i] instanceof String) {
                                            break Block_84;
                                        }
                                        if (hn.ao == null) {
                                            hn.ao = new int[hm.c];
                                        }
                                        hn.ao[i] = (int)((Object[])hm.j)[i];
                                        ((Object[])hm.j)[i] = null;
                                    }
                                    break;
                                }
                                int i = 0;
                                hn.ap = (String)((Object[])hm.j)[i];
                                ((Object[])hm.j)[i] = null;
                                break;
                            }
                            catch (Exception ex4) {
                                break;
                            }
                            break Label_2845;
                        }
                        break;
                    }
                    case 279:
                    case 325: {
                        if (hn.an.length < hm.c) {
                            hn.an = new hl[hm.c];
                            for (int j = 0; j < hm.c; ++j) {
                                hn.an[j] = new hl();
                            }
                        }
                        for (int k = 0; k < hm.c; ++k) {
                            hn.an[k].b = (int)((Object[])hm.j)[k];
                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2).append(":").append(k))), "".concat(String.valueOf(String.valueOf(hn.an[k].b))));
                            hn.an[k].b = Math.min(hn.an[k].b, n);
                        }
                        break;
                    }
                    case 273:
                    case 324: {
                        if (hn.an.length < hm.c) {
                            hn.an = new hl[hm.c];
                            for (int l = 0; l < hm.c; ++l) {
                                hn.an[l] = new hl();
                            }
                            if (this.d) {
                                this.a(dx.bk, this.a(822), new Integer(hm.c));
                            }
                        }
                        for (int n2 = 0; n2 < hm.c; ++n2) {
                            hn.an[n2].a = (int)((Object[])hm.j)[n2];
                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2).append(":").append(n2))), "".concat(String.valueOf(String.valueOf(hn.an[n2].a))));
                        }
                        break;
                    }
                    case 347: {
                        if (hm.j instanceof Object[]) {
                            hn.al = new byte[hm.c];
                            final Integer[] array = (Integer[])hm.j;
                            for (int n3 = 0; n3 < hm.c; ++n3) {
                                hn.al[n3] = (byte)(Object)array[n3];
                            }
                            break;
                        }
                        break;
                    }
                    default: {
                        if (hm.a == 258) {
                            hn.v = 0;
                            hn.w = new int[hm.c];
                            for (int n4 = 0; n4 < hm.c; ++n4) {
                                hn.w[n4] = ji.util.d.c(((Object[])hm.j)[n4].toString(), 0);
                                hn.v += hn.w[n4];
                                this.a(dx.bk, String.valueOf(String.valueOf(this.a(848))).concat(String.valueOf(String.valueOf(n4))), new Integer(hn.w[n4]));
                            }
                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2))), "".concat(String.valueOf(String.valueOf(new Integer(hn.v)))));
                            break;
                        }
                        if (hm.a == 320) {
                            if (this.d) {
                                this.a(dx.bk, this.a(860), this.a(861));
                            }
                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2))), String.valueOf(String.valueOf(new StringBuffer("-").append(this.a(861)).append("-"))));
                            if (hm.j instanceof Object[]) {
                                hn.h = new short[hm.c];
                                for (int n5 = 0; n5 < hm.c; ++n5) {
                                    hn.h[n5] = (short)ji.util.d.c(((Object[])hm.j)[n5].toString(), 0);
                                }
                                break;
                            }
                            break;
                        }
                        else {
                            if (hm.a == 282) {
                                boolean b = true;
                                try {
                                    double doubleValue = Double.valueOf(hm.j.toString());
                                    if (doubleValue > 24000.0 || doubleValue < 1.0) {
                                        b = false;
                                    }
                                    if (b) {
                                        if (doubleValue <= 1.0) {
                                            doubleValue = 100.0;
                                        }
                                        if (ji.util.d.bn()) {
                                            dx.ac = doubleValue;
                                        }
                                        this.a(dx.bk, this.a(824), "".concat(String.valueOf(String.valueOf(doubleValue))));
                                    }
                                    this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2))), "".concat(String.valueOf(String.valueOf(doubleValue))));
                                }
                                catch (Exception ex5) {}
                                break;
                            }
                            if (hm.a == 283) {
                                boolean b2 = true;
                                try {
                                    double doubleValue2 = Double.valueOf(hm.j.toString());
                                    if (doubleValue2 > 24000.0 || doubleValue2 < 1.0) {
                                        b2 = false;
                                    }
                                    if (b2) {
                                        if (doubleValue2 <= 1.0) {
                                            doubleValue2 = 100.0;
                                        }
                                        if (ji.util.d.bn()) {
                                            dx.ad = doubleValue2;
                                        }
                                        this.a(dx.bk, this.a(825), "".concat(String.valueOf(String.valueOf(doubleValue2))));
                                    }
                                    this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2))), "".concat(String.valueOf(String.valueOf(doubleValue2))));
                                }
                                catch (Exception ex6) {}
                                break;
                            }
                            if (hm.a == 519) {
                                final String a4 = hm.a(this.o);
                                if (hm.j instanceof Object[]) {
                                    if (e1.e) {
                                        hn.ag = new byte[hm.c][64];
                                    }
                                    for (int n6 = 0; n6 < hm.c; ++n6) {
                                        final Object o = ((Object[])hm.j)[n6];
                                        if (o instanceof Integer) {
                                            if (e1.e) {
                                                final int intValue = (int)o;
                                                final long f = hp.f();
                                                hp.a((long)intValue);
                                                hp.a(hn.ag[n6]);
                                                hp.a(f);
                                            }
                                            this.a(dx.bk, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a4))).append(":").append(n6))), o);
                                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(a4).append(":").append(n6))), "".concat(String.valueOf(String.valueOf(o))));
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            else if (hm.a == 520) {
                                final String a5 = hm.a(this.o);
                                if (hm.j instanceof Object[]) {
                                    if (e1.e) {
                                        hn.ah = new byte[hm.c][33];
                                    }
                                    for (int n7 = 0; n7 < hm.c; ++n7) {
                                        final Object o2 = ((Object[])hm.j)[n7];
                                        if (o2 instanceof Integer) {
                                            if (e1.e) {
                                                final int intValue2 = (int)o2;
                                                final long f2 = hp.f();
                                                hp.a((long)intValue2);
                                                hp.a(hn.ah[n7]);
                                                hp.a(f2);
                                            }
                                            this.a(dx.bk, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a5))).append(":").append(n7))), o2);
                                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(a5).append(":").append(n7))), "".concat(String.valueOf(String.valueOf(o2))));
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            else if (hm.a == 521) {
                                final String a6 = hm.a(this.o);
                                if (hm.j instanceof Object[]) {
                                    if (e1.e) {
                                        hn.ai = new byte[hm.c][272];
                                    }
                                    for (int n8 = 0; n8 < hm.c; ++n8) {
                                        final Object o3 = ((Object[])hm.j)[n8];
                                        if (o3 instanceof Integer) {
                                            if (e1.e) {
                                                final int intValue3 = (int)o3;
                                                final long f3 = hp.f();
                                                hp.a((long)intValue3);
                                                hp.a(hn.ai[n8]);
                                                hp.a(f3);
                                            }
                                            this.a(dx.bk, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a6))).append(":").append(n8))), o3);
                                            this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(a6).append(":").append(n8))), "".concat(String.valueOf(String.valueOf(o3))));
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            else {
                                if (hm.a == 290 || hm.a == 291) {
                                    this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s2))), "".concat(String.valueOf(String.valueOf(hm.j))));
                                    break;
                                }
                                String s4 = hm.a(this.o);
                                if (s4 == null) {
                                    s4 = this.a(856);
                                }
                                if (hm.j instanceof Object[]) {
                                    for (int n9 = 0; n9 < hm.c; ++n9) {
                                        this.a(dx.bk, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s4))).append(":").append(n9))), ((Object[])hm.j)[n9]);
                                        this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s4).append(":").append(n9))), "".concat(String.valueOf(String.valueOf(((Object[])hm.j)[n9]))));
                                    }
                                    break;
                                }
                                this.a(dx.bk, s4, hm.j);
                                this.b(dx.bl, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(872)))).append(": (").append(hm.a).append(") ").append(s4))), "".concat(String.valueOf(String.valueOf(hm.j))));
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        int[] array = null;
        final hn hn = (hn)dx.ax;
        try {
            if (hn.n == 3) {
                array = new int[hn.h.length / 3];
                int n = 0;
                int i = 0;
                try {
                    for (i = 0; i < hn.h.length / 3; ++i) {
                        array[n++] = (hn.h[i] & 0xFF00) >> 8 << 16;
                    }
                }
                catch (Exception ex2) {}
                int n2 = 0;
                try {
                    while (i < 2 * hn.h.length / 3) {
                        final int n3 = (hn.h[i] & 0xFF00) >> 8;
                        final int[] array2 = array;
                        final int n4 = n2++;
                        array2[n4] |= n3 << 8;
                        ++i;
                    }
                }
                catch (Exception ex3) {}
                int n5 = 0;
                try {
                    while (i < hn.h.length) {
                        final int n6 = (hn.h[i] & 0xFF00) >> 8;
                        final int[] array3 = array;
                        final int n7 = n5++;
                        array3[n7] |= n6;
                        ++i;
                    }
                }
                catch (Exception ex4) {}
                try {
                    int n8 = 0;
                    while (i < array.length) {
                        final int[] array4 = array;
                        final int n9 = n8;
                        array4[n9] |= 0xFF000000;
                        ++n8;
                    }
                }
                catch (Exception ex5) {}
            }
        }
        catch (Exception ex) {
            ji.io.h.d(s, "TIFF Filter palllet error: ");
            ex.printStackTrace();
        }
        return array;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        super.b = false;
        if (!this.n) {
            throw new Exception(ji.util.d.b(335, this.o));
        }
        super.c = true;
        try {
            int n = 0;
            q a = null;
            ac ac = null;
            ac ac2 = null;
            ac ac3 = null;
            String s = null;
            Image image = null;
            final ac b = fh.b;
            final hn hn = (hn)fh.d.ax;
            final int b2 = this.b(fh);
            switch (hn.j) {
                case 2: {
                    if (b2 < 2 && fh.o != null && fh.o.bi(45)) {
                        this.a(fh.d, hn);
                        break;
                    }
                    break;
                }
            }
            if (b2 < 2) {
                switch (hn.j) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 32773: {
                        fh.c.b(1, true, fh.o);
                        break;
                    }
                }
            }
            else if (hn.j == 32773) {
                if (b2 == 4) {
                    fh.c.b(2, true, fh.o);
                }
                else if (b2 == 8) {
                    fh.c.b(3, false, fh.o);
                }
                else {
                    fh.c.b(4, false, fh.o);
                }
            }
            else if (b2 == 4) {
                fh.c.b(2, true, fh.o);
            }
            else if (b2 == 8) {
                boolean b3 = true;
                if (fh.c.m() * fh.c.n() > ji.util.d.i()) {
                    b3 = false;
                }
                ji.io.h.c(this.o, "TIFF Filter: Greyscale, using blockWrite = ".concat(String.valueOf(String.valueOf(b3))));
                fh.c.b(3, b3, fh.o);
            }
            else {
                fh.c.b(4, false, fh.o);
            }
            try {
                fh.c.a(fh.d.x);
            }
            catch (Exception ex5) {}
            super.b = false;
            if (fh.c.o()) {
                int m = 0;
                int n2 = 0;
                try {
                    m = fh.d.m;
                    n2 = fh.d.n;
                    if (fh.d.c()) {
                        final dx d = fh.d;
                        d.m /= fh.d.d();
                    }
                    if (fh.d.c()) {
                        final dx d2 = fh.d;
                        d2.n /= fh.d.d();
                    }
                    fh.c.a(this.getPalette(b, fh.d, fh.u), fh.d);
                    this.c = new hp(b, fh.d.ao);
                    switch (hn.j) {
                        case 4: {
                            n = this.a(b, fh.d, fh.c, fh.g, a, n, hn, fh.o);
                            break;
                        }
                        case 2:
                        case 3: {
                            n = this.b(b, fh.d, fh.c, fh.g, a, n, hn, fh.o);
                            break;
                        }
                        case 1: {
                            n = this.c(b, fh.d, fh.c, fh.g, a, n, hn, fh.o);
                            break;
                        }
                        case 32773: {
                            this.a(fh.d, fh.c, fh.g, a, hn, fh.o);
                            break;
                        }
                        case 5: {
                            n = this.a(fh.d, fh.c, fh.g, a, n, hn, fh.o);
                            break;
                        }
                        case 6: {
                            boolean b4 = true;
                            boolean b5 = false;
                            if (hn.i) {
                                try {
                                    b5 = true;
                                    n = this.a(fh.d, fh.c, fh.g, a, n, hn, fh.o, fh, 0);
                                    b4 = false;
                                }
                                catch (Exception ex) {
                                    if (ji.util.i.c(5)) {
                                        ji.io.h.d(this.o, "Jpeg in Tiff compression 7 failed - trying compression 6");
                                    }
                                }
                            }
                            if (b4) {
                                try {
                                    n = this.b(fh.d, fh.c, fh.g, a, n, hn, fh.o, fh, 0);
                                }
                                catch (Exception ex) {
                                    if (!b5) {
                                        if (ji.util.i.c(5)) {
                                            ji.io.h.d(this.o, "Jpeg in Tiff compression 6 failed - trying compression 7");
                                        }
                                        try {
                                            n = this.a(fh.d, fh.c, fh.g, a, n, hn, fh.o, fh, 0);
                                        }
                                        catch (Exception ex6) {
                                            if (ji.util.i.c(5)) {
                                                ji.io.h.d(this.o, "Jpeg in Tiff compression 7 failed as well");
                                            }
                                        }
                                    }
                                    else if (ji.util.i.c(5)) {
                                        ji.io.h.d(this.o, "Jpeg in Tiff compression 6 failed as well");
                                    }
                                    throw ex;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
                catch (Exception ex2) {
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.o, "TIFF Filter error 1: ");
                        ex2.printStackTrace();
                    }
                    throw ex2;
                }
                finally {
                    if (fh.d.c()) {
                        fh.d.m = m;
                    }
                    if (fh.d.c()) {
                        fh.d.n = n2;
                    }
                    if (fh.d.cu != 0) {
                        final String s2 = "1104";
                        fh.c.a(fh.d);
                        final ds a2 = fh.c.a(fh.d.cu, fh.o, new Point(0, 0), true, 0, 0, fh.g, null, s2, false);
                        if (a2 != fh.c) {
                            if (ji.util.i.c(290)) {
                                fh.d.ct = a2;
                            }
                            else {
                                final boolean h = fh.c.h();
                                if (!h) {
                                    fh.c.ae();
                                    fh.c.b(fh.o);
                                }
                                fh.c.b(a2);
                                fh.c.d(h);
                            }
                            fh.d.m = (int)a2.j;
                            fh.d.n = (int)a2.k;
                            fh.d.bm = true;
                        }
                        if (fh.g != null) {
                            try {
                                if (fh.g != null) {
                                    fh.g.a(new a6(this, 10, ""));
                                }
                            }
                            catch (Exception ex7) {}
                        }
                    }
                    if (ac2 != null) {
                        ac2.a(fh.o);
                        ac2 = null;
                    }
                    if (ac != null) {
                        ac.a(fh.o);
                        ac = null;
                    }
                    if (a == null) {
                        a = ji.io.q.a(fh.o, this.o);
                    }
                    if (s != null) {
                        a.d(s);
                        s = null;
                    }
                    if (ac3 != null) {
                        ac3.a(fh.o);
                        a.d(s);
                        ac3 = null;
                    }
                    if (image != null) {
                        image.flush();
                        image = null;
                    }
                    if (this.c != null) {
                        this.c.a();
                        this.c = null;
                    }
                    this.c();
                }
            }
            try {
                if (fh.c.o() && n < fh.d.n && fh.d.ad >= 10000) {
                    fh.d.n = n;
                    fh.d.ac = 100.0;
                    fh.d.ad = 100.0;
                    fh.d.bm = true;
                }
            }
            catch (Exception ex3) {
                ji.util.d.a(ex3);
                if (ji.util.d.dr() || ji.util.i.c(5)) {
                    ji.io.h.d(this.o, "TIFF Filter error 2: ");
                    ex3.printStackTrace();
                }
            }
        }
        catch (Exception ex4) {
            ji.util.d.a(ex4);
            if (ji.util.d.dr() || ji.util.i.c(5)) {
                ji.io.h.d(this.o, "TIFF Filter error 3: ");
                ex4.printStackTrace();
            }
        }
        catch (Error error) {
            ji.util.d.a(error);
            if (ji.util.d.dr() || ji.util.i.c(5)) {
                ji.io.h.d(this.o, "TIFF Filter error 4: ");
                error.printStackTrace();
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    private final int a(final ac ac, final dx dx, final ds ds, final af af, q a, int n, final hn hn, final Object o) throws Exception {
        if (this.h == null) {
            this.h = new gd(this.o);
        }
        if (a == null) {
            a = ji.io.q.a(o, this.o);
        }
        this.h.a(af, dx, ds, this.c, a, o);
        try {
            for (int n2 = 0; n2 < hn.an.length && !super.b; ++n2) {
                final hl hl = hn.an[n2];
                if (hl.b == 0) {
                    hl.b = hl.c * dx.m;
                }
                n += this.h.a(hl.c, hl.a, hl.b, n);
            }
        }
        finally {
            this.h.b();
        }
        try {
            if (af != null && !dx.b8 && ji.util.d.du() && dx.ak) {
                final a6 a2 = new a6(this, 23, "");
                a2.a("100");
                af.a(a2);
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    private final int b(final ac ac, final dx dx, final ds ds, final af af, q a, int n, final hn hn, final Object o) throws Exception {
        if (this.g == null) {
            this.g = new gc(this.o);
        }
        if (a == null) {
            a = ji.io.q.a(o, this.o);
        }
        this.g.a(af, dx, ds, this.c, a, o);
        try {
            int n2 = 0;
            for (int n3 = 0; n3 < hn.an.length && !super.b; ++n3) {
                if (n2 + hn.an[n3].c > dx.n) {
                    hn.an[n3].c = dx.n - n2;
                }
                n2 += hn.an[n3].c;
            }
            for (int n4 = 0; n4 < hn.an.length && !super.b; ++n4) {
                n += this.g.a(hn.an[n4].c, hn.an[n4].a, hn.an[n4].b, n);
            }
        }
        finally {
            this.g.b();
        }
        try {
            if (af != null && !dx.b8 && ji.util.d.du() && dx.ak) {
                final a6 a2 = new a6(this, 23, "");
                a2.a("100");
                af.a(a2);
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    private final int c(final ac ac, final dx dx, final ds ds, final af af, q a, int n, final hn hn, final Object o) throws Exception {
        if (this.i == null) {
            this.i = new ge();
        }
        if (a == null) {
            a = ji.io.q.a(o, this.o);
        }
        this.i.a(af, dx, ds, a, o, this.o);
        try {
            a6 a2 = new a6(this, 4, "");
            if (ji.util.d.du() && dx.ak) {
                a2 = new a6(this, 23, "");
            }
            if (ji.util.i.c(87)) {
                ds.e(true);
            }
            for (int n2 = 0; n2 < hn.an.length && !super.b; ++n2) {
                n += this.i.a(ac, af, hn.an[n2].b, dx.m, hn.an[n2].c, dx, hn.an[n2].a, n, ds, o);
                try {
                    if (hn.an.length > 2 && af != null) {
                        a2.a("".concat(String.valueOf(String.valueOf(100 * n2 / hn.an.length))));
                        af.a(a2);
                    }
                }
                catch (Exception ex) {}
            }
            try {
                if (ji.util.d.du() && af != null) {
                    a2.a("100");
                    af.a(a2);
                }
            }
            catch (Exception ex2) {}
            if (dx.z >= 24) {
                ds.e(o);
            }
        }
        finally {
            this.i.b();
        }
        return n;
    }
    
    private final int a(final dx dx, final ds ds, final af af, final q q, int n, final hn hn, final ad ad, final fh fh, final int n2) throws Exception {
        int n3 = 0;
        boolean b = false;
        int n4 = 0;
        if (hn.al != null && hn.al.length > 4) {
            n4 = hn.al.length - 4;
        }
        final boolean b2 = hn.an.length > 1;
        a6 a6 = null;
        af af2 = null;
        if (b2 && af != null) {
            a6 = new a6(this, 4, "");
            if (ji.util.d.du() && dx.ak) {
                a6 = new a6(this, 23, "");
            }
            a6.a("0");
            af.a(a6);
        }
        else {
            af2 = af;
        }
        if (hn.n == 2 && ji.util.i.c(5)) {
            ji.io.h.d(this.o, "compression 7 JPEG with photometric tag set to RGB discovered. Using fast decoder...");
        }
        final int length = hn.an.length;
        final double n5 = 10.0;
        double n6 = 0.0;
        for (int n7 = 0; n7 < length && !super.b; ++n7) {
            int a7 = 0;
            if (hn.an[n7].a + hn.an[n7].b <= this.c.g()) {
                this.c.a((long)hn.an[n7].a);
            }
            else {
                this.c.a(0L);
            }
            final byte[] array = new byte[hn.an[n7].b + n4];
            this.c.a(array, 0, 2);
            if (n4 > 0) {
                System.arraycopy(hn.al, 2, array, 2, n4);
            }
            this.c.a(array, n4 + 2, hn.an[n7].b - 2);
            if (ji.util.i.c(87) && hn.n != 2) {
                final c2 c2 = new c2(this.o, fh.o, fh.d, af2, Toolkit.getDefaultToolkit().createImage(array, 0, array.length), 0, n3, fh.d.m, hn.an[n7].c, 0, fh.c, 1);
                c2.a(fh.d.ak);
                a7 = (c2.a() ? 1 : 0);
                if (c2.f()) {
                    dx.am = 24;
                }
                if (a7 != 0) {
                    if ((c2.b() | 0x40) == 0x40) {
                        a7 = 0;
                    }
                    else if ((c2.b() | 0x80) == 0x80) {
                        a7 = 0;
                    }
                }
                c2.i();
                if (a7 != 0) {
                    n3 += hn.an[n7].c;
                }
            }
            Label_1234: {
                if (ji.util.d.a6() || (a7 == 0 && ji.util.i.c(87))) {
                    try {
                        ac ac = null;
                        e2 e2 = null;
                        dx loadImageHeaderInternal = null;
                        String n8 = null;
                        try {
                            if (this.f == null) {
                                this.f = q.a(ad, this.o);
                            }
                            n8 = this.f.n();
                            ac = new ac(n8, true, false, 0, false, ad, this.o);
                            ac.b(array);
                            ac.a(ad);
                            ac = new ac(n8, false, false, 0, false, ad, this.o);
                            e2 = new e2();
                            e2.b(true);
                            if (hn.n == 2) {
                                e2.a(true);
                            }
                            e2.a(this.o);
                            loadImageHeaderInternal = e2.loadImageHeaderInternal(new fh(this.p, ac, this.b, n8, af2, this.r, this.m, this.s, this.t, ad, dx.a0, dx.n, dx.n, this.o, fh.j, fh.k, fh.q, fh.p, ji.util.i.c(164), null, fh.z, ds, false));
                            if (loadImageHeaderInternal != null) {
                                e2.fillDibInternal(new fh(ac, ds, loadImageHeaderInternal, ad, af2, fh.r, fh.s, fh.t, this.o, fh.j, fh.k, fh.q, fh.p, ji.util.i.c(164)));
                                n = loadImageHeaderInternal.n;
                                n3 += hn.an[n7].c;
                                break Label_1234;
                            }
                            b = true;
                            throw new Exception("Unable to process JPEG7 strip...");
                        }
                        catch (Exception ex) {
                            if (b) {
                                throw ex;
                            }
                            ex.printStackTrace();
                        }
                        finally {
                            try {
                                if (ac != null) {
                                    ac.a(ad);
                                }
                            }
                            catch (Exception ex3) {}
                            try {
                                if (e2 != null) {
                                    e2.close(loadImageHeaderInternal, ad);
                                }
                            }
                            catch (Exception ex4) {}
                            try {
                                if (n8 != null) {
                                    this.f.d(n8);
                                    ji.io.ac.c(n8, this.o);
                                }
                            }
                            catch (Exception ex5) {}
                        }
                    }
                    catch (Exception ex2) {
                        if (b) {
                            throw ex2;
                        }
                    }
                }
            }
            if (b2 && af != null && af2 == null) {
                final double n9 = Math.round(100 * n3 / dx.n);
                if (n9 > n6 + n5) {
                    n6 = n9;
                    if (hn.an.length > 2) {
                        a6.a("".concat(String.valueOf(String.valueOf(n6))));
                        af.a(a6);
                    }
                }
            }
        }
        if (b2 && af != null && af2 == null && hn.an.length > 2) {
            a6.a("100");
            af.a(a6);
        }
        if (fh.d.am > 1) {
            fh.c.e(fh.o);
        }
        return n;
    }
    
    private int b(final fh fh) {
        int n = fh.d.am;
        final hn hn = (hn)fh.d.ax;
        if (hn != null) {
            n = hn.v;
        }
        return n;
    }
    
    private final int b(final dx dx, final ds ds, final af af, final q q, int n, final hn hn, final ad ad, final fh fh, final int n2) throws Exception {
        Image image = null;
        byte[] array = null;
        try {
            final boolean b = hn.an.length > 1;
            int n3 = 0;
            for (int n4 = 0; n4 < hn.an.length && !super.b; ++n4) {
                n3 += hn.an[n4].b;
            }
            if (b) {
                n3 += hn.an.length * 2 + 6;
            }
            int n5 = 0;
            int n6 = 0;
            if (hn.ab > 0) {
                n3 += hn.ac;
            }
            array = new byte[n3];
            if (hn.ab > 0) {
                this.c.a((long)hn.ab);
                if (hn.ac > 0) {
                    final int a = this.c.a(array, n6, hn.ac);
                    if (a > 0) {
                        n5 += a;
                        n6 += hn.ac;
                    }
                }
            }
            else if (n3 == 0) {
                throw new Exception("Invalid file: Unable to process TIFF header (missing TIFF directory)");
            }
            dx dx2 = null;
            if (n5 > 0) {
                if (this.f == null) {
                    this.f = q.a(ad, this.o);
                }
                final String n7 = this.f.n();
                final ac ac = new ac(n7, true, false, 0, false, ad, this.o);
                ac.b(array, 0, n5);
                ac.a(ad);
                dx2 = new dx();
                final ac ac2 = new ac(n7, false, true, n5, false, ad, this.o);
                ou.a(ac2, dx2, this.o, true);
                ac2.a(ad);
                this.f.d(n7);
            }
            final int n8 = 65488;
            final int n9 = 65495;
            int n10 = n8;
            Integer n11 = null;
            if (dx2 != null) {
                n11 = dx2.bk.get("C0 Sample Factor");
            }
            if (n11 == null) {
                n11 = new Integer(1);
            }
            final int n12 = n11 * 8;
            int n13;
            if (hn.r % n12 != 0) {
                n13 = (hn.r + (n12 - hn.r % n12)) * hn.am / 64;
            }
            else {
                n13 = hn.r * hn.am / 64;
            }
            if (n11 != null) {
                while (n13 % n11 != 0) {
                    ++n13;
                }
                n13 /= n11;
            }
            if (b) {
                array[n6++] = -1;
                array[n6++] = -35;
                array[n6++] = 0;
                array[n6++] = 4;
                array[n6++] = (byte)(n13 >> 8);
                array[n6++] = (byte)(n13 & 0xFF);
            }
            final int n14 = (int)this.c.g();
            byte[] array2 = null;
            if (e1.l) {
                array2 = new byte[n14];
                this.c.a(0L);
                this.c.a(array2);
            }
            final int length = hn.an.length;
            int n15 = 0;
            for (int n16 = 0; n16 < hn.an.length && !super.b; ++n16) {
                if (b && n16 < length && n16 > 0) {
                    array[n6++] = (byte)((n10 & 0xFF00) >> 8 & 0xFF);
                    array[n6++] = (byte)(n10 & 0xFF & 0xFF);
                    if (++n10 > n9) {
                        n10 = n8;
                    }
                }
                this.c.a((long)hn.an[n16].a);
                if (array2 != null) {
                    try {
                        if (hn.an[n16].a + hn.an[n16].b <= array2.length) {
                            System.arraycopy(array2, hn.an[n16].a, array, n6, hn.an[n16].b);
                        }
                        else {
                            System.arraycopy(array2, 0, array, n6, hn.an[n16].b);
                        }
                    }
                    catch (Exception ex3) {}
                }
                else {
                    this.c.a(array, n6, hn.an[n16].b);
                }
                n6 += hn.an[n16].b;
                n15 += hn.an[n16].c;
            }
            int n17 = -1;
            int a2 = 0;
            if (ji.util.i.c(87)) {
                image = Toolkit.getDefaultToolkit().createImage(array, 0, array.length);
                final c2 c2 = new c2(this.o, fh.o, fh.d, fh.g, image, 0, 0, fh.d.m, fh.d.n, 0, fh.c);
                c2.a(fh.d.ak);
                a2 = (c2.a() ? 1 : 0);
                if (c2.f()) {
                    dx.am = 24;
                }
                if (a2 != 0) {
                    if ((c2.b() | 0x40) == 0x40) {
                        a2 = 0;
                    }
                    else if ((c2.b() | 0x80) == 0x80) {
                        a2 = 0;
                    }
                }
                c2.i();
                if (a2 == 0) {
                    ji.io.h.d(this.o, "Trying recovery...");
                }
                else if (fh.d.am > 1) {
                    fh.c.e(fh.o);
                }
            }
            Label_1755: {
                if (!ji.util.d.a6()) {
                    if (a2 != 0 || !ji.util.i.c(87)) {
                        break Label_1755;
                    }
                }
                try {
                    ac ac3 = null;
                    e2 e2 = null;
                    dx loadImageHeaderInternal = null;
                    String n18 = null;
                    try {
                        if (this.f == null) {
                            this.f = q.a(ad, this.o);
                        }
                        n18 = this.f.n();
                        ac3 = new ac(n18, true, false, 0, false, ad, this.o);
                        ac3.b(array);
                        ac3.a(ad);
                        ac3 = new ac(n18, false, false, 0, false, ad, this.o);
                        e2 = new e2();
                        e2.a(this.o);
                        loadImageHeaderInternal = e2.loadImageHeaderInternal(new fh(this.p, ac3, this.b, n18, af, this.r, this.m, this.s, this.t, ad, dx.a0, dx.n, dx.n, this.o, fh.j, fh.k, fh.q, fh.p, ji.util.i.c(164), null, fh.z, ds, false));
                        if (loadImageHeaderInternal != null) {
                            e2.fillDibInternal(new fh(ac3, ds, loadImageHeaderInternal, ad, af, fh.r, fh.s, fh.t, this.o, fh.j, fh.k, fh.q, fh.p, ji.util.i.c(164)));
                            array = null;
                            n17 = (n = loadImageHeaderInternal.n);
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        n17 = 0;
                    }
                    finally {
                        try {
                            if (ac3 != null) {
                                ac3.a(ad);
                            }
                        }
                        catch (Exception ex4) {}
                        try {
                            if (e2 != null) {
                                e2.close(loadImageHeaderInternal, ad);
                            }
                        }
                        catch (Exception ex5) {}
                        try {
                            if (n18 != null) {
                                this.f.d(n18);
                                ac.c(n18, this.o);
                            }
                        }
                        catch (Exception ex6) {}
                    }
                }
                catch (Exception ex7) {}
            }
            if (n17 <= 0 && fh.d.am >= 24 && a2 == 0) {
                image = Toolkit.getDefaultToolkit().createImage(array, 0, array.length);
                ji.util.d.a(new Frame(), image);
                if (image.getWidth(null) <= 0) {
                    ji.io.h.d(this.o, "Recovery failed, JPEG Width = 0");
                    ji.util.e.b(false);
                    ji.util.e.e(ji.util.d.b(326, this.o));
                    throw new fg(ji.util.d.b(326, this.o));
                }
                int min = Math.min(ji.util.d.ar / 4 / dx.m, dx.n);
                if (min < 1) {
                    min = 1;
                }
                final int n19 = min * dx.m;
                int n20 = dx.n;
                int n21 = 0;
                a6 a3 = new a6(this, 4, "");
                if (ji.util.d.du() && dx.ak) {
                    a3 = new a6(this, 23, "");
                }
                final int[] array3 = new int[n19];
                ds.f(ad);
                while (n20 > 0 && !super.b) {
                    final c3 a4 = ji.util.d.a(image, this.o, ad);
                    ji.util.d.a(ad, this.o, image, a4, 0, n21, dx.m, min, dx.m, array3);
                    ji.util.d.a(image, a4);
                    ds.a(array3, min * dx.m, ad, n21, n21 + min - 1, true);
                    n21 += min;
                    n20 -= min;
                    if (n20 < min) {
                        min = n20;
                    }
                    if (af != null) {
                        a3.a("".concat(String.valueOf(String.valueOf(100 * n21 / dx.n))));
                        af.a(a3);
                    }
                }
                ds.e(ad);
            }
        }
        catch (Exception ex2) {
            throw ex2;
        }
        finally {
            if (image != null) {
                image.flush();
            }
            if (array != null) {}
        }
        return n;
    }
    
    private final void a(final dx dx, final ds ds, final af af, q q, final hn hn, final Object o) throws Exception {
        String n = null;
        ac ac = null;
        ac ac2 = null;
        if (this.j == null) {
            this.j = new gf();
        }
        try {
            this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
            final a6 a6 = new a6(this, 4, "");
            if (q == null) {
                q = q.a(o, this.o);
            }
            final String n2 = q.n();
            final ac ac3 = new ac(n2, true, false, 0, o, this.o);
            int n3 = 0;
            int b = 0;
            for (int n4 = 0; n4 < hn.an.length && !super.b; ++n4) {
                if (hn.an[n4].b > b) {
                    b = hn.an[n4].b;
                }
                n3 += hn.an[n4].b;
            }
            final byte[] array = new byte[b];
            for (int n5 = 0; n5 < hn.an.length && !super.b; ++n5) {
                this.c.a((long)hn.an[n5].a);
                ac3.b(array, 0, this.c.a(array, 0, hn.an[n5].b));
                try {
                    if (hn.an.length > 2 && af != null) {
                        a6.a("".concat(String.valueOf(String.valueOf(100 * n5 / hn.an.length))));
                        af.a(a6);
                    }
                }
                catch (Exception ex) {}
            }
            ac3.a(o);
            final ac ac4 = new ac(n2, false, true, b, o, this.o);
            if (dx.z <= 1) {
                if (q == null) {
                    q = q.a(o, this.o);
                }
                n = q.n();
                ac = new ac(n, true, false, 0, o, this.o);
                if (af != null) {
                    af.a(new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("...")));
                }
            }
            try {
                if (af != null) {
                    a6.a("0");
                    af.a(a6);
                }
            }
            catch (Exception ex2) {}
            this.j.a(af, dx, ds);
            this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
            if (af != null) {
                af.a(new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("...")));
            }
            if (ji.util.i.c(87)) {
                ds.e(true);
            }
            this.j.a(ac4, af, n3, dx.m, dx.n, dx, ds, ac, o);
            ac4.a(o);
            ji.io.ac.c(n2, this.o);
        }
        finally {
            this.j.b();
        }
        this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
        if (ac != null) {
            ac.a(o);
        }
        if (af != null) {
            af.a(new a6(this, 1, ""));
        }
        if (dx.z == 8 && !ds.g()) {
            ds.e(o);
        }
        else if (dx.z == 24) {
            ds.e(o);
        }
        else if (dx.z <= 1) {
            final int n6 = (int)q.c(n, o);
            ac2 = new ac(n, false, false, 0, o, this.o);
            final ge ge = new ge();
            try {
                if (q == null) {
                    q = q.a(o, this.o);
                }
                ge.a(af, dx, ds, q, o, this.o);
                ge.a(ac2, af, n6, dx.m, dx.n, dx, 0, 0, ds, o);
            }
            finally {
                ge.b();
            }
        }
        if (ac2 != null) {
            ac2.a(o);
            q.d(n);
        }
    }
    
    private final int a(final dx dx, final ds ds, final af af, q q, int n, final hn hn, final Object o) throws Exception {
        byte[] array = null;
        String n2 = null;
        final ac ac = null;
        ac ac2 = null;
        ac ac3 = null;
        if (this.k == null) {
            this.k = new gg();
        }
        final boolean b = hn.an.length > 1;
        a6 a6 = new a6(this, 4, "0");
        if (ji.util.d.du() && dx.ak && dx.z > 1) {
            a6 = new a6(this, 23, "");
        }
        try {
            this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
            if (dx.z <= 1) {
                if (q == null) {
                    q = q.a(o, this.o);
                }
                n2 = q.n();
                ac3 = new ac(n2, true, false, 0, o, this.o);
                if (af != null) {
                    af.a(new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("...")));
                }
            }
            if (ji.util.i.c(87)) {
                ds.e(true);
            }
            n = 0;
            this.k.a(af, dx.n);
            this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
            final double n3 = 100.0 / hn.an.length;
            double n4 = 0.0;
            int n5 = 0;
            int n6 = 0;
            for (int n7 = 0; n7 < hn.an.length && !super.b; ++n7) {
                if (hn.an[n7].b == 0) {
                    hn.an[n7].b = hn.an[n7].c * dx.m;
                }
                if (array == null) {
                    array = new byte[hn.an[n7].b];
                }
                else if (array.length < hn.an[n7].b) {
                    array = new byte[hn.an[n7].b];
                }
                this.c.a((long)hn.an[n7].a);
                this.c.a(array, 0, hn.an[n7].b);
                final int a7 = this.k.a(array, af, hn.an[n7].b, dx.m, hn.an[n7].c, dx.z, dx.aw, dx.x, ds, n, ac3, o, b, n6);
                n6 += hn.an[n7].c;
                if (b) {
                    n4 += n3;
                    if (n4 - n5 > 10) {
                        if (hn.an.length > 2) {
                            a6.a(String.valueOf(n4));
                            if (af != null) {
                                af.a(a6);
                            }
                        }
                        n5 = (int)n4;
                    }
                }
                n += a7;
            }
        }
        finally {
            if (ji.util.d.du() && b) {
                a6.a("100");
                af.a(a6);
            }
            if (ac3 != null) {
                ac3.a(o);
                ac3 = null;
            }
            if (ac != null) {
                ac.a(o);
            }
            this.k.b();
        }
        this.a(af, String.valueOf(String.valueOf(ji.util.d.b(334, this.o))).concat("..."));
        if (dx.z >= 24) {
            ds.e(o);
        }
        else if (dx.z <= 1) {
            if (af != null) {
                af.a(new a6(this, 1, ""));
            }
            final int n8 = (int)q.c(n2, o);
            ac2 = new ac(n2, false, false, 0, o, this.o);
            final ge ge = new ge();
            try {
                if (q == null) {
                    q = q.a(o, this.o);
                }
                ge.a(af, dx, ds, q, o, this.o);
                ge.a(ac2, af, n8, dx.m, dx.n, dx, 0, 0, ds, o);
            }
            finally {
                ge.b();
            }
        }
        if (ac2 != null) {
            ac2.a(o);
            q.d(n2);
        }
        if (ac3 != null) {
            ac3.a(o);
        }
        return n;
    }
    
    private final void a(final af af, final String s) {
        try {
            if (s != null && af != null) {
                af.a(new a6(this, 1, s));
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (ji.util.e.ai()) {
                super.b = true;
                if (this.g != null) {
                    this.g.a();
                }
                if (this.h != null) {
                    this.h.a();
                }
                if (this.i != null) {
                    this.i.a();
                }
                if (this.j != null) {
                    this.j.a();
                }
                if (this.k != null) {
                    this.k.a();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void c() {
        this.g = null;
        this.h = null;
        this.j = null;
        this.i = null;
        this.k = null;
    }
    
    public final void close(final dx dx, final ad ad) {
        try {
            this.n = false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (dx != null && dx.cv != null) {
                while (dx.cv.b() > 0) {
                    final String s = (String)dx.cv.b(0);
                    dx.cv.d(0);
                    try {
                        ji.io.q.a(ad, this.o).d(s);
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (Exception ex3) {}
        try {
            while (this.u.b() > 0) {
                final String s2 = (String)this.u.b(0);
                this.u.d(0);
                try {
                    ji.io.q.a(ad, this.o).d(s2);
                }
                catch (Exception ex4) {}
            }
        }
        catch (Exception ex5) {}
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        try {
            final hp hp = new hp(ac);
            ac.a(0L);
            final short l = ac.l();
            if (l != 19789 && l != 18761) {
                return 0;
            }
            if (l == 18761) {
                hp.a(1);
            }
            return (hp.d() == 42) ? 1 : 0;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return 0;
        }
    }
    
    public String c(final String s) {
        return s.a(872, s);
    }
    
    public void a(final dx dx, final dx dx2) {
        if (ji.util.i.c(290)) {
            dx2.ct = dx.ct;
        }
    }
    
    static {
        e1.e = false;
        e1.l = false;
    }
}
