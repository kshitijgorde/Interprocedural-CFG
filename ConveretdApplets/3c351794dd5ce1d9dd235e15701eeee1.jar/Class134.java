// 
// Decompiled by Procyon v0.5.30
// 

class Class134 implements Interface18
{
    static boolean aBoolean3457;
    static Class196 aClass196_3458;
    Class332 aClass332_3459;
    Class337 aClass337_3460;
    static int anInt3461;
    private Class207 aClass207_3462;
    static float aFloat3463;
    static int anInt3464;
    static Class139 aClass139_3465;
    static OutgoingOpcode aClass171_3466;
    
    public static void method2241(final int n) {
        try {
            Class134.aClass139_3465 = null;
            Class134.aClass171_3466 = null;
            Class134.aClass196_3458 = null;
            if (n > -79) {
                Class134.aFloat3463 = 0.14854974f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.H(" + n + ')');
        }
    }
    
    @Override
    public void method60(final boolean b, final byte b2) {
        try {
            if (b2 < -81) {
                if (b) {
                    this.aClass332_3459.method3735(this.aClass337_3460.aClass63_3538.method545(Class98_Sub17_Sub1.anInt5782, this.aClass332_3459.method3737(), (byte)83) + this.aClass337_3460.anInt3541, this.aClass337_3460.aClass110_3540.method2088(this.aClass332_3459.method3749(), Class246_Sub2.anInt5072, (byte)(-56)) + this.aClass337_3460.anInt3542);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.B(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            if (b != -43) {
                this.aClass207_3462 = null;
            }
            this.aClass332_3459 = Class237_Sub1.method2915(this.aClass337_3460.anInt3535, this.aClass207_3462, (byte)(-89));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.C(" + b + ')');
        }
    }
    
    @Override
    public final boolean method59(final int n) {
        try {
            if (n != 14017) {
                this.method58((byte)85);
            }
            return this.aClass207_3462.method2742(n ^ 0xFFFFC902, this.aClass337_3460.anInt3535);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.A(" + n + ')');
        }
    }
    
    static final boolean method2242(final int n, final int n2, final Class155 class155) {
        try {
            RuntimeException_Sub1.aClass111_3203.method2103(class155.anIntArray1240[n], class155.anIntArray1237[n], class155.anIntArray1241[n], Class114.anIntArray958);
            if (n2 != 5541) {
                Class134.aClass196_3458 = null;
            }
            final int n3 = Class114.anIntArray958[2];
            if (n3 < 50) {
                return false;
            }
            class155.aShortArray1244[n] = (short)(Class2.anInt69 + Class38.anInt358 * Class114.anIntArray958[0] / n3);
            class155.aShortArray1235[n] = (short)(Class98_Sub10_Sub23.anInt5659 + Class114.anIntArray958[1] * Class331.anInt2800 / n3);
            class155.aShortArray1234[n] = (short)n3;
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.I(" + n + ',' + n2 + ',' + ((class155 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method2243(final boolean b) {
        try {
            if (!b) {
                return -16;
            }
            return Class118.anInt979;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.J(" + b + ')');
        }
    }
    
    Class134(final Class207 aClass207_3462, final Class337 aClass337_3460) {
        try {
            this.aClass337_3460 = aClass337_3460;
            this.aClass207_3462 = aClass207_3462;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jca.<init>(" + ((aClass207_3462 != null) ? "{...}" : "null") + ',' + ((aClass337_3460 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class134.aBoolean3457 = true;
        Class134.aClass196_3458 = new Class196("WTI", "office", "_wti", 5);
        Class134.anInt3464 = 0;
        Class134.aClass171_3466 = new OutgoingOpcode(11, 15);
    }
}
