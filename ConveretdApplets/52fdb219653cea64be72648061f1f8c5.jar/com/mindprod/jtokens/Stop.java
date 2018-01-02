// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

public final class Stop extends WhiteSpace
{
    static final long serialVersionUID = 1L;
    private final String stopHTML;
    
    public Stop(final String stopHTML) {
        super("");
        this.stopHTML = stopHTML;
    }
    
    public String getHTML() {
        return this.stopHTML;
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    public boolean isUseless() {
        return false;
    }
}
