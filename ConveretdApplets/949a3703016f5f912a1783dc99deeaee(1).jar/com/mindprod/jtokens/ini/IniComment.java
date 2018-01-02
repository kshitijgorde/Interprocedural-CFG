// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.ini;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Comment;

public final class IniComment extends Comment
{
    static final long serialVersionUID = 1L;
    private static final Font iniCommentFont;
    
    public IniComment(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return IniComment.iniCommentFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.INI_FOREGROUND_FOR_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"inicomment\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        iniCommentFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
