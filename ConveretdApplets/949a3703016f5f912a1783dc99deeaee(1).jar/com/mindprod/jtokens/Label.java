// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public final class Label extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font labelFont;
    
    public Label(final String label) {
        super(label);
    }
    
    public Font getFont() {
        return Label.labelFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_LABEL;
    }
    
    public String getHTML() {
        return "<span class=\"label\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        labelFont = Token.bestFont(TokenFonts.MONO_FONTS, 1, 17);
    }
}
