import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Point
{
    int a;
    int b;
    
    a() {
    }
    
    a(final int a, final int b, final int n, final int n2) {
        super(n, n2);
        this.a = a;
        this.b = b;
    }
    
    a(final a a) {
        this(a.a, a.b, a.x, a.y);
    }
}
