// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.Comment;

public final class CommentSlashSlash extends Comment
{
    static final long serialVersionUID = 1L;
    
    public CommentSlashSlash(final String comment) {
        super(comment);
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_COMMENT_SLASH_SLASH;
    }
    
    public String getHTML() {
        return "<span class=\"commentslashslash\">" + this.getRawHTML() + "</span>";
    }
}
