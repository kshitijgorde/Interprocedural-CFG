import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class x extends t implements Serializable
{
    public i p;
    public i d;
    
    public x() {
        this.p = new i("in - source", this, 1, true);
        this.d = new i("out - copy", this, 3, false);
    }
    
    public final boolean initialize() {
        if (this.d.p().size() == 0) {
            System.out.println("CopyImage32: no user supplied image, so creating internal copy image");
            this.d.p = this.p.p;
            this.d.d = this.p.d;
            this.d.a = new int[this.p.a.length];
        }
        return true;
    }
    
    public final boolean execute() {
        for (int n = (this.p.a.length <= this.d.a.length) ? this.p.a.length : this.d.a.length, i = 0; i < n; ++i) {
            this.d.a[i] = this.p.a[i];
        }
        return true;
    }
    
    public final void connectorChanged(final v v) {
        if (v == this.p && (this.p.p != this.d.p || this.p.d != this.d.d)) {
            this.a();
        }
    }
}
