// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.bat;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Comment;

public final class BatComment extends Comment
{
    static final long serialVersionUID = 1L;
    private static final Font batCommentFont;
    
    public BatComment(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return BatComment.batCommentFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_BAT_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"batcomment\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        batCommentFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
