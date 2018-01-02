import java.util.Date;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aa
{
    public d a;
    public final String b;
    public final int c;
    public Hashtable d;
    public s e;
    
    public aa(final d a, final int c, final s e, final String b) {
        this.a = a;
        this.c = c;
        this.e = e;
        this.b = b;
        this.d = new Hashtable();
    }
    
    public void a(final String s, final Object o) {
        if (o != null) {
            this.d.put(s, o);
        }
    }
    
    public Object a(final String s) {
        if (s == null) {
            return null;
        }
        return this.d.get(s);
    }
    
    public Object b(final String s) {
        return this.a(s);
    }
    
    public Object c(final String s) {
        Object o = this.a(s);
        if (!this.e.ad() && this.e.f.b() != null && this.e.f.b().f.equals(s)) {
            o = this.a(o, this.e.ab().a(s), this.e.x());
        }
        return o;
    }
    
    public boolean equals(final Object o) {
        return o instanceof aa && ((aa)o).c == this.c && ((aa)o).b.equals(this.b);
    }
    
    public final void a() {
        this.e = null;
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.a = null;
    }
    
    private Object a(Object o, final Object o2, final boolean b) {
        if (o instanceof Integer && o2 instanceof Integer) {
            o = new Integer((int)o - (int)o2);
        }
        else if (o instanceof Double && o2 instanceof Double) {
            o = new Double((double)o - (double)o2);
        }
        else if (o instanceof Date && o2 instanceof Date) {
            o = new Date(((Date)o).getTime() - ((Date)o2).getTime());
        }
        return o;
    }
}
