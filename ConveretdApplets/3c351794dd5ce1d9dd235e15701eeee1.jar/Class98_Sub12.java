import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub12 extends Class98
{
    int anInt3871;
    static int anInt3872;
    int anInt3873;
    int anInt3874;
    int anInt3875;
    int anInt3876;
    static IncomingOpcode aClass58_3877;
    boolean aBoolean3878;
    static int anInt3879;
    
    static final int method1128(final Class65 class65, final int n) {
        try {
            if (Class300.aClass65_2499 == class65) {
                return 5890;
            }
            if (Class98_Sub43_Sub3.aClass65_5926 == class65) {
                return 34167;
            }
            if (IncomingOpcode.aClass65_459 == class65) {
                return 34168;
            }
            if (class65 == Class64_Sub16.aClass65_3681) {
                return 34166;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "er.A(" + ((class65 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method1129(final boolean b) {
        try {
            if (b) {
                method1129(false);
            }
            Class98_Sub12.aClass58_3877 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "er.B(" + b + ')');
        }
    }
    
    static final void method1130(final int n) {
        try {
            if (Class177.anInt1376 != 9) {
                if (Class177.anInt1376 == 5 || Class177.anInt1376 == 6) {
                    Class61.method538(3, false);
                }
                else if (Class177.anInt1376 == 12) {
                    Class61.method538(3, false);
                }
            }
            else {
                Class61.method538(5, false);
            }
            if (n != 27089) {
                method1130(-66);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "er.D(" + n + ')');
        }
    }
    
    static final boolean method1131(final int n, final int anInt3011, final String aString3016) {
        try {
            if (Class98_Sub43_Sub2.aClass88_5907.aBoolean682) {
                Class98_Sub46_Sub10.aClass354_6011 = new Class354();
                Class98_Sub46_Sub10.aClass354_6011.aString3016 = aString3016;
                Class98_Sub46_Sub10.aClass354_6011.anInt3011 = anInt3011;
                if (Class43.aClass196_375 != Class64_Sub29.aClass196_3720) {
                    Class98_Sub46_Sub10.aClass354_6011.anInt3012 = Class98_Sub46_Sub10.aClass354_6011.anInt3011 + 50000;
                    Class98_Sub46_Sub10.aClass354_6011.anInt3015 = Class98_Sub46_Sub10.aClass354_6011.anInt3011 + 40000;
                }
                if (Class98_Sub28_Sub1.aClass53_Sub1Array5805.length > anInt3011 && Class98_Sub28_Sub1.aClass53_Sub1Array5805[anInt3011] != null) {
                    Class98_Sub46.anInt4260 = Class98_Sub28_Sub1.aClass53_Sub1Array5805[anInt3011].anInt427;
                }
                return true;
            }
            String string = "";
            if (Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                string = ":" + (anInt3011 + 7000);
            }
            String string2 = "";
            if (Class89.aString716 != null) {
                string2 = "/p=" + Class89.aString716;
            }
            final String string3 = "http://" + aString3016 + string + "/l=" + Class374.anInt3159 + "/a=" + Class98_Sub10_Sub15.anInt5619 + string2 + "/j" + (Class76_Sub7.aBoolean3761 ? "1" : "0") + ",o" + (Class133.aBoolean3456 ? "1" : "0") + ",a2";
            if (n != -8804) {
                method1129(false);
            }
            try {
                Class315.aClient3529.getAppletContext().showDocument(new URL(string3), "_self");
            }
            catch (Exception ex2) {
                return false;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "er.C(" + n + ',' + anInt3011 + ',' + ((aString3016 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class98_Sub12(final int anInt3876, final int anInt3877, final int anInt3878, final int anInt3879, final int anInt3880, final boolean aBoolean3878) {
        try {
            this.aBoolean3878 = aBoolean3878;
            this.anInt3876 = anInt3876;
            this.anInt3875 = anInt3879;
            this.anInt3873 = anInt3878;
            this.anInt3871 = anInt3880;
            this.anInt3874 = anInt3877;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "er.<init>(" + anInt3876 + ',' + anInt3877 + ',' + anInt3878 + ',' + anInt3879 + ',' + anInt3880 + ',' + aBoolean3878 + ')');
        }
    }
    
    static {
        Class98_Sub12.aClass58_3877 = new IncomingOpcode(66, 1);
    }
}
