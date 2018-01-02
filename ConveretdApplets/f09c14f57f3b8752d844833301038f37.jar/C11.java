// 
// Decompiled by Procyon v0.5.30
// 

public class C11 extends C07
{
    int a;
    int b;
    public static String c;
    int d;
    
    public Object clone() {
        return new C11(super.f, super.d, super.c, super.g, this.b, this.a, this.d);
    }
    
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
        if (abs == 0 || abs2 == 0) {
            return;
        }
        if (super.D > 0) {
            abs3 = Math.abs(n10 - (int)((super.h + super.D - n) * n2 + n3));
        }
        if (super.D > 0 && abs3 > 0) {
            System.out.println("EllipseArc weight endScreenWeight");
            for (int i = 0; i < abs3; ++i) {
                if (super.A == C28.S) {}
            }
        }
        else if (super.A == C28.S) {
            final int n12 = n8 - abs / 2;
            final int n13 = n9 - abs2 / 2;
            final int n14 = n12 + abs;
            final int n15 = n13 + abs2;
            final double n16 = this.b;
            final double n17 = this.a;
            c20.Arc(n12, n13, n14, n15, (int)(n12 + abs / 2 + Math.cos(n16 * 3.141592653589793 / 180.0) * abs / 2.0), (int)(n13 + abs / 2 - Math.sin(n16 * 3.141592653589793 / 180.0) * abs / 2.0), (int)(n12 + abs / 2 + Math.cos(n17 * 3.141592653589793 / 180.0) * abs / 2.0), (int)(n13 + abs / 2 - Math.sin(n17 * 3.141592653589793 / 180.0) * abs / 2.0));
        }
        else {
            System.out.println("EllipseArc No weight No PATTERN_SOLID");
        }
    }
    
    static {
        C11.c = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
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
                    c15.g(n8 - abs / 2 - i, n9 - abs2 / 2 - i, abs + i * 2, abs2 + i * 2, this.b, this.a);
                }
                else {
                    c15.j(n8 - abs / 2 - i, n9 - abs2 / 2 - i, abs + i * 2, abs2 + i * 2, super.A, this.b, this.a);
                }
            }
        }
        else if (super.A == C28.S) {
            c15.g(n8 - abs / 2, n9 - abs2 / 2, abs, abs2, this.b, this.a);
        }
        else {
            c15.j(n8 - abs / 2, n9 - abs2 / 2, abs, abs2, super.A, this.b, this.a);
        }
    }
    
    public C11(final int n, final int n2, final int n3, final int n4, int n5, int n6, final int d) {
        super(n, n2, n3, n4);
        this.b = n5 + d;
        if (n5 > 360) {
            n5 %= 360;
        }
        this.a = n6 + d;
        if (n6 > 360) {
            n6 %= 360;
        }
        this.d = d;
    }
}
