// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.bat;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class BatText extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font batCommandFont;
    
    public BatText(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return BatText.batCommandFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_BAT_TEXT;
    }
    
    static {
        batCommandFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
