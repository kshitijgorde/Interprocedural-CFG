// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Literal;

public final class HTMLStringLiteral extends Literal
{
    static final long serialVersionUID = 2L;
    private static final Font font;
    
    public HTMLStringLiteral(final String contents) {
        super(contents);
    }
    
    public Font getFont() {
        return HTMLStringLiteral.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTML_STRING_LITERAL;
    }
    
    public String getHTML() {
        return "<span class=\"htmlstring\">" + this.getRawHTML() + "</span>";
    }
    
    public boolean isCollapsible() {
        return true;
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
