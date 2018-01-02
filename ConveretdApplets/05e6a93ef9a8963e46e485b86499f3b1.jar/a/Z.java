// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class Z
{
    public Z q;
    public Z w;
    public Object q;
    
    public Z(final Object q, final Z w, final Z q2) {
        this.q = q;
        this.w = w;
        this.q = q2;
    }
    
    public final void q() {
        this.q.w = this.w;
        this.w.q = this.q;
    }
    
    public final String toString() {
        if (this.q == null) {
            return "null";
        }
        return this.q.toString();
    }
}
