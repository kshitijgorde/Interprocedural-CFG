import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public String w;
    public int v;
    public Color u;
    public Color t;
    public boolean s;
    public boolean r;
    public boolean q;
    public boolean p;
    
    public d() {
        this.w = "";
        this.v = -1;
        this.u = Color.black;
        this.s = false;
        this.r = false;
        this.q = false;
        this.p = false;
    }
    
    public d(final Color u) {
        this.w = "";
        this.v = -1;
        this.u = Color.black;
        this.s = false;
        this.r = false;
        this.q = false;
        this.p = false;
        this.u = u;
    }
    
    public d(final Color t, final Color u, final boolean s, final boolean r) {
        this.w = "";
        this.v = -1;
        this.u = Color.black;
        this.s = false;
        this.r = false;
        this.q = false;
        this.p = false;
        this.t = t;
        this.u = u;
        this.s = s;
        this.r = r;
    }
    
    public final void l(final String w) {
        this.w = w;
    }
    
    public final void k() {
        this.t = null;
        this.s = false;
        this.r = false;
    }
}
