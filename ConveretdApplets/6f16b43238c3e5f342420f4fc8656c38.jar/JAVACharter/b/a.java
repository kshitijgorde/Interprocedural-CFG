// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import JAVACharter.StyleManage.b;
import java.util.Date;

public class a
{
    private Date a;
    private b do;
    private String if;
    
    public a(final Date date, final b do1, final String s) {
        System.out.println("in constructor: " + date);
        this.a(date);
        this.a(s);
        this.do = do1;
    }
    
    public Date a() {
        return this.a;
    }
    
    public void a(final Date a) {
        this.a = a;
    }
    
    public String if() {
        return this.if;
    }
    
    public void a(final String if1) {
        this.if = if1;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final boolean b) {
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        final int[] if1 = this.do.if();
        final int[] try1 = this.do.try();
        final int length = if1.length;
        for (int i = 0; i < length; ++i) {
            final int[] array = if1;
            final int n3 = i;
            array[n3] += n;
            final int[] array2 = try1;
            final int n4 = i;
            array2[n4] += n2;
        }
        graphics.setColor(this.do.int());
        graphics.fillPolygon(if1, try1, length);
        graphics.setFont(this.do.a());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n5 = fontMetrics.stringWidth(this.if) / 2;
        int n6;
        if (b) {
            n6 = this.do.new() / 2 + fontMetrics.getHeight();
        }
        else {
            n6 = -1 * (this.do.new() / 2 + 2);
        }
        graphics.drawString(this.if, n - n5, n2 + n6);
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    public String for() {
        return this.do.do();
    }
    
    public int int() {
        return this.do.new();
    }
    
    public int do() {
        return this.do.for();
    }
}
