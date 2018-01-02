// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

public enum AlignJavaCommentState
{
    OUTSIDE_QUOTES, 
    INSIDE_QUOTES, 
    INSIDE_SINGLE_QUOTES, 
    SEEN_QUOTE_BACK, 
    SEEN_SINGLE_QUOTE_BACK, 
    INSIDE_STAR_COMMENT, 
    SEEN_SLASH, 
    SEEN_SLASH_STAR_STAR, 
    INSIDE_SLASH_SLASH;
}
