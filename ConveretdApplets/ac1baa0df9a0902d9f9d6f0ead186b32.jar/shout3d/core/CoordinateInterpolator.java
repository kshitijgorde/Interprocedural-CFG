// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class CoordinateInterpolator extends Interpolator
{
    public final float[] defaultkeyValue;
    public final FloatArrayField key;
    public final FloatArrayField keyValue;
    public final FloatArrayField value;
    int b;
    float a;
    int f;
    float c;
    float d;
    float e;
    float g;
    float h;
    float i;
    int j;
    int k;
    float l;
    boolean m;
    
    public CoordinateInterpolator() {
        this.defaultkeyValue = new float[0];
        this.key = new FloatArrayField(this, "key", 19, this.defaultkeyValue);
        this.keyValue = new FloatArrayField(this, "keyValue", 7, this.defaultkeyValue);
        this.value = new FloatArrayField(this, "value", 7, this.defaultkeyValue);
        this.a = -1.0f;
        this.f = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0.0f;
    }
    
    public void update() {
        super.update();
        this.a(super.fraction.a);
    }
    
    protected void a(final float n) {
        final int k = this.keyValue.a.length / this.key.a.length;
        this.j = 0;
        this.k = k;
        this.l = 0.0f;
        if (this.value.a == null || this.value.a.length != k) {
            this.value.a = new float[k];
        }
        this.m = false;
        this.b = ((n > this.a) ? this.f : 0);
        while (this.b < this.key.a.length) {
            if (this.b == this.key.a.length - 1) {
                System.arraycopy(this.keyValue.a, this.keyValue.a.length - k, this.value.a, 0, k);
                this.f = this.b;
                this.a = n;
                this.m = true;
                break;
            }
            if ((this.b == 0 && n < this.key.a[this.b]) || (n >= this.key.a[this.b] && n < this.key.a[this.b + 1])) {
                this.j = this.b * k;
                this.k = this.j + k;
                this.l = (n - this.key.a[this.b]) / (this.key.a[this.b + 1] - this.key.a[this.b]);
                this.f = this.b;
                this.a = n;
                break;
            }
            ++this.b;
        }
        if (!this.m) {
            for (int i = 0; i < k; ++i, ++this.j, ++this.k) {
                this.c = this.keyValue.a[this.j];
                this.g = this.keyValue.a[this.k];
                this.value.a[i] = this.c + (this.g - this.c) * this.l;
            }
        }
        this.value.setValue(this.value.a);
    }
}
