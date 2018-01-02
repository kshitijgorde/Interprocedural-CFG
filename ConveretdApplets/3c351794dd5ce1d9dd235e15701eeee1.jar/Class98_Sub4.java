// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub4 extends Class98
{
    static int anInt3826;
    volatile int anInt3827;
    static int anInt3828;
    volatile String aString3829;
    
    static final void method953(final int n, final int n2, final boolean aBoolean6540) {
        try {
            int method2674 = Class42_Sub1.aClass197_5354.method2674(Class309.aClass309_2610.method3615(Class374.anInt3159, (byte)25), 99);
            int n4;
            if (Class248.aBoolean1896) {
                for (Class98_Sub46_Sub9 class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0)) {
                    int n3;
                    if (~class98_Sub46_Sub9.anInt6001 != 0xFFFFFFFE) {
                        n3 = Class98_Sub10_Sub25.method1079(class98_Sub46_Sub9, 21);
                    }
                    else {
                        n3 = Class222.method2824((byte)81, (Class98_Sub46_Sub8)class98_Sub46_Sub9.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262);
                    }
                    if (~n3 < ~method2674) {
                        method2674 = n3;
                    }
                }
                method2674 += 8;
                n4 = 16 * Class64_Sub12.anInt3672 + 21;
                Class15.anInt172 = Class64_Sub12.anInt3672 * 16 + (Class98_Sub5_Sub3.aBoolean5539 ? 26 : 22);
            }
            else {
                for (Class98_Sub46_Sub8 class98_Sub46_Sub10 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(32); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(119)) {
                    final int method2675 = Class222.method2824((byte)75, class98_Sub46_Sub10);
                    if (~method2674 > ~method2675) {
                        method2674 = method2675;
                    }
                }
                method2674 += 8;
                Class15.anInt172 = Class359.anInt3058 * 16 + (Class98_Sub5_Sub3.aBoolean5539 ? 26 : 22);
                n4 = 21 + Class359.anInt3058 * 16;
            }
            int anInt355 = n2 + -(method2674 / 2);
            if (~Class39_Sub1.anInt3593 > ~(anInt355 - -method2674)) {
                anInt355 = Class39_Sub1.anInt3593 - method2674;
            }
            if (~anInt355 > -1) {
                anInt355 = 0;
            }
            int anInt356 = n;
            if (n4 + anInt356 > Class98_Sub25.anInt4024) {
                anInt356 = -n4 + Class98_Sub25.anInt4024;
            }
            Class38.anInt355 = anInt355;
            if (~anInt356 > -1) {
                anInt356 = 0;
            }
            Class104.anInt897 = anInt356;
            Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 = aBoolean6540;
            Class246_Sub3_Sub4_Sub4.anInt6488 = method2674;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bfa.A(" + n + ',' + n2 + ',' + aBoolean6540 + ')');
        }
    }
    
    Class98_Sub4(final String aString3829) {
        this.anInt3827 = -1;
        try {
            this.aString3829 = aString3829;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bfa.<init>(" + ((aString3829 != null) ? "{...}" : "null") + ')');
        }
    }
}
