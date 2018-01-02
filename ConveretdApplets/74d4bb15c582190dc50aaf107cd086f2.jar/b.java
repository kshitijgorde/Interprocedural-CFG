import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private Font K;
    private Font L;
    private Color M;
    private String N;
    private String O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private Rectangle T;
    private static String Ma = "\u480a\u4839\u4822\u482a\u4827";
    private static String Na = "\u480d\u486b\u482e\u486b\u482a\u486b\u483f\u486b\u483e\u486b\u4839\u486b\u482e\u486b\u482f\u486b\u486b\u481c\u486b\u4824\u486b\u4839\u486b\u482f\u486b\u4871\u486b";
    private static String Oa = "\u4869\u486b";
    
    public b(final Rectangle t) {
        this.K = new Font(b.Ma, 1, 16);
        this.L = new Font(b.Ma, 1, 14);
        this.M = new Color(16763904);
        this.N = b.Na;
        this.P = -1;
        this.Q = 315;
        this.R = -1;
        this.S = 341;
        this.T = t;
    }
    
    public void _(final String s) {
        if (s != null) {
            final int length = s.length();
            final StringBuffer sb = new StringBuffer(length * 2 + 3);
            sb.append(b.Oa);
            for (int i = 0; i < length; ++i) {
                sb.append(s.charAt(i)).append(' ');
            }
            sb.append('\"');
            this.O = sb.toString();
            this.R = -1;
            return;
        }
        this.O = null;
    }
    
    public void _(final Graphics graphics) {
        if (this.O != null) {
            graphics.setColor(this.M);
            graphics.setFont(this.K);
            if (this.P == -1) {
                this.P = d._(this.N, true, this.T, graphics);
            }
            graphics.drawString(this.N, this.P, this.Q);
            graphics.setFont(this.L);
            if (this.R == -1) {
                this.R = d._(this.O, true, this.T, graphics);
            }
            graphics.drawString(this.O, this.R, this.S);
        }
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u484b');
        }
        return new String(array);
    }
    
    static {
        b.Ma = a(b.Ma);
        b.Na = a(b.Na);
        b.Oa = a(b.Oa);
    }
}
