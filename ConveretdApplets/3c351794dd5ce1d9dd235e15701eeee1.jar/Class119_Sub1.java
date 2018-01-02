// 
// Decompiled by Procyon v0.5.30
// 

final class Class119_Sub1 extends Class119
{
    static int anInt4716;
    private int anInt4717;
    static Class154 aClass154_4718;
    private int anInt4719;
    private int anInt4720;
    private int anInt4721;
    
    static final void method2180(final int n, final int n2) {
        try {
            if (n2 != 15233) {
                method2181(false);
            }
            Class185.method2628(n, -103, 1).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method2181(final boolean b) {
        try {
            Class119_Sub1.aClass154_4718 = null;
            if (!b) {
                Class119_Sub1.anInt4716 = 12;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.A(" + b + ')');
        }
    }
    
    static final Class197 method2182(final Class207 class207, final boolean b, final int n) {
        try {
            final byte[] method2733 = class207.method2733(n, -96);
            if (method2733 == null) {
                return null;
            }
            if (!b) {
                method2181(false);
            }
            return new Class197(method2733);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.D(" + ((class207 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2174(final int n, final int n2, final int n3) {
        try {
            if (n3 != -5515) {
                Class119_Sub1.anInt4716 = 29;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class119_Sub1(final int anInt4721, final int anInt4722, final int anInt4723, final int anInt4724, final int n, final int n2) {
        super(-1, n, n2);
        try {
            this.anInt4721 = anInt4721;
            this.anInt4719 = anInt4723;
            this.anInt4720 = anInt4724;
            this.anInt4717 = anInt4722;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.<init>(" + anInt4721 + ',' + anInt4722 + ',' + anInt4723 + ',' + anInt4724 + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2179(final byte b, final int n, final int n2) {
        try {
            InputStream_Sub2.method125(n2 * this.anInt4719 >> -1503457972, super.anInt985, this.anInt4720 * n >> 243200236, this.anInt4717 * n >> -1908682836, n2 * this.anInt4721 >> 611692, 21597);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bp.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2178(final int n, final int n2, final int n3) {
    }
}
