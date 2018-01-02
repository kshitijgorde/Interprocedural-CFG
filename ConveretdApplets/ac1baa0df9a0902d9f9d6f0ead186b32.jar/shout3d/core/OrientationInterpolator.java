// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class OrientationInterpolator extends Interpolator implements FieldObserver
{
    public final float[] defaultkeyValue;
    public final FloatArrayField key;
    public final FloatArrayField keyValue;
    public final FloatArrayField value;
    float[] a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    float k;
    float l;
    float m;
    float n;
    int o;
    float p;
    float q;
    int r;
    boolean s;
    int t;
    boolean u;
    float v;
    float w;
    
    public OrientationInterpolator() {
        this.defaultkeyValue = new float[0];
        this.key = new FloatArrayField(this, "key", 19, this.defaultkeyValue);
        this.keyValue = new FloatArrayField(this, "keyValue", 22, this.defaultkeyValue);
        this.value = new FloatArrayField(this, "value", 21, this.defaultkeyValue);
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.o = 0;
        this.p = 0.0f;
        this.q = -1.0f;
        this.r = 0;
        this.keyValue.addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        super.onFieldChange(field, o);
        if (field == this.keyValue) {
            this.a();
        }
    }
    
    public void update() {
        super.update();
        this.a(super.fraction.a);
    }
    
    protected void finalize() throws Throwable {
        this.keyValue.removeFieldObserver(this);
        super.finalize();
    }
    
    protected void a() {
        if (this.keyValue.a == null) {
            return;
        }
        this.a = new float[this.keyValue.a.length];
        for (int i = 0; i < this.a.length; i += 4) {
            final float n = (float)Math.sin(this.keyValue.a[i + 3] * 0.5f);
            this.a[i] = this.keyValue.a[i] * n;
            this.a[i + 1] = this.keyValue.a[i + 1] * n;
            this.a[i + 2] = this.keyValue.a[i + 2] * n;
            this.a[i + 3] = (float)Math.cos(this.keyValue.a[i + 3] * 0.5f);
        }
    }
    
    protected void a(final float n) {
        if (this.value.a == null || this.value.a.length != 4) {
            this.value.a = new float[4];
        }
        this.u = false;
        this.t = ((n > this.q) ? this.r : 0);
        while (this.t < this.key.a.length) {
            if (this.t == this.key.a.length - 1) {
                this.o = this.t << 2;
                this.value.a[0] = this.keyValue.a[this.o];
                this.value.a[1] = this.keyValue.a[this.o + 1];
                this.value.a[2] = this.keyValue.a[this.o + 2];
                this.value.a[3] = this.keyValue.a[this.o + 3];
                this.r = this.t;
                this.q = n;
                this.u = true;
                break;
            }
            if ((this.t == 0 && n < this.key.a[this.t]) || (n >= this.key.a[this.t] && n < this.key.a[this.t + 1])) {
                this.o = this.t << 2;
                this.b = this.a[this.o];
                this.c = this.a[this.o + 1];
                this.d = this.a[this.o + 2];
                this.e = this.a[this.o + 3];
                this.f = this.a[this.o + 4];
                this.g = this.a[this.o + 5];
                this.h = this.a[this.o + 6];
                this.i = this.a[this.o + 7];
                this.p = (n - this.key.a[this.t]) / (this.key.a[this.t + 1] - this.key.a[this.t]);
                this.r = this.t;
                this.q = n;
                break;
            }
            ++this.t;
        }
        if (!this.u) {
            this.k = this.b * this.f + this.c * this.g + this.d * this.h + this.e * this.i;
            final boolean s = this.k < 0.0f;
            this.s = s;
            if (s) {
                this.k = -this.k;
            }
            if (1.0f - this.k > 1.0E-5f) {
                this.j = (float)Math.acos(this.k);
                this.l = (float)Math.sin(this.j);
                this.m = (float)Math.sin((1.0f - this.p) * this.j) / this.l;
                this.n = (float)Math.sin(this.p * this.j) / this.l;
            }
            else {
                this.m = 1.0f - this.p;
                this.n = this.p;
            }
            if (this.s) {
                this.n = -this.n;
            }
            this.b = this.m * this.b + this.n * this.f;
            this.c = this.m * this.c + this.n * this.g;
            this.d = this.m * this.d + this.n * this.h;
            this.e = this.m * this.e + this.n * this.i;
            this.v = (float)Math.acos(this.e);
            this.w = (float)Math.sin(this.v);
            if (this.w > -1.0E-6f && this.w < 1.0E-6f) {
                this.value.a[0] = 0.0f;
                this.value.a[1] = 0.0f;
                this.value.a[2] = 1.0f;
                this.value.a[3] = 0.0f;
            }
            else {
                this.value.a[0] = this.b / this.w;
                this.value.a[1] = this.c / this.w;
                this.value.a[2] = this.d / this.w;
                this.value.a[3] = 2.0f * this.v;
            }
        }
        this.value.setValue(this.value.a);
    }
}
