// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;

public final class bj extends av
{
    private boolean a;
    private boolean b;
    private boolean j;
    private int a;
    private int b;
    private u a;
    private av b;
    
    public bj(final String s) {
        this(new es(s));
    }
    
    public final void a(final boolean j) {
        this.j = j;
        this.h();
    }
    
    public bj(final u a) {
        super((byte)0);
        this.a = false;
        this.b = false;
        this.j = true;
        this.b = new av((byte)0);
        this.a = a;
        this.a(this.b, 10, 1, 1, 1, 1, 0, 0, 3, 3, 3, 3);
        this.b.a(a, 10, 0, 1, 1, 1, 0, 0);
    }
    
    private void a() {
        final boolean a = this.a;
        this.a = (this.b && this.a >= 0 && this.a < this.c() && this.b >= 0 && this.b < this.d());
        if (a != this.a) {
            if ((this = this).a) {
                final u a2 = this.a;
                ++a2.c;
                final u a3 = this.a;
                ++a3.d;
            }
            else {
                final u a4 = this.a;
                --a4.c;
                final u a5 = this.a;
                --a5.d;
            }
            this.h();
        }
    }
    
    public final void a(ei ei) {
        this.b.c = this.c();
        super.a(ei);
        ei.a(0, this.c(), this.d(), this.d(), this.f());
        for (int i = 0; i < 2; ++i) {
            ei.a(this.f());
            ei.a(0, 0, 3, 1, this.c() - 4, 1, this.c(), this.d(), i << 1);
            ei.a(0, 0, 1, 3, 1, this.d() - 4, this.c(), this.d(), i << 1);
            ei.a((this.a ^ i % 2 == 0) ? Color.white : this.e());
            ei.a(0, 0, 3, 2, this.c() - 4, 2, this.c(), this.d(), i << 1);
            ei.a(0, 0, 2, 3, 2, this.d() - 4, this.c(), this.d(), i << 1);
        }
        if (!this.j) {
            ei.a(this.a());
            final bj bj = this;
            ei = ei;
            this = bj;
            c(ei, 3, 3, this.c() - 6, this.d() - 6);
        }
    }
    
    public static void c(final ei ei, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n3; i += 2) {
            final int n5 = Math.min(n4, n3 - i) - 1;
            ei.a(n + i, n2, n + i + n5, n2 + n5);
        }
        for (int j = 0; j < n4; j += 2) {
            final int n6 = Math.min(n3, n4 - j) - 1;
            ei.a(n, n2 + j, n + n6, n2 + n6 + j);
        }
    }
    
    public final boolean a(final Event event, final int a, final int b) {
        if (this.j) {
            this.b = true;
            this.a = a;
            this.b = b;
            this.a();
        }
        return true;
    }
    
    public final boolean b(final Event event, final int a, final int b) {
        this.a = a;
        this.b = b;
        this.a();
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        if (this.a) {
            super.a.a(new Event(this, 502, null));
            this.a(new Event(this, 1001, null));
            this.b = false;
            this.a();
        }
        return true;
    }
}
