import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class d extends b
{
    private final try nb;
    protected Component r;
    protected boolean s;
    
    public d(final try try1, final Component r, final b b) {
        super(b.t, b.u, b.v, b.w, b.x, b.y);
        this.nb = try1;
        this.nb = try1;
        this.s = (super.u == super.w && super.t == super.v);
        this.r = r;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o instanceof Component) {
            b = (this.r == o);
        }
        return b;
    }
}
