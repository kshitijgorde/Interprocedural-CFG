// 
// Decompiled by Procyon v0.5.30
// 

final class Class107
{
    boolean aBoolean909;
    int anInt910;
    private short[] aShortArray911;
    static String aString912;
    private short[] aShortArray913;
    private int anInt914;
    private int anInt915;
    private int anInt916;
    private int anInt917;
    private int anInt918;
    private short[] aShortArray919;
    private int anInt920;
    Class304 aClass304_921;
    private short[] aShortArray922;
    byte aByte923;
    private int anInt924;
    int anInt925;
    
    static final int method1720(final int n, final int n2, final int n3, int n4) {
        try {
            n4 &= 0x3;
            if (~n4 == -1) {
                return n;
            }
            if (~n4 == 0xFFFFFFFE) {
                return n3;
            }
            if (n2 != 0) {
                Class107.aString912 = null;
            }
            if (n4 == 2) {
                return -n + 7;
            }
            return -n3 + 7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final Class146 method1721(final ha ha, final int n, final int n2, final int n3, final Class183 class183, final int n4, final int n5) {
        try {
            if (n2 != 21945) {
                method1724(49, -89, -82, -97);
            }
            return this.method1723(n3, n5, 0, n, null, null, 0, (byte)5, class183, false, ha, false, 0, n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.D(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + ((class183 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final Class146 method1722(final ha ha, final Class183 class183, final int n, final int n2, final int n3, final boolean b, final s s, final int n4, final int n5, final s s2, final int n6, final int n7, final byte b2) {
        try {
            if (b2 != 2) {
                return null;
            }
            return this.method1723(n2, n, n4, n7, s2, s, n3, (byte)2, class183, false, ha, b, n6, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.B(" + ((ha != null) ? "{...}" : "null") + ',' + ((class183 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ',' + b2 + ')');
        }
    }
    
    private final Class146 method1723(final int n, final int n2, final int n3, final int n4, final s s, final s s2, final int n5, final byte b, final Class183 class183, final boolean b2, final ha ha, boolean b3, final int n6, final int n7) {
        try {
            int method1777 = n;
            final Class97 class184 = (this.anInt910 == -1 || ~n2 == 0x0) ? null : class183.method2623(this.anInt910, 16383);
            b3 &= (~this.aByte923 != -1);
            if (b2) {
                this.anInt918 = 18;
            }
            if (class184 != null) {
                method1777 |= class184.method932(false, n2, true, n4);
            }
            if (b3) {
                method1777 |= ((~this.aByte923 != 0xFFFFFFFC) ? 2 : 7);
            }
            if (this.anInt916 != 128) {
                method1777 |= 0x2;
            }
            if (~this.anInt920 != 0xFFFFFF7F || ~this.anInt924 != -1) {
                method1777 |= 0x5;
            }
            Class146 method1778;
            synchronized (this.aClass304_921.aClass79_2537) {
                final Class79 aClass79_2537 = this.aClass304_921.aClass79_2537;
                final int n8 = -125;
                final int anInt925 = this.anInt925 | ha.anInt937 << 1821407229;
                this.anInt925 = anInt925;
                method1778 = (Class146)aClass79_2537.method802(n8, anInt925);
            }
            if (method1778 == null || ha.c(method1778.ua(), method1777) != 0) {
                if (method1778 != null) {
                    method1777 = ha.method1777(method1777, method1778.ua());
                }
                int n9 = method1777;
                if (this.aShortArray922 != null) {
                    n9 |= 0x4000;
                }
                if (this.aShortArray913 != null) {
                    n9 |= 0x8000;
                }
                final Class178 method1779 = Class98_Sub6.method981(0, -9252, this.aClass304_921.aClass207_2536, this.anInt914);
                if (method1779 == null) {
                    return null;
                }
                if (method1779.anInt1387 < 13) {
                    method1779.method2592(13746, 2);
                }
                method1778 = ha.method1790(method1779, n9, this.aClass304_921.anInt2539, 64 + this.anInt915, 850 + this.anInt917);
                if (this.aShortArray922 != null) {
                    for (int i = 0; i < this.aShortArray922.length; ++i) {
                        method1778.ia(this.aShortArray922[i], this.aShortArray919[i]);
                    }
                }
                if (this.aShortArray913 != null) {
                    for (int n10 = 0; this.aShortArray913.length > n10; ++n10) {
                        method1778.aa(this.aShortArray913[n10], this.aShortArray911[n10]);
                    }
                }
                method1778.s(method1777);
                synchronized (this.aClass304_921.aClass79_2537) {
                    final Class79 aClass79_2538 = this.aClass304_921.aClass79_2537;
                    final int anInt926 = this.anInt925 | ha.anInt937 << 378441757;
                    this.anInt925 = anInt926;
                    aClass79_2538.method805(anInt926, method1778, (byte)(-80));
                }
            }
            final Class146 class185 = (class184 != null) ? class184.method930(b, 0, n2, method1777, n4, (byte)86, method1778, n7) : method1778.method2341(b, method1777, true);
            if (~this.anInt920 != 0xFFFFFF7F || this.anInt916 != 128) {
                class185.O(this.anInt920, this.anInt916, this.anInt920);
            }
            if (this.anInt924 != 0) {
                if (~this.anInt924 == 0xFFFFFFA5) {
                    class185.a(4096);
                }
                if (this.anInt924 == 180) {
                    class185.a(8192);
                }
                if (this.anInt924 == 270) {
                    class185.a(12288);
                }
            }
            if (b3) {
                class185.p(this.aByte923, this.anInt918, s, s2, n5, n3, n6);
            }
            class185.s(n);
            return class185;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n5 + ',' + b + ',' + ((class183 != null) ? "{...}" : "null") + ',' + b2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + b3 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final int method1724(final int n, int n2, final int n3, final int n4) {
        try {
            n2 &= 0x3;
            if (~n2 == -1) {
                return n3;
            }
            if (n != 7) {
                return -123;
            }
            if (n2 == 1) {
                return 4095 - n4;
            }
            if (~n2 == 0xFFFFFFFD) {
                return -n3 + 4095;
            }
            return n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method1725(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n != 0) {
                this.method1722(null, null, -47, 65, -101, false, null, 26, 120, null, -16, -44, (byte)(-122));
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-114));
                if (unsignedByte == 0) {
                    break;
                }
                this.method1727(n - 120, class98_Sub22, unsignedByte);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1726(final boolean b) {
        try {
            if (!b) {
                Class107.aString912 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.E(" + b + ')');
        }
    }
    
    private final void method1727(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n2 == 1) {
                this.anInt914 = class98_Sub22.readShort((byte)127);
            }
            else if (n2 == 2) {
                this.anInt910 = class98_Sub22.readShort((byte)127);
            }
            else if (n2 == 4) {
                this.anInt920 = class98_Sub22.readShort((byte)127);
            }
            else if (n2 != 5) {
                if (~n2 == 0xFFFFFFF9) {
                    this.anInt924 = class98_Sub22.readShort((byte)127);
                }
                else if (n2 == 7) {
                    this.anInt915 = class98_Sub22.readUnsignedByte((byte)(-120));
                }
                else if (~n2 == 0xFFFFFFF7) {
                    this.anInt917 = class98_Sub22.readUnsignedByte((byte)62);
                }
                else if (n2 == 9) {
                    this.anInt918 = 8224;
                    this.aByte923 = 3;
                }
                else if (~n2 == 0xFFFFFFF5) {
                    this.aBoolean909 = true;
                }
                else if (n2 != 11) {
                    if (~n2 == 0xFFFFFFF3) {
                        this.aByte923 = 4;
                    }
                    else if (n2 != 13) {
                        if (~n2 == 0xFFFFFFF1) {
                            this.aByte923 = 2;
                            this.anInt918 = 256 * class98_Sub22.readUnsignedByte((byte)91);
                        }
                        else if (~n2 == 0xFFFFFFF0) {
                            this.aByte923 = 3;
                            this.anInt918 = class98_Sub22.readShort((byte)127);
                        }
                        else if (n2 == 16) {
                            this.aByte923 = 3;
                            this.anInt918 = class98_Sub22.readInt(-2);
                        }
                        else if (n2 != 40) {
                            if (n2 == 41) {
                                final int i = class98_Sub22.readUnsignedByte((byte)95);
                                this.aShortArray913 = new short[i];
                                this.aShortArray911 = new short[i];
                                for (int n3 = 0; i > n3; ++n3) {
                                    this.aShortArray913[n3] = (short)class98_Sub22.readShort((byte)127);
                                    this.aShortArray911[n3] = (short)class98_Sub22.readShort((byte)127);
                                }
                            }
                        }
                        else {
                            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)75);
                            this.aShortArray919 = new short[unsignedByte];
                            this.aShortArray922 = new short[unsignedByte];
                            for (int j = 0; j < unsignedByte; ++j) {
                                this.aShortArray922[j] = (short)class98_Sub22.readShort((byte)127);
                                this.aShortArray919[j] = (short)class98_Sub22.readShort((byte)127);
                            }
                        }
                    }
                    else {
                        this.aByte923 = 5;
                    }
                }
                else {
                    this.aByte923 = 1;
                }
            }
            else {
                this.anInt916 = class98_Sub22.readShort((byte)127);
            }
            if (n >= -49) {
                this.anInt920 = 7;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.H(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final Class146 method1728(final int n, final Class183 class183, final int n2, final int n3, final byte b, final int n4, final ha ha) {
        try {
            return this.method1723(n2, n, 0, n4, null, null, 0, (byte)2, class183, false, ha, false, 0, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gp.I(" + n + ',' + ((class183 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class107() {
        this.anInt910 = -1;
        this.aBoolean909 = false;
        this.anInt917 = 0;
        this.anInt916 = 128;
        this.anInt918 = -1;
        this.anInt924 = 0;
        this.aByte923 = 0;
        this.anInt915 = 0;
        this.anInt920 = 128;
    }
}
