import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    private Font U;
    private Color V;
    private Rectangle T;
    private int W;
    private int X;
    private static String Ma = "\ud332\ud301\ud31a\ud312\ud31f";
    private static String Na = "\ud331\ud33f\ud335\ud331\ud33a\ud33f\ud33f";
    private static String Oa = "\ud323\ud33f\ud332\ud32a\ud336\ud321";
    
    public a(final Rectangle t) {
        this.U = new Font(a.Ma, 1, 11);
        this.V = new Color(6736896);
        this.T = t;
    }
    
    public void _(final int n) {
        this.W += n * 10;
    }
    
    public void a(final int n) {
        this.X += n * 10;
    }
    
    public void _(final Graphics graphics) {
        graphics.setFont(this.U);
        graphics.setColor(this.V);
        graphics.drawString(a.Na, this.T.x + 10, this.T.y + 17);
        graphics.drawString(a.Oa, this.T.x + 10, this.T.y + 35);
        graphics.drawString(String.valueOf(this.W), this.T.x + 70, this.T.y + 17);
        graphics.drawString(String.valueOf(this.X), this.T.x + 70, this.T.y + 35);
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFED373);
        }
        return new String(array);
    }
    
    static {
        a.Ma = a(a.Ma);
        a.Na = a(a.Na);
        a.Oa = a(a.Oa);
    }
}
