// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

public final class Space extends WhiteSpace
{
    static final long serialVersionUID = 1L;
    private static final String blanks = "                                                                                                              ";
    private int count;
    
    public Space() {
        super(null);
        this.count = 1;
    }
    
    public Space(final int count) {
        super(null);
        assert count > 0 : "Space 0";
        this.count = count;
    }
    
    public String getText() {
        return "                                                                                                              ".substring(0, Math.min("                                                                                                              ".length(), this.count));
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    public boolean isUseless() {
        return this.count == 0;
    }
    
    public int length() {
        return this.count;
    }
    
    public void setText(final String x) {
        this.count = x.length();
    }
    
    public String toString() {
        return "Token: " + this.getName() + "\n" + "                spaces: " + this.count + "\n";
    }
}
