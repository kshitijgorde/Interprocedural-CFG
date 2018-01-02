// 
// Decompiled by Procyon v0.5.30
// 

public class a7
{
    public az a;
    public a8 b;
    
    public a7(final az a) {
        this.a = a;
        this.b = new a8();
    }
    
    public void a() {
        this.b.a(new bp(this.a));
        this.b.a(new b9(this.a));
        this.b.a(new bu(this.a));
        if (this.a.aj().j("STATS_HISTORY_LENGTH") > 0) {
            this.b.a(new ca(this.a));
        }
        this.b.a();
    }
    
    public final br a(final String s) {
        return this.b.a(s);
    }
    
    public final void a(final t t) {
        this.b.a(t);
    }
    
    public final void b() {
        this.b.b();
        this.a = null;
    }
}
