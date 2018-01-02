// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.csv;

import com.mindprod.jtokens.Token;
import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Comment;

public final class CSVComment extends Comment
{
    static final long serialVersionUID = 1L;
    private static final Font csvCommentFont;
    
    public CSVComment(final String comment) {
        super(comment);
    }
    
    public Font getFont() {
        return CSVComment.csvCommentFont;
    }
    
    public Color getForeground() {
        return TokenColourScheme.CSV_FOREGROUND_FOR_COMMENT;
    }
    
    public String getHTML() {
        return "<span class=\"csvcomment\">" + this.getRawHTML() + "</span>";
    }
    
    static {
        csvCommentFont = Token.bestFont(TokenFonts.MONO_FONTS, 2, 16);
    }
}
