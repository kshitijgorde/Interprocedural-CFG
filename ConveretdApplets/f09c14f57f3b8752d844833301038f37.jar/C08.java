// 
// Decompiled by Procyon v0.5.30
// 

public class C08 extends C47
{
    public static String c;
    
    public Object clone() {
        return new C08(super.d, super.f);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
    
    static {
        C08.c = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public C08(final int[] array, final int[] array2) {
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
                c15.t((int)((super.d[n8] - n) * n2 + n3), (int)(n7 - ((super.f[n8] - n4) * n5 + n6)));
            }
        }
    }
}
