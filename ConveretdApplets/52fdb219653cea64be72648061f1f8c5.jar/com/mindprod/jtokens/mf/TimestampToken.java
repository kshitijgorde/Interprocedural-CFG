// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.mf;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class TimestampToken extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font propValueFont;
    
    public TimestampToken(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return TimestampToken.propValueFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_TIMESTAMP;
    }
    
    public String getHTML() {
        return "<span class=\"date\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        propValueFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
