// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class CharLiteral extends Literal
{
    static final long serialVersionUID = 1L;
    private static final Font font;
    
    public CharLiteral(final char c) {
        super(c);
    }
    
    public CharLiteral(final String charLit) {
        super(charLit);
    }
    
    public Font getFont() {
        return CharLiteral.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CHAR_LITERAL;
    }
    
    public String getHTML() {
        return "<span class=\"charliteral\">" + this.getRawHTML() + "</span>";
    }
    
    public String getText() {
        return '\'' + super.getText() + '\'';
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    public boolean isUseless() {
        return false;
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
