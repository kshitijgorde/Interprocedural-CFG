import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemcp extends Canvas
{
    abljema a;
    abljemf b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    byte[] j;
    boolean k;
    boolean l;
    
    abljemcp(final abljema a) {
        this.hide();
        this.a = a;
        this.b = this.a.fb;
        this.j = new byte[1];
        this.k = false;
        this.l = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.k) {
            final int n = this.i - this.b.a5 - ((this.b.a4 < 10) ? 2 : 3);
            if (this.b.bu != null) {
                graphics.setColor(this.b.bu);
            }
            else {
                graphics.setColor(this.b.bx);
            }
            if (this.getBackground() != this.b.bz) {
                this.setBackground(this.b.bz);
            }
            graphics.setFont(this.b.y);
            graphics.drawBytes(this.j, 0, 1, 0, n);
            if (!this.l) {
                this.l = true;
                this.hide();
                this.show();
            }
        }
    }
    
    public void a() {
        final int a7 = this.b.a7;
        final int a8 = this.b.a6;
        final int ba = this.b.ba;
        final int a9 = this.b.a8;
        final int a10 = this.b.a5;
        final int bb = this.b.bb;
        final int bc = this.b.bc;
        final int a11 = this.b.a4;
        final int dl = this.b.dl;
        final int dm = this.b.dm;
        this.c = this.a.e5;
        this.d = this.a.e6;
        this.e = (this.c - 1) * this.a.es + (this.d - 1);
        if (this.e < 0) {
            this.e = 0;
        }
        if (this.e > this.a.eu - 1) {
            this.e = this.a.eu - 1;
        }
        if (this.k) {
            this.hide();
        }
        this.j[0] = this.a.el[this.e];
        int n;
        for (n = 0; n < this.a.fw && (this.a.fl[n] > this.e || this.a.fl[n] + this.a.fk[n] - 1 < this.e); ++n) {}
        if (n < this.a.fw && (this.a.fo[n] & 0x7) == 0x7) {
            this.j[0] = 32;
        }
        this.h = a7;
        this.i = a8;
        this.f = (this.d - 1) * a7 + bb + dl;
        this.g = (this.c - 1) * a8 + a10 + bc + dm;
        if (!this.b.z && this.b.ap != null) {
            this.i -= this.b.ap.z;
            this.g += this.b.ap.z;
        }
        this.reshape(this.f, this.g, this.h, this.i);
        this.k = true;
        if (this.c == 1 && this.d == 1 && this.a.i > 0) {
            return;
        }
        this.show();
    }
    
    public void b() {
        if (this.k) {
            this.k = false;
            this.hide();
        }
    }
}
