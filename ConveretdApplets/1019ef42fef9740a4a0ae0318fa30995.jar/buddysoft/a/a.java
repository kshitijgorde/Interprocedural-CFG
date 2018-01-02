// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class a extends Panel
{
    private boolean int;
    private boolean new;
    private boolean a;
    private int width;
    private int height;
    private int if;
    private Insets do;
    private String try;
    private int for;
    private int byte;
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.do = new Insets(n, n2, n3, n4);
    }
    
    private int if() {
        if (this.for == 0) {
            this.for = this.byte;
        }
        this.for *= this.byte;
        this.for += this.byte % 7;
        this.for *= this.for % 5;
        this.for %= 90;
        if (++this.byte > 18) {
            this.byte = 1;
        }
        return this.for;
    }
    
    public a() {
        this(8, 8, 8, 8, 6, true);
    }
    
    public a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n, n2, n3, n4, n5, true);
    }
    
    public a(final int n, final int n2, final int n3, final int n4, final int if1, final boolean int1) {
        this.width = -1;
        this.height = -1;
        this.new = false;
        this.do = new Insets(n, n2, n3, n4);
        this.if = if1;
        this.int = int1;
    }
    
    public void paint(final Graphics graphics) {
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        if (this.if == 5) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.int);
        }
        else if (this.if == 6) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.int);
            graphics.draw3DRect(2, 2, this.width - 4, this.height - 4, this.int);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, this.width, this.height, 5, 5);
        }
        if (this.new) {
            if (this.a) {
                graphics.setColor(this.getBackground().darker());
                graphics.drawRect(this.do.left / 2 + 1, this.do.top / 2 + 1, this.width - this.do.left / 2 - this.do.right / 2 - 1, this.height - this.do.top / 2 - this.do.bottom / 2 - 1);
                graphics.setColor(this.getBackground().brighter());
                graphics.drawRect(this.do.left / 2, this.do.top / 2, this.width - this.do.left / 2 - this.do.right / 2 - 1, this.height - this.do.top / 2 - this.do.bottom / 2 - 1);
            }
            else {
                graphics.setColor(this.getBackground().brighter());
                graphics.drawRect(this.do.left / 2 + 1, this.do.top / 2 + 1, this.width - this.do.left / 2 - this.do.right / 2 - 1, this.height - this.do.top / 2 - this.do.bottom / 2 - 1);
                graphics.setColor(this.getBackground().darker());
                graphics.drawRect(this.do.left / 2, this.do.top / 2, this.width - this.do.left / 2 - this.do.right / 2 - 1, this.height - this.do.top / 2 - this.do.bottom / 2 - 1);
            }
        }
        if (this.try != null) {
            graphics.setFont(new Font("Halvetica", 1, 13));
            final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.do.left, this.do.top / 2 - fontMetrics.getHeight() / 2, fontMetrics.stringWidth(this.try) + fontMetrics.getMaxDescent() * 2, fontMetrics.getHeight());
            graphics.setColor(this.getForeground());
            graphics.drawString(this.try, this.do.left + fontMetrics.getMaxDescent(), this.do.top / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        }
    }
    
    public void a(final boolean a) {
        this.new = true;
        this.a = a;
    }
    
    public Dimension minimumSize() {
        if (this.width != -1) {
            return new Dimension(this.width, this.height);
        }
        return super.minimumSize();
    }
    
    public Dimension a() {
        return this.minimumSize();
    }
    
    protected String a(String lowerCase) {
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
            final int n4 = (Math.abs(this.if() + abs) + lowerCase.charAt(n3)) % 10;
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
                abs = Math.abs(this.if());
            }
        }
        return new String(array);
    }
    
    private void a(final int for1) {
        this.for = for1;
        this.byte = 10;
    }
    
    public Insets insets() {
        return this.do;
    }
    
    public void if(final String try1) {
        this.try = try1;
        this.invalidate();
        this.repaint();
    }
}
