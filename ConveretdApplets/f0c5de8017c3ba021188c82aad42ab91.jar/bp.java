import com.is_teledata.cache.LRUCache;

// 
// Decompiled by Procyon v0.5.30
// 

public class bp extends bq
{
    private az a;
    private bs b;
    
    public bp() {
    }
    
    public bp(final az az) {
        this.a(az);
    }
    
    public void a(final az a) {
        this.a = a;
        super.b = a.g.b;
    }
    
    public void a() throws ef {
    }
    
    public void a(final t t) throws ef {
        String a = null;
        try {
            a = t.a("CACHE_IMPLEMENTATION");
            if (this.b == null || !a.equals(this.b.getClass().getName())) {
                this.b = (bs)Class.forName(a).newInstance();
            }
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.b(this.a.as() + " can not create instance of Cache \"" + a + "\". Going to create a LRUCache ...", ex);
            }
            this.b = new LRUCache();
        }
        this.b.a(t.j("CACHE_MAX_SIZE"), t.j("CACHE_MAX_ELEMENTS"));
        if (a0.a.i()) {
            a0.a.g(this.a.as() + " initialized MDGObject cache to " + this.b.getClass().getName() + ":" + this.b.a + ":" + this.b.b);
        }
        this.e();
    }
    
    public bs b() {
        return this.b;
    }
    
    public void c() {
        if (this.b != null) {
            this.b.a();
        }
        this.a = null;
    }
    
    public String d() {
        return "CACHE_MODULE";
    }
}
