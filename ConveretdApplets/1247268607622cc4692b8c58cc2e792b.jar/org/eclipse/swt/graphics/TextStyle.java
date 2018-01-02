// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;

public class TextStyle
{
    public Font font;
    public Color foreground;
    public Color background;
    public boolean underline;
    public Color underlineColor;
    public int underlineStyle;
    public boolean strikeout;
    public Color strikeoutColor;
    public int borderStyle;
    public Color borderColor;
    public GlyphMetrics metrics;
    public int rise;
    public Object data;
    
    public TextStyle() {
    }
    
    public TextStyle(final Font font, final Color foreground, final Color background) {
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        if (foreground != null && foreground.isDisposed()) {
            SWT.error(5);
        }
        if (background != null && background.isDisposed()) {
            SWT.error(5);
        }
        this.font = font;
        this.foreground = foreground;
        this.background = background;
    }
    
    public TextStyle(final TextStyle textStyle) {
        if (textStyle == null) {
            SWT.error(5);
        }
        this.font = textStyle.font;
        this.foreground = textStyle.foreground;
        this.background = textStyle.background;
        this.underline = textStyle.underline;
        this.underlineColor = textStyle.underlineColor;
        this.underlineStyle = textStyle.underlineStyle;
        this.strikeout = textStyle.strikeout;
        this.strikeoutColor = textStyle.strikeoutColor;
        this.borderStyle = textStyle.borderStyle;
        this.borderColor = textStyle.borderColor;
        this.metrics = textStyle.metrics;
        this.rise = textStyle.rise;
        this.data = textStyle.data;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof TextStyle)) {
            return false;
        }
        final TextStyle textStyle = (TextStyle)o;
        if (this.foreground != null) {
            if (!this.foreground.equals(textStyle.foreground)) {
                return false;
            }
        }
        else if (textStyle.foreground != null) {
            return false;
        }
        if (this.background != null) {
            if (!this.background.equals(textStyle.background)) {
                return false;
            }
        }
        else if (textStyle.background != null) {
            return false;
        }
        if (this.font != null) {
            if (!this.font.equals(textStyle.font)) {
                return false;
            }
        }
        else if (textStyle.font != null) {
            return false;
        }
        if (this.metrics != null || textStyle.metrics != null) {
            return false;
        }
        if (this.underline != textStyle.underline) {
            return false;
        }
        if (this.underlineStyle != textStyle.underlineStyle) {
            return false;
        }
        if (this.borderStyle != textStyle.borderStyle) {
            return false;
        }
        if (this.strikeout != textStyle.strikeout) {
            return false;
        }
        if (this.rise != textStyle.rise) {
            return false;
        }
        if (this.underlineColor != null) {
            if (!this.underlineColor.equals(textStyle.underlineColor)) {
                return false;
            }
        }
        else if (textStyle.underlineColor != null) {
            return false;
        }
        if (this.strikeoutColor != null) {
            if (!this.strikeoutColor.equals(textStyle.strikeoutColor)) {
                return false;
            }
        }
        else if (textStyle.strikeoutColor != null) {
            return false;
        }
        if (this.underlineStyle != textStyle.underlineStyle) {
            return false;
        }
        if (this.borderColor != null) {
            if (!this.borderColor.equals(textStyle.borderColor)) {
                return false;
            }
        }
        else if (textStyle.borderColor != null) {
            return false;
        }
        if (this.data != null) {
            if (!this.data.equals(textStyle.data)) {
                return false;
            }
        }
        else if (textStyle.data != null) {
            return false;
        }
        return true;
    }
    
    public int hashCode() {
        int n = 0;
        if (this.foreground != null) {
            n ^= this.foreground.hashCode();
        }
        if (this.background != null) {
            n ^= this.background.hashCode();
        }
        if (this.font != null) {
            n ^= this.font.hashCode();
        }
        if (this.metrics != null) {
            n ^= this.metrics.hashCode();
        }
        if (this.underline) {
            n ^= n << 1;
        }
        if (this.strikeout) {
            n ^= n << 2;
        }
        int n2 = n ^ this.rise;
        if (this.underlineColor != null) {
            n2 ^= this.underlineColor.hashCode();
        }
        if (this.strikeoutColor != null) {
            n2 ^= this.strikeoutColor.hashCode();
        }
        if (this.borderColor != null) {
            n2 ^= this.borderColor.hashCode();
        }
        return n2 ^ this.underlineStyle;
    }
    
    boolean isAdherentBorder(final TextStyle textStyle) {
        if (this == textStyle) {
            return true;
        }
        if (textStyle == null) {
            return false;
        }
        if (this.borderStyle != textStyle.borderStyle) {
            return false;
        }
        if (this.borderColor != null) {
            if (!this.borderColor.equals(textStyle.borderColor)) {
                return false;
            }
        }
        else {
            if (textStyle.borderColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals(textStyle.foreground)) {
                    return false;
                }
            }
            else if (textStyle.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    boolean isAdherentUnderline(final TextStyle textStyle) {
        if (this == textStyle) {
            return true;
        }
        if (textStyle == null) {
            return false;
        }
        if (this.underline != textStyle.underline) {
            return false;
        }
        if (this.underlineStyle != textStyle.underlineStyle) {
            return false;
        }
        if (this.underlineColor != null) {
            if (!this.underlineColor.equals(textStyle.underlineColor)) {
                return false;
            }
        }
        else {
            if (textStyle.underlineColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals(textStyle.foreground)) {
                    return false;
                }
            }
            else if (textStyle.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    boolean isAdherentStrikeout(final TextStyle textStyle) {
        if (this == textStyle) {
            return true;
        }
        if (textStyle == null) {
            return false;
        }
        if (this.strikeout != textStyle.strikeout) {
            return false;
        }
        if (this.strikeoutColor != null) {
            if (!this.strikeoutColor.equals(textStyle.strikeoutColor)) {
                return false;
            }
        }
        else {
            if (textStyle.strikeoutColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals(textStyle.foreground)) {
                    return false;
                }
            }
            else if (textStyle.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("TextStyle {");
        final int length = sb.length();
        if (this.font != null) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("font=");
            sb.append(this.font);
        }
        if (this.foreground != null) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("foreground=");
            sb.append(this.foreground);
        }
        if (this.background != null) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("background=");
            sb.append(this.background);
        }
        if (this.underline) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("underline=");
            switch (this.underlineStyle) {
                case 0: {
                    sb.append("single");
                    break;
                }
                case 1: {
                    sb.append("double");
                    break;
                }
                case 3: {
                    sb.append("squiggle");
                    break;
                }
                case 2: {
                    sb.append("error");
                    break;
                }
                case 4: {
                    sb.append("link");
                    break;
                }
            }
            if (this.underlineColor != null) {
                sb.append(", underlineColor=");
                sb.append(this.underlineColor);
            }
        }
        if (this.strikeout) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("striked out");
            if (this.strikeoutColor != null) {
                sb.append(", strikeoutColor=");
                sb.append(this.strikeoutColor);
            }
        }
        if (this.borderStyle != 0) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("border=");
            switch (this.borderStyle) {
                case 1: {
                    sb.append("solid");
                    break;
                }
                case 4: {
                    sb.append("dot");
                    break;
                }
                case 2: {
                    sb.append("dash");
                    break;
                }
            }
            if (this.borderColor != null) {
                sb.append(", borderColor=");
                sb.append(this.borderColor);
            }
        }
        if (this.rise != 0) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("rise=");
            sb.append(this.rise);
        }
        if (this.metrics != null) {
            if (sb.length() > length) {
                sb.append(", ");
            }
            sb.append("metrics=");
            sb.append(this.metrics);
        }
        sb.append("}");
        return sb.toString();
    }
}
