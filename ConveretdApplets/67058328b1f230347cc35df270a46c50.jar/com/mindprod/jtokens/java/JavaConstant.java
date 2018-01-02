// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class JavaConstant extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public JavaConstant(final String constantName, final boolean defining) {
        super(constantName, defining);
        if (!constantName.toUpperCase().equals(constantName)) {
            System.err.println("\u0007Constants must be all upper case.");
        }
    }
    
    public Font getFont() {
        return this.defining ? JavaConstant.fontDef : JavaConstant.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CCONSTANT;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"constantdef\">" : "<span class=\"constant\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
