// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.filter.e5;
import ji.filter.fh;
import ji.io.q;
import ji.awt.c;
import ji.image.ev;
import java.awt.Dimension;
import ji.filter.ew;
import ji.v1event.ao;
import ji.v1event.af;
import java.net.URL;
import java.applet.Applet;
import ji.util.d;
import ji.util.e;
import ji.io.h;
import ji.util.i;
import ji.net.a0;
import ji.document.ad;
import ji.io.ac;
import ji.image.dx;
import ji.image.ds;

class hw implements ht
{
    private ds a;
    private dx b;
    private byte[] c;
    private Object d;
    private ac e;
    
    public hw(final dx dx, String substring, final ad d, final String s, final a0 a0, final boolean b) {
        this.d = null;
        this.e = null;
        try {
            if (i.c(5)) {
                h.d(s, "WANG: Processing annotation Image: ".concat(String.valueOf(String.valueOf(substring))));
            }
            this.d = d;
            boolean b2 = false;
            final String a2 = ji.util.d.a(ji.util.e.an(), substring, s);
            String value;
            if (a2 != null && substring.indexOf("\\") == -1) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(ji.util.d.bu(s)).append(substring)));
            }
            else {
                value = substring;
            }
            if (value.indexOf("file:") != -1) {
                b2 = true;
                substring = value.substring(value.indexOf("file:") + 5);
            }
            final Applet applet = (Applet)ji.util.d.y(s);
            String h;
            if (!b2 && (ji.util.d.bj(substring) || applet != null)) {
                if (applet != null) {
                    h = a0.b(new URL(applet.getCodeBase(), substring), false, true, null, d, null, d);
                }
                else {
                    h = a0.b(new URL(substring), false, true, null, d, null, d);
                }
            }
            else if (ac.d(substring, s)) {
                h = a0.a(substring, null, d, null);
            }
            else {
                h = ji.util.d.a(ji.util.e.an(), substring, s);
                if (h != null) {
                    h = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(h))).append(ji.util.d.bu(s)).append(substring)));
                }
            }
            if (i.c(5)) {
                ji.io.h.d(s, "WANG: Processing annotation image: source: ".concat(String.valueOf(String.valueOf(h))));
            }
            if (this.e != null) {
                this.e.a(d);
            }
            this.e = new ac(h, false, false, 0, d, s);
            try {
                this.b = ew.a(substring, this.e, substring, substring, null, false, d, null, null, s, dx.a0, dx.m, dx.n, 1, true, -1, null, b, false, false, null, null, null, false, false, false, false);
                if (this.b != null) {
                    if (i.c(5)) {
                        ji.io.h.d(s, "WANG: Processing annotation image: filter: ".concat(String.valueOf(String.valueOf(this.b.bj))));
                    }
                    this.b.h = h;
                    final String o = q.a(d, s).o();
                    if (o != null) {
                        try {
                            if (this.a != null) {
                                this.a.b(d);
                            }
                        }
                        catch (Exception ex2) {}
                        this.a = new ds("tempDib", s);
                        if (this.b.c()) {
                            final int m = (int)this.b.a((long)this.b.m);
                            final int n = (int)this.b.b((long)this.b.n);
                            this.b.a(1);
                            this.b.m = m;
                            this.b.n = n;
                        }
                        final int i = this.b.m;
                        final int n2 = this.b.n;
                        this.a.a(o, i, n2, 0, this.b.am, d, this.b);
                        this.b.bj.fillDibInternal(new fh(this.e, this.a, this.b, d, null, false, 0, 0, s, -1, null, b, false, ji.util.i.c(164)));
                        this.a.a(this.b.bj.getPalette(this.e, this.b, s), this.b);
                        this.a.a(true, d);
                        if (ji.util.i.c(5)) {
                            ji.io.h.d(s, "WANG: Saving annotation, image width = ".concat(String.valueOf(String.valueOf(i))));
                        }
                        if (ji.util.i.c(5)) {
                            ji.io.h.d(s, "WANG: Saving annotation, image height = ".concat(String.valueOf(String.valueOf(n2))));
                        }
                    }
                    this.b.bj.close(this.b, d);
                }
            }
            finally {}
        }
        catch (Exception ex) {
            h.a(s, ex);
            this.c = null;
        }
    }
    
    public int b() {
        int n = 0;
        int n4 = 0;
        switch (this.b.am) {
            case 1: {
                int n2 = this.b.m * 3;
                final int n3 = n2 % 4;
                if (n3 > 0) {
                    n2 += 4 - n3;
                }
                n4 = this.b.n * n2;
                n = 8;
                break;
            }
            case 4: {
                int n5 = this.b.m / 2;
                final int n6 = this.b.m % 2;
                if (n6 > 0) {
                    n5 += 2 - n6;
                }
                int n7 = n5;
                if (n7 % 4 > 0) {
                    n7 = n7 + 4 - n7 % 4;
                }
                n4 = n7 * this.b.n;
                n = 64;
                break;
            }
            case 8: {
                int m = this.b.m;
                final int n8 = this.b.m % 2;
                if (n8 > 0) {
                    m += 2 - n8;
                }
                int n9 = m;
                if (n9 % 4 > 0) {
                    n9 = n9 + 4 - n9 % 4;
                }
                n4 = n9 * this.b.n;
                n = 1024;
                break;
            }
            default: {
                int n10 = this.b.m * 3;
                final int n11 = n10 % 4;
                if (n11 > 0) {
                    n10 += 4 - n11;
                }
                n4 = this.b.n * n10;
                break;
            }
        }
        return n4 + n + 124;
    }
    
    public String c() {
        return "OiDIB";
    }
    
    public byte[] d() {
        return this.c;
    }
    
    public hw(final ac ac, final int n, final String s) throws Exception {
        this.d = null;
        this.e = null;
        ac.a(this.c = new byte[n]);
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation image size : ".concat(String.valueOf(String.valueOf(ji.util.d.a((long)n, s)))));
        }
    }
    
    public int a(final ac ac, final String s) throws Exception {
        int length = 0;
        if (this.b != null && this.a != null) {
            try {
                final byte[] a = new e5().a(this.b, this.a);
                if (a != null) {
                    length = a.length;
                    ac.b(a);
                }
            }
            catch (Exception ex) {
                h.a(s, ex);
            }
        }
        return length;
    }
    
    public final void a() {
        try {
            if (this.a != null) {
                this.a.b(this.d);
            }
            if (this.e != null) {
                this.e.a(this.d);
            }
        }
        catch (Exception ex) {}
        this.d = null;
    }
}
