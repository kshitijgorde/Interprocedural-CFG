// 
// Decompiled by Procyon v0.5.30
// 

package z;

final class W extends Throwable
{
    private int a;
    
    public W(final int a) {
        this.a = a;
    }
    
    public final String toString() {
        return "Parsing error on line " + this.a;
    }
}
