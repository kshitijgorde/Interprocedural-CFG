// 
// Decompiled by Procyon v0.5.30
// 

final class Class182
{
    private int[][][] anIntArrayArrayArray1433;
    private int[][][] anIntArrayArrayArray1434;
    private static float[][] aFloatArrayArray1435;
    private static float aFloat1436;
    int[] anIntArray1437;
    static int[][] anIntArrayArray1438;
    static int anInt1439;
    private int[] anIntArray1440;
    
    final void method2612(final Class98_Sub22 class98_Sub22, final Class209 class209) {
        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-126));
        this.anIntArray1437[0] = unsignedByte >> 4;
        this.anIntArray1437[1] = (unsignedByte & 0xF);
        if (unsignedByte != 0) {
            this.anIntArray1440[0] = class98_Sub22.readShort((byte)127);
            this.anIntArray1440[1] = class98_Sub22.readShort((byte)127);
            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-116));
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < this.anIntArray1437[i]; ++j) {
                    this.anIntArrayArrayArray1433[i][0][j] = class98_Sub22.readShort((byte)127);
                    this.anIntArrayArrayArray1434[i][0][j] = class98_Sub22.readShort((byte)127);
                }
            }
            for (int k = 0; k < 2; ++k) {
                for (int l = 0; l < this.anIntArray1437[k]; ++l) {
                    if ((unsignedByte2 & 1 << k * 4 << l) != 0x0) {
                        this.anIntArrayArrayArray1433[k][1][l] = class98_Sub22.readShort((byte)127);
                        this.anIntArrayArrayArray1434[k][1][l] = class98_Sub22.readShort((byte)127);
                    }
                    else {
                        this.anIntArrayArrayArray1433[k][1][l] = this.anIntArrayArrayArray1433[k][0][l];
                        this.anIntArrayArrayArray1434[k][1][l] = this.anIntArrayArrayArray1434[k][0][l];
                    }
                }
            }
            if (unsignedByte2 != 0 || this.anIntArray1440[1] != this.anIntArray1440[0]) {
                class209.method2772(class98_Sub22);
            }
        }
        else {
            this.anIntArray1440[0] = (this.anIntArray1440[1] = 0);
        }
    }
    
    final int method2613(final int n, final float n2) {
        if (n == 0) {
            Class182.aFloat1436 = (float)Math.pow(0.1, (this.anIntArray1440[0] + (this.anIntArray1440[1] - this.anIntArray1440[0]) * n2) * 0.0030517578f / 20.0f);
            Class182.anInt1439 = (int)(Class182.aFloat1436 * 65536.0f);
        }
        if (this.anIntArray1437[n] == 0) {
            return 0;
        }
        final float method2617 = this.method2617(n, 0, n2);
        Class182.aFloatArrayArray1435[n][0] = -2.0f * method2617 * (float)Math.cos(this.method2615(n, 0, n2));
        Class182.aFloatArrayArray1435[n][1] = method2617 * method2617;
        for (int i = 1; i < this.anIntArray1437[n]; ++i) {
            final float method2618 = this.method2617(n, i, n2);
            final float n3 = -2.0f * method2618 * (float)Math.cos(this.method2615(n, i, n2));
            final float n4 = method2618 * method2618;
            Class182.aFloatArrayArray1435[n][i * 2 + 1] = Class182.aFloatArrayArray1435[n][i * 2 - 1] * n4;
            Class182.aFloatArrayArray1435[n][i * 2] = Class182.aFloatArrayArray1435[n][i * 2 - 1] * n3 + Class182.aFloatArrayArray1435[n][i * 2 - 2] * n4;
            for (int j = i * 2 - 1; j >= 2; --j) {
                final float[] array = Class182.aFloatArrayArray1435[n];
                final int n5 = j;
                array[n5] += Class182.aFloatArrayArray1435[n][j - 1] * n3 + Class182.aFloatArrayArray1435[n][j - 2] * n4;
            }
            final float[] array2 = Class182.aFloatArrayArray1435[n];
            final int n6 = 1;
            array2[n6] += Class182.aFloatArrayArray1435[n][0] * n3 + n4;
            final float[] array3 = Class182.aFloatArrayArray1435[n];
            final int n7 = 0;
            array3[n7] += n3;
        }
        if (n == 0) {
            for (int k = 0; k < this.anIntArray1437[0] * 2; ++k) {
                final float[] array4 = Class182.aFloatArrayArray1435[0];
                final int n8 = k;
                array4[n8] *= Class182.aFloat1436;
            }
        }
        for (int l = 0; l < this.anIntArray1437[n] * 2; ++l) {
            Class182.anIntArrayArray1438[n][l] = (int)(Class182.aFloatArrayArray1435[n][l] * 65536.0f);
        }
        return this.anIntArray1437[n] * 2;
    }
    
    public static void method2614() {
        Class182.aFloatArrayArray1435 = null;
        Class182.anIntArrayArray1438 = null;
    }
    
    private final float method2615(final int n, final int n2, final float n3) {
        return method2616((this.anIntArrayArrayArray1433[n][0][n2] + n3 * (this.anIntArrayArrayArray1433[n][1][n2] - this.anIntArrayArrayArray1433[n][0][n2])) * 1.2207031E-4f);
    }
    
    private static final float method2616(final float n) {
        return 32.703197f * (float)Math.pow(2.0, n) * 3.1415927f / 11025.0f;
    }
    
    private final float method2617(final int n, final int n2, final float n3) {
        return 1.0f - (float)Math.pow(10.0, -((this.anIntArrayArrayArray1434[n][0][n2] + n3 * (this.anIntArrayArrayArray1434[n][1][n2] - this.anIntArrayArrayArray1434[n][0][n2])) * 0.0015258789f) / 20.0f);
    }
    
    public Class182() {
        this.anIntArrayArrayArray1433 = new int[2][2][4];
        this.anIntArrayArrayArray1434 = new int[2][2][4];
        this.anIntArray1437 = new int[2];
        this.anIntArray1440 = new int[2];
    }
    
    static {
        Class182.aFloatArrayArray1435 = new float[2][8];
        Class182.anIntArrayArray1438 = new int[2][8];
    }
}
