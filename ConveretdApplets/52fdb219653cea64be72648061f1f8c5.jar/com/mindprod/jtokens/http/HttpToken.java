// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.http;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class HttpToken extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font httpFont;
    
    public HttpToken(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return HttpToken.httpFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTTP;
    }
    
    public String getHTML() {
        return "<span class=\"http\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        httpFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
