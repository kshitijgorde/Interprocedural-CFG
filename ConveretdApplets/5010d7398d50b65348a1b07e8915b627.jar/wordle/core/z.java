// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import wordle.core.e.b;

public enum z
{
    a("HORIZONTAL", 0) {
        private a g;
        
        {
            this.g = new a(0.0);
        }
        
        public final double a(final C c) {
            return this.g.a();
        }
    }, 
    b("MOSTLY_HORIZONTAL", 1) {
        private a g;
        
        {
            this.g = new a(0.25);
        }
        
        public final double a(final C c) {
            return this.g.a();
        }
    }, 
    g("LONG_HORIZONTAL_LIKELY", 2) {
        private a g;
        
        {
            this.g = new a(0.5);
        }
        
        public final double a(final C c) {
            if (c.b.length() > 6) {
                return 0.0;
            }
            return this.g.a();
        }
    }, 
    c("HALF_AND_HALF", 3) {
        private a g;
        
        {
            this.g = new a(0.5);
        }
        
        public final double a(final C c) {
            return this.g.a();
        }
    }, 
    d("MOSTLY_VERTICAL", 4) {
        private a g;
        
        {
            this.g = new a(0.75);
        }
        
        public final double a(final C c) {
            return this.g.a();
        }
    }, 
    e("VERTICAL", 5) {
        private a g;
        
        {
            this.g = new a(1.0);
        }
        
        public final double a(final C c) {
            return this.g.a();
        }
    }, 
    f("ANY_WHICH_WAY", 6) {
        l(final String s, final int n) {
        }
        
        public final double a(final C c) {
            return (wordle.core.e.b.a() - 0.5) * 3.141592653589793;
        }
    };
    
    static {
        h = new z[] { z.a, z.b, z.g, z.c, z.d, z.e, z.f };
    }
    
    private z(final String s, final int n, final byte b) {
    }
    
    public abstract double a(final C p0);
}
