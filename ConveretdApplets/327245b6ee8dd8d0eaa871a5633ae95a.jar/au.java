import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class au extends c
{
    static as a;
    Vector b;
    private int c;
    
    c a(final aa aa) {
        return aa.f;
    }
    
    public au() {
        this.b = new Vector();
        this.c = -1;
        this.a(0);
    }
    
    public au(final au au) {
        this.b = new Vector();
        this.c = -1;
        this.a(au);
    }
    
    void a(final int n) {
        super.a = 8;
        this.b = new Vector(n);
        this.f(n);
    }
    
    void a(final au au) {
        super.a = 8;
        if (au == null || au.b.size() == 0) {
            return;
        }
        if (au.b.size() == 1 && au.b(0).a == 3) {
            this.a((int)au.b(0).af());
            return;
        }
        final int size = au.b.size();
        (this.b = new Vector(size)).setSize(size);
        int n = 0;
        while (true) {
            Label_0113: {
                if (!c.l) {
                    break Label_0113;
                }
                this.b.setElementAt(au.b.elementAt(n), n);
                ++n;
            }
            if (n >= size) {
                return;
            }
            continue;
        }
    }
    
    void a(final as as, final c e) {
        if (as.a.equals("")) {
            return;
        }
        if (!super.b) {
            this.ad();
        }
        if (as.a.equals("__proto__")) {
            super.e = e;
        }
        if (super.d.a(as, false) != null && as.a.equals("length")) {
            this.f(Math.abs((int)e.af()));
            return;
        }
        final char char1 = as.a.charAt(0);
        if (char1 > '/' && char1 < ':') {
            final int j = d.j(as.a);
            if (as.a.equals(aq.a.format(j))) {
                if (j + 1 > this.b.size()) {
                    this.f(j + 1);
                }
                this.b.setElementAt(e, j);
                return;
            }
        }
        super.d.a(as, e);
    }
    
    c d(final c c, final boolean b) {
        if (c.a == 3) {
            final aq aq = (aq)c;
            final int n = (int)aq.c;
            if (n == aq.c) {
                return this.b(n);
            }
        }
        return super.d(c, b);
    }
    
    c b(final as as, final boolean b) {
        if (as.a.equals("length")) {
            return new aq(this.b.size());
        }
        final char char1 = as.a.charAt(0);
        if (char1 > '/' && char1 < ':') {
            final int j = d.j(as.a);
            if (as.a.equals(aq.a.format(j))) {
                return this.b(j);
            }
        }
        return super.b(as, b);
    }
    
    c b(final int n) {
        return (n >= 0 && n < this.b.size()) ? this.b.elementAt(n) : d.e;
    }
    
    void a() {
        this.f(0);
        super.d.a();
    }
    
    as ae() {
        if (++this.c >= this.b.size()) {
            if (super.d != null) {
                final String b = super.d.b();
                if (b != null) {
                    return new as(b);
                }
            }
            this.c = -1;
            return null;
        }
        return new as(aq.a.format(this.c));
    }
    
    public int a(c e) {
        if (e == null) {
            e = d.e;
        }
        this.b.addElement(e);
        return this.b.size();
    }
    
    public c e() {
        if (this.b.isEmpty()) {
            return null;
        }
        final c c = this.b.elementAt(0);
        this.b.removeElementAt(0);
        return c;
    }
    
    c h(final int n) {
        return new as(this.toString());
    }
    
    public String toString() {
        final boolean l = c.l;
        String s = "";
        if (this.b.isEmpty()) {
            return s;
        }
        final int size = this.b.size();
        int n = 0;
        while (true) {
            while (true) {
                Label_0084: {
                    if (!l) {
                        break Label_0084;
                    }
                    new StringBuffer().append(s + this.b.elementAt(n)).append(",").toString();
                    final String string;
                    s = string;
                    ++n;
                }
                if (n < size - 1) {
                    continue;
                }
                break;
            }
            final String string = s + this.b.elementAt(size - 1);
            if (!l) {
                return string;
            }
            continue;
        }
    }
    
    protected Object clone() {
        final au au = new au();
        au.b = (Vector)this.b.clone();
        return au;
    }
    
    private void f(final int size) {
        final int size2 = this.b.size();
        if (size2 != size) {
            this.b.setSize(size);
            for (int i = size2; i < size; ++i) {
                this.b.setElementAt(d.e, i);
            }
        }
    }
    
    static {
        au.a = new as("length");
    }
}
