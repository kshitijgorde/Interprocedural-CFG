// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class CDATAContents extends HTMLText
{
    static final long serialVersionUID = 1L;
    private static final Font cdataContentsFont;
    
    public CDATAContents(final String tag) {
        super(tag);
    }
    
    public Font getFont() {
        return CDATAContents.cdataContentsFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CDATA_CONTENTS;
    }
    
    public String getHTML() {
        return "<span class=\"cdata\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        cdataContentsFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
