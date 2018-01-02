// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.text;

import org.jfree.util.Log;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.awt.font.LineMetrics;
import java.awt.Graphics;
import java.awt.FontMetrics;
import org.jfree.ui.Size2D;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import java.awt.Graphics2D;
import org.jfree.util.LogContext;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public class TextFragment implements Serializable
{
    public static final Font DEFAULT_FONT;
    public static final Paint DEFAULT_PAINT;
    private String text;
    private Font font;
    private Paint paint;
    protected static final LogContext logger;
    static /* synthetic */ Class class$org$jfree$text$TextFragment;
    
    public TextFragment(final String s) {
        this(s, TextFragment.DEFAULT_FONT, TextFragment.DEFAULT_PAINT);
    }
    
    public TextFragment(final String s, final Font font) {
        this(s, font, TextFragment.DEFAULT_PAINT);
    }
    
    public TextFragment(final String text, final Font font, final Paint paint) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.text = text;
        this.font = font;
        this.paint = paint;
    }
    
    public String getText() {
        return this.text;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void draw(final Graphics2D graphics2D, final float n, final float n2, final TextAnchor textAnchor, final float n3, final float n4, final double n5) {
        graphics2D.setFont(this.font);
        graphics2D.setPaint(this.paint);
        RefineryUtilities.drawRotatedString(this.text, graphics2D, n, n2, textAnchor, n3, n4, n5);
    }
    
    public Size2D calculateDimensions(final Graphics2D graphics2D) {
        final FontMetrics fontMetrics = graphics2D.getFontMetrics(this.font);
        final double n = fontMetrics.stringWidth(this.text);
        final double n2 = fontMetrics.getHeight();
        if (TextFragment.logger.isDebugEnabled()) {
            TextFragment.logger.debug("width = " + n + ", height = " + n2);
        }
        return new Size2D(n, n2);
    }
    
    public float calculateBaselineOffset(final Graphics2D graphics2D, final TextAnchor textAnchor) {
        float ascent = 0.0f;
        final LineMetrics lineMetrics = graphics2D.getFontMetrics(this.font).getLineMetrics("ABCxyz", graphics2D);
        if (textAnchor == TextAnchor.TOP_LEFT || textAnchor == TextAnchor.TOP_CENTER || textAnchor == TextAnchor.TOP_RIGHT) {
            ascent = lineMetrics.getAscent();
        }
        else if (textAnchor == TextAnchor.BOTTOM_LEFT || textAnchor == TextAnchor.BOTTOM_CENTER || textAnchor == TextAnchor.BOTTOM_RIGHT) {
            ascent = -lineMetrics.getDescent() - lineMetrics.getLeading();
        }
        return ascent;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof TextFragment) {
            final TextFragment textFragment = (TextFragment)o;
            return this.text.equals(textFragment.text) && this.font.equals(textFragment.font) && this.paint.equals(textFragment.paint);
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, objectOutputStream);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(objectInputStream);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        DEFAULT_FONT = new Font("Serif", 0, 12);
        DEFAULT_PAINT = Color.black;
        logger = Log.createContext((TextFragment.class$org$jfree$text$TextFragment == null) ? (TextFragment.class$org$jfree$text$TextFragment = class$("org.jfree.text.TextFragment")) : TextFragment.class$org$jfree$text$TextFragment);
    }
}
