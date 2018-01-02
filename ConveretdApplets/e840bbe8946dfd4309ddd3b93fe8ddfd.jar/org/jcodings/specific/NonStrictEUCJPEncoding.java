// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

public final class NonStrictEUCJPEncoding extends BaseEUCJPEncoding
{
    public static final NonStrictEUCJPEncoding INSTANCE;
    
    protected NonStrictEUCJPEncoding() {
        super(null);
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        return this.length(bytes[p]);
    }
    
    static {
        INSTANCE = new NonStrictEUCJPEncoding();
    }
}
