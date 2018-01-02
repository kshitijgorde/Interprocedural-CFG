// 
// Decompiled by Procyon v0.5.30
// 

public interface o
{
    void a(final k p0);
    
    double if() throws s;
    
    String do();
    
    String for();
    
    public abstract static class a implements o
    {
        private k b;
        private double void;
        
        public a() {
            this.b = new k() {
                public void a(final double n) {
                }
            };
            this.void = 0.0;
        }
        
        public void a(final k k) {
            this.b = new k() {
                private final /* synthetic */ k val$b = this.b;
                
                public void a(final double n) {
                    k.a(n);
                    this.val$b.a(n);
                }
            };
        }
        
        public double if() throws s {
            return this.void;
        }
        
        public void int() {
            try {
                this.b.a(this.if());
            }
            catch (s s) {}
        }
        
        public void a(final double void1) {
            this.void = void1;
            this.int();
        }
        
        public abstract String do();
        
        public abstract String for();
    }
}
