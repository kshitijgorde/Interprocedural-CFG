// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.prop;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Literal;

public final class UnicodeLiteral extends Literal
{
    static final long serialVersionUID = 1L;
    private static final Font font;
    
    public UnicodeLiteral(final String suxxxx) {
        super(suxxxx);
    }
    
    public Font getFont() {
        return UnicodeLiteral.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_UNICODE_LITERAL;
    }
    
    public String getHTML() {
        return "<span class=\"unicodeliteral\">" + this.getRawHTML() + "</span>";
    }
    
    public boolean isUseless() {
        return false;
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
