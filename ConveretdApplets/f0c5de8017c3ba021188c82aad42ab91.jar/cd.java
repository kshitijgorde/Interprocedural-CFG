// 
// Decompiled by Procyon v0.5.30
// 

public class cd implements e
{
    private az a;
    
    public cd(final az a) {
        this.a = a;
    }
    
    public void produce() {
        final long a = bz.a(this.a);
        if (a != Long.MIN_VALUE) {
            synchronized (this.a.u) {
                this.a.u = new Long(a);
            }
        }
        if (!this.a.al()) {
            this.a.at().a(this, System.currentTimeMillis() + this.a.aj().k("CACHE_ADJUST_EXPIRY_INTERVAL"));
        }
    }
}
