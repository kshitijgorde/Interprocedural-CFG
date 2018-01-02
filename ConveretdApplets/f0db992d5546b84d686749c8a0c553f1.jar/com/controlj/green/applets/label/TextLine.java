// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import com.controlj.green.applets.SpecialFunction;
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
    protected int line_padding;
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
    protected boolean specialChars;
    
    public boolean isUsingSpecialChars() {
        return this.specialChars;
    }
    
    public void useSpecialChars(final boolean specialChars) {
        this.specialChars = specialChars;
    }
    
    public TextLine() {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.line_padding = 0;
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
        this.specialChars = false;
    }
    
    public TextLine(final String s) {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.line_padding = 0;
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
        this.specialChars = false;
        this.text = s;
    }
    
    public TextLine(final String s, final Font f) {
        this(s);
        this.font = f;
        if (this.font == null) {
            return;
        }
        this.fontname = f.getName();
        this.fontstyle = f.getStyle();
        this.fontsize = f.getSize();
    }
    
    public TextLine(final String s, final Font f, final Color c, final int j) {
        this(s, f);
        this.color = c;
        this.justification = j;
    }
    
    public TextLine(final String s, final Color c) {
        this(s);
        this.color = c;
    }
    
    public TextLine(final Font f, final Color c, final int j) {
        this.script_fraction = 0.8;
        this.sup_offset = 0.6;
        this.sub_offset = 0.7;
        this.line_padding = 0;
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
        this.specialChars = false;
        this.font = f;
        this.color = c;
        this.justification = j;
        if (this.font == null) {
            return;
        }
        this.fontname = f.getName();
        this.fontstyle = f.getStyle();
        this.fontsize = f.getSize();
    }
    
    public TextLine copyState() {
        return new TextLine(this.font, this.color, this.justification);
    }
    
    public void copyState(final TextLine t) {
        if (t == null) {
            return;
        }
        this.font = t.getFont();
        this.color = t.getColor();
        this.justification = t.getJustification();
        if (this.font == null) {
            return;
        }
        this.fontname = this.font.getName();
        this.fontstyle = this.font.getStyle();
        this.fontsize = this.font.getSize();
        this.parse = true;
    }
    
    public void setFont(final Font f) {
        this.font = f;
        this.fontname = f.getName();
        this.fontstyle = f.getStyle();
        this.fontsize = f.getSize();
        this.parse = true;
    }
    
    public void setText(final String s) {
        this.text = s;
        this.parse = true;
    }
    
    public void setColor(final Color c) {
        this.color = c;
    }
    
    public void setBackground(final Color c) {
        this.background = c;
    }
    
    public void setJustification(final int i) {
        switch (i) {
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
    
    public FontMetrics getFM(final Graphics g) {
        if (g == null) {
            return null;
        }
        if (this.font == null) {
            return g.getFontMetrics();
        }
        return g.getFontMetrics(this.font);
    }
    
    public int charWidth(final Graphics g, final char ch) {
        if (g == null) {
            return 0;
        }
        FontMetrics fm;
        if (this.font == null) {
            fm = g.getFontMetrics();
        }
        else {
            fm = g.getFontMetrics(this.font);
        }
        return fm.charWidth(ch);
    }
    
    public int getWidth(final Graphics g) {
        this.parseText(g);
        return this.width;
    }
    
    public int getHeight(final Graphics g) {
        this.parseText(g);
        return this.height;
    }
    
    public int getLinePadding() {
        return this.line_padding;
    }
    
    public void setLinePadding(final int pad) {
        this.line_padding = pad;
    }
    
    public int getAscent(final Graphics g) {
        if (g == null) {
            return 0;
        }
        this.parseText(g);
        return this.ascent;
    }
    
    public int getMaxAscent(final Graphics g) {
        if (g == null) {
            return 0;
        }
        this.parseText(g);
        return this.maxAscent;
    }
    
    public int getDescent(final Graphics g) {
        if (g == null) {
            return 0;
        }
        this.parseText(g);
        return this.descent;
    }
    
    public int getMaxDescent(final Graphics g) {
        if (g == null) {
            return 0;
        }
        this.parseText(g);
        return this.maxDescent;
    }
    
    public int getLeading(final Graphics g) {
        if (g == null) {
            return 0;
        }
        this.parseText(g);
        return this.leading;
    }
    
    public void parseText(final Graphics g) {
        TextState current = new TextState();
        final Stack state = new Stack();
        int w = 0;
        if (this.lg != g) {
            this.parse = true;
        }
        this.lg = g;
        if (!this.parse) {
            return;
        }
        this.parse = false;
        if (this.text == null || g == null) {
            return;
        }
        this.list.removeAllElements();
        if (this.font == null) {
            current.f = g.getFont();
        }
        else {
            current.f = this.font;
        }
        state.push(current);
        this.list.addElement(current);
        final FontMetrics fm = g.getFontMetrics(current.f);
        for (int i = 0; i < this.text.length(); ++i) {
            final char ch = this.text.charAt(i);
            if (this.specialChars) {
                switch (ch) {
                    case '$': {
                        if (++i < this.text.length()) {
                            current.s.append(this.text.charAt(i));
                            break;
                        }
                        break;
                    }
                    case '{': {
                        w = current.getWidth(g);
                        if (!current.isEmpty()) {
                            current = current.copyState();
                            this.list.addElement(current);
                        }
                        state.push(current);
                        final TextState textState = current;
                        textState.x += w;
                        break;
                    }
                    case '}': {
                        w = current.x + current.getWidth(g);
                        state.pop();
                        current = state.peek().copyState();
                        this.list.addElement(current);
                        current.x = w;
                        break;
                    }
                    case '^': {
                        w = current.getWidth(g);
                        if (!current.isEmpty()) {
                            current = current.copyState();
                            this.list.addElement(current);
                        }
                        current.f = this.getScriptFont(current.f);
                        final TextState textState2 = current;
                        textState2.x += w;
                        final TextState textState3 = current;
                        textState3.y -= (int)(current.getAscent(g) * this.sup_offset + 0.5);
                        break;
                    }
                    case '_': {
                        w = current.getWidth(g);
                        if (!current.isEmpty()) {
                            current = current.copyState();
                            this.list.addElement(current);
                        }
                        current.f = this.getScriptFont(current.f);
                        final TextState textState4 = current;
                        textState4.x += w;
                        final TextState textState5 = current;
                        textState5.y += (int)(current.getDescent(g) * this.sub_offset + 0.5);
                        break;
                    }
                    case '\n': {
                        if (!current.isEmpty()) {
                            current = current.copyState();
                            this.list.addElement(current);
                        }
                        final TextState textState6 = current;
                        textState6.y += current.getAscent(g) + current.getDescent(g) + this.line_padding;
                        break;
                    }
                    default: {
                        current.s.append(ch);
                        break;
                    }
                }
            }
            else if (ch == '\n') {
                if (!current.isEmpty()) {
                    current = current.copyState();
                    this.list.addElement(current);
                }
                final TextState textState7 = current;
                textState7.y += current.getAscent(g) + current.getDescent(g) + this.line_padding;
            }
            else {
                current.s.append(ch);
            }
        }
        this.width = 0;
        this.leading = 0;
        this.ascent = 0;
        this.descent = 0;
        this.height = 0;
        this.maxAscent = 0;
        this.maxDescent = 0;
        for (int i = 0; i < this.list.size(); ++i) {
            current = this.list.elementAt(i);
            if (!current.isEmpty()) {
                this.ascent = Math.max(this.ascent, current.getAscent(g));
                this.descent = Math.max(this.descent, current.getDescent(g));
                this.leading = Math.max(this.leading, current.getLeading(g));
                this.maxDescent = Math.max(this.maxDescent, Math.abs(current.y) + current.getMaxDescent(g));
                this.maxAscent = Math.max(this.maxAscent, Math.abs(current.y) + current.getMaxAscent(g));
                this.width = Math.max(this.width, current.x + current.getWidth(g));
                this.height = Math.max(this.height, current.y + current.getHeight(g));
            }
        }
    }
    
    public boolean isNull() {
        return this.text == null;
    }
    
    public void draw(final Graphics g, final int x, final int y, final int j) {
        try {
            this.justification = j;
            if (g == null) {
                return;
            }
            this.draw(g, x, y);
        }
        catch (Exception e) {
            System.out.println("Exception in draw :" + e.toString());
        }
    }
    
    public void draw(final Graphics g, final int x, final int y) {
        int xoffset = x;
        if (g == null || this.text == null) {
            return;
        }
        Graphics lg = g.create();
        this.parseText(g);
        if (this.justification == 0) {
            xoffset = x - this.width / 2;
        }
        else if (this.justification == 2) {
            xoffset = x - this.width;
        }
        if (this.background != null) {
            lg.setColor(this.background);
            lg.fillRect(xoffset, y - this.ascent, this.width, this.height);
            lg.setColor(g.getColor());
        }
        if (this.font != null) {
            lg.setFont(this.font);
        }
        if (this.color != null) {
            lg.setColor(this.color);
        }
        for (int i = 0; i < this.list.size(); ++i) {
            final TextState ts = this.list.elementAt(i);
            if (ts.f != null) {
                lg.setFont(ts.f);
            }
            if (ts.s != null) {
                int recenter = 0;
                if (this.justification == 0) {
                    recenter = (this.width - ts.getWidth(g)) / 2;
                }
                lg.drawString(ts.toString(), ts.x + xoffset + recenter, ts.y + y);
            }
        }
        lg.dispose();
        lg = null;
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
    
    public void setFontName(final String s) {
        this.fontname = s;
        this.rebuildFont();
    }
    
    public void setFontStyle(final int i) {
        this.fontstyle = i;
        this.rebuildFont();
    }
    
    public void setFontSize(final int i) {
        this.fontsize = i;
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
    
    public Font getScriptFont(final Font f) {
        if (f == null) {
            return f;
        }
        int size = f.getSize();
        if (size <= 6) {
            return f;
        }
        size = (int)(f.getSize() * this.script_fraction + 0.5);
        if (size <= 6) {
            return f;
        }
        return new Font(f.getName(), f.getStyle(), size);
    }
    
    public boolean parseDouble(final double d) {
        return this.parseDouble(d, 7, 6, 2);
    }
    
    public boolean parseDouble(final double d, final int p) {
        return this.parseDouble(d, p + 1, p, 2);
    }
    
    public boolean parseDouble(final double d, final int n, final int p, final int f) {
        double x = d;
        int left = n - p;
        double right = 0.0;
        final StringBuffer s = new StringBuffer(n + 4);
        if (left < 0) {
            System.out.println("TextLine.parseDouble: Precision > significant figures!");
            return false;
        }
        if (d < 0.0) {
            x = -d;
            s.append("-");
        }
        int exponent;
        if (d == 0.0) {
            exponent = 0;
        }
        else {
            exponent = (int)Math.floor(SpecialFunction.log10(x));
        }
        int power = exponent - (left - 1);
        if (power < 0) {
            for (int i = power; i < 0; ++i) {
                x *= 10.0;
            }
        }
        else {
            for (int i = 0; i < power; ++i) {
                x /= 10.0;
            }
        }
        left = (int)x;
        s.append(left);
        if (p > 0) {
            s.append('.');
            right = x - left;
            for (int i = 0; i < p; ++i) {
                right *= 10.0;
                if (i == p - 1) {
                    right += 0.5;
                }
                s.append((int)right);
                right -= (int)right;
            }
        }
        if (power != 0) {
            if (f == 1) {
                s.append('E');
                if (power < 0) {
                    s.append('-');
                }
                else {
                    s.append('+');
                }
                power = Math.abs(power);
                if (power > 9) {
                    s.append(power);
                }
                else {
                    s.append('0');
                    s.append(power);
                }
            }
            else {
                s.append("x10{^");
                s.append(power);
                s.append("}");
            }
        }
        this.setText(s.toString());
        return true;
    }
}
