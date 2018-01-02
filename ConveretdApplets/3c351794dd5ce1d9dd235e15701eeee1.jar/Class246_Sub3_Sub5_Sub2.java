// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub5_Sub2 extends Class246_Sub3_Sub5 implements Interface19
{
    private r aR6266;
    private byte aByte6267;
    static int anInt6268;
    private boolean aBoolean6269;
    private boolean aBoolean6270;
    static Applet_Sub1 anApplet_Sub1_6271;
    static boolean aBoolean6272;
    static Class246_Sub3_Sub4[] aClass246_Sub3_Sub4Array6273;
    private Class228 aClass228_6274;
    private byte aByte6275;
    private short aShort6276;
    private Class146 aClass146_6277;
    private boolean aBoolean6278;
    
    @Override
    public final int method66(final int n) {
        try {
            if (n != 4657) {
                this.method2988(null, 86);
            }
            return this.aByte6267;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.N(" + n + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            final Class146 method3095 = this.method3095(0, 131072, ha);
            if (b < 59) {
                this.aR6266 = null;
            }
            if (method3095 == null) {
                return false;
            }
            final Class111 method3096 = ha.method1793();
            method3096.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            if (!Class239.aBoolean1839) {
                return method3095.method2339(n, n2, method3096, false, 0);
            }
            return method3095.method2333(n, n2, method3096, false, 0, Class16.anInt197);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final void method3093(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final byte b, final int n8) {
        try {
            if (!Class85.method837(n4, 68)) {
                if (~n2 == 0x0) {
                    for (int n9 = 0; ~n9 > -101; ++n9) {
                        aa_Sub3.aBooleanArray3574[n9] = true;
                    }
                }
                else {
                    aa_Sub3.aBooleanArray3574[n2] = true;
                }
            }
            else {
                int anInt3716 = 0;
                int anInt3717 = 0;
                int anInt3718 = 0;
                int anInt3719 = 0;
                int anInt3720 = 0;
                if (za_Sub2.aBoolean6079) {
                    anInt3718 = Class98_Sub19.anInt3956;
                    anInt3720 = Class98_Sub10_Sub5.anInt5554;
                    anInt3719 = Class82.anInt629;
                    anInt3716 = Class64_Sub27.anInt3716;
                    anInt3717 = Applet_Sub1.anInt2;
                    Class98_Sub10_Sub5.anInt5554 = 1;
                }
                if (Class64_Sub13.aClass293ArrayArray3674[n4] == null) {
                    Class98_Sub10_Sub24.method1077(-1, true, n6, n8, n3, n2, Class159.aClass293ArrayArray1252[n4], n7, n, ~n2 > -1, n5);
                }
                else {
                    Class98_Sub10_Sub24.method1077(-1, true, n6, n8, n3, n2, Class64_Sub13.aClass293ArrayArray3674[n4], n7, n, n2 < 0, n5);
                }
                if (za_Sub2.aBoolean6079) {
                    if (n2 >= 0 && Class98_Sub10_Sub5.anInt5554 == 2) {
                        Class93_Sub1_Sub1.method908(Class82.anInt629, Applet_Sub1.anInt2, false, Class64_Sub27.anInt3716, Class98_Sub19.anInt3956);
                    }
                    Class98_Sub10_Sub5.anInt5554 = anInt3720;
                    Applet_Sub1.anInt2 = anInt3717;
                    Class82.anInt629 = anInt3719;
                    Class64_Sub27.anInt3716 = anInt3716;
                    Class98_Sub19.anInt3956 = anInt3718;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + b + ',' + n8 + ')');
        }
    }
    
    @Override
    public final void method62(final ha ha, final int n) {
        try {
            r ar6266;
            if (this.aR6266 != null || !this.aBoolean6269) {
                ar6266 = this.aR6266;
                this.aR6266 = null;
            }
            else {
                final Class298 method3094 = this.method3094(262144, 0, true, ha);
                ar6266 = ((method3094 == null) ? null : method3094.aR2479);
            }
            if (ar6266 != null) {
                Class184.method2626(ar6266, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
            if (n != 24447) {
                method3096(-28, 5, -60);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.G(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final boolean method65(final boolean b) {
        try {
            if (!b) {
                this.aByte6275 = -120;
            }
            return this.aBoolean6269;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.A(" + b + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273 = null;
            }
            return this.aClass146_6277 != null && this.aClass146_6277.F();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.I(" + n + ')');
        }
    }
    
    @Override
    public final void method61(final byte b) {
        try {
            if (this.aClass146_6277 != null) {
                this.aClass146_6277.method2326();
            }
            if (b != -96) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.Q(" + b + ')');
        }
    }
    
    private final Class298 method3094(final int n, final int n2, final boolean b, final ha ha) {
        try {
            final Class352 method3546 = Class130.aClass302_1028.method3546(this.aShort6276 & 0xFFFF, (byte)119);
            if (n2 != 0) {
                return null;
            }
            s s;
            s s2;
            if (!this.aBoolean6278) {
                s = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081];
                if (~super.aByte5081 <= -4) {
                    s2 = null;
                }
                else {
                    s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[1 + super.aByte5081];
                }
            }
            else {
                s = Class81.aSArray618[super.aByte5081];
                s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[0];
            }
            return method3546.method3851(super.anInt5079, false, s2, this.aByte6267, super.anInt5089, b, super.anInt5084, n, null, s, ha, this.aByte6275);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.K(" + n + ',' + n2 + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return this.aClass146_6277 == null || !this.aClass146_6277.r();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.H(" + n + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass146_6277 == null) {
                return null;
            }
            final Class111 method1793 = ha.method1793();
            if (n > -12) {
                this.aClass146_6277 = null;
            }
            method1793.method2100(super.anInt5084 - -super.aShort6165, super.anInt5089, super.aShort6163 + super.anInt5079);
            final Class246_Sub1 method1794 = Class94.method915(1, (byte)(-47), this.aBoolean6270);
            if (Class239.aBoolean1839) {
                this.aClass146_6277.method2329(method1793, method1794.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
                if (!client.aBoolean3553) {
                    return method1794;
                }
            }
            this.aClass146_6277.method2325(method1793, method1794.aClass246_Sub6Array5067[0], 0);
            return method1794;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final int method63(final byte b) {
        try {
            if (b != 20) {
                Class246_Sub3_Sub5_Sub2.anInt6268 = -124;
            }
            return this.aByte6275;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.B(" + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                return 33;
            }
            if (this.aClass146_6277 == null) {
                return 0;
            }
            return this.aClass146_6277.fa();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.J(" + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                Class246_Sub3_Sub5_Sub2.aBoolean6272 = true;
            }
            if (this.aClass228_6274 == null) {
                this.aClass228_6274 = Class48_Sub2_Sub1.method472(super.anInt5089, super.anInt5084, this.method3095(0, 0, ha), super.anInt5079, 4);
            }
            return this.aClass228_6274;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub3_Sub5_Sub2(final ha ha, final Class352 class352, final int n, final int n2, final int anInt5084, final int n3, final int anInt5085, final boolean aBoolean6278, final int n4, final int n5, final int n6, final int n7) {
        super(anInt5084, n3, anInt5085, n, n2, n4, n5);
        try {
            super.anInt5084 = anInt5084;
            super.anInt5079 = anInt5085;
            this.aByte6275 = (byte)n6;
            this.aByte6267 = (byte)n7;
            this.aBoolean6278 = aBoolean6278;
            this.aShort6276 = (short)class352.id;
            this.aBoolean6270 = (class352.anInt2998 != 0 && !aBoolean6278);
            this.aBoolean6269 = (ha.method1771() && class352.aBoolean2935 && !this.aBoolean6278 && Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)120) != 0);
            final Class298 method3094 = this.method3094(2048, 0, this.aBoolean6269, ha);
            if (method3094 != null) {
                this.aClass146_6277 = method3094.aClass146_2477;
                this.aR6266 = method3094.aR2479;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5084 + ',' + n3 + ',' + anInt5085 + ',' + aBoolean6278 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    public final int method64(final int n) {
        try {
            if (n != 30472) {
                Class246_Sub3_Sub5_Sub2.anInt6268 = 40;
            }
            return this.aShort6276 & 0xFFFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.C(" + n + ')');
        }
    }
    
    @Override
    public final void method67(final int n, final ha ha) {
        try {
            if (n != -25163) {
                this.method63((byte)113);
            }
            r ar6266;
            if (this.aR6266 != null || !this.aBoolean6269) {
                ar6266 = this.aR6266;
                this.aR6266 = null;
            }
            else {
                final Class298 method3094 = this.method3094(262144, 0, true, ha);
                ar6266 = ((method3094 != null) ? method3094.aR2479 : null);
            }
            if (ar6266 != null) {
                Class268.method3254(ar6266, super.aByte5081, super.anInt5084, super.anInt5079, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class146 method3095(final int n, final int n2, final ha ha) {
        try {
            if (this.aClass146_6277 != null && ha.c(this.aClass146_6277.ua(), n2) == 0) {
                return this.aClass146_6277;
            }
            final Class298 method3094 = this.method3094(n2, n, false, ha);
            if (method3094 == null) {
                return null;
            }
            return method3094.aClass146_2477;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.P(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method3096(final int n, final int n2, final int n3) {
        try {
            if (n != -27984) {
                method3098(null, false, 3);
            }
            return ~(0x800 & n3) != -1 && ~(0x37 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.L(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
    }
    
    public static void method3097(final int n) {
        try {
            if (n != 0) {
                Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273 = null;
            }
            Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.F(" + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.aByte6275 = 100;
            }
            if (this.aClass146_6277 == null) {
                return 0;
            }
            return this.aClass146_6277.ma();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.O(" + b + ')');
        }
    }
    
    static final void method3098(final Class98_Sub31_Sub2 class98_Sub31_Sub2, final boolean b, final int n) {
        try {
            if (n != 28643) {
                method3093(41, -106, -106, 7, 104, 23, 118, (byte)62, 77);
            }
            Class145.aClass268_1173.method3252(0, class98_Sub31_Sub2);
            if (b) {
                aa_Sub3.method159(Class119_Sub2.aClass207_4726, -25233, class98_Sub31_Sub2, Class145.aClass268_1173, Class196.aClass207_1512, Class76_Sub2.aClass207_3733);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tv.D(" + ((class98_Sub31_Sub2 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 = null;
        Class246_Sub3_Sub5_Sub2.aBoolean6272 = true;
    }
}
