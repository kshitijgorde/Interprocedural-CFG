// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class HTMLAttribute extends HTMLText
{
    static final long serialVersionUID = 1L;
    private static final Font htmlAttributeFont;
    
    public HTMLAttribute(final String tag) {
        super(tag);
    }
    
    public Font getFont() {
        return HTMLAttribute.htmlAttributeFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTML_ATTRIBUTE;
    }
    
    public String getHTML() {
        return "<span class=\"htmlattribute\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        htmlAttributeFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
