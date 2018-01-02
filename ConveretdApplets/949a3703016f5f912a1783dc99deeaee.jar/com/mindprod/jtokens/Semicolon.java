// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import java.awt.Color;

public final class Semicolon extends Operator
{
    static final long serialVersionUID = 1L;
    
    public Semicolon() {
        super(';');
    }
    
    public Color getForeground() {
        return TokenColourScheme.FOREGROUND_FOR_SEMICOLON;
    }
    
    public String getHTML() {
        return "<span class=\"sc\">" + this.getText() + "</span>";
    }
}
