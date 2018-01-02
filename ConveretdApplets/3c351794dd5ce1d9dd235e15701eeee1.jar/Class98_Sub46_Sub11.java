// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub11 extends Class98_Sub46
{
    static int anInt6024;
    static int anInt6025;
    private int[] anIntArray6026;
    boolean aBoolean6027;
    Class280 aClass280_6028;
    int[] anIntArray6029;
    private int[][] anIntArrayArray6030;
    private String[] aStringArray6031;
    static Class332[] aClass332Array6032;
    
    final int method1574(final byte b) {
        try {
            if (b > -103) {
                this.anIntArrayArray6030 = null;
            }
            if (this.anIntArray6026 == null) {
                return 0;
            }
            return this.anIntArray6026.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.N(" + b + ')');
        }
    }
    
    final void method1575(final byte b) {
        try {
            if (this.anIntArray6029 != null) {
                for (int n = 0; ~n > ~this.anIntArray6029.length; ++n) {
                    this.anIntArray6029[n] = Class41.method366(this.anIntArray6029[n], 32768);
                }
            }
            if (b > -115) {
                this.anIntArrayArray6030 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.D(" + b + ')');
        }
    }
    
    final String method1576(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            final StringBuffer sb = new StringBuffer(80);
            if (this.anIntArray6026 != null) {
                for (int i = 0; i < this.anIntArray6026.length; ++i) {
                    sb.append(this.aStringArray6031[i]);
                    sb.append(this.aClass280_6028.method3326(this.method1580(i, -41), this.anIntArrayArray6030[i], (byte)70, class98_Sub22.method1248(Class98_Sub10_Sub7.method1025(this.anIntArray6026[i], (byte)(-65)).anInt2910, false)));
                }
            }
            if (n != 15281) {
                return null;
            }
            sb.append(this.aStringArray6031[-1 + this.aStringArray6031.length]);
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.G(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1577(final byte b) {
        try {
            if (b != 99) {
                method1581((byte)(-118), null);
            }
            Class98_Sub46_Sub11.aClass332Array6032 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.M(" + b + ')');
        }
    }
    
    private final void method1578(final int n, final int n2, final Class98_Sub22 class98_Sub22) {
        try {
            if (~n == 0xFFFFFFFE) {
                this.aStringArray6031 = Class112.method2142(class98_Sub22.readString((byte)84), '<', false);
            }
            else if (~n != 0xFFFFFFFD) {
                if (~n == 0xFFFFFFFC) {
                    final int i = class98_Sub22.readUnsignedByte((byte)(-103));
                    this.anIntArrayArray6030 = new int[i][];
                    this.anIntArray6026 = new int[i];
                    for (int n3 = 0; i > n3; ++n3) {
                        final int short1 = class98_Sub22.readShort((byte)127);
                        final Class348 method1025 = Class98_Sub10_Sub7.method1025(short1, (byte)(-96));
                        if (method1025 != null) {
                            this.anIntArray6026[n3] = short1;
                            this.anIntArrayArray6030[n3] = new int[method1025.anInt2915];
                            for (int n4 = 0; ~n4 > ~method1025.anInt2915; ++n4) {
                                this.anIntArrayArray6030[n3][n4] = class98_Sub22.readShort((byte)127);
                            }
                        }
                    }
                }
                else if (~n == 0xFFFFFFFB) {
                    this.aBoolean6027 = false;
                }
            }
            else {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)117);
                this.anIntArray6029 = new int[unsignedByte];
                for (int n5 = 0; ~unsignedByte < ~n5; ++n5) {
                    this.anIntArray6029[n5] = class98_Sub22.readShort((byte)127);
                }
            }
            if (n2 != -3) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.P(" + n + ',' + n2 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1579(final Class98_Sub22 class98_Sub22, final int[] array, final int n) {
        try {
            if (n != -3) {
                this.method1584((byte)4, null);
            }
            if (this.anIntArray6026 != null) {
                for (int i = 0; i < this.anIntArray6026.length; ++i) {
                    if (~array.length >= ~i) {
                        break;
                    }
                    final int anInt2913 = this.method1580(i, n ^ 0x47).anInt2913;
                    if (anInt2913 > 0) {
                        class98_Sub22.method1213(31498, array[i], anInt2913);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class348 method1580(final int n, final int n2) {
        try {
            if (this.anIntArray6026 == null || ~n > -1 || ~n < ~this.anIntArray6026.length) {
                return null;
            }
            return Class98_Sub10_Sub7.method1025(this.anIntArray6026[n], (byte)(-109));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final Class98_Sub10 method1581(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            class98_Sub22.readUnsignedByte((byte)(-106));
            if (b >= -41) {
                Class98_Sub46_Sub11.aClass332Array6032 = null;
            }
            final Class98_Sub10 method3630 = Class313.method3630(class98_Sub22.readUnsignedByte((byte)(-108)), 115);
            method3630.anInt3860 = class98_Sub22.readUnsignedByte((byte)61);
            for (int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-116)), n = 0; ~n > ~unsignedByte; ++n) {
                method3630.method991(class98_Sub22.readUnsignedByte((byte)(-104)), class98_Sub22, (byte)(-116));
            }
            method3630.method1001((byte)66);
            return method3630;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.O(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final String method1582(final boolean b) {
        try {
            final StringBuffer sb = new StringBuffer(80);
            if (this.aStringArray6031 == null) {
                return "";
            }
            sb.append(this.aStringArray6031[0]);
            if (b) {
                return null;
            }
            for (int n = 1; ~this.aStringArray6031.length < ~n; ++n) {
                sb.append("...");
                sb.append(this.aStringArray6031[n]);
            }
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.E(" + b + ')');
        }
    }
    
    final int method1583(final int n, final int n2, final int n3) {
        try {
            if (n < 41) {
                return -47;
            }
            if (this.anIntArray6026 == null || n3 < 0 || ~n3 < ~this.anIntArray6026.length) {
                return -1;
            }
            if (this.anIntArrayArray6030[n3] == null || ~n2 > -1 || this.anIntArrayArray6030[n3].length < n2) {
                return -1;
            }
            return this.anIntArrayArray6030[n3][n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method1584(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b >= -81) {
                this.anIntArray6026 = null;
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-126));
                if (~unsignedByte == -1) {
                    break;
                }
                this.method1578(unsignedByte, -3, class98_Sub22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gj.B(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class98_Sub46_Sub11() {
        this.aBoolean6027 = true;
    }
}
