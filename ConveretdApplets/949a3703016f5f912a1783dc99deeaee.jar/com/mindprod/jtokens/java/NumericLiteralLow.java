// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.NumericLiteral;

public final class NumericLiteralLow extends NumericLiteral
{
    static final long serialVersionUID = 2L;
    private final int base;
    
    public NumericLiteralLow(final char c, final int base) {
        super(c);
        this.base = base;
    }
    
    public NumericLiteralLow(final String s, final int base) {
        super(s);
        this.base = base;
    }
    
    public Color getForeground() {
        switch (this.base) {
            case 2: {
                return TokenColourScheme.FOREGROUND_FOR_BINARY_LOW;
            }
            case 8: {
                return TokenColourScheme.FOREGROUND_FOR_OCTAL_LOW;
            }
            case 10: {
                return TokenColourScheme.FOREGROUND_FOR_DECIMAL_LOW;
            }
            case 16: {
                return TokenColourScheme.FOREGROUND_FOR_HEX_LOW;
            }
            default: {
                return TokenColourScheme.FOREGROUND_FOR_NUMERIC_LOW;
            }
        }
    }
    
    public String getHTML() {
        String baseword = null;
        switch (this.base) {
            case 2: {
                baseword = "binary";
                break;
            }
            case 8: {
                baseword = "octal";
                break;
            }
            case 10: {
                baseword = "decimal";
                break;
            }
            case 16: {
                baseword = "hex";
                break;
            }
            default: {
                baseword = "numeric";
                break;
            }
        }
        return "<span class=\"" + baseword + "low\">" + this.getRawHTML() + "</span>";
    }
}
