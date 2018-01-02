import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class as extends bk
{
    f G;
    o H;
    
    public as(final f g, final o h) {
        super(g._fldnew);
        this.G = g;
        this.H = h;
    }
    
    public double _mthfor(final int n) throws c {
        return Math.abs(this.G.a(n) - this.H.a(n)) / (this.G.a(n) + this.H.a(n)) * 100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
