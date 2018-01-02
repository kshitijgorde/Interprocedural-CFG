// 
// Decompiled by Procyon v0.5.30
// 

final class Class39
{
    final int[] anIntArray665;
    private final int[][][] anIntArrayArrayArray666;
    private final int[][][] anIntArrayArrayArray667;
    private final int[] anIntArray668;
    private static final float[][] aFloatArrayArray669;
    static final int[][] anIntArrayArray670;
    private static float aFloat671;
    static int anInt672;
    
    private float method541(final int n, final int n2, final float n3) {
        return 1.0f - (float)Math.pow(10.0, -((this.anIntArrayArrayArray667[n][0][n2] + n3 * (this.anIntArrayArrayArray667[n][1][n2] - this.anIntArrayArrayArray667[n][0][n2])) * 0.001525879f) / 20.0f);
    }
    
    private float method542(final float n) {
        return 32.7032f * (float)Math.pow(2.0, n) * 3.141593f / 11025.0f;
    }
    
    private float method543(final float n, final int n2, final int n3) {
        return this.method542((this.anIntArrayArrayArray666[n3][0][n2] + n * (this.anIntArrayArrayArray666[n3][1][n2] - this.anIntArrayArrayArray666[n3][0][n2])) * 1.220703E-4f);
    }
    
    public int method544(final int n, final float n2) {
        if (n == 0) {
            Class39.aFloat671 = (float)Math.pow(0.1, (this.anIntArray668[0] + (this.anIntArray668[1] - this.anIntArray668[0]) * n2) * 0.003051758f / 20.0f);
            Class39.anInt672 = (int)(Class39.aFloat671 * 65536.0f);
        }
        if (this.anIntArray665[n] == 0) {
            return 0;
        }
        final float method541 = this.method541(n, 0, n2);
        Class39.aFloatArrayArray669[n][0] = -2.0f * method541 * (float)Math.cos(this.method543(n2, 0, n));
        Class39.aFloatArrayArray669[n][1] = method541 * method541;
        for (int i = 1; i < this.anIntArray665[n]; ++i) {
            final float method542 = this.method541(n, i, n2);
            final float n3 = -2.0f * method542 * (float)Math.cos(this.method543(n2, i, n));
            final float n4 = method542 * method542;
            Class39.aFloatArrayArray669[n][i * 2 + 1] = Class39.aFloatArrayArray669[n][i * 2 - 1] * n4;
            Class39.aFloatArrayArray669[n][i * 2] = Class39.aFloatArrayArray669[n][i * 2 - 1] * n3 + Class39.aFloatArrayArray669[n][i * 2 - 2] * n4;
            for (int j = i * 2 - 1; j >= 2; --j) {
                final float[] array = Class39.aFloatArrayArray669[n];
                final int n5 = j;
                array[n5] += Class39.aFloatArrayArray669[n][j - 1] * n3 + Class39.aFloatArrayArray669[n][j - 2] * n4;
            }
            final float[] array2 = Class39.aFloatArrayArray669[n];
            final int n6 = 1;
            array2[n6] += Class39.aFloatArrayArray669[n][0] * n3 + n4;
            final float[] array3 = Class39.aFloatArrayArray669[n];
            final int n7 = 0;
            array3[n7] += n3;
        }
        if (n == 0) {
            for (int k = 0; k < this.anIntArray665[0] * 2; ++k) {
                final float[] array4 = Class39.aFloatArrayArray669[0];
                final int n8 = k;
                array4[n8] *= Class39.aFloat671;
            }
        }
        for (int l = 0; l < this.anIntArray665[n] * 2; ++l) {
            Class39.anIntArrayArray670[n][l] = (int)(Class39.aFloatArrayArray669[n][l] * 65536.0f);
        }
        return this.anIntArray665[n] * 2;
    }
    
    public void method545(final Stream stream, final Class29 class29) {
        final int unsignedByte = stream.readUnsignedByte();
        this.anIntArray665[0] = unsignedByte >> 4;
        this.anIntArray665[1] = (unsignedByte & 0xF);
        if (unsignedByte != 0) {
            this.anIntArray668[0] = stream.readUnsignedWord();
            this.anIntArray668[1] = stream.readUnsignedWord();
            final int unsignedByte2 = stream.readUnsignedByte();
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < this.anIntArray665[i]; ++j) {
                    this.anIntArrayArrayArray666[i][0][j] = stream.readUnsignedWord();
                    this.anIntArrayArrayArray667[i][0][j] = stream.readUnsignedWord();
                }
            }
            for (int k = 0; k < 2; ++k) {
                for (int l = 0; l < this.anIntArray665[k]; ++l) {
                    if ((unsignedByte2 & 1 << k * 4 << l) != 0x0) {
                        this.anIntArrayArrayArray666[k][1][l] = stream.readUnsignedWord();
                        this.anIntArrayArrayArray667[k][1][l] = stream.readUnsignedWord();
                    }
                    else {
                        this.anIntArrayArrayArray666[k][1][l] = this.anIntArrayArrayArray666[k][0][l];
                        this.anIntArrayArrayArray667[k][1][l] = this.anIntArrayArrayArray667[k][0][l];
                    }
                }
            }
            if (unsignedByte2 != 0 || this.anIntArray668[1] != this.anIntArray668[0]) {
                class29.method326(stream);
            }
        }
        else {
            this.anIntArray668[0] = (this.anIntArray668[1] = 0);
        }
    }
    
    public Class39() {
        this.anIntArray665 = new int[2];
        this.anIntArrayArrayArray666 = new int[2][2][4];
        this.anIntArrayArrayArray667 = new int[2][2][4];
        this.anIntArray668 = new int[2];
    }
    
    static {
        aFloatArrayArray669 = new float[2][8];
        anIntArrayArray670 = new int[2][8];
    }
}
