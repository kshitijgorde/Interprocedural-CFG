// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.ini;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class IniValue extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font iniValueFont;
    
    public IniValue(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return IniValue.iniValueFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_INI_VALUE;
    }
    
    public String getHTML() {
        return "<span class=\"inivalue\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        iniValueFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
