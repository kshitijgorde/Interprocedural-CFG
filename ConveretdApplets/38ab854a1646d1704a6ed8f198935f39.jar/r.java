// 
// Decompiled by Procyon v0.5.30
// 

public interface r extends o, t
{
    void a(final i p0);
    
    public abstract static class b extends o.a implements r
    {
        private boolean m;
        private i l;
        
        public b() {
            this.m = false;
        }
        
        public void a(final i l) {
            if (!this.m) {
                l.a(this);
                this.l = l;
                this.m = true;
            }
        }
        
        public String toString() {
            return this.m ? (" on " + this.l.a()) : "";
        }
        
        public abstract void a(final n p0);
    }
    
    public abstract static class a extends b implements c.a
    {
        c n;
        
        public a(final c n) {
            this.n = n;
        }
        
        public void a(final i i) {
            super.a(i);
            this.n.a((c.a)this);
        }
        
        public double byte() {
            return this.n.a();
        }
        
        public abstract void try();
    }
}
