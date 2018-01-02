import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private Rectangle case;
    private int[] new;
    private String[] a;
    public int int;
    private FontMetrics try;
    private Font do;
    private Font if;
    private Color byte;
    private Color for;
    
    public b(final String s, final Graphics graphics) {
        this.new = new int[30];
        this.a = new String[30];
        this.byte = Color.white;
        this.for = Color.yellow;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int n = 0;
        int n2 = 0;
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        if (nextToken2.indexOf("BOLD") >= 0) {
            ++n;
            ++n2;
        }
        if (nextToken2.indexOf("ITALIC") >= 0) {
            n += 2;
            n2 += 2;
        }
        this.do = new Font(nextToken, n, int1);
        this.if = new Font(nextToken, n2, int1);
        this.try = graphics.getFontMetrics(this.do);
        this.byte = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.for = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.int = 0;
    }
    
    public void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.case = new Rectangle();
        this.case.x = Integer.parseInt(stringTokenizer.nextToken());
        this.case.y = Integer.parseInt(stringTokenizer.nextToken());
        this.case.width = Integer.parseInt(stringTokenizer.nextToken());
        this.case.height = Integer.parseInt(stringTokenizer.nextToken());
    }
    
    public void a(final int n, final String s) {
        this.new[this.int] = n;
        this.a[this.int] = s;
        ++this.int;
    }
    
    public void a(final int n, final Graphics graphics) {
        if (this.int == 0) {
            return;
        }
        int height = this.case.height / this.int;
        if (height < this.try.getHeight()) {
            height = this.try.getHeight();
        }
        int i = 0;
        if (n * height > this.case.height) {
            ++i;
            while ((n - i) * height > this.case.height) {
                ++i;
            }
        }
        final int n2 = i;
        while (i < this.int) {
            final int n3 = this.new[i] - n2;
            if (i == n) {
                graphics.setColor(this.for);
                graphics.setFont(this.if);
            }
            else {
                graphics.setColor(this.byte);
                graphics.setFont(this.do);
            }
            graphics.drawString(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n3 + n2 + 1))).append(". "))), this.case.x + 5, this.case.y + height * n3);
            graphics.drawString(this.a[i], this.case.x + 35, this.case.y + height * n3);
            if (height * n3 > this.case.height) {
                break;
            }
            ++i;
        }
    }
}
