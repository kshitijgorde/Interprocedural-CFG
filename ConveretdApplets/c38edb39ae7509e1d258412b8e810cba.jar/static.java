import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class static
{
    private Rectangle oa;
    private Color w;
    private Font x;
    private int x;
    private int y;
    private String y;
    private static String ya = "\ua963\ua950\ua94b\ua943\ua94e";
    
    public static(final Rectangle oa) {
        this.w = new Color(16384);
        this.x = new Font(static.ya, 1, 12);
        this.x = -1;
        this.y = 118;
        this.oa = oa;
    }
    
    public void a(final String y) {
        this.y = y;
        this.x = -1;
    }
    
    public void b(final Graphics graphics) {
        if (this.y != null) {
            graphics.setColor(this.w);
            graphics.setFont(this.x);
            if (this.x == -1) {
                this.x = switch.b(this.y, true, this.oa, graphics);
            }
            graphics.drawString(this.y, this.x, this.y);
        }
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEA922);
        }
        return new String(array);
    }
    
    static {
        static.ya = _(static.ya);
    }
}
