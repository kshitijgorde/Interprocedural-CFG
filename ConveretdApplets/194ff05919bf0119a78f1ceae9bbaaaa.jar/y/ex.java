// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.io.DataInput;
import java.io.DataInputStream;
import java.awt.Color;
import java.util.Vector;
import java.awt.Frame;

public abstract class ex extends n implements dv
{
    private bj h;
    private bj i;
    private bj j;
    private bl a;
    private long a;
    private do a;
    private Frame a;
    private q a;
    private cs a;
    m a;
    
    public ex() {
        this.a = new bl((byte)0);
        this.a = 0L;
        new Vector();
        this.a(new bi(this));
    }
    
    public abstract Frame a();
    
    public void a() {
        this.a = (do)this.a();
        this.a(this.h = new bj(this.a.a.a().a(1716519249)));
        this.a(this.i = new bj(this.a.a.a().a(1716519012)));
        this.a(this.j = new bj(this.a.a.a().a(1716519011)));
        this.a = this.a();
        this.a = (m)this.a();
        this.a = new q(this);
        this.a = new cs(this.a.a, this.a.a.a().a(1716519012), this.a());
        this.a.a.a(0, 3.0);
        for (int i = 0; i < this.a.a; ++i) {
            final String[] array = new String[14];
            for (int j = 0; j < 14; ++j) {
                array[j] = "";
            }
            final cs a = this.a;
            final String[] array2 = array;
            final cs cs = a;
            a.a.a("", (Object)null);
            for (int k = 0; k < array2.length; ++k) {
                cs.a.a(array2[k], cs.a.a.size() - 1, k);
            }
            cs.a.a.n();
        }
        for (int l = 0; l < 13; ++l) {
            this.a.a.a(l + 1, Integer.toString(l + 1));
        }
        super.a();
    }
    
    public final void b() {
        final do a = this.a;
        final int a2 = super.a ? 1 : 0;
        final do do1 = a;
        for (int i = 0; i < do1.a; ++i) {
            do1.a[i].a.c(a2);
        }
    }
    
    public final void a(final int n) {
        this.r();
        this.a.a.a(true);
        this.a.b.a(true);
        this.a.c.a(true);
        super.a(n);
    }
    
    private void r() {
        for (int i = 0; i < this.a.a; ++i) {
            this.a.a.a(this.a(i), (en)null);
        }
    }
    
    public void b(final int n, final String s) {
        this.a.a(s, n, 0);
        super.b(n, s);
    }
    
    public void g(final int n) {
        this.a.a("", n, 0);
        super.g(n);
    }
    
    public final void b(final int n) {
        this.r();
        this.a.b.a(false);
        this.a.a.a(false);
        this.a.c.a(false);
        super.b(n);
    }
    
    public void a_() {
        this.a.a.b(-1);
        this.a.a.a(-1);
        final bl bl = new bl((byte)0);
        for (int i = 0; i < this.a.a; ++i) {
            this.a.a(bl, i);
            this.a.a.a(i, (en)null);
            this.a.a.c(i, 0);
        }
        this.a(-2, "");
        for (int j = 0; j < this.a.a; ++j) {
            this.a.a.a(j, "");
        }
        this.a = System.currentTimeMillis() + this.a.a.g() * 15;
    }
    
    public final void c_() {
        for (int i = 0; i < this.a.a; ++i) {
        Label_0254:
            for (int j = 0; j < 13; ++j) {
                final m a = this.a;
                final int n = i;
                final int n2 = j;
                final int n3 = n;
                final m m = a;
                en a2 = null;
                if (m.a[n3][n2] != 52) {
                    a2 = y.m.a(m.a[n3][n2] & 0x3F);
                }
                final en en;
                if ((en = a2) != null) {
                    String s = en.a(this.a());
                    final int n4 = i;
                    final m a3 = this.a;
                    final int n5 = j;
                    final m k = a3;
                    while (true) {
                        for (int l = 0; l < k.a; ++l) {
                            if ((k.a[l][n5] & 0x80) != 0x0) {
                                final int n6 = l;
                                if (n4 == n6) {
                                    s = "[" + s + "]";
                                }
                                else if ((this.a.a[i][j] & 0x40) != 0x0) {
                                    s = "(" + s + ")";
                                }
                                this.a.a(s, i, j + 1);
                                continue Label_0254;
                            }
                        }
                        final int n6 = -1;
                        continue;
                    }
                }
                this.a.a("", i, j + 1);
            }
        }
    }
    
    public final void l() {
        super.l();
        this.a.a.a();
        if (this.a > 0L && this.a < System.currentTimeMillis() && this.e() >= 0) {
            ((do)(this.a = 0L)).a(this.a, 0);
        }
    }
    
    public final void a(final int n, final en[] array) {
        this.a.a.b(-1);
        for (int i = 0; i < this.a.a; ++i) {
            this.a.a.a(this.a(i), array[i]);
        }
        for (int j = 0; j < this.a.a; ++j) {
            final int a = this.a((n + j + 1) % this.a.a);
            this.a.a.b(a, 66 + j * 200 / 15);
            this.a.a.a(a, (en)null);
        }
    }
    
    public final void d(final int n, final int n2) {
        this.a.a.c(this.a(n), n2);
    }
    
    public final void a(final int n, final en en) {
        this.a.a.a(this.a(n), en);
    }
    
    public final void c(final int n) {
        if (n == this.e()) {
            this.k();
        }
        this.a.a.b(this.a(n));
        this.a(-2, "");
        this.a(n, this.a().a(1716519008));
    }
    
    public void b(final int n, final bl bl) {
        if (n == this.e() || (super.a && !super.a[n])) {
            if (n == this.e() && this.a > 0L) {
                this.a.a.removeAllElements();
                this.a.a(bl);
                return;
            }
            this.a.a(bl, this.a(n));
        }
    }
    
    public final void d_(final int n) {
        this.a("*** " + this.a(n) + this.a().a(1716519009), Color.blue);
    }
    
    public final void f(final int n) {
        this.a("*** " + this.a(n) + this.a().a(1716519007), Color.blue);
    }
    
    public final void e_(final int n) {
        this.a("*** " + this.a(n) + this.a().a(1716519010), Color.blue);
    }
    
    public final void f_(final int n) {
        new fi(this.b(), this.a().a(n), this.a());
    }
    
    public synchronized void a(final byte b, final DataInputStream dataInputStream) {
        switch (b) {
            case 121: {
                final m a = this.a;
                final byte byte1 = dataInputStream.readByte();
                final en a2 = en.a((DataInput)dataInputStream);
                final byte b2 = byte1;
                final m m = a;
                if (a.a(b2, a2)) {
                    m.a.a(b2, a2);
                    m.a[b2][m.d] = (byte)y.m.a(a2);
                    ((bl)(m.a[m.c] = a2)).a(a2);
                    m.a.b(m.c, m.a[m.c]);
                    m.c = m.a(m.c);
                    if (m.c == m.b) {
                        final byte[] array = m.a[m.b];
                        final int d = m.d;
                        array[d] |= 0xFFFFFF80;
                        final m i = m;
                        ++i.d;
                        final m j = m;
                        final m k;
                        int b3 = (k = m).b;
                        int a3 = k.a(k.a[k.b], k.a[k.b]);
                        for (int l = 1; l < k.a; ++l) {
                            final int n = (k.b + l) % k.a;
                            final int a4;
                            if (k.a[n] != null && (a4 = k.a(k.a[k.b], k.a[n])) > a3) {
                                b3 = n;
                                a3 = a4;
                            }
                        }
                        j.b = b3;
                        m.c();
                        m.c = m.b;
                        final int[] a5 = m.a;
                        final int c = m.c;
                        ++a5[c];
                        m.a.a(m.b, m.a);
                        m.a.d(m.b, m.a[m.b]);
                        if (m.d == 13) {
                            final m m2 = m;
                            while (m2.d < 13) {
                                final en a6 = m2.a[m2.c].a(0);
                                m2.a[m2.c][m2.d] = (byte)(y.m.a(a6) | 0x40);
                                m2.a[m2.c].a(a6);
                                m2.c = m2.a(m2.c);
                                if (m2.c == m2.b) {
                                    final m m3 = m2;
                                    ++m3.d;
                                }
                            }
                            m2.e = 3;
                            m2.a.c_();
                            m2.a();
                            return;
                        }
                        for (int n2 = 0; n2 < m.a; ++n2) {
                            m.a[n2] = null;
                            m.c[n2] = false;
                        }
                    }
                    m.a.c(m.c);
                    m.a.k(180000);
                }
            }
            case 106: {
                final m a7 = this.a;
                final byte byte2 = dataInputStream.readByte();
                final m m4 = a7;
                if (a7.e != 0 && !m4.a[byte2]) {
                    m4.a[byte2] = true;
                    m4.a.d_(byte2);
                    for (int n3 = 0; n3 < m4.a; ++n3) {
                        if (!m4.a[n3]) {
                            return;
                        }
                    }
                    m4.e();
                }
            }
            case 122: {
                final m a8 = this.a;
                final byte byte3 = dataInputStream.readByte();
                final m m5 = a8;
                if (a8.e != 0 && !m5.b[byte3]) {
                    m5.b[byte3] = true;
                    m5.a.f(byte3);
                    for (int n4 = 0; n4 < m5.a; ++n4) {
                        if (!m5.b[n4]) {
                            return;
                        }
                    }
                    m5.f();
                    m5.a.b_();
                    for (int n5 = 0; n5 < m5.a; ++n5) {
                        m5.b[n5] = false;
                    }
                }
            }
            case 107: {
                final m a9 = this.a;
                final byte byte4 = dataInputStream.readByte();
                final m m6 = a9;
                if (a9.c(byte4)) {
                    m6.c[byte4] = true;
                    m6.a.e_(byte4);
                    for (int n6 = 0; n6 < m6.a; ++n6) {
                        if (!m6.c[n6]) {
                            return;
                        }
                    }
                    for (int n7 = 0; n7 < m6.a; ++n7) {
                        m6.c[n7] = false;
                    }
                    if (m6.e == 2) {
                        int n10;
                        if (m6.c == m6.b) {
                            final m m7 = m6;
                            --m7.d;
                            final int b4 = m6.b;
                            for (int b5 = 0; b5 < m6.a; ++b5) {
                                m6.a[b5] = m.a(m6.a[b5][m6.d] & 0x3F);
                                if ((m6.a[b5][m6.d] & 0x80) != 0x0) {
                                    m6.b = b5;
                                }
                                for (int n8 = 0; n8 < m6.a; ++n8) {
                                    m6.a.a(n8, m6.a[n8]);
                                }
                            }
                            final int[] a10 = m6.a;
                            final int n9 = b4;
                            --a10[n9];
                            m6.a.d(b4, m6.a[b4]);
                            n10 = m6.b;
                        }
                        else {
                            n10 = m6.c;
                        }
                        m6.c = m6.b;
                        while (m6.a(m6.c) != n10) {
                            m6.c = m6.a(m6.c);
                        }
                        m6.a[m6.c].a(m6.a[m6.c]);
                        m6.a[m6.c] = null;
                        m6.a.a(m6.c, (en)null);
                        m6.a[m6.c][m6.d] = 52;
                        m6.a.c(m6.c);
                        m6.a.k(180000);
                        m6.a.b(m6.c, m6.a[m6.c]);
                    }
                }
            }
            default: {
                super.a(b, dataInputStream);
            }
        }
    }
    
    final synchronized void a(final en en) {
        if (this.e() >= 0 && this.a.a(this.e(), en)) {
            this.a.a.a(this.a(this.e()), en);
            this.b(en);
        }
    }
    
    final synchronized void q() {
        if (this.e() >= 0 && this.a.c(this.e())) {
            this.a('K');
        }
    }
    
    public void i() {
        this.a.hide();
        this.a.dispose();
        this.a.hide();
        this.a.dispose();
        this.a.hide();
        this.a.dispose();
        super.i();
    }
    
    public final void a(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.h) {
                this.a.show();
                return;
            }
            if (event.target == this.i) {
                this.a.show();
                return;
            }
            if (event.target == this.j) {
                this.a.show();
                return;
            }
        }
        super.a(event);
    }
}
