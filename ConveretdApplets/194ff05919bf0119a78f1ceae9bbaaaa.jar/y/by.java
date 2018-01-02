// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;

public final class by extends u
{
    int a;
    private int v;
    int b;
    int t;
    private int w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    int u;
    private int C;
    private int D;
    
    public by() {
        this.u = 1;
        this.C = -1;
        this.D = -1;
        this.w = 1;
        this.a(Color.lightGray);
    }
    
    public final void e() {
        this.x = ((this.w == 0) ? super.e : super.f);
        this.y = this.x - 30;
    }
    
    private void a(final ei ei, final int n, final int n2, final int n3, final int n4, final boolean b) {
        ei.a(Color.black);
        ei.c(n, n2, n3 - 1, n4 - 1);
        for (int i = 0; i < 2; ++i) {
            ei.a((b ^ i % 2 == 0) ? Color.white : this.e());
            ei.a(n, n2, 1, 1, n3 - 2, 1, n3, n4, i << 1);
            ei.a(n, n2, 1, 1, 1, n4 - 2, n3, n4, i << 1);
        }
    }
    
    private void a(final ei ei, final int n, final int n2, final boolean b) {
        ei.a(this.c());
        if (this.w == 0) {
            ei.b(n, 0, n2, 15);
            this.a(ei, n, 0, n2, 15, b);
            return;
        }
        ei.b(0, n, 15, n2);
        this.a(ei, 0, n, 15, n2, b);
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(Color.black);
        ei.a(this.b());
        if (this.w == 1) {
            bj.c(ei, 0, 14, 14, this.y + 1);
            ei.a(Color.black);
            ei.c(0, 14, 14, this.y + 1);
        }
        else {
            bj.c(ei, 14, 0, this.y + 1, 14);
            ei.a(Color.black);
            ei.c(14, 0, this.y + 1, 14);
        }
        int min;
        if (this.t == 0) {
            this.z = this.y;
            min = 0;
        }
        else {
            this.z = Math.min(this.y, this.b * this.y / this.t);
            if (this.D >= 0) {
                min = Math.min(this.A + this.B, this.y - this.z);
            }
            else {
                min = this.a * this.y / this.t;
            }
        }
        if (this.z > this.y - min) {
            this.z = this.y - min;
        }
        if (this.z < 8) {
            this.z = 8;
        }
        if (this.a + this.b == this.t) {
            min = this.y - this.z;
        }
        if (min < 0) {
            min = 0;
        }
        if (this.z + min > this.y) {
            min = this.y - this.z;
        }
        this.a(ei, min + 15, this.z, false);
        if (this.D == -1) {
            this.A = min;
        }
        this.a(ei, 0, 15, this.C == 0);
        this.a(ei, this.x - 15, 15, this.C == 1);
        for (int i = 0; i < 2; ++i) {
            ei.a(Color.black);
            for (int j = 0; j < 4; ++j) {
                final int n = j + 5;
                ei.a(0, 0, n, 7 - j, n, j + 7, this.x, 15, this.w + (i << 1));
            }
        }
    }
    
    private Event a() {
        return new Event(this, 605, new Integer(this.a));
    }
    
    private void a() {
        if (this.a > this.t - this.b) {
            this.a = this.t - this.b;
        }
        if (this.a < 0) {
            this.a = 0;
        }
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        final by by = this;
        final int d = ((this.w == 0) ? n : n2) - 15;
        this = by;
        Event a = null;
        if (d > this.A && d < this.A + this.z) {
            this.D = d;
            this.v = this.A;
        }
        else {
            this.D = -1;
            if (d < 0) {
                this.C = 0;
                final by by2 = this;
                by2.a -= this.u;
            }
            else if (d > this.y) {
                this.C = 1;
                final by by3 = this;
                by3.a += this.u;
            }
            else if (d < this.A) {
                final by by4 = this;
                by4.a -= this.b;
            }
            else {
                final by by5 = this;
                by5.a += this.b;
            }
            this.a();
            a = this.a();
            this.h();
        }
        if (a != null) {
            this.a(a);
        }
        return true;
    }
    
    public final boolean b(final Event event, final int n, final int n2) {
        final by by = this;
        final int n3 = ((this.w == 0) ? n : n2) - 15;
        this = by;
        Event a = null;
        if (this.D != -1 && this.y != 0) {
            this.B = n3 - this.D;
            this.a = (this.v + this.B) * this.t / this.y;
            this.a();
            a = this.a();
            this.h();
        }
        if (a != null) {
            this.a(a);
        }
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        if (this.C != -1) {
            this.C = -1;
            this.h();
        }
        else if (this.D != -1) {
            this.D = -1;
            this.h();
        }
        return true;
    }
    
    public final int a() {
        if (this.w == 1) {
            return 15;
        }
        return 38;
    }
    
    public final int b() {
        if (this.w == 0) {
            return 15;
        }
        return 38;
    }
}
