import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class j
{
    static Graphics a;
    Font b;
    Font a;
    Color b;
    Color a;
    String c;
    String b;
    String a;
    int c;
    int b;
    int d;
    boolean a;
    FontMetrics a;
    int a;
    
    public j(final Font b, final Color b2, final String c) {
        this.a = false;
        this.a = 0;
        this.b = b;
        this.b = b2;
        this.c = c;
        this.a = j.a.getFontMetrics(b);
        this.c = this.a.stringWidth(c.concat("  ")) + 15;
    }
    
    public void a(final String s, int n) {
        this.b.getFamily();
        this.b.getSize();
        this.b = new Font(s, this.b.getStyle(), n);
        j j = this;
        if (!d.a) {
            if (this.a == -1) {
                n -= n / 4;
            }
            this.a = new Font(s, this.a.getStyle(), n);
            j = this;
        }
        j.a();
    }
    
    public j(final Font b, final Color b2, final Font a, final Color a2, final String c, final String b3, final String a3, final int a4) {
        this.a = false;
        this.a = 0;
        this.b = b;
        this.a = a;
        this.b = b2;
        this.a = a2;
        this.c = c;
        this.a = true;
        this.a = a4;
        this.b = b3;
        this.a = a3;
        this.a();
    }
    
    void a() {
        this.a = j.a.getFontMetrics(this.b);
        int n = 0;
        j j = this;
        if (!d.a) {
            if (this.a > 0) {
                n = 17;
            }
            this.c = this.a.stringWidth(this.c) + 15 + n;
            this.b = this.c + j.a.getFontMetrics(this.a).stringWidth(this.b) + 10;
            j = this;
        }
        j.d = this.b + this.a.stringWidth(this.a);
    }
}
