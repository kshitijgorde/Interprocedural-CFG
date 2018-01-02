// 
// Decompiled by Procyon v0.5.30
// 

final class Class358
{
    Class246_Sub4 aClass246_Sub4_3028;
    static IncomingOpcode aClass58_3029;
    static Class110 aClass110_3030;
    private Class246_Sub4 aClass246_Sub4_3031;
    static Class148 aClass148_3032;
    static boolean aBoolean3033;
    static int[] anIntArray3034;
    
    final Class246_Sub4 method3884(final int n) {
        try {
            if (n < 18) {
                return null;
            }
            final Class246_Sub4 aClass246_Sub4_3031 = this.aClass246_Sub4_3031;
            if (this.aClass246_Sub4_3028 == aClass246_Sub4_3031) {
                return this.aClass246_Sub4_3031 = null;
            }
            this.aClass246_Sub4_3031 = aClass246_Sub4_3031.aClass246_Sub4_5091;
            return aClass246_Sub4_3031;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.G(" + n + ')');
        }
    }
    
    final int method3885(final boolean b) {
        try {
            if (!b) {
                return 36;
            }
            int n = 0;
            for (Class246_Sub4 class246_Sub4 = this.aClass246_Sub4_3028.aClass246_Sub4_5091; this.aClass246_Sub4_3028 != class246_Sub4; class246_Sub4 = class246_Sub4.aClass246_Sub4_5091) {
                ++n;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.B(" + b + ')');
        }
    }
    
    final void method3886(final byte b) {
        try {
            while (true) {
                final Class246_Sub4 aClass246_Sub4_5091 = this.aClass246_Sub4_3028.aClass246_Sub4_5091;
                if (this.aClass246_Sub4_3028 == aClass246_Sub4_5091) {
                    break;
                }
                aClass246_Sub4_5091.method3101(-74);
            }
            this.aClass246_Sub4_3031 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.H(" + b + ')');
        }
    }
    
    static final void method3887(final int n) {
        try {
            Class287_Sub1.anIntArray3421 = Class64_Sub15.method610(35, 2048, 0.4f, 4, true, true, 8, 8);
            if (n != 110) {
                method3889(-6, -105, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.D(" + n + ')');
        }
    }
    
    public static void method3888(final byte b) {
        try {
            Class358.anIntArray3034 = null;
            if (b != -3) {
                method3889(-15, -67, false);
            }
            Class358.aClass148_3032 = null;
            Class358.aClass58_3029 = null;
            Class358.aClass110_3030 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.F(" + b + ')');
        }
    }
    
    static final boolean method3889(int n, final int n2, final boolean b) {
        try {
            if (b) {
                Class358.anIntArray3034 = null;
            }
            if (n == 11) {
                n = 10;
            }
            final Class352 method3546 = Class130.aClass302_1028.method3546(n2, (byte)119);
            if (n >= 5 && n <= 8) {
                n = 4;
            }
            return method3546.method3853((byte)49, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    final Class246_Sub4 method3890(final byte b) {
        try {
            if (b != 71) {
                return null;
            }
            final Class246_Sub4 aClass246_Sub4_5091 = this.aClass246_Sub4_3028.aClass246_Sub4_5091;
            if (aClass246_Sub4_5091 == this.aClass246_Sub4_3028) {
                return this.aClass246_Sub4_3031 = null;
            }
            this.aClass246_Sub4_3031 = aClass246_Sub4_5091.aClass246_Sub4_5091;
            return aClass246_Sub4_5091;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.A(" + b + ')');
        }
    }
    
    final void method3891(final Class246_Sub4 class246_Sub4, final int n) {
        try {
            if (class246_Sub4.aClass246_Sub4_5092 != null) {
                class246_Sub4.method3101(-102);
            }
            class246_Sub4.aClass246_Sub4_5091 = this.aClass246_Sub4_3028;
            if (n != 8) {
                this.method3884(51);
            }
            class246_Sub4.aClass246_Sub4_5092 = this.aClass246_Sub4_3028.aClass246_Sub4_5092;
            class246_Sub4.aClass246_Sub4_5092.aClass246_Sub4_5091 = class246_Sub4;
            class246_Sub4.aClass246_Sub4_5091.aClass246_Sub4_5092 = class246_Sub4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.E(" + ((class246_Sub4 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class358() {
        this.aClass246_Sub4_3028 = new Class246_Sub4();
        try {
            this.aClass246_Sub4_3028.aClass246_Sub4_5092 = this.aClass246_Sub4_3028;
            this.aClass246_Sub4_3028.aClass246_Sub4_5091 = this.aClass246_Sub4_3028;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vn.<init>()");
        }
    }
    
    static {
        Class358.aClass58_3029 = new IncomingOpcode(110, -1);
        Class358.aClass110_3030 = new Class110();
        Class358.aClass148_3032 = new Class148();
        Class358.aBoolean3033 = true;
        Class358.anIntArray3034 = new int[] { 36064, 36065, 36066, 36067, 36068, 36069, 36070, 36071, 36096 };
    }
}
