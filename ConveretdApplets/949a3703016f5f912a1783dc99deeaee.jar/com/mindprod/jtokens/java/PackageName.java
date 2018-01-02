// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class PackageName extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public PackageName(final String packageFragment, final boolean defining) {
        super(packageFragment, defining);
        if (!packageFragment.toLowerCase().equals(packageFragment)) {
            System.err.println("\u0007Packages must be all lower case.");
        }
    }
    
    public Font getFont() {
        return this.defining ? PackageName.fontDef : PackageName.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_PACKAGE;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"packagedef\">" : "<span class=\"package\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
