// 
// Decompiled by Procyon v0.5.30
// 

public class df extends dl
{
    public boolean p;
    
    public df() {
    }
    
    public df(final boolean p) {
        this.p = p;
    }
    
    public final void p(final dl dl) {
        if (dl instanceof df) {
            this.p = ((df)dl).p;
        }
    }
    
    public final void p(final String s) {
        this.p = Boolean.valueOf(s);
    }
    
    public final String toString() {
        return String.valueOf(this.p);
    }
    
    public final void p(final df df) {
        this.p = df.p;
    }
    
    public final void p(final boolean p) {
        this.p = p;
    }
    
    public final boolean p() {
        return this.p;
    }
    
    public final void p(final dl dl, final dl dl2) {
    }
    
    public final void p(final float n, final float n2, final float n3) {
    }
    
    public final float p(final float n, final float n2) {
        return 1.0f;
    }
    
    public final boolean p(final df df) {
        return this.p == df.p;
    }
}
