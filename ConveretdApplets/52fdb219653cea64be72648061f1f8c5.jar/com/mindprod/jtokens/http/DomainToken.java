// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.http;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class DomainToken extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font domainFont;
    
    public DomainToken(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return DomainToken.domainFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_DOMAIN;
    }
    
    public String getHTML() {
        return "<span class=\"domain\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        domainFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
