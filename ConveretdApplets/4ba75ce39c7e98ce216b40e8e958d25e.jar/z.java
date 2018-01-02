import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class z
{
    protected int[] a;
    protected Dimension b;
    
    int[] a() {
        return this.a;
    }
    
    z(final Dimension b) {
        this.a = new int[b.width * b.height];
        this.b = b;
    }
    
    Dimension b() {
        return this.b;
    }
}
