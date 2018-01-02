import java.util.Vector;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class v implements Serializable
{
    public static final int p = 1;
    public static final int d = 2;
    public static final int a = 3;
    public static final int n = 4;
    static final String v = "_";
    protected y i;
    String l;
    int b;
    Vector c;
    Vector e;
    v f;
    Vector g;
    
    protected v() {
        this.g = null;
    }
    
    public v(final String l, final y i, final int b, final boolean b2) {
        this.g = null;
        this.l = l;
        this.i = i;
        this.b = b;
        i.p(this);
        this.c = new Vector(4);
        this.e = new Vector(4);
        if (b2) {
            try {
                this.f = (v)this.getClass().newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            this.f = null;
        }
    }
    
    public final boolean d() {
        return this.f != null;
    }
    
    public final Vector p() {
        return this.c;
    }
    
    public final boolean p(final k k) {
        Vector vector;
        if (k.p() == this) {
            vector = this.c;
        }
        else {
            if (k.d() != this) {
                return false;
            }
            vector = this.e;
        }
        if (vector.contains(k)) {
            return false;
        }
        vector.addElement(k);
        this.i.p(k);
        return true;
    }
    
    public final y p() {
        return this.i;
    }
    
    public final String d() {
        return this.l;
    }
    
    public abstract boolean p(final v p0);
    
    public abstract void p(final v p0);
    
    public final boolean d(final v v) {
        return v != null && (this.getClass().isInstance(v) || v.getClass().isInstance(this));
    }
    
    public final boolean a(final v v) {
        boolean b = false;
        switch (this.b) {
            case 2: {
                if (v.b == 1 || v.b == 3) {
                    b = true;
                    break;
                }
                break;
            }
            case 1: {
                if (v.b == 2 || v.b == 3) {
                    b = true;
                    break;
                }
                break;
            }
            case 3: {
                if (v.b == 1 || v.b == 2 || v.b == 3) {
                    b = true;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    public final String toString() {
        return this.l;
    }
}
