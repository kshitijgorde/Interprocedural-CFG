// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.util.Utilities;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class TextStyle implements Cloneable
{
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_LEFT = 2;
    public static final int ALIGNMENT_RIGHT = 3;
    public static final TextStyle DEFAULT_STYLE;
    public static final Color DEFAULT_BKCOLOR;
    protected ExtendedFont defFont;
    protected Color backcolor;
    protected int alignment;
    protected ClickableTextAction action;
    
    public void finalize() {
        this.defFont = null;
        this.backcolor = null;
        this.action = null;
    }
    
    public ExtendedFont getDefaultFont() {
        return this.defFont;
    }
    
    public Font getFont() {
        return this.defFont.getFont();
    }
    
    public Color getBackColor() {
        return this.backcolor;
    }
    
    public FontMetrics getFontMetrics() {
        return this.defFont.getFontMetrics();
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
    }
    
    public ClickableTextAction getClickableTextAction() {
        return this.action;
    }
    
    public void setClickableTextAction(final ClickableTextAction action) {
        this.action = action;
    }
    
    public boolean isClickable() {
        return this.action != null;
    }
    
    public TextStyle concreteStyle() {
        return this;
    }
    
    public TextStyle cloneStyle() {
        return new TextStyle(this.getDefaultFont());
    }
    
    public void drawText(final Graphics graphics, final char[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        Color color = null;
        if (this.defFont.getColor() != null && (array != null || this.defFont.isUnderline())) {
            if (!this.backcolor.equals(Color.lightGray)) {
                final Color color2 = graphics.getColor();
                graphics.setColor(this.backcolor);
                graphics.fillRect(n3, n4, n5, n6);
                graphics.setColor(color2);
            }
            color = graphics.getColor();
            graphics.setColor(this.defFont.getColor());
        }
        if (array != null) {
            final Font font = graphics.getFont();
            graphics.setFont(this.defFont.getFont());
            graphics.drawChars(array, n, n2, n3, n4 + n7);
            graphics.setFont(font);
        }
        if (this.defFont.isUnderline()) {
            graphics.drawLine(n3, n4 + n7 + 1, n3 + n5, n4 + n7 + 1);
        }
        if (color != null) {
            graphics.setColor(color);
        }
    }
    
    public String toString() {
        return "FONT family == " + this.defFont.getFont().getFamily() + '\n' + "FONT name   == " + this.defFont.getFont().getName() + '\n' + "FONT size   == " + this.defFont.getFont().getSize() + '\n' + "FONT style  == " + this.defFont.getFont().getStyle() + '\n' + "FONT color  == " + this.defFont.getColor() + '\n' + "FONT bkcolor== " + this.backcolor + '\n';
    }
    
    public Object clone() {
        try {
            final TextStyle textStyle = (TextStyle)super.clone();
            textStyle.defFont = this.defFont;
            textStyle.backcolor = this.backcolor;
            textStyle.alignment = this.alignment;
            return textStyle;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError("Clones are not being supported");
        }
    }
    
    public TextStyle(final String s, final int n, final int n2) {
        this(s, n, n2, null, false, null);
    }
    
    public TextStyle(final String s, final int n, final int n2, final Color color) {
        this(s, n, n2, color, false, null);
    }
    
    public TextStyle(final String s, final int n, final int n2, final boolean b) {
        this(s, n, n2, null, b, null);
    }
    
    public TextStyle(final String s, final int n, final int n2, final Color color, final boolean b) {
        this(s, n, n2, color, b, null);
    }
    
    public TextStyle(final String s, final int n, final int n2, final Color color, final boolean b, final Color backcolor) {
        this(new ExtendedFont(s, n, n2, color, b));
        this.backcolor = backcolor;
    }
    
    public TextStyle(final String s, final int n, final int n2, final Color color, final boolean b, final Color color2, final int n3, final ClickableTextAction action) {
        this(s, n, n2, color, b, color2, n3);
        this.action = action;
    }
    
    public TextStyle(final String s, final int n, final int n2, final Color color, final boolean b, final Color backcolor, final int alignment) {
        this(new ExtendedFont(s, n, n2, color, b));
        this.alignment = alignment;
        this.backcolor = backcolor;
    }
    
    public TextStyle(final Font font) {
        this(font, null, false);
    }
    
    public TextStyle(final Font font, final Color color) {
        this(font, color, false);
    }
    
    public TextStyle(final Font font, final boolean b) {
        this(font, null, b);
    }
    
    public TextStyle(final Font font, final Color color, final boolean b) {
        this(new ExtendedFont(font, color, b));
    }
    
    public TextStyle(final ExtendedFont defFont) {
        this.defFont = null;
        this.backcolor = Color.lightGray;
        this.alignment = 2;
        this.action = null;
        if (defFont == null) {
            throw new NullPointerException("TextStyle(): null exFont");
        }
        this.defFont = defFont;
    }
    
    public TextStyle(final ExtendedFont defFont, final Color backcolor, final int alignment) {
        this.defFont = null;
        this.backcolor = Color.lightGray;
        this.alignment = 2;
        this.action = null;
        if (defFont == null) {
            throw new NullPointerException("TextStyle(): null exFont");
        }
        this.defFont = defFont;
        this.backcolor = backcolor;
        this.alignment = alignment;
    }
    
    static {
        DEFAULT_STYLE = new TextStyle(Utilities.getSystemFont("Dialog"), 0, 12, Color.black, false, Color.lightGray, 2);
        DEFAULT_BKCOLOR = Color.lightGray;
    }
}
