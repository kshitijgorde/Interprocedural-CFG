// 
// Decompiled by Procyon v0.5.30
// 

package complabel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Vector;

public class CompLabel
{
    public CompParser parser;
    Vector comps;
    public int totalWidth;
    public int maxAscent;
    public int maxHeight;
    public ParserElement nextDefaultElement;
    public ParserNode tagTree;
    
    public CompLabel(final String s, final String s2, final String s3, final String[] array, final String s4, final String s5) {
        this.parser = new CompParser(s2, s3, array, s4, s5);
        this.go(s);
    }
    
    public CompLabel(final String s, final ParserNode parserNode) {
        this.parser = new CompParser(parserNode);
        this.go(s);
    }
    
    void go(final String s) {
        this.tagTree = this.parser.parseString(s);
        this.comps = this.parser.makeComps();
        final boolean b = false;
        this.maxAscent = (b ? 1 : 0);
        this.totalWidth = (b ? 1 : 0);
    }
    
    public final void calcMetrics(final Graphics graphics) {
        this.totalWidth = 0;
        this.maxAscent = 0;
        this.maxHeight = 0;
        final Enumeration<TextComp> elements = this.comps.elements();
        while (elements.hasMoreElements()) {
            final TextComp textComp = elements.nextElement();
            final FontMetrics fontMetrics = graphics.getFontMetrics(textComp.font);
            textComp.width = fontMetrics.stringWidth(textComp.text);
            textComp.ascent = fontMetrics.getAscent();
            textComp.height = fontMetrics.getHeight();
            this.totalWidth += textComp.width;
            if (textComp.ascent > this.maxAscent) {
                this.maxAscent = textComp.ascent;
            }
            if (textComp.height > this.maxHeight) {
                this.maxHeight = textComp.height;
            }
        }
    }
    
    public final int getWidth(final Graphics graphics) {
        if (this.totalWidth == 0) {
            this.calcMetrics(graphics);
        }
        return this.totalWidth;
    }
    
    public final void paintAt(final Graphics graphics, int n, final int n2, final float n3) {
        if (this.totalWidth == 0) {
            this.calcMetrics(graphics);
        }
        final Enumeration<TextComp> elements = this.comps.elements();
        while (elements.hasMoreElements()) {
            final TextComp textComp = elements.nextElement();
            final int width = textComp.width;
            graphics.setFont(textComp.font);
            graphics.setColor(this.colorMul(textComp.fgColor, n3));
            graphics.drawString(textComp.text, n, n2);
            n += width;
        }
    }
    
    private Color colorMul(final Color color, final float n) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        if (n > 1.0f) {
            final int n2 = (int)((n - 1.0f) * 255.0f);
            red += n2;
            green += n2;
            blue += n2;
        }
        int n3 = (int)(red * n);
        int n4 = (int)(green * n);
        int n5 = (int)(blue * n);
        if (n3 > 255) {
            n3 = 255;
        }
        if (n4 > 255) {
            n4 = 255;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        return new Color(n3, n4, n5);
    }
}
