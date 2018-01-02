// 
// Decompiled by Procyon v0.5.30
// 

public class b9 extends bq
{
    public az a;
    public j b;
    
    public b9(final az a) {
        super.b = a.g.b;
        this.a = a;
    }
    
    public void a() throws ef {
        this.a(this.a.aj());
    }
    
    public void a(final t t) throws ef {
        if (this.b == null) {
            this.b = new j(t.j("KEEP_ALIVE_WORKER_THREADS"), t.j("MAX_ALIVE_WORKER_THREADS"), "SNr" + this.a.an());
        }
        else {
            this.b.a(t.j("KEEP_ALIVE_WORKER_THREADS"), t.j("MAX_ALIVE_WORKER_THREADS"));
        }
        this.e();
    }
    
    public void c() {
        if (this.b != null) {
            this.b.a();
        }
        this.a = null;
        this.b = null;
    }
    
    public String d() {
        return "NON_BLOCKING_MODULE";
    }
}
