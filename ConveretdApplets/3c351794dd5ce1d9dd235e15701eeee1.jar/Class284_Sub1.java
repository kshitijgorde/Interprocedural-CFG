// 
// Decompiled by Procyon v0.5.30
// 

class Class284_Sub1 extends Class284
{
    private int[] anIntArray5172;
    private int anInt5173;
    private byte[] aByteArray5174;
    private int anInt5175;
    static IncomingOpcode aClass58_5176;
    static Class43 aClass43_5177;
    static int[] anIntArray5178;
    
    void method3363(final byte b, final int n, final byte b2) {
        try {
            this.aByteArray5174[this.anInt5173++] = (byte)(Class202.method2702(b >> 1624804897, 127) + 127);
            if (b2 != 42) {
                Class284_Sub1.aClass58_5176 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.M(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    public static void method3364(final int n) {
        try {
            Class284_Sub1.aClass43_5177 = null;
            Class284_Sub1.anIntArray5178 = null;
            Class284_Sub1.aClass58_5176 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.P(" + n + ')');
        }
    }
    
    @Override
    final void method3354(final int n) {
        try {
            this.anInt5173 = 0;
            this.anInt5175 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.D(" + n + ')');
        }
    }
    
    @Override
    final void method3356(final int n, final int n2, final int n3) {
        try {
            this.anInt5175 += n2 * this.anIntArray5172[n] >> 460423148;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3358(final int anInt5175) {
        try {
            this.anInt5175 = Math.abs(this.anInt5175);
            if (~this.anInt5175 <= -4097) {
                this.anInt5175 = 4095;
            }
            this.method3363((byte)(this.anInt5175 >> 1016186948), this.anInt5173++, (byte)42);
            this.anInt5175 = anInt5175;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.H(" + anInt5175 + ')');
        }
    }
    
    Class284_Sub1(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        super(n, n2, n3, n4, n5);
        try {
            this.anIntArray5172 = new int[super.anInt2159];
            for (int i = 0; i < super.anInt2159; ++i) {
                this.anIntArray5172[i] = (short)(4096.0 * Math.pow(n6, i));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gd.<init>(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    static {
        Class284_Sub1.aClass58_5176 = new IncomingOpcode(107, 6);
    }
}
