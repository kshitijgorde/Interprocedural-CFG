// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.prop;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class PropKey extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font propKeywordFont;
    
    public PropKey(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return PropKey.propKeywordFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.PROP_FOREGROUND_FOR_KEYWORD;
    }
    
    public String getHTML() {
        return "<span class=\"propkey\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        propKeywordFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
