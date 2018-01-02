// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.ini;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class IniKey extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font iniKeywordFont;
    
    public IniKey(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return IniKey.iniKeywordFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_INI_KEYWORD;
    }
    
    public String getHTML() {
        return "<span class=\"inikey\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        iniKeywordFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
