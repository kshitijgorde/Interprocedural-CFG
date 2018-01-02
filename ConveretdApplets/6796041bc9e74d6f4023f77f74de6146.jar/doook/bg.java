// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bg extends cF
{
    protected bz a;
    protected bx a;
    public boolean b;
    
    public void a(final bz a) {
        this.d(a.toString());
        this.a = a;
    }
    
    public void a(final bx a) {
        this.d(a.toString());
        this.a = a;
    }
    
    public bg(final int n, final String s) {
        super(n, s);
        this.b = true;
    }
    
    public bg(final int n, final bz a) {
        this(n, a.toString());
        this.a = a;
    }
    
    public bg(final int n, final bx a) {
        this(n, a.toString());
        this.a = a;
    }
}
