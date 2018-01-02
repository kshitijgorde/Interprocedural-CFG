import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class while
{
    private String ba;
    private Font ca;
    private Color da;
    private Rectangle T;
    private int y;
    private static String Ma = "\ucf2f\ucf1c\ucf07\ucf0f\ucf02";
    
    public while(final Rectangle t) {
        this.ca = new Font(while.Ma, 1, 11);
        this.da = new Color(13434777);
        this.T = t;
        this.y = -1;
    }
    
    public void a(final String ba) {
        this.ba = ba;
    }
    
    public void _(final Graphics graphics) {
        if (this.ba != null) {
            graphics.setFont(this.ca);
            graphics.setColor(this.da);
            if (this.y == -1) {
                this.y = d._(this.ba, false, this.T, graphics);
            }
            graphics.drawString(this.ba, d._(this.ba, true, this.T, graphics), this.y);
        }
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFECF6E);
        }
        return new String(array);
    }
    
    static {
        while.Ma = a(while.Ma);
    }
}
