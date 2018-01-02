import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a9 extends Vector implements a0
{
    private static int a;
    private az b;
    private Hashtable c;
    private Hashtable d;
    private Vector e;
    private boolean f;
    private int g;
    private int h;
    
    public a9(final int n) {
        super(n);
        this.c = new Hashtable();
        this.d = new Hashtable();
        this.e = new Vector();
        this.d();
    }
    
    public a9(final az b) {
        this.c = new Hashtable();
        this.d = new Hashtable();
        this.e = new Vector();
        this.b = b;
        this.f = b.aj().i("COLLECT_DUPLICATES");
    }
    
    private final void d() {
        this.g = ++a9.a;
        if (a0.a.i()) {
            a0.a.g(((this.b != null) ? this.b.as() : "") + " created requests " + this.b());
        }
    }
    
    public final Vector a() {
        return this.e;
    }
    
    public final int a(final a3 a3) {
        ++this.h;
        final a3 b = a3.b();
        if (b != null) {
            if (a3.g != null) {
                a3.a(a3, b);
                a3.b(a3);
                this.e.addElement(a3);
            }
            else {
                a3.b(b);
            }
            return 2;
        }
        final a4 a4;
        if (this.f && (a4 = this.c.get(a3.l())) != null && a4 instanceof a3) {
            ((a3)a4).a(a3);
            if (a0.a.k()) {
                a0.a.i(this.b.as() + " duplicate: " + a3);
            }
            final a1 i = this.b.i();
            ++i.c;
            return 1;
        }
        this.c.put(a3.l(), a3);
        if (a3.g != null) {
            this.e.addElement(a3);
        }
        this.addElement(a3);
        return 3;
    }
    
    public final String b() {
        return this.g + ":" + this.size();
    }
    
    public final int c() {
        return this.h;
    }
}
