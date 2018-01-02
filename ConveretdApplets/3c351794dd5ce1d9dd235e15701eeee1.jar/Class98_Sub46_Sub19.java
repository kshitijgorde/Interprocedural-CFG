// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub19 extends Class98_Sub46
{
    static IncomingOpcode aClass58_6057;
    private int[] anIntArray6058;
    private Class98_Sub10 aClass98_Sub10_6059;
    private Class98_Sub10 aClass98_Sub10_6060;
    private Class98_Sub10[] aClass98_Sub10Array6061;
    static Class258 aClass258_6062;
    private int[] anIntArray6063;
    private Class98_Sub10 aClass98_Sub10_6064;
    static int anInt6065;
    static Class98_Sub46_Sub8 aClass98_Sub46_Sub8_6066;
    static Class207 aClass207_6067;
    static Class205 aClass205_6068;
    static int anInt6069;
    
    final boolean method1629(final int n, final Class207 class207, final d d) {
        try {
            if (~Class98_Sub10_Sub26.anInt5683 > -1) {
                for (int n2 = 0; this.anIntArray6063.length > n2; ++n2) {
                    if (!class207.method2742(-117, this.anIntArray6063[n2])) {
                        return false;
                    }
                }
            }
            else {
                for (int i = 0; i < this.anIntArray6063.length; ++i) {
                    if (!class207.method2751(this.anIntArray6063[i], Class98_Sub10_Sub26.anInt5683, -6329)) {
                        return false;
                    }
                }
            }
            for (int n3 = n; ~n3 > ~this.anIntArray6058.length; ++n3) {
                if (!d.method8(43, this.anIntArray6058[n3])) {
                    return false;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.E(" + n + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ')');
        }
    }
    
    final float[] method1630(final d ad356, final Class207 aClass207_1019, final byte b, final int n, final int i, final boolean b2) {
        try {
            Class38.aD356 = ad356;
            Class127.aClass207_1019 = aClass207_1019;
            for (int j = 0; j < this.aClass98_Sub10Array6061.length; ++j) {
                this.aClass98_Sub10Array6061[j].method998(i, n, -256);
            }
            Class64_Sub2.method559(true, n, i);
            final float[] array = new float[4 * n * i];
            int n2 = 0;
            for (int n3 = 0; i > n3; ++n3) {
                int[] method990;
                int[] array3;
                int[] array2;
                if (this.aClass98_Sub10_6064.aBoolean3861) {
                    array2 = (array3 = (method990 = this.aClass98_Sub10_6064.method990(255, n3)));
                }
                else {
                    final int[][] method991 = this.aClass98_Sub10_6064.method997(-94, n3);
                    array2 = method991[2];
                    array3 = method991[1];
                    method990 = method991[0];
                }
                int[] method992;
                if (this.aClass98_Sub10_6059.aBoolean3861) {
                    method992 = this.aClass98_Sub10_6059.method990(255, n3);
                }
                else {
                    method992 = this.aClass98_Sub10_6059.method997(-119, n3)[0];
                }
                int[] method993;
                if (!this.aClass98_Sub10_6060.aBoolean3861) {
                    method993 = this.aClass98_Sub10_6060.method997(-115, n3)[0];
                }
                else {
                    method993 = this.aClass98_Sub10_6060.method990(255, n3);
                }
                if (b2) {
                    n2 = n3 << -2088274622;
                }
                for (int n4 = -1 + n; ~n4 <= -1; --n4) {
                    float n5 = method992[n4] / 4096.0f;
                    if (n5 >= 0.0f) {
                        if (n5 > 1.0f) {
                            n5 = 1.0f;
                        }
                    }
                    else {
                        n5 = 0.0f;
                    }
                    final float n6 = (1.0f + 31.0f * method993[n4] / 4096.0f) / 4096.0f;
                    array[n2++] = n6 * method990[n4];
                    array[n2++] = n6 * array3[n4];
                    array[n2++] = array2[n4] * n6;
                    array[n2++] = n5;
                    if (b2) {
                        n2 += (n << 698649026) - 4;
                    }
                }
            }
            for (int n7 = 0; ~n7 > ~this.aClass98_Sub10Array6061.length; ++n7) {
                this.aClass98_Sub10Array6061[n7].method993(1002);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.F(" + ((ad356 != null) ? "{...}" : "null") + ',' + ((aClass207_1019 != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + i + ',' + b2 + ')');
        }
    }
    
    final int[] method1631(final int n, final boolean b, final d ad356, final double n2, final boolean b2, final Class207 aClass207_1019, final int n3, final byte b3) {
        try {
            Class127.aClass207_1019 = aClass207_1019;
            Class38.aD356 = ad356;
            for (int n4 = 0; ~this.aClass98_Sub10Array6061.length < ~n4; ++n4) {
                this.aClass98_Sub10Array6061[n4].method998(n, n3, -256);
            }
            Class64_Sub22.method640(n2, 0);
            Class64_Sub2.method559(true, n3, n);
            final int[] array = new int[n * n3];
            int n5;
            int n6;
            int n7;
            if (b) {
                n5 = -1;
                n6 = -1;
                n7 = n3 - 1;
            }
            else {
                n6 = 1;
                n7 = 0;
                n5 = n3;
            }
            int n8 = 0;
            for (int n9 = 0; ~n < ~n9; ++n9) {
                if (b2) {
                    n8 = n9;
                }
                int[] array2;
                int[] array3;
                int[] method998;
                if (!this.aClass98_Sub10_6064.aBoolean3861) {
                    final int[][] method997 = this.aClass98_Sub10_6064.method997(-91, n9);
                    array2 = method997[0];
                    array3 = method997[2];
                    method998 = method997[1];
                }
                else {
                    array3 = (array2 = (method998 = this.aClass98_Sub10_6064.method990(255, n9)));
                }
                for (int n10 = n7; ~n5 != ~n10; n10 += n6) {
                    int n11 = array2[n10] >> 1027477380;
                    if (n11 > 255) {
                        n11 = 255;
                    }
                    if (n11 < 0) {
                        n11 = 0;
                    }
                    int n12 = method998[n10] >> -1996970460;
                    if (~n12 < -256) {
                        n12 = 255;
                    }
                    if (n12 < 0) {
                        n12 = 0;
                    }
                    int n13 = array3[n10] >> -262928124;
                    if (~n13 < -256) {
                        n13 = 255;
                    }
                    if (~n13 > -1) {
                        n13 = 0;
                    }
                    int n14 = (Class151_Sub4.anIntArray4985[n11] << 1130962256) - (-(Class151_Sub4.anIntArray4985[n12] << 1388200360) - Class151_Sub4.anIntArray4985[n13]);
                    if (n14 != 0) {
                        n14 |= 0xFF000000;
                    }
                    array[n8++] = n14;
                    if (b2) {
                        n8 += n3 - 1;
                    }
                }
            }
            for (int n15 = 0; this.aClass98_Sub10Array6061.length > n15; ++n15) {
                this.aClass98_Sub10Array6061[n15].method993(1002);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.B(" + n + ',' + b + ',' + ((ad356 != null) ? "{...}" : "null") + ',' + n2 + ',' + b2 + ',' + ((aClass207_1019 != null) ? "{...}" : "null") + ',' + n3 + ',' + b3 + ')');
        }
    }
    
    public static void method1632(final byte b) {
        try {
            if (b != 37) {
                Class98_Sub46_Sub19.anInt6069 = 43;
            }
            Class98_Sub46_Sub19.aClass58_6057 = null;
            Class98_Sub46_Sub19.aClass258_6062 = null;
            Class98_Sub46_Sub19.aClass205_6068 = null;
            Class98_Sub46_Sub19.aClass207_6067 = null;
            Class98_Sub46_Sub19.aClass98_Sub46_Sub8_6066 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.A(" + b + ')');
        }
    }
    
    final int[] method1633(final boolean b, final double n, final int i, final d ad356, final Class207 aClass207_1019, final byte b2, final int n2) {
        try {
            Class127.aClass207_1019 = aClass207_1019;
            Class38.aD356 = ad356;
            for (int n3 = 0; ~n3 > ~this.aClass98_Sub10Array6061.length; ++n3) {
                this.aClass98_Sub10Array6061[n3].method998(i, n2, b2 ^ 0xFFFFFF4F);
            }
            Class64_Sub22.method640(n, b2 - 79);
            Class64_Sub2.method559(true, n2, i);
            final int[] array = new int[n2 * i];
            int n4 = 0;
            for (int n5 = 0; i > n5; ++n5) {
                int[] method998;
                int[] array2;
                int[] array3;
                if (!this.aClass98_Sub10_6064.aBoolean3861) {
                    final int[][] method997 = this.aClass98_Sub10_6064.method997(-106, n5);
                    method998 = method997[0];
                    array2 = method997[1];
                    array3 = method997[2];
                }
                else {
                    array2 = (array3 = (method998 = this.aClass98_Sub10_6064.method990(255, n5)));
                }
                if (b) {
                    n4 = n5;
                }
                int[] method999;
                if (this.aClass98_Sub10_6059.aBoolean3861) {
                    method999 = this.aClass98_Sub10_6059.method990(255, n5);
                }
                else {
                    method999 = this.aClass98_Sub10_6059.method997(-118, n5)[0];
                }
                for (int j = -1 + n2; j >= 0; --j) {
                    int n6 = method998[j] >> 1903900964;
                    if (n6 > 255) {
                        n6 = 255;
                    }
                    if (~n6 > -1) {
                        n6 = 0;
                    }
                    int n7 = array2[j] >> -949533276;
                    if (~n7 < -256) {
                        n7 = 255;
                    }
                    if (~n7 > -1) {
                        n7 = 0;
                    }
                    int n8 = array3[j] >> -1335175580;
                    if (~n8 < -256) {
                        n8 = 255;
                    }
                    final int n9 = Class151_Sub4.anIntArray4985[n7];
                    if (~n8 > -1) {
                        n8 = 0;
                    }
                    final int n10 = Class151_Sub4.anIntArray4985[n6];
                    final int n11 = Class151_Sub4.anIntArray4985[n8];
                    int n12;
                    if (n10 == 0 && ~n9 == -1 && ~n11 == -1) {
                        n12 = 0;
                    }
                    else {
                        n12 = method999[j] >> 1894756100;
                        if (n12 > 255) {
                            n12 = 255;
                        }
                        if (~n12 > -1) {
                            n12 = 0;
                        }
                    }
                    array[n4++] = (n9 << -1606414968) + (n10 << 869398736) + ((n12 << -1732549160) - -n11);
                    if (b) {
                        n4 += n2 - 1;
                    }
                }
            }
            if (b2 != 79) {
                Class98_Sub46_Sub19.aClass258_6062 = null;
            }
            for (int k = 0; k < this.aClass98_Sub10Array6061.length; ++k) {
                this.aClass98_Sub10Array6061[k].method993(1002);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.D(" + b + ',' + n + ',' + i + ',' + ((ad356 != null) ? "{...}" : "null") + ',' + ((aClass207_1019 != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ')');
        }
    }
    
    static final Class367 method1634(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n < 79) {
                Class98_Sub46_Sub19.aClass258_6062 = null;
            }
            return new Class367(class98_Sub22.readShort((byte)127));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class98_Sub46_Sub19() {
        try {
            this.anIntArray6063 = new int[0];
            this.anIntArray6058 = new int[0];
            this.aClass98_Sub10_6060 = new Class98_Sub10_Sub13(0);
            this.aClass98_Sub10_6060.anInt3860 = 1;
            this.aClass98_Sub10_6064 = new Class98_Sub10_Sub13();
            this.aClass98_Sub10_6064.anInt3860 = 1;
            this.aClass98_Sub10_6059 = new Class98_Sub10_Sub13();
            this.aClass98_Sub10Array6061 = new Class98_Sub10[] { this.aClass98_Sub10_6064, this.aClass98_Sub10_6059, this.aClass98_Sub10_6060 };
            this.aClass98_Sub10_6059.anInt3860 = 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.<init>()");
        }
    }
    
    Class98_Sub46_Sub19(final Class98_Sub22 class98_Sub22) {
        try {
            final int i = class98_Sub22.readUnsignedByte((byte)(-113));
            int n = 0;
            int n2 = 0;
            this.aClass98_Sub10Array6061 = new Class98_Sub10[i];
            final int[][] array = new int[i][];
            for (int j = 0; j < i; ++j) {
                final Class98_Sub10 method1581 = Class98_Sub46_Sub11.method1581((byte)(-71), class98_Sub22);
                if (method1581.method996((byte)(-127)) >= 0) {
                    ++n;
                }
                if (method1581.method992(16) >= 0) {
                    ++n2;
                }
                final int length = method1581.aClass98_Sub10Array3857.length;
                array[j] = new int[length];
                for (int k = 0; k < length; ++k) {
                    array[j][k] = class98_Sub22.readUnsignedByte((byte)116);
                }
                this.aClass98_Sub10Array6061[j] = method1581;
            }
            this.anIntArray6063 = new int[n];
            int n3 = 0;
            this.anIntArray6058 = new int[n2];
            int n4 = 0;
            for (int n5 = 0; i > n5; ++n5) {
                final Class98_Sub10 class98_Sub23 = this.aClass98_Sub10Array6061[n5];
                for (int l = class98_Sub23.aClass98_Sub10Array3857.length, n6 = 0; l > n6; ++n6) {
                    class98_Sub23.aClass98_Sub10Array3857[n6] = this.aClass98_Sub10Array6061[array[n5][n6]];
                }
                final int method1582 = class98_Sub23.method996((byte)(-124));
                final int method1583 = class98_Sub23.method992(62);
                if (~method1582 < -1) {
                    this.anIntArray6063[n3++] = method1582;
                }
                if (~method1583 < -1) {
                    this.anIntArray6058[n4++] = method1583;
                }
                array[n5] = null;
            }
            this.aClass98_Sub10_6064 = this.aClass98_Sub10Array6061[class98_Sub22.readUnsignedByte((byte)(-123))];
            this.aClass98_Sub10_6059 = this.aClass98_Sub10Array6061[class98_Sub22.readUnsignedByte((byte)(-109))];
            this.aClass98_Sub10_6060 = this.aClass98_Sub10Array6061[class98_Sub22.readUnsignedByte((byte)(-124))];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mp.<init>(" + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub19.aClass58_6057 = new IncomingOpcode(91, 10);
        Class98_Sub46_Sub19.aClass258_6062 = new Class258();
        Class98_Sub46_Sub19.anInt6065 = 1;
    }
}
