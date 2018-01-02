// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

public class EmuMenuOption
{
    public int a;
    public int b;
    public int c;
    public int d;
    private int e;
    public int f;
    public StyleEventArg g;
    public String h;
    
    public EmuMenuOption(final int e) {
        this.e = e;
    }
    
    public void a(final int a) {
        this.a = a;
        this.b = this.a / this.e + 1;
        this.c = this.a % this.e + 1;
    }
    
    public void a(final int b, final int c) {
        this.b = b;
        this.c = c;
        this.a = (this.b - 1) * this.e + (this.c - 1);
    }
    
    public void b(final int n) {
        this.a(this.b, n);
    }
}
