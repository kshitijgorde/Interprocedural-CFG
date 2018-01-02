// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.CloneableCompatibility;
import org.eclipse.swt.graphics.TextStyle;

public class StyleRange extends TextStyle implements CloneableCompatibility
{
    public int start;
    public int length;
    public int fontStyle;
    
    public StyleRange() {
        this.fontStyle = 0;
    }
    
    public StyleRange(final TextStyle textStyle) {
        super(textStyle);
        this.fontStyle = 0;
    }
    
    public StyleRange(final int start, final int length, final Color color, final Color color2) {
        super(null, color, color2);
        this.fontStyle = 0;
        this.start = start;
        this.length = length;
    }
    
    public StyleRange(final int n, final int n2, final Color color, final Color color2, final int fontStyle) {
        this(n, n2, color, color2);
        this.fontStyle = fontStyle;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof StyleRange) {
            final StyleRange styleRange = (StyleRange)o;
            return this.start == styleRange.start && this.length == styleRange.length && this.similarTo(styleRange);
        }
        return false;
    }
    
    public int hashCode() {
        return super.hashCode() ^ this.fontStyle;
    }
    
    boolean isVariableHeight() {
        return this.font != null || this.metrics != null || this.rise != 0;
    }
    
    public boolean isUnstyled() {
        return this.font == null && this.rise == 0 && this.metrics == null && this.foreground == null && this.background == null && this.fontStyle == 0 && !this.underline && !this.strikeout && this.borderStyle == 0;
    }
    
    public boolean similarTo(final StyleRange styleRange) {
        return super.equals(styleRange) && this.fontStyle == styleRange.fontStyle;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("StyleRange {");
        sb.append(this.start);
        sb.append(", ");
        sb.append(this.length);
        sb.append(", fontStyle=");
        switch (this.fontStyle) {
            case 1: {
                sb.append("bold");
                break;
            }
            case 2: {
                sb.append("italic");
                break;
            }
            case 3: {
                sb.append("bold-italic");
                break;
            }
            default: {
                sb.append("normal");
                break;
            }
        }
        final String string = super.toString();
        final String substring = string.substring(string.indexOf(123) + 1);
        if (substring.length() > 1) {
            sb.append(", ");
        }
        sb.append(substring);
        return sb.toString();
    }
}
