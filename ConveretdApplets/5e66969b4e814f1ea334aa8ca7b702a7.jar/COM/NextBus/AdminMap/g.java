// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

public final class g
{
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    
    public g(final String a, final String b, final String c, final String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final String b() {
        return this.b;
    }
    
    public final String c() {
        return this.c;
    }
    
    public final String d() {
        return this.d;
    }
    
    public final String toString() {
        return this.a + "-" + this.b + "-" + this.c + "-" + this.d;
    }
    
    public final int hashCode() {
        return (((31 + ((this.a == null) ? 0 : this.a.hashCode())) * 31 + ((this.d == null) ? 0 : this.d.hashCode())) * 31 + ((this.b == null) ? 0 : this.b.hashCode())) * 31 + ((this.c == null) ? 0 : this.c.hashCode());
    }
    
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final g g = (g)o;
        if (this.a == null) {
            if (g.a != null) {
                return false;
            }
        }
        else if (!this.a.equals(g.a)) {
            return false;
        }
        if (this.d == null) {
            if (g.d != null) {
                return false;
            }
        }
        else if (!this.d.equals(g.d)) {
            return false;
        }
        if (this.b == null) {
            if (g.b != null) {
                return false;
            }
        }
        else if (!this.b.equals(g.b)) {
            return false;
        }
        if (this.c == null) {
            if (g.c != null) {
                return false;
            }
        }
        else if (!this.c.equals(g.c)) {
            return false;
        }
        return true;
    }
}
