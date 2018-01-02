// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class XForm
{
    public Vec2 position;
    public Mat22 R;
    public static XForm identity;
    
    static {
        (XForm.identity = new XForm()).setIdentity();
    }
    
    public XForm() {
        this.position = new Vec2();
        this.R = new Mat22();
    }
    
    public XForm(final XForm xf) {
        this.position = xf.position.clone();
        this.R = xf.R.clone();
    }
    
    public XForm(final Vec2 _position, final Mat22 _R) {
        this.position = _position.clone();
        this.R = _R.clone();
    }
    
    public void set(final XForm xf) {
        this.position.set(xf.position);
        this.R.set(xf.R);
    }
    
    public void setIdentity() {
        this.position.setZero();
        this.R.setIdentity();
    }
    
    public static Vec2 mul(final XForm T, final Vec2 v) {
        return T.position.add(T.R.mul(v));
    }
    
    public static Vec2 mulT(final XForm T, final Vec2 v) {
        return T.R.mulT(v.sub(T.position));
    }
    
    public String toString() {
        String s = "XForm:\n";
        s = String.valueOf(s) + "Position: " + this.position + "\n";
        s = String.valueOf(s) + "R: \n" + this.R + "\n";
        return s;
    }
}
