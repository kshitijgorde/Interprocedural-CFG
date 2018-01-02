// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class Annotation extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font fontRef;
    
    public Annotation(final String annotation) {
        super(annotation);
    }
    
    public Font getFont() {
        return Annotation.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_ANNOTATION;
    }
    
    public String getHTML() {
        return "<span class=\"annotation\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
