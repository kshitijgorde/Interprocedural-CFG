// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public abstract class SingleByteEncoding extends AbstractEncoding
{
    protected final byte[] LowerCaseTable;
    
    protected SingleByteEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable) {
        super(name, 1, 1, CTypeTable);
        this.LowerCaseTable = LowerCaseTable;
    }
    
    protected SingleByteEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable, final boolean isDummy) {
        super(name, 1, 1, CTypeTable, isDummy);
        this.LowerCaseTable = LowerCaseTable;
    }
    
    public int length(final byte c) {
        return 1;
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        return 1;
    }
    
    public final int strLength(final byte[] bytes, final int p, final int end) {
        return end - p;
    }
    
    public int strCodeAt(final byte[] bytes, final int p, final int end, final int index) {
        return bytes[index] & 0xFF;
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        return bytes[p] & 0xFF;
    }
    
    public final int codeToMbcLength(final int code) {
        return 1;
    }
    
    public final int codeToMbc(final int code, final byte[] bytes, final int p) {
        bytes[p] = (byte)(code & 0xFF);
        return 1;
    }
    
    public final int[] ctypeCodeRange(final int ctype, final IntHolder sbOut) {
        return null;
    }
    
    public final int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        return s;
    }
    
    public final boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return true;
    }
}
