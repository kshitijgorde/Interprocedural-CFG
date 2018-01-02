// 
// Decompiled by Procyon v0.5.30
// 

class FisheyeTransform
{
    protected static final float RADIUS_DEFAULT = 255.0f;
    protected static final float TWO_OVER_PI = 0.63661975f;
    protected static final float HALF_PI = 1.5707964f;
    protected static final float MACH_EPS = 1.0E-5f;
    protected float radius;
    protected float[] a;
    protected float[] n;
    protected float[] o;
    protected float[] ar;
    protected float[] nr;
    protected float[] or;
    protected float[] viewpoint;
    
    FisheyeTransform() {
        this.radius = 255.0f;
        this.a = new float[3];
        this.n = new float[3];
        this.o = new float[3];
        this.ar = new float[3];
        this.nr = new float[3];
        this.or = new float[3];
        this.viewpoint = null;
    }
    
    final void setRadius(final float rad) {
        this.radius = ((rad > 0.0f) ? rad : 255.0f);
    }
    
    final float[] getViewpoint() {
        return this.viewpoint;
    }
    
    final void setViewpoint(final float[] vp) {
        this.viewpoint = vp;
        final float cp = (float)Math.cos(vp[0]);
        final float sp = (float)Math.sin(vp[0]);
        final float ct = (float)Math.cos(vp[1]);
        final float st = (float)Math.sin(vp[1]);
        final float[] _o = { sp * st, ct, cp * st };
        final float[] _a = { sp * ct, -st, cp * ct };
        this.o[0] = Util.dot(this.nr, _o);
        this.o[1] = Util.dot(this.or, _o);
        this.o[2] = Util.dot(this.ar, _o);
        this.a[0] = Util.dot(this.nr, _a);
        this.a[1] = Util.dot(this.or, _a);
        this.a[2] = Util.dot(this.ar, _a);
        Util.cross(this.o, this.a, this.n);
    }
    
    final float forward(final float[] out, final float[] in) {
        final float mr = this.viewpoint[3] * this.radius;
        final float Px = out[0] * this.n[0] + out[1] * this.o[0] + mr * this.a[0];
        final float Py = out[0] * this.n[1] + out[1] * this.o[1] + mr * this.a[1];
        final float Pz = out[0] * this.n[2] + out[1] * this.o[2] + mr * this.a[2];
        final float den = (float)Math.sqrt(Px * Px + Py * Py);
        if (den < 1.0E-5f) {
            in[0] = (in[1] = 0.0f);
            return Pz;
        }
        final float beta = (float)Math.atan2(den, Math.abs(Pz)) * 0.63661975f * this.radius / den;
        in[0] = Px * beta;
        in[1] = Py * beta;
        return Pz;
    }
    
    final boolean inverse(float image, final float[] in, final float[] out) {
        final float mr = this.viewpoint[3] * this.radius;
        image = ((image >= 0.0f) ? 1.0f : -1.0f);
        float rho = (float)Math.sqrt(in[0] * in[0] + in[1] * in[1]);
        if (rho < 1.0E-5f) {
            if (image * this.a[2] > 1.0E-5f) {
                out[0] = mr * this.n[2] * this.a[2];
                out[1] = mr * this.o[2] * this.a[2];
                return true;
            }
            return true;
        }
        else {
            final float beta = rho * 1.5707964f / this.radius;
            final float sbeta = (float)Math.sin(beta);
            final float cbeta = (float)Math.cos(beta);
            rho *= image;
            float den = in[0] * sbeta * this.a[0] + in[1] * sbeta * this.a[1] + rho * cbeta * this.a[2];
            if (den <= 1.0E-5f) {
                return false;
            }
            den = mr / den;
            out[0] = den * (in[0] * this.n[0] * sbeta + in[1] * this.n[1] * sbeta + rho * this.n[2] * cbeta);
            out[1] = den * (in[0] * this.o[0] * sbeta + in[1] * this.o[1] * sbeta + rho * this.o[2] * cbeta);
            return true;
        }
    }
    
    final void setRefViewpoint(final float[] ref) {
        final float cp = (float)Math.cos(ref[0]);
        final float sp = (float)Math.sin(ref[0]);
        final float ct = (float)Math.cos(ref[1]);
        final float st = (float)Math.sin(ref[1]);
        final float cr = (float)Math.cos(ref[2]);
        final float sr = (float)Math.sin(ref[2]);
        final float stcr = st * cr;
        this.or[0] = sp * stcr - cp * sr;
        this.or[1] = ct * cr;
        this.or[2] = cp * stcr + sp * sr;
        this.ar[0] = sp * ct;
        this.ar[1] = -st;
        this.ar[2] = cp * ct;
        Util.cross(this.or, this.ar, this.nr);
    }
}
