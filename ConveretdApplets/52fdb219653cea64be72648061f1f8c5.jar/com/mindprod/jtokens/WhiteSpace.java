// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public abstract class WhiteSpace extends Noise
{
    static final long serialVersionUID = 2L;
    private static final Font font;
    
    public Font getFont() {
        return WhiteSpace.font;
    }
    
    public Color getForeground() {
        return Color.WHITE;
    }
    
    public String getHTML() {
        return this.getText();
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    WhiteSpace(final String text) {
        super(text);
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
