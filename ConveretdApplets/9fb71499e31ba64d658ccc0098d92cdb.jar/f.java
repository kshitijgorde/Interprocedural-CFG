// 
// Decompiled by Procyon v0.5.30
// 

public class f implements Cloneable
{
    public int a;
    public int b;
    public int c;
    public int d;
    
    public int a() {
        return this.d << 24 | this.a << 16 | this.b << 8 | this.c;
    }
    
    public f(final int a, final int b, final int c, final int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public f(final int a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = 255;
    }
    
    public synchronized Object clone() throws CloneNotSupportedException {
        return new f(this.a, this.b, this.c, this.d);
    }
}
