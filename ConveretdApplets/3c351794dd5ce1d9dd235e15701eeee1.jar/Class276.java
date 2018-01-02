// 
// Decompiled by Procyon v0.5.30
// 

final class Class276
{
    static final void method3284(final byte[] array, final int n) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            if (n != -65536) {
                method3285(null, null, (byte)(-37));
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)70);
                if (~unsignedByte == -1) {
                    break;
                }
                if (unsignedByte == 1) {
                    final int[] array2 = Class50.anIntArray417 = new int[6];
                    array2[0] = class98_Sub22.readShort((byte)127);
                    array2[1] = class98_Sub22.readShort((byte)127);
                    array2[2] = class98_Sub22.readShort((byte)127);
                    array2[3] = class98_Sub22.readShort((byte)127);
                    array2[4] = class98_Sub22.readShort((byte)127);
                    array2[5] = class98_Sub22.readShort((byte)127);
                }
                else if (~unsignedByte == 0xFFFFFFFB) {
                    final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)104);
                    Class272.anIntArray2036 = new int[unsignedByte2];
                    for (int i = 0; i < unsignedByte2; ++i) {
                        Class272.anIntArray2036[i] = class98_Sub22.readShort((byte)127);
                        if (~Class272.anIntArray2036[i] == 0xFFFF0000) {
                            Class272.anIntArray2036[i] = -1;
                        }
                    }
                }
                else {
                    if (unsignedByte != 5) {
                        continue;
                    }
                    final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)65);
                    Class35.anIntArray333 = new int[unsignedByte3];
                    for (int j = 0; j < unsignedByte3; ++j) {
                        Class35.anIntArray333[j] = class98_Sub22.readShort((byte)127);
                        if (Class35.anIntArray333[j] == 65535) {
                            Class35.anIntArray333[j] = -1;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rda.D(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3285(final String s, final String s2, final byte b) {
        try {
            if (b != -68) {
                method3285(null, null, (byte)102);
            }
            Class146_Sub2.anInt4855 = -1;
            Class98_Sub46_Sub20_Sub2.anInt6317 = 1;
            Class342.method3814(false, s2, 72, s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rda.C(" + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final boolean method3286(final byte b, final int n, final int n2) {
        try {
            if (b <= 19) {
                method3287(-88, -7, -49, -121, -81, 87, 14, null, -2, 46, null, 113, -42);
            }
            return Class98_Sub10_Sub9.method1033(n, n2, 16) & Class140.method2287(n, n2, 2048);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rda.A(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method3287(int n, final int n2, final int n3, final int n4, final int n5, int n6, final int n7, final ha ha, final int n8, final int n9, final byte[][][] array, final int n10, final int n11) {
        try {
            if (n6 != 0 && ~n3 != -1) {
                if (n6 == 9) {
                    n = (n + 1 & 0x3);
                    n6 = 1;
                }
                if (n11 == n6) {
                    n = (0x3 & 3 + n);
                    n6 = 1;
                }
                if (n6 == 11) {
                    n = (3 + n & 0x3);
                    n6 = 8;
                }
                ha.Q(n4, n8, n9, n5, n2, n7, array[-1 + n6][n], n3, n10);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rda.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ',' + ((array != null) ? "{...}" : "null") + ',' + n10 + ',' + n11 + ')');
        }
    }
}
