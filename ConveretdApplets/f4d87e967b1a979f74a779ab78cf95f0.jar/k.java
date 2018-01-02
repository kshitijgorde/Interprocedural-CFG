import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class k implements Serializable
{
    v p;
    v d;
    
    public k() {
    }
    
    public k(final v p2, final v d) {
        this.p = p2;
        this.d = d;
    }
    
    public final void p() throws Exception {
        if (!this.d.d()) {
            this.p.p(this.d);
        }
        else if (!this.p.p(this.d)) {
            this.p.p(this.d);
            this.d.p().connectorChanged(this.d);
        }
        else {
            this.p.p(this.d);
        }
    }
    
    public final y p() {
        return this.d.p();
    }
    
    public final y d() {
        return this.p.p();
    }
    
    public final v p() {
        return this.d;
    }
    
    public final v d() {
        return this.p;
    }
}
