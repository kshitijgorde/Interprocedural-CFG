// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.math;

public class Quaternion
{
    private float[] a;
    float[][] b;
    float[] c;
    float[] d;
    Quaternion e;
    Quaternion f;
    float g;
    
    private void a(final float[][] array) {
        final float n = 2.0f / this.b();
        final float n2 = this.a[0] * n;
        final float n3 = this.a[1] * n;
        final float n4 = this.a[2] * n;
        final float n5 = this.a[3] * n2;
        final float n6 = this.a[3] * n3;
        final float n7 = this.a[3] * n4;
        final float n8 = this.a[0] * n2;
        final float n9 = this.a[0] * n3;
        final float n10 = this.a[0] * n4;
        final float n11 = this.a[1] * n3;
        final float n12 = this.a[1] * n4;
        final float n13 = this.a[2] * n4;
        array[0][0] = 1.0f - n11 - n13;
        array[0][1] = n9 - n7;
        array[0][2] = n10 + n6;
        array[1][0] = n9 + n7;
        array[1][1] = 1.0f - n8 - n13;
        array[1][2] = n12 - n5;
        array[2][0] = n10 - n6;
        array[2][1] = n12 + n5;
        array[2][2] = 1.0f - n8 - n11;
    }
    
    private void a() {
        final float n = 1.0f / this.c();
        this.a[0] *= n;
        this.a[1] *= n;
        this.a[2] *= n;
        this.a[3] *= n;
    }
    
    private void a(final Quaternion quaternion) {
        final float n = 1.0f / quaternion.c();
        this.a[0] = quaternion.a(0) * n;
        this.a[1] = quaternion.a(1) * n;
        this.a[2] = quaternion.a(2) * n;
        this.a[3] = quaternion.a(3) * n;
    }
    
    private void a(final float[] array) {
        this.a[0] = array[0];
        this.a[1] = array[1];
        this.a[2] = array[2];
        this.a[3] = 0.0f;
    }
    
    private void b(final float[] array) {
        array[0] = this.a[0];
        array[1] = this.a[1];
        array[2] = this.a[2];
    }
    
    private void c(final float[] array) {
        this.a[0] = array[0];
        this.a[1] = array[1];
        this.a[2] = array[2];
        this.a[3] = array[3];
    }
    
    public float[] getValue() {
        return this.a;
    }
    
    public float[] getMat() {
        this.a(this.b = new float[3][3]);
        final float[] array = new float[16];
        MatUtil.c(array);
        array[0] = this.b[0][0];
        array[1] = this.b[0][1];
        array[2] = this.b[0][2];
        array[4] = this.b[1][0];
        array[5] = this.b[1][1];
        array[6] = this.b[1][2];
        array[8] = this.b[2][0];
        array[9] = this.b[2][1];
        array[10] = this.b[2][2];
        return array;
    }
    
    public Quaternion() {
        this.a = new float[4];
        this.makeIdent();
    }
    
    public void makeIdent() {
        this.a[0] = 0.0f;
        this.a[1] = 0.0f;
        this.a[2] = 0.0f;
        this.a[3] = 1.0f;
    }
    
    private float b() {
        return this.a[0] * this.a[0] + this.a[1] * this.a[1] + this.a[2] * this.a[2] + this.a[3] * this.a[3];
    }
    
    public void xform(final float[] array) {
        this.e = new Quaternion();
        this.f = new Quaternion();
        this.e.makeIdent();
        this.f.makeIdent();
        this.e.a(array);
        this.f.b(this);
        this.f.mult(this.e, this.f);
        this.f.mult(this, this.f);
        this.f.b(array);
    }
    
    private float[] b(final float[][] array) {
        final float[] array2 = new float[3];
        final float n = -array[1][2];
        final float n2 = (float)Math.sqrt(1.0f - n * n);
        float n3;
        float n4;
        float n5;
        float n6;
        if (Math.abs(n2) > 1.0E-4f) {
            n3 = array[2][2] / n2;
            n4 = array[0][2] / n2;
            n5 = array[2][1] / n2;
            n6 = array[1][0] / n2;
        }
        else {
            n3 = 1.0f;
            n4 = 0.0f;
            n5 = array[0][0];
            n6 = -array[0][1];
        }
        array2[0] = (float)Math.atan2(n4, n3);
        array2[1] = (float)Math.atan2(n, n2);
        array2[2] = (float)Math.atan2(n6, n5);
        return array2;
    }
    
    public void setEulers(final float[] array) {
        final float n = (float)Math.sin(array[0] * 0.5f);
        final float n2 = (float)Math.cos(array[0] * 0.5f);
        final float n3 = (float)Math.sin(array[1] * 0.5f);
        final float n4 = (float)Math.cos(array[1] * 0.5f);
        final float n5 = (float)Math.sin(array[2] * 0.5f);
        final float n6 = (float)Math.cos(array[2] * 0.5f);
        this.a[0] = n2 * n3 * n6 + n * n4 * n5;
        this.a[1] = n * n4 * n6 - n2 * n3 * n5;
        this.a[2] = n2 * n4 * n5 - n * n3 * n6;
        this.a[3] = n2 * n4 * n6 + n * n3 * n5;
    }
    
    public void setEulers(final float n, final float n2, final float n3) {
        this.setEulers(new float[] { n, n2, n3 });
    }
    
    public void getEulers(final float[] array) {
        this.a(this.b = new float[3][3]);
        System.arraycopy(this.b(this.b), 0, array, 0, 3);
    }
    
    private void b(final Quaternion quaternion) {
        final float n = 1.0f / quaternion.b();
        this.a[0] = -quaternion.a(0) * n;
        this.a[1] = -quaternion.a(1) * n;
        this.a[2] = -quaternion.a(2) * n;
        this.a[3] = quaternion.a(3) * n;
    }
    
    public void mult(final Quaternion quaternion, final Quaternion quaternion2) {
        this.c = new float[4];
        this.d = new float[4];
        quaternion.getQuat(this.c);
        quaternion2.getQuat(this.d);
        this.a[0] = this.c[3] * this.d[0] - this.c[2] * this.d[1] + this.c[1] * this.d[2] + this.c[0] * this.d[3];
        this.a[1] = this.c[2] * this.d[0] + this.c[3] * this.d[1] - this.c[0] * this.d[2] + this.c[1] * this.d[3];
        this.a[2] = -this.c[1] * this.d[0] + this.c[0] * this.d[1] + this.c[3] * this.d[2] + this.c[2] * this.d[3];
        this.a[3] = -this.c[0] * this.d[0] - this.c[1] * this.d[1] - this.c[2] * this.d[2] + this.c[3] * this.d[3];
    }
    
    public void setQuat(final float[] array) {
        this.a[0] = array[0];
        this.a[1] = array[1];
        this.a[2] = array[2];
        this.a[3] = array[3];
    }
    
    public void getQuat(final float[] array) {
        array[0] = this.a[0];
        array[1] = this.a[1];
        array[2] = this.a[2];
        array[3] = this.a[3];
    }
    
    public void setQuat(final Quaternion quaternion) {
        final float[] quat = new float[4];
        quaternion.getQuat(quat);
        this.setQuat(quat);
    }
    
    public void getQuat(final Quaternion quaternion) {
        final float[] quat = new float[4];
        this.getQuat(quat);
        quaternion.setQuat(quat);
    }
    
    private void a(final float n, final float n2, final float n3, final float n4) {
        this.a[0] = n;
        this.a[1] = n2;
        this.a[2] = n3;
        this.a[3] = n4;
    }
    
    private void a(final float[] array, final float[] array2, final float[] array3, final float[] array4) {
        array[0] = this.a[0];
        array2[0] = this.a[1];
        array3[0] = this.a[2];
        array4[0] = this.a[3];
    }
    
    private void a(final int n, final float n2) {
        if (n < 0 || n > 3) {
            return;
        }
        this.a[n] = n2;
    }
    
    private float a(final int n) {
        if (n < 0 || n > 3) {
            return 0.0f;
        }
        return this.a[n];
    }
    
    private float c() {
        return (float)Math.sqrt(this.a[0] * this.a[0] + this.a[1] * this.a[1] + this.a[2] * this.a[2] + this.a[3] * this.a[3]);
    }
    
    public void getAxisAngle(final float[] array) {
        array[3] = 2.0f * (float)Math.acos(this.a[3]);
        if (Math.abs(array[3]) < 1.0E-4f) {
            array[0] = 0.0f;
            array[1] = 1.0f;
            array[2] = 0.0f;
            return;
        }
        this.g = 1.0f / (float)Math.sin(array[3] * 0.5f);
        array[0] = this.a[0] * this.g;
        array[1] = this.a[1] * this.g;
        array[2] = this.a[2] * this.g;
    }
    
    public void setAxisAngle(final float[] array) {
        if (array[3] == 0.0f) {
            this.makeIdent();
        }
        final float n = array[0] * array[0] + array[1] * array[1] + array[2] * array[2];
        if (n < 1.0E-4) {
            this.makeIdent();
        }
        final float n2 = 1.0f / (float)Math.sqrt(n);
        array[0] *= n2;
        array[1] *= n2;
        array[2] *= n2;
        final float n3 = (float)Math.sin(array[3] * 0.5f);
        this.a[0] = array[0] * n3;
        this.a[1] = array[1] * n3;
        this.a[2] = array[2] * n3;
        this.a[3] = (float)Math.cos(array[3] * 0.5f);
    }
}
