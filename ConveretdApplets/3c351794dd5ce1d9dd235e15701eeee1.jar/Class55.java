// 
// Decompiled by Procyon v0.5.30
// 

final class Class55
{
    Class151_Sub9 aClass151_Sub9_432;
    static IncomingOpcode aClass58_433;
    private int anInt434;
    private ha_Sub1 aHa_Sub1_435;
    private int anInt436;
    private Class151[] aClass151Array437;
    private int anInt438;
    private Class51 aClass51_439;
    static int anInt440;
    static String[][] aStringArrayArray441;
    static int anInt442;
    static int anInt443;
    static Class225[] aClass225Array444;
    
    public static void method506(final boolean b) {
        try {
            Class55.aClass225Array444 = null;
            if (b) {
                method506(false);
            }
            Class55.aStringArrayArray441 = null;
            Class55.aClass58_433 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dq.B(" + b + ')');
        }
    }
    
    final boolean method507(final int n, final int n2) {
        try {
            return n2 != -6634 || this.aClass151Array437[n].method2439(31565);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dq.D(" + n + ',' + n2 + ')');
        }
    }
    
    final void method508(final boolean b, boolean b2, int n, final int n2, final boolean b3, int anInt436) {
        try {
            b2 &= this.aHa_Sub1_435.method1747();
            if (b3) {
                if (!b2 && (~anInt436 == 0xFFFFFFFB || ~anInt436 == 0xFFFFFFF7 || anInt436 == 9)) {
                    if (~anInt436 == 0xFFFFFFFB) {
                        n = n2;
                    }
                    anInt436 = 2;
                }
                if (~anInt436 != -1 && b) {
                    anInt436 |= Integer.MIN_VALUE;
                }
                if (this.anInt436 == anInt436) {
                    if (this.anInt436 != 0) {
                        this.aClass151Array437[Integer.MAX_VALUE & this.anInt436].method2443(b, 255);
                        if (~this.anInt434 != ~n2 || ~this.anInt438 != ~n) {
                            this.aClass151Array437[Integer.MAX_VALUE & this.anInt436].method2441(n2, n, -57);
                            this.anInt438 = n;
                            this.anInt434 = n2;
                        }
                    }
                }
                else {
                    if (this.anInt436 != 0) {
                        this.aClass151Array437[this.anInt436 & Integer.MAX_VALUE].method2445((byte)61);
                    }
                    if (~anInt436 != -1) {
                        this.aClass151Array437[anInt436 & Integer.MAX_VALUE].method2440(!b3, b);
                        this.aClass151Array437[Integer.MAX_VALUE & anInt436].method2443(b, 255);
                        this.aClass151Array437[Integer.MAX_VALUE & anInt436].method2441(n2, n, -51);
                    }
                    this.anInt434 = n2;
                    this.anInt438 = n;
                    this.anInt436 = anInt436;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dq.A(" + b + ',' + b2 + ',' + n + ',' + n2 + ',' + b3 + ',' + anInt436 + ')');
        }
    }
    
    final boolean method509(final Class42 class42, final boolean b, final int n) {
        try {
            if (this.anInt436 == 0) {
                return false;
            }
            this.aClass151Array437[Integer.MAX_VALUE & this.anInt436].method2442(class42, b, n);
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dq.C(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    Class55(final ha_Sub1 aHa_Sub1_435) {
        this.anInt436 = 0;
        this.anInt438 = 0;
        this.anInt434 = 0;
        try {
            this.aHa_Sub1_435 = aHa_Sub1_435;
            this.aClass51_439 = new Class51(aHa_Sub1_435);
            (this.aClass151Array437 = new Class151[10])[1] = new Class151_Sub6(aHa_Sub1_435);
            this.aClass151Array437[2] = new Class151_Sub3(aHa_Sub1_435, this.aClass51_439);
            this.aClass151Array437[4] = new Class151_Sub1(aHa_Sub1_435, this.aClass51_439);
            this.aClass151Array437[5] = new Class151_Sub7(aHa_Sub1_435, this.aClass51_439);
            this.aClass151Array437[6] = new Class151_Sub5(aHa_Sub1_435);
            this.aClass151Array437[7] = new Class151_Sub8(aHa_Sub1_435);
            this.aClass151Array437[3] = (this.aClass151_Sub9_432 = new Class151_Sub9(aHa_Sub1_435));
            this.aClass151Array437[8] = new Class151_Sub4(aHa_Sub1_435, this.aClass51_439);
            this.aClass151Array437[9] = new Class151_Sub2(aHa_Sub1_435, this.aClass51_439);
            if (!this.aClass151Array437[8].method2439(31565)) {
                this.aClass151Array437[8] = this.aClass151Array437[4];
            }
            if (!this.aClass151Array437[9].method2439(31565)) {
                this.aClass151Array437[9] = this.aClass151Array437[8];
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dq.<init>(" + ((aHa_Sub1_435 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class55.anInt440 = 0;
        Class55.aStringArrayArray441 = new String[][] { { "M1", "M2", "S1", "F" }, { "M1", "M2", "M3", "S1", "S2", "F" }, { "M1", "M2", "M3", "M4", "S1", "S2", "S3", "F" } };
        Class55.aClass58_433 = new IncomingOpcode(39, 17);
        Class55.anInt442 = -2;
        Class55.anInt443 = 52;
        Class55.aClass225Array444 = new Class225[37];
    }
}
