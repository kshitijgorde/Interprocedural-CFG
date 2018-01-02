// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.SingleByteEncoding;

public final class USASCIIEncoding extends SingleByteEncoding
{
    public static final USASCIIEncoding INSTANCE;
    
    protected USASCIIEncoding() {
        super("US-ASCII", AsciiTables.AsciiCtypeTable, AsciiTables.ToLowerCaseTable);
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        return ((bytes[p] & 0x80) == 0x0) ? 1 : -1;
    }
    
    public final byte[] toLowerCaseTable() {
        return this.LowerCaseTable;
    }
    
    public String getCharsetName() {
        return "US-ASCII";
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        return code < 128 && this.isCodeCTypeInternal(code, ctype);
    }
    
    static {
        INSTANCE = new USASCIIEncoding();
    }
}
