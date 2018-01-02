import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class p
{
    private int[] ua;
    private Font va;
    private Color[] wa;
    private Rectangle[] xa;
    private int ya;
    private Rectangle za;
    private Rectangle Aa;
    private static String e = "\u0edf\u0eec\u0ef7\u0eff\u0ef2";
    
    public p(final Color[] array, final int[] ua) {
        this.ya = -1;
        this.za = new Rectangle(401, 16, 60, 262);
        this.Aa = new Rectangle(427, 290, 10, 10);
        this.ua = ua;
        this.b(array);
        this.va = new Font(p.e, 1, 12);
        this._();
    }
    
    public void b(final Color[] wa) {
        this.wa = wa;
    }
    
    public void b(final int[] ua) {
        this.ua = ua;
    }
    
    public void _() {
        this.xa = new Rectangle[this.wa.length];
        for (int i = 0; i < this.wa.length; ++i) {
            this.xa[i] = new Rectangle((i < 8) ? (this.za.x + 4) : (this.za.x + 38), this.za.y + 3 + i % 8 * 34, 19, 19);
        }
    }
    
    public void _(final Graphics graphics) {
        graphics.setFont(this.va);
        for (int i = 0; i < this.wa.length; ++i) {
            graphics.setColor(this.wa[i]);
            graphics.fillRect(this.xa[i].x, this.xa[i].y, this.xa[i].width, this.xa[i].height);
            graphics.setColor(this.a(this.wa[i]));
            final String string = Integer.toString(this.ua[i]);
            graphics.drawString(string, this.za.x + this.a(string, i), this.za.y + 18 + i % 8 * 34);
        }
        if (this.ya != -1) {
            graphics.setColor(this.wa[this.ya]);
        }
        else {
            graphics.setColor(Color.white);
        }
        graphics.fillRect(this.Aa.x, this.Aa.y, this.Aa.width, this.Aa.height);
    }
    
    public boolean inside(final int n, final int n2) {
        return this.za.inside(n, n2);
    }
    
    public void a(final int n, final int n2) {
        for (int i = 0; i < this.wa.length; ++i) {
            if (this.xa[i].inside(n, n2)) {
                this.ya = i;
                return;
            }
        }
    }
    
    private final int a(final String s, final int n) {
        if (s.length() == 1) {
            if (n < 8) {
                return 10;
            }
            return 44;
        }
        else {
            if (n < 8) {
                return 7;
            }
            return 41;
        }
    }
    
    private final Color a(final Color color) {
        final int n = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        if (color.getGreen() > 250) {
            return Color.black;
        }
        if (n > 125) {
            return Color.black;
        }
        return Color.white;
    }
    
    public int b() {
        if (this.ya == -1) {
            return -16777216;
        }
        final Color color = this.wa[this.ya];
        return 0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }
    
    public int l() {
        return this.ya;
    }
    
    public void reset() {
        this.ya = -1;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u0e9e');
        }
        return new String(array);
    }
    
    static {
        p.e = a(p.e);
    }
}
