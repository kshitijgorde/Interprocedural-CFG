import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends cc
{
    public f(final Vector vector) {
        this(vector, 14);
    }
    
    public f(final Vector vector, final int period) {
        super(vector);
        super.ag = new e(vector, period);
        super.ae = new b3(vector, period);
        super.period = period;
    }
    
    public void a() {
        super.a();
        super.ag.a("period", new Integer(super.period));
        super.ae.a("period", new Integer(super.period));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
