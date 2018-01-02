// 
// Decompiled by Procyon v0.5.30
// 

final class Class155
{
    short[] aShortArray1234;
    short[] aShortArray1235;
    short aShort1236;
    int[] anIntArray1237;
    byte aByte1238;
    short aShort1239;
    int[] anIntArray1240;
    int[] anIntArray1241;
    byte aByte1242;
    short aShort1243;
    short[] aShortArray1244;
    short aShort1245;
    
    static final Class53_Sub1 method2494(final byte b) {
        try {
            Class239.anInt1843 = 0;
            if (b <= 74) {
                method2494((byte)(-118));
            }
            return Class69_Sub2.method706(200);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kia.A(" + b + ')');
        }
    }
    
    static final void method2495(final byte[] array, final byte b) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            boolean b2 = false;
            if (b == -25) {
                while (true) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-105));
                    if (unsignedByte == 0) {
                        break;
                    }
                    if (~unsignedByte == 0xFFFFFFFE) {
                        if (Class57.anIntArray457 == null) {
                            Class235.anIntArray1764 = new int[4];
                            Class362.anInt3090 = 4;
                            Class57.anIntArray457 = new int[4];
                        }
                        for (int n = 0; Class57.anIntArray457.length > n; ++n) {
                            Class57.anIntArray457[n] = class98_Sub22.readUShort(false);
                            Class235.anIntArray1764[n] = class98_Sub22.readUShort(false);
                        }
                        b2 = true;
                    }
                    else if (unsignedByte == 2) {
                        Class64_Sub10.anInt3666 = class98_Sub22.readShort((byte)127);
                    }
                    else {
                        if (~unsignedByte != 0xFFFFFFFC) {
                            continue;
                        }
                        Class362.anInt3090 = class98_Sub22.readUnsignedByte((byte)(-109));
                        Class57.anIntArray457 = new int[Class362.anInt3090];
                        Class235.anIntArray1764 = new int[Class362.anInt3090];
                    }
                }
                if (!b2) {
                    if (Class57.anIntArray457 == null) {
                        Class362.anInt3090 = 4;
                        Class235.anIntArray1764 = new int[4];
                        Class57.anIntArray457 = new int[4];
                    }
                    for (int i = 0; i < Class57.anIntArray457.length; ++i) {
                        Class57.anIntArray457[i] = 0;
                        Class235.anIntArray1764[i] = 20 * i;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kia.B(" + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    Class155(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14) {
        try {
            this.aByte1242 = (byte)n;
            this.anIntArray1237 = new int[4];
            this.aByte1238 = (byte)n2;
            this.anIntArray1240 = new int[4];
            this.anIntArray1241 = new int[4];
            this.anIntArray1240[2] = n5;
            this.anIntArray1240[3] = n6;
            this.anIntArray1240[1] = n4;
            this.anIntArray1240[0] = n3;
            this.anIntArray1237[3] = n10;
            this.anIntArray1237[0] = n7;
            this.anIntArray1237[2] = n9;
            this.anIntArray1237[1] = n8;
            this.anIntArray1241[2] = n13;
            this.anIntArray1241[1] = n12;
            this.anIntArray1241[3] = n14;
            this.aShort1236 = (short)(n3 >> Class151_Sub8.anInt5015);
            this.anIntArray1241[0] = n11;
            this.aShort1243 = (short)(n5 >> Class151_Sub8.anInt5015);
            this.aShort1239 = (short)(n11 >> Class151_Sub8.anInt5015);
            this.aShort1245 = (short)(n13 >> Class151_Sub8.anInt5015);
            this.aShortArray1234 = new short[4];
            this.aShortArray1235 = new short[4];
            this.aShortArray1244 = new short[4];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kia.<init>(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + n12 + ',' + n13 + ',' + n14 + ')');
        }
    }
}
