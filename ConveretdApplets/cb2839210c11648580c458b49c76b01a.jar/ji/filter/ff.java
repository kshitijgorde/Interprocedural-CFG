// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.document.ad;
import ji.decode.ol;
import ji.font.j;
import java.awt.Color;
import ji.io.oe;
import ji.v1event.a6;
import java.awt.Component;
import ji.res.ay;
import java.io.FileNotFoundException;
import java.io.IOException;
import ji.image.ds;
import ji.image.ev;
import java.awt.Dimension;
import ji.v1event.af;
import ji.util.e;
import ji.v1event.ao;
import java.net.URL;
import ji.net.a0;
import ji.res.s;
import ji.io.ac;
import java.util.Hashtable;
import ji.util.d;
import ji.io.h;
import ji.util.i;
import ji.io.hp;
import ji.image.dx;
import ji.io.p;
import ji.awt.c;
import ji.io.q;
import ji.filter.filenetcold.n9;
import ji.decode.o1;
import ji.decode.ok;

public class ff extends cj
{
    private static final String[] a;
    private final int b = 18;
    private static final String[] c;
    private static final String[] d;
    private static final String[] e;
    private static final String[] f;
    private ok g;
    private o1 h;
    private n9 i;
    private boolean j;
    private boolean k;
    private String l;
    private q m;
    private boolean n;
    private boolean o;
    private c p;
    private static boolean q;
    private static boolean r;
    private String s;
    private String t;
    private int u;
    private p v;
    
    public void a(final String l) {
        this.l = l;
    }
    
    public ff() {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = true;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.p = null;
        this.s = null;
        this.t = null;
        this.u = 0;
        this.v = null;
    }
    
    public final String getFilterName() {
        return "jiFilterFilenetCold";
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (ff.q) {
            return null;
        }
        this.c();
        this.j = true;
        final hp hp = new hp(fh.b, 0);
        final abe abe = new abe();
        final dx dx = new dx();
        dx.i = hp.g();
        if (dx.i < 100) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #1 Header length (").append(dx.i).append(") must be at least ").append(100).append(" bytes"))));
            }
            hp.a();
            return null;
        }
        final abf a = this.a(hp);
        if (a == null) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "FileNet Form Header is null");
            }
            hp.a();
            return null;
        }
        boolean b = false;
        String s = "";
        final int n = a.a & 0xFF;
        switch (n) {
            case 197: {
                s = "Text Only";
                b = true;
                break;
            }
            case 213: {
                s = "Compressed Text Only";
                b = true;
                break;
            }
            case 198: {
                s = "Text and Image";
                b = true;
                break;
            }
            case 214: {
                s = "Compressed Text and Image";
                b = true;
                break;
            }
        }
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, "jiFilterFilenetCold: Simple validation: ".concat(String.valueOf(String.valueOf(ji.util.d.ai()))));
        }
        if (ji.util.d.ai()) {
            if (dx.i < 96 || !b) {
                if (ji.util.i.c(5)) {
                    ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #2 Header length (").append(dx.i).append(") must be at least ").append(96).append(" bytes"))));
                }
                hp.a();
                return null;
            }
        }
        else if (dx.i < 96 || a.p != 1413565515L || !b) {
            if (ji.util.i.c(5)) {
                if (dx.i < 96) {
                    ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #2 Header length (").append(dx.i).append(") must be at least ").append(96).append(" bytes"))));
                }
                else if (a.p != 1413565515L) {
                    ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #2 Header magic number (").append(a.p).append(") must be set to ").append(1413565515L))));
                }
                else {
                    ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #2 Header page type (").append(n).append(") is not avalid type"))));
                }
            }
            hp.a();
            return null;
        }
        if (ji.util.d.co(this.l)) {
            dx.l = String.valueOf(String.valueOf(new StringBuffer("Filenet COLD v2 (").append(s).append(")")));
        }
        else {
            dx.l = String.valueOf(String.valueOf(new StringBuffer("Filenet COLD (").append(s).append(")")));
        }
        dx.u = 1;
        dx.a = 7;
        dx.bk = new Hashtable(5);
        if (a.r == 1 && n != 213 && n != 214) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: #2 Header page type (").append(n).append(") must be at  ").append(213).append(" or ").append(214))));
            }
            hp.a();
            return null;
        }
        try {
            final int n2 = (int)a.h;
            byte[] a2 = new byte[n2];
            if (this.m == null) {
                this.m = ji.io.q.a(fh.o, this.l);
            }
            abe.a = this.m.n();
            this.s = abe.a;
            dx.b0 = "";
            abe.au = a.x;
            if (a.r == 1) {
                if (this.h == null) {
                    this.h = new o1(fh.o, this.l);
                }
                dx.z = 0;
                final ac ac = new ac(abe.a, true, false, 0, false, fh.o, this.l);
                hp.a(a.k);
                hp.a(a2);
                final byte[] array = new byte[(int)a.s];
                try {
                    this.h.a(a2, n2, array, (int)a.s, fh.o, this.l);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                ac.b(array);
                ac.a(fh.o);
                final ac ac2 = new ac(abe.a, false, false, 0, false, fh.o, this.l);
                final hp hp2 = new hp(ac2, 0);
                this.b(hp2);
                this.a(this.p, dx, abe, true, false);
                dx.bt = abe.al;
                dx.bv = abe.am;
                final String a3 = abe.a;
                ac ac3 = null;
                if (!ji.util.d.co(this.l)) {
                    abe.a = this.m.n();
                    this.t = abe.a;
                    ac3 = new ac(abe.a, true, false, 0, false, fh.o, this.l);
                }
                final byte[] array2 = new byte[(int)(hp2.g() - hp2.f() + 1)];
                final int a4 = hp2.a(array2);
                hp2.a();
                while (this.u > 0) {
                    if (ac3 != null) {
                        ac3.b(" \n".getBytes());
                    }
                    --this.u;
                    final dx dx2 = dx;
                    dx2.b0 = String.valueOf(String.valueOf(dx2.b0)).concat("\n");
                }
                final dx dx3 = dx;
                dx3.b0 = String.valueOf(String.valueOf(dx3.b0)).concat(String.valueOf(String.valueOf(new String(array2))));
                if (ac3 != null) {
                    ac3.b(array2, 0, Math.min(a4, array2.length));
                    ac3.a(fh.o);
                }
                hp2.a();
                ac2.a(fh.o);
                ji.io.ac.c(a3, this.l);
            }
            else {
                hp.a(a.k);
                this.b(hp);
                this.a(this.p, dx, abe, true, false);
                dx.bt = abe.al;
                dx.bv = abe.am;
                int n3;
                if (hp.f() + a.k > a.e) {
                    a2 = new byte[(int)(a.e - hp.f())];
                    n3 = hp.a(a2);
                }
                else {
                    n3 = hp.a(a2);
                }
                ac ac4 = null;
                if (!ji.util.d.co(this.l)) {
                    ac4 = new ac(abe.a, true, false, 0, false, fh.o, this.l);
                }
                while (this.u > 0) {
                    if (ac4 != null) {
                        ac4.b(" \n".getBytes());
                    }
                    --this.u;
                    final dx dx4 = dx;
                    dx4.b0 = String.valueOf(String.valueOf(dx4.b0)).concat("\n");
                }
                if (!ji.util.i.c(215)) {
                    a2 = this.a(a2, n3, dx, abe);
                }
                final int length = a2.length;
                if (ac4 != null) {
                    ac4.b(a2, 0, length);
                }
                final dx dx5 = dx;
                dx5.b0 = String.valueOf(String.valueOf(dx5.b0)).concat(String.valueOf(String.valueOf(new String(a2))));
                if (ac4 != null) {
                    ac4.a(fh.o);
                }
            }
            if (!ji.util.d.co(this.l)) {
                dx.ac = abe.h;
                dx.ad = abe.h;
                dx.bu = abe.au;
                double n4 = -1.0;
                double n5 = -1.0;
                if (abe.q > 0 && abe.r > 0) {
                    final double n6 = abe.q;
                    final double n7 = abe.r;
                    this.a(dx.bk, "pCode: Y", String.valueOf(String.valueOf(new StringBuffer("Paper size <EQUALS> ").append(n6).append(" x ").append(n7))));
                    dx.a((int)n6, (int)n7);
                }
                else {
                    for (int i = 0; i < ff.a.length; i += 4) {
                        if (abe.at == ji.util.d.c(ff.a[i + 0], -4)) {
                            n4 = ji.util.d.a(ff.a[i + 2], -99.0);
                            n5 = ji.util.d.a(ff.a[i + 3], -99.0);
                            this.a(dx.bk, "pCode: Y", String.valueOf(String.valueOf(new StringBuffer("Paper size <EQUALS> ").append(ff.a[i + 1]).append(" (").append(n4).append("\" x ").append(n5).append("\")"))));
                        }
                    }
                    int n8 = 0;
                    if (n4 < 0) {
                        final String l = ji.res.s.l();
                        if (l.equals("us") || l.equals("ca")) {
                            for (int n9 = 0; n9 < ff.a.length && n8 == 0; n9 += 4) {
                                if (ji.util.d.c(ff.a[n9 + 0], -4) == 4) {
                                    n4 = ji.util.d.a(ff.a[n9 + 2], 8.5);
                                    n5 = ji.util.d.a(ff.a[n9 + 3], 11.0);
                                    this.a(dx.bk, "pCode: Y (default)", String.valueOf(String.valueOf(new StringBuffer("Paper size <EQUALS> ").append(ff.a[n9 + 1]).append(" (").append(n4).append("\" x ").append(n5).append("\")"))));
                                    n8 = 1;
                                }
                            }
                        }
                        else {
                            for (int n10 = 0; n10 < ff.a.length && n8 == 0; n10 += 4) {
                                if (ji.util.d.c(ff.a[n10 + 0], -4) == 3) {
                                    n4 = ji.util.d.a(ff.a[n10 + 2], 8.27);
                                    n5 = ji.util.d.a(ff.a[n10 + 3], 11.69);
                                    this.a(dx.bk, "pCode: Y (default)", String.valueOf(String.valueOf(new StringBuffer("Paper size <EQUALS> ").append(ff.a[n10 + 1]).append(" (").append(n4).append("\" x ").append(n5).append("\")"))));
                                    n8 = 1;
                                }
                            }
                        }
                        dx.a(n4, n5);
                    }
                }
            }
            URL a5 = null;
            a0 a6 = null;
            try {
                if (!ji.util.d.by(dx.bu)) {
                    this.a(dx.bk, "Template (CE) ID", "".concat(String.valueOf(String.valueOf(dx.bu))));
                    this.a(dx.bk, "Template Page", "".concat(String.valueOf(String.valueOf(dx.bv))));
                }
                else {
                    this.a(dx.bk, "Template ID", "".concat(String.valueOf(String.valueOf(dx.bt))));
                    this.a(dx.bk, "Template Page", "".concat(String.valueOf(String.valueOf(dx.bv))));
                }
                boolean b2 = false;
                boolean b3 = false;
                if (ji.util.d.co(this.l) && fh.y != null && fh.y.a != null) {
                    b3 = true;
                }
                if (!b3) {
                    boolean b4;
                    if (fh.o.bi(24)) {
                        if (fh.y != null) {
                            b4 = fh.y.d();
                            if (b4) {
                                b4 = dx.a();
                                if (!b4) {
                                    fh.y = null;
                                }
                            }
                        }
                        else {
                            b4 = dx.a();
                        }
                    }
                    else {
                        b4 = dx.a();
                    }
                    if (b4) {
                        if (fh.q) {
                            fh.y = null;
                        }
                        if (fh.y == null) {
                            b2 = true;
                        }
                        else if (fh.y.a == null) {
                            b2 = true;
                        }
                    }
                    if (!b2 && fh.y != null) {
                        dx.cb.a(fh.y);
                    }
                }
                if (b3 || b2) {
                    ff.q = true;
                    a6 = new a0(this, this.l);
                    a6.b(fh.g);
                    int max = 1;
                    if (b2) {
                        String s2 = "".concat(String.valueOf(String.valueOf(fh.a)));
                        if (!ji.util.d.by(ji.util.d.ah())) {
                            s2 = ji.util.d.ah();
                        }
                        String s3 = ji.util.d.b(s2, "\\", "/");
                        max = Math.max(dx.bv, 1);
                        if (!ji.util.d.bj(s3)) {
                            s3 = "file:///".concat(String.valueOf(String.valueOf(s3)));
                        }
                        if (s3.indexOf("file:") >= 0 || ji.util.i.c(188)) {
                            final String s4 = s3;
                            final String bh = ji.util.d.bh(s3);
                            String s5;
                            if (ji.util.d.by(dx.bu)) {
                                s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s4.substring(0, s4.lastIndexOf("/"))))).append("/").append(dx.bt).append("_").append(max).append(".").append(bh)));
                            }
                            else {
                                s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s4.substring(0, s4.lastIndexOf("/"))))).append("/").append(dx.bt).append("_").append(max).append(".").append(bh)));
                            }
                            a5 = new URL(s5);
                        }
                        else if (ji.util.d.by(dx.bu)) {
                            a5 = (URL)ji.util.d.a(s3, ji.util.d.aj(), dx.bt, ji.util.d.ak(), max, false);
                        }
                        else {
                            a5 = (URL)ji.util.d.a(s3, ji.util.d.aj(), dx.bu, ji.util.d.ak(), max, true);
                        }
                    }
                    else if (fh.y != null && fh.y.a != null) {
                        if (fh.y.a instanceof URL) {
                            a5 = (URL)fh.y.a;
                        }
                        else if (fh.y.a instanceof String) {
                            a5 = new URL((String)fh.y.a);
                        }
                    }
                    final String b5 = a6.b(a5, true, true, null, fh.o, fh.g, fh.o);
                    Label_3585: {
                        if (b5 != null) {
                            if (!ji.util.e.aa()) {
                                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD is not licensed!");
                            }
                            boolean b6 = true;
                            ac ac5 = null;
                            a6.i();
                            final String j = a6.j();
                            final String k = a6.k();
                            if (j != null && j.toLowerCase().startsWith("html")) {
                                b6 = false;
                                try {
                                    ac5 = new ac(b5, false, false, 0, false, fh.o, this.l);
                                    final byte[] array3 = new byte[(int)ac5.v()];
                                    ac5.a(array3);
                                    ac5.a(fh.o);
                                    ji.io.h.d(this.l, "jiFilterFilenetCold: ".concat(String.valueOf(String.valueOf(new String(array3)))));
                                }
                                catch (Exception ex2) {
                                    ex2.printStackTrace();
                                }
                            }
                            if (b6) {
                                if (!ji.util.d.co(this.l)) {
                                    try {
                                        ac5 = new ac(b5, false, false, 0, false, fh.o, this.l);
                                        final boolean r = ff.r;
                                        final dx a7 = ew.a(b5, ac5, b5, b5, null, false, fh.o, j, k, this.l, dx.a0, dx.m, dx.n, max, true, -1, null, fh.q, false, false, null, null, null, false, true, false, false);
                                        ff.r = r;
                                        if (a7 != null) {
                                            if (ji.util.i.c(243) && a7.l.equals("Text")) {
                                                final ac ac6 = new ac(b5, false, false, 0, false, fh.o, this.l);
                                                final byte[] array4 = new byte[(int)ac6.v()];
                                                ac6.a(array4);
                                                ac6.a(fh.o);
                                                final String s6 = new String(array4);
                                                ji.io.h.d(this.l, s6);
                                                throw new IOException(s6);
                                            }
                                            dx.cb.a = a5;
                                            dx.cb.b();
                                            dx.cb.b = Math.max(dx.bv, 1);
                                            dx.cb.f = a7.m;
                                            dx.cb.g = a7.n;
                                            dx.cb.d = a7.ac;
                                            dx.cb.e = a7.ad;
                                            a6.i();
                                        }
                                        break Label_3585;
                                    }
                                    finally {
                                        if (ac5 != null) {
                                            ac5.a(fh.o);
                                        }
                                    }
                                }
                                dx.cb.a = a5;
                                dx.cb.b = Math.max(dx.bv, 1);
                                dx.cb.b();
                                dx.cc = b5;
                                fh.x = b5;
                                fh.y.a = a5;
                                fh.y.b();
                            }
                        }
                    }
                    a6.a(fh.g);
                }
            }
            catch (Exception ex3) {
                if (ex3 instanceof FileNotFoundException || ex3 instanceof IOException) {
                    if (ff.r) {
                        ji.util.d.a(ay.a(), this.a(dx.bt, a5), fh.o, null, fh.g, this.l, false);
                    }
                    dx.bu = null;
                }
                else {
                    ji.util.d.a(ex3, ay.a(), fh.o, fh.g, this.l);
                }
                ff.r = false;
            }
            finally {
                ff.q = false;
                if (a6 != null) {
                    a6.a();
                }
            }
            if (ji.util.i.c(246) && dx.cb != null) {
                dx.ac = dx.cb.d;
                dx.ad = dx.cb.e;
                abe.h = (int)dx.cb.d;
            }
            if (ji.util.d.co(this.l)) {
                if (!this.a(dx, fh)) {
                    return null;
                }
            }
            else {
                this.a(dx, abe, fh, false);
            }
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        finally {
            if (this.p != null) {
                this.p.c();
                this.p = null;
            }
        }
        dx.an = 0;
        dx.ax = abe.a();
        if (ji.util.d.co(this.l)) {
            if (ji.util.i.c(210)) {
                dx.z = 1;
                dx.aa = 1;
                dx.am = 1;
            }
            else {
                dx.z = 8;
                dx.aa = 3;
                dx.am = 24;
            }
        }
        else {
            dx.z = 1;
            dx.aa = 1;
            dx.am = 1;
        }
        dx.as = true;
        dx.an = 0;
        dx.ar = false;
        dx.at = 0;
        hp.a();
        dx.cn = 0;
        return dx;
    }
    
    private String a(final long n, final URL url) {
        final StringBuffer sb = new StringBuffer(ji.util.d.b(999, this.l));
        sb.append(" (id = ");
        sb.append(n);
        sb.append(") \n");
        sb.append(ji.util.d.b(1000, this.l));
        sb.append(" (");
        if (url != null) {
            sb.append(url.toString());
        }
        sb.append(") ");
        return sb.toString();
    }
    
    private final boolean a(final dx dx, final fh fh) throws Exception {
        boolean b = false;
        boolean b2 = false;
        if (this.i == null) {
            try {
                (this.i = (n9)ji.util.d.a2("ji.filter.filenetcold.jiFilenetColdDecoder")).setParentId(this.l);
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                b = true;
                noClassDefFoundError.printStackTrace();
                ji.io.h.d(this.l, "******** No Filenet COLD class!...");
                this.i = null;
            }
            catch (Exception ex) {
                b = true;
                ex.printStackTrace();
                this.i = null;
            }
            finally {
                if (this.i == null) {
                    ji.io.h.d(this.l, "Filenet COLD DLL filter: Problem creating decoder");
                }
            }
        }
        try {
            if (this.i != null) {
                this.g(this.l);
                if (fh.r && fh.j == 6) {
                    b2 = true;
                    fh.r = false;
                }
                if (fh.r && fh.j != 6) {
                    final Dimension dimension = new Dimension(120, 120);
                    try {
                        this.v.b(dimension, fh.o);
                    }
                    catch (Exception ex3) {}
                }
                if (fh.d == null) {
                    fh.d = new dx();
                }
                if (this.i.processHeader(dx, fh, this.l) == null) {}
            }
        }
        catch (Exception ex2) {
            b = true;
            ex2.printStackTrace();
        }
        if (b) {
            this.i = null;
            ji.io.h.d(this.l, "Filenet COLD DLL filter: Problem creating Filenet COLD decoder");
            ji.util.d.a(ay.a(), ji.util.d.b(1231, this.l), fh.o, 60, null, fh.g, this.l);
        }
        boolean b3 = false;
        boolean loadFailedDueToPermissions = false;
        if (this.i != null) {
            b3 = !this.i.isDecoderLoaded();
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "DLL Decoder Loaded:".concat(String.valueOf(String.valueOf(b3))));
            }
            if (b3) {
                loadFailedDueToPermissions = this.i.loadFailedDueToPermissions();
                if (ji.util.i.c(5)) {
                    ji.io.h.d(this.l, "DLL Decoder Permissions:".concat(String.valueOf(String.valueOf(loadFailedDueToPermissions))));
                }
            }
        }
        if (this.g() || b3) {
            if (this.i != null) {
                this.i.close(fh.d);
                this.i.removeDecoder(fh.o, this.l);
            }
            fh.d = null;
        }
        if (fh.d == null) {
            ji.util.e.ag(null);
            if (fh.g != null) {
                fh.g.a(new a6(fh.g, 1, ""));
            }
            if (fh.g != null) {
                fh.g.a(new a6(fh.g, 13, ""));
            }
        }
        if (b2) {
            fh.r = true;
        }
        if (this.g()) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "Filenet COLD - user aborted open");
            }
            fh.d = null;
            throw new oe(ji.util.d.b(966, this.l));
        }
        if (fh.d != null) {
            fh.d.e = true;
        }
        if (b3 && loadFailedDueToPermissions) {
            ji.util.d.a(ay.a(), ji.util.d.f(ji.util.d.b(1236, this.l), 70), (fh != null) ? fh.o : null, null, null, this.l, null, 120, true, false);
        }
        return this.i != null;
    }
    
    private final void a(final dx dx, final abe abe, final fh fh, final boolean b) throws Exception {
        boolean b2 = true;
        dx.cf = abe.ad;
        if (dx.cb.d > 0) {
            if (abe.h != (int)dx.cb.d) {
                if (dx.cb.d == 600 && abe.h == 200) {
                    abe.h = 100;
                    dx.cf = 0.8;
                    this.a(this.p, dx, abe, false);
                }
                else if (dx.cb.d == 400 && abe.h == 200) {
                    if (ji.util.i.c(139) && abe.c == 0) {
                        abe.h = 100;
                        this.a(this.p, dx, abe, false);
                    }
                }
                else {
                    boolean b3 = false;
                    if (ji.util.i.c(259) && dx.cb.d == 300 && abe.h == 200) {
                        this.a(this.p, dx, abe);
                        b3 = true;
                    }
                    boolean b4 = true;
                    if (ji.util.i.c(211) && abe.ai) {
                        b4 = false;
                    }
                    if (b4) {
                        abe.h = (int)dx.cb.d;
                        this.a(this.p, dx, abe, b3);
                    }
                }
            }
            else {
                if (ji.util.i.c(205) && abe.ah > 0.0 && abe.h == 200 && abe.af && !abe.ag) {
                    abe.h = (int)abe.ah;
                    this.a(this.p, dx, abe, false);
                }
                if (ji.util.i.c(277) && abe.aj > 0 && abe.c == 0) {
                    dx.cf = abe.aj;
                }
            }
        }
        else if (abe.y != 0.0) {
            abe.h = (int)abe.y;
            this.a(this.p, dx, abe, false);
        }
        final int m = dx.m();
        final int n = dx.n();
        dx.m = m;
        dx.n = n;
        int i = abe.m;
        int n2 = abe.n;
        int o = abe.o;
        final int p4 = abe.p;
        abe.i = 0;
        if (n2 == 0) {
            n2 = i;
        }
        if (!b) {}
        final boolean b5 = dx.bt > 0 && dx.b9 > 0;
        if (ji.util.i.c(179) && abe.c <= 0.0) {
            abe.c = abe.x;
            if (abe.aa > 0.0) {
                i = (int)(abe.t / 2.0 + i * abe.aa);
                o = (int)(abe.t / 2.0 + o * abe.aa);
            }
        }
        if (abe.c > 0) {
            dx.b((int)Math.round(abe.h / abe.c));
            if (abe.c == 8.0) {
                abe.v = abe.d;
            }
            else if (abe.e == 33.4) {
                abe.v = 0.72;
                b2 = false;
            }
        }
        if (n2 > 1000) {
            n2 = 0;
        }
        if (this.o) {
            if (abe.z > 0.0) {
                i += (int)(abe.z * abe.t);
            }
            if (abe.ab > 0.0) {
                o -= (int)(abe.ab * abe.t);
            }
        }
        double n3 = 1.0;
        if (!dx.a0 && ji.util.i.c(35)) {
            if (abe.u == 0) {
                abe.u = abe.t;
                final int u = abe.u;
                final int d = ji.util.i.d(1);
                if (abe.u < d) {
                    final int t = d;
                    n3 = t / abe.u;
                    if (dx.p() > 0) {
                        n3 = t / dx.p();
                    }
                    dx.ac = (int)(n3 * dx.ac);
                    dx.ad = (int)(n3 * dx.ad);
                    dx.m *= (int)n3;
                    dx.n *= (int)n3;
                    dx.b9 *= (int)n3;
                    dx.ca *= (int)n3;
                    dx.b((int)(n3 * dx.p()));
                    int n4 = (int)(n3 * i);
                    int n5 = (int)(n3 * o);
                    final int n6 = (int)(n3 * n2);
                    final int n7 = (int)(n3 * p4);
                    if (this.o) {
                        if (abe.ac) {
                            if (b2) {
                                n4 -= t;
                                n5 -= t / 2;
                            }
                            else {
                                n4 -= t / 2;
                                n5 += t / 2;
                            }
                        }
                        else {
                            n5 = 0;
                            if (b2) {
                                n4 -= t;
                            }
                            else {
                                n4 -= t / 2;
                            }
                        }
                    }
                    dx.a(n4, n6, n5, n7);
                    abe.t = t;
                    if (abe.t == 36) {
                        abe.v *= 1.05;
                        abe.w *= 1.05;
                    }
                }
                else {
                    dx.a(i, n2, o, p4);
                }
            }
            else {
                n3 = abe.t / abe.u;
                if (dx.p() > 0) {
                    n3 = abe.t / dx.p();
                }
                dx.b((int)(dx.p() * n3));
            }
        }
        else {
            dx.a(i, n2, o, p4);
        }
        if (this.g == null) {
            boolean b6 = false;
            if (abe.m == 0 && abe.o == 0 && abe.ae) {
                b6 = true;
            }
            this.g = new ok(this.l, abe.v, abe.w, dx.o(), dx.cf, 1, Color.blue, false, b6, ji.util.i.c(215));
        }
        this.g.a(ji.util.i.c(263) && ji.util.i.c(247));
        this.g.a(dx.q(), dx.r(), dx.s(), dx.t(), abe.i);
        if (dx.cb.g > 0 && dx.cb.d > 0.0 && dx.ac > 0.0) {
            final double n8 = dx.ac / dx.cb.d;
            this.g.a((int)(n8 * dx.cb.f), (int)(n8 * dx.cb.g));
        }
        if (b5) {
            this.g.a(dx.b9, dx.ca);
        }
        if (abe.j == 2 || abe.f == 4) {
            this.g.b(true);
        }
        if (abe.s != null) {
            this.g.a(ji.font.j.a(abe.s, abe.t));
        }
        this.g.a(abe.av, n3);
        final ac ac = new ac(abe.a, false, false, 0, false, fh.o, this.l);
        boolean b7 = false;
        final int d2 = ji.util.i.d(2);
        if ((d2 == 1 || d2 == 2) && fh.q) {
            b7 = true;
        }
        else if (d2 == 2 && !fh.q) {
            b7 = true;
        }
        this.g.a(dx, ac, fh.o, fh.g, b7, 1, false);
        dx.br = this.g.f();
        dx.b1 = this.g.e();
        ac.a(fh.o);
        try {
            this.a(dx.bk, "pCode: W (used)", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.g.a()))).append(" ").append(this.g.c()).append(" (scaled to ").append(abe.t).append(")"))));
            this.a(dx.bk, "Num Lines", "".concat(String.valueOf(String.valueOf(dx.b1))));
            this.a(dx.bk, "Max Line Width", "".concat(String.valueOf(String.valueOf(dx.br))));
            this.a(dx.bk, "Source Font", String.valueOf(String.valueOf(new StringBuffer("").append(this.g.a()).append(" ").append(this.g.c()).append(" - ").append(this.g.b()))));
            this.a(dx.bk, "Font size", "".concat(String.valueOf(String.valueOf(this.g.d()))));
        }
        catch (Exception ex) {}
        dx.br = this.g.f();
    }
    
    private final void a(final Hashtable hashtable, final String s, final Object o) {
        if (hashtable != null && s != null && o != null && hashtable.get(s) == null) {
            hashtable.put(s, o);
        }
    }
    
    private final abf a(final hp hp) throws Exception {
        abf abf = new abf();
        try {
            hp.a(0L);
            abf.a = (byte)hp.c();
            abf.b = (byte)hp.c();
            abf.c = (byte)hp.c();
            abf.d = (byte)hp.c();
            abf.e = hp.e();
            abf.f = hp.e();
            abf.g = hp.e();
            abf.h = hp.e();
            abf.i = hp.e();
            abf.j = hp.e();
            abf.k = hp.e();
            abf.l = hp.e();
            abf.m = hp.e();
            abf.n = hp.e();
            abf.o = hp.e();
            abf.p = hp.e();
            abf.q = hp.e();
            abf.r = hp.e();
            abf.s = hp.e();
            final long f = hp.f();
            abf.t = hp.e();
            abf.u = hp.e();
            abf.v = hp.e();
            abf.w = hp.e();
            if (abf.t != 0 || abf.u != 0 || abf.v != 0 || abf.w != 0) {
                hp.a(f);
                abf.x = String.valueOf(String.valueOf(new StringBuffer("").append(this.b(hp.e())).append("-").append(this.a(hp.d())).append("-").append(this.a(hp.d()))));
                final byte[] array = new byte[8];
                hp.a(array);
                abf.x = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(abf.x))).append("-").append(this.a(array[0])).append(this.a(array[1])).append("-").append(this.a(array[2])).append(this.a(array[3])).append(this.a(array[4])).append(this.a(array[5])).append(this.a(array[6])).append(this.a(array[7]))));
                hp.a(f);
            }
        }
        catch (Exception ex) {
            abf = null;
        }
        return abf;
    }
    
    private final String a(final byte b) {
        return this.a(b & 0xFF, 2);
    }
    
    private final String a(final int n) {
        return this.a(n & 0xFFFF, 4);
    }
    
    private final String b(final int n) {
        return this.a(n & -1, 8);
    }
    
    private final String a(final int n, final int n2) {
        String s;
        for (s = Integer.toHexString(n & -1); s.length() < n2; s = "0".concat(String.valueOf(String.valueOf(s)))) {}
        return s.toUpperCase();
    }
    
    private final byte[] a(byte[] array, final int n, final dx dx, final abe abe) {
        try {
            int n2 = 0;
            int n3 = 0;
            int i = 1;
            final boolean b = true;
            while (i != 0) {
                i = 0;
                int n4 = 0;
                final byte[] array2 = new byte[n];
                int n5 = 0;
                for (int j = 0; j < n; ++j) {
                    final byte b2 = array[j];
                    boolean b3 = false;
                    String s = "";
                    boolean b4 = false;
                    if (this.c(b2)) {
                        int k = 0;
                        while (k < 50) {
                            ++j;
                            final byte b5 = array[j];
                            if (this.d(b5)) {
                                if (n3 != 0 && b) {
                                    b4 = true;
                                    break;
                                }
                                ++n2;
                                b3 = true;
                                break;
                            }
                            else {
                                if (this.c(b5)) {
                                    b3 = true;
                                    --j;
                                    break;
                                }
                                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)b5)));
                                ++k;
                            }
                        }
                        if (b4) {
                            array2[n4] = array[j];
                            if (array2[n4] == 10 || array2[n4] == 13) {
                                ++n5;
                            }
                            ++n4;
                        }
                        else if (b3) {
                            final byte b6 = array[j];
                            final byte b7 = array[j + 1];
                            if ((b6 == 73 || b6 == 105) && this.d(b7)) {
                                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)b6)));
                                j += 2;
                            }
                            final abg abg = new abg(b2, s);
                            if (abg.a == 11) {
                                this.a(abg, abe, n5);
                            }
                        }
                    }
                    else {
                        array2[n4] = array[j];
                        if (array2[n4] == 10 || array2[n4] == 13) {
                            ++n5;
                        }
                        ++n4;
                    }
                }
                if (n2 > 5 && n3 == 0 && n5 <= 1 && b) {
                    n2 = 0;
                    n3 = 1;
                    i = 1;
                }
                else {
                    array = new byte[n4];
                    System.arraycopy(array2, 0, array, 0, array.length);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
    
    private final int b(final hp hp) throws Exception {
        this.u = 0;
        this.p = this.e(hp);
        return (int)hp.f();
    }
    
    private final void a(final c c, final dx dx, final abe abe, final boolean b) {
        if (c != null) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "jiFilterFilenetCold: Re-parse Pcodes based on new Resolution ".concat(String.valueOf(String.valueOf(abe.h))));
            }
            this.a(c, dx, abe, false, b);
        }
    }
    
    private final void a(final c c, final dx dx, final abe abe) {
        if (c != null) {
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "jiFilterFilenetCold: Re-parse Pcodes based on new Resolution ".concat(String.valueOf(String.valueOf(abe.h))));
            }
            for (int b = c.b(), i = 0; i < b; ++i) {
                final abg abg = (abg)c.b(i);
                try {
                    if (abg.a == 23) {
                        this.b(abg, abe, true);
                    }
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
            }
        }
    }
    
    private final void a(final c c, final dx dx, final abe abe, final boolean b, final boolean b2) {
        final int b3 = c.b();
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, "jiFilterFilenetCold: Num pCodes: ".concat(String.valueOf(String.valueOf(b3))));
        }
        for (int i = 0; i < b3; ++i) {
            final abg abg = (abg)c.b(i);
            try {
                if (ji.util.d.co(this.l)) {
                    if (abg.a == 16) {
                        this.c(abg, abe);
                    }
                }
                else {
                    switch (abg.a) {
                        case 1: {
                            this.e(abg, abe);
                            break;
                        }
                        case 11: {
                            this.a(abg, abe, 0);
                            break;
                        }
                        case 13: {
                            this.b(abg, abe);
                            break;
                        }
                        case 16: {
                            this.c(abg, abe);
                            break;
                        }
                        case 17: {
                            this.d(abg, abe);
                            break;
                        }
                        case 18: {
                            this.a(abg, abe, b);
                            break;
                        }
                        case 19: {
                            this.a(abg, abe);
                            break;
                        }
                        case 22: {
                            this.f(abg, abe);
                            break;
                        }
                        case 23: {
                            this.b(abg, abe, b2);
                            break;
                        }
                        case 25: {
                            this.g(abg, abe);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {}
            if (!ji.util.d.co(this.l)) {
                if (abg.c == null) {
                    this.a(dx.bk, "pCode: ".concat(String.valueOf(String.valueOf((char)(abg.a + 65 - 1)))), "Not processed");
                }
                else {
                    this.a(dx.bk, "pCode: ".concat(String.valueOf(String.valueOf((char)(abg.a + 65 - 1)))), "".concat(String.valueOf(String.valueOf(abg.c))));
                }
            }
        }
    }
    
    private final int c(final hp hp) throws Exception {
        return hp.c() & 0xFF;
    }
    
    private final void d(final hp hp) throws Exception {
        hp.a(hp.f() - 1);
    }
    
    private final boolean c(final int n) {
        return n >= 1 && n <= 26 && n != 14 && n != 10 && n != 6;
    }
    
    private final boolean d(final int n) {
        return n == 10;
    }
    
    private final c e(final hp hp) {
        final c c = new c("jiFilterFilenetCold1");
        try {
            int i = 0;
            while (i == 0) {
                int n = this.c(hp);
                boolean b = false;
                if (n == 0) {
                    b = true;
                    n = this.c(hp);
                }
                if (this.c(n)) {
                    final int j = 50;
                    boolean b2 = false;
                    String s = "";
                    long n2 = hp.f();
                    while (j > 0) {
                        final int c2 = this.c(hp);
                        if (this.d(c2)) {
                            n2 = hp.f();
                            b2 = true;
                            break;
                        }
                        if (this.c(c2)) {
                            b2 = true;
                            this.d(hp);
                            break;
                        }
                        if (!this.d(c2)) {
                            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)c2)));
                        }
                        else {
                            n2 = hp.f();
                        }
                    }
                    if (b2) {
                        final int c3 = this.c(hp);
                        final int c4 = this.c(hp);
                        if ((c3 == 73 || c3 == 105) && this.d(c4)) {
                            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)c3)));
                        }
                        else {
                            this.d(hp);
                            this.d(hp);
                        }
                        c.c(new abg(n, s));
                    }
                    else {
                        i = 1;
                        hp.a(n2);
                    }
                }
                else {
                    i = 1;
                    this.d(hp);
                    if (!b) {
                        continue;
                    }
                    this.d(hp);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            int n3 = 0;
            for (int k = c.b() - 1; k >= 0; --k) {
                final abg abg = (abg)c.b(k);
                if (abg.a == 13 && !abg.a()) {
                    ++n3;
                }
            }
            if (n3 > 0) {
                this.u = n3 - 1;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return c;
    }
    
    private final long a(final String s, final long n) {
        long a = n;
        try {
            a = ji.util.d.a(s, a);
        }
        catch (Exception ex) {}
        return a;
    }
    
    private final int a(final String s, final int n) {
        int c = n;
        try {
            c = ji.util.d.c(s, c);
        }
        catch (Exception ex) {}
        return c;
    }
    
    private final int e(final String s) {
        return this.a(s, -1);
    }
    
    private final double a(final String s, final double n) {
        double a = n;
        try {
            a = ji.util.d.a(s, a);
        }
        catch (Exception ex) {}
        return a;
    }
    
    private final double f(final String s) {
        return this.a(s, -1.0);
    }
    
    private void a(final abg abg, final abe abe, final int n) {
        abe.k = abg.a(0, abe.k, 0, abe.h);
        abg.c = "Xpos <EQUALS> ".concat(String.valueOf(String.valueOf(abe.k)));
        abe.l = abg.a(1, abe.l, 0, abe.h);
        if (abg.c == null) {
            abg.c = "Ypos <EQUALS> ".concat(String.valueOf(String.valueOf(abe.l)));
        }
        else {
            abg.c = String.valueOf(String.valueOf(abg.c)).concat(String.valueOf(String.valueOf(", Ypos <EQUALS> ".concat(String.valueOf(String.valueOf(abe.l))))));
        }
        if (abe.av == null) {
            abe.av = new c("coldHeaderLineLocations");
        }
        final String value = String.valueOf(n);
        if (abe.av.d(value) != null) {
            abe.av.a(value);
        }
        abe.av.a(value, new ol(n, abe.k, abe.l));
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlK: ").append(abe.k).append(", ").append(abg.c).append(", ").append(abe.l))));
        }
    }
    
    private void a(final abg abg, final abe abe, final boolean b) {
        if (b) {
            final int e = this.e(abg.a(0));
            final int e2 = this.e(abg.a(1));
            if (e >= 0) {
                if (e < 2) {
                    if ((abe.g = e) == 0) {
                        if (e2 > 0) {
                            abe.h = e2;
                            abe.af = true;
                            abg.c = String.valueOf(String.valueOf(new StringBuffer("Resolution <EQUALS> ").append(abe.h).append("(expanded)")));
                        }
                        else {
                            abe.h = 100;
                        }
                    }
                    else {
                        if (e2 > 0) {
                            abe.h = e2;
                            abe.af = true;
                        }
                        else {
                            abe.h = 100;
                        }
                        abg.c = String.valueOf(String.valueOf(new StringBuffer("Resolution <EQUALS> ").append(abe.h).append("(not expanded)")));
                    }
                }
                else {
                    if (e > 0) {
                        abe.h = e;
                        abe.af = true;
                    }
                    else {
                        abe.h = 100;
                    }
                    abg.c = "Resolution <EQUALS> ".concat(String.valueOf(String.valueOf(abe.h)));
                }
            }
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlR: ").append(abg.c).append(", ").append(abe.h))));
            }
        }
        if (ji.util.i.c(169)) {
            abe.m = 0;
            abe.n = 0;
            abe.o = 0;
            abe.p = 0;
        }
        else {
            abe.m = (int)(0.5 * abe.h);
            abe.n = (int)(0.2 * abe.h);
            abe.o = (int)(0.4 * abe.h);
            abe.p = (int)(0.4 * abe.h);
        }
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlR: Margins ").append(abe.m).append(", ").append(abe.n).append(", ").append(abe.o).append(", ").append(abe.p))));
        }
    }
    
    private void a(final abg abg, final abe abe) {
        final double f = this.f(abg.a(0));
        final String b = abg.b(0);
        final boolean c = abg.c(0);
        if (b != null) {
            if (b.equals("i") && !c) {
                abe.c = 1.0 / f;
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Lines Per Inch <EQUALS> (").append(abg.a(0)).append(") ").append(abe.c)));
                abe.e = f;
                if (abe.c < 1.0) {
                    abe.c = abe.h / f;
                    abg.c = String.valueOf(String.valueOf(new StringBuffer("Lines Per Inch <EQUALS> (").append(f).append(") ").append(abe.c)));
                }
            }
            else {
                abe.c = abe.h / f;
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Lines Per Inch <EQUALS> (").append(f).append(") ").append(abe.c)));
            }
        }
        else {
            abe.c = abe.h / f;
            abg.c = String.valueOf(String.valueOf(new StringBuffer("Lines Per Inch <EQUALS> (").append(f).append(") ").append(abe.c)));
            abe.e = f;
        }
        if (abg.a(1) != null) {
            ji.io.h.d(this.l, "jiFilterFilenetCold: PCode S word spacing not supported: P".concat(String.valueOf(String.valueOf(abg.i))));
        }
        if (abg.a(2) != null) {
            ji.io.h.d(this.l, "jiFilterFilenetCold: PCode S character spacing not supported: P".concat(String.valueOf(String.valueOf(abg.i))));
        }
        abe.ag = true;
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlS: ").append(abg.c).append(", ").append(abe.e).append(", ").append(abe.c))));
        }
    }
    
    private void b(final abg abg, final abe abe) {
        if (!abg.a()) {
            return;
        }
        final String a = abg.a(0);
        if (a != null) {
            Label_0344: {
                switch (a.toCharArray()[0]) {
                    case '\r': {
                        break Label_0344;
                    }
                    case '\u0003': {
                        a.substring(1);
                        break;
                    }
                }
                abe.m = 0;
                abe.n = 0;
                abe.o = 0;
                abe.p = 0;
                int m = abg.a(0, abe.m, 0, abe.h);
                int n = abg.a(1, abe.n, 0, abe.h);
                int o = abg.a(2, abe.o, 0, abe.h);
                int p2 = abg.a(3, abe.p, 0, abe.h);
                if (m > 10000) {
                    m = this.e(abg.a(0));
                }
                if (n > 10000) {
                    n = this.e(abg.a(1));
                }
                if (o > 10000) {
                    o = this.e(abg.a(2));
                }
                if (p2 > 10000) {
                    p2 = this.e(abg.a(3));
                }
                if (m > 0) {
                    abe.m = m;
                }
                if (n > 0) {
                    abe.n = n;
                }
                if (o > 0) {
                    abe.o = o;
                }
                if (p2 > 0) {
                    abe.p = p2;
                }
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Left <EQUALS> ").append(abe.m).append(", Right <EQUALS> ").append(abe.n).append(", Top <EQUALS> ").append(abe.o).append(", Bottom <EQUALS> ").append(abe.p)));
                this.o = true;
            }
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlM: ").append(abg.c).append(", ").append(abe.m).append(", ").append(abe.n).append(", ").append(abe.o).append(", ").append(abe.p))));
            }
        }
    }
    
    private void c(final abg abg, final abe abe) {
        final String a = abg.a(0);
        int n = 0;
        final char[] charArray = a.toCharArray();
        if (charArray[0] == '\u0007') {
            ji.io.h.d(this.l, "jiFilterFilenetCold: PCode P image filename not supported: P".concat(String.valueOf(String.valueOf(abg.i))));
            n = 1;
        }
        if (n == 0 && a.indexOf(14, 1) > 0 && a.indexOf(14, 1) < a.length() - 1) {
            final StringBuffer sb = new StringBuffer();
            final StringBuffer sb2 = new StringBuffer();
            int n2 = 0;
            for (int i = 0; i < charArray.length; ++i) {
                try {
                    if (i > 0 && charArray[i] == '\u000e') {
                        if (n2 == 0) {
                            n2 = 1;
                        }
                    }
                    else if (n2 != 0) {
                        sb.append(charArray[i]);
                    }
                    else {
                        sb2.append(charArray[i]);
                    }
                }
                catch (Exception ex) {}
            }
            abe.al = this.a(sb2.toString(), 0L);
            abe.am = this.a(sb.toString(), 0);
            abg.c = String.valueOf(String.valueOf(new StringBuffer("DocId <EQUALS> ").append(abe.al).append(", Page <EQUALS> ").append(abe.am)));
            n = 1;
        }
        if (n == 0) {
            abe.al = this.a(abg.a(0), 0L);
            abe.am = this.a(abg.a(1), 0);
            abg.c = String.valueOf(String.valueOf(new StringBuffer("DocId <EQUALS> ").append(abe.al).append(", Page <EQUALS> ").append(abe.am)));
        }
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlP: ").append(abg.c).append(", ").append(abe.al).append(", ").append(abe.am))));
        }
    }
    
    private void d(final abg abg, final abe abe) {
        abg.b();
        final String a = abg.a(0);
        if (a != null) {
            final char[] charArray = a.toCharArray();
            if (charArray[0] == 'i' || charArray[0] == 'I') {
                abe.ap = this.a(abg.a(a.substring(1)), 0);
                abe.aq = this.a(abg.a(1), 0);
                abe.ar = this.a(abg.a(1), 0);
                abe.as = this.a(abg.a(3), 0);
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Scale <EQUALS> ").append(abe.ap).append(", fineX <EQUALS> ").append(abe.aq).append(", fineY <EQUALS> ").append(abe.ar).append(", rotation <EQUALS> ").append(abe.as)));
            }
            else {
                ji.io.h.d(this.l, "jiFilterFilenetCold: PCode Q format not supported: Q".concat(String.valueOf(String.valueOf(abg.i))));
            }
        }
        if (ji.util.i.c(5)) {
            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlQ: ").append(abg.c).append(", ").append(abe.ap).append(", ").append(abe.aq).append(", ").append(abe.ar).append(", ").append(abe.as))));
        }
    }
    
    private void b(final abg abg, final abe abe, final boolean b) {
        final String a = abg.a(0);
        if (ji.util.d.co(this.l)) {
            return;
        }
        if (a != null) {
            String s = "Courier";
            int t = 16;
            double v = 1.0;
            double w = 1.0;
            double n = 6.2;
            double y = 100.0;
            double z = 1.0;
            double aa = 1.0;
            double ab = 1.0;
            double d = 0.0;
            boolean ac = true;
            double ad = 1.0;
            double aj = 0.0;
            boolean ae = false;
            double ah = 0.0;
            boolean ai = false;
            int n2 = 0;
            Label_1664: {
                try {
                    final String c = ji.util.d.c(a.toLowerCase(), true);
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: Looking for match of ").append(c).append("..."))));
                    }
                    if (b) {
                        for (int i = 0; i < ff.f.length; i += 18) {
                            if (ji.util.d.bc(ff.f[i].toLowerCase()).equals(c)) {
                                s = ff.f[i + 2];
                                t = this.a(ff.f[i + 3], 16);
                                v = this.a(ff.f[i + 4], 1.72);
                                w = this.a(ff.f[i + 5], 1.72);
                                n = this.a(ff.f[i + 6], 6.2);
                                y = this.a(ff.f[i + 7], 0.0);
                                z = this.a(ff.f[i + 8], 1.0);
                                aa = this.a(ff.f[i + 9], 1.0);
                                ab = this.a(ff.f[i + 10], 1.0);
                                d = this.a(ff.f[i + 11], 0.0);
                                ac = (this.a(ff.f[i + 12], 0) == 1);
                                ad = this.a(ff.f[i + 13], 1.0);
                                ae = (this.a(ff.f[i + 14], 0) == 1);
                                ah = this.a(ff.f[i + 15], 0.0);
                                ai = false;
                                if (this.a(ff.f[i + 16], 0) == 1) {
                                    ai = true;
                                }
                                aj = this.a(ff.f[i + 17], 0.0);
                                if (ji.util.i.c(5)) {
                                    ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: Found PreFix2 ").append(ff.f[i]).append("..."))));
                                }
                                n2 = 1;
                                break;
                            }
                        }
                    }
                    if (n2 == 0) {
                        if (!ji.util.i.c(189)) {
                            for (int j = 0; j < ff.d.length; j += 18) {
                                if (ji.util.d.bc(ff.d[j].toLowerCase()).equals(c)) {
                                    s = ff.d[j + 2];
                                    t = this.a(ff.d[j + 3], 16);
                                    v = this.a(ff.d[j + 4], 1.72);
                                    w = this.a(ff.d[j + 5], 1.72);
                                    n = this.a(ff.d[j + 6], 6.2);
                                    y = this.a(ff.d[j + 7], 0.0);
                                    z = this.a(ff.d[j + 8], 1.0);
                                    aa = this.a(ff.d[j + 9], 1.0);
                                    ab = this.a(ff.d[j + 10], 1.0);
                                    d = this.a(ff.d[j + 11], 0.0);
                                    ac = (this.a(ff.d[j + 12], 0) == 1);
                                    ad = this.a(ff.d[j + 13], 1.0);
                                    ae = (this.a(ff.d[j + 14], 0) == 1);
                                    ah = this.a(ff.d[j + 15], 0.0);
                                    ai = false;
                                    if (this.a(ff.d[j + 16], 0) == 1) {
                                        ai = true;
                                    }
                                    aj = this.a(ff.d[j + 17], 0.0);
                                    if (ji.util.i.c(5)) {
                                        ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: Found PreFix ").append(ff.d[j]).append("..."))));
                                    }
                                    n2 = 1;
                                    break;
                                }
                            }
                        }
                        if (!ji.util.i.c(211)) {
                            for (int k = 0; k < ff.e.length; k += 18) {
                                if (ji.util.d.bc(ff.e[k].toLowerCase()).equals(c)) {
                                    s = ff.e[k + 2];
                                    t = this.a(ff.e[k + 3], 16);
                                    v = this.a(ff.e[k + 4], 1.72);
                                    w = this.a(ff.e[k + 5], 1.72);
                                    n = this.a(ff.e[k + 6], 6.2);
                                    y = this.a(ff.e[k + 7], 0.0);
                                    z = this.a(ff.e[k + 8], 1.0);
                                    aa = this.a(ff.e[k + 9], 1.0);
                                    ab = this.a(ff.e[k + 10], 1.0);
                                    d = this.a(ff.e[k + 11], 0.0);
                                    ac = (this.a(ff.e[k + 12], 0) == 1);
                                    ad = this.a(ff.e[k + 13], 1.0);
                                    ae = (this.a(ff.e[k + 14], 0) == 1);
                                    ah = this.a(ff.e[k + 15], 0.0);
                                    ai = false;
                                    if (this.a(ff.e[k + 16], 0) == 1) {
                                        ai = true;
                                    }
                                    aj = this.a(ff.e[k + 17], 0.0);
                                    if (ji.util.i.c(5)) {
                                        ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: Found PreFix1 ").append(ff.e[k]).append("..."))));
                                    }
                                    n2 = 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (n2 == 0) {
                        Block_32: {
                            for (int l = 0; l < ff.c.length; l += 18) {
                                if (ji.util.d.bc(ff.c[l].toLowerCase()).equals(c)) {
                                    break Block_32;
                                }
                            }
                            break Label_1664;
                        }
                        int l = 0;
                        s = ff.c[l + 2];
                        t = this.a(ff.c[l + 3], 16);
                        v = this.a(ff.c[l + 4], 1.72);
                        w = this.a(ff.c[l + 5], 1.72);
                        n = this.a(ff.c[l + 6], 6.2);
                        y = this.a(ff.c[l + 7], 0.0);
                        z = this.a(ff.c[l + 8], 1.0);
                        aa = this.a(ff.c[l + 9], 1.0);
                        ab = this.a(ff.c[l + 10], 1.0);
                        d = this.a(ff.c[l + 11], 0.0);
                        ac = (this.a(ff.c[l + 12], 0) == 1);
                        ad = this.a(ff.c[l + 13], 1.0);
                        ae = (this.a(ff.c[l + 14], 0) == 1);
                        ah = this.a(ff.c[l + 15], 0.0);
                        ai = false;
                        if (this.a(ff.c[l + 16], 0) == 1) {
                            ai = true;
                        }
                        aj = this.a(ff.c[l + 17], 0.0);
                        if (ji.util.i.c(5)) {
                            ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: Found ").append(ff.c[l]).append("..."))));
                        }
                        n2 = 1;
                    }
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
            }
            abe.s = s;
            abe.t = t;
            abe.v = v;
            abe.w = w;
            abe.x = n;
            abe.y = y;
            abe.z = z;
            abe.aa = aa;
            abe.ab = ab;
            abe.d = d;
            abe.x = n;
            abe.ac = ac;
            abe.ad = ad;
            abe.ae = ae;
            abe.ah = ah;
            abe.ai = ai;
            abe.aj = aj;
            if (n2 != 0) {
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Font <EQUALS> ").append(abe.s).append(" ").append(abe.t).append(" (closest to ").append(a).append(")")));
            }
            else {
                abg.c = String.valueOf(String.valueOf(new StringBuffer("Font <EQUALS> ").append(abe.s).append(" ").append(abe.t).append(" (*BUT NOT MATCHED* ").append(a).append(")")));
            }
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 1: ".concat(String.valueOf(String.valueOf(a))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 2: ".concat(String.valueOf(String.valueOf(abg.c))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 3: ".concat(String.valueOf(String.valueOf(abe.s))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 4: ".concat(String.valueOf(String.valueOf(abe.t))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 5: ".concat(String.valueOf(String.valueOf(abe.v))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 6: ".concat(String.valueOf(String.valueOf(abe.w))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 7: ".concat(String.valueOf(String.valueOf(abe.x))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 8: ".concat(String.valueOf(String.valueOf(abe.y))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW 9: ".concat(String.valueOf(String.valueOf(abe.z))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW A: ".concat(String.valueOf(String.valueOf(abe.aa))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW B: ".concat(String.valueOf(String.valueOf(abe.ab))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW C: ".concat(String.valueOf(String.valueOf(abe.d))));
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlW D: ".concat(String.valueOf(String.valueOf(abe.x))));
            }
        }
    }
    
    private void e(final abg abg, final abe abe) {
        final String a = abg.a(0);
        if (a != null) {
            abe.f = this.a(a, 0);
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlA: ").append(a).append(", ").append(abe.f))));
            }
        }
    }
    
    private void f(final abg abg, final abe abe) {
        final String a = abg.a(0);
        if (a != null) {
            abe.j = this.a(a, 0);
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlV: ").append(a).append(", ").append(abe.j))));
            }
        }
    }
    
    private void g(final abg abg, final abe abe) {
        final String a = abg.a(0);
        if (!abg.d(0)) {
            abe.q = abg.a(0, abe.q, -1, abe.h);
            final String a2 = abg.a(1);
            abe.r = abg.a(1, abe.r, -1, abe.h);
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("jiFilterFilenetCold: COLD Header: CtrlY: ").append(a).append(", ").append(a2).append(" - ").append(abe.q).append(", ").append(abe.r))));
            }
        }
        else {
            abe.at = this.a(a, -99);
            if (ji.util.i.c(5)) {
                ji.io.h.d(this.l, "jiFilterFilenetCold: COLD Header: CtrlY: Default ".concat(String.valueOf(String.valueOf(abe.at))));
            }
        }
    }
    
    public final boolean e() {
        return false;
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        this.n = true;
        try {
            this.k = false;
            if (!this.j) {
                throw new Exception(ji.util.d.b(335, this.l));
            }
            try {
                if (ji.util.d.co(this.l)) {
                    if (ji.util.i.c(210)) {
                        fh.c.b(1, false, fh.o);
                    }
                    else {
                        fh.c.b(4, false, fh.o);
                    }
                    if (this.i != null) {
                        this.i.fillDib(fh, this.l);
                    }
                }
                else {
                    fh.c.b(1, true, fh.o);
                    ac ac = null;
                    try {
                        final abe abe = (abe)fh.d.ax;
                        this.a(fh.d, abe, fh, true);
                        ac = new ac(abe.a, false, false, 0, false, fh.o, this.l);
                        this.g.a(ac, fh.c, fh.d, fh.g, fh.o);
                    }
                    finally {
                        if (ac != null) {
                            try {
                                ac.a(fh.o);
                            }
                            catch (Exception ex2) {}
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    if (this.h != null) {
                        this.h.a();
                        this.h = null;
                    }
                    if (this.g != null) {
                        this.g.a(fh.d);
                        this.g = null;
                    }
                }
                catch (Exception ex3) {}
            }
        }
        finally {
            this.n = false;
        }
        if (this.k) {
            fh.c.a(fh.o);
        }
    }
    
    public final boolean isAborted(final dx dx, final String s) {
        return this.k;
    }
    
    public final void clearAbort(final dx dx, final String s) {
        this.k = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (ji.util.e.ai()) {
                this.k = true;
                if (this.g != null) {
                    this.g.g();
                }
                if (this.i != null) {
                    this.i.abort(dx);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        try {
            ff.r = true;
            this.c();
            if (dx != null) {
                final abe abe = (abe)dx.ax;
                if (abe != null && abe.a != null) {
                    ac.c(abe.a, this.l);
                    abe.a = null;
                }
            }
            this.j = false;
            this.m = null;
            if (this.h != null) {
                this.h.a();
                this.h = null;
            }
            if (this.g != null) {
                this.g.a(dx);
                this.g = null;
            }
            if (this.i != null) {
                this.i.close(dx);
                this.i = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c() {
        try {
            if (this.s != null) {
                ac.c(this.s, this.l);
                this.s = null;
            }
            if (this.t != null) {
                ac.c(this.t, this.l);
                this.t = null;
            }
            if (this.h != null) {
                this.h.a();
                this.h = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void g(final String s) {
        try {
            if (this.v == null) {
                this.v = new p(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean g() {
        return this.i != null && this.i.isAborted();
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        hp hp = null;
        try {
            hp = new hp(ac, 0);
            if (hp.g() < 100 || hp.g() < 96) {
                return 0;
            }
            final abf a = new ff().a(hp);
            if (a == null) {
                return 0;
            }
            switch (a.a & 0xFF) {
                case 197:
                case 198:
                case 213:
                case 214: {
                    break;
                }
                default: {
                    return 0;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        finally {
            if (hp != null) {
                hp.a();
            }
        }
        return 2;
    }
    
    public String c(final String s) {
        return s.a(1235, s);
    }
    
    static {
        a = new String[] { "17", "AHALF", "5.5", "8.5", "4", "LETTER", "8.5", "11.0", "0", "LEGAL", "8.5", "14.0", "54", "10x14", "10.0", "14.0", "6", "A5", "5.83", "8.27", "3", "A4", "8.27", "11.69", "50", "A3", "11.69", "16.54", "51", "A2", "16.54", "23.39", "52", "A1", "23.39", "33.11", "53", "A0", "33.11", "46.81", "5", "B5", "7.19", "10.13", "1", "B4", "10.13", "14.38", "18", "B", "11.0", "17.0", "19", "C", "17.0", "22.0", "20", "C+", "18.0", "24.0", "21", "D", "22.0", "34.0", "22", "E", "34.0", "44.0", "23", "E+", "34.0", "56.0", "-5", "BEST", "-99", "-99", "-4", "DON'T CARE", "-99.0", "-99.0" };
        c = new String[] { "laser/mono400", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "400.0", "1.0", "1.0", "1.5", "1.25", "1", "1.0", "0", "0.0", "0", "0", "laser/Mono100", "Monospaced", "Courier", "10", "1.25", "1.0", "6.2", "100.0", "1.0", "1.0", "1.5", "1.25", "1", "1.0", "0", "0.0", "0", "0", "laser/mono200", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "200.0", "1.0", "1.0", "1.5", "1.25", "1", "1.0", "0", "0.0", "0", "0.83", "laser/Mono.100", "Monospaced", "Courier", "10", "1.25", "1.0", "6.2", "100.0", "1.0", "1.0", "1.5", "1.25", "1", "1.0", "0", "0.0", "0", "0", "laser/Mono.200", "Monospaced", "Courier", "16", "0.97", "1.0", "6.2", "200.0", "2", "1.0", "0.6", "1.27", "1", "1.0", "0", "0.0", "0", "0.83", "laser/default", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "default", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/Monolg07", "Monospaced", "Courier", "12", "1.0", "1.0", "8.5", "150.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "Monolg07", "Monospaced", "Courier", "12", "0.96", "1.0", "6.2", "200.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mlg05.8", "Monospaced", "Courier", "10", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mlg05.8", "Monospaced", "Courier", "10", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mlg08.0", "Monospaced", "Courier", "14", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mlg08.0", "Monospaced", "Courier", "14", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mlg08.6", "Monospaced", "Courier", "12", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mlg08.6", "Monospaced", "Courier", "12", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mlg09.4", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mlg09.4", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mlg11.5", "Monospaced", "Courier", "20", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mlg011.5", "Monospaced", "Courier", "20", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "laser/COLD20x32", "Monospaced", "Courier", "20", "1.255", "1.0", "3.1", "150", "1.0", "2.0", "1.5", "1.56", "1", "1.0", "0", "100.0", "0", "0", "COLD20x32", "Monospaced", "Courier", "20", "1.255", "1.0", "3.1", "200.0", "1.0", "2.0", "1.5", "1.56", "1", "1.0", "0", "0.0", "0", "0", "flaser/default", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "flaser/ANTfont", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "Mono.100", "Monospaced", "Courier", "10", "1.25", "1.0", "6.2", "100.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "Mono.200", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "200.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "ANT.fnt.200", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "200.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "flaser/clas", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "flaser/mono", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "laser/Mono", "Monospaced", "Courier", "20", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "Mono", "Monospaced", "Courier", "20", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "laser/annot", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "annot", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "400/Mono10", "Monospaced", "Courier", "16", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "200/mptge09.4", "Helvetica", "Courier", "17", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "mptge09.4", "Helvetica", "Courier", "17", "1.0", "1.0", "6.2", "0.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*5monospace", "Monospaced", "Courier", "17", "1.255", "1.0", "3.1", "200.0", "0.0", "2.0", "1.2", "0.8", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*6monospace", "Monospaced", "Courier", "17", "0.75", "0.75", "8", "200", "1.0", "1.0", "1", "1", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*7monospace", "Monospaced", "Courier", "17", "0.85", "1.0", "6.2", "150.0", "1.5", "1.0", "1.5", "1.12", "1", "1.015", "1", "0.0", "0", "0", "couriermediumroman*8monospace", "Monospaced", "Courier", "17", "0.98", "1.0", "6.2", "175.0", "1.5", "1.0", "1.5", "1.28", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*9monospace", "Monospaced", "Courier", "17", "1.185", "1.0", "6.2", "185.0", "1.0", "1.0", "1.5", "1.45", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*10monospace", "Monospaced", "Courier", "17", "1.18", "1.0", "5.95", "210.0", "1.0", "1.0", "2.3", "0.96", "1", "1.0", "0", "0.0", "1", "0", "couriermediumroman*11monospace", "Monospaced", "Courier", "17", "1.215", "1.0", "6.2", "225.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*12monospace", "Monospaced", "Courier", "17", "1.20", "1.0", "6.2", "250.0", "1.0", "1.0", "1.5", "0.96", "0", "1.0", "0", "0.0", "0", "0" };
        d = new String[] { "couriermediumroman*5monospace", "Monospaced", "Courier", "17", "1.255", "1.0", "3.1", "200.0", "1.0", "2.0", "1.5", "0.8", "1", "1.0", "0", "0.0", "0", "0", "couriermediumroman*7monospace", "Monospaced", "Courier", "17", "0.85", "1.0", "6.2", "150.0", "1.0", "1.0", "1", "1.12", "1", "1.015", "1", "0.0", "0", "0" };
        e = new String[] { "couriermediumroman*10monospace", "Monospaced", "Courier", "17", "1.17", "1.0", "6.2", "210.0", "1.0", "1.0", "1.5", "0.96", "1", "1.0", "0", "0.0", "0", "0" };
        f = new String[] { "couriermediumroman*7monospace", "Monospaced", "Courier", "17", "1.15", "1.0", "8.5", "150.0", "1.5", "1.0", "1.5", "1.12", "1", "1.015", "1", "0.0", "1", "0", "couriermediumroman*10monospace", "Monospaced", "Courier", "17", "1.18", "1.0", "5.95", "210.0", "1.0", "1.0", "2.3", "0.96", "1", "1.0", "0", "0.0", "1", "0" };
        ff.q = false;
        ff.r = true;
    }
    
    class abg
    {
        int a;
        String b;
        String c;
        c d;
        c e;
        c f;
        boolean g;
        boolean h;
        String i;
        String j;
        
        public String toString() {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf((char)(65 + this.a - 1)))).append(">").append(this.b)));
        }
        
        public boolean a() {
            return this.b != null && this.b.length() > 0;
        }
        
        abg(final ff ff, final int a, final String b) {
            this.a = 0;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = false;
            this.i = "";
            this.j = "";
            this.a = a;
            this.b = b;
            final char[] charArray = b.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] < ' ') {
                    this.i = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append("<").append((char)(charArray[i] + 'A' - '\u0001')).append(">")));
                }
                else {
                    this.i = String.valueOf(String.valueOf(this.i)).concat(String.valueOf(String.valueOf(charArray[i])));
                }
            }
        }
        
        final int b() {
            this.c();
            if (this.d == null) {
                return 0;
            }
            return this.d.b();
        }
        
        final void c() {
            if (!this.h) {
                if (this.b != null) {
                    if (this.d == null) {
                        this.d = new c("jiFilterFilenetCold2");
                    }
                    if (this.e == null) {
                        this.e = new c("jiFilterFilenetCold3");
                    }
                    if (this.f == null) {
                        this.f = new c("jiFilterFilenetCold4");
                    }
                    String s = ji.util.d.bc(this.b);
                    int i = s.indexOf(",");
                    this.j = "";
                    if (i >= 0) {
                        while (i >= 0) {
                            this.d.c(this.a(s.substring(0, i)));
                            this.e.c(this.j);
                            this.f.c(new Boolean(this.g));
                            s = s.substring(i + 1);
                            i = s.indexOf(",");
                        }
                        this.d.c(this.a(s));
                        this.e.c(this.j);
                        this.f.c(new Boolean(this.g));
                    }
                    else {
                        this.d.c(this.a(s));
                        this.e.c(this.j);
                        this.f.c(new Boolean(this.g));
                    }
                }
                this.h = true;
            }
        }
        
        final String a(final String s) {
            try {
                this.j = "";
                this.g = false;
                if (s != null) {
                    final char[] charArray = s.toCharArray();
                    int n = 0;
                    int n2 = charArray.length - 1;
                    boolean b = false;
                    if (charArray[n] == '\u000e') {
                        ++n;
                    }
                    if (charArray.length > n + 1 && charArray[n] == '0' && (charArray[n + 1] == 'x' || charArray[n + 1] == 'X')) {
                        n += 2;
                        this.j = "hex";
                        b = true;
                    }
                    if (charArray[n] == 'x') {
                        ++n;
                        this.j = "hex";
                        b = true;
                    }
                    if (charArray[n2] == '\t') {
                        this.j = "i";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'i') {
                        this.j = "i";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'I') {
                        this.j = "i";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'c') {
                        this.j = "c";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'C') {
                        this.j = "c";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'd') {
                        this.j = "d";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'D') {
                        this.j = "d";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'h') {
                        this.j = "h";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'H') {
                        this.j = "h";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'l') {
                        this.j = "l";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'L') {
                        this.j = "l";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'L') {
                        this.j = "l";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'm') {
                        this.j = "m";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'M') {
                        this.j = "m";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'n') {
                        this.j = "n";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'N') {
                        this.j = "n";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'p') {
                        this.j = "p";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'P') {
                        this.j = "p";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'w') {
                        this.j = "w";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == 'W') {
                        this.j = "w";
                        --n2;
                        b = true;
                    }
                    if (charArray[n2] == '*') {
                        this.j = "*";
                        --n2;
                        b = true;
                    }
                    if (!b && s.indexOf(".") >= 0) {
                        this.j = "i";
                        this.g = true;
                    }
                    return ji.util.d.bc(s.substring(n, n2 + 1));
                }
                return "";
            }
            catch (Exception ex) {
                return "";
            }
        }
        
        final String a(final int n) {
            this.c();
            if (this.d == null) {
                return null;
            }
            if (this.d.b() > n) {
                return (String)this.d.b(n);
            }
            return null;
        }
        
        final String b(final int n) {
            this.c();
            if (this.e == null) {
                return null;
            }
            if (this.e.b() > n) {
                return (String)this.e.b(n);
            }
            return null;
        }
        
        final boolean c(final int n) {
            this.c();
            return this.f != null && this.f.b() > n && (boolean)this.f.b(n);
        }
        
        final int a(final int n, final int n2, final int n3, double n4) {
            final String b = this.b(n);
            final boolean c = this.c(n);
            if (b == null) {
                return ji.util.d.c(this.a(n), n3);
            }
            if (n4 == 0.0) {
                n4 = 1.0;
            }
            if (b.equals("i") && !c) {
                return (int)(ji.util.d.a(this.a(n), (double)n3) * n4);
            }
            if (b.equals("c")) {
                return (int)(ji.util.d.a(this.a(n), (double)n3) / 2.54 * n4);
            }
            if (b.equals("d")) {
                return (int)ji.util.d.a(this.a(n), (double)n3);
            }
            if (b.equals("*")) {
                return n2 * ji.util.d.c(this.a(n), n3);
            }
            return ji.util.d.c(this.a(n), n3);
        }
        
        final boolean d(final int n) {
            this.b(n);
            return !this.j.equals("c") && !this.j.equals("d") && !this.j.equals("i");
        }
    }
    
    class abe
    {
        String a;
        double b;
        double c;
        double d;
        double e;
        float f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int o;
        int p;
        int q;
        int r;
        String s;
        int t;
        int u;
        double v;
        double w;
        double x;
        double y;
        double z;
        double aa;
        double ab;
        boolean ac;
        double ad;
        boolean ae;
        boolean af;
        boolean ag;
        double ah;
        boolean ai;
        double aj;
        int ak;
        long al;
        int am;
        int an;
        String ao;
        int ap;
        int aq;
        int ar;
        int as;
        int at;
        String au;
        c av;
        
        abe() {
            this.a = null;
            this.b = 0.0;
            this.c = 0.0;
            this.d = 0.0;
            this.e = 0.0;
            this.f = 0.0f;
            this.g = 0;
            this.h = 100;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
            this.q = -1;
            this.r = -1;
            this.s = null;
            this.t = 16;
            this.u = 0;
            this.v = 1.72;
            this.w = 1.72;
            this.x = 6.2;
            this.y = 0.0;
            this.z = 1.0;
            this.aa = 1.0;
            this.ab = 1.0;
            this.ac = true;
            this.ad = 1.0;
            this.ae = false;
            this.af = false;
            this.ag = false;
            this.ah = 0.0;
            this.ai = false;
            this.aj = 0.0;
            this.at = -99;
            this.au = null;
            this.av = null;
        }
        
        public abe a() {
            final abe abe = new abe();
            abe.a = this.a;
            abe.f = this.f;
            abe.h = this.h;
            abe.at = this.at;
            abe.q = this.q;
            abe.r = this.r;
            abe.i = this.i;
            abe.b = this.b;
            abe.c = this.c;
            abe.d = this.d;
            abe.e = this.e;
            abe.x = this.x;
            abe.z = this.z;
            abe.aa = this.aa;
            abe.ac = this.ac;
            abe.ab = this.ab;
            abe.j = this.j;
            abe.k = this.k;
            abe.l = this.l;
            abe.m = this.m;
            abe.n = this.n;
            abe.o = this.o;
            abe.p = this.p;
            abe.s = this.s;
            abe.t = this.t;
            abe.u = this.u;
            abe.ak = this.ak;
            abe.al = this.al;
            abe.am = this.am;
            abe.an = this.an;
            abe.ao = this.ao;
            abe.ap = this.ap;
            abe.aq = this.aq;
            abe.ar = this.ar;
            abe.as = this.as;
            abe.au = this.au;
            abe.av = this.av;
            abe.v = this.v;
            abe.w = this.w;
            abe.y = this.y;
            abe.ae = this.ae;
            abe.af = this.af;
            abe.ag = this.ag;
            abe.ah = this.ah;
            abe.ai = this.ai;
            abe.aj = this.aj;
            return abe;
        }
    }
    
    class abf
    {
        byte a;
        byte b;
        byte c;
        byte d;
        long e;
        long f;
        long g;
        long h;
        long i;
        long j;
        long k;
        long l;
        long m;
        long n;
        long o;
        long p;
        long q;
        long r;
        long s;
        long t;
        long u;
        long v;
        long w;
        String x;
        
        abf(final ff ff) {
            this.x = null;
        }
    }
}
