// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class CDATATag extends HTMLText
{
    static final long serialVersionUID = 1L;
    private static final Font cdataTagFont;
    
    public CDATATag(final String tag) {
        super(tag);
    }
    
    public Font getFont() {
        return CDATATag.cdataTagFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CDATA_TAG;
    }
    
    public String getHTML() {
        return "<span class=\"cdatatag\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        cdataTagFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 15);
    }
}
