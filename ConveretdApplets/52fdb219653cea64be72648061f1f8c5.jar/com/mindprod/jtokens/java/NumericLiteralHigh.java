// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.NumericLiteral;

public final class NumericLiteralHigh extends NumericLiteral
{
    static final long serialVersionUID = 2L;
    private final int base;
    
    public NumericLiteralHigh(final char c, final int base) {
        super(c);
        this.base = base;
    }
    
    public NumericLiteralHigh(final String s, final int base) {
        super(s);
        this.base = base;
    }
    
    public Color getForeground() {
        switch (this.base) {
            case 2: {
                return TokenColourScheme.FOREGROUND_FOR_BINARY_HIGH;
            }
            case 8: {
                return TokenColourScheme.FOREGROUND_FOR_OCTAL_HIGH;
            }
            case 10: {
                return TokenColourScheme.FOREGROUND_FOR_DECIMAL_HIGH;
            }
            case 16: {
                return TokenColourScheme.FOREGROUND_FOR_HEX_HIGH;
            }
            default: {
                return TokenColourScheme.FOREGROUND_FOR_NUMERIC_HIGH;
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
        return "<span class=\"" + baseword + "high\">" + this.getRawHTML() + "</span>";
    }
}
