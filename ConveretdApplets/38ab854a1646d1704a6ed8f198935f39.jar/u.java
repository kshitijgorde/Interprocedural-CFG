// 
// Decompiled by Procyon v0.5.30
// 

public interface u
{
    void a(final w p0);
    
    double a(final int p0) throws s;
    
    int do();
    
    String a();
    
    String if();
    
    public abstract static class a implements u
    {
        private w if;
        double[] a;
        
        public a(final int n) {
            this.if = new w() {
                public void new() {
                }
            };
            this.a = new double[n];
        }
        
        public void a(final w w) {
            this.if = new w() {
                private final /* synthetic */ w val$b = this.if;
                
                public void new() {
                    w.new();
                    this.val$b.new();
                }
            };
        }
        
        public int do() {
            return this.a.length;
        }
        
        public double a(final int n) throws s {
            return this.a[n];
        }
        
        public void for() {
            this.if.new();
        }
        
        public abstract String a();
        
        public abstract String if();
    }
}
