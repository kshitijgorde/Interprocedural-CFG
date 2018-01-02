// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public abstract class ISOEncoding extends CaseFoldMapEncoding
{
    protected ISOEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable, final int[][] CaseFoldMap) {
        this(name, CTypeTable, LowerCaseTable, CaseFoldMap, true);
    }
    
    protected ISOEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable, final int[][] CaseFoldMap, final boolean foldFlag) {
        super(name, CTypeTable, LowerCaseTable, CaseFoldMap, foldFlag);
    }
    
    public String getCharsetName() {
        return new String(this.getName());
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        final int p = pp.value;
        int lowerP = 0;
        if (bytes[p] == -33 && (flag & 0x40000000) != 0x0) {
            lower[lowerP] = (lower[lowerP++] = 115);
            ++pp.value;
            return 2;
        }
        lower[lowerP] = this.LowerCaseTable[bytes[p] & 0xFF];
        ++pp.value;
        return 1;
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        return code < 256 && this.isCodeCTypeInternal(code, ctype);
    }
}
