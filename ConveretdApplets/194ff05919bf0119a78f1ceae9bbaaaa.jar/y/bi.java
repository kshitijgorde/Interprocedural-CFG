// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.io.DataInputStream;

public final class bi implements db, fb
{
    private boolean a;
    private aj a;
    private ba a;
    private ba b;
    
    public bi(final aj a) {
        this.a = false;
        this.a = a;
    }
    
    private String a(final int n) {
        if (this.a.a().b() && this.a.e() % 2 == n % 2) {
            return this.a.a().a(1716519096);
        }
        return this.a.a().a(1716519064);
    }
    
    public final void a() {
    }
    
    public final void b() {
    }
    
    public final void e() {
    }
    
    private final void c(final int n) {
        if (bp.e(this.a.a())) {
            new dc(this.a, this.a(n), n);
        }
    }
    
    public final void a(final int n) {
        while (true) {
            for (int i = 0; i < this.a.d(); ++i) {
                if (this.a.a().b(i)) {
                    final int n2 = i;
                    final int n3 = n2;
                    if (n == 0 && this.a.e() >= 0 && !this.a.a().b(this.a.e()) && bp.c(this.a.a()) && bp.d(this.a.a()) && n3 >= 0) {
                        this.c(n3);
                    }
                    return;
                }
            }
            final int n2 = -1;
            continue;
        }
    }
    
    public final void b(final int n) {
        if (this.a.a().c && this.a.e() >= 0 && n != this.a.e() && bp.c(this.a.a()) && bp.d(this.a.a())) {
            final String a = this.a(n);
            if (this.a.a().b(n) && !this.a.d()) {
                this.c(n);
                return;
            }
            new fi(this.a.b(), this.a.a().a(1716519089) + a + this.a.a().a(1716519092) + (this.a.a().b(this.a.e()) ? this.a.a().a(1716519050) : this.a.a().a(1716519098)) + this.a.a().a(1716519059) + a + this.a.a().a(1716519054), this.a.a());
        }
    }
    
    public final boolean a(final byte b, final DataInputStream dataInputStream) {
        if (b == 61) {
            final byte byte1 = dataInputStream.readByte();
            final boolean b2 = dataInputStream.readByte() != 0;
            this.a.a(b2 ? this.a.a().a(1716519055) : this.a.a().a(1716519083), Color.blue);
            if (this.a.e() >= 0 && byte1 != this.a.e()) {
                final String a = this.a((int)byte1);
                if (b2) {
                    if (this.a == null) {
                        this.a = new ba(this.a.a(), this.a.b(), this.a.a().a(1716519089) + a + this.a.a().a(1716519057), this);
                    }
                }
                else if (this.b == null) {
                    this.b = new ba(this.a.a(), this.a.b(), this.a.a().a(1716519089) + a + this.a.a().a(1716519061), this);
                }
            }
            return true;
        }
        return false;
    }
    
    public final boolean a() {
        return this.b(false);
    }
    
    public final boolean a(final boolean b) {
        return this.b(b);
    }
    
    private boolean b(final boolean b) {
        final boolean b2 = this.a.a() > 0L && this.a.b() == 0;
        if ((this.a.e() < 0 || !b2) && (!this.a.a().c || !bp.c(this.a.a()) || this.a.e() < 0 || this.a)) {
            return true;
        }
        if (b) {
            this.a.h();
            return true;
        }
        new cd(this.a);
        return false;
    }
    
    public final void f() {
        this.a = false;
    }
    
    public final void g() {
        this.a = (this.a.e() < 0);
    }
    
    public final void a(final ba ba) {
        if (ba == this.a) {
            this.a.a((byte)1, (byte)1);
            this.a = null;
            return;
        }
        if (ba == this.b) {
            this.a.a((byte)1, (byte)0);
            this.b = null;
        }
    }
    
    public final void b(final ba ba) {
        if (ba == this.a) {
            this.a.a((byte)0, (byte)1);
            this.a = null;
            return;
        }
        if (ba == this.b) {
            this.a.a((byte)0, (byte)0);
            this.b = null;
        }
    }
    
    public final void c() {
    }
    
    public final void a(final eu eu) {
    }
    
    public final void d() {
    }
    
    public final void a(final String s) {
    }
}
