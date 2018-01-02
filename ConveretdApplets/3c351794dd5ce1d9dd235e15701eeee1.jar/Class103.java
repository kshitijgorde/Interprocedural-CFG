// 
// Decompiled by Procyon v0.5.30
// 

final class Class103 implements Runnable
{
    static int anInt890;
    Class88 aClass88_891;
    static int anInt892;
    volatile boolean aBoolean893;
    volatile Class268[] aClass268Array894;
    volatile boolean aBoolean895;
    static int anInt896;
    
    @Override
    public final void run() {
        try {
            this.aBoolean895 = true;
            try {
                while (!this.aBoolean893) {
                    for (int n = 0; ~n > -3; ++n) {
                        final Class268 class268 = this.aClass268Array894[n];
                        if (class268 != null) {
                            class268.method3261((byte)(-122));
                        }
                    }
                    Class246_Sub7.method3131(0, 10L);
                    PlayerUpdate.method2857(this.aClass88_891, 31668, null);
                }
            }
            catch (Exception ex) {
                Class305_Sub1.method3585(ex, -123, null);
            }
            finally {
                this.aBoolean895 = false;
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "gi.run()");
        }
    }
    
    static final void method1711(final ha ha, final byte b, final Class98_Sub47 class98_Sub47, final Class24 class24) {
        try {
            final Class332 method286 = class24.method286(ha, (byte)92);
            if (method286 != null) {
                int n = method286.method3734();
                if (~n > ~method286.method3731()) {
                    n = method286.method3731();
                }
                final int n2 = 10;
                int n3 = class98_Sub47.anInt4266;
                if (b <= 7) {
                    Class103.anInt892 = 104;
                }
                int n4 = class98_Sub47.anInt4271;
                int i = 0;
                int n5 = 0;
                int n6 = 0;
                if (class24.aString263 != null) {
                    i = Class98_Sub46_Sub2_Sub2.aClass197_6296.method2675(class24.aString263, Class35.aStringArray335, null, null, -1);
                    for (int n7 = 0; i > n7; ++n7) {
                        String substring = Class35.aStringArray335[n7];
                        if (n7 < i - 1) {
                            substring = substring.substring(0, -4 + substring.length());
                        }
                        final int method287 = Class339_Sub1.aClass326_5308.method3701(substring);
                        if (n5 < method287) {
                            n5 = method287;
                        }
                    }
                    n6 = Class339_Sub1.aClass326_5308.method3705() * i - -(Class339_Sub1.aClass326_5308.method3704() / 2);
                }
                int n8 = n / 2 + class98_Sub47.anInt4266;
                if (Class278.anInt2086 - -n <= n3) {
                    if (~n3 < ~(-n + Class278.anInt2093)) {
                        n8 = Class278.anInt2093 - n / 2 - (n2 + n5 / 2) - 5;
                        n3 = -n + Class278.anInt2093;
                    }
                }
                else {
                    n8 = n5 / 2 + n2 + (n / 2 + Class278.anInt2086 + 5);
                    n3 = Class278.anInt2086;
                }
                int anInt4271 = class98_Sub47.anInt4271;
                if (~(Class278.anInt2077 - -n) < ~n4) {
                    anInt4271 = n / 2 + n2 + Class278.anInt2077;
                    n4 = Class278.anInt2077;
                }
                else if (~(Class278.anInt2094 - n) > ~n4) {
                    anInt4271 = -n6 + (-n2 + Class278.anInt2094) + -(n / 2);
                    n4 = -n + Class278.anInt2094;
                }
                method286.method3730(n3 + n / 2.0f, n / 2.0f + n4, 4096, 0xFFFF & (int)(32767.0 * (Math.atan2(n3 - class98_Sub47.anInt4266, -class98_Sub47.anInt4271 + n4) / 3.141592653589793)));
                int anInt4272 = -2;
                int anInt4273 = -2;
                int anInt4274 = -2;
                int anInt4275 = -2;
                if (class24.aString263 != null) {
                    anInt4273 = anInt4271;
                    anInt4272 = n8 + -(n5 / 2) - 5;
                    anInt4275 = 3 + (i * Class339_Sub1.aClass326_5308.method3705() + anInt4273);
                    anInt4274 = 10 + anInt4272 + n5;
                    if (~class24.anInt226 != -1) {
                        ha.method1760(anInt4274 + -anInt4272, anInt4275 + -anInt4273, anInt4273, class24.anInt226, (byte)(-66), anInt4272);
                    }
                    if (~class24.anInt239 != -1) {
                        ha.method1781(true, anInt4275 + -anInt4273, anInt4274 - anInt4272, class24.anInt239, anInt4272, anInt4273);
                    }
                    for (int n9 = 0; ~n9 > ~i; ++n9) {
                        String substring2 = Class35.aStringArray335[n9];
                        if (n9 < i - 1) {
                            substring2 = substring2.substring(0, -4 + substring2.length());
                        }
                        Class339_Sub1.aClass326_5308.method3706(ha, substring2, n8, anInt4271, class24.anInt257, true);
                        anInt4271 += Class339_Sub1.aClass326_5308.method3705();
                    }
                }
                if (class24.anInt245 != -1 || class24.aString263 != null) {
                    final Class98_Sub23 class98_Sub48 = new Class98_Sub23(class98_Sub47);
                    final int n10 = n >> 1;
                    class98_Sub48.anInt4004 = anInt4275;
                    class98_Sub48.anInt4005 = anInt4273;
                    class98_Sub48.anInt4002 = anInt4274;
                    class98_Sub48.anInt4003 = anInt4272;
                    class98_Sub48.anInt4000 = n4 - -n10;
                    class98_Sub48.anInt4006 = n3 + n10;
                    class98_Sub48.anInt3996 = n3 + -n10;
                    class98_Sub48.anInt3999 = -n10 + n4;
                    Class8.aClass148_110.method2419(class98_Sub48, -20911);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gi.A(" + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + ((class98_Sub47 != null) ? "{...}" : "null") + ',' + ((class24 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class103() {
        this.aClass268Array894 = new Class268[2];
        this.aBoolean893 = false;
        this.aBoolean895 = false;
    }
    
    static {
        Class103.anInt896 = 0;
    }
}
