// 
// Decompiled by Procyon v0.5.30
// 

public class dc extends dl
{
    public float p;
    
    public dc() {
    }
    
    public dc(final float p) {
        this.p = p;
    }
    
    public final void p(final dl dl) {
        if (dl instanceof dc) {
            this.p = ((dc)dl).p;
        }
    }
    
    public final void p(final String s) {
        this.p = Float.valueOf(s);
    }
    
    public final String toString() {
        return String.valueOf(this.p);
    }
    
    public final void p(final dc dc) {
        this.p = dc.p;
    }
    
    public final void p(final float p) {
        this.p = p;
    }
    
    public final float p() {
        return this.p;
    }
    
    public final void p(final dl dl, final dl dl2) {
        if (dl instanceof dc && dl2 instanceof dc) {
            final float p2 = ((dc)dl).p;
            final float p3 = ((dc)dl2).p;
            if (this.p < p2) {
                this.p = p2;
            }
            else if (this.p > p3) {
                this.p = p3;
            }
        }
    }
    
    public final void p(final float n, final float n2, final float n3) {
        this.p = n * (n3 - n2) + n2;
    }
    
    public final float p(final float n, final float n2) {
        if (n2 == n) {
            System.out.println("xFloat: divide by zero");
            return 0.0f;
        }
        return (this.p - n) / (n2 - n);
    }
    
    public final boolean p(final dc dc) {
        return this.p == dc.p;
    }
}
