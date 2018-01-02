import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class m
{
    String a;
    int b;
    int c;
    int d;
    int e;
    Color f;
    int g;
    boolean h;
    boolean i;
    boolean j;
    
    m() {
        this.g = -1;
        this.e = -1;
        this.j = false;
    }
    
    public final void a(final String a, final int b, final int c, final Color f, final int g, final int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
        this.g = g;
        this.h = true;
        this.e = 0;
        this.i = false;
        this.j = false;
        this.d = d;
    }
    
    public final void a(final String a, final int c, final Color f, final int g, final int d) {
        this.a = a;
        this.c = c;
        this.f = f;
        this.g = g;
        this.h = true;
        this.e = 0;
        this.i = true;
        this.j = false;
        this.d = d;
    }
    
    public final void a(final Graphics graphics, final Font font) {
        graphics.setColor(this.f);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int b = this.i ? (fps.C - fontMetrics.stringWidth(this.a) / 2) : this.b;
        int c = this.c;
        for (int i = 0; i < this.e; ++i) {
            graphics.drawString(this.a.substring(i, i + 1), b, c);
            b += fontMetrics.stringWidth(this.a.substring(i, i + 1));
            if (this.d != -1 && b > this.d - 30 && this.a.charAt(i) == ' ') {
                c += 15;
                b = this.b;
            }
        }
        Label_0233: {
            m m;
            int length;
            if (this.e < this.a.length() && !this.j) {
                ++this.e;
                if (this.e >= this.a.length() || this.j) {
                    break Label_0233;
                }
                m = this;
                length = this.e + 1;
            }
            else {
                this.j = true;
                m = this;
                length = this.a.length();
            }
            m.e = length;
        }
        if (this.g > -1 && this.g != 12345 && this.e >= this.a.length() - 1) {
            --this.g;
            if (this.g < 1) {
                this.h = false;
            }
        }
    }
}
