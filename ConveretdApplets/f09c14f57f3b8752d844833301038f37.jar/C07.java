// 
// Decompiled by Procyon v0.5.30
// 

public class C07 extends C28 implements C41
{
    int c;
    int d;
    public static String e;
    int f;
    int g;
    int h;
    int i;
    
    public void f(final int n, final int n2) {
        super.f(n, n2);
        this.f += n;
        this.d += n2;
        this.h += n;
        this.i += n2;
    }
    
    public C07(final int n, final int n2, final int n3, final int n4) {
        this.f = n;
        this.d = n2;
        this.c = n3;
        this.g = n4;
        this.h = n + n3;
        this.i = n2 + n4;
        super.P = n;
        super.B = n2;
        super.J = n4;
        super.H = n3;
    }
    
    static {
        C07.e = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public Object clone() {
        return new C07(this.f, this.d, this.c, this.g);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((this.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((this.d - n4) * n5 + n6));
        final int n10 = (int)((this.h - n) * n2 + n3);
        final int n11 = (int)(n7 - ((this.i - n4) * n5 + n6));
        int abs = 0;
        if (super.D > 0) {
            abs = Math.abs(n10 - (int)((this.h + super.D - n) * n2 + n3));
        }
        final int abs2 = Math.abs(n10 - n8);
        final int abs3 = Math.abs(n11 - n9);
        if (super.D > 0 && abs > 0) {
            for (int i = 0; i < abs; ++i) {
                if (super.A == C28.S) {}
            }
        }
        else if (super.A == C28.S) {
            final int n12 = n8 + abs2;
            final int n13 = n9 + abs3 / 2;
            c20.Arc(n8, n9, n8 + abs2, n9 + abs3, n12, n13, n12, n13);
        }
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4) {
        return super.P == n && super.B == n2 && super.H == n3 && super.J == n4;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((this.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((this.d - n4) * n5 + n6));
        final int n10 = (int)((this.h - n) * n2 + n3);
        final int n11 = (int)(n7 - ((this.i - n4) * n5 + n6));
        int abs = 0;
        if (super.D > 0) {
            abs = Math.abs(n10 - (int)((this.h + super.D - n) * n2 + n3));
        }
        final int abs2 = Math.abs(n10 - n8);
        final int abs3 = Math.abs(n11 - n9);
        if (super.D > 0 && abs > 0) {
            for (int i = 0; i < abs; ++i) {
                if (super.A == C28.S) {
                    c15.x(n8 - i, n9 - i, abs2 + i * 2, abs3 + i * 2);
                }
                else {
                    c15.j(n8 - i, n9 - i, abs2 + i * 2, abs3 + i * 2, super.A, 0, 360);
                }
            }
        }
        else if (super.A == C28.S) {
            c15.x(n8, n9, abs2, abs3);
        }
        else {
            c15.j(n8, n9, abs2, abs3, super.A, 0, 360);
        }
    }
}
