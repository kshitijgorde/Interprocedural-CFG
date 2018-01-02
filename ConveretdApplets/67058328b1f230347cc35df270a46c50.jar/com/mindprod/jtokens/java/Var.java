// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class Var extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public Var(final String varName, final boolean defining) {
        super(varName, defining);
        final char firstLetter = varName.charAt(0);
        if (firstLetter != '_' && firstLetter != '$' && !Character.isLowerCase(firstLetter)) {
            System.err.println("\u0007variable " + varName + " should begin with a lower case letter.");
        }
    }
    
    public Font getFont() {
        return this.defining ? Var.fontDef : Var.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_JAVA_VAR;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"vardef\">" : "<span class=\"var\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
