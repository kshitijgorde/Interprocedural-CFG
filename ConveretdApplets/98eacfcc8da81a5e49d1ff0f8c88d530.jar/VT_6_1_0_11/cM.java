// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.InputStream;

public final class cM
{
    private bk[] a;
    private int b;
    private q c;
    private bd d;
    private cK e;
    private InputStream f;
    private int g;
    private String h;
    private String i;
    private bf j;
    private bf k;
    private V l;
    private int m;
    private byte[] n;
    private boolean o;
    private boolean p;
    private String q;
    private boolean r;
    private boolean s;
    
    cM(final bk[] a, final int b, final q q) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = -1;
        this.n = null;
        this.o = false;
        this.p = false;
        this.q = null;
        this.r = false;
        this.s = false;
        this.a = a;
        this.b = b;
        try {
            final int index = q.c().indexOf(63);
            this.j = new bf(q.a().b(), null, q.a().c(), q.a().d(), (index < 0) ? q.c() : q.c().substring(0, index), (index < 0) ? null : q.c().substring(index + 1), null);
        }
        catch (dh dh) {}
        this.q = q.b();
    }
    
    final void a(final q c, final bd d) {
        this.c = c;
        this.d = d;
        d.a = this;
        d.b = this.b;
        this.p = d.m;
    }
    
    final void a(final q c, final cK e) {
        this.c = c;
        this.e = e;
    }
    
    public final int a() {
        if (!this.o) {
            this.e();
        }
        return this.g;
    }
    
    public final String b() {
        if (!this.o) {
            this.e();
        }
        return this.h;
    }
    
    private synchronized byte[] h() {
        if (!this.o) {
            this.e();
        }
        if (this.n == null) {
            try {
                final InputStream f = this.f;
                if (this.m != 0) {
                    if (this.n == null) {
                        this.n = new byte[0];
                    }
                    int length = this.n.length;
                    try {
                        final String s = "Content-Length";
                        if (!this.o) {
                            this.e();
                        }
                        if (this.l.a(s.trim()) != null) {
                            int read = 0;
                            this.n = new byte[this.m];
                            do {
                                length += read;
                            } while ((read = f.read(this.n, length, this.m - length)) != -1 && length + read < this.m);
                        }
                        else {
                            int read2 = 0;
                            do {
                                length += read2;
                                this.n = bz.a(this.n, length + 1000);
                            } while ((read2 = f.read(this.n, length, 1000)) != -1);
                            this.n = bz.a(this.n, length);
                        }
                    }
                    catch (IOException ex) {
                        this.n = bz.a(this.n, length);
                        throw ex;
                    }
                    finally {
                        try {
                            f.close();
                        }
                        catch (IOException ex4) {}
                    }
                }
            }
            catch (InterruptedIOException ex2) {
                throw ex2;
            }
            catch (IOException ex3) {
                new StringBuffer().append("HResp: (\"").append(this.q).append(" ").append(this.j.d()).append("\")").toString();
                aF.a(2, "       ", ex3);
                try {
                    this.f.close();
                }
                catch (Exception ex5) {}
                throw ex3;
            }
            this.f.close();
        }
        return this.n;
    }
    
    public final synchronized InputStream c() {
        if (!this.o) {
            this.e();
        }
        if (this.n == null) {
            return this.f;
        }
        this.h();
        return new ByteArrayInputStream(this.n);
    }
    
    public final String toString() {
        if (!this.o) {
            try {
                this.e();
            }
            catch (Exception ex2) {
                final Exception ex = ex2;
                if (!(ex2 instanceof InterruptedIOException)) {
                    new StringBuffer().append("HResp: (\"").append(this.q).append(" ").append(this.j.d()).append("\")").toString();
                    aF.a(2, "       ", ex);
                }
                return "Failed to read headers: " + ex;
            }
        }
        final String property = System.getProperty("line.separator", "\n");
        final StringBuffer sb;
        (sb = new StringBuffer(this.i)).append(' ');
        sb.append(this.g);
        sb.append(' ');
        sb.append(this.h);
        sb.append(property);
        if (this.k != null) {
            sb.append("Effective-URI: ");
            sb.append(this.k);
            sb.append(property);
        }
        final Enumeration keys = this.l.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(s);
            sb.append(": ");
            sb.append(this.l.a(s));
            sb.append(property);
        }
        return sb.toString();
    }
    
    final bk[] d() {
        return this.a;
    }
    
    final synchronized boolean e() {
        if (this.o) {
            return false;
        }
        if (this.e != null) {
            this.d = this.e.a();
            this.d.a = this;
            this.e = null;
        }
    Label_0355:
        while (true) {
            for (int n = 0; n < this.a.length && !this.p; ++n) {
                try {
                    this.a[n].a(this.d);
                }
                catch (cp cp2) {
                    final cp cp = cp2;
                    if (!cp2.g) {
                        throw cp;
                    }
                    continue Label_0355;
                }
            }
            int n2 = 0;
            while (n2 < this.a.length && !this.p) {
                final int a;
                switch (a = this.a[n2].a(this.d, this.c)) {
                    case 10: {
                        ++n2;
                        continue;
                    }
                    case 11: {
                        continue Label_0355;
                    }
                    case 12: {
                        break Label_0355;
                    }
                    case 13:
                    case 15: {
                        this.d.f().close();
                        if (this.r) {
                            this.a(true);
                        }
                        if (this.c.e) {
                            return true;
                        }
                        this.c.a().a(this.c, this, this.d, true);
                        if (!this.o) {
                            continue Label_0355;
                        }
                        break Label_0355;
                    }
                    case 14:
                    case 16: {
                        this.d.f().close();
                        if (this.r) {
                            this.a(true);
                        }
                        if (this.c.e) {
                            return true;
                        }
                        this.c.a().a(this.c, this, this.d, false);
                        continue Label_0355;
                    }
                    default: {
                        throw new Error("HTTPClient Internal Error: invalid status " + a + " returned by module " + this.a[n2].getClass().getName());
                    }
                }
            }
            break;
        }
        this.d.a();
        if (!this.c.e) {
            this.a(this.d);
        }
        if (this.r) {
            this.a(false);
        }
        return false;
    }
    
    final void a(final bd bd) {
        if (this.o) {
            return;
        }
        this.g = bd.d;
        this.h = bd.e;
        this.i = bd.f;
        this.k = bd.g;
        this.m = bd.i;
        this.l = bd.h;
        this.f = bd.c;
        this.n = bd.k;
        this.o = true;
    }
    
    final void a(final boolean b) {
        if (this.s) {
            return;
        }
        if (!b && !this.o) {
            this.r = true;
            return;
        }
        this.s = true;
    }
    
    final void f() {
        this.p = true;
    }
    
    final int g() {
        return this.b;
    }
}
