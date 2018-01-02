// 
// Decompiled by Procyon v0.5.30
// 

public class db extends dl
{
    public int p;
    
    public db() {
    }
    
    public db(final int p) {
        this.p = p;
    }
    
    public final void p(final dl dl) {
        if (dl instanceof db) {
            this.p = ((db)dl).p;
        }
    }
    
    public final void p(final String s) {
        this.p = Integer.decode(s);
    }
    
    public final String toString() {
        return String.valueOf(this.p);
    }
    
    public final void p(final db db) {
        this.p = db.p;
    }
    
    public final void p(final int p) {
        this.p = p;
    }
    
    public final int p() {
        return this.p;
    }
    
    public final void p(final dl dl, final dl dl2) throws Exception {
        if (dl instanceof db && dl2 instanceof db) {
            final int p2 = ((db)dl).p;
            final int p3 = ((db)dl2).p;
            if (this.p < p2) {
                this.p = p2;
            }
            else if (this.p > p3) {
                this.p = p3;
            }
        }
    }
    
    public final void p(final float n, final float n2, final float n3) {
        this.p = (int)(n * (n3 - n2) + n2);
    }
    
    public final float p(final float n, final float n2) {
        if (n2 == n) {
            System.out.println("xInteger: divide by zero");
            return 0.0f;
        }
        return (this.p - n) / (n2 - n);
    }
    
    public final boolean p(final db db) {
        return this.p == db.p;
    }
}
