// 
// Decompiled by Procyon v0.5.30
// 

final class Class74
{
    private Class377 aClass377_545;
    static byte[] aByteArray546;
    static Class164 aClass164_547;
    private int anInt548;
    private Class215 aClass215_549;
    private int anInt550;
    static boolean[][] aBooleanArrayArray551;
    
    final void method722(final boolean b) {
        try {
            if (b) {
                this.aClass215_549.method2786(16711680);
                this.aClass377_545.method3994(-67);
                this.anInt550 = this.anInt548;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.K(" + b + ')');
        }
    }
    
    private final void method723(final int n, final Interface20 interface20, final Object o, final boolean b) {
        try {
            if (~n < ~this.anInt548) {
                throw new IllegalStateException("s>cs");
            }
            this.method731(interface20, b);
            this.anInt550 -= n;
            while (this.anInt550 < 0) {
                this.method728((Class98_Sub46_Sub20)this.aClass215_549.method2789(-16711936), (byte)(-100));
            }
            final Class98_Sub46_Sub20_Sub2 class98_Sub46_Sub20_Sub2 = new Class98_Sub46_Sub20_Sub2(interface20, o, n);
            this.aClass377_545.method3996(class98_Sub46_Sub20_Sub2, interface20.method69(!b), -1);
            this.aClass215_549.method2785(class98_Sub46_Sub20_Sub2, -51);
            class98_Sub46_Sub20_Sub2.aLong4259 = 0L;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.I(" + n + ',' + ((interface20 != null) ? "{...}" : "null") + ',' + ((o != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method724(final byte b, final int n) {
        try {
            if (Class39_Sub1.aClass277_3590 != null) {
                for (Class98_Sub46_Sub20 class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass215_549.method2792(-1); class98_Sub46_Sub20 != null; class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass215_549.method2787(0)) {
                    if (class98_Sub46_Sub20.method1638(896)) {
                        if (class98_Sub46_Sub20.method1635(121) == null) {
                            class98_Sub46_Sub20.method942(114);
                            class98_Sub46_Sub20.method1524((byte)(-90));
                            this.anInt550 += class98_Sub46_Sub20.anInt6072;
                        }
                    }
                    else {
                        final Class98_Sub46_Sub20 class98_Sub46_Sub21 = class98_Sub46_Sub20;
                        final long aLong4259 = class98_Sub46_Sub21.aLong4259 + 1L;
                        class98_Sub46_Sub21.aLong4259 = aLong4259;
                        if (aLong4259 > n) {
                            final Class98_Sub46_Sub20 method3290 = Class39_Sub1.aClass277_3590.method3290(class98_Sub46_Sub20, (byte)(-33));
                            this.aClass377_545.method3996(method3290, class98_Sub46_Sub20.aLong832, -1);
                            Class101.method1697(class98_Sub46_Sub20, (byte)37, method3290);
                            class98_Sub46_Sub20.method942(82);
                            class98_Sub46_Sub20.method1524((byte)(-90));
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.D(" + b + ',' + n + ')');
        }
    }
    
    final void method725(final int n) {
        try {
            for (Class98_Sub46_Sub20 class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass215_549.method2792(-1); class98_Sub46_Sub20 != null; class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass215_549.method2787(n - 13937)) {
                if (class98_Sub46_Sub20.method1638(n ^ 0x35F1)) {
                    class98_Sub46_Sub20.method942(n - 13861);
                    class98_Sub46_Sub20.method1524((byte)(-90));
                    this.anInt550 += class98_Sub46_Sub20.anInt6072;
                }
            }
            if (n != 13937) {
                method727((byte)(-57));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.B(" + n + ')');
        }
    }
    
    final int method726(final boolean b) {
        try {
            if (!b) {
                Class74.aByteArray546 = null;
            }
            return this.anInt550;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.A(" + b + ')');
        }
    }
    
    public static void method727(final byte b) {
        try {
            Class74.aBooleanArrayArray551 = null;
            Class74.aClass164_547 = null;
            Class74.aByteArray546 = null;
            if (b != 5) {
                Class74.aClass164_547 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.L(" + b + ')');
        }
    }
    
    private final void method728(final Class98_Sub46_Sub20 class98_Sub46_Sub20, final byte b) {
        try {
            if (class98_Sub46_Sub20 != null) {
                class98_Sub46_Sub20.method942(89);
                class98_Sub46_Sub20.method1524((byte)(-90));
                this.anInt550 += class98_Sub46_Sub20.anInt6072;
            }
            if (b != -100) {
                this.method724((byte)(-95), -14);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.J(" + ((class98_Sub46_Sub20 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method729(final Object o, final Interface20 interface20, final boolean b) {
        try {
            this.method723(1, interface20, o, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.E(" + ((o != null) ? "{...}" : "null") + ',' + ((interface20 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final int method730(final int n) {
        try {
            if (n != -19536) {
                return 7;
            }
            return this.anInt548;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.F(" + n + ')');
        }
    }
    
    private final void method731(final Interface20 interface20, final boolean b) {
        try {
            if (b) {
                this.method728(null, (byte)(-125));
            }
            for (Class98_Sub46_Sub20 class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass377_545.method3990(interface20.method69(!b), -1); class98_Sub46_Sub20 != null; class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass377_545.method3993(122)) {
                if (class98_Sub46_Sub20.anInterface20_6071.method68(22000, interface20)) {
                    this.method728(class98_Sub46_Sub20, (byte)(-100));
                    break;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.C(" + ((interface20 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Object method732(final Interface20 interface20, final int n) {
        try {
            if (n != 0) {
                return null;
            }
            for (Class98_Sub46_Sub20 class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass377_545.method3990(interface20.method69(true), ~n); class98_Sub46_Sub20 != null; class98_Sub46_Sub20 = (Class98_Sub46_Sub20)this.aClass377_545.method3993(-123)) {
                if (class98_Sub46_Sub20.anInterface20_6071.method68(22000, interface20)) {
                    final Object method1635 = class98_Sub46_Sub20.method1635(-97);
                    if (method1635 != null) {
                        if (class98_Sub46_Sub20.method1638(896)) {
                            final Class98_Sub46_Sub20_Sub2 class98_Sub46_Sub20_Sub2 = new Class98_Sub46_Sub20_Sub2(interface20, method1635, class98_Sub46_Sub20.anInt6072);
                            this.aClass377_545.method3996(class98_Sub46_Sub20_Sub2, class98_Sub46_Sub20.aLong832, -1);
                            this.aClass215_549.method2785(class98_Sub46_Sub20_Sub2, n - 68);
                            class98_Sub46_Sub20_Sub2.aLong4259 = 0L;
                            class98_Sub46_Sub20.method942(70);
                            class98_Sub46_Sub20.method1524((byte)(-90));
                        }
                        else {
                            this.aClass215_549.method2785(class98_Sub46_Sub20, -97);
                            class98_Sub46_Sub20.aLong4259 = 0L;
                        }
                        return method1635;
                    }
                    class98_Sub46_Sub20.method942(53);
                    class98_Sub46_Sub20.method1524((byte)(-90));
                    this.anInt550 += class98_Sub46_Sub20.anInt6072;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.G(" + ((interface20 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class74(final int n) {
        this.aClass215_549 = new Class215();
        try {
            this.anInt550 = n;
            this.anInt548 = n;
            int n2;
            for (n2 = 1; ~(n2 + n2) > ~n; n2 += n2) {}
            this.aClass377_545 = new Class377(n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.<init>(" + n + ')');
        }
    }
    
    static final void method733(final long n, final int n2) {
        try {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex2) {}
            if (n2 <= 52) {
                method733(-115L, -9);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ep.H(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class74.aByteArray546 = new byte[] { 31, -117, 8, 0, 0, 0, 0, 0, 0, 0, -5, 127, -29, -1, 109, 6, 103, 6, 14, 54, 54, 118, 54, 86, 14, 118, 118, 118, 78, 78, 14, 46, 30, 17, 94, 30, 110, 110, 30, 73, 33, 97, 126, 17, 89, 41, 121, 57, 89, 41, 25, 25, 5, 21, 61, 117, 5, 37, 29, 101, 25, 25, 13, 115, 77, 29, 3, 67, 19, 19, 19, 121, 117, 75, 91, 11, 35, 27, 61, 99, 19, 35, -112, 33, -116, -100, -100, -100, 60, -36, 60, 18, -68, -68, 18, 70, -118, 50, -118, 70, 36, -125, -1, 7, 24, 68, 56, 24, 24, 25, 24, 89, 24, -107, 24, -104, 4, 25, -103, 5, 25, 89, -108, 24, -2, 31, 97, -112, 103, 96, 96, 100, 101, 4, 3, 6, 40, 96, 100, 98, 102, 97, 101, 99, -25, -32, -28, -30, 6, 42, -40, 42, -64, -64, -60, -56, -52, -52, -60, -62, -52, -54, -54, -62, 2, -108, -83, 5, -54, 51, -80, 8, -78, 10, 41, 26, 58, -78, 9, 7, 38, -78, 43, 21, -118, 24, 53, 78, 92, -56, -95, -20, -76, -15, -96, 104, -48, -59, 15, 42, -58, 73, 69, 77, -100, 92, 98, -30, 18, -110, 82, -86, 106, -22, 26, -102, 90, 38, -90, 102, -26, 22, -106, 86, -50, 46, -82, 110, -18, 30, -98, 94, -63, 33, -95, 97, -31, 17, -111, 81, -55, 41, -87, 105, -23, 25, -103, 89, -59, 37, -91, 101, -27, 21, -107, 85, -51, 45, -83, 109, -19, 29, -99, 93, -109, 38, 79, -103, 58, 109, -6, -116, -103, -77, 22, 45, 94, -78, 116, -39, -14, 21, 43, 87, 109, -38, -68, 101, -21, -74, -19, 59, 118, -18, 58, 116, -8, -56, -47, 99, -57, 79, -100, 60, 117, -23, -14, -107, -85, -41, -82, -33, -72, 121, -21, -31, -93, -57, 79, -98, 62, 123, -2, -30, -27, -85, -113, -97, 62, 127, -7, -6, -19, -5, -113, -97, -65, 64, -2, 98, 100, 96, 102, -124, 1, -84, -2, 18, 4, -6, -117, -119, -123, -123, -103, -123, 29, -28, 47, 70, -90, 114, -112, 2, 65, 22, 86, 69, 67, 54, 33, -57, 64, -10, -60, 66, 97, 37, -93, 70, 14, 17, -89, -119, 11, 55, 30, -28, 84, 54, 14, -6, 32, -102, 84, 116, -111, 75, 76, -59, -28, -95, -22, 71, -112, -41, -64, 62, 35, -50, 99, 77, 100, -7, 12, -18, 49, -124, -65, 110, 49, -16, -79, 48, 2, -29, 15, -24, 78, 6, 6, 123, -122, -97, -1, -70, 52, 22, -16, -49, 127, -79, -88, -21, -1, 77, 0, 38, 27, -28, 10, 110, 2, 0, 0 };
        Class74.aClass164_547 = new Class164(2);
    }
}
