// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.text;

import org.jfree.util.Log;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.text.BreakIterator;
import java.awt.Paint;
import java.awt.Font;
import org.jfree.util.LogContext;

public abstract class TextUtilities
{
    protected static final LogContext logger;
    private static boolean useFontMetricsGetStringBounds;
    static /* synthetic */ Class class$org$jfree$text$TextUtilities;
    
    public static TextBlock createTextBlock(final String s, final Font font, final Paint paint, final float n, final TextMeasurer textMeasurer) {
        return createTextBlock(s, font, paint, n, Integer.MAX_VALUE, textMeasurer);
    }
    
    public static TextBlock createTextBlock(final String text, final Font font, final Paint paint, final float n, final int n2, final TextMeasurer textMeasurer) {
        final TextBlock textBlock = new TextBlock();
        final BreakIterator lineInstance = BreakIterator.getLineInstance();
        lineInstance.setText(text);
        int nextLineBreak;
        for (int n3 = 0, n4 = 0; n3 < text.length() && n4 < n2; ++n4, n3 = nextLineBreak) {
            nextLineBreak = nextLineBreak(text, n3, n, lineInstance, textMeasurer);
            if (nextLineBreak == -1) {
                textBlock.addLine(text.substring(n3), font, paint);
                return textBlock;
            }
            textBlock.addLine(text.substring(n3, nextLineBreak), font, paint);
        }
        return textBlock;
    }
    
    private static int nextLineBreak(final String s, final int n, final float n2, final BreakIterator breakIterator, final TextMeasurer textMeasurer) {
        int n3 = n;
        float n4 = 0.0f;
        int n5 = 1;
        int next;
        while ((next = breakIterator.next()) != -1) {
            n4 += textMeasurer.getStringWidth(s, n3, next);
            if (n4 > n2) {
                if (n5 != 0) {
                    while (textMeasurer.getStringWidth(s, n, next) > n2) {
                        --next;
                    }
                    return next;
                }
                return breakIterator.previous();
            }
            else {
                n5 = 0;
                n3 = next;
            }
        }
        return -1;
    }
    
    public static Rectangle2D getTextBounds(final String s, final Graphics2D graphics2D, final FontMetrics fontMetrics) {
        Rectangle2D stringBounds;
        if (TextUtilities.useFontMetricsGetStringBounds) {
            stringBounds = fontMetrics.getStringBounds(s, graphics2D);
        }
        else {
            final double n = fontMetrics.stringWidth(s);
            final double n2 = fontMetrics.getHeight();
            if (TextUtilities.logger.isDebugEnabled()) {
                TextUtilities.logger.debug("Height = " + n2);
            }
            stringBounds = new Rectangle2D.Double(0.0, -fontMetrics.getAscent(), n, n2);
        }
        return stringBounds;
    }
    
    public static boolean getUseFontMetricsGetStringBounds() {
        return TextUtilities.useFontMetricsGetStringBounds;
    }
    
    public static void setUseFontMetricsGetStringBounds(final boolean useFontMetricsGetStringBounds) {
        TextUtilities.useFontMetricsGetStringBounds = useFontMetricsGetStringBounds;
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
        logger = Log.createContext((TextUtilities.class$org$jfree$text$TextUtilities == null) ? (TextUtilities.class$org$jfree$text$TextUtilities = class$("org.jfree.text.TextUtilities")) : TextUtilities.class$org$jfree$text$TextUtilities);
        TextUtilities.useFontMetricsGetStringBounds = false;
    }
}
