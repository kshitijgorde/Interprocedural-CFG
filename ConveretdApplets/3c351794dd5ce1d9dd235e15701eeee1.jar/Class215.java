// 
// Decompiled by Procyon v0.5.30
// 

final class Class215
{
    static int anInt1612;
    static float aFloat1613;
    public static int anInt1614;
    Class98_Sub46 aClass98_Sub46_1615;
    private Class98_Sub46 aClass98_Sub46_1616;
    
    final void method2785(final Class98_Sub46 class98_Sub46, final int n) {
        try {
            if (class98_Sub46.aClass98_Sub46_4265 != null) {
                class98_Sub46.method1524((byte)(-90));
            }
            class98_Sub46.aClass98_Sub46_4262 = this.aClass98_Sub46_1615;
            class98_Sub46.aClass98_Sub46_4265 = this.aClass98_Sub46_1615.aClass98_Sub46_4265;
            class98_Sub46.aClass98_Sub46_4265.aClass98_Sub46_4262 = class98_Sub46;
            class98_Sub46.aClass98_Sub46_4262.aClass98_Sub46_4265 = class98_Sub46;
            if (n >= -25) {
                this.method2789(-121);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.A(" + ((class98_Sub46 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method2786(final int n) {
        try {
            if (n != 16711680) {
                this.aClass98_Sub46_1616 = null;
            }
            while (true) {
                final Class98_Sub46 aClass98_Sub46_4262 = this.aClass98_Sub46_1615.aClass98_Sub46_4262;
                if (this.aClass98_Sub46_1615 == aClass98_Sub46_4262) {
                    break;
                }
                aClass98_Sub46_4262.method1524((byte)(-90));
            }
            this.aClass98_Sub46_1616 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.D(" + n + ')');
        }
    }
    
    final Class98_Sub46 method2787(final int n) {
        try {
            if (n != 0) {
                return null;
            }
            final Class98_Sub46 aClass98_Sub46_1616 = this.aClass98_Sub46_1616;
            if (this.aClass98_Sub46_1615 == aClass98_Sub46_1616) {
                return this.aClass98_Sub46_1616 = null;
            }
            this.aClass98_Sub46_1616 = aClass98_Sub46_1616.aClass98_Sub46_4262;
            return aClass98_Sub46_1616;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.F(" + n + ')');
        }
    }
    
    final int method2788(final int n) {
        try {
            int n2 = 0;
            if (n > -108) {
                return 8;
            }
            for (Class98_Sub46 class98_Sub46 = this.aClass98_Sub46_1615.aClass98_Sub46_4262; this.aClass98_Sub46_1615 != class98_Sub46; class98_Sub46 = class98_Sub46.aClass98_Sub46_4262) {
                ++n2;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.H(" + n + ')');
        }
    }
    
    final Class98_Sub46 method2789(final int n) {
        try {
            if (n != -16711936) {
                return null;
            }
            final Class98_Sub46 aClass98_Sub46_4262 = this.aClass98_Sub46_1615.aClass98_Sub46_4262;
            if (this.aClass98_Sub46_1615 == aClass98_Sub46_4262) {
                return null;
            }
            aClass98_Sub46_4262.method1524((byte)(-90));
            return aClass98_Sub46_4262;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.E(" + n + ')');
        }
    }
    
    static final int method2790(final int n, final int n2, final int n3, int n4) {
        try {
            if (n < 124) {
                return 75;
            }
            n4 = ((n2 * (0xFF00FF & n4) & 0xFF00FF00) | (n2 * (n4 & 0xFF00) & 0xFF0000)) >>> 238779912;
            final int n5 = -n2 + 255;
            return (((0xFF0000 & n5 * (n3 & 0xFF00)) | (n5 * (0xFF00FF & n3) & 0xFF00FF00)) >>> -896446872) - -n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method2791(final byte b) {
        try {
            Class65.aClass293Array500 = null;
            if (za_Sub2.aBoolean6079 && ~Class146_Sub2.method2391((byte)106) != 0xFFFFFFFE) {
                Class92.method892(21337, 0, 0, Class98_Sub21.method1176(false), Class177.anInt1376 == 3 || ~Class177.anInt1376 == 0xFFFFFFF8, Class98_Sub10_Sub36.method1110((byte)60));
            }
            int method2642 = 0;
            int method2643 = 0;
            if (za_Sub2.aBoolean6079) {
                method2642 = Class189.method2642((byte)42);
                method2643 = Class335.method3765(false);
            }
            Class246_Sub3_Sub5_Sub2.method3093(method2643 + Class98_Sub25.anInt4024, -1, method2643, Class15.anInt185, method2643, method2642, Class39_Sub1.anInt3593 + method2642, (byte)88, method2642);
            if (Class65.aClass293Array500 != null) {
                Class98_Sub10_Sub24.method1077(-1412584499, true, IncomingOpcode.anInt463, method2642, method2643, Class189.aClass293_1457.anInt2238, Class65.aClass293Array500, Class39_Sub1.anInt3593 + method2642, method2643 + Class98_Sub25.anInt4024, true, Class64_Sub5.anInt3654);
                Class65.aClass293Array500 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.G(" + b + ')');
        }
    }
    
    final Class98_Sub46 method2792(final int n) {
        try {
            final Class98_Sub46 aClass98_Sub46_4262 = this.aClass98_Sub46_1615.aClass98_Sub46_4262;
            if (aClass98_Sub46_4262 == this.aClass98_Sub46_1615) {
                return this.aClass98_Sub46_1616 = null;
            }
            if (n != -1) {
                Class215.anInt1612 = -120;
            }
            this.aClass98_Sub46_1616 = aClass98_Sub46_4262.aClass98_Sub46_4262;
            return aClass98_Sub46_4262;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.C(" + n + ')');
        }
    }
    
    public Class215() {
        this.aClass98_Sub46_1615 = new Class98_Sub46();
        try {
            this.aClass98_Sub46_1615.aClass98_Sub46_4265 = this.aClass98_Sub46_1615;
            this.aClass98_Sub46_1615.aClass98_Sub46_4262 = this.aClass98_Sub46_1615;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ns.<init>()");
        }
    }
    
    static {
        Class215.anInt1612 = 0;
        Class215.anInt1614 = 0;
    }
}
