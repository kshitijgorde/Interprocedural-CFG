// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

public final class CommentJavadocTag extends CommentJavadoc
{
    static final long serialVersionUID = 1L;
    
    public CommentJavadocTag(final String comment) {
        super(comment);
    }
    
    public String getHTML() {
        return "<span class=\"javadoctag\">" + this.getRawHTML() + "</span>";
    }
}
