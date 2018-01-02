// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import java.util.Vector;
import ji.filter.tiff.hn;
import java.awt.Polygon;
import ji.io.q;
import ji.awt.c;
import ji.annotate.df;
import ji.font.ct;
import ji.awt.d4;
import ji.awt.da;
import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import ji.font.j;
import ji.net.a0;
import ji.io.h;
import ji.util.i;
import ji.awt.d5;
import java.awt.Color;
import ji.document.ad;
import ji.image.dx;
import ji.annotate.dg;
import ji.io.ac;

public class ej
{
    private static final int[] a;
    private static final String[] b;
    
    public static String[] a() {
        return ej.b;
    }
    
    public static boolean a(final int n) {
        boolean b = true;
        for (int n2 = 0; n2 < ej.a.length && b; ++n2) {
            if (ej.a[n2] == n) {
                b = false;
            }
        }
        return b;
    }
    
    private static byte[] c() {
        final byte[] array = new byte[30];
        int n = 0;
        final byte[] a = hq.a(array, n, 6);
        n += 4;
        final byte[] a2 = hq.a(a, n, 12);
        n += 4;
        final byte[] bytes = "OiIndex".getBytes();
        for (int i = 0; i < bytes.length; ++i, ++n) {
            a2[n] = bytes[i];
        }
        a2[n] = 0;
        ++n;
        final byte[] a3 = hq.a(a2, n, 10);
        n += 4;
        final byte[] bytes2 = "0".getBytes();
        for (int j = 0; j < bytes2.length; ++j, ++n) {
            a3[n] = bytes2[j];
        }
        for (int k = bytes2.length; k < 10; ++k, ++n) {
            a3[n] = 0;
        }
        return a3;
    }
    
    private static void a(final ac ac, final int n) throws Exception {
        ac.b(new byte[] { (byte)(n & 0xFF), (byte)((n & 0xFF00) >> 8), (byte)((n & 0xFF0000) >> 16), (byte)((n & 0xFF000000) >> 24) });
    }
    
    private static byte[] d() {
        final byte[] array = new byte[31];
        int n = 0;
        final byte[] a = hq.a(array, n, 6);
        n += 4;
        final byte[] a2 = hq.a(a, n, 12);
        n += 4;
        final byte[] bytes = "OiGroup".getBytes();
        for (int i = 0; i < bytes.length; ++i, ++n) {
            a2[n] = bytes[i];
        }
        a2[n] = 0;
        ++n;
        final byte[] a3 = hq.a(a2, n, 11);
        n += 4;
        final byte[] bytes2 = "[UNTITLED]".getBytes();
        for (int j = 0; j < bytes2.length; ++j, ++n) {
            a3[n] = bytes2[j];
        }
        a3[n] = 0;
        return a3;
    }
    
    public static byte[] b() {
        final byte[] array = new byte[152];
        int n = 4;
        array[n] = 1;
        n += 4;
        array[n] = 2;
        n += 4;
        array[n] = 12;
        n += 4;
        final byte[] bytes = "OiUGroup*".getBytes();
        for (int i = 0; i < bytes.length; ++i, ++n) {
            array[n] = bytes[i];
        }
        n += 3;
        array[n] = 12;
        n += 4;
        final byte[] bytes2 = "[Untitled]".getBytes();
        for (int j = 0; j < bytes2.length; ++j, ++n) {
            array[n] = bytes2[j];
        }
        n += 2;
        for (int k = 0; k < bytes2.length; ++k, n += 2) {
            array[n] = bytes2[k];
        }
        n += 6;
        array[n] = 2;
        n += 4;
        array[n] = 12;
        n += 4;
        final byte[] bytes3 = "OiGroup".getBytes();
        for (int l = 0; l < bytes3.length; ++l, ++n) {
            array[n] = bytes3[l];
        }
        ++n;
        array[n] = 12;
        n += 4;
        final byte[] bytes4 = "[Untitled]".getBytes();
        for (int n2 = 0; n2 < bytes4.length; ++n2, ++n) {
            array[n] = bytes4[n2];
        }
        n += 2;
        array[n] = 2;
        n += 4;
        array[n] = 12;
        n += 4;
        final byte[] bytes5 = "OiIndex".getBytes();
        for (int n3 = 0; n3 < bytes5.length; ++n3, ++n) {
            array[n] = bytes5[n3];
        }
        ++n;
        array[n] = 30;
        n += 4;
        array[n] = 52;
        return array;
    }
    
    public static void a(final dg dg, final dx dx, final ad ad, final String s, final ac ac, final boolean b) {
        final hq hq = new hq();
        hq.i = true;
        hq.d = dg.b(dg, ad.o9);
        hq.e = Color.black;
        hq.h = (int)dx.a((long)dg.a(dx));
        hq.j = new hr();
        final da a = dx.a(dg.bf(false));
        final long a2 = a.a;
        final long b2 = a.b;
        final long c = a.c;
        final long d = a.d;
        final int n = (int)(72000 / dx.ad);
        hq.b = new d5(a2, b2);
        hq.c = new d5(a2 + c, b2 + d);
        ht[] array = new ht[0];
        if (i.c(5)) {
            h.d(s, "WANG: Creating Wang annotation...");
        }
        switch (dg.d5()) {
            case 3: {
                if (i.c(5)) {
                    h.d(s, "WANG: Creating Wang annotation type: LINE");
                }
                hq.a = 3;
                final d5 a3 = dx.a(dg.fo());
                final d5 a4 = dx.a(dg.fn());
                final long n2 = (a2 < a3.a) ? a2 : a3.a;
                final long n3 = (b2 < a3.b) ? b2 : a3.b;
                final long n4 = (a2 > a3.a) ? a2 : a3.a;
                final long n5 = (b2 > a3.b) ? b2 : a3.b;
                final hs hs = new hs();
                (hs.b = new d5[2])[0] = new d5(a4.a - a2, a4.b - b2);
                hs.b[1] = new d5(a3.a - a2, a3.b - b2);
                hs.a = 2;
                if (dg.e1()) {
                    array = new ht[] { hs, new hu(0, dg.c9(), dg.e5()) };
                    break;
                }
                array = new ht[] { hs };
                break;
            }
            case 5:
            case 9: {
                if (i.c(5)) {
                    h.d(s, "WANG: Creating Wang annotation type: FREEHAND");
                }
                hq.a = 4;
                final d4 fm = dg.fm();
                final hs hs2 = new hs();
                hs2.b = new d5[fm.npoints];
                hs2.a = fm.npoints;
                for (int i = 0; i < hs2.a; ++i) {
                    hs2.b[i] = new d5(dx.a((long)fm.xpoints[i]) - a2, dx.b((long)fm.ypoints[i]) - b2);
                }
                if (dg.e2()) {
                    array = new ht[] { hs2, new hu(1, dg.c9(), dg.e5()) };
                    break;
                }
                array = new ht[] { hs2 };
                break;
            }
            case 0:
            case 10:
            case 12: {
                if (i.c(5)) {
                    h.d(s, "WANG: Creating Wang annotation type: RECTANGLE");
                }
                if (dg.ei()) {
                    hq.a = 6;
                    hq.d = dg.c(dg, ad.o9);
                    hq.f = dg.a(dg, ad.o9);
                    break;
                }
                hq.a = 5;
                break;
            }
            case 6:
            case 8: {
                if (dg.az() != 0) {
                    da da = dg.bf(false);
                    if (dg.at() && dg.aj() != null) {
                        da = dg.aj();
                    }
                    hq.c = new d5(a2 + dx.a(da.c), b2 + dx.b(da.d));
                }
                else {
                    final String[] a5 = dg.a9();
                    if (a5.length == 2) {
                        hq.c = new d5(Integer.parseInt(a5[0]), Integer.parseInt(a5[1]));
                    }
                }
                if (!dg.e9()) {
                    if (i.c(5)) {
                        h.d(s, "WANG: Creating Wang annotation type: EMBEDDED IMAGE");
                    }
                    hq.a = 1;
                    if (dg.a(dg, ad.o9) && dg.d6() != null) {
                        hq.g = true;
                    }
                    else {
                        hq.g = false;
                    }
                    final double cb = dg.cb();
                    if (i.c(5)) {
                        h.d(s, "WANG: Saving Wang annotation scaleFactor = ".concat(String.valueOf(String.valueOf(cb))));
                    }
                    final da ff = dg.ff();
                    hq.c.a = hq.b.a + dx.a(ff.c);
                    hq.c.b = hq.b.b + dx.b(ff.d);
                    if (i.c(5)) {
                        h.d(s, "WANG: Saving Wang annotation topLeftPos = ".concat(String.valueOf(String.valueOf(hq.b))));
                    }
                    if (i.c(5)) {
                        h.d(s, "WANG: Saving Wang annotation bottomRightPos = ".concat(String.valueOf(String.valueOf(hq.c))));
                    }
                    if (i.c(5)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("WANG: Saving Wang annotation (Width = ").append(hq.c.a - hq.b.a).append(")"))));
                    }
                    if (i.c(5)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("WANG: Saving Wang annotation (height = ").append(hq.c.b - hq.b.b).append(")"))));
                    }
                    array = new ht[3];
                    final hv hv = new hv(dg.cj(), cb);
                    a0 a6 = null;
                    try {
                        a6 = new a0(ad, s);
                        final hw hw = new hw(dx, dg.fa(), ad, s, a6, b);
                        array[0] = hv;
                        array[1] = new hx(dg.fa());
                        array[2] = hw;
                        break;
                    }
                    finally {
                        if (a6 != null) {
                            a6.a();
                        }
                    }
                }
                if (i.c(5)) {
                    h.d(s, "WANG: Creating Wang annotation type: TEXT");
                }
                if (dg.ei()) {
                    hq.a = 10;
                    hq.e = dg.b(dg, ad.o9);
                    hq.d = dg.c(dg, ad.o9);
                }
                else {
                    hq.a = 7;
                }
                hq.j.n = dg.cu().c;
                final double a7 = dx.a(dg.c6());
                double n6 = 16.0;
                try {
                    n6 = a7 * j.a(ad, a(hq.j.n, hq.j.a, hq.j.f), null, s);
                    n6 /= dx.ad / 100;
                    n6 /= 1.9;
                }
                catch (Exception ex2) {}
                hq.j.a = (int)n6;
                array = new ht[] { new hy(dg.ev(), n, dg.b4() * 10) };
                break;
            }
        }
        if (i.c(5)) {
            h.d(s, "WANG: Created Wang annotation");
        }
        try {
            a(ac, 5);
            a(ac, hq.a());
            hq.a(ac);
            for (int j = 0; j < array.length; ++j) {
                a(ac, 6);
                a(ac, 12);
                final byte[] array2 = new byte[8];
                System.arraycopy(array[j].c().getBytes(), 0, array2, 0, array[j].c().getBytes().length);
                try {
                    if (i.c(5)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("WANG: Saving Wang annotation: ").append(ji.util.d.c(new String(array2), true)).append("..."))));
                    }
                }
                catch (Exception ex3) {}
                ac.b(array2);
                final long r = ac.r();
                a(ac, array[j].b());
                final int a8 = array[j].a(ac, s);
                final long r2 = ac.r();
                ac.a(r);
                a(ac, a8);
                ac.a(r2);
            }
            ac.b(d());
            ac.b(c());
            for (int k = 0; k < array.length; ++k) {
                array[k].a();
            }
        }
        catch (Exception ex) {
            h.a(s, ex);
        }
        if (i.c(5)) {
            h.d(s, "WANG: Saved Wang annotation");
        }
    }
    
    private static final ct a(final String s, final double n, final boolean b) {
        return j.h();
    }
    
    private static dg a(final df df, final hq hq, final c c, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final boolean b2, final int n2) {
        dg dg = null;
        hy hy = null;
        hv hv = null;
        hw hw = null;
        hs hs = null;
        hx hx = null;
        double n3 = 1.0;
        if (dx.ac > dx.ad) {
            n3 = dx.ac / dx.ad;
            hq.c.b = (int)(hq.c.b * n3);
            hq.b.b = (int)(hq.b.b * n3);
        }
        final long n4 = hq.c.a - hq.b.a;
        final long n5 = hq.c.b - hq.b.b;
        try {
            switch (hq.a) {
                case 0: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: IMAGE_EMBEDDED...");
                    }
                    for (int i = 0; i < c.b(); ++i) {
                        if (c.b(i) instanceof hx) {
                            hx = (hx)c.b(i);
                        }
                        if (c.b(i) instanceof hv) {
                            hv = (hv)c.b(i);
                        }
                        if (c.b(i) instanceof hw) {
                            hw = (hw)c.b(i);
                        }
                    }
                    if ((hx == null && hw == null) || hv == null) {
                        break;
                    }
                    dg = new dg(df.a(d.b(557, s)), hq.d, hq.h, s, 1, ad.hc());
                    dg.a(ad);
                    String s2 = null;
                    Label_0634: {
                        if (hw != null) {
                            ac ac = null;
                            try {
                                final String a = q.a(ad, s).a("dib", false, d.cq(String.valueOf(String.valueOf(dx.h)).concat(String.valueOf(String.valueOf(n2)))), "w");
                                ac = new ac(a, true, false, 0, ad, s);
                                ac.b(hw.d());
                                s2 = String.valueOf(String.valueOf(new StringBuffer("image:file:").append(a)));
                                if (i.c(5)) {
                                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("WANG: Loading annotations, Create cached image filename = ").append(a).append(", page: ").append(n))));
                                }
                                break Label_0634;
                            }
                            catch (Exception ex) {
                                if (d.cy()) {
                                    ex.printStackTrace();
                                }
                                break Label_0634;
                            }
                            finally {
                                try {
                                    ac.a(ad);
                                }
                                catch (Exception ex2) {}
                            }
                        }
                        if (i.c(5)) {
                            h.d(s, "WANG: Loading annotations, Use existing cached image filename = ".concat(String.valueOf(String.valueOf(hx.a))));
                        }
                        s2 = "image:".concat(String.valueOf(String.valueOf(hx.a)));
                    }
                    final int e = hv.e();
                    long n6 = 0L;
                    long n7 = 0L;
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation, initial width = ".concat(String.valueOf(String.valueOf(n4))));
                    }
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation, initial height = ".concat(String.valueOf(String.valueOf(n5))));
                    }
                    switch (e) {
                        case 90: {
                            n6 = n4;
                            break;
                        }
                        case 270: {
                            n7 = n5;
                            break;
                        }
                        case 180: {
                            n6 = n4;
                            n7 = n5;
                            break;
                        }
                    }
                    final int n8 = (int)hq.b.a;
                    final int n9 = (int)hq.b.b;
                    final int n10 = (int)(n8 + n6);
                    final int n11 = (int)(n9 + n7);
                    final int n12 = (int)(hq.c.a + n6);
                    final int n13 = (int)(hq.c.b + n7);
                    dg.a(dx.bo, 9, 0, s2, n10, n11, b);
                    dg.d((int)n4, (int)n5);
                    dg.ao(false);
                    if (hw != null) {
                        dg.c(true, n2);
                    }
                    else {
                        dg.u(true);
                    }
                    dg.e(String.valueOf(n12));
                    dg.e(String.valueOf(n13));
                    dg.l(e);
                    dg.n(hv.d());
                    dg.ag((int)n4);
                    dg.af((int)n5);
                    if (hq.g) {
                        dg.au(true);
                        dg.e(new Color(255, 255, 255));
                        dg.g(new Color(255, 255, 255));
                    }
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation, final width = ".concat(String.valueOf(String.valueOf(n4))));
                    }
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation, final height = ".concat(String.valueOf(String.valueOf(n5))));
                        break;
                    }
                    break;
                }
                case 2: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: LINE...");
                    }
                    hu hu = null;
                    for (int j = 0; j < c.b(); ++j) {
                        if (c.b(j) instanceof hs) {
                            hs = (hs)c.b(j);
                        }
                        else if (c.b(j) instanceof hu) {
                            hu = (hu)c.b(j);
                        }
                    }
                    if (hs == null) {
                        break;
                    }
                    final int n14 = (int)dx.d(hq.h);
                    dg = new dg(df.a(d.b(554, s)), hq.d, hq.h, s, 1, ad.hc());
                    dg.a(ad);
                    dg.a(4, hq.b.a + hs.b[0].a, hq.b.b + (int)(hs.b[0].b * n3), hq.b.a + hs.b[1].a, hq.b.b + (int)(hs.b[1].b * n3));
                    if (hu != null) {
                        dg.bc(hu.a == 0);
                        dg.m(hu.b);
                        dg.be(hu.d);
                        break;
                    }
                    break;
                }
                case 3: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: FREEHAND...");
                    }
                    hu hu2 = null;
                    for (int k = 0; k < c.b(); ++k) {
                        if (c.b(k) instanceof hs) {
                            hs = (hs)c.b(k);
                        }
                        else if (c.b(k) instanceof hu) {
                            hu2 = (hu)c.b(k);
                        }
                    }
                    if (hs == null) {
                        break;
                    }
                    dg = new dg(df.a(d.b(568, s)), hq.d, hq.h, s, 1, ad.hc());
                    dg.a(ad);
                    final Polygon polygon = new Polygon();
                    final long a2 = hq.b.a;
                    final long b3 = hq.b.b;
                    for (int l = 0; l < hs.b.length; ++l) {
                        polygon.addPoint((int)(a2 + hs.b[l].a), (int)(b3 + hs.b[l].b * n3));
                    }
                    dg.a(10, polygon);
                    if (hu2 != null) {
                        dg.bd(hu2.a == 1);
                        dg.m(hu2.b);
                        dg.be(hu2.d);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: RECTANGLE...");
                    }
                    dg = new dg(df.a(d.b(558, s)), hq.d, 1, s, 1, ad.hc());
                    dg.a(ad);
                    dg.a(1, hq.b.a, hq.b.b, n4, n5);
                    dg.v(hq.h);
                    dg.ba(false);
                    break;
                }
                case 5: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: FILLED_RECTANGLE...");
                    }
                    dg = new dg(df.a(d.b(558, s)), Color.white, 1, s, 1, ad.hc());
                    dg.a(ad);
                    dg.a(1, hq.b.a, hq.b.b, n4, n5);
                    dg.ba(true);
                    dg.v(hq.h);
                    dg.d(hq.d);
                    dg.au(hq.f);
                    break;
                }
                case 6:
                case 7:
                case 8:
                case 9: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: TEXT...");
                    }
                    for (int n15 = 0; n15 < c.b(); ++n15) {
                        if (c.b(n15) instanceof hy) {
                            hy = (hy)c.b(n15);
                            break;
                        }
                    }
                    if (hy == null) {
                        break;
                    }
                    dg = new dg(df.a(d.b(556, s)), hq.d, 1, s, 1, ad.hc());
                    dg.a(ad);
                    dg.a(dx.bo, 7, 0, hy.d, (int)hq.b.a, (int)hq.b.b, b);
                    if (i.c(177)) {
                        dg.ax(true);
                    }
                    else {
                        dg.ax(false);
                    }
                    if (hq.a == 10) {
                        dg.au(false);
                        dg.ba(true);
                        dg.c(hq.e);
                        dg.d(hq.d);
                    }
                    final double n16 = hq.j.a * (72000 / hy.b / 100) * 1.9;
                    final ct a3 = a(hq.j.n, hq.j.a, hq.j.f);
                    final double n17 = n16 / a3.a;
                    dg.a(a3);
                    dg.c(n17);
                    dg.k(hy.a / 10);
                    dg.e(String.valueOf((int)hq.c.a));
                    dg.e(String.valueOf((int)hq.c.b));
                    if (b) {
                        dg.x(true);
                        dg.a((int)(hq.c.a - hq.b.a), (int)(hq.c.b - hq.b.b));
                        break;
                    }
                    break;
                }
                case 11: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: FORM (Not supported)...");
                    }
                }
                case 12: {
                    if (i.c(5)) {
                        h.d(s, "WANG: Loading annotation type: OCR Region (Not supported)...");
                    }
                    dg = new dg(df.a(d.b(556, s)), hq.d, 1, s, 1, ad.hc());
                    dg.a(ad);
                    dg.a(dx.bo, 7, 0, "NOT SUPPORTED", (int)hq.b.a, (int)hq.b.b, b);
                    break;
                }
            }
            if (dg != null) {
                dg.bp().c(1);
                if (b2 && ad.c7()) {
                    dg.d(1);
                }
                else {
                    dg.d(0);
                }
                dg.j(n);
                dg.c(1);
                dg.bp().c(true);
                dg.bp().h(true);
                dg.bp().f(true);
                dg.bp().e(null);
            }
        }
        catch (Exception ex3) {}
        return dg;
    }
    
    public static df a(final df df, final ac ac, final hn hn, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final boolean b2, final int n2) throws Exception {
        if (hn != null && df != null) {
            if (hn.ap != null) {
                final boolean ic = ad.ic();
                final Vector<Object> vector = new Vector<Object>(500);
                if (ac.a(hn.ap, s) > 4) {
                    final ac ac2 = new ac(hn.ap, false, true, 102400, false, ad, false, s);
                    if (i.c(5)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("WANG: Loading annotations (").append(d.a(ac2.v(), s)).append(")..."))));
                    }
                    hq hq = null;
                    c c = null;
                    int n3 = 0;
                    try {
                        ac2.a(4L);
                        final int n4 = ac2.h() + (ac2.h() << 8) + (ac2.h() << 16) + (ac2.h() << 24);
                        int i = 1;
                        hq = null;
                        c = null;
                        boolean b3 = true;
                        while (i != 0) {
                            final int n5 = ac2.h() + (ac2.h() << 8) + (ac2.h() << 16) + (ac2.h() << 24);
                            final int n6 = ac2.h() + (ac2.h() << 8) + (ac2.h() << 16) + (ac2.h() << 24);
                            if (ji.util.i.c(5)) {
                                h.d(s, "WANG: Loading annotations blockType = ".concat(String.valueOf(String.valueOf(n5))));
                            }
                            switch (n5) {
                                case 0: {
                                    String concat = "";
                                    for (int j = 0; j < 8; ++j) {
                                        final int h = ac2.h();
                                        if (h > 0) {
                                            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf((char)h)));
                                        }
                                    }
                                    final int n7 = ac2.h() + (ac2.h() << 8) + (ac2.h() << 16) + (ac2.h() << 24);
                                    if (ji.util.i.c(5)) {
                                        h.d(s, "WANG: Loading annotations namedBlockSize = ".concat(String.valueOf(String.valueOf(n7))));
                                    }
                                    if (n4 == 0) {
                                        ac2.a(ac2.r() + 4);
                                    }
                                    if (n7 > 0) {
                                        ac2.a(ac2.r() + n7);
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    if (ji.util.i.c(5)) {
                                        h.d(s, "WANG: Loading annotations namedBlocks = ".concat(String.valueOf(String.valueOf(c))));
                                    }
                                    if (c != null && c.b() > 0) {
                                        if (ji.util.i.c(5)) {
                                            h.d(s, "WANG: Loading annotations Create annotation, nameBlocks = ".concat(String.valueOf(String.valueOf(c))));
                                        }
                                        if (!b2 || n2 == n3) {
                                            vector.addElement(a(df, hq, c, dx, af, ad, s, n, ic, b, n3));
                                        }
                                        ++n3;
                                    }
                                    final long r = ac2.r();
                                    hq = new hq(ac2, n4, s);
                                    c = new c("jiFilterTiff:namedBlocks");
                                    b3 = (hq.a == 3 || hq.a == 4);
                                    ac2.a(r + n6);
                                    break;
                                }
                                case 4: {
                                    String concat2 = "";
                                    for (int k = 0; k < 8; ++k) {
                                        final int h2 = ac2.h();
                                        if (h2 > 0) {
                                            concat2 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf((char)h2)));
                                        }
                                    }
                                    if (ji.util.i.c(5)) {
                                        h.d(s, "WANG: Loading annotations blockName = ".concat(String.valueOf(String.valueOf(concat2))));
                                    }
                                    final int n8 = ac2.h() + (ac2.h() << 8) + (ac2.h() << 16) + (ac2.h() << 24);
                                    if (ji.util.i.c(5)) {
                                        h.d(s, "WANG: Loading annotations namedBlockSize = ".concat(String.valueOf(String.valueOf(n8))));
                                    }
                                    if (n4 == 0) {
                                        ac2.a(ac2.r() + 4);
                                    }
                                    if (n8 > 0) {
                                        final long r2 = ac2.r();
                                        c.c(a(concat2, ac2, n4, b3, n8, s));
                                        if (ji.util.i.c(5)) {
                                            h.d(s, "WANG: Loading annotations Added namedBlock, nameBlocks = ".concat(String.valueOf(String.valueOf(c))));
                                        }
                                        ac2.a(r2 + n8);
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    i = 0;
                                    break;
                                }
                            }
                            if (ac2.g()) {
                                i = 0;
                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    finally {
                        if (ac2 != null) {
                            ac2.a(ad);
                        }
                    }
                    if (c != null && c.b() > 0) {
                        if (i.c(5)) {
                            h.d(s, "WANG: Loading annotations Create left over annotations, nameBlocks = ".concat(String.valueOf(String.valueOf(c))));
                        }
                        if (!b2 || n2 == n3) {
                            vector.addElement(a(df, hq, c, dx, af, ad, s, n, ic, b, n3));
                        }
                        ++n3;
                    }
                }
                try {
                    if (vector.size() > 0) {
                        df.h(vector.size());
                        if (i.c(5)) {
                            h.d(s, "WANG: Loading annotations number of annotations = ".concat(String.valueOf(String.valueOf(vector.size()))));
                        }
                        while (vector.size() > 0) {
                            if (i.c(5)) {
                                h.d(s, "WANG: Loading annotations item = ".concat(String.valueOf(String.valueOf(vector.elementAt(0)))));
                            }
                            final dg dg = vector.elementAt(vector.size() - 1);
                            if (d.cs()) {
                                h.d(s, "READ WANG ANNOTATION: ".concat(String.valueOf(String.valueOf(dg))));
                            }
                            df.b(dg);
                            vector.removeElementAt(vector.size() - 1);
                        }
                        df.e();
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            hn.ao = null;
        }
        return df;
    }
    
    private static ht a(final String s, final ac ac, final int n, final boolean b, final int n2, final String s2) throws Exception {
        ht ht = null;
        if (i.c(5)) {
            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("WANG: Processing annotation (").append(s).append(")..."))));
        }
        if (s.equalsIgnoreCase("oianodat")) {
            if (b) {
                ht = new hs(ac, n, s2);
            }
            else {
                ht = new hv(ac, n, s2);
            }
        }
        else if (s.equalsIgnoreCase("oifilnam")) {
            ht = new hx(ac, s2);
        }
        else if (s.equalsIgnoreCase("oidib")) {
            ht = new hw(ac, n2, s2);
        }
        else if (s.equalsIgnoreCase("oigroup")) {
            ht = new om(ac, s2);
        }
        else if (s.equalsIgnoreCase("oiindex")) {
            ht = new om(ac, s2);
        }
        else if (s.equalsIgnoreCase("oiantext")) {
            ht = new hy(ac, n, s2);
        }
        else if (s.equalsIgnoreCase("oihyplnk")) {
            ht = new on(ac, n);
        }
        else if (s.equalsIgnoreCase("v1line")) {
            ht = new hu(ac, n, s2);
        }
        return ht;
    }
    
    static {
        a = new int[] { 5, 17, 14, 8, 2, 3, 12 };
        b = new String[] { "arrow", "highlightpoly", "note", "oval", "poly", "redactpoly", "circle", "ruler", "angle" };
    }
}
