// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class InterfaceName extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public InterfaceName(final String interfaceName, final boolean defining) {
        super(interfaceName, defining);
        if (!Character.isUpperCase(interfaceName.charAt(0))) {
            System.err.println("\u0007interface " + interfaceName + " should begin with an upper case letter.");
        }
    }
    
    public Font getFont() {
        return this.defining ? InterfaceName.fontDef : InterfaceName.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_INTERFACE_NAME;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"interfacedef\">" : "<span class=\"interface\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 3, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
