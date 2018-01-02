import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class volatile
{
    public static final int ea = 0;
    public static final int fa = 1;
    public static final int ga = 2;
    private Font ha;
    private FontMetrics ia;
    private Color M;
    private Rectangle T;
    private Image ja;
    private Image ka;
    private int la;
    private int ma;
    private boolean na;
    private boolean oa;
    private boolean pa;
    private String[][] qa;
    private String O;
    private int[] ra;
    private int y;
    private static String Ma = "\u3bef\u3bdc\u3bc7\u3bcf\u3bc2";
    
    public volatile(final Image ja, final Image ka, final String[] array, final int[] ra, final Rectangle rectangle, final boolean oa) {
        this.ha = new Font(volatile.Ma, 0, 12);
        this.M = new Color(10053120);
        this.T = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.ja = ja;
        this.ka = ka;
        this.oa = oa;
        this.ra = ra;
        this.qa = new String[3][];
        for (int i = 0; i < array.length; ++i) {
            switch (ra[i]) {
                case 2: {
                    this.O = array[i].toUpperCase();
                    this.qa[i] = f.b(this.O, this.T.width - 20, this.ha);
                    break;
                }
                case 1: {
                    if (i == 2 && array[2].length() == 1 && !Character.isLetter(array[2].charAt(0))) {
                        final String[] array2 = this.qa[1];
                        final int n = 0;
                        array2[n] = String.valueOf(array2[n]) + array[2];
                        this.pa = true;
                        this.qa[2] = new String[0];
                        break;
                    }
                    this.qa[i] = f.b(array[i], this.T.width - 20, this.ha);
                    break;
                }
                case 0: {
                    this.qa[i] = new String[0];
                    break;
                }
            }
        }
        this.ia = Toolkit.getDefaultToolkit().getFontMetrics(this.ha);
        this.la = this.ia.getHeight();
        this.ma = (this.T.height - (this.qa[0].length + this.qa[1].length + this.qa[2].length) * this.la) / 2;
        this.na = true;
    }
    
    public void _(final Graphics graphics) {
        if (!this.na) {
            graphics.drawImage(this.ja, this.T.x, this.T.y, null);
            this.y = this.T.y + this.ma + this.la / 2;
            graphics.setFont(this.ha);
            for (int i = 0; i < this.qa.length; ++i) {
                this.b(i, graphics);
            }
            return;
        }
        graphics.drawImage(this.ka, this.T.x, this.T.y, null);
    }
    
    private void b(final int n, final Graphics graphics) {
        switch (this.ra[n]) {
            case 2: {
                graphics.setColor(this.M);
                for (int i = 0; i < this.qa[n].length; ++i) {
                    final int _ = d._(this.qa[n][i], true, this.T, graphics);
                    if (this.pa && i == this.qa[n].length - 1) {
                        final int n2 = this.qa[n][i].length() - 1;
                        final String substring = this.qa[n][i].substring(0, n2);
                        final String substring2 = this.qa[n][i].substring(n2);
                        graphics.drawString(substring, _, this.y);
                        graphics.setColor(Color.black);
                        graphics.drawString(substring2, _ + this.ia.stringWidth(substring), this.y);
                    }
                    else {
                        graphics.drawString(this.qa[n][i], _, this.y);
                        this.y += this.la;
                    }
                }
            }
            case 1: {
                graphics.setColor(Color.black);
                for (int j = 0; j < this.qa[n].length; ++j) {
                    graphics.drawString(this.qa[n][j], d._(this.qa[n][j], true, this.T, graphics), this.y);
                    this.y += this.la;
                }
                break;
            }
        }
    }
    
    public void _(final boolean na) {
        this.na = na;
    }
    
    public boolean h() {
        return this.na;
    }
    
    public boolean i() {
        return this.oa;
    }
    
    public void b(final int x) {
        this.T.x = x;
    }
    
    public String g() {
        return this.O;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF3BAE);
        }
        return new String(array);
    }
    
    static {
        volatile.Ma = a(volatile.Ma);
    }
}
