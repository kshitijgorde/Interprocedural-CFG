import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends d
{
    Color m;
    Color e;
    Color f;
    Color n;
    
    void a(final Color n) {
        this.n = n;
    }
    
    public void a(final Component component, final Graphics graphics) {
        final int n = this.d - this.b / 2;
        final int n2 = this.j - this.i / 2;
        graphics.setColor(this.m);
        graphics.drawLine(n, n2, n + 5, n2);
        graphics.drawLine(n, n2, n, n2 + 3);
        graphics.drawLine(n, n2 + this.i - 4, n + 5, n2 + this.i - 4);
        graphics.drawLine(n, n2 + this.i - 4, n, n2 + this.i - 1);
        graphics.setColor(this.e);
        graphics.fillRect(n + 1, n2 + 1, 5, 3);
        graphics.fillRect(n + 1, n2 + this.i - 3, 5, 3);
        graphics.setColor(this.f);
        graphics.drawLine(n + 6, n2, n + 6, n2 + 4);
        graphics.drawLine(n, n2 + 4, n + 6, n2 + 4);
        graphics.drawLine(n + 6, n2 + this.i - 4, n + 6, n2 + this.i);
        graphics.drawLine(n, n2 + this.i, n + 6, n2 + this.i);
        graphics.setColor(this.n);
        final int n3 = n2 + 6;
        for (int n4 = n2 + this.i - 5, i = n3; i < n4; i += 2) {
            graphics.drawLine(n + 1, i, n + 5, i);
        }
        super.a(component, graphics);
    }
    
    public void a(final h h, final b b) {
        if (this.a(h.d, h.j)) {
            b.d = this;
            b.b = true;
            b.a = true;
            b.c = "Yay!\nYou hit the target!\nTry another level...";
        }
    }
    
    private final void a() {
        this.m = new Color(255, 204, 0);
        this.e = new Color(255, 153, 0);
        this.f = new Color(255, 102, 0);
        this.n = new Color(0, 0, 204);
    }
    
    public m(final int d, final int j, final int n, final int n2) {
        this.a();
        this.d = d;
        this.j = j;
        this.b(n, n2);
        this.a(new Color(0, 0, 204));
    }
}
