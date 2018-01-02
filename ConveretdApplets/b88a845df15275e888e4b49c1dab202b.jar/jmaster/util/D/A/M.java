// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class M extends H
{
    static final long G = -2724874183243154495L;
    private float F;
    private float E;
    
    public M() {
        this.F = 0.5f;
        this.E = 0.5f;
    }
    
    protected int A(final int n) {
        return jmaster.util.D.A.D.B((int)(I.C(I.B(n / 255.0f, this.F), this.E) * 255.0f));
    }
    
    public void B(final float f) {
        this.F = f;
        this.B = false;
    }
    
    public float D() {
        return this.F;
    }
    
    public void A(final float e) {
        this.E = e;
        this.B = false;
    }
    
    public float C() {
        return this.E;
    }
    
    public String toString() {
        return "Colors/Contrast...";
    }
}
