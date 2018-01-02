// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class PositionInterpolator extends Interpolator
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
    float k;
    boolean l;
    
    public PositionInterpolator() {
        this.defaultkeyValue = new float[0];
        this.key = new FloatArrayField(this, "key", 19, this.defaultkeyValue);
        this.keyValue = new FloatArrayField(this, "keyValue", 7, this.defaultkeyValue);
        this.value = new FloatArrayField(this, "value", 6, this.defaultkeyValue);
        this.a = -1.0f;
        this.f = 0;
        this.j = 0;
        this.k = 0.0f;
    }
    
    public void update() {
        super.update();
        this.a(super.fraction.a);
    }
    
    protected void a(final float n) {
        if (this.value.a == null || this.value.a.length != 3) {
            this.value.a = new float[3];
        }
        this.l = false;
        this.b = ((n > this.a) ? this.f : 0);
        while (this.b < this.key.a.length) {
            if (this.b == this.key.a.length - 1) {
                this.j = this.b * 3;
                this.value.a[0] = this.keyValue.a[this.j];
                this.value.a[1] = this.keyValue.a[this.j + 1];
                this.value.a[2] = this.keyValue.a[this.j + 2];
                this.f = this.b;
                this.a = n;
                this.l = true;
                break;
            }
            if ((this.b == 0 && n < this.key.a[this.b]) || (n >= this.key.a[this.b] && n < this.key.a[this.b + 1])) {
                this.j = this.b * 3;
                this.c = this.keyValue.a[this.j];
                this.d = this.keyValue.a[this.j + 1];
                this.e = this.keyValue.a[this.j + 2];
                this.g = this.keyValue.a[this.j + 3];
                this.h = this.keyValue.a[this.j + 4];
                this.i = this.keyValue.a[this.j + 5];
                this.k = (n - this.key.a[this.b]) / (this.key.a[this.b + 1] - this.key.a[this.b]);
                this.f = this.b;
                this.a = n;
                break;
            }
            ++this.b;
        }
        if (!this.l) {
            this.value.a[0] = this.c + (this.g - this.c) * this.k;
            this.value.a[1] = this.d + (this.h - this.d) * this.k;
            this.value.a[2] = this.e + (this.i - this.e) * this.k;
        }
        this.value.setValue(this.value.a);
    }
}
