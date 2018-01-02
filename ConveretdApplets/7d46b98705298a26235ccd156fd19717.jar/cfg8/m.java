// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

class m
{
    final g a;
    private char b;
    private int c;
    
    m(final g a, final char b) {
        this.a = a;
        this.b = '\0';
        this.c = 0;
        this.b = b;
        this.c = 0;
    }
    
    protected char a() {
        return this.b;
    }
    
    protected int b() {
        return this.c;
    }
    
    protected void a(final int c) {
        this.c = c;
    }
}
