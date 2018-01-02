// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.prop;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class PropValue extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font propValueFont;
    
    public PropValue(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return PropValue.propValueFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_PROP_VALUE;
    }
    
    public String getHTML() {
        return "<span class=\"propvalue\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        propValueFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
