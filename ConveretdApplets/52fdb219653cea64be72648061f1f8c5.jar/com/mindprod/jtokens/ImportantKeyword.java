// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class ImportantKeyword extends Keyword
{
    static final long serialVersionUID = 2L;
    private static final Font importantKeywordFont;
    
    public ImportantKeyword(final String keyword) {
        super(keyword);
    }
    
    public Font getFont() {
        return ImportantKeyword.importantKeywordFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_IMPORTANT_KEYWORD;
    }
    
    public String getHTML() {
        return "<span class=\"ik\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        importantKeywordFont = Token.bestFont(TokenFonts.KEYWORD_FONTS, 1, 17);
    }
}
