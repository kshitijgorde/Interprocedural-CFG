// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.Comment;

public final class CommentSlashStar extends Comment
{
    static final long serialVersionUID = 1L;
    
    public CommentSlashStar(final String comment) {
        super(comment);
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_COMMENT_SLASH_STAR;
    }
    
    public String getHTML() {
        return "<span class=\"commentslashstar\">" + this.getRawHTML() + "</span>";
    }
}
