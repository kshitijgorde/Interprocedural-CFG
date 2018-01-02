// 
// Decompiled by Procyon v0.5.30
// 

final class Class9
{
    int anInt114;
    int anInt115;
    boolean aBoolean116;
    Class335 aClass335_117;
    
    static final void method189(final Class98_Sub46_Sub9 class98_Sub46_Sub9, final byte b) {
        try {
            if (b == 87) {
                boolean b2 = false;
                class98_Sub46_Sub9.method1524((byte)(-90));
                for (Class98_Sub46_Sub9 class98_Sub46_Sub10 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0)) {
                    if (Class378.method4004((byte)127, class98_Sub46_Sub9.method1559(78), class98_Sub46_Sub10.method1559(103))) {
                        b2 = true;
                        Class51.method487(b - 38, class98_Sub46_Sub10, class98_Sub46_Sub9);
                        break;
                    }
                }
                if (!b2) {
                    Class98_Sub18.aClass215_3949.method2785(class98_Sub46_Sub9, -107);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.G(" + ((class98_Sub46_Sub9 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Class332 method190(final boolean b, final int n, final int n2, final ha ha) {
        try {
            final long n3 = n2 << -529389104 | this.anInt114 | (b ? 262144 : 0) | ha.anInt937 << -1675288589;
            final Class332 class332 = (Class332)this.aClass335_117.aClass79_2818.method802(-119, n3);
            if (class332 != null) {
                return class332;
            }
            if (!this.aClass335_117.aClass207_2814.method2742(-51, this.anInt114)) {
                return null;
            }
            final Class324 method3685 = Class324.method3685(this.aClass335_117.aClass207_2814, this.anInt114, n);
            if (method3685 != null) {
                final Class324 class333 = method3685;
                final Class324 class334 = method3685;
                final Class324 class335 = method3685;
                final Class324 class336 = method3685;
                final boolean b2 = false;
                class336.anInt2721 = (b2 ? 1 : 0);
                class335.anInt2719 = (b2 ? 1 : 0);
                class334.anInt2725 = (b2 ? 1 : 0);
                class333.anInt2724 = (b2 ? 1 : 0);
                if (b) {
                    method3685.method3682();
                }
                for (int i = 0; i < n2; ++i) {
                    method3685.method3687();
                }
            }
            final Class332 method3686 = ha.method1758(method3685, true);
            if (method3686 != null) {
                this.aClass335_117.aClass79_2818.method805(n3, method3686, (byte)(-80));
            }
            return method3686;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.C(" + b + ',' + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method191(final int n) {
        try {
            return this.aClass335_117.aClass207_2814.method2742(-68, this.anInt114);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.F(" + n + ')');
        }
    }
    
    final void method192(final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            if (b) {
                method189(null, (byte)10);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-118));
                if (~unsignedByte == -1) {
                    break;
                }
                this.method193(class98_Sub22, (byte)(-43), unsignedByte);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method193(final Class98_Sub22 class98_Sub22, final byte b, final int n) {
        try {
            if (b != -43) {
                method195(79, -65, null);
            }
            if (~n == 0xFFFFFFFE) {
                this.anInt114 = class98_Sub22.readShort((byte)127);
            }
            else if (~n == 0xFFFFFFFD) {
                this.anInt115 = class98_Sub22.method1186(b ^ 0x56);
            }
            else if (n == 3) {
                this.aBoolean116 = true;
            }
            else if (n == 4) {
                this.anInt114 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.E(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static final void method194() {
        while (true) {
            boolean b = true;
            for (int i = 0; i < Class98_Sub46_Sub5.aClass174Array5970.length; ++i) {
                if (!Class98_Sub46_Sub5.aClass174Array5970[i].method2563()) {
                    synchronized (Class98_Sub46_Sub5.aClass174Array5970[i]) {
                        Class98_Sub46_Sub5.aClass174Array5970[i].notify();
                    }
                    b = false;
                }
                else {
                    Class98_Sub10_Sub39.aLongArray5772[i] = Class98_Sub46_Sub5.aClass174Array5970[i].method2562();
                }
            }
            if (b) {
                break;
            }
            try {
                Class246_Sub7.method3131(0, 1L);
            }
            catch (Exception ex) {}
        }
        Class98_Sub46_Sub5.aClass174Array5970[Class98_Sub46_Sub5.aClass174Array5970.length - 1].method2561();
        Class69.method701(1);
        while (true) {
            boolean b2 = true;
            for (int j = 0; j < Class98_Sub46_Sub5.aClass174Array5970.length - 1; ++j) {
                if (!Class98_Sub46_Sub5.aClass174Array5970[j].method2563()) {
                    synchronized (Class98_Sub46_Sub5.aClass174Array5970[j]) {
                        Class98_Sub46_Sub5.aClass174Array5970[j].notify();
                    }
                    b2 = false;
                }
            }
            if (b2) {
                break;
            }
            try {
                Class246_Sub7.method3131(0, 1L);
            }
            catch (Exception ex2) {}
        }
        for (int k = 1; k < Class98_Sub46_Sub5.aClass174Array5970.length - 2; ++k) {
            Class98_Sub46_Sub5.aClass174Array5970[k].method2561();
        }
        Class69.method701(2);
        while (!Class98_Sub46_Sub5.aClass174Array5970[0].method2563()) {
            synchronized (Class98_Sub46_Sub5.aClass174Array5970[0]) {
                Class98_Sub46_Sub5.aClass174Array5970[0].notify();
            }
            try {
                Class246_Sub7.method3131(0, 1L);
            }
            catch (Exception ex3) {}
        }
        Class98_Sub46_Sub5.aClass174Array5970[0].method2561();
    }
    
    public Class9() {
        this.aBoolean116 = false;
    }
    
    static final void method195(final int n, final int n2, final Class293[] array) {
        try {
            if (n2 != 28219) {
                method194();
            }
            for (int n3 = 0; array.length > n3; ++n3) {
                final Class293 class293 = array[n3];
                if (class293 != null && n == class293.anInt2234 && !client.method111(class293)) {
                    if (~class293.anInt2354 == -1) {
                        method195(class293.anInt2248, 28219, array);
                        if (class293.aClass293Array2339 != null) {
                            method195(class293.anInt2248, 28219, class293.aClass293Array2339);
                        }
                        final Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3990(class293.anInt2248, -1);
                        if (class98_Sub18 != null) {
                            Class350.method3844(class98_Sub18.anInt3945, n2 - 28101);
                        }
                    }
                    if (class293.anInt2354 == 6 && class293.anInt2208 != -1) {
                        final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383);
                        if (method2623 != null) {
                            final Class293 class294 = class293;
                            class294.anInt2312 += Class279.anInt2099;
                            final int anInt2303 = class293.anInt2303;
                            while (~class293.anInt2312 < ~method2623.anIntArray811[class293.anInt2303]) {
                                final Class293 class295 = class293;
                                class295.anInt2312 -= method2623.anIntArray811[class293.anInt2303];
                                final Class293 class296 = class293;
                                ++class296.anInt2303;
                                if (~class293.anInt2303 <= ~method2623.anIntArray818.length) {
                                    final Class293 class297 = class293;
                                    class297.anInt2303 -= method2623.anInt828;
                                    if (class293.anInt2303 < 0 || class293.anInt2303 >= method2623.anIntArray818.length) {
                                        class293.anInt2303 = 0;
                                    }
                                }
                                class293.anInt2287 = 1 + class293.anInt2303;
                                if (~class293.anInt2287 <= ~method2623.anIntArray818.length) {
                                    final Class293 class298 = class293;
                                    class298.anInt2287 -= method2623.anInt828;
                                    if (class293.anInt2287 < 0 || method2623.anIntArray818.length <= class293.anInt2287) {
                                        class293.anInt2287 = -1;
                                    }
                                }
                                Class341.method3812(1, class293);
                            }
                            if (anInt2303 != class293.anInt2303) {
                                Class280.method3327(class293.anInt2303, method2623, (byte)121);
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "an.D(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
}
