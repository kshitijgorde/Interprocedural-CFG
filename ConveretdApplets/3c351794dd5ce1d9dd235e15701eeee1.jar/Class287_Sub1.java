import jaclib.memory.Buffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class287_Sub1 extends Class287 implements Interface16
{
    private int anInt3418;
    static int[] anIntArray3419;
    static Class232 aClass232_3420;
    static int[] anIntArray3421;
    
    Class287_Sub1(final ha_Sub1 ha_Sub1, final int anInt3418, final Buffer buffer, final int n, final boolean b) {
        super(ha_Sub1, 34962, buffer, n, b);
        try {
            this.anInt3418 = anInt3418;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3418 + ',' + ((buffer != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    public static void method3390(final int n) {
        try {
            Class287_Sub1.anIntArray3421 = null;
            Class287_Sub1.aClass232_3420 = null;
            Class287_Sub1.anIntArray3419 = null;
            if (n != -28614) {
                Class287_Sub1.aClass232_3420 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.A(" + n + ')');
        }
    }
    
    @Override
    public final void method54(final int n, final int n2, final byte[] array, final int anInt3418) {
        try {
            this.method3389(0, n, array);
            this.anInt3418 = anInt3418;
            if (n2 != 7896) {
                this.anInt3418 = -39;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.F(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt3418 + ')');
        }
    }
    
    @Override
    public final long method52(final int n) {
        try {
            if (n != 24582) {
                Class287_Sub1.anIntArray3421 = null;
            }
            return 0L;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.E(" + n + ')');
        }
    }
    
    @Override
    public final int method55(final int n) {
        try {
            return this.anInt3418;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.C(" + n + ')');
        }
    }
    
    Class287_Sub1(final ha_Sub1 ha_Sub1, final int anInt3418, final byte[] array, final int n, final boolean b) {
        super(ha_Sub1, 34962, array, n, b);
        try {
            this.anInt3418 = anInt3418;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3418 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    @Override
    public final int method53(final int n) {
        try {
            if (n != -14112) {
                return 57;
            }
            return super.anInt2191;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.B(" + n + ')');
        }
    }
    
    @Override
    final void method3384(final int n) {
        try {
            super.aHa_Sub1_2185.method1887(this, n + 34962);
            if (n != 0) {
                this.method53(-28);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iv.D(" + n + ')');
        }
    }
    
    static {
        Class287_Sub1.anIntArray3419 = new int[256];
        for (int i = 0; i < 256; ++i) {
            int n = i;
            for (int n2 = 0; ~n2 > -9; ++n2) {
                if ((n & 0x1) == 0x1) {
                    n = (0xEDB88320 ^ n >>> 1453156353);
                }
                else {
                    n >>>= 1;
                }
            }
            Class287_Sub1.anIntArray3419[i] = n;
        }
        Class287_Sub1.aClass232_3420 = new Class232();
    }
}
