import java.util.Enumeration;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class v
{
    public d a;
    public Hashtable b;
    public Vector c;
    public Vector d;
    public String e;
    public Date f;
    
    public v(final d a, final String e) {
        this.b = new Hashtable(1);
        this.c = new Vector(0);
        this.d = new Vector(0);
        v v = null;
        v v2 = null;
        this.a = a;
        this.f = new Date();
        this.e = e;
        if (e == null) {
            this.e = "";
        }
        this.e = this.e.trim();
        final Vector a2 = f.a(this.e, "\n");
        for (int i = 0; i < a2.size(); ++i) {
            final Vector a3 = f.a(a2.elementAt(i), "|");
            if (a3.size() > 0) {
                if (a3.elementAt(0).trim().length() == 0 && a3.size() > 1) {
                    v = this.a(a3.elementAt(1).trim());
                    v2 = v.a(v.c.size());
                    if (a3.size() > 2) {
                        final v a4 = v2.a((String)null);
                        final v a5 = a4.a(a4.c.size());
                        for (int j = 2; j < a3.size(); ++j) {
                            String trim;
                            if ((trim = a3.elementAt(j).trim()).length() == 0) {
                                trim = null;
                            }
                            a5.d.addElement(trim);
                        }
                    }
                }
                else {
                    if (v == null) {
                        v = this.a((String)null);
                    }
                    if (v2 == null) {
                        v2 = v.a(v.c.size());
                    }
                    final v a6 = v2.a(a3.elementAt(0).trim());
                    final v a7 = a6.a(a6.c.size());
                    for (int k = 1; k < a3.size(); ++k) {
                        String trim2;
                        if ((trim2 = a3.elementAt(k).trim()).length() == 0) {
                            trim2 = null;
                        }
                        a7.d.addElement(trim2);
                    }
                }
            }
        }
    }
    
    public final Enumeration a() {
        return this.b.keys();
    }
    
    public final v a(String s) {
        s = ((s == null) ? "" : s);
        v v;
        if ((v = this.b.get(s)) == null) {
            v = new v(this.a, null);
            this.b.put(s, v);
        }
        return v;
    }
    
    public final Vector a(final String s, final String s2, final String s3) {
        final v a = this.a(s);
        final Vector<v> vector = new Vector<v>();
        for (int i = 0; i < a.b(); ++i) {
            final v a2 = a.a(i);
            final String b = a2.a((String)null).a(0).b(0);
            if ((b == null && s2 == null) || (b != null && b.equals(s2))) {
                final String b2 = a2.a((String)null).a(0).b(1);
                if ((b2 == null && s3 == null) || (b2 != null && b2.equals(s3))) {
                    vector.addElement(a2);
                }
            }
        }
        return vector;
    }
    
    public final boolean b(String s) {
        s = ((s == null) ? "" : s);
        return this.b.get(s) != null;
    }
    
    public final void a(String s, final v v) {
        s = ((s == null) ? "" : s);
        final v v2;
        if ((v2 = this.b.get(s)) != null) {
            v2.d();
        }
        this.b.put(s, v.e());
    }
    
    public final int b() {
        return this.c.size();
    }
    
    public final v a(final int n) {
        if (n >= this.c.size()) {
            this.c.setSize(n + 1);
        }
        v v;
        if ((v = this.c.elementAt(n)) == null) {
            v = new v(this.a, null);
            this.c.setElementAt(v, n);
        }
        return v;
    }
    
    public final int c() {
        return this.d.size();
    }
    
    public final String b(final int n) {
        return (n < this.d.size()) ? this.d.elementAt(n) : null;
    }
    
    public final String a(final String s, final int n, final String s2, final int n2, final int n3) {
        return this.a(s).a(n).a(s2).a(n2).b(n3);
    }
    
    public final void a(final String s, final int n, final String s2, final int n2, final int n3, final String s3) {
        final Vector d = this.a(s).a(n).a(s2).a(n2).d;
        if (n3 >= d.size()) {
            d.setSize(n3 + 1);
        }
        d.setElementAt(s3, n3);
    }
    
    public final void d() {
        if (this.b != null) {
            final Enumeration keys = this.b.keys();
            while (keys != null && keys.hasMoreElements()) {
                final Object nextElement;
                final Object value;
                if ((nextElement = keys.nextElement()) != null && (value = this.b.get(nextElement)) instanceof v) {
                    ((v)value).d();
                }
            }
            this.b.clear();
            this.b = null;
        }
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); ++i) {
                ((v)this.c.elementAt(i)).d();
            }
            this.c.removeAllElements();
            this.c = null;
        }
        if (this.d != null) {
            this.d.removeAllElements();
            this.d = null;
        }
        this.e = null;
        this.a = null;
    }
    
    public final v e() {
        final v v = new v(this.a, this.e);
        final Enumeration a = this.a();
        while (a.hasMoreElements()) {
            final String s = a.nextElement();
            v.b.put(s, this.a(s).e());
        }
        for (int i = 0; i < this.b(); ++i) {
            v.c.addElement(this.a(i).e());
        }
        for (int j = 0; j < this.c(); ++j) {
            v.d.addElement(this.b(j));
        }
        return v;
    }
    
    public void a(final Date f) {
        this.f = f;
    }
    
    public Date f() {
        return this.f;
    }
}
