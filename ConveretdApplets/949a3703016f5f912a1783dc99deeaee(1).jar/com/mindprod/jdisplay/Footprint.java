// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jdisplay;

import java.awt.Font;
import java.awt.FontMetrics;
import com.mindprod.jtokens.NL;
import java.awt.Component;
import com.mindprod.jtokens.Token;
import java.io.Serializable;

public final class Footprint implements Cloneable, Serializable
{
    public static final long serialVersionUID = 28L;
    public transient Rendering rendering;
    public transient String wayRendered;
    public transient boolean hasBar;
    public transient boolean hasHScroll;
    public transient boolean hasLineNumbers;
    public transient boolean hasVScroll;
    public transient int actualAPPLET_HEIGHT;
    public transient int actualAPPLET_WIDTH;
    public int htmlLengthInChars;
    public transient int idealAppletHeight;
    public transient int idealAppletWidth;
    public int lineNumberWidthInPixels;
    public int payloadHeight;
    public int payloadWidth;
    public transient int scrollableHeight;
    public transient int scrollableWidth;
    public transient int scrollableWidthWithLineNumbers;
    public transient int scrollableWidthWithoutLineNumbers;
    public int totalLines;
    public int viewportHeight;
    public int viewportWidth;
    private int fatHeight;
    private int fatWidth;
    
    public void calcActualAppletFootPrint(final Rendering rendering, final boolean hasBar, final boolean hasLineNumbers, final boolean hasHScroll, final boolean hasVScroll, final int minWidth, final int minHeight, final int maxWidth, final int maxHeight, final float safetyFactor) {
        this.s4CalcScrollableFootprint(rendering);
        this.s5CalcIdealAppletFootPrint(rendering, hasBar, hasLineNumbers, hasHScroll, hasVScroll, safetyFactor);
        this.s6CalcActualAppletFootPrint(minWidth, minHeight, maxWidth, maxHeight);
    }
    
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public void s1CalcHTMLLength(final Token[] tokens) {
        int htmlSize = 0;
        for (final Token t : tokens) {
            htmlSize += t.getHTML().length();
        }
        this.htmlLengthInChars = htmlSize;
    }
    
    public void s2CalcPayloadFootprint(final Token[] tokens, final Component panel) {
        int widest = 0;
        int height = 0;
        int totalLines = 0;
        if (tokens != null) {
            int x = 0;
            for (final Token t : tokens) {
                if (t instanceof NL) {
                    final int lines = ((NL)t).getCount();
                    switch (lines) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            height += 23;
                            break;
                        }
                        case 2: {
                            height += 33;
                            break;
                        }
                        default: {
                            height += 43;
                            break;
                        }
                    }
                    totalLines += lines;
                    x = 0;
                }
                else {
                    final String text = t.getText();
                    final FontMetrics fm = getFontMetrics(t.getFont(), panel);
                    x += fm.stringWidth(text);
                    if (x > widest) {
                        widest = x;
                    }
                }
            }
        }
        ++totalLines;
        height += 23;
        if (totalLines == 0) {
            throw new IllegalArgumentException("empty snippet");
        }
        this.totalLines = totalLines;
        this.payloadWidth = widest;
        this.payloadHeight = height;
        this.calcLineNumberWidthInPixels(panel);
    }
    
    public void s3CalcFat(final Token[] tokens) {
        this.fatWidth = 0;
        this.fatHeight = 0;
        for (final Token t : tokens) {
            if (t instanceof NL) {
                final int lines = ((NL)t).getCount();
                switch (lines) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        this.fatHeight += 13;
                        break;
                    }
                    default: {
                        this.fatHeight += 26;
                        break;
                    }
                }
            }
        }
    }
    
    public void s4CalcScrollableFootprint(final Rendering rendering) {
        this.rendering = rendering;
        int xPadding = 0;
        int yPadding = 0;
        int lPadding = 0;
        xPadding += 12;
        yPadding += 8;
        switch (rendering) {
            case APPLET: {
                lPadding = 3 + this.lineNumberWidthInPixels;
                break;
            }
            case INLINE:
            case IFRAME: {
                xPadding += this.fatWidth;
                yPadding += this.fatHeight;
                break;
            }
        }
        this.scrollableWidthWithoutLineNumbers = this.payloadWidth + xPadding;
        this.scrollableWidth = this.scrollableWidthWithoutLineNumbers;
        this.scrollableWidthWithLineNumbers = this.payloadWidth + xPadding + lPadding;
        this.scrollableHeight = this.payloadHeight + yPadding;
    }
    
    public void s5CalcIdealAppletFootPrint(final Rendering rendering, final boolean hasBar, final boolean hasLineNumbers, final boolean hasHScroll, final boolean hasVScroll, final float safetyFactor) {
        this.rendering = rendering;
        this.hasBar = hasBar;
        this.hasLineNumbers = hasLineNumbers;
        this.hasHScroll = hasHScroll;
        this.hasVScroll = hasVScroll;
        int xPadding = 0;
        int yPadding = 0;
        switch (rendering) {
            case IFRAME: {
                if (hasVScroll) {
                    xPadding += 15;
                }
                if (hasHScroll) {
                    yPadding += 16;
                    break;
                }
                break;
            }
            case APPLET: {
                xPadding += 2;
                yPadding += 2;
                if (hasBar) {
                    yPadding += 29;
                }
                if (hasVScroll) {
                    xPadding += 15;
                }
                if (hasHScroll) {
                    yPadding += 16;
                    break;
                }
                break;
            }
        }
        this.scrollableWidth = (hasLineNumbers ? this.scrollableWidthWithLineNumbers : this.scrollableWidthWithoutLineNumbers);
        this.idealAppletWidth = (int)((this.scrollableWidth + xPadding) * safetyFactor);
        this.idealAppletHeight = this.scrollableHeight + yPadding;
    }
    
    public void s6CalcActualAppletFootPrint(int minWidth, int minHeight, final int maxWidth, final int maxHeight) {
        if (this.hasHScroll) {
            minWidth = Math.max(minWidth, 28 + (this.hasVScroll ? 15 : 0));
            if (minWidth <= maxWidth) {
                this.actualAPPLET_WIDTH = Math.min(Math.max(minWidth, this.idealAppletWidth), maxWidth);
            }
            else {
                this.actualAPPLET_WIDTH = 0;
            }
        }
        else if (this.idealAppletWidth <= maxWidth) {
            this.actualAPPLET_WIDTH = Math.max(minWidth, this.idealAppletWidth);
        }
        else {
            this.actualAPPLET_WIDTH = 0;
        }
        if (this.hasVScroll) {
            minHeight = Math.max(minHeight, 28 + (this.hasBar ? 29 : 0) + (this.hasHScroll ? 16 : 0));
            if (minHeight <= maxHeight) {
                this.actualAPPLET_HEIGHT = Math.min(Math.max(minHeight, this.idealAppletHeight), maxHeight);
            }
            else {
                this.actualAPPLET_HEIGHT = 0;
            }
        }
        else if (this.idealAppletHeight <= maxHeight) {
            this.actualAPPLET_HEIGHT = Math.max(minHeight, this.idealAppletHeight);
        }
        else {
            this.actualAPPLET_HEIGHT = 0;
        }
        if (this.actualAPPLET_WIDTH <= 0 || this.actualAPPLET_HEIGHT <= 0) {
            this.actualAPPLET_WIDTH = 0;
            this.actualAPPLET_HEIGHT = 0;
            this.viewportWidth = 0;
            this.viewportHeight = 0;
        }
        else {
            this.viewportWidth = this.actualAPPLET_WIDTH;
            if (this.hasVScroll) {
                this.viewportWidth -= 15;
            }
            this.viewportHeight = this.actualAPPLET_HEIGHT;
            if (this.hasHScroll) {
                this.viewportHeight -= 16;
            }
            if (this.hasBar) {
                this.viewportHeight -= 29;
            }
            if (this.viewportWidth <= 0 || this.viewportHeight <= 0) {
                this.viewportWidth = 0;
                this.viewportHeight = 0;
            }
        }
    }
    
    public String toString() {
        return new StringBuilder(400).append("Footprint").append(" scroll:").append(this.hasHScroll).append("/").append(this.hasVScroll).append(" lines:").append(this.totalLines).append(" bar:").append(this.hasBar).append(" lineNumbers:").append(this.hasLineNumbers).append(" rendering:").append(this.rendering).append(" way:").append(this.wayRendered).append(" uid:").append(28L).append("\n       payload:").append(this.payloadWidth).append("x").append(this.payloadHeight).append("\n    scrollable:").append(this.scrollableWidth).append("x").append(this.scrollableHeight).append("\n      viewport:").append(this.viewportWidth).append("x").append(this.viewportHeight).append("\n        actual:").append(this.actualAPPLET_WIDTH).append("x").append(this.actualAPPLET_HEIGHT).append("\n         ideal:").append(this.idealAppletWidth).append("x").append(this.idealAppletHeight).append("\n           fat:").append(this.fatWidth).append("x").append(this.fatHeight).toString();
    }
    
    static FontMetrics getFontMetrics(final Font font, final Component panel) {
        if (font == null) {
            throw new IllegalArgumentException("null Font");
        }
        final FontMetrics fm = panel.getFontMetrics(font);
        if (fm == null) {
            throw new IllegalArgumentException("no FontMetrics available for font " + font);
        }
        return fm;
    }
    
    private void calcLineNumberWidthInPixels(final Component panel) {
        int widestDigitInPixels = 0;
        final FontMetrics fm = getFontMetrics(Token.getLineNumberFont(), panel);
        for (int i = 0; i < 10; ++i) {
            final int widthOfDigit = fm.stringWidth(String.valueOf((char)(48 + i)));
            if (widthOfDigit > widestDigitInPixels) {
                widestDigitInPixels = widthOfDigit;
            }
        }
        this.lineNumberWidthInPixels = Integer.toString(this.totalLines).length() * widestDigitInPixels;
    }
}
