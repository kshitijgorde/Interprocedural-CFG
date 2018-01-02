// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class Method extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public Method(final String methodName, final boolean defining) {
        super(methodName, defining);
        final char firstChar = methodName.charAt(0);
        if (firstChar != '_' && firstChar != '$' && !Character.isLowerCase(firstChar)) {
            System.err.println("\u0007Methods must begin with a lower case letter.");
        }
    }
    
    public Font getFont() {
        return this.defining ? Method.fontDef : Method.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_METHOD;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"methoddef\">" : "<span class=\"method\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
