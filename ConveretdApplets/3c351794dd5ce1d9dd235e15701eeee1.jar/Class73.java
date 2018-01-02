// 
// Decompiled by Procyon v0.5.30
// 

final class Class73 implements Interface20
{
    int anInt3480;
    int anInt3481;
    static IncomingOpcode aClass58_3482;
    int anInt3483;
    int anInt3484;
    static Class6 aClass6_3485;
    int anInt3486;
    int anInt3487;
    boolean aBoolean3488;
    
    static final boolean method719(final int n, final int n2, final int n3, final int n4) {
        try {
            RuntimeException_Sub1.aClass111_3203.method2103(n2, n3, n, Class114.anIntArray958);
            final int n5 = Class114.anIntArray958[2];
            if (~n5 > -51) {
                return false;
            }
            Class114.anIntArray958[1] = Class331.anInt2800 * Class114.anIntArray958[1] / n5 + Class98_Sub10_Sub23.anInt5659;
            Class114.anIntArray958[2] = n5;
            Class114.anIntArray958[n4] = Class38.anInt358 * Class114.anIntArray958[0] / n5 + Class2.anInt69;
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eo.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final long method69(final boolean b) {
        try {
            final long[] aLongArray3164 = Class374.aLongArray3164;
            final long n = -1L;
            final long n2 = n >>> 104055176 ^ aLongArray3164[(int)(0xFFL & (this.anInt3483 ^ n))];
            final long n3 = n2 >>> -1169160440 ^ aLongArray3164[(int)((n2 ^ this.anInt3480 >> 333698888) & 0xFFL)];
            final long n4 = aLongArray3164[(int)((this.anInt3480 ^ n3) & 0xFFL)] ^ n3 >>> 2082866632;
            final long n5 = n4 >>> 785508872 ^ aLongArray3164[(int)(0xFFL & (n4 ^ this.anInt3484 >> 1900552600))];
            final long n6 = aLongArray3164[(int)((n5 ^ this.anInt3484 >> -944629936) & 0xFFL)] ^ n5 >>> -784712120;
            final long n7 = n6 >>> -982760568 ^ aLongArray3164[(int)(0xFFL & (this.anInt3484 >> -780870712 ^ n6))];
            final long n8 = aLongArray3164[(int)((n7 ^ this.anInt3484) & 0xFFL)] ^ n7 >>> -723616056;
            final long n9 = n8 >>> 546854024 ^ aLongArray3164[(int)(0xFFL & (this.anInt3486 ^ n8))];
            final long n10 = aLongArray3164[(int)((this.anInt3487 >> 19611320 ^ n9) & 0xFFL)] ^ n9 >>> -1652365816;
            final long n11 = aLongArray3164[(int)(0xFFL & (this.anInt3487 >> -1593786384 ^ n10))] ^ n10 >>> 172527176;
            final long n12 = n11 >>> -2082785080 ^ aLongArray3164[(int)(0xFFL & (n11 ^ this.anInt3487 >> -970562392))];
            final long n13 = n12 >>> 298776520 ^ aLongArray3164[(int)((this.anInt3487 ^ n12) & 0xFFL)];
            final long n14 = aLongArray3164[(int)((n13 ^ this.anInt3481) & 0xFFL)] ^ n13 >>> -1877289208;
            final long n15 = n14 >>> 1282797832 ^ aLongArray3164[(int)(0xFFL & ((this.aBoolean3488 ? 1 : 0) ^ n14))];
            if (!b) {
                method721(null, -115, '$');
            }
            return n15;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eo.C(" + b + ')');
        }
    }
    
    @Override
    public final boolean method68(final int n, final Interface20 interface20) {
        try {
            if (!(interface20 instanceof Class73)) {
                return false;
            }
            final Class73 class73 = (Class73)interface20;
            if (~class73.anInt3483 != ~this.anInt3483) {
                return false;
            }
            if (~this.anInt3480 != ~class73.anInt3480) {
                return false;
            }
            if (~this.anInt3484 != ~class73.anInt3484) {
                return false;
            }
            if (this.anInt3486 != class73.anInt3486) {
                return false;
            }
            if (class73.anInt3487 != this.anInt3487) {
                return false;
            }
            if (this.anInt3481 != class73.anInt3481) {
                return false;
            }
            if (n != 22000) {
                this.anInt3483 = -99;
            }
            return !this.aBoolean3488 != class73.aBoolean3488;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eo.D(" + n + ',' + ((interface20 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method720(final int n) {
        try {
            Class73.aClass58_3482 = null;
            Class73.aClass6_3485 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eo.B(" + n + ')');
        }
    }
    
    static final int method721(final String s, final int n, final char c) {
        try {
            int n2 = 0;
            for (int length = s.length(), i = 0; i < length; ++i) {
                if (~c == ~s.charAt(i)) {
                    ++n2;
                }
            }
            if (n < 10) {
                method720(61);
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eo.E(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + c + ')');
        }
    }
    
    static {
        Class73.aClass58_3482 = new IncomingOpcode(120, -1);
    }
}
