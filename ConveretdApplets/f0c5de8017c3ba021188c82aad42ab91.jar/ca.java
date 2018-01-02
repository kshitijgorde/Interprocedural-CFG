import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ca extends bq
{
    public az a;
    public dy b;
    public Hashtable c;
    
    public ca(final az a) {
        this.c = new Hashtable();
        super.b = a.g.b;
        this.a = a;
    }
    
    public void a() throws ef {
    }
    
    public void a(final t t) throws ef {
        if (this.b != null) {
            this.b.b();
        }
        final String[] h = t.h("STATS_OBJECT_NAMES");
        if (h != null) {
            for (int i = 0; i < h.length; ++i) {
                this.c.put(h[i], new l());
            }
        }
        this.b = new dy(t.j("STATS_HISTORY_LENGTH"), t.h("STATS_INTERVALLS"), this.a.at());
        try {
            this.a.i().a(this.b, this.a, this.c);
        }
        catch (aw aw) {
            throw new ef("Failed initializing MDGStats: ", aw);
        }
        this.b.a();
        this.e();
        if (a0.a.i()) {
            a0.a.g(this.a.as() + "statsModule:loaded");
        }
    }
    
    public String d() {
        return "STATS_MODULE";
    }
    
    public void c() {
        this.b.b();
        this.a = null;
    }
}
