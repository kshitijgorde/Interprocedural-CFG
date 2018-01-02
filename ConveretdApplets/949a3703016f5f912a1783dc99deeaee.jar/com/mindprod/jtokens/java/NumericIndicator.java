// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.NumericLiteral;

public final class NumericIndicator extends NumericLiteral
{
    static final long serialVersionUID = 1L;
    
    public NumericIndicator(final char c) {
        super(c);
    }
    
    public NumericIndicator(final String s) {
        super(s);
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_NUMERIC_INDICATOR;
    }
    
    public String getHTML() {
        return "<span class=\"numericindicator\">" + this.getRawHTML() + "</span>";
    }
}
