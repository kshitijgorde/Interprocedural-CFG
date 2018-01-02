import jaclib.memory.Buffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class156_Sub2 extends Class156 implements Interface16
{
    private int anInt3422;
    static int anInt3423;
    
    @Override
    public final void method54(final int n, final int n2, final byte[] array, final int anInt3422) {
        try {
            this.method2496(array, n);
            if (n2 != 7896) {
                this.anInt3422 = -117;
            }
            this.anInt3422 = anInt3422;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.F(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt3422 + ')');
        }
    }
    
    static final void method2500(final int anInt415, final int n) {
        try {
            Class49.anInt415 = anInt415;
            if (n != 0) {
                method2500(-95, -97);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.A(" + anInt415 + ',' + n + ')');
        }
    }
    
    @Override
    public final long method52(final int n) {
        try {
            if (n != 24582) {
                this.anInt3422 = 44;
            }
            return super.aBuffer1247.getAddress();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.E(" + n + ')');
        }
    }
    
    static final long method2501(final byte b, final String s) {
        try {
            long n = 0L;
            for (int length = s.length(), i = 0; i < length; ++i) {
                n *= 37L;
                final char char1 = s.charAt(i);
                if (char1 >= 'A' && char1 <= 'Z') {
                    n += 1 - (-char1 + 65);
                }
                else if (char1 >= 'a' && char1 <= 'z') {
                    n += -97 + ('\u0001' + char1);
                }
                else if (char1 >= '0' && char1 <= '9') {
                    n += char1 - 21;
                }
                if (~n <= -177917621779460414L) {
                    break;
                }
            }
            if (b > -124) {
                Class156_Sub2.anInt3423 = 56;
            }
            while (~(n % 37L) == -1L && n != 0L) {
                n /= 37L;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.D(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class156_Sub2(final ha_Sub1 ha_Sub1, final int anInt3422, final byte[] array, final int n) {
        super(ha_Sub1, array, n);
        try {
            this.anInt3422 = anInt3422;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3422 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class156_Sub2(final ha_Sub1 ha_Sub1, final int anInt3422, final Buffer buffer) {
        super(ha_Sub1, buffer);
        try {
            this.anInt3422 = anInt3422;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt3422 + ',' + ((buffer != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method53(final int n) {
        try {
            if (n != -14112) {
                this.method55(-28);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.B(" + n + ')');
        }
    }
    
    @Override
    public final int method55(final int n) {
        try {
            return this.anInt3422;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "se.C(" + n + ')');
        }
    }
    
    static {
        Class156_Sub2.anInt3423 = 0;
    }
}
