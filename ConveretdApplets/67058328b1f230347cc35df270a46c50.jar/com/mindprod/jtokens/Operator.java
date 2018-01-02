// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public class Operator extends Token
{
    static final long serialVersionUID = 2L;
    private static final Font font;
    
    public Operator(final char operator) {
        super(operator);
    }
    
    public Operator(final String operator) {
        super(operator);
    }
    
    public Font getFont() {
        return Operator.font;
    }
    
    public Color getForeground() {
        return this.isDivOperator() ? TokenColourScheme.FOREGROUND_FOR_DIV_OPERATOR : TokenColourScheme.FOREGROUND_FOR_OPERATOR;
    }
    
    public String getHTML() {
        final String cssClass = this.isDivOperator() ? "divoperator" : "op";
        return "<span class=\"" + cssClass + "\">" + this.getRawHTML() + "</span>";
    }
    
    private boolean isDivOperator() {
        final String op = this.text.trim();
        return op.equals("/") || op.equals("%");
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 1, 17);
    }
}
