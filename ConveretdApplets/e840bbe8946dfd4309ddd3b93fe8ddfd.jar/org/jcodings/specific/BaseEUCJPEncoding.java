// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.CodeRange;
import org.jcodings.exception.InternalException;
import org.jcodings.IntHolder;
import org.jcodings.exception.EncodingException;
import org.jcodings.Encoding;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.util.BytesHash;
import org.jcodings.EucEncoding;

abstract class BaseEUCJPEncoding extends EucEncoding
{
    private static final int[] CR_Hiragana;
    private static final int[] CR_Katakana;
    private static final int[][] PropertyList;
    private static final BytesHash<Integer> CTypeNameHash;
    static final int[] EUCJPEncLen;
    
    protected BaseEUCJPEncoding(final int[][] Trans) {
        super("EUC-JP", 1, 3, BaseEUCJPEncoding.EUCJPEncLen, Trans, AsciiTables.AsciiCtypeTable);
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        return this.mbnMbcToCode(bytes, p, end);
    }
    
    public int codeToMbcLength(final int code) {
        if (Encoding.isAscii(code)) {
            return 1;
        }
        if (code > 16777215) {
            return 0;
        }
        if ((code & 0xFF0000) >= 8388608) {
            return 3;
        }
        if ((code & 0xFF00) >= 32768) {
            return 2;
        }
        throw new EncodingException("invalid code point value");
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        if ((code & 0xFF0000) != 0x0) {
            bytes[p_++] = (byte)(code >> 16 & 0xFF);
        }
        if ((code & 0xFF00) != 0x0) {
            bytes[p_++] = (byte)(code >> 8 & 0xFF);
        }
        bytes[p_++] = (byte)(code & 0xFF);
        if (this.length(bytes, p, p_) != p_ - p) {
            throw new EncodingException("invalid code point value");
        }
        return p_ - p;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        int p = pp.value;
        int lowerP = 0;
        if (Encoding.isMbcAscii(bytes[p])) {
            lower[lowerP] = AsciiTables.ToLowerCaseTable[bytes[p] & 0xFF];
            ++pp.value;
            return 1;
        }
        final int len = this.length(bytes, p, end);
        for (int i = 0; i < len; ++i) {
            lower[lowerP++] = bytes[p++];
        }
        pp.value += len;
        return len;
    }
    
    protected boolean isLead(final int c) {
        return (c - 161 & 0xFF) > 93;
    }
    
    public boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        final int c = bytes[p] & 0xFF;
        return c <= 126 || c == 142 || c == 143;
    }
    
    public int propertyNameToCType(final byte[] bytes, final int p, final int end) {
        final Integer ctype;
        if ((ctype = BaseEUCJPEncoding.CTypeNameHash.get(bytes, p, end)) == null) {
            return super.propertyNameToCType(bytes, p, end);
        }
        return ctype;
    }
    
    public boolean isCodeCType(final int code, int ctype) {
        if (ctype <= 14) {
            if (code < 128) {
                return this.isCodeCTypeInternal(code, ctype);
            }
            return Encoding.isWordGraphPrint(ctype) && this.codeToMbcLength(code) > 1;
        }
        else {
            ctype -= 15;
            if (ctype >= BaseEUCJPEncoding.PropertyList.length) {
                throw new InternalException("undefined type (bug)");
            }
            return CodeRange.isInCodeRange(BaseEUCJPEncoding.PropertyList[ctype], code);
        }
    }
    
    public int[] ctypeCodeRange(int ctype, final IntHolder sbOut) {
        if (ctype <= 14) {
            return null;
        }
        sbOut.value = 128;
        ctype -= 15;
        if (ctype >= BaseEUCJPEncoding.PropertyList.length) {
            throw new InternalException("undefined type (bug)");
        }
        return BaseEUCJPEncoding.PropertyList[ctype];
    }
    
    static {
        CR_Hiragana = new int[] { 1, 42145, 42227 };
        CR_Katakana = new int[] { 3, 42401, 42486, 43686, 43695, 43697, 43741 };
        PropertyList = new int[][] { BaseEUCJPEncoding.CR_Hiragana, BaseEUCJPEncoding.CR_Katakana };
        (CTypeNameHash = new BytesHash<Integer>()).put("Hiragana".getBytes(), 15);
        BaseEUCJPEncoding.CTypeNameHash.put("Katakana".getBytes(), 16);
        EUCJPEncLen = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 };
    }
}
