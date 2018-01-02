// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;
import java.awt.Font;

public abstract class Comment extends Noise
{
    static final long serialVersionUID = 2L;
    private static final Font commentFont;
    
    public Comment(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return Comment.commentFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"comment\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        commentFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
