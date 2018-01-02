import java.io.InputStream;
import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_be extends rp_gd
{
    private rp_cI a;
    private Object a;
    private Object b;
    private rp_fK a;
    
    public rp_be(final rp_fK a, final rp_cI a2, final Object a3, final Object b) {
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.b = b;
    }
    
    public final rp_av a() {
        final Vector vector = new Vector();
        final Dimension dimension = new Dimension();
        InputStream a;
        try {
            a = this.a.a(this.a, this.b);
        }
        catch (Exception ex) {
            rp_C.a(1, "Ex in DataLoaderThread: " + ex.getMessage());
            return null;
        }
        if (!this.a.a(a, vector, dimension)) {
            return null;
        }
        return new rp_av(this, vector, dimension);
    }
    
    protected final void a() {
        try {
            this.a((rp_av)this.get());
        }
        catch (Exception ex) {}
    }
    
    protected final void a(final rp_av rp_av) {
        if (rp_av == null) {
            this.a.a(this.a, null, new Dimension(), this.b);
            return;
        }
        this.a.a(this.a, rp_av.a, rp_av.a, this.b);
    }
}
