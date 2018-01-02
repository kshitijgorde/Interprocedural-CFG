// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class Separator extends Operator
{
    static final long serialVersionUID = 1L;
    private static final Font separatorFont;
    
    public Separator(final char separator) {
        super(separator);
    }
    
    public Separator(final String separator) {
        super(separator);
    }
    
    public Font getFont() {
        return Separator.separatorFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_SEPARATOR;
    }
    
    public String getHTML() {
        return "<span class=\"sep\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        separatorFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
