import java.awt.Frame;
import java.util.Collection;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_fT
{
    public String d;
    String e;
    public String f;
    Vector a;
    public int a;
    
    public abstract int a();
    
    abstract String a();
    
    public rp_fT() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.a = 0;
    }
    
    public rp_fT(final rp_fT rp_fT) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.a = 0;
        this.d = rp_fT.d;
        this.e = rp_fT.e;
        this.f = rp_fT.f;
        if (rp_fT.a != null) {
            this.a = new Vector(rp_fT.a);
        }
    }
    
    public String b() {
        return this.d;
    }
    
    abstract boolean a(final Frame p0, final rp_dC p1, final rp_dv p2);
    
    public abstract void a(final Vector p0, final rp_dv p1, final int p2, final rp_eP p3);
    
    final void a(final String s) {
        if (this.a == null) {
            this.a = new Vector();
        }
        this.a.addElement(s);
    }
}
