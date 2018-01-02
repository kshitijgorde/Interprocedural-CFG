// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.prop;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Comment;

public final class PropComment extends Comment
{
    static final long serialVersionUID = 1L;
    private static final Font propCommentFont;
    
    public PropComment(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return PropComment.propCommentFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.PROP_FOREGROUND_FOR_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"propcomment\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        propCommentFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
