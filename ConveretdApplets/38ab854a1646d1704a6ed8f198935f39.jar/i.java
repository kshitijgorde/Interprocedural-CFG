// 
// Decompiled by Procyon v0.5.30
// 

public interface i
{
    void a(final t p0);
    
    String a();
    
    public abstract static class a implements i
    {
        private t else;
        
        public a() {
            this.else = new t() {
                public void a(final n n) {
                }
            };
        }
        
        public void a(final t t) {
            this.else = new t() {
                private final /* synthetic */ t val$a = this.else;
                
                public void a(final n n) {
                    t.a(n);
                    this.val$a.a(n);
                }
            };
        }
        
        public void if(final n n) {
            this.else.a(n);
        }
        
        public abstract String a();
    }
    
    public static class b extends a implements t
    {
        private g long;
        private i null;
        
        public b(final g long1, final i null) {
            this.long = long1;
            (this.null = null).a(this);
        }
        
        public void a(final n n) {
            if (this.long.a(n)) {
                ((a)this).if(n);
            }
        }
        
        public String a() {
            return String.valueOf(this.long.toString()) + " on " + this.null.a();
        }
    }
}
