// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.SingleByteEncoding;

public final class ASCIIEncoding extends SingleByteEncoding
{
    public static final ASCIIEncoding INSTANCE;
    public static final ASCIIEncoding DUMMY;
    
    protected ASCIIEncoding() {
        super("ASCII-8BIT", AsciiTables.AsciiCtypeTable, AsciiTables.ToLowerCaseTable);
    }
    
    protected ASCIIEncoding(final boolean isDummy) {
        super(isDummy ? "DUMMY" : "ASCII-8BIT", AsciiTables.AsciiCtypeTable, AsciiTables.ToLowerCaseTable, isDummy);
    }
    
    public final byte[] toLowerCaseTable() {
        return this.LowerCaseTable;
    }
    
    public String getCharsetName() {
        return "ISO-8859-1";
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        return code < 128 && this.isCodeCTypeInternal(code, ctype);
    }
    
    static {
        INSTANCE = new ASCIIEncoding();
        DUMMY = new ASCIIEncoding(true);
    }
}
