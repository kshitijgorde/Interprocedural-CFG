// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class Sweep
{
    public Vec2 localCenter;
    public Vec2 c0;
    public Vec2 c;
    public float a0;
    public float a;
    public float t0;
    
    public String toString() {
        String s = "Sweep:\nlocalCenter: " + this.localCenter + "\n";
        s = String.valueOf(s) + "c0: " + this.c0 + ", c: " + this.c + "\n";
        s = String.valueOf(s) + "a0: " + this.a0 + ", a: " + this.a + "\n";
        s = String.valueOf(s) + "t0: " + this.t0 + "\n";
        return s;
    }
    
    public Sweep() {
        this.localCenter = new Vec2();
        this.c0 = new Vec2();
        this.c = new Vec2();
    }
    
    public void getXForm(final XForm xf, final float t) {
        assert xf != null;
        if (1.0f - this.t0 > 1.1920929E-7f) {
            final float alpha = (t - this.t0) / (1.0f - this.t0);
            xf.position.x = (1.0f - alpha) * this.c0.x + alpha * this.c.x;
            xf.position.y = (1.0f - alpha) * this.c0.y + alpha * this.c.y;
            final float angle = (1.0f - alpha) * this.a0 + alpha * this.a;
            xf.R.set(angle);
        }
        else {
            xf.position.set(this.c);
            xf.R.set(this.a);
        }
        xf.position.subLocal(Mat22.mul(xf.R, this.localCenter));
    }
    
    public void advance(final float t) {
        if (this.t0 < t && 1.0f - this.t0 > 1.1920929E-7f) {
            final float alpha = (t - this.t0) / (1.0f - this.t0);
            this.c0.x = (1.0f - alpha) * this.c0.x + alpha * this.c.x;
            this.c0.y = (1.0f - alpha) * this.c0.y + alpha * this.c.y;
            this.a0 = (1.0f - alpha) * this.a0 + alpha * this.a;
            this.t0 = t;
        }
    }
}
