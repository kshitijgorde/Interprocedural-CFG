// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import com.mindprod.jtokens.Comment;

public class CommentJavadoc extends Comment
{
    static final long serialVersionUID = 1L;
    
    public CommentJavadoc(final String comment) {
        super(comment);
    }
    
    public Color getForeground() {
        return TokenColourScheme.JAVADOC_FOREGROUND_FOR_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"javadoc\">" + this.getRawHTML() + "</span>";
    }
}
