// 
// Decompiled by Procyon v0.5.30
// 

final class Class315 implements Interface21
{
    String aString3519;
    int anInt3520;
    int anInt3521;
    Class110 aClass110_3522;
    int anInt3523;
    int anInt3524;
    int anInt3525;
    int anInt3526;
    static String[] aStringArray3527;
    Class63 aClass63_3528;
    static client aClient3529;
    int anInt3530;
    int anInt3531;
    int anInt3532;
    static IncomingOpcode aClass58_3533;
    int anInt3534;
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                Class315.aClient3529 = null;
            }
            return Class308.aClass113_2582;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tga.A(" + n + ')');
        }
    }
    
    public static void method3645(final boolean b) {
        try {
            Class315.aStringArray3527 = null;
            Class315.aClient3529 = null;
            if (!b) {
                Class315.aClass58_3533 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tga.C(" + b + ')');
        }
    }
    
    static final void method3646(final int n) {
        try {
            if (n <= -31) {
                Class246_Sub4_Sub1.aClass79_6170.method806((byte)(-118));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tga.D(" + n + ')');
        }
    }
    
    static final void method3647(final boolean b, final boolean b2, final String aString6507, final boolean b3, final Class88 aClass88_3104) {
        try {
            if (b3) {
                if (b) {
                    if (Class88.aString699.startsWith("win") && aClass88_3104.aBoolean682) {
                        String parameter = null;
                        if (Class76_Sub11.anApplet3799 != null) {
                            parameter = Class76_Sub11.anApplet3799.getParameter("haveie6");
                        }
                        if (parameter == null || !parameter.equals("1")) {
                            final Class143 method1097 = Class98_Sub10_Sub32.method1097(-18871, aString6507, aClass88_3104, 0);
                            Class364.aClass88_3104 = aClass88_3104;
                            Class113.aClass143_953 = method1097;
                            Class246_Sub3_Sub4_Sub2_Sub1.aString6507 = aString6507;
                            return;
                        }
                    }
                    if (Class88.aString699.startsWith("mac")) {
                        String parameter2 = null;
                        if (Class76_Sub11.anApplet3799 != null) {
                            parameter2 = Class76_Sub11.anApplet3799.getParameter("havefirefox");
                        }
                        if (parameter2 != null && parameter2.equals("1") && b2) {
                            Class98_Sub10_Sub32.method1097(-18871, aString6507, aClass88_3104, 1);
                            return;
                        }
                    }
                    Class98_Sub10_Sub32.method1097(-18871, aString6507, aClass88_3104, 2);
                }
                else {
                    Class98_Sub10_Sub32.method1097(-18871, aString6507, aClass88_3104, 3);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tga.B(" + b + ',' + b2 + ',' + ((aString6507 != null) ? "{...}" : "null") + ',' + b3 + ',' + ((aClass88_3104 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class315(final String aString3519, final Class63 aClass63_3528, final Class110 aClass110_3522, final int anInt3521, final int anInt3522, final int anInt3523, final int anInt3524, final int anInt3525, final int anInt3526, final int anInt3527, final int anInt3528, final int anInt3529, final int anInt3530) {
        try {
            this.anInt3530 = anInt3525;
            this.anInt3524 = anInt3523;
            this.anInt3525 = anInt3528;
            this.aClass63_3528 = aClass63_3528;
            this.anInt3523 = anInt3529;
            this.anInt3532 = anInt3526;
            this.anInt3534 = anInt3524;
            this.anInt3521 = anInt3521;
            this.anInt3526 = anInt3527;
            this.anInt3531 = anInt3530;
            this.anInt3520 = anInt3522;
            this.aClass110_3522 = aClass110_3522;
            this.aString3519 = aString3519;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tga.<init>(" + ((aString3519 != null) ? "{...}" : "null") + ',' + ((aClass63_3528 != null) ? "{...}" : "null") + ',' + ((aClass110_3522 != null) ? "{...}" : "null") + ',' + anInt3521 + ',' + anInt3522 + ',' + anInt3523 + ',' + anInt3524 + ',' + anInt3525 + ',' + anInt3526 + ',' + anInt3527 + ',' + anInt3528 + ',' + anInt3529 + ',' + anInt3530 + ')');
        }
    }
    
    static {
        Class315.aStringArray3527 = new String[200];
        Class315.aClass58_3533 = new IncomingOpcode(93, 6);
    }
}
