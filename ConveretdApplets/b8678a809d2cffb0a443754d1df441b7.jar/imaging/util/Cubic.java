// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

public class Cubic
{
    public static final float[][] BEZIER;
    public static final float[][] BSPLINE;
    public static final float[][] CATMULL_ROM;
    public static final float[][] HERMITE;
    float a;
    float b;
    float c;
    float d;
    float[][] C;
    float[][] T;
    float[] C3;
    float[] C2;
    float[] C1;
    float[] C0;
    
    static {
        BEZIER = new float[][] { { -1.0f, 3.0f, -3.0f, 1.0f }, { 3.0f, -6.0f, 3.0f, 0.0f }, { -3.0f, 3.0f, 0.0f, 0.0f }, { 1.0f, 0.0f, 0.0f, 0.0f } };
        BSPLINE = new float[][] { { 0.0f, 0.0f, 0.0f, 0.0f }, { 0.0f, -1.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 0.0f, 0.0f } };
        CATMULL_ROM = new float[][] { { -0.5f, 1.5f, -1.5f, 0.5f }, { 1.0f, -2.5f, 2.0f, -0.5f }, { -0.5f, 0.0f, 0.5f, 0.0f }, { 0.0f, 1.0f, 0.0f, 0.0f } };
        HERMITE = new float[][] { { 2.0f, -2.0f, 1.0f, 1.0f }, { -3.0f, 3.0f, -2.0f, -1.0f }, { 0.0f, 0.0f, 1.0f, 0.0f }, { 1.0f, 0.0f, 0.0f, 0.0f } };
    }
    
    Cubic(final float[][] M, final float[] G) {
        this.C = new float[4][4];
        this.T = new float[4][4];
        this.C3 = this.C[0];
        this.C2 = this.C[1];
        this.C1 = this.C[2];
        this.C0 = this.C[3];
        final float d = this.d;
        this.c = d;
        this.b = d;
        this.a = d;
        for (int k = 0; k < 4; ++k) {
            this.a += M[0][k] * G[k];
            this.b += M[1][k] * G[k];
            this.c += M[2][k] * G[k];
            this.d += M[3][k] * G[k];
        }
    }
    
    public float eval(final float t) {
        return t * (t * (t * this.a + this.b) + this.c) + this.d;
    }
    
    Cubic(final float[][] M, final float[][] G) {
        this.C = new float[4][4];
        this.T = new float[4][4];
        this.C3 = this.C[0];
        this.C2 = this.C[1];
        this.C1 = this.C[2];
        this.C0 = this.C[3];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    final float[] array = this.T[i];
                    final int n = j;
                    array[n] += G[i][k] * M[j][k];
                }
            }
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    final float[] array2 = this.C[i];
                    final int n2 = j;
                    array2[n2] += M[i][k] * this.T[k][j];
                }
            }
        }
    }
    
    public float eval(final float u, final float v) {
        return u * (u * (u * (v * (v * (v * this.C3[0] + this.C3[1]) + this.C3[2]) + this.C3[3]) + (v * (v * (v * this.C2[0] + this.C2[1]) + this.C2[2]) + this.C2[3])) + (v * (v * (v * this.C1[0] + this.C1[1]) + this.C1[2]) + this.C1[3])) + (v * (v * (v * this.C0[0] + this.C0[1]) + this.C0[2]) + this.C0[3]);
    }
}
