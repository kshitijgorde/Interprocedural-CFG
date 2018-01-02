// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class StringLiteral extends Literal
{
    static final long serialVersionUID = 1L;
    private static final Font font;
    
    public StringLiteral(final char c) {
        super(c);
    }
    
    public StringLiteral(final String stringLit) {
        super(stringLit);
    }
    
    public Font getFont() {
        return StringLiteral.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_STRING_LITERAL;
    }
    
    public String getHTML() {
        return "<span class=\"string\">" + this.getRawHTML() + "</span>";
    }
    
    public String getText() {
        return '\"' + super.getText() + '\"';
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
