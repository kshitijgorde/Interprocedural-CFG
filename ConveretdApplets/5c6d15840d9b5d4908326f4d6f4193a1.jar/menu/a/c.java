// 
// Decompiled by Procyon v0.5.30
// 

package menu.a;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;

public class c extends a
{
    public c() {
        super.try = "";
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.getSize().width - 1;
        final int n2 = this.getSize().height - 1;
        graphics.setColor(this.getBackground());
        graphics.draw3DRect(0, 0, n - 1, n2 - 1, !super.int);
        graphics.draw3DRect(1, 1, n - 3, n2 - 3, !super.int);
        graphics.setColor(Color.black);
        graphics.drawLine(0, n2, n, n2);
        graphics.drawLine(n, 0, n, n2 - 1);
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        graphics.setColor(this.getForeground());
        if (super.int) {
            graphics.drawString(super.try, n / 2 - fontMetrics.stringWidth(super.try) / 2 + 1, n2 / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        }
        else {
            graphics.drawString(super.try, n / 2 - fontMetrics.stringWidth(super.try) / 2, n2 / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        }
    }
    
    protected String if(String lowerCase) {
        final int n = 18;
        lowerCase = lowerCase.toLowerCase();
        final char[] array = new char[n];
        final int length = lowerCase.length();
        char c = '\0';
        final int n2 = 0;
        for (int i = 0; i < length; c += lowerCase.charAt(i++)) {}
        int n3 = 0;
        this.a(c);
        int j = 0;
        int abs = 0;
        while (j < n) {
            final int n4 = (Math.abs(this.a() + abs) + lowerCase.charAt(n3)) % 10;
            if (n4 < 10) {
                array[j] = (char)(n4 + 48);
            }
            else if (n4 < 34) {
                array[j] = (char)(n4 - 10 + 87);
            }
            else {
                array[j] = (char)(n4 - 34 + 67);
            }
            ++j;
            if (++n3 >= length) {
                n3 = n2;
                abs = Math.abs(this.a());
            }
        }
        return new String(array);
    }
    
    public void a(final String try1) {
        super.try = try1;
        this.invalidate();
        this.repaint();
    }
}
