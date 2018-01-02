import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends l
{
    public String i;
    public String h;
    public String g;
    public String f;
    public Font e;
    public int d;
    public String b;
    public String a;
    
    public a(final String i, final String h, final String g, final Color b9, final Color b10, final String s, final int n, final String b11, final String a) {
        super(100, 20);
        this.b = b11;
        this.a = a;
        this.i = i;
        this.h = h;
        this.g = g;
        this.e = new Font(s, 1, n);
        this.d = this.getFontMetrics(this.e).getHeight();
        super.b9 = b9;
        super.b8 = b10;
        super.setFont(this.e);
        this.resize(100, this.d + 5);
        this.a();
    }
    
    public final void d(final String i) {
        this.i = i;
        this.a();
    }
    
    public final void c(final String h) {
        this.h = h;
        this.a();
    }
    
    public final void b(final String g) {
        this.g = g;
        this.a();
    }
    
    public final void a() {
        this.f = "#" + this.i + ": " + this.h + " " + this.a + ", " + this.b + " " + this.g;
        super.g();
        super.a6(this.f);
        super.repaint();
    }
}
