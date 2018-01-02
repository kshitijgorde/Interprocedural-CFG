// 
// Decompiled by Procyon v0.5.30
// 

public class _zp extends _zh
{
    public String p;
    private double p;
    private double d;
    private double a;
    private double n;
    private double v;
    private double i;
    private int p;
    
    public _zp(final String s, final String s2, final _zs zs, final PixScreen pixScreen, final double n, final double n2, final String p14, final int p15, final double p16, final double d, final double a, final double v, final double i, final double n3) {
        super(s, s2, zs, pixScreen, n, n2, 1, 2, 0);
        this.p = p15;
        this.p = p16;
        this.d = d;
        this.a = a;
        this.v = v;
        this.i = i;
        this.n = n3;
        this.p = p14;
    }
    
    public final void n() {
        if (_zh.p != null) {
            _zh.p.a(null);
            _zh.p.TransitTo(this.p, this.p, this.d, this.a, this.v, this.i, this.n, this.p, 40.0);
        }
    }
}
