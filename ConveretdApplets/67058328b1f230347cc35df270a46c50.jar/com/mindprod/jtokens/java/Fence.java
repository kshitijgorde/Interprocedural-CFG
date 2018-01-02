// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.awt.Color;
import java.awt.Font;
import com.mindprod.jtokens.Token;

public final class Fence extends Token
{
    static final long serialVersionUID = 1L;
    private static final Font[] font;
    private final char fenceChar;
    private int nestingDepth;
    
    public Fence(final char fenceChar, final int nestingDepth) {
        super(fenceChar);
        this.fenceChar = fenceChar;
        this.nestingDepth = nestingDepth;
    }
    
    public char getChar() {
        return this.fenceChar;
    }
    
    public Font getFont() {
        int i = 0;
        switch (this.fenceChar) {
            case '(':
            case ')':
            case '[':
            case ']': {
                i = this.nestingDepth + 2;
                break;
            }
            default: {
                i = this.nestingDepth;
                break;
            }
        }
        if (i < 0) {
            i = 0;
        }
        if (i >= 5) {
            i = 4;
        }
        return Fence.font[i];
    }
    
    public Color getForeground() {
        switch (this.fenceChar) {
            case '(':
            case ')': {
                return TokenColourScheme.FOREGROUND_FOR_FENCE_PAREN;
            }
            case '[':
            case ']': {
                return TokenColourScheme.FOREGROUND_FOR_FENCE_BRACKET;
            }
            default: {
                return TokenColourScheme.FOREGROUND_FOR_FENCE_BRACE;
            }
        }
    }
    
    public String getHTML() {
        switch (this.nestingDepth) {
            case 0: {
                return "<span class=\"fence0\">" + this.getRawHTML() + "</span>";
            }
            case 1: {
                return "<span class=\"fence1\">" + this.getRawHTML() + "</span>";
            }
            case 2: {
                return "<span class=\"fence2\">" + this.getRawHTML() + "</span>";
            }
            default: {
                return "<span class=\"fence3\">" + this.getRawHTML() + "</span>";
            }
        }
    }
    
    public int getNestingDepth() {
        return this.nestingDepth;
    }
    
    public void setNestingDepth(int nestingDepth) {
        if (nestingDepth < 0) {
            nestingDepth = 0;
        }
        this.nestingDepth = nestingDepth;
    }
    
    public String getText() {
        return String.valueOf(this.fenceChar);
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    static {
        font = new Font[5];
        for (int i = 0; i < 5; ++i) {
            final int size = 19 - i;
            final int style = (size >= 16) ? 1 : 0;
            Fence.font[i] = Token.bestFont(TokenFonts.MONO_FONTS, style, size);
        }
    }
}
