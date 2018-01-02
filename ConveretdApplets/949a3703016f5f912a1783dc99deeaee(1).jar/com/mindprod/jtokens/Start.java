// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

public final class Start extends WhiteSpace
{
    static final long serialVersionUID = 1L;
    private final String startHTML;
    
    public Start(final String startHTML) {
        super("");
        this.startHTML = startHTML;
    }
    
    public String getHTML() {
        return this.startHTML;
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    public boolean isUseless() {
        return false;
    }
}
