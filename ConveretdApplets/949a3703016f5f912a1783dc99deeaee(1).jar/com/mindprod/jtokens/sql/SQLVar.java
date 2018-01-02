// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.sql;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class SQLVar extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font font;
    
    public SQLVar(final String varName) {
        super(varName);
    }
    
    public Font getFont() {
        return SQLVar.font;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_SQL_VAR;
    }
    
    static {
        font = Token.bestFont(TokenFonts.MONO_FONTS, 0, 16);
    }
}
