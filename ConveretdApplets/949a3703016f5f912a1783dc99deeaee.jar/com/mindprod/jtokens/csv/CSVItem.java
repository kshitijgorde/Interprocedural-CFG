// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.csv;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class CSVItem extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font csvItemFont;
    
    public CSVItem(final String text) {
        super(text);
    }
    
    public Font getFont() {
        return CSVItem.csvItemFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CSV_ITEM;
    }
    
    public String getHTML() {
        return "<span class=\"csvitem\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        csvItemFont = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
