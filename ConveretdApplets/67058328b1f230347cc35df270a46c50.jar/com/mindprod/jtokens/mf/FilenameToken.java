// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.mf;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class FilenameToken extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font propValueFont;
    
    public FilenameToken(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return FilenameToken.propValueFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_FILENAME;
    }
    
    public String getHTML() {
        return "<span class=\"file\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        propValueFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
