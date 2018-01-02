// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Comment;

public final class HTMLCommentTag extends Comment
{
    static final long serialVersionUID = 3L;
    private static final Font htmlCommentTagFont;
    
    public HTMLCommentTag(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return HTMLCommentTag.htmlCommentTagFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_HTML_COMMENT_TAG;
    }
    
    public String getHTML() {
        return "<span class=\"hct\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        htmlCommentTagFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 15);
    }
}
