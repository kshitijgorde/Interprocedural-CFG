// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class HTMLTag extends HTMLText
{
    static final long serialVersionUID = 1L;
    private static final Font htmlTagfont;
    
    public HTMLTag(final String tag) {
        super(tag);
    }
    
    public Font getFont() {
        return HTMLTag.htmlTagfont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTML_HTML_TAG;
    }
    
    public String getHTML() {
        return "<span class=\"htmltag\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        htmlTagfont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
