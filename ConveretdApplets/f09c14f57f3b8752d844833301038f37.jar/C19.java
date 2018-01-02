// 
// Decompiled by Procyon v0.5.30
// 

public class C19 extends C47
{
    public static String a;
    
    public Object clone() {
        return new C19(super.d, super.f);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        for (int n8 = 0; n8 < super.d.length && n8 < super.f.length; ++n8) {
            C47.g[n8] = (int)((super.d[n8] - n) * n2 + n3);
            C47.h[n8] = (int)(n7 - ((super.f[n8] - n4) * n5 + n6));
        }
        final int[] array = new int[10];
        for (int n9 = 0; n9 < super.f.length - 2 && n9 < super.f.length - 2; ++n9) {
            for (int i = 0; i < 3; ++i) {
                array[i * 2] = C47.g[i + n9];
                array[i * 2 + 1] = C47.h[i + n9];
            }
            if (null != super.Q) {
                c20.CreateBrushIndirect(0, super.Q.getBlue() * 65536 + super.Q.getGreen() * 256 + super.Q.getRed());
                c20.SelectBrushObject();
                c20.Polygon(array, 3);
                c20.DeleteBrushObject();
            }
            else {
                c20.Polygon(array, 3);
            }
        }
    }
    
    static {
        C19.a = "Copyright (c) 1998 - Arnona Internet Software inc. All Rights Reserved";
    }
    
    public C19(final int[] array, final int[] array2) {
        super(array, array2);
        if (array.length > C47.g.length) {
            synchronized (this.getClass()) {
                C47.g = new int[(int)(array.length * 1.5)];
                C47.h = new int[(int)(array.length * 1.5)];
            }
        }
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        synchronized (this.getClass()) {
            for (int n8 = 0; n8 < super.d.length && n8 < super.f.length; ++n8) {
                C47.g[n8] = (int)((super.d[n8] - n) * n2 + n3);
                C47.h[n8] = (int)(n7 - ((super.f[n8] - n4) * n5 + n6));
            }
            final int[] array = new int[3];
            final int[] array2 = new int[3];
            final boolean e = c15.e();
            c15.n(true);
            for (int n9 = 0; n9 < super.f.length - 2 && n9 < super.f.length - 2; ++n9) {
                for (int i = 0; i < 3; ++i) {
                    array[i] = C47.g[i + n9];
                    array2[i] = C47.h[i + n9];
                }
                c15.u(array, array2, array.length);
            }
            c15.n(e);
        }
    }
}
