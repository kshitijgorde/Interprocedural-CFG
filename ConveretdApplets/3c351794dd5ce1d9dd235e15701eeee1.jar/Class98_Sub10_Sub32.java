import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub32 extends Class98_Sub10
{
    static int anInt5718;
    private int anInt5719;
    static int anInt5720;
    private int anInt5721;
    private int anInt5722;
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                this.anInt5719 = 57;
            }
            if (super.aClass16_3863.aBoolean198) {
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    final int n4 = Class64_Sub1.anIntArray3640[n3];
                    final int n5 = Class352.anIntArray3001[n2];
                    int n6 = this.anInt5721 * n4 >> 160759052;
                    final int n7 = n5 * this.anInt5719 >> 274375116;
                    final int n8 = n4 % (4096 / this.anInt5721) * this.anInt5721;
                    if (n5 % (4096 / this.anInt5719) * this.anInt5719 < this.anInt5722) {
                        for (n6 -= n7; ~n6 > -1; n6 += 4) {}
                        while (~n6 < -4) {
                            n6 -= 4;
                        }
                        if (~n6 != 0xFFFFFFFE) {
                            method237[n3] = 0;
                            continue;
                        }
                        if (~this.anInt5722 < ~n8) {
                            method237[n3] = 0;
                            continue;
                        }
                    }
                    if (~n8 > ~this.anInt5722) {
                        int i;
                        for (i = n6 - n7; ~i > -1; i += 4) {}
                        while (i > 3) {
                            i -= 4;
                        }
                        if (~i < -1) {
                            method237[n3] = 0;
                            continue;
                        }
                    }
                    method237[n3] = 4096;
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sd.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1096(final int n, final int n2) {
        try {
            if (n2 != 127) {
                method1098((byte)59);
            }
            return n & 0x7F;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sd.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final Class143 method1097(final int n, final String s, final Class88 class88, final int n2) {
        try {
            if (~n2 == -1) {
                return class88.method859(-14, s);
            }
            if (n2 == 1) {
                try {
                    Class203.method2705(26635, Class76_Sub11.anApplet3799, "openjs", new Object[] { new URL(Class76_Sub11.anApplet3799.getCodeBase(), s).toString() });
                    final Class143 class89 = new Class143();
                    class89.anInt1163 = 1;
                    return class89;
                }
                catch (Throwable t) {
                    final Class143 class90 = new Class143();
                    class90.anInt1163 = 2;
                    return class90;
                }
            }
            if (~n2 == 0xFFFFFFFD) {
                try {
                    Class76_Sub11.anApplet3799.getAppletContext().showDocument(new URL(Class76_Sub11.anApplet3799.getCodeBase(), s), "_blank");
                    final Class143 class91 = new Class143();
                    class91.anInt1163 = 1;
                    return class91;
                }
                catch (Exception ex2) {
                    final Class143 class92 = new Class143();
                    class92.anInt1163 = 2;
                    return class92;
                }
            }
            if (n != -18871) {
                method1096(103, -97);
            }
            if (n2 == 3) {
                try {
                    Class203.method2704("loggedout", Class76_Sub11.anApplet3799, -26978);
                }
                catch (Throwable t2) {}
                try {
                    Class76_Sub11.anApplet3799.getAppletContext().showDocument(new URL(Class76_Sub11.anApplet3799.getCodeBase(), s), "_top");
                    final Class143 class93 = new Class143();
                    class93.anInt1163 = 1;
                    return class93;
                }
                catch (Exception ex3) {
                    final Class143 class94 = new Class143();
                    class94.anInt1163 = 2;
                    return class94;
                }
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sd.E(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + ((class88 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                this.anInt5719 = 113;
            }
            if (n != 0) {
                if (n != 1) {
                    if (~n == 0xFFFFFFFD) {
                        this.anInt5722 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5719 = class98_Sub22.readUnsignedByte((byte)8);
                }
            }
            else {
                this.anInt5721 = class98_Sub22.readUnsignedByte((byte)126);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sd.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1098(final byte b) {
        try {
            if (Class98_Sub10_Sub9.aBoolean5585) {
                final Class293 method3139 = Class246_Sub9.method3139((byte)72, Class187.anInt1450, Class310.anInt2652);
                if (method3139 != null && method3139.anObjectArray2324 != null) {
                    final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                    class98_Sub21.aClass293_3986 = method3139;
                    class98_Sub21.anObjectArray3981 = method3139.anObjectArray2324;
                    Class247.method3144(class98_Sub21);
                }
                Class98_Sub10_Sub9.aBoolean5585 = false;
                Class376.anInt3173 = -1;
                Class21_Sub2.anInt5387 = -1;
                if (method3139 != null) {
                    Class341.method3812(1, method3139);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sd.D(" + b + ')');
        }
    }
    
    public Class98_Sub10_Sub32() {
        super(0, true);
        this.anInt5719 = 1;
        this.anInt5721 = 1;
        this.anInt5722 = 204;
    }
    
    static {
        Class98_Sub10_Sub32.anInt5718 = 0;
        Class98_Sub10_Sub32.anInt5720 = 0;
    }
}
