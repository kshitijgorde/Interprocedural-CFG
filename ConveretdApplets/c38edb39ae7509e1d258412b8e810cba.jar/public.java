import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class public
{
    private Rectangle oa;
    private Image[] S;
    private Font T;
    private Color U;
    private int V;
    private int Bwc;
    private int Cwc;
    private static String ya = "\u6d27\u6d14\u6d0f\u6d07\u6d0a";
    private static String za = "\u6d0e\u6d09\u6d0a\u6d02";
    
    public public(final int v, final Image[] s, final Rectangle rectangle) {
        this.T = new Font(public.ya, 1, 11);
        this.U = Color.white;
        this.Bwc = -1;
        this.Cwc = 396;
        this.V = v;
        this.S = s;
        this.oa = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void b(final Graphics graphics) {
        graphics.drawImage(this.S[synchronized.sa[this.V] - 1], this.oa.x, this.oa.y, null);
        if (synchronized.Ia[this.V]) {
            graphics.setFont(this.T);
            graphics.setColor(this.U);
            if (this.Bwc == -1) {
                this.Bwc = switch.b(public.za, true, this.oa, graphics);
            }
            graphics.drawString(public.za, this.Bwc, this.Cwc);
        }
    }
    
    public boolean contains(final int n, final int n2) {
        return this.oa.contains(n, n2);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6d66');
        }
        return new String(array);
    }
    
    static {
        public.ya = _(public.ya);
        public.za = _(public.za);
    }
}
