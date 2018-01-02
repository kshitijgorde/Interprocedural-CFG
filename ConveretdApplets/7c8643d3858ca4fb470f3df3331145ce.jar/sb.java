import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class sb extends Thread
{
    private final esChat a;
    boolean b;
    private int c;
    public int d;
    public int e;
    public d f;
    
    sb(final esChat a, final d f) {
        this.a = a;
        this.b = true;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = f;
    }
    
    static int a(final sb sb) {
        return sb.c;
    }
    
    public int a() {
        return this.c;
    }
    
    public void a(final int n) {
        sb sb = this;
        if (!d.r) {
            if (this.c <= 0) {
                return;
            }
            sb = this;
        }
        sb.c(this.c - 1);
    }
    
    public void b(final int n) {
        sb sb = this;
        if (!d.r) {
            if (this.c >= this.f.n.length()) {
                return;
            }
            sb = this;
        }
        sb.c(this.c + 1);
    }
    
    public void a(final Graphics graphics) {
        final boolean b = this.b;
        if (!d.r) {
            if (!b) {
                return;
            }
            final boolean h = this.f.h;
        }
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(this.e + 1, 4, this.e + 1, 16);
            graphics.setColor(Color.gray);
            graphics.drawLine(this.e + 2, 4, this.e + 2, 16);
        }
    }
    
    public void run() {
        final boolean r = d.r;
        try {
        Label_0054_Outer:
            while (true) {
                Thread.sleep(500L);
                this.b ^= true;
                if (!this.f.f) {
                    this.b = false;
                }
                final boolean h = this.f.h;
                while (true) {
                    Label_0061: {
                        if (r) {
                            break Label_0061;
                        }
                        if (!h) {
                            this.b = false;
                        }
                        final boolean h2 = this.f.h;
                    }
                    if (h) {
                        this.f.repaint();
                        if (!r) {
                            continue Label_0054_Outer;
                        }
                        continue;
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void c(final int c) {
        this.d = this.c;
        this.c = c;
    }
}
