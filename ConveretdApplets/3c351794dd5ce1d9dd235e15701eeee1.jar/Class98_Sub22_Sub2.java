import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub22_Sub2 extends Class98_Sub22
{
    static OutgoingOpcode aClass171_5792;
    static IncomingOpcode aClass58_5793;
    static float aFloat5794;
    
    static final int method1262(final int n, final int n2) {
        try {
            if (n != 8) {
                method1262(43, -97);
            }
            if (n2 < 96) {
                return 0;
            }
            if (n2 < 128) {
                return 2;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vda.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class98_Sub22_Sub2(final int n) {
        super(n);
    }
    
    public static void method1263(final int n) {
        try {
            if (n >= -66) {
                method1262(-69, 69);
            }
            Class98_Sub22_Sub2.aClass58_5793 = null;
            Class98_Sub22_Sub2.aClass171_5792 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vda.D(" + n + ')');
        }
    }
    
    final void method1264(final byte b, final float n) {
        try {
            final int floatToRawIntBits = Stream.floatToRawIntBits(n);
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> -1300741416);
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> 494236848);
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> -747420472);
            super.aByteArray3992[super.anInt3991++] = (byte)floatToRawIntBits;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vda.B(" + b + ',' + n + ')');
        }
    }
    
    final void method1265(final byte b, final float n) {
        try {
            final int floatToRawIntBits = Stream.floatToRawIntBits(n);
            if (b != -52) {
                Class98_Sub22_Sub2.aClass171_5792 = null;
            }
            super.aByteArray3992[super.anInt3991++] = (byte)floatToRawIntBits;
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> 660574952);
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> 664922064);
            super.aByteArray3992[super.anInt3991++] = (byte)(floatToRawIntBits >> 566266104);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vda.C(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub22_Sub2.aClass171_5792 = new OutgoingOpcode(71, 7);
        Class98_Sub22_Sub2.aFloat5794 = 0.0f;
        Class98_Sub22_Sub2.aClass58_5793 = new IncomingOpcode(17, 8);
    }
}
