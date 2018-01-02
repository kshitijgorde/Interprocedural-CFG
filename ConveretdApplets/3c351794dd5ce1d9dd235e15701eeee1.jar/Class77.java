// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class77
{
    static IncomingOpcode aClass58_591;
    static IncomingOpcode aClass58_592;
    static Class293 aClass293_593;
    
    abstract void method773(final byte p0);
    
    abstract void method774(final byte p0);
    
    static final void method775(final int n, final Class207 aClass207_3687, final int[] anIntArray70, final Class207 aClass207_3688) {
        try {
            if (n != 3) {
                method775(-54, null, null, null);
            }
            if (anIntArray70 != null) {
                Class2.anIntArray70 = anIntArray70;
            }
            Class64_Sub17.aClass207_3687 = aClass207_3687;
            Class64_Sub16.aClass207_3683 = aClass207_3688;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fa.G(" + n + ',' + ((aClass207_3687 != null) ? "{...}" : "null") + ',' + ((anIntArray70 != null) ? "{...}" : "null") + ',' + ((aClass207_3688 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract Interface7 method776(final byte p0);
    
    public static void method777(final byte b) {
        try {
            Class77.aClass58_591 = null;
            Class77.aClass58_592 = null;
            Class77.aClass293_593 = null;
            if (b != 72) {
                method780(67, (byte)(-65), 92);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fa.H(" + b + ')');
        }
    }
    
    static final void method778(final int n, final int n2) {
        try {
            Class185.method2628(n2, n ^ 0xFFFFFFF2, 14).method1621(n ^ n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fa.E(" + n + ',' + n2 + ')');
        }
    }
    
    abstract boolean method779(final int p0, final int p1);
    
    static final int method780(final int n, final byte b, final int n2) {
        try {
            if (~n == 0xFFFFFFFE || ~n == 0xFFFFFFFC) {
                return Class373.anIntArray3479[n2 & 0x3];
            }
            if (b != -72) {
                return -111;
            }
            return Class137.anIntArray1081[n2 & 0x3];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fa.F(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static {
        Class77.aClass58_591 = new IncomingOpcode(99, 0);
        Class77.aClass58_592 = new IncomingOpcode(44, 4);
    }
}
