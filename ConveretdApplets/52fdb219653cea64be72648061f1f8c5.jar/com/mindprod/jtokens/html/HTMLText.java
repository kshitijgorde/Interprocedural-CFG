// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public class HTMLText extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font htmlTextfont;
    
    public HTMLText(final String html) {
        super(html);
    }
    
    public Font getFont() {
        return HTMLText.htmlTextfont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTML_TEXT;
    }
    
    static {
        htmlTextfont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
