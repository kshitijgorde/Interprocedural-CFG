// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.CodeRange;
import org.jcodings.exception.InternalException;
import org.jcodings.Encoding;
import org.jcodings.IntHolder;
import org.jcodings.exception.EncodingException;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.util.BytesHash;
import org.jcodings.CanBeTrailTableEncoding;

abstract class BaseSJISEncoding extends CanBeTrailTableEncoding
{
    private static final int[] CR_Hiragana;
    private static final int[] CR_Katakana;
    private static final int[][] PropertyList;
    private static final BytesHash<Integer> CTypeNameHash;
    static final boolean[] SJIS_CAN_BE_TRAIL_TABLE;
    static final int[] SjisEncLen;
    
    protected BaseSJISEncoding(final int[][] Trans) {
        super("Shift_JIS", 1, 2, BaseSJISEncoding.SjisEncLen, Trans, AsciiTables.AsciiCtypeTable, BaseSJISEncoding.SJIS_CAN_BE_TRAIL_TABLE);
    }
    
    public String getCharsetName() {
        return "windows-31j";
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        return this.mbnMbcToCode(bytes, p, end);
    }
    
    public int codeToMbcLength(final int code) {
        if (code < 256) {
            return (BaseSJISEncoding.SjisEncLen[code] == 1) ? 1 : 0;
        }
        if (code <= 65535) {
            return 2;
        }
        throw new EncodingException("invalid code point value");
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if ((code & 0xFF00) != 0x0) {
            bytes[p_++] = (byte)(code >> 8 & 0xFF);
        }
        bytes[p_++] = (byte)(code & 0xFF);
        return p_ - p;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        return this.mbnMbcCaseFold(flag, bytes, pp, end, lower);
    }
    
    public int propertyNameToCType(final byte[] bytes, final int p, final int end) {
        final Integer ctype;
        if ((ctype = BaseSJISEncoding.CTypeNameHash.get(bytes, p, end)) == null) {
            return super.propertyNameToCType(bytes, p, end);
        }
        return ctype;
    }
    
    public boolean isCodeCType(final int code, int ctype) {
        if (ctype <= 14) {
            if (code < 128) {
                return this.isCodeCTypeInternal(code, ctype);
            }
            return Encoding.isWordGraphPrint(ctype);
        }
        else {
            ctype -= 15;
            if (ctype >= BaseSJISEncoding.PropertyList.length) {
                throw new InternalException("undefined type (bug)");
            }
            return CodeRange.isInCodeRange(BaseSJISEncoding.PropertyList[ctype], code);
        }
    }
    
    public int[] ctypeCodeRange(int ctype, final IntHolder sbOut) {
        if (ctype <= 14) {
            return null;
        }
        sbOut.value = 128;
        ctype -= 15;
        if (ctype >= BaseSJISEncoding.PropertyList.length) {
            throw new InternalException("undefined type (bug)");
        }
        return BaseSJISEncoding.PropertyList[ctype];
    }
    
    static {
        CR_Hiragana = new int[] { 1, 33439, 33521 };
        CR_Katakana = new int[] { 4, 166, 175, 177, 221, 33600, 33662, 33664, 33686 };
        PropertyList = new int[][] { BaseSJISEncoding.CR_Hiragana, BaseSJISEncoding.CR_Katakana };
        (CTypeNameHash = new BytesHash<Integer>()).put("Hiragana".getBytes(), 15);
        BaseSJISEncoding.CTypeNameHash.put("Katakana".getBytes(), 16);
        SJIS_CAN_BE_TRAIL_TABLE = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false };
        SjisEncLen = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1 };
    }
}
