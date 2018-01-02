// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public class NumericLiteral extends Literal
{
    static final long serialVersionUID = 1L;
    private static final Font font;
    
    public NumericLiteral(final String s) {
        super(s);
    }
    
    public NumericLiteral(final char c) {
        super(c);
    }
    
    public Font getFont() {
        return NumericLiteral.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_NUMERIC_LITERAL;
    }
    
    public String getHTML() {
        return "<span class=\"numericliteral\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
