// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class ScalarInterpolator extends Interpolator
{
    public final float[] defaultkeyValue;
    public final FloatArrayField key;
    public final FloatArrayField keyValue;
    public final FloatField value;
    int b;
    float a;
    int f;
    float c;
    float d;
    int e;
    float g;
    boolean h;
    
    public ScalarInterpolator() {
        this.defaultkeyValue = new float[0];
        this.key = new FloatArrayField(this, "key", 19, this.defaultkeyValue);
        this.keyValue = new FloatArrayField(this, "keyValue", 0, this.defaultkeyValue);
        this.value = new FloatField(this, "value", 0, 0.0f);
        this.a = -1.0f;
        this.f = 0;
        this.e = 0;
        this.g = 0.0f;
    }
    
    public void update() {
        super.update();
        this.a(super.fraction.a);
    }
    
    protected void a(final float n) {
        this.h = false;
        this.b = ((n > this.a) ? this.f : 0);
        while (this.b < this.key.a.length) {
            if (this.b == this.key.a.length - 1) {
                this.e = this.b;
                this.value.setValue(this.keyValue.a[this.e]);
                this.f = this.b;
                this.a = n;
                this.h = true;
                break;
            }
            if ((this.b == 0 && n < this.key.a[this.b]) || (n >= this.key.a[this.b] && n < this.key.a[this.b + 1])) {
                this.e = this.b;
                this.c = this.keyValue.a[this.e];
                this.d = this.keyValue.a[this.e + 1];
                this.g = (n - this.key.a[this.b]) / (this.key.a[this.b + 1] - this.key.a[this.b]);
                this.f = this.b;
                this.a = n;
                break;
            }
            ++this.b;
        }
        if (!this.h) {
            this.value.setValue(this.c + (this.d - this.c) * this.g);
        }
    }
}
