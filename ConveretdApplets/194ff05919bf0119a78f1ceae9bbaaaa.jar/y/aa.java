// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class aa
{
    public int a;
    public int b;
    
    public aa(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public final boolean equals(final Object o) {
        return o instanceof aa && ((aa)o).a == this.a && ((aa)o).b == this.b;
    }
    
    public final int hashCode() {
        return (this.a << 16) + this.b;
    }
    
    public final String toString() {
        return this.a + "," + this.b;
    }
}
