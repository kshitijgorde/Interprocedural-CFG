import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class dh
{
    public int a;
    public int b;
    public int c;
    public int d;
    public Color e;
    
    public dh(final int c, final int d) {
        this.c = c;
        this.d = d;
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.e);
        graphics.fillRect(this.a, this.b, this.c, this.d);
        graphics.setColor(Color.black);
        graphics.drawRect(this.a, this.b, this.c, this.d);
    }
    
    public boolean a(final int n, final int n2) {
        return n > this.a & n <= this.a + this.c & (n2 > this.b & n2 <= this.b + this.d);
    }
}
