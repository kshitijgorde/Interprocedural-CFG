// 
// Decompiled by Procyon v0.5.30
// 

class dd
{
    int a;
    int b;
    private final db c;
    
    dd(final db c) {
        this.c = c;
    }
    
    dd(final db c, final int a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public int hashCode() {
        return this.a + this.b;
    }
    
    public boolean equals(final Object o) {
        final dd dd = (dd)o;
        return (dd.a == this.a && dd.b == this.b) || (dd.a == this.b && dd.b == this.a);
    }
}
