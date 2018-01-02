// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

public final class PMatrix implements PConstants
{
    static final int DEFAULT_STACK_DEPTH = 0;
    public float m00;
    public float m01;
    public float m02;
    public float m03;
    public float m10;
    public float m11;
    public float m12;
    public float m13;
    public float m20;
    public float m21;
    public float m22;
    public float m23;
    public float m30;
    public float m31;
    public float m32;
    public float m33;
    int maxStackDepth;
    int stackPointer;
    float[][] stack;
    
    public final void reset() {
        this.set(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void clearStack() {
        this.stackPointer = 0;
    }
    
    public final boolean push() {
        if (this.stackPointer == this.maxStackDepth) {
            return false;
        }
        this.stack[this.stackPointer][0] = this.m00;
        this.stack[this.stackPointer][1] = this.m01;
        this.stack[this.stackPointer][2] = this.m02;
        this.stack[this.stackPointer][3] = this.m03;
        this.stack[this.stackPointer][4] = this.m10;
        this.stack[this.stackPointer][5] = this.m11;
        this.stack[this.stackPointer][6] = this.m12;
        this.stack[this.stackPointer][7] = this.m13;
        this.stack[this.stackPointer][8] = this.m20;
        this.stack[this.stackPointer][9] = this.m21;
        this.stack[this.stackPointer][10] = this.m22;
        this.stack[this.stackPointer][11] = this.m23;
        this.stack[this.stackPointer][12] = this.m30;
        this.stack[this.stackPointer][13] = this.m31;
        this.stack[this.stackPointer][14] = this.m32;
        this.stack[this.stackPointer][15] = this.m33;
        ++this.stackPointer;
        return true;
    }
    
    public final boolean pop() {
        if (this.stackPointer == 0) {
            return false;
        }
        --this.stackPointer;
        this.m00 = this.stack[this.stackPointer][0];
        this.m01 = this.stack[this.stackPointer][1];
        this.m02 = this.stack[this.stackPointer][2];
        this.m03 = this.stack[this.stackPointer][3];
        this.m10 = this.stack[this.stackPointer][4];
        this.m11 = this.stack[this.stackPointer][5];
        this.m12 = this.stack[this.stackPointer][6];
        this.m13 = this.stack[this.stackPointer][7];
        this.m20 = this.stack[this.stackPointer][8];
        this.m21 = this.stack[this.stackPointer][9];
        this.m22 = this.stack[this.stackPointer][10];
        this.m23 = this.stack[this.stackPointer][11];
        this.m30 = this.stack[this.stackPointer][12];
        this.m31 = this.stack[this.stackPointer][13];
        this.m32 = this.stack[this.stackPointer][14];
        this.m33 = this.stack[this.stackPointer][15];
        return true;
    }
    
    public final void set(final PMatrix pMatrix) {
        this.set(pMatrix.m00, pMatrix.m01, pMatrix.m02, pMatrix.m03, pMatrix.m10, pMatrix.m11, pMatrix.m12, pMatrix.m13, pMatrix.m20, pMatrix.m21, pMatrix.m22, pMatrix.m23, pMatrix.m30, pMatrix.m31, pMatrix.m32, pMatrix.m33);
    }
    
    public final void set(final float m00, final float m2, final float m3, final float m4, final float m5, final float m6, final float m7, final float m8, final float m9, final float m10, final float m11, final float m12, final float m13, final float m14, final float m15, final float m16) {
        this.m00 = m00;
        this.m01 = m2;
        this.m02 = m3;
        this.m03 = m4;
        this.m10 = m5;
        this.m11 = m6;
        this.m12 = m7;
        this.m13 = m8;
        this.m20 = m9;
        this.m21 = m10;
        this.m22 = m11;
        this.m23 = m12;
        this.m30 = m13;
        this.m31 = m14;
        this.m32 = m15;
        this.m33 = m16;
    }
    
    public final void translate(final float n, final float n2) {
        this.translate(n, n2, 0.0f);
    }
    
    public final void invTranslate(final float n, final float n2) {
        this.invTranslate(n, n2, 0.0f);
    }
    
    public final void translate(final float n, final float n2, final float n3) {
        this.m03 += n * this.m00 + n2 * this.m01 + n3 * this.m02;
        this.m13 += n * this.m10 + n2 * this.m11 + n3 * this.m12;
        this.m23 += n * this.m20 + n2 * this.m21 + n3 * this.m22;
        this.m33 += n * this.m30 + n2 * this.m31 + n3 * this.m32;
    }
    
    public final void invTranslate(final float n, final float n2, final float n3) {
        this.preApply(1.0f, 0.0f, 0.0f, -n, 0.0f, 1.0f, 0.0f, -n2, 0.0f, 0.0f, 1.0f, -n3, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void rotateX(final float n) {
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.apply(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invRotateX(final float n) {
        final float cos = this.cos(-n);
        final float sin = this.sin(-n);
        this.preApply(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void rotateY(final float n) {
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.apply(cos, 0.0f, sin, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -sin, 0.0f, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invRotateY(final float n) {
        final float cos = this.cos(-n);
        final float sin = this.sin(-n);
        this.preApply(cos, 0.0f, sin, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -sin, 0.0f, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void rotate(final float n) {
        this.rotateZ(n);
    }
    
    public final void invRotate(final float n) {
        this.invRotateZ(n);
    }
    
    public final void rotateZ(final float n) {
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.apply(cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invRotateZ(final float n) {
        final float cos = this.cos(-n);
        final float sin = this.sin(-n);
        this.preApply(cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void rotate(final float n, final float n2, final float n3, final float n4) {
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        final float n5 = 1.0f - cos;
        this.apply(n5 * n2 * n2 + cos, n5 * n2 * n3 - sin * n4, n5 * n2 * n4 + sin * n3, 0.0f, n5 * n2 * n3 + sin * n4, n5 * n3 * n3 + cos, n5 * n3 * n4 - sin * n2, 0.0f, n5 * n2 * n4 - sin * n3, n5 * n3 * n4 + sin * n2, n5 * n4 * n4 + cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invRotate(final float n, final float n2, final float n3, final float n4) {
        final float cos = this.cos(-n);
        final float sin = this.sin(-n);
        final float n5 = 1.0f - cos;
        this.preApply(n5 * n2 * n2 + cos, n5 * n2 * n3 - sin * n4, n5 * n2 * n4 + sin * n3, 0.0f, n5 * n2 * n3 + sin * n4, n5 * n3 * n3 + cos, n5 * n3 * n4 - sin * n2, 0.0f, n5 * n2 * n4 - sin * n3, n5 * n3 * n4 + sin * n2, n5 * n4 * n4 + cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void scale(final float n) {
        this.apply(n, 0.0f, 0.0f, 0.0f, 0.0f, n, 0.0f, 0.0f, 0.0f, 0.0f, n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invScale(final float n) {
        this.preApply(1.0f / n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f / n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f / n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void scale(final float n, final float n2) {
        this.apply(n, 0.0f, 0.0f, 0.0f, 0.0f, n2, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invScale(final float n, final float n2) {
        this.preApply(1.0f / n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f / n2, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void scale(final float n, final float n2, final float n3) {
        this.apply(n, 0.0f, 0.0f, 0.0f, 0.0f, n2, 0.0f, 0.0f, 0.0f, 0.0f, n3, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void invScale(final float n, final float n2, final float n3) {
        this.preApply(1.0f / n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f / n2, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f / n3, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void transform(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.apply(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public final void preApply(final PMatrix pMatrix) {
        this.preApply(pMatrix.m00, pMatrix.m01, pMatrix.m02, pMatrix.m03, pMatrix.m10, pMatrix.m11, pMatrix.m12, pMatrix.m13, pMatrix.m20, pMatrix.m21, pMatrix.m22, pMatrix.m23, pMatrix.m30, pMatrix.m31, pMatrix.m32, pMatrix.m33);
    }
    
    public final void preApply(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        final float m00 = n * this.m00 + n2 * this.m10 + n3 * this.m20 + n4 * this.m30;
        final float m2 = n * this.m01 + n2 * this.m11 + n3 * this.m21 + n4 * this.m31;
        final float m3 = n * this.m02 + n2 * this.m12 + n3 * this.m22 + n4 * this.m32;
        final float m4 = n * this.m03 + n2 * this.m13 + n3 * this.m23 + n4 * this.m33;
        final float m5 = n5 * this.m00 + n6 * this.m10 + n7 * this.m20 + n8 * this.m30;
        final float m6 = n5 * this.m01 + n6 * this.m11 + n7 * this.m21 + n8 * this.m31;
        final float m7 = n5 * this.m02 + n6 * this.m12 + n7 * this.m22 + n8 * this.m32;
        final float m8 = n5 * this.m03 + n6 * this.m13 + n7 * this.m23 + n8 * this.m33;
        final float m9 = n9 * this.m00 + n10 * this.m10 + n11 * this.m20 + n12 * this.m30;
        final float m10 = n9 * this.m01 + n10 * this.m11 + n11 * this.m21 + n12 * this.m31;
        final float m11 = n9 * this.m02 + n10 * this.m12 + n11 * this.m22 + n12 * this.m32;
        final float m12 = n9 * this.m03 + n10 * this.m13 + n11 * this.m23 + n12 * this.m33;
        final float m13 = n13 * this.m00 + n14 * this.m10 + n15 * this.m20 + n16 * this.m30;
        final float m14 = n13 * this.m01 + n14 * this.m11 + n15 * this.m21 + n16 * this.m31;
        final float m15 = n13 * this.m02 + n14 * this.m12 + n15 * this.m22 + n16 * this.m32;
        final float m16 = n13 * this.m03 + n14 * this.m13 + n15 * this.m23 + n16 * this.m33;
        this.m00 = m00;
        this.m01 = m2;
        this.m02 = m3;
        this.m03 = m4;
        this.m10 = m5;
        this.m11 = m6;
        this.m12 = m7;
        this.m13 = m8;
        this.m20 = m9;
        this.m21 = m10;
        this.m22 = m11;
        this.m23 = m12;
        this.m30 = m13;
        this.m31 = m14;
        this.m32 = m15;
        this.m33 = m16;
    }
    
    public final boolean invApply(final PMatrix pMatrix) {
        final PMatrix invert = new PMatrix(pMatrix).invert();
        if (invert == null) {
            return false;
        }
        this.preApply(invert);
        return true;
    }
    
    public final boolean invApply(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        final PMatrix invert = new PMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16).invert();
        if (invert == null) {
            return false;
        }
        this.preApply(invert);
        return true;
    }
    
    public final void apply(final PMatrix pMatrix) {
        this.apply(pMatrix.m00, pMatrix.m01, pMatrix.m02, pMatrix.m03, pMatrix.m10, pMatrix.m11, pMatrix.m12, pMatrix.m13, pMatrix.m20, pMatrix.m21, pMatrix.m22, pMatrix.m23, pMatrix.m30, pMatrix.m31, pMatrix.m32, pMatrix.m33);
    }
    
    public final void apply(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        final float m00 = this.m00 * n + this.m01 * n5 + this.m02 * n9 + this.m03 * n13;
        final float m2 = this.m00 * n2 + this.m01 * n6 + this.m02 * n10 + this.m03 * n14;
        final float m3 = this.m00 * n3 + this.m01 * n7 + this.m02 * n11 + this.m03 * n15;
        final float m4 = this.m00 * n4 + this.m01 * n8 + this.m02 * n12 + this.m03 * n16;
        final float m5 = this.m10 * n + this.m11 * n5 + this.m12 * n9 + this.m13 * n13;
        final float m6 = this.m10 * n2 + this.m11 * n6 + this.m12 * n10 + this.m13 * n14;
        final float m7 = this.m10 * n3 + this.m11 * n7 + this.m12 * n11 + this.m13 * n15;
        final float m8 = this.m10 * n4 + this.m11 * n8 + this.m12 * n12 + this.m13 * n16;
        final float m9 = this.m20 * n + this.m21 * n5 + this.m22 * n9 + this.m23 * n13;
        final float m10 = this.m20 * n2 + this.m21 * n6 + this.m22 * n10 + this.m23 * n14;
        final float m11 = this.m20 * n3 + this.m21 * n7 + this.m22 * n11 + this.m23 * n15;
        final float m12 = this.m20 * n4 + this.m21 * n8 + this.m22 * n12 + this.m23 * n16;
        final float m13 = this.m30 * n + this.m31 * n5 + this.m32 * n9 + this.m33 * n13;
        final float m14 = this.m30 * n2 + this.m31 * n6 + this.m32 * n10 + this.m33 * n14;
        final float m15 = this.m30 * n3 + this.m31 * n7 + this.m32 * n11 + this.m33 * n15;
        final float m16 = this.m30 * n4 + this.m31 * n8 + this.m32 * n12 + this.m33 * n16;
        this.m00 = m00;
        this.m01 = m2;
        this.m02 = m3;
        this.m03 = m4;
        this.m10 = m5;
        this.m11 = m6;
        this.m12 = m7;
        this.m13 = m8;
        this.m20 = m9;
        this.m21 = m10;
        this.m22 = m11;
        this.m23 = m12;
        this.m30 = m13;
        this.m31 = m14;
        this.m32 = m15;
        this.m33 = m16;
    }
    
    public final void mult3(final float[] array, final float[] array2) {
        final float n = this.m00 * array[0] + this.m01 * array[1] + this.m02 * array[2] + this.m03;
        final float n2 = this.m10 * array[0] + this.m11 * array[1] + this.m12 * array[2] + this.m13;
        final float n3 = this.m20 * array[0] + this.m21 * array[1] + this.m22 * array[2] + this.m23;
        array2[0] = n;
        array2[1] = n2;
        array2[2] = n3;
    }
    
    public final void mult(final float[] array, final float[] array2) {
        final float n = this.m00 * array[0] + this.m01 * array[1] + this.m02 * array[2] + this.m03 * array[3];
        final float n2 = this.m10 * array[0] + this.m11 * array[1] + this.m12 * array[2] + this.m13 * array[3];
        final float n3 = this.m20 * array[0] + this.m21 * array[1] + this.m22 * array[2] + this.m23 * array[3];
        final float n4 = this.m30 * array[0] + this.m31 * array[1] + this.m32 * array[2] + this.m33 * array[3];
        array2[0] = n;
        array2[1] = n2;
        array2[2] = n3;
        array2[3] = n4;
    }
    
    public final float determinant() {
        return this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33) - this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33) + this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33) - this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
    }
    
    private final float determinant3x3(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        return n * (n5 * n9 - n6 * n8) + n2 * (n6 * n7 - n4 * n9) + n3 * (n4 * n8 - n5 * n7);
    }
    
    public final PMatrix transpose() {
        final float m01 = this.m01;
        this.m01 = this.m10;
        this.m10 = m01;
        final float m2 = this.m02;
        this.m02 = this.m20;
        this.m20 = m2;
        final float m3 = this.m03;
        this.m03 = this.m30;
        this.m30 = m3;
        final float m4 = this.m12;
        this.m12 = this.m21;
        this.m21 = m4;
        final float m5 = this.m13;
        this.m13 = this.m31;
        this.m31 = m5;
        final float m6 = this.m23;
        this.m23 = this.m32;
        this.m32 = m6;
        return this;
    }
    
    public final PMatrix invert() {
        final float determinant = this.determinant();
        if (determinant != 0.0f) {
            final float n = 1.0f / determinant;
            final float determinant3x3 = this.determinant3x3(this.m11, this.m12, this.m13, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
            final float n2 = -this.determinant3x3(this.m10, this.m12, this.m13, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33);
            final float determinant3x4 = this.determinant3x3(this.m10, this.m11, this.m13, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33);
            final float n3 = -this.determinant3x3(this.m10, this.m11, this.m12, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32);
            final float n4 = -this.determinant3x3(this.m01, this.m02, this.m03, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
            final float determinant3x5 = this.determinant3x3(this.m00, this.m02, this.m03, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33);
            final float n5 = -this.determinant3x3(this.m00, this.m01, this.m03, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33);
            final float determinant3x6 = this.determinant3x3(this.m00, this.m01, this.m02, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32);
            final float determinant3x7 = this.determinant3x3(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m31, this.m32, this.m33);
            final float n6 = -this.determinant3x3(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m30, this.m32, this.m33);
            final float determinant3x8 = this.determinant3x3(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m30, this.m31, this.m33);
            final float n7 = -this.determinant3x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m30, this.m31, this.m32);
            final float n8 = -this.determinant3x3(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m21, this.m22, this.m23);
            final float determinant3x9 = this.determinant3x3(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m20, this.m22, this.m23);
            final float n9 = -this.determinant3x3(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m20, this.m21, this.m23);
            final float determinant3x10 = this.determinant3x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
            this.m00 = determinant3x3 * n;
            this.m11 = determinant3x5 * n;
            this.m22 = determinant3x8 * n;
            this.m33 = determinant3x10 * n;
            this.m01 = n4 * n;
            this.m10 = n2 * n;
            this.m20 = determinant3x4 * n;
            this.m02 = determinant3x7 * n;
            this.m12 = n6 * n;
            this.m21 = n5 * n;
            this.m03 = n8 * n;
            this.m30 = n3 * n;
            this.m13 = determinant3x9 * n;
            this.m31 = determinant3x6 * n;
            this.m32 = n7 * n;
            this.m23 = n9 * n;
            return this;
        }
        return null;
    }
    
    public final void print() {
        int n = (int)Math.abs(this.max(this.max(this.max(this.max(this.abs(this.m00), this.abs(this.m01)), this.max(this.abs(this.m02), this.abs(this.m03))), this.max(this.max(this.abs(this.m10), this.abs(this.m11)), this.max(this.abs(this.m12), this.abs(this.m13)))), this.max(this.max(this.max(this.abs(this.m20), this.abs(this.m21)), this.max(this.abs(this.m22), this.abs(this.m23))), this.max(this.max(this.abs(this.m30), this.abs(this.m31)), this.max(this.abs(this.m32), this.abs(this.m33))))));
        if (Float.isNaN(n) || Float.isInfinite(n)) {
            n = 1000000;
        }
        int n2 = 1;
        while ((n /= 10) != 0) {
            ++n2;
        }
        System.out.println(PApplet.nfs(this.m00, n2, 4) + ' ' + PApplet.nfs(this.m01, n2, 4) + ' ' + PApplet.nfs(this.m02, n2, 4) + ' ' + PApplet.nfs(this.m03, n2, 4));
        System.out.println(PApplet.nfs(this.m10, n2, 4) + ' ' + PApplet.nfs(this.m11, n2, 4) + ' ' + PApplet.nfs(this.m12, n2, 4) + ' ' + PApplet.nfs(this.m13, n2, 4));
        System.out.println(PApplet.nfs(this.m20, n2, 4) + ' ' + PApplet.nfs(this.m21, n2, 4) + ' ' + PApplet.nfs(this.m22, n2, 4) + ' ' + PApplet.nfs(this.m23, n2, 4));
        System.out.println(PApplet.nfs(this.m30, n2, 4) + ' ' + PApplet.nfs(this.m31, n2, 4) + ' ' + PApplet.nfs(this.m32, n2, 4) + ' ' + PApplet.nfs(this.m33, n2, 4));
        System.out.println();
    }
    
    private final float max(final float n, final float n2) {
        return (n > n2) ? n : n2;
    }
    
    private final float abs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    private final float sin(final float n) {
        return (float)Math.sin(n);
    }
    
    private final float cos(final float n) {
        return (float)Math.cos(n);
    }
    
    private final /* synthetic */ void this() {
        this.stackPointer = 0;
    }
    
    public PMatrix() {
        this.this();
        this.set(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.maxStackDepth = 0;
    }
    
    public PMatrix(final int maxStackDepth) {
        this.this();
        this.set(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.stack = new float[maxStackDepth][16];
        this.maxStackDepth = maxStackDepth;
    }
    
    public PMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.this();
        this.set(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
        this.maxStackDepth = 0;
    }
    
    public PMatrix(final PMatrix pMatrix) {
        this.this();
        this.set(pMatrix);
        this.maxStackDepth = pMatrix.maxStackDepth;
        this.stack = new float[this.maxStackDepth][16];
    }
}
