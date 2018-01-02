import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class cc extends bk
{
    public static final int af = 14;
    public int period;
    bk ag;
    bk ae;
    
    public cc(final Vector vector) {
        super(vector);
    }
    
    public double _mthfor(final int n) throws c {
        return this.ag.a(n) / this.ae.a(n) * 100.0;
    }
}
