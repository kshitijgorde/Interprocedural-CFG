// 
// Decompiled by Procyon v0.5.30
// 

final class Class217 implements Interface14
{
    static IncomingOpcode aClass58_3406;
    private Class207 aClass207_3407;
    static Class332[] aClass332Array3408;
    private String aString3409;
    static boolean[] aBooleanArray3410;
    
    static final void method2799(final int anInt529, final int anInt530, final boolean aBoolean5007, final byte b) {
        try {
            if (b <= 71) {
                method2800(-42);
            }
            if (~anInt530 > -8001 || ~anInt530 < -48001) {
                throw new IllegalArgumentException();
            }
            PlayerUpdateMask.anInt529 = anInt529;
            Class64_Sub15.anInt3678 = anInt530;
            Class151_Sub7.aBoolean5007 = aBoolean5007;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.A(" + anInt529 + ',' + anInt530 + ',' + aBoolean5007 + ',' + b + ')');
        }
    }
    
    static final boolean method2800(final int n) {
        try {
            if (n != -8001) {
                method2801((byte)(-63));
            }
            return Class98_Sub17_Sub1.aBoolean5778;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.C(" + n + ')');
        }
    }
    
    @Override
    public final Class191 method50(final int n) {
        try {
            if (n != 15763) {
                Class217.aBooleanArray3410 = null;
            }
            return Class191.aClass191_1475;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.B(" + n + ')');
        }
    }
    
    @Override
    public final int method51(final byte b) {
        try {
            if (b <= 126) {
                Class217.aClass58_3406 = null;
            }
            if (this.aClass207_3407.method2741(this.aString3409, 0)) {
                return 100;
            }
            return this.aClass207_3407.method2748(29952, this.aString3409);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.E(" + b + ')');
        }
    }
    
    public static void method2801(final byte b) {
        try {
            Class217.aBooleanArray3410 = null;
            if (b != 101) {
                method2799(67, 18, false, (byte)(-61));
            }
            Class217.aClass58_3406 = null;
            Class217.aClass332Array3408 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.D(" + b + ')');
        }
    }
    
    Class217(final Class207 aClass207_3407, final String aString3409) {
        try {
            this.aClass207_3407 = aClass207_3407;
            this.aString3409 = aString3409;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nw.<init>(" + ((aClass207_3407 != null) ? "{...}" : "null") + ',' + ((aString3409 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class217.aClass58_3406 = new IncomingOpcode(59, 3);
        Class217.aBooleanArray3410 = new boolean[5];
    }
}
