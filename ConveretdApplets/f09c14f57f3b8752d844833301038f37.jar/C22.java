// 
// Decompiled by Procyon v0.5.30
// 

public class C22 extends C07 implements C41
{
    boolean d;
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public C22(final int n, final int n2, final int n3) {
        super(n - n3, n2 + n3, n3 * 2, n3 * 2);
        this.d = false;
        super.P = n - n3;
        super.B = n2 - n3;
        super.H = n3 * 2;
        super.J = n3 * 2;
    }
    
    public boolean b(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return super.a(n, n2, n3, n4) && this.d == b;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        if (super.Q != null) {
            c15.B(super.Q);
        }
        if (!this.d) {
            super.b(c15, n, n2, n3, n4, n5, n6, n7);
            return;
        }
        final int n8 = (int)((super.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((super.d - n4) * n5 + n6));
        final int abs = Math.abs((int)((super.h - n) * n2 + n3) - n8);
        c15.C(n8 + abs / 2, n9 + abs / 2, abs / 2);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        if (!this.d) {
            super.a(c20, n, n2, n3, n4, n5, n6, n7);
            return;
        }
        final int n8 = (int)((super.f - n) * n2 + n3);
        final int n9 = (int)(n7 - ((super.d - n4) * n5 + n6));
        final int abs = Math.abs((int)((super.h - n) * n2 + n3) - n8);
        final int n10 = n8 + abs / 2;
        final int n11 = n9 + abs / 2;
        final int n12 = abs / 2;
        final double n13 = 0.6283185307179586;
        final int n14 = (int)(Math.sin(n13) * n12);
        final int n15 = (int)(n12 * 0.9);
        for (int i = 0; i < 10; ++i) {
            final int n16 = 36 * i;
            final double n17 = n13 * i;
            final int n18 = (int)(n10 + Math.cos(n17) * n15);
            final int n19 = (int)(n11 - Math.sin(n17) * n15);
            final int n20 = 360 - (90 - n16);
            final int n21 = n20 + 180;
            final int n22 = n18 - n14 / 2;
            final int n23 = n19 - n14 / 2;
            c20.Arc(n22, n23, n22 + n14, n23 + n14, (int)(n22 + n14 / 2 + Math.cos(n20 * 3.141592653589793 / 180.0) * n14 / 2.0), (int)(n23 + n14 / 2 - Math.sin(n20 * 3.141592653589793 / 180.0) * n14 / 2.0), (int)(n22 + n14 / 2 + Math.cos(n21 * 3.141592653589793 / 180.0) * n14 / 2.0), (int)(n23 + n14 / 2 - Math.sin(n21 * 3.141592653589793 / 180.0) * n14 / 2.0));
        }
    }
    
    public Object clone() {
        return new C22(super.f, super.d, super.c / 2);
    }
}
