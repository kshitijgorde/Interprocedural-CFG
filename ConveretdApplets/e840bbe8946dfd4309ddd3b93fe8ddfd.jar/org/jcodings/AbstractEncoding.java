// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

import org.jcodings.exception.CharacterPropertyException;
import org.jcodings.constants.PosixBracket;
import org.jcodings.ascii.AsciiTables;

abstract class AbstractEncoding extends Encoding
{
    private final short[] CTypeTable;
    protected static final CaseFoldCodeItem[] EMPTY_FOLD_CODES;
    
    protected AbstractEncoding(final String name, final int minLength, final int maxLength, final short[] CTypeTable) {
        super(name, minLength, maxLength);
        this.CTypeTable = CTypeTable;
    }
    
    protected AbstractEncoding(final String name, final int minLength, final int maxLength, final short[] CTypeTable, final boolean isDummy) {
        super(name, minLength, maxLength, isDummy);
        this.CTypeTable = CTypeTable;
    }
    
    private static int CTypeToBit(final int ctype) {
        return 1 << ctype;
    }
    
    protected final boolean isCodeCTypeInternal(final int code, final int ctype) {
        return (this.CTypeTable[code] & CTypeToBit(ctype)) != 0x0;
    }
    
    public boolean isNewLine(final byte[] bytes, final int p, final int end) {
        return p < end && bytes[p] == 10;
    }
    
    protected final int asciiMbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        lower[0] = AsciiTables.ToLowerCaseTable[bytes[pp.value] & 0xFF];
        ++pp.value;
        return 1;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        return this.asciiMbcCaseFold(flag, bytes, pp, end, lower);
    }
    
    protected final void asciiApplyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        final int[] code = { 0 };
        for (int i = 0; i < AsciiTables.LowerMap.length; ++i) {
            code[0] = AsciiTables.LowerMap[i][1];
            fun.apply(AsciiTables.LowerMap[i][0], code, 1, arg);
            code[0] = AsciiTables.LowerMap[i][0];
            fun.apply(AsciiTables.LowerMap[i][1], code, 1, arg);
        }
    }
    
    public void applyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        this.asciiApplyAllCaseFold(flag, fun, arg);
    }
    
    protected final CaseFoldCodeItem[] asciiCaseFoldCodesByString(final int flag, final byte[] bytes, final int p, final int end) {
        final int b = bytes[p] & 0xFF;
        if (65 <= b && b <= 90) {
            return new CaseFoldCodeItem[] { new CaseFoldCodeItem(1, 1, new int[] { b + 32 }) };
        }
        if (97 <= b && b <= 122) {
            return new CaseFoldCodeItem[] { new CaseFoldCodeItem(1, 1, new int[] { b - 32 }) };
        }
        return AbstractEncoding.EMPTY_FOLD_CODES;
    }
    
    public CaseFoldCodeItem[] caseFoldCodesByString(final int flag, final byte[] bytes, final int p, final int end) {
        return this.asciiCaseFoldCodesByString(flag, bytes, p, end);
    }
    
    public int propertyNameToCType(final byte[] bytes, final int p, final int end) {
        final Integer ctype = PosixBracket.PBSTableUpper.get(bytes, p, end);
        if (ctype != null) {
            return ctype;
        }
        throw new CharacterPropertyException("invalid character property name {%n}", new String(bytes, p, end - p));
    }
    
    static {
        EMPTY_FOLD_CODES = new CaseFoldCodeItem[0];
    }
}
