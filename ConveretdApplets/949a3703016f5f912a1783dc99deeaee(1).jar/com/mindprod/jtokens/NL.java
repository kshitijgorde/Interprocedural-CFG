// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

public final class NL extends WhiteSpace
{
    static final long serialVersionUID = 1L;
    private static final String nls = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\";                                                                                                              ";
    private final int count;
    
    public NL() {
        super(null);
        this.count = 1;
    }
    
    public NL(final int count) {
        super(null);
        assert count > 0 : "NL 0";
        this.count = count;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public String getText() {
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\";                                                                                                              ".substring(0, Math.min("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\";                                                                                                              ".length(), this.count));
    }
    
    public boolean isUseless() {
        return this.count == 0;
    }
    
    public String toString() {
        return "Token: " + this.getName() + "\n" + "                nls: " + this.count + "\n";
    }
}
