// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class Viewpoint extends Bindable implements FieldObserver
{
    public final float[] defaultOrientation;
    public final float[] defaultPosition;
    public final StringField description;
    public final FloatField fieldOfView;
    public final FloatArrayField orientation;
    public final FloatArrayField position;
    protected float[] a;
    protected float[] b;
    protected boolean c;
    protected boolean d;
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
    float o;
    float p;
    float q;
    
    protected float[] a() {
        if (!this.d) {
            this.c();
        }
        return this.a;
    }
    
    protected float[] b() {
        if (!this.c) {
            this.d();
        }
        return this.b;
    }
    
    public Viewpoint() {
        this(null);
    }
    
    public Viewpoint(final Shout3DViewer shout3DViewer) {
        super(shout3DViewer);
        this.defaultOrientation = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.defaultPosition = new float[] { 0.0f, 0.0f, 10.0f };
        this.description = new StringField(this, "description", 0, "");
        this.fieldOfView = new FloatField(this, "fieldOfView", 9, 0.785398f);
        this.orientation = new FloatArrayField(this, "orientation", 21, this.defaultOrientation);
        this.position = new FloatArrayField(this, "position", 6, this.defaultPosition);
        this.a = new float[12];
        this.b = new float[12];
        this.c = false;
        this.d = false;
        this.fieldOfView.addFieldObserver(this, null);
        this.orientation.addFieldObserver(this, null);
        this.position.addFieldObserver(this, null);
    }
    
    public float[] getParentToCamera() {
        return MatUtil.d(this.a());
    }
    
    public float[] getCameraToParent() {
        return MatUtil.d(this.b());
    }
    
    public void onFieldChange(final Field field, final Object o) {
        super.onFieldChange(field, o);
        if (field == this.position || field == this.orientation || field == this.fieldOfView) {
            this.c = false;
            this.d = false;
        }
    }
    
    protected void c() {
        if (this.d) {
            return;
        }
        this.e = (float)Math.sin(this.orientation.a[3]);
        this.f = (float)Math.cos(this.orientation.a[3]);
        this.g = 1.0f - this.f;
        this.h = this.orientation.a[0] * this.g;
        this.i = this.orientation.a[1] * this.g;
        this.j = this.orientation.a[2] * this.g;
        this.k = this.orientation.a[0] * this.e;
        this.l = this.orientation.a[1] * this.e;
        this.m = this.orientation.a[2] * this.e;
        this.a[0] = this.orientation.a[0] * this.h + this.f;
        this.a[1] = this.orientation.a[1] * this.h - this.m;
        this.a[2] = this.orientation.a[2] * this.h + this.l;
        this.a[3] = this.orientation.a[0] * this.i + this.m;
        this.a[4] = this.orientation.a[1] * this.i + this.f;
        this.a[5] = this.orientation.a[2] * this.i - this.k;
        this.a[6] = this.orientation.a[0] * this.j - this.l;
        this.a[7] = this.orientation.a[1] * this.j + this.k;
        this.a[8] = this.orientation.a[2] * this.j + this.f;
        this.a[9] = -this.position.a[0] * this.a[0] - this.position.a[1] * this.a[3] - this.position.a[2] * this.a[6];
        this.a[10] = -this.position.a[0] * this.a[1] - this.position.a[1] * this.a[4] - this.position.a[2] * this.a[7];
        this.a[11] = -this.position.a[0] * this.a[2] - this.position.a[1] * this.a[5] - this.position.a[2] * this.a[8];
        this.d = true;
    }
    
    protected void d() {
        if (this.c) {
            return;
        }
        this.n = -this.orientation.a[0];
        this.o = -this.orientation.a[1];
        this.p = -this.orientation.a[2];
        this.q = this.orientation.a[3];
        this.e = (float)Math.sin(this.q);
        this.f = (float)Math.cos(this.q);
        this.g = 1.0f - this.f;
        this.h = this.n * this.g;
        this.i = this.o * this.g;
        this.j = this.p * this.g;
        this.k = this.n * this.e;
        this.l = this.o * this.e;
        this.m = this.p * this.e;
        this.b[0] = this.n * this.h + this.f;
        this.b[1] = this.o * this.h - this.m;
        this.b[2] = this.p * this.h + this.l;
        this.b[3] = this.n * this.i + this.m;
        this.b[4] = this.o * this.i + this.f;
        this.b[5] = this.p * this.i - this.k;
        this.b[6] = this.n * this.j - this.l;
        this.b[7] = this.o * this.j + this.k;
        this.b[8] = this.p * this.j + this.f;
        this.b[9] = this.position.a[0];
        this.b[10] = this.position.a[1];
        this.b[11] = this.position.a[2];
        this.c = true;
    }
}
