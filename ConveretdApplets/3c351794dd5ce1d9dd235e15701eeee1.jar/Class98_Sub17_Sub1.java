import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub17_Sub1 extends Class98_Sub17
{
    int anInt5774;
    int anInt5775;
    int anInt5776;
    long aLong5777;
    static boolean aBoolean5778;
    int anInt5779;
    static OutgoingOpcode aClass171_5780;
    static OutgoingOpcode aClass171_5781;
    static int anInt5782;
    static Class207 aClass207_5783;
    
    static final void method1157(final int n) {
        try {
            if (n >= -115) {
                method1160(14);
            }
            Class217.method2799(2, 22050, ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub1_4043.method558((byte)123) == 0xFFFFFFFE, (byte)117);
            Class145.aClass268_1173 = Class153.method2484(22050, 0, (byte)(-126), Class98_Sub43_Sub2.aClass88_5907, Class42_Sub3.aCanvas5361);
            Class246_Sub3_Sub5_Sub2.method3098(Class111_Sub1.method2115(-124, null), true, 28643);
            Class27.aClass268_276 = Class153.method2484(2048, 1, (byte)(-126), Class98_Sub43_Sub2.aClass88_5907, Class42_Sub3.aCanvas5361);
            Class81.aClass98_Sub31_Sub3_619 = new Class98_Sub31_Sub3();
            Class27.aClass268_276.method3252(0, Class81.aClass98_Sub31_Sub3_619);
            Class148.aClass314_1195 = new Class314(22050, Class64_Sub15.anInt3678);
            Class233.method2883((byte)111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.C(" + n + ')');
        }
    }
    
    @Override
    final long method1154(final boolean b) {
        try {
            if (!b) {
                return -39L;
            }
            return this.aLong5777;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.A(" + b + ')');
        }
    }
    
    @Override
    final int method1151(final int n) {
        try {
            if (n < 34) {
                return -100;
            }
            return this.anInt5776;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.G(" + n + ')');
        }
    }
    
    @Override
    final int method1156(final int n) {
        try {
            if (n != -5) {
                this.aLong5777 = 50L;
            }
            return this.anInt5779;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.E(" + n + ')');
        }
    }
    
    static final void method1158(final int n) {
        try {
            if (n == -2) {
                final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class219.aClass171_1640, Class331.aClass117_2811);
                method3023.aClass98_Sub22_Sub1_3865.method1194(Class146_Sub2.method2391((byte)112), n ^ 0x24);
                method3023.aClass98_Sub22_Sub1_3865.writeShort(Class39_Sub1.anInt3593, n + 1571862890);
                method3023.aClass98_Sub22_Sub1_3865.writeShort(Class98_Sub25.anInt4024, 1571862888);
                method3023.aClass98_Sub22_Sub1_3865.method1194(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055.method648((byte)120), -70);
                Class98_Sub10_Sub29.sendPacket(false, method3023);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.B(" + n + ')');
        }
    }
    
    public static void method1159(final int n) {
        try {
            Class98_Sub17_Sub1.aClass171_5780 = null;
            Class98_Sub17_Sub1.aClass171_5781 = null;
            Class98_Sub17_Sub1.aClass207_5783 = null;
            if (n != -15367) {
                Class98_Sub17_Sub1.anInt5782 = -111;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.H(" + n + ')');
        }
    }
    
    @Override
    final int method1155(final int n) {
        try {
            return this.anInt5775;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.I(" + n + ')');
        }
    }
    
    @Override
    final int method1152(final int n) {
        try {
            if (n != 2) {
                this.aLong5777 = -28L;
            }
            return this.anInt5774;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.F(" + n + ')');
        }
    }
    
    static final boolean method1160(final int n) {
        try {
            return n <= 38 || ~Class359.anInt3058 < -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gfa.D(" + n + ')');
        }
    }
    
    static {
        Class98_Sub17_Sub1.aBoolean5778 = false;
        Class98_Sub17_Sub1.aClass171_5780 = new OutgoingOpcode(9, -1);
        Class98_Sub17_Sub1.aClass171_5781 = new OutgoingOpcode(44, 3);
        Class98_Sub17_Sub1.anInt5782 = 765;
    }
}
