// 
// Decompiled by Procyon v0.5.30
// 

public class C31 extends C22
{
    int a;
    boolean b;
    int c;
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((super.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((super.d - n4) * n5 + n6));
        final int n10 = (int)((super.h - n) * n2 + n3);
        final int n11 = (int)(n7 - ((super.i - n4) * n5 + n6));
        final int abs = Math.abs(n10 - n8);
        final int abs2 = Math.abs(n11 - n9);
        int abs3 = 0;
        final double n12 = (int)(n8 + abs / 2 + Math.cos(this.c * 3.141592653589793 / 180.0) * abs / 2.0);
        final double n13 = (int)(n9 + abs / 2 - Math.sin(this.c * 3.141592653589793 / 180.0) * abs / 2.0);
        final double n14 = (int)(n8 + abs / 2 + Math.cos(this.a * 3.141592653589793 / 180.0) * abs / 2.0);
        final double n15 = (int)(n9 + abs / 2 - Math.sin(this.a * 3.141592653589793 / 180.0) * abs / 2.0);
        if (super.D > 0) {
            abs3 = Math.abs(n10 - (int)((super.h + super.D - n) * n2 + n3));
        }
        if (super.D > 0 && abs3 > 0) {
            for (int i = 0; i < abs3; ++i) {
                if (super.A == C28.S) {}
            }
        }
        else if (super.A == C28.S) {
            c20.Arc(n8, n9, n8 + abs, n9 + abs2, (int)n12, (int)n13, (int)n14, (int)n15);
        }
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((super.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((super.d - n4) * n5 + n6));
        final int n10 = (int)((super.h - n) * n2 + n3);
        final int n11 = (int)(n7 - ((super.i - n4) * n5 + n6));
        final int abs = Math.abs(n10 - n8);
        final int abs2 = Math.abs(n11 - n9);
        int abs3 = 0;
        if (super.D > 0) {
            abs3 = Math.abs(n10 - (int)((super.h + super.D - n) * n2 + n3));
        }
        if (super.D > 0 && abs3 > 0) {
            for (int i = 0; i < abs3; ++i) {
                if (super.A == C28.S) {
                    c15.g(n8 - i, n9 - i, abs + i * 2, abs2 + i * 2, this.c, this.a);
                }
                else {
                    c15.j(n8 - i, n9 - i, abs + i * 2, abs2 + i * 2, super.A, this.c, this.a);
                }
            }
        }
        else if (super.A == C28.S) {
            c15.g(n8, n9, abs, abs2, this.c, this.a);
        }
        else {
            c15.j(n8, n9, abs, abs2, super.A, this.c, this.a);
        }
    }
    
    public Object clone() {
        return new C31(super.f, super.d, super.c / 2, this.c, this.a);
    }
    
    public C31(final int n, final int n2, final int n3, final int c, final int a) {
        super(n, n2, n3);
        this.b = true;
        this.c = c;
        this.a = a;
    }
}
