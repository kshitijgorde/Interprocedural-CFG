// 
// Decompiled by Procyon v0.5.30
// 

final class Class302
{
    Class207 aClass207_2512;
    boolean aBoolean2513;
    static IncomingOpcode aClass58_2514;
    private Class207 aClass207_2515;
    boolean aBoolean2516;
    static int[] anIntArray2517;
    static int anInt2518;
    private Class79 aClass79_2519;
    static OutgoingOpcode aClass171_2520;
    static int[] anIntArray2521;
    Class79 aClass79_2522;
    static int anInt2523;
    static int anInt2524;
    Class79 aClass79_2525;
    static boolean aBoolean2526;
    Class79 aClass79_2527;
    int anInt2528;
    
    final Class352 method3546(final int id, final byte b) {
        try {
            final Class352 class352;
            synchronized (this.aClass79_2519) {
                class352 = (Class352)this.aClass79_2519.method802(-125, id);
            }
            if (class352 != null) {
                return class352;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2515) {
                method2745 = this.aClass207_2515.method2745(Class151.method2444(id, -119), za.method1674(-1035933944, id), false);
            }
            final Class352 class353 = new Class352();
            class353.id = id;
            class353.aClass302_2963 = this;
            if (method2745 != null) {
                class353.method3850(new Class98_Sub22(method2745), false);
            }
            class353.method3865(118);
            if (!this.aBoolean2516 && class353.aBoolean2927) {
                class353.anIntArray2934 = null;
                class353.aStringArray2939 = null;
            }
            if (class353.clippingFlag) {
                class353.walkable = false;
                class353.actionCount = 0;
            }
            synchronized (this.aClass79_2519) {
                this.aClass79_2519.method805(id, class353, (byte)(-80));
            }
            return class353;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.B(" + id + ',' + 0 + ')');
        }
    }
    
    final void method3547(final byte b) {
        try {
            synchronized (this.aClass79_2519) {
                this.aClass79_2519.method806((byte)(-90));
            }
            if (b > 126) {
                synchronized (this.aClass79_2522) {
                    this.aClass79_2522.method806((byte)(-105));
                }
                synchronized (this.aClass79_2525) {
                    this.aClass79_2525.method806((byte)99);
                }
                synchronized (this.aClass79_2527) {
                    this.aClass79_2527.method806((byte)8);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.J(" + b + ')');
        }
    }
    
    final void method3548(final int n, final byte b) {
        try {
            synchronized (this.aClass79_2519) {
                this.aClass79_2519.method800((byte)62, n);
            }
            synchronized (this.aClass79_2522) {
                this.aClass79_2522.method800((byte)62, n);
            }
            synchronized (this.aClass79_2525) {
                this.aClass79_2525.method800((byte)62, n);
            }
            if (b > 36) {
                synchronized (this.aClass79_2527) {
                    this.aClass79_2527.method800((byte)62, n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.A(" + n + ',' + b + ')');
        }
    }
    
    final void method3549(final byte b) {
        try {
            synchronized (this.aClass79_2519) {
                this.aClass79_2519.method794(72);
            }
            synchronized (this.aClass79_2522) {
                this.aClass79_2522.method794(103);
            }
            synchronized (this.aClass79_2525) {
                this.aClass79_2525.method794(119);
            }
            synchronized (this.aClass79_2527) {
                this.aClass79_2527.method794(123);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.G(" + b + ')');
        }
    }
    
    final void method3550(final int n, final int n2) {
        try {
            if (n != -129) {
                Class302.anInt2523 = -114;
            }
            this.aClass79_2519 = new Class79(n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.E(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method3551(final int n) {
        try {
            Class302.aClass171_2520 = null;
            Class302.anIntArray2521 = null;
            Class302.aClass58_2514 = null;
            if (n > -91) {
                method3551(92);
            }
            Class302.anIntArray2517 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.C(" + n + ')');
        }
    }
    
    final void method3552(final boolean aBoolean2513, final int n) {
        try {
            if (this.aBoolean2513 != aBoolean2513) {
                this.aBoolean2513 = aBoolean2513;
                this.method3549((byte)100);
                if (n <= 18) {
                    this.aClass79_2525 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.H(" + aBoolean2513 + ',' + n + ')');
        }
    }
    
    static final boolean method3553(final int n, final byte b) {
        try {
            final int n2 = b & 0xFF;
            if (~n2 == -1) {
                return false;
            }
            if (~n2 <= -129 && n2 < 160 && ~Class65.aCharArray497[n2 - 128] == -1) {
                return false;
            }
            if (n > -116) {
                Class302.anInt2524 = 55;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.I(" + n + ',' + b + ')');
        }
    }
    
    final void method3554(final boolean b, final int anInt2528) {
        try {
            this.anInt2528 = anInt2528;
            synchronized (this.aClass79_2522) {
                this.aClass79_2522.method794(91);
            }
            synchronized (this.aClass79_2525) {
                this.aClass79_2525.method794(102);
                if (!b) {
                    Class302.anInt2518 = 125;
                }
            }
            synchronized (this.aClass79_2527) {
                this.aClass79_2527.method794(14);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.D(" + b + ',' + anInt2528 + ')');
        }
    }
    
    final void method3555(final byte b, final boolean aBoolean2516) {
        try {
            if (aBoolean2516 == !this.aBoolean2516) {
                this.aBoolean2516 = aBoolean2516;
                this.method3549((byte)123);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.F(" + b + ',' + aBoolean2516 + ')');
        }
    }
    
    Class302(final Class279 class279, final int n, final boolean aBoolean2516, final Class207 aClass207_2515, final Class207 aClass207_2516) {
        this.aBoolean2513 = false;
        this.aClass79_2519 = new Class79(64);
        this.aClass79_2522 = new Class79(500);
        this.aClass79_2525 = new Class79(30);
        this.aClass79_2527 = new Class79(50);
        try {
            this.aBoolean2516 = aBoolean2516;
            this.aClass207_2515 = aClass207_2515;
            this.aClass207_2512 = aClass207_2516;
            if (this.aClass207_2515 != null) {
                this.aClass207_2515.method2761(0, -1 + this.aClass207_2515.method2752((byte)(-11)));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sia.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + aBoolean2516 + ',' + ((aClass207_2515 != null) ? "{...}" : "null") + ',' + ((aClass207_2516 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class302.aClass58_2514 = new IncomingOpcode(29, 5);
        Class302.aClass171_2520 = new OutgoingOpcode(47, 3);
        Class302.anIntArray2521 = new int[4];
    }
}
