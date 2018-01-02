// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ar
{
    public ar q;
    public ar w;
    public Object q;
    
    public ar(final Object q, final ar w, final ar q2) {
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
