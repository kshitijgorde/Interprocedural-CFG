import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

final class f
{
    int a;
    int b;
    int c;
    int d;
    
    f() {
        final int n = Integer.MIN_VALUE;
        this.d = n;
        this.c = n;
        this.b = n;
        this.a = n;
    }
    
    f(final f f) {
        this.a = f.a;
        this.b = f.b;
        this.c = f.c;
        this.d = f.d;
    }
    
    f(final int n, final int n2, final int n3, final int n4) {
        final boolean l = c.l;
        Label_0039: {
            if (n < n3) {
                this.a = n;
                this.c = n3;
                if (!l) {
                    break Label_0039;
                }
            }
            this.a = n3;
            this.c = n;
        }
        if (n2 < n4) {
            this.b = n2;
            this.d = n4;
            if (!l) {
                return;
            }
        }
        this.b = n4;
        this.d = n2;
    }
    
    final void a() {
        final int n = Integer.MIN_VALUE;
        this.d = n;
        this.b = n;
        this.c = n;
        this.a = n;
    }
    
    final boolean b() {
        return this.a == Integer.MIN_VALUE;
    }
    
    final void a(final f f) {
        if (f.a != Integer.MIN_VALUE) {
            if (this.a == Integer.MIN_VALUE) {
                this.a = f.a;
                this.c = f.c;
                this.b = f.b;
                this.d = f.d;
                return;
            }
            this.a = Math.min(this.a, f.a);
            this.c = Math.max(this.c, f.c);
            this.b = Math.min(this.b, f.b);
            this.d = Math.max(this.d, f.d);
        }
    }
    
    final void a(final Point point) {
        this.a(point.x, point.y);
    }
    
    final void a(final int n, final int n2) {
        final boolean l = c.l;
        if (this.a == Integer.MIN_VALUE) {
            this.c = n;
            this.a = n;
            this.d = n2;
            this.b = n2;
            return;
        }
        Label_0064: {
            if (n < this.a) {
                this.a = n;
                if (!l) {
                    break Label_0064;
                }
            }
            if (n > this.c) {
                this.c = n;
            }
        }
        if (n2 < this.b) {
            this.b = n2;
            if (!l) {
                return;
            }
        }
        if (n2 > this.d) {
            this.d = n2;
        }
    }
    
    final boolean b(final f f) {
        return this.a <= f.c && f.a <= this.c && this.b <= f.d && f.b <= this.d;
    }
    
    final boolean b(final Point point) {
        return this.a <= point.x && point.x <= this.c && this.b <= point.y && point.y <= this.d;
    }
}
