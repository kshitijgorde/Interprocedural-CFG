import jaclib.memory.heap.NativeHeap;

// 
// Decompiled by Procyon v0.5.30
// 

final class za_Sub2 extends za
{
    static boolean aBoolean6079;
    static int anInt6080;
    static double aDouble6081;
    NativeHeap aNativeHeap6082;
    
    static final void method1680(final int anInt6054, final int n, final int n2) {
        try {
            if (n2 == 9767) {
                final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -68, 6);
                method2628.method1626((byte)(-103));
                method2628.anInt6054 = anInt6054;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.D(" + anInt6054 + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method1681(final byte b) {
        try {
            this.aNativeHeap6082.b();
            if (b != 36) {
                method1682(-128, -23, -19);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.F(" + b + ')');
        }
    }
    
    static final boolean method1682(final int n, final int n2, final int n3) {
        try {
            if (n2 != 0) {
                method1683(-45, 78);
            }
            return (n & 0x800) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.I(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    za_Sub2(final int n) {
        try {
            this.aNativeHeap6082 = new NativeHeap(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.<init>(" + n + ')');
        }
    }
    
    static final boolean method1683(final int n, final int n2) {
        try {
            return n == -11297 && (n2 == 10 || ~n2 == 0xFFFFFFF4 || n2 == 12);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1684(final String s, final int n, final byte b) {
        try {
            Class98_Sub45.method1521((byte)122, n, s, 0, "", "", "");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.E(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    static final void method1685(final int n, final int n2, final int n3, final boolean b, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            Label_0208: {
                if (n2 >= Class76_Sub8.anInt3778 && Class3.anInt77 >= n2 && ~Class76_Sub8.anInt3778 >= ~n && ~n >= ~Class3.anInt77 && ~Class76_Sub8.anInt3778 >= ~n3 && ~Class3.anInt77 <= ~n3 && n9 >= Class76_Sub8.anInt3778 && ~Class3.anInt77 <= ~n9 && n8 >= Class98_Sub10_Sub38.anInt5753 && ~n8 >= ~Class218.anInt1635 && Class98_Sub10_Sub38.anInt5753 <= n5 && ~n5 >= ~Class218.anInt1635 && n4 >= Class98_Sub10_Sub38.anInt5753 && Class218.anInt1635 >= n4 && ~Class98_Sub10_Sub38.anInt5753 >= ~n6 && ~Class218.anInt1635 <= ~n6) {
                    Class176.method2579(n8, n4, n6, n, n2, n7, n3, n9, n5, 22024);
                    if (!client.aBoolean3553) {
                        break Label_0208;
                    }
                }
                Class339.method3788(n2, n7, n4, n, (byte)(-67), n9, n3, n6, n8, n5);
            }
            if (!b) {
                method1682(87, 47, -5);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ig.H(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    static {
        za_Sub2.aBoolean6079 = false;
        za_Sub2.anInt6080 = -1;
    }
}
