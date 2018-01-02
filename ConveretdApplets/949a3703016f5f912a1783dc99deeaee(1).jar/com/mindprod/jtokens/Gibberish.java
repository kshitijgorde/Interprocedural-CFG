// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class Gibberish extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font gibberishFont;
    
    public Gibberish(final String text) {
        super(text);
    }
    
    public Gibberish(final char c) {
        super(c);
    }
    
    public Font getFont() {
        return Gibberish.gibberishFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_GIBBERISH;
    }
    
    public String getHTML() {
        return "<span class=\"gibberish\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        gibberishFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
