// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.Point;
import java.awt.Font;

public class TextAligner
{
    private final String[] myText;
    private final TextAlignContext myContext;
    private Font myAdjustedFont;
    private float myCenterVerticallyAdjustment;
    
    public TextAligner(final String[] myText, final TextAlignContext myContext) {
        if (myText.length == 0) {
            throw new IllegalArgumentException("Please align empty text yourself.");
        }
        this.myText = myText;
        this.myContext = myContext;
        this.adjustFont();
        this.myCenterVerticallyAdjustment = (this.myContext.getTotalHeight() - this.getTotalTextHeight()) / 2.0f;
    }
    
    private void adjustFont() {
        this.adjustFontVertically();
        this.adjustFontHorizontally();
    }
    
    private void adjustFontVertically() {
        final float totalTextHeight = this.getTotalTextHeight();
        final float n = this.myContext.getTotalHeight();
        if (totalTextHeight > n) {
            this.myAdjustedFont = this.getAdjustedFont().deriveFont(this.getAdjustedFont().getSize2D() * n / totalTextHeight * 0.8f);
        }
    }
    
    private void adjustFontHorizontally() {
        final float totalTextWidth = this.getTotalTextWidth();
        final float n = this.myContext.getTotalWidth();
        if (totalTextWidth > n) {
            this.myAdjustedFont = this.getAdjustedFont().deriveFont(this.getAdjustedFont().getSize2D() * n / totalTextWidth * 0.8f);
        }
    }
    
    private float getTotalTextHeight() {
        return this.getRelativeVerticalDeltaForSingleLine(this.myText.length - 1);
    }
    
    private float getTotalTextWidth() {
        double n = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.myText.length; ++i) {
            final double width = this.getAdjustedFont().getStringBounds(this.myText[i], this.getFontRenderContext()).getWidth();
            if (width > n) {
                n = width;
            }
        }
        return (float)n;
    }
    
    private float getRelativeVerticalDeltaForSingleLine(final int n) {
        float n2 = 0.0f + this.getLineMetrics(this.myText[0]).getAscent();
        for (int i = 1; i <= n; ++i) {
            n2 += this.getLineMetrics(this.myText[i]).getHeight();
        }
        return n2;
    }
    
    public float getLineHeight(final int n) {
        return this.getAdjustedFont().getLineMetrics(this.myText[n], this.getFontRenderContext()).getHeight();
    }
    
    public Font getAdjustedFont() {
        return (this.myAdjustedFont != null) ? this.myAdjustedFont : this.myContext.getFont();
    }
    
    public Point getSymbolPlacement(final SymbolPosition symbolPosition) {
        return this.getSymbolPlacement(symbolPosition.getLineNumber(), symbolPosition.getPosition());
    }
    
    public Point getSymbolPlacement(final int n, final int n2) {
        final String s = this.myText[n];
        final FontRenderContext fontRenderContext = this.getFontRenderContext();
        float n3 = (float)(this.myContext.getTotalWidth() - this.getAdjustedFont().getStringBounds(s, fontRenderContext).getWidth()) / 2.0f;
        if (n3 < 0.0f) {
            n3 = 0.0f;
        }
        return new Point((int)(this.getAdjustedFont().getStringBounds(s, n2, n2 + 1, fontRenderContext).getMinX() + n3 + this.getAdjustedFont().getStringBounds(s, 0, n2, fontRenderContext).getMaxX()), (int)(this.myCenterVerticallyAdjustment + this.getRelativeVerticalDeltaForSingleLine(n)));
    }
    
    private LineMetrics getLineMetrics(final String s) {
        return this.getAdjustedFont().getLineMetrics(s, this.getFontRenderContext());
    }
    
    private FontRenderContext getFontRenderContext() {
        return this.myContext.getFontRenderContext();
    }
}
