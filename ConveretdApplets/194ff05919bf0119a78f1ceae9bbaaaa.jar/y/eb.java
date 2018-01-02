// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataInputStream;
import java.awt.Color;
import java.awt.Frame;

public final class eb extends ex implements ad
{
    private ev a;
    private v a;
    p a;
    private boolean c;
    private static bl a;
    
    public final Frame a() {
        return this.a;
    }
    
    public final av a() {
        return this.a;
    }
    
    public final void a() {
        this.a = (p)this.a();
        if (this.a.a) {
            this.b(new es(this.a().a(1716521466)));
        }
        if (this.a.b) {
            this.b(new es(this.a().a(1716521472)));
        }
        this.a = new v(this.a());
        this.a = new ev(this);
        super.a();
    }
    
    public final void a_() {
        super.a_();
        this.b(false);
        this.c = false;
        this.a.e.c(0);
        this.a.d.c(0);
        for (int i = 0; i < 13; ++i) {
            this.a.a[i].a(false);
        }
        this.a.g.a(false);
        this.a.e.a(true);
        this.a.f.a(true);
    }
    
    public final void a(final int n, final en en, final en en2) {
        if (n == this.e()) {
            this.a.a[0].a.a.a(en);
            this.a.a[0].a.a.a(en2);
        }
    }
    
    public final void b(final int n, final bl bl) {
        if (this.e() != n) {
            super.b(n, bl);
            return;
        }
        if (this.c) {
            super.b(n, bl);
            return;
        }
        super.b(n, eb.a);
    }
    
    public final void a(int i, final bl bl) {
        if (this.e() == i) {
            this.c = true;
            this.b(i, bl);
            for (i = 0; i < 13; ++i) {
                this.a.a[i].a(true);
            }
            this.a.g.a(true);
            this.a.e.a(false);
            this.a.f.a(false);
        }
    }
    
    public final void b_(final int n) {
        if (n != -1) {
            this.a.a.a(this.a().a(1716521470));
            this.a.a.a(this.a(n));
            if (n == this.e()) {
                this.a.e.c(1);
                this.a.d.c(1);
                this.k();
            }
        }
        else {
            this.a.a.a(-1);
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n == this.e()) {
            this.a.e.c(0);
            this.a.d.c(0);
        }
        String s;
        if (n2 == 0) {
            s = this.a().a(1716521477);
        }
        else if (n2 == -1) {
            s = this.a().a(1716521476);
        }
        else {
            s = Integer.toString(n2);
        }
        this.a.a.a(this.a(n), this.a().a(1716521468) + s);
    }
    
    public final void c_(final int n) {
        this.b(n >= 0 && n == this.e());
        if (n != -1) {
            this.a.a.a(this.a(n));
            this.a.a.a(this.a().a(1716521469));
        }
    }
    
    public final void a(final int[] array) {
        String s = this.a().a(1716521474);
        for (int i = 0; i < 2; ++i) {
            s = s + "\n" + this.a(i) + "/" + this.a(i + 2) + ": " + array[i];
        }
        this.a(s);
    }
    
    public final void m() {
        this.a.a[0].m();
        this.a.a[1].m();
        super.m();
    }
    
    public final void a(final int[] array, final int[] array2) {
        for (int i = 0; i < 2; ++i) {
            final String[] array3 = { Integer.toString(array[i]), Integer.toString(array2[i]) };
            this.a.a[i].a(Integer.toString(array[i]), (Object)null);
            this.a.a[i].a(Integer.toString(array2[i]), this.a.a[i].a.size() - 1, 1);
            this.a.a[i].a.n();
        }
    }
    
    private void c(final int n, final String s) {
        this.a("*** " + this.a(n) + "/" + this.a(n + 2) + " " + s, Color.blue);
    }
    
    public final void b(final int n, final int n2) {
        if (n == -1) {
            this.a(this.a().a(1716521475), Color.blue);
            return;
        }
        this.c(n, this.a().a(n2));
    }
    
    public final void a(final int n, final int n2, final int n3) {
        this.c(n, this.a().a(1716521893) + n2 * 10 + this.a().a(1716521895) + ((n3 == 0) ? "." : (this.a().a(1716521891) + n3 + this.a().a(1716521890))));
    }
    
    public final void c(final int n, final int n2) {
        this.c(n, this.a().a(1716521894) + n2 + this.a().a(1716521892));
    }
    
    public final void b_() {
        this.a.a[0].m();
        this.a.a[1].m();
    }
    
    public final synchronized void a(final byte b, final DataInputStream dataInputStream) {
        switch (b) {
            case 115: {
                final byte byte1 = dataInputStream.readByte();
                final en a = en.a((DataInput)dataInputStream);
                final en a2 = en.a((DataInput)dataInputStream);
                final p a3 = this.a;
                final byte b2 = byte1;
                final en en = a;
                final en en2 = a2;
                final en en3 = en;
                final byte b3 = b2;
                final p p2 = a3;
                final int n = (a3.h + 2) % 4;
                if (p2.e == 1 && p2.b && p2.b[p2.h] == -1 && ((b3 == p2.h && p2.a[p2.h].a.size() == 13) || (b3 == n && p2.a[n].a.size() == 15)) && !y.en.a(en3, en2) && p2.a[b3].b(en3) && p2.a[b3].b(en2)) {
                    if (b3 == p2.h) {
                        p2.d[n] = true;
                        p2.a.a(n, p2.a[n]);
                    }
                    p2.a[b3].a(en3);
                    p2.a[b3].a(en2);
                    p2.a[(b3 + 2) % 4].a(en3);
                    p2.a[(b3 + 2) % 4].a(en2);
                    p2.a.b(p2.h, p2.a[p2.h]);
                    p2.a.b(n, p2.a[n]);
                    p2.a.a((b3 + 2) % 4, en3, en2);
                    if (b3 != p2.h) {
                        p2.a.c_(-1);
                        p2.h();
                        return;
                    }
                    p2.a.c_((p2.h + 2) % 4);
                    p2.a.k(180000);
                }
            }
            case 109: {
                final byte byte2 = dataInputStream.readByte();
                final byte byte3 = dataInputStream.readByte();
                final p a4 = this.a;
                final byte b4 = byte2;
                final byte b5 = byte3;
                final byte b6 = b4;
                final p p3 = a4;
                if (a4.e == 1 && p3.h == b6 && p3.b[b6] == -2 && (b5 >= 0 || !p3.d[b6]) && (!p3.b || b5 != -1 || p3.c[(b6 + 1) % 2] - p3.c[b6 % 2] >= 200)) {
                    if ((p3.h + 4 - p3.g) % 4 >= 2 && p3.b[(p3.h + 2) % 4] > 0 && p3.a && b5 > 0) {
                        p3.a.f_(1716521428);
                        return;
                    }
                    p3.b[b6] = b5;
                    p3.a.a(b6, b5);
                    if (!p3.d[b6]) {
                        p3.d[b6] = true;
                        p3.a.a(b6, p3.a[b6]);
                    }
                    if (!p3.b || b5 != -1) {
                        p3.h();
                        return;
                    }
                    p3.a.c_(b6);
                    p3.a.k(180000);
                }
            }
            case 118: {
                final p a5 = this.a;
                final byte byte4 = dataInputStream.readByte();
                final p p4 = a5;
                if (a5.e == 1 && p4.h == byte4 && !p4.d[byte4]) {
                    p4.d[byte4] = true;
                    p4.a.a(byte4, p4.a[byte4]);
                }
            }
            default: {
                super.a(b, dataInputStream);
            }
        }
    }
    
    private void b(final boolean b) {
        this.a.d.a(b);
        if (b) {
            this.a.a[0].a.a.a();
        }
        this.a.a[0].a.a.a(b);
        this.a(this.e(), b ? this.a().a(1716521467) : "");
    }
    
    private void l(final int n) {
        this.a.a[n].a(this.a(n) + "/" + this.a(n + 2));
    }
    
    public final void b(final int n, final String s) {
        this.l(n % 2);
        super.b(n, s);
    }
    
    public final void g(final int n) {
        this.l(n % 2);
        super.g(n);
    }
    
    public final void i() {
        super.i();
    }
    
    public final boolean b() {
        return true;
    }
    
    static {
        eb.a = new bl((byte)0);
    }
}
