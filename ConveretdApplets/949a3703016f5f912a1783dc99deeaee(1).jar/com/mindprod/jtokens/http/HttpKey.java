// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.http;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class HttpKey extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font httpKeyFont;
    
    public HttpKey(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return HttpKey.httpKeyFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTTP_KEY;
    }
    
    public String getHTML() {
        return "<span class=\"httpkey\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        httpKeyFont = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
    }
}
