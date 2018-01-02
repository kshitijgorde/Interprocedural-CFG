// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;

public final class JavaClassName extends Definable
{
    static final long serialVersionUID = 1L;
    private static final Font fontDef;
    private static final Font fontRef;
    
    public JavaClassName(final String className, final boolean defining) {
        super(className, defining);
        if (!Character.isUpperCase(className.charAt(0))) {
            System.err.println("\u0007final class " + className + " should begin with an upper case letter.");
        }
    }
    
    public Font getFont() {
        return this.defining ? JavaClassName.fontDef : JavaClassName.fontRef;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_CLASSNAME;
    }
    
    public String getHTML() {
        return (this.defining ? "<span class=\"classdef\">" : "<span class=\"class\">") + this.getRawHTML() + "</span>";
    }
    
    static {
        fontDef = Token.bestFont(TokenFonts.MONO_FONTS, 1, 16);
        fontRef = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
