// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public class Keyword extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font keywordFont;
    
    public Keyword(final String keyword) {
        super(keyword);
    }
    
    public Font getFont() {
        return Keyword.keywordFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_KEYWORD;
    }
    
    public String getHTML() {
        return "<span class=\"k\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        keywordFont = Token.bestFont(TokenFonts.KEYWORD_FONTS, 1, 16);
    }
}
