import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public String u;
    public Color t;
    public Color s;
    public boolean r;
    public boolean q;
    public boolean p;
    
    public d() {
        this.u = "";
        this.t = Color.black;
        this.r = false;
        this.q = false;
        this.p = false;
    }
    
    public d(final Color t) {
        this.u = "";
        this.t = Color.black;
        this.r = false;
        this.q = false;
        this.p = false;
        this.t = t;
    }
    
    public d(final Color s, final Color t, final boolean r, final boolean q) {
        this.u = "";
        this.t = Color.black;
        this.r = false;
        this.q = false;
        this.p = false;
        this.s = s;
        this.t = t;
        this.r = r;
        this.q = q;
    }
    
    public final void k(final String u) {
        this.u = u;
    }
    
    public final void j() {
        this.s = null;
        this.r = false;
        this.q = false;
    }
}
