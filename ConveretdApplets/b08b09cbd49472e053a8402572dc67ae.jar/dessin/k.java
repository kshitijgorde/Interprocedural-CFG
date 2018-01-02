// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Label;

public class k extends m
{
    boolean e;
    Label null;
    String void;
    String d;
    int c;
    int b;
    
    public k(final Label null, final boolean e) {
        this.null = new Label();
        if (e) {
            final String text = null.getText();
            final int index = text.indexOf("###");
            this.d = text.substring(0, index);
            this.void = text.substring(index + 3);
        }
        else {
            this.d = null.getText();
        }
        this.null = null;
        this.e = e;
    }
    
    m do(final int n, final int n2) {
        final FontMetrics fontMetrics = this.null.getFontMetrics(this.null.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.d);
        final int ascent = fontMetrics.getAscent();
        if (n >= this.c && n <= this.c + stringWidth && n2 >= this.b - ascent && n2 <= this.b) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final FontMetrics fontMetrics = this.null.getFontMetrics(this.null.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.d);
        final int ascent = fontMetrics.getAscent();
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.c && intValue2 < this.b - ascent && intValue3 > this.c + stringWidth && intValue4 > this.b) {
            super.a = true;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        this.c += n3 - n;
        this.b += n4 - n2;
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(Color.black);
        }
        else if (!super.a) {
            return;
        }
        this.do(graphics);
    }
    
    private void do(final Graphics graphics) {
        graphics.setColor(this.null.getForeground());
        graphics.setFont(this.null.getFont());
        graphics.drawString(this.d, this.c, this.b);
    }
    
    void if(final Graphics graphics) {
    }
    
    void a(final Graphics graphics) {
        final FontMetrics fontMetrics = this.null.getFontMetrics(this.null.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.d);
        final int ascent = fontMetrics.getAscent();
        graphics.setColor(Color.red);
        graphics.drawRect(this.c, this.b - ascent, stringWidth, ascent);
    }
    
    void a(final Graphics graphics, final int c, final int b) {
        this.c = c;
        this.b = b;
        this.do(graphics);
    }
    
    public Rectangle for() {
        final FontMetrics fontMetrics = this.null.getFontMetrics(this.null.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.null.getText());
        final int ascent = fontMetrics.getAscent();
        final int n = (stringWidth >= ascent) ? stringWidth : ascent;
        return new Rectangle(this.c - n, this.b - n, n, n);
    }
    
    void a() {
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        final String s = "";
        final Color foreground = this.null.getForeground();
        String s2;
        if (this.e) {
            s2 = s + "textelink;";
        }
        else {
            s2 = s + "texte;";
        }
        final String string = s2 + this.c + ";" + this.b + ";" + foreground.getRGB() + ";";
        final Font font = this.null.getFont();
        final String string2 = string + font.getName() + ";" + font.getSize() + ";" + font.getStyle() + ";";
        String s3;
        if (this.e) {
            s3 = string2 + this.d + "###" + this.void + ";";
        }
        else {
            s3 = string2 + this.d + ";";
        }
        return s3 + "0;" + "0;";
    }
    
    void if(final int n, final int n2) {
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) != null) {
            return 1;
        }
        return -1;
    }
}
