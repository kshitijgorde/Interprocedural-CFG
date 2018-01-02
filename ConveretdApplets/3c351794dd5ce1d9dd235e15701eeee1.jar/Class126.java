// 
// Decompiled by Procyon v0.5.30
// 

final class Class126
{
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iia.toString()");
        }
    }
    
    static final int method2216(int n, final int n2, int n3) {
        try {
            if (n2 < 27) {
                return 3;
            }
            if (~n > ~n3) {
                final int n4 = n;
                n = n3;
                n3 = n4;
            }
            while (~n3 != -1) {
                final int n5 = n % n3;
                n = n3;
                n3 = n5;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iia.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2217(final byte b, final Class98_Sub47 class98_Sub47, final int n, final ha ha, final Class326 class326, int n2, final Class24 class327, final int n3, final int n4) {
        try {
            if (b >= 6) {
                final int n5 = n4 - (n / 2 + 5);
                final int n6 = n2 + 2;
                if (class327.anInt226 != 0) {
                    ha.method1760(10 + n, -n6 + n2 - -(n3 * class326.method3705()) + 1, n6, class327.anInt226, (byte)(-66), n5);
                }
                if (class327.anInt239 != 0) {
                    ha.method1781(true, -n6 + n3 * class326.method3705() + n2 + 1, 10 + n, class327.anInt239, n5, n6);
                }
                int n7 = class327.anInt257;
                if (class98_Sub47.aBoolean4275 && class327.anInt238 != -1) {
                    n7 = class327.anInt238;
                }
                for (int n8 = 0; ~n3 < ~n8; ++n8) {
                    String substring = Class35.aStringArray335[n8];
                    if (~(n3 - 1) < ~n8) {
                        substring = substring.substring(0, -4 + substring.length());
                    }
                    class326.method3706(ha, substring, n4, n2, n7, true);
                    n2 += class326.method3705();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iia.B(" + b + ',' + ((class98_Sub47 != null) ? "{...}" : "null") + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class326 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((class327 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
}
