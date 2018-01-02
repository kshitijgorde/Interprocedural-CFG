import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class bb implements bc
{
    public final Hashtable a;
    public final j b;
    
    public bb(final Hashtable a, final j b) {
        this.a = a;
        this.b = b;
    }
    
    public abstract void a(final Vector p0, final bf p1, final String p2, final long p3, final String p4);
    
    public abstract void a(final x p0, final bf p1, final int p2, final String p3, final long p4, final String p5);
    
    public abstract void a(final x p0, final bf p1, final String p2, final long p3, final String p4);
}
