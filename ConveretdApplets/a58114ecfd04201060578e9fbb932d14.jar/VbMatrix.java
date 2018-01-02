// 
// Decompiled by Procyon v0.5.30
// 

class VbMatrix
{
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int G = 6;
    public static final int H = 7;
    public static final int I = 8;
    public float[] El;
    
    void RotY(final float n) {
        this.El[0] = (float)Math.cos(n);
        this.El[1] = 0.0f;
        this.El[2] = (float)(-Math.sin(n));
        this.El[3] = 0.0f;
        this.El[4] = 1.0f;
        this.El[5] = 0.0f;
        this.El[6] = (float)Math.sin(n);
        this.El[7] = 0.0f;
        this.El[8] = (float)Math.cos(n);
    }
    
    void RotZ(final float n) {
        this.El[0] = (float)Math.cos(n);
        this.El[1] = (float)Math.sin(n);
        this.El[2] = 0.0f;
        this.El[3] = (float)(-Math.sin(n));
        this.El[4] = (float)Math.cos(n);
        this.El[5] = 0.0f;
        this.El[6] = 0.0f;
        this.El[7] = 0.0f;
        this.El[8] = 1.0f;
    }
    
    void RotX(final float n) {
        this.El[0] = 1.0f;
        this.El[1] = 0.0f;
        this.El[2] = 0.0f;
        this.El[3] = 0.0f;
        this.El[4] = (float)Math.cos(n);
        this.El[5] = (float)Math.sin(n);
        this.El[6] = 0.0f;
        this.El[7] = (float)(-Math.sin(n));
        this.El[8] = (float)Math.cos(n);
    }
    
    void Concat(final VbMatrix vbMatrix, final VbMatrix vbMatrix2) {
        this.El[0] = vbMatrix.El[0] * vbMatrix2.El[0] + vbMatrix.El[1] * vbMatrix2.El[3] + vbMatrix.El[2] * vbMatrix2.El[6];
        this.El[1] = vbMatrix.El[0] * vbMatrix2.El[1] + vbMatrix.El[1] * vbMatrix2.El[4] + vbMatrix.El[2] * vbMatrix2.El[7];
        this.El[2] = vbMatrix.El[0] * vbMatrix2.El[2] + vbMatrix.El[1] * vbMatrix2.El[5] + vbMatrix.El[2] * vbMatrix2.El[8];
        this.El[3] = vbMatrix.El[3] * vbMatrix2.El[0] + vbMatrix.El[4] * vbMatrix2.El[3] + vbMatrix.El[5] * vbMatrix2.El[6];
        this.El[4] = vbMatrix.El[3] * vbMatrix2.El[1] + vbMatrix.El[4] * vbMatrix2.El[4] + vbMatrix.El[5] * vbMatrix2.El[7];
        this.El[5] = vbMatrix.El[3] * vbMatrix2.El[2] + vbMatrix.El[4] * vbMatrix2.El[5] + vbMatrix.El[5] * vbMatrix2.El[8];
        this.El[6] = vbMatrix.El[6] * vbMatrix2.El[0] + vbMatrix.El[7] * vbMatrix2.El[3] + vbMatrix.El[8] * vbMatrix2.El[6];
        this.El[7] = vbMatrix.El[6] * vbMatrix2.El[1] + vbMatrix.El[7] * vbMatrix2.El[4] + vbMatrix.El[8] * vbMatrix2.El[7];
        this.El[8] = vbMatrix.El[6] * vbMatrix2.El[2] + vbMatrix.El[7] * vbMatrix2.El[5] + vbMatrix.El[8] * vbMatrix2.El[8];
    }
    
    VbMatrix() {
        this.El = new float[9];
        final float[] el = this.El;
        final int n = 0;
        final float[] el2 = this.El;
        final int n2 = 4;
        final float[] el3 = this.El;
        final int n3 = 8;
        final float n4 = 1.0f;
        el3[n3] = n4;
        el[n] = (el2[n2] = n4);
    }
}
