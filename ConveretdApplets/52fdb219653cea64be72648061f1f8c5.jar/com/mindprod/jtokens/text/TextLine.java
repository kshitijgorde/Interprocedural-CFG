// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.text;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class TextLine extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font textFont;
    
    public TextLine(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return TextLine.textFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_TEXT_LINE;
    }
    
    static {
        textFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
