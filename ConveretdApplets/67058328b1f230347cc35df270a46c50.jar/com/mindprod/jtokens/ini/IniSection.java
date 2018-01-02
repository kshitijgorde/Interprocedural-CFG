// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.ini;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class IniSection extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font iniSectionFont;
    
    public IniSection(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return IniSection.iniSectionFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_INI_SECTION;
    }
    
    public String getHTML() {
        return "<span class=\"inisection\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        iniSectionFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 17);
    }
}
