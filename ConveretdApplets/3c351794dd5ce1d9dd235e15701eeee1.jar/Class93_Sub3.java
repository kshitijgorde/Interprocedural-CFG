// 
// Decompiled by Procyon v0.5.30
// 

final class Class93_Sub3 extends Class93
{
    static IncomingOpcode aClass58_5493;
    static String aString5494;
    int anInt5495;
    int anInt5496;
    
    public static void method912(final int n) {
        try {
            Class93_Sub3.aString5494 = null;
            Class93_Sub3.aClass58_5493 = null;
            if (n != 58) {
                method912(108);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uu.C(" + n + ')');
        }
    }
    
    static final long method913(final int n) {
        try {
            if (n != 58) {
                Class93_Sub3.aClass58_5493 = null;
            }
            return OutputStream_Sub1.aClass240_36.method2924((byte)53);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uu.B(" + n + ')');
        }
    }
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                Class93_Sub3.aClass58_5493 = null;
            }
            return Class47.aClass113_399;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uu.A(" + n + ')');
        }
    }
    
    Class93_Sub3(final Class63 class63, final Class110 class64, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int anInt5496, final int anInt5497) {
        super(class63, class64, n, n2, n3, n4, n5, n6, n7);
        try {
            this.anInt5495 = anInt5497;
            this.anInt5496 = anInt5496;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uu.<init>(" + ((class63 != null) ? "{...}" : "null") + ',' + ((class64 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + anInt5496 + ',' + anInt5497 + ')');
        }
    }
    
    static {
        Class93_Sub3.aString5494 = null;
        Class93_Sub3.aClass58_5493 = new IncomingOpcode(58, -1);
    }
}
