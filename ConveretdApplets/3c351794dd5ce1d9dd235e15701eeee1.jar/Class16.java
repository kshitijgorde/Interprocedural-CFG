// 
// Decompiled by Procyon v0.5.30
// 

final class Class16
{
    private int anInt187;
    private int anInt188;
    private Class148 aClass148_189;
    static int anInt190;
    static IncomingOpcode aClass58_191;
    private int anInt192;
    private int anInt193;
    private int[][] anIntArrayArray194;
    private Class98_Sub48[] aClass98_Sub48Array195;
    static int anInt196;
    static int anInt197;
    boolean aBoolean198;
    static int anInt199;
    
    public static void method235(final byte b) {
        try {
            Class16.aClass58_191 = null;
            if (b != 102) {
                Class16.aClass58_191 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bca.A(" + b + ')');
        }
    }
    
    final void method236(final int n) {
        try {
            for (int n2 = 0; this.anInt188 > n2; ++n2) {
                this.anIntArrayArray194[n2] = null;
            }
            this.anIntArrayArray194 = null;
            this.aClass98_Sub48Array195 = null;
            this.aClass148_189.method2422((byte)47);
            this.aClass148_189 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bca.B(" + n + ')');
        }
    }
    
    final int[] method237(final byte b, final int anInt187) {
        try {
            if (b != 98) {
                return null;
            }
            if (this.anInt192 == this.anInt188) {
                this.aBoolean198 = (this.aClass98_Sub48Array195[anInt187] == null);
                this.aClass98_Sub48Array195[anInt187] = Class132.aClass98_Sub48_1048;
                return this.anIntArrayArray194[anInt187];
            }
            if (this.anInt188 == 1) {
                this.aBoolean198 = (~anInt187 != ~this.anInt187);
                this.anInt187 = anInt187;
                return this.anIntArrayArray194[0];
            }
            Class98_Sub48 class98_Sub48 = this.aClass98_Sub48Array195[anInt187];
            if (class98_Sub48 != null) {
                this.aBoolean198 = false;
            }
            else {
                this.aBoolean198 = true;
                if (this.anInt193 < this.anInt188) {
                    class98_Sub48 = new Class98_Sub48(anInt187, this.anInt193);
                    ++this.anInt193;
                }
                else {
                    final Class98_Sub48 class98_Sub49 = (Class98_Sub48)this.aClass148_189.method2427(-111);
                    class98_Sub48 = new Class98_Sub48(anInt187, class98_Sub49.anInt4282);
                    this.aClass98_Sub48Array195[class98_Sub49.anInt4278] = null;
                    class98_Sub49.method942(63);
                }
                this.aClass98_Sub48Array195[anInt187] = class98_Sub48;
            }
            this.aClass148_189.method2423(-2, class98_Sub48);
            return this.anIntArrayArray194[class98_Sub48.anInt4282];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bca.C(" + b + ',' + anInt187 + ')');
        }
    }
    
    final int[][] method238(final int n) {
        try {
            if (~this.anInt188 != ~this.anInt192) {
                throw new RuntimeException("Can only retrieve a full image cache");
            }
            for (int n2 = 0; this.anInt188 > n2; ++n2) {
                this.aClass98_Sub48Array195[n2] = Class132.aClass98_Sub48_1048;
            }
            return this.anIntArrayArray194;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bca.D(" + n + ')');
        }
    }
    
    Class16(final int anInt188, final int anInt189, final int n) {
        this.anInt187 = -1;
        this.anInt193 = 0;
        this.aClass148_189 = new Class148();
        this.aBoolean198 = false;
        try {
            this.anInt192 = anInt189;
            this.anInt188 = anInt188;
            this.anIntArrayArray194 = new int[this.anInt188][n];
            this.aClass98_Sub48Array195 = new Class98_Sub48[this.anInt192];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bca.<init>(" + anInt188 + ',' + anInt189 + ',' + n + ')');
        }
    }
    
    static {
        Class16.anInt190 = -1;
        Class16.aClass58_191 = new IncomingOpcode(45, 0);
        Class16.anInt196 = 0;
        Class16.anInt199 = 7000;
        Class16.anInt197 = Class16.anInt199;
    }
}
