// 
// Decompiled by Procyon v0.5.30
// 

public class C10 extends C28 implements C41
{
    boolean c;
    public static String d;
    
    static {
        C10.d = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public C10(final int n, final int n2, final int n3, final int n4) {
        super.P = Math.min(n, n3);
        super.B = Math.min(n2, n4);
        super.H = Math.abs(Math.max(n, n3) - Math.min(n, n3));
        super.J = Math.abs(Math.max(n2, n4) - Math.min(n2, n4));
        if ((super.P == n && super.B != n2) || (super.P == n3 && super.B != n4)) {
            this.c = false;
        }
        else {
            this.c = true;
        }
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((super.P - n) * n2 + n3);
        final int n9 = (int)(n7 - ((super.B - n4) * n5 + n6));
        final int n10 = (int)((super.P + super.H - n) * n2 + n3);
        final int n11 = (int)(n7 - ((super.B + super.J - n4) * n5 + n6));
        if (super.Q != null) {
            c15.B(super.Q);
        }
        if (super.D == 0 && (super.A == null || super.A == C28.S)) {
            if (this.c) {
                c15.a(n8, n9, n10, n11);
            }
            else {
                c15.a(n8, n11, n10, n9);
            }
        }
        else if (this.c) {
            c15.I(n8, n9, n10, n11, super.A, (int)(super.D * n2));
        }
        else {
            c15.I(n8, n11, n10, n9, super.A, (int)(super.D * n2));
        }
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((super.P - n) * n2 + n3);
        int n9 = (int)(n7 - ((super.B - n4) * n5 + n6));
        final int n10 = (int)((super.P + super.H - n) * n2 + n3);
        int n11 = (int)(n7 - ((super.B + super.J - n4) * n5 + n6));
        if (!this.c) {
            final int n12 = n9;
            n9 = n11;
            n11 = n12;
        }
        final int[] array = { n8, n9, n10, n11 };
        if (super.D == 0 && (super.A == null || super.A == C28.S)) {
            c20.Polyline(array, 2);
        }
        else {
            System.out.println("weight LineWidget");
        }
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4) {
        return super.P == n && super.B == n2 && super.H == super.H && super.J == super.J;
    }
    
    public Object clone() {
        return new C10(super.P, super.B, super.P + super.H, super.B + super.J);
    }
}
