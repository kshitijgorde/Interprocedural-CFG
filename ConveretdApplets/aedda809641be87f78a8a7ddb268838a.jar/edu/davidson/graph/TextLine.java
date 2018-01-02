// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.util.Stack;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class TextLine
{
    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SCIENTIFIC = 1;
    public static final int ALGEBRAIC = 2;
    static final int MINIMUM_SIZE = 6;
    protected double script_fraction;
    protected double sup_offset;
    protected double sub_offset;
    protected Font font;
    protected Color color;
    protected Color background;
    protected String text;
    protected String fontname;
    protected int fontsize;
    protected int fontstyle;
    protected int justification;
    protected int width;
    protected int ascent;
    protected int maxAscent;
    protected int descent;
    protected int maxDescent;
    protected int height;
    protected int leading;
    protected boolean parse;
    protected Graphics lg;
    protected Vector list;
    
    public TextLine() {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.font = null;
        this.color = null;
        this.background = null;
        this.text = null;
        this.fontname = "TimesRoman";
        this.fontsize = 0;
        this.fontstyle = 0;
        this.justification = 1;
        this.width = 0;
        this.ascent = 0;
        this.maxAscent = 0;
        this.descent = 0;
        this.maxDescent = 0;
        this.height = 0;
        this.leading = 0;
        this.parse = true;
        this.lg = null;
        this.list = new Vector(8, 4);
    }
    
    public TextLine(final String text) {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.font = null;
        this.color = null;
        this.background = null;
        this.text = null;
        this.fontname = "TimesRoman";
        this.fontsize = 0;
        this.fontstyle = 0;
        this.justification = 1;
        this.width = 0;
        this.ascent = 0;
        this.maxAscent = 0;
        this.descent = 0;
        this.maxDescent = 0;
        this.height = 0;
        this.leading = 0;
        this.parse = true;
        this.lg = null;
        this.list = new Vector(8, 4);
        this.text = text;
    }
    
    public TextLine(final String s, final Font font) {
        this(s);
        this.font = font;
        if (this.font == null) {
            return;
        }
        this.fontname = font.getName();
        this.fontstyle = font.getStyle();
        this.fontsize = font.getSize();
    }
    
    public TextLine(final String s, final Font font, final Color color, final int justification) {
        this(s, font);
        this.color = color;
        this.justification = justification;
    }
    
    public TextLine(final String s, final Color color) {
        this(s);
        this.color = color;
    }
    
    public TextLine(final Font font, final Color color, final int justification) {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.font = null;
        this.color = null;
        this.background = null;
        this.text = null;
        this.fontname = "TimesRoman";
        this.fontsize = 0;
        this.fontstyle = 0;
        this.justification = 1;
        this.width = 0;
        this.ascent = 0;
        this.maxAscent = 0;
        this.descent = 0;
        this.maxDescent = 0;
        this.height = 0;
        this.leading = 0;
        this.parse = true;
        this.lg = null;
        this.list = new Vector(8, 4);
        this.font = font;
        this.color = color;
        this.justification = justification;
        if (this.font == null) {
            return;
        }
        this.fontname = font.getName();
        this.fontstyle = font.getStyle();
        this.fontsize = font.getSize();
    }
    
    public TextLine copyState() {
        return new TextLine(this.font, this.color, this.justification);
    }
    
    public void copyState(final TextLine textLine) {
        if (textLine == null) {
            return;
        }
        this.font = textLine.getFont();
        this.color = textLine.getColor();
        this.justification = textLine.getJustification();
        if (this.font == null) {
            return;
        }
        this.fontname = this.font.getName();
        this.fontstyle = this.font.getStyle();
        this.fontsize = this.font.getSize();
        this.parse = true;
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.fontname = font.getName();
        this.fontstyle = font.getStyle();
        this.fontsize = font.getSize();
        this.parse = true;
    }
    
    public void setText(final String text) {
        this.text = text;
        this.parse = true;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public void setJustification(final int n) {
        switch (n) {
            case 0: {
                this.justification = 0;
                break;
            }
            default: {
                this.justification = 1;
                break;
            }
            case 2: {
                this.justification = 2;
                break;
            }
        }
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public String getText() {
        return this.text;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public Color getBackground() {
        return this.background;
    }
    
    public int getJustification() {
        return this.justification;
    }
    
    public FontMetrics getFM(final Graphics graphics) {
        if (graphics == null) {
            return null;
        }
        if (this.font == null) {
            return graphics.getFontMetrics();
        }
        return graphics.getFontMetrics(this.font);
    }
    
    public int charWidth(final Graphics graphics, final char c) {
        if (graphics == null) {
            return 0;
        }
        FontMetrics fontMetrics;
        if (this.font == null) {
            fontMetrics = graphics.getFontMetrics();
        }
        else {
            fontMetrics = graphics.getFontMetrics(this.font);
        }
        return fontMetrics.charWidth(c);
    }
    
    public int getWidth(final Graphics graphics) {
        this.parseText(graphics);
        return this.width;
    }
    
    public int getHeight(final Graphics graphics) {
        this.parseText(graphics);
        return this.height;
    }
    
    public int getAscent(final Graphics graphics) {
        if (graphics == null) {
            return 0;
        }
        this.parseText(graphics);
        return this.ascent;
    }
    
    public int getMaxAscent(final Graphics graphics) {
        if (graphics == null) {
            return 0;
        }
        this.parseText(graphics);
        return this.maxAscent;
    }
    
    public int getDescent(final Graphics graphics) {
        if (graphics == null) {
            return 0;
        }
        this.parseText(graphics);
        return this.descent;
    }
    
    public int getMaxDescent(final Graphics graphics) {
        if (graphics == null) {
            return 0;
        }
        this.parseText(graphics);
        return this.maxDescent;
    }
    
    public int getLeading(final Graphics graphics) {
        if (graphics == null) {
            return 0;
        }
        this.parseText(graphics);
        return this.leading;
    }
    
    public void parseText(final Graphics lg) {
        TextState textState = new TextState();
        final Stack<TextState> stack = new Stack<TextState>();
        if (this.lg != lg) {
            this.parse = true;
        }
        this.lg = lg;
        if (!this.parse) {
            return;
        }
        this.parse = false;
        this.width = 0;
        this.leading = 0;
        this.ascent = 0;
        this.descent = 0;
        this.height = 0;
        this.maxAscent = 0;
        this.maxDescent = 0;
        if (this.text == null || lg == null) {
            return;
        }
        this.list.removeAllElements();
        if (this.font == null) {
            textState.f = lg.getFont();
        }
        else {
            textState.f = this.font;
        }
        stack.push(textState);
        this.list.addElement(textState);
        lg.getFontMetrics(textState.f);
        for (int i = 0; i < this.text.length(); ++i) {
            final char char1 = this.text.charAt(i);
            switch (char1) {
                case 36: {
                    if (++i < this.text.length()) {
                        textState.s.append(this.text.charAt(i));
                        break;
                    }
                    break;
                }
                case 123: {
                    final int width = textState.getWidth(lg);
                    if (!textState.isEmpty()) {
                        textState = textState.copyState();
                        this.list.addElement(textState);
                    }
                    stack.push(textState);
                    final TextState textState2 = textState;
                    textState2.x += width;
                    break;
                }
                case 125: {
                    final int x = textState.x + textState.getWidth(lg);
                    stack.pop();
                    textState = stack.peek().copyState();
                    this.list.addElement(textState);
                    textState.x = x;
                    break;
                }
                case 94: {
                    final int width2 = textState.getWidth(lg);
                    if (!textState.isEmpty()) {
                        textState = textState.copyState();
                        this.list.addElement(textState);
                    }
                    textState.f = this.getScriptFont(textState.f);
                    final TextState textState3 = textState;
                    textState3.x += width2;
                    final TextState textState4 = textState;
                    textState4.y -= (int)(textState.getAscent(lg) * this.sup_offset + 0.5);
                    break;
                }
                case 95: {
                    final int width3 = textState.getWidth(lg);
                    if (!textState.isEmpty()) {
                        textState = textState.copyState();
                        this.list.addElement(textState);
                    }
                    textState.f = this.getScriptFont(textState.f);
                    final TextState textState5 = textState;
                    textState5.x += width3;
                    final TextState textState6 = textState;
                    textState6.y += (int)(textState.getDescent(lg) * this.sub_offset + 0.5);
                    break;
                }
                default: {
                    textState.s.append(char1);
                    break;
                }
            }
        }
        final Vector vector;
        synchronized (this.list) {
            vector = (Vector)this.list.clone();
        }
        // monitorexit(this.list)
        for (int j = 0; j < vector.size(); ++j) {
            final TextState textState7 = vector.elementAt(j);
            if (!textState7.isEmpty()) {
                this.width += textState7.getWidth(lg);
                this.ascent = Math.max(this.ascent, Math.abs(textState7.y) + textState7.getAscent(lg));
                this.descent = Math.max(this.descent, Math.abs(textState7.y) + textState7.getDescent(lg));
                this.leading = Math.max(this.leading, textState7.getLeading(lg));
                this.maxDescent = Math.max(this.maxDescent, Math.abs(textState7.y) + textState7.getMaxDescent(lg));
                this.maxAscent = Math.max(this.maxAscent, Math.abs(textState7.y) + textState7.getMaxAscent(lg));
            }
        }
        this.height = this.ascent + this.descent + this.leading;
    }
    
    public boolean isNull() {
        return this.text == null;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int justification) {
        this.justification = justification;
        if (graphics == null) {
            return;
        }
        this.draw(graphics, n, n2);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        int n3 = n;
        if (graphics == null || this.text == null) {
            return;
        }
        final Graphics create = graphics.create();
        if (create == null) {
            return;
        }
        this.parseText(graphics);
        if (this.justification == 0) {
            n3 = n - this.width / 2;
        }
        else if (this.justification == 2) {
            n3 = n - this.width;
        }
        if (this.background != null) {
            create.setColor(this.background);
            create.fillRect(n3, n2 - this.ascent, this.width, this.height);
            create.setColor(graphics.getColor());
        }
        if (this.font != null) {
            create.setFont(this.font);
        }
        if (this.color != null) {
            create.setColor(this.color);
        }
        final Vector vector;
        synchronized (this.list) {
            vector = (Vector)this.list.clone();
        }
        // monitorexit(this.list)
        for (int i = 0; i < vector.size(); ++i) {
            final TextState textState = vector.elementAt(i);
            if (textState.f != null) {
                create.setFont(textState.f);
            }
            if (textState.s != null) {
                create.drawString(textState.toString(), textState.x + n3, textState.y + n2);
            }
        }
        create.dispose();
    }
    
    public String getFontName() {
        return this.fontname;
    }
    
    public int getFontStyle() {
        return this.fontstyle;
    }
    
    public int getFontSize() {
        return this.fontsize;
    }
    
    public void setFontName(final String fontname) {
        this.fontname = fontname;
        this.rebuildFont();
    }
    
    public void setFontStyle(final int fontstyle) {
        this.fontstyle = fontstyle;
        this.rebuildFont();
    }
    
    public void setFontSize(final int fontsize) {
        this.fontsize = fontsize;
        this.rebuildFont();
    }
    
    private void rebuildFont() {
        this.parse = true;
        if (this.fontsize <= 0 || this.fontname == null) {
            this.font = null;
        }
        else {
            this.font = new Font(this.fontname, this.fontstyle, this.fontsize);
        }
    }
    
    public Font getScriptFont(final Font font) {
        if (font == null) {
            return font;
        }
        if (font.getSize() <= 6) {
            return font;
        }
        final int n = (int)(font.getSize() * this.script_fraction + 0.5);
        if (n <= 6) {
            return font;
        }
        return new Font(font.getName(), font.getStyle(), n);
    }
    
    public boolean parseDouble(final double n) {
        return this.parseDouble(n, 7, 6, 2);
    }
    
    public boolean parseDouble(final double n, final int n2) {
        return this.parseDouble(n, n2 + 1, n2, 2);
    }
    
    public boolean parseDouble(final double n, final int n2, final int n3, final int n4) {
        double n5 = n;
        final int n6 = n2 - n3;
        final StringBuffer sb = new StringBuffer(n2 + 4);
        if (n6 < 0) {
            System.out.println("TextLine.parseDouble: Precision > significant figures!");
            return false;
        }
        if (n < 0.0) {
            n5 = -n;
            sb.append("-");
        }
        int n7;
        if (n == 0.0) {
            n7 = 0;
        }
        else {
            n7 = (int)Math.floor(SpecialFunction.log10(n5));
        }
        final int n8 = n7 - (n6 - 1);
        if (n8 < 0) {
            for (int i = n8; i < 0; ++i) {
                n5 *= 10.0;
            }
        }
        else {
            for (int j = 0; j < n8; ++j) {
                n5 /= 10.0;
            }
        }
        final int n9 = (int)n5;
        sb.append(n9);
        if (n3 > 0) {
            sb.append('.');
            double n10 = n5 - n9;
            for (int k = 0; k < n3; ++k) {
                final double n11 = n10 * 10;
                final int n12 = (int)Math.round(n11);
                sb.append(n12);
                n10 = n11 - n12;
            }
        }
        if (n8 != 0) {
            if (n4 == 1) {
                sb.append('E');
                if (n8 < 0) {
                    sb.append('-');
                }
                else {
                    sb.append('+');
                }
                final int abs = Math.abs(n8);
                if (abs > 9) {
                    sb.append(abs);
                }
                else {
                    sb.append('0');
                    sb.append(abs);
                }
            }
            else {
                sb.append("x10{^");
                sb.append(n8);
                sb.append("}");
            }
        }
        this.setText(sb.toString());
        return true;
    }
}
