// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

public final class NonStrictSJISEncoding extends BaseSJISEncoding
{
    public static final NonStrictSJISEncoding INSTANCE;
    
    protected NonStrictSJISEncoding() {
        super(null);
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        return this.length(bytes[p]);
    }
    
    static {
        INSTANCE = new NonStrictSJISEncoding();
    }
}
