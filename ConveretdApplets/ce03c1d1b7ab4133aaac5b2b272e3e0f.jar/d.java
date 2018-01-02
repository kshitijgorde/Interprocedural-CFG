import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    static final PrintStream h;
    static double c;
    static double g;
    int d;
    int j;
    int b;
    int i;
    boolean a;
    boolean l;
    
    public void a(final Component component, final Graphics graphics) {
        if (this.a) {
            graphics.setColor(Color.green);
            graphics.drawRect(this.d - this.b / 2 - 2, this.j - this.i / 2 - 2, this.b + 5, this.i + 5);
        }
    }
    
    public void a(final boolean l) {
        this.l = l;
    }
    
    public void c(final int d, final int j) {
        this.d = d;
        this.j = j;
    }
    
    public void b(final int b, final int i) {
        this.b = b;
        this.i = i;
    }
    
    public void b() {
        this.a = true;
    }
    
    public void b(final boolean a) {
        this.a = a;
    }
    
    public boolean a(final int n, final int n2) {
        final int abs = Math.abs(n - this.d);
        final int abs2 = Math.abs(n2 - this.j);
        if (abs > this.b / 2 || abs2 <= this.i / 2) {}
        boolean b = false;
        if (abs <= this.b / 2 && abs2 <= this.i / 2) {
            b = true;
        }
        return b;
    }
    
    public void a(final h h, final b b) {
    }
    
    static {
        h = System.err;
        d.c = 90.0;
        d.g = 50.0;
    }
}
