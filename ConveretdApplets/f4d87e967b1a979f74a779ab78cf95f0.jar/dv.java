import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class dv extends t implements Serializable
{
    long p;
    public d d;
    
    public dv() {
        this.d = new d("elapsed", this, 2, false);
    }
    
    public final boolean initialize() {
        this.p();
        return true;
    }
    
    public final boolean execute() {
        this.d.p((System.currentTimeMillis() - this.p) * 0.001f);
        return true;
    }
    
    public final long p() {
        return this.p = System.currentTimeMillis();
    }
}
