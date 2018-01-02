// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

enum B
{
    a("UPPER", 0) {
        c(final String s, final int n) {
        }
        
        final String a(final String s) {
            return s.toUpperCase();
        }
    }, 
    b("LOWER", 1) {
        d(final String s, final int n) {
        }
        
        final String a(final String s) {
            return s.toLowerCase();
        }
    };
    
    static {
        c = new B[] { B.a, B.b };
    }
    
    private B(final String s, final int n, final byte b) {
    }
    
    abstract String a(final String p0);
}
