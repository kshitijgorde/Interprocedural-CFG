// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    private i new;
    static final int try = 0;
    static final int case = 1;
    static final int for = 2;
    static c byte;
    public r do;
    public r int;
    public r a;
    r[] if;
    
    static {
        l.byte = new c(300L);
    }
    
    public l(final i new1) {
        this.do = new r.b() {
            public void a(final n n) {
                try {
                    ((o.a)this).a(((o.a)this).if() + 1.0);
                }
                catch (s s) {}
            }
            
            public String do() {
                return "Hits" + super.toString();
            }
            
            public String for() {
                return "Hits";
            }
        };
        this.int = new r.a(l.byte) {
            int p;
            
            public void a(final n n) {
                this.p += n.a;
            }
            
            public void try() {
                ((o.a)this).a(this.p / ((r.a)this).byte() / 1000.0);
                this.p = 0;
            }
            
            public String do() {
                return "Bandwidth" + super.toString();
            }
            
            public String for() {
                return "kB/s";
            }
        };
        this.a = new r.a(l.byte) {
            int o;
            
            public void a(final n n) {
                ++this.o;
            }
            
            public void try() {
                ((o.a)this).a(this.o / ((r.a)this).byte());
                this.o = 0;
            }
            
            public String do() {
                return "Hit Rate" + super.toString();
            }
            
            public String for() {
                return "Hits/s";
            }
        };
        this.if = new r[] { this.do, this.int, this.a };
        this.new = new1;
    }
    
    public r a() {
        return this.a(0);
    }
    
    public r a(final int n) {
        this.if[n].a(this.new);
        return this.if[n];
    }
    
    public static o a(final o o, final int n) {
        return a(o, a.int, 3, 0);
    }
    
    public static o a(final o o, final a a, final int n, final int n2) {
        return new e(o, a.a(n, n2)).a(a, n, n2);
    }
}
