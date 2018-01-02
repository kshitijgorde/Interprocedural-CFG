// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.unicode;

import org.jcodings.CaseFoldCodeItem;
import org.jcodings.ApplyAllCaseFoldFunction;
import org.jcodings.IntHolder;
import org.jcodings.exception.CharacterPropertyException;
import org.jcodings.CodeRange;
import org.jcodings.MultiByteEncoding;

public abstract class UnicodeEncoding extends MultiByteEncoding
{
    private static final int PROPERTY_NAME_MAX_SIZE = 20;
    static final short[] UNICODE_ISO_8859_1_CTypeTable;
    
    protected UnicodeEncoding(final String name, final int minLength, final int maxLength, final int[] EncLen) {
        super(name, minLength, maxLength, EncLen, null, UnicodeEncoding.UNICODE_ISO_8859_1_CTypeTable);
    }
    
    protected UnicodeEncoding(final String name, final int minLength, final int maxLength, final int[] EncLen, final int[][] Trans) {
        super(name, minLength, maxLength, EncLen, Trans, UnicodeEncoding.UNICODE_ISO_8859_1_CTypeTable);
    }
    
    public String getCharsetName() {
        return new String(this.getName());
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        if (ctype <= 14 && code < 256) {
            return this.isCodeCTypeInternal(code, ctype);
        }
        if (ctype > UnicodeCodeRanges.CodeRangeTable.length) {
            throw new InternalError("undefined type (bug)");
        }
        return CodeRange.isInCodeRange(UnicodeCodeRanges.CodeRangeTable[ctype], code);
    }
    
    protected final int[] ctypeCodeRange(final int ctype) {
        if (ctype >= UnicodeCodeRanges.CodeRangeTable.length) {
            throw new InternalError("undefined type (bug)");
        }
        return UnicodeCodeRanges.CodeRangeTable[ctype];
    }
    
    public int propertyNameToCType(final byte[] name, final int p, final int end) {
        final byte[] buf = new byte[20];
        int p_ = p;
        int len = 0;
        while (p_ < end) {
            final int code = this.mbcToCode(name, p_, end);
            if (code >= 128) {
                throw new CharacterPropertyException("invalid character property name {%n}");
            }
            buf[len++] = (byte)code;
            if (len >= 20) {
                throw new CharacterPropertyException("invalid character property name {%n}", name, p, end);
            }
            p_ += this.length(name, p_, end);
        }
        final Integer ctype = UnicodeCTypeNames.CTypeNameHash.get(buf, 0, len);
        if (ctype == null) {
            throw new CharacterPropertyException("invalid character property name {%n}", name, p, end);
        }
        return ctype;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] fold) {
        int p = pp.value;
        int foldP = 0;
        final int code = this.mbcToCode(bytes, p, end);
        int len = this.length(bytes, p, end);
        pp.value += len;
        final int[] to = UnicodeCaseFolds.FoldHash.get(code);
        if (to == null) {
            for (int i = 0; i < len; ++i) {
                fold[foldP++] = bytes[p++];
            }
            return len;
        }
        if (to.length == 1) {
            return this.codeToMbc(to[0], fold, foldP);
        }
        int rlen = 0;
        for (int j = 0; j < to.length; ++j) {
            len = this.codeToMbc(to[j], fold, foldP);
            foldP += len;
            rlen += len;
        }
        return rlen;
    }
    
    public void applyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        final int[] code = { 0 };
        for (int i = 0; i < UnicodeCaseFolds.CaseUnfold_11_From.length; ++i) {
            final int from = UnicodeCaseFolds.CaseUnfold_11_From[i];
            final int[] to = UnicodeCaseFolds.CaseUnfold_11_To[i];
            for (int j = 0; j < to.length; ++j) {
                code[0] = from;
                fun.apply(to[j], code, 1, arg);
                code[0] = to[j];
                fun.apply(from, code, 1, arg);
                for (int k = 0; k < j; ++k) {
                    code[0] = to[k];
                    fun.apply(to[j], code, 1, arg);
                    code[0] = to[j];
                    fun.apply(to[k], code, 1, arg);
                }
            }
        }
        for (int i = 0; i < UnicodeCaseFolds.CaseUnfold_11_Locale_From.length; ++i) {
            final int from = UnicodeCaseFolds.CaseUnfold_11_Locale_From[i];
            final int[] to = UnicodeCaseFolds.CaseUnfold_11_Locale_To[i];
            for (int j = 0; j < to.length; ++j) {
                code[0] = from;
                fun.apply(to[j], code, 1, arg);
                code[0] = to[j];
                fun.apply(from, code, 1, arg);
                for (int k = 0; k < j; ++k) {
                    code[0] = to[k];
                    fun.apply(to[j], code, 1, arg);
                    code[0] = to[j];
                    fun.apply(to[k], code, 1, arg);
                }
            }
        }
        if ((flag & 0x40000000) != 0x0) {
            for (int i = 0; i < UnicodeCaseFolds.CaseUnfold_12.length; i += 2) {
                final int[] from2 = UnicodeCaseFolds.CaseUnfold_12[i];
                final int[] to = UnicodeCaseFolds.CaseUnfold_12[i + 1];
                for (int j = 0; j < to.length; ++j) {
                    fun.apply(to[j], from2, 2, arg);
                    for (int k = 0; k < to.length; ++k) {
                        if (k != j) {
                            code[0] = to[k];
                            fun.apply(to[j], code, 1, arg);
                        }
                    }
                }
            }
            for (int i = 0; i < UnicodeCaseFolds.CaseUnfold_12_Locale.length; i += 2) {
                final int[] from2 = UnicodeCaseFolds.CaseUnfold_12_Locale[i];
                final int[] to = UnicodeCaseFolds.CaseUnfold_12_Locale[i + 1];
                for (int j = 0; j < to.length; ++j) {
                    fun.apply(to[j], from2, 2, arg);
                    for (int k = 0; k < to.length; ++k) {
                        if (k != j) {
                            code[0] = to[k];
                            fun.apply(to[j], code, 1, arg);
                        }
                    }
                }
            }
            for (int i = 0; i < UnicodeCaseFolds.CaseUnfold_13.length; i += 2) {
                final int[] from2 = UnicodeCaseFolds.CaseUnfold_13[i];
                final int[] to = UnicodeCaseFolds.CaseUnfold_13[i + 1];
                for (int j = 0; j < to.length; ++j) {
                    fun.apply(to[j], from2, 3, arg);
                    for (int k = 0; k < to.length; ++k) {
                        if (k != j) {
                            code[0] = to[k];
                            fun.apply(to[j], code, 1, arg);
                        }
                    }
                }
            }
        }
    }
    
    public CaseFoldCodeItem[] caseFoldCodesByString(int flag, final byte[] bytes, int p, final int end) {
        int code = this.mbcToCode(bytes, p, end);
        int len = this.length(bytes, p, end);
        int n = 0;
        int fn = 0;
        int[] to = UnicodeCaseFolds.FoldHash.get(code);
        CaseFoldCodeItem[] items = null;
        if (to != null) {
            items = new CaseFoldCodeItem[13];
            if (to.length == 1) {
                final int origCode = code;
                items[0] = new CaseFoldCodeItem(len, 1, new int[] { to[0] });
                ++n;
                code = to[0];
                to = UnicodeCaseFolds.Unfold1Hash.get(code);
                if (to != null) {
                    for (int i = 0; i < to.length; ++i) {
                        if (to[i] != origCode) {
                            items[n] = new CaseFoldCodeItem(len, 1, new int[] { to[i] });
                            ++n;
                        }
                    }
                }
            }
            else if ((flag & 0x40000000) != 0x0) {
                final int[][] cs = new int[3][4];
                final int[] ncs = new int[3];
                for (fn = 0; fn < to.length; ++fn) {
                    cs[fn][0] = to[fn];
                    final int[] z3 = UnicodeCaseFolds.Unfold1Hash.get(cs[fn][0]);
                    if (z3 != null) {
                        for (int j = 0; j < z3.length; ++j) {
                            cs[fn][j + 1] = z3[j];
                        }
                        ncs[fn] = z3.length + 1;
                    }
                    else {
                        ncs[fn] = 1;
                    }
                }
                if (fn == 2) {
                    for (int k = 0; k < ncs[0]; ++k) {
                        for (int l = 0; l < ncs[1]; ++l) {
                            items[n] = new CaseFoldCodeItem(len, 2, new int[] { cs[0][k], cs[1][l] });
                            ++n;
                        }
                    }
                    final int[] z4 = UnicodeCaseFolds.Unfold2Hash.get(to);
                    if (z4 != null) {
                        for (int j = 0; j < z4.length; ++j) {
                            if (z4[j] != code) {
                                items[n] = new CaseFoldCodeItem(len, 1, new int[] { z4[j] });
                                ++n;
                            }
                        }
                    }
                }
                else {
                    for (int k = 0; k < ncs[0]; ++k) {
                        for (int l = 0; l < ncs[1]; ++l) {
                            for (int m = 0; m < ncs[2]; ++m) {
                                items[n] = new CaseFoldCodeItem(len, 3, new int[] { cs[0][k], cs[1][l], cs[2][m] });
                                ++n;
                            }
                        }
                    }
                    final int[] z4 = UnicodeCaseFolds.Unfold3Hash.get(to);
                    if (z4 != null) {
                        for (int j = 0; j < z4.length; ++j) {
                            if (z4[j] != code) {
                                items[n] = new CaseFoldCodeItem(len, 1, new int[] { z4[j] });
                                ++n;
                            }
                        }
                    }
                }
                flag = 0;
            }
        }
        else {
            to = UnicodeCaseFolds.Unfold1Hash.get(code);
            if (to != null) {
                items = new CaseFoldCodeItem[13];
                for (int i2 = 0; i2 < to.length; ++i2) {
                    items[n] = new CaseFoldCodeItem(len, 1, new int[] { to[i2] });
                    ++n;
                }
            }
        }
        if ((flag & 0x40000000) != 0x0) {
            if (items == null) {
                items = new CaseFoldCodeItem[13];
            }
            p += len;
            if (p < end) {
                final int[] codes = { code, 0, 0 };
                code = this.mbcToCode(bytes, p, end);
                to = UnicodeCaseFolds.FoldHash.get(code);
                if (to != null && to.length == 1) {
                    codes[1] = to[0];
                }
                else {
                    codes[1] = code;
                }
                int clen = this.length(bytes, p, end);
                len += clen;
                int[] z4 = UnicodeCaseFolds.Unfold2Hash.get(codes);
                if (z4 != null) {
                    for (int j = 0; j < z4.length; ++j) {
                        items[n] = new CaseFoldCodeItem(len, 1, new int[] { z4[j] });
                        ++n;
                    }
                }
                p += clen;
                if (p < end) {
                    code = this.mbcToCode(bytes, p, end);
                    to = UnicodeCaseFolds.FoldHash.get(code);
                    if (to != null && to.length == 1) {
                        codes[2] = to[0];
                    }
                    else {
                        codes[2] = code;
                    }
                    clen = this.length(bytes, p, end);
                    len += clen;
                    z4 = UnicodeCaseFolds.Unfold3Hash.get(codes);
                    if (z4 != null) {
                        for (int j = 0; j < z4.length; ++j) {
                            items[n] = new CaseFoldCodeItem(len, 1, new int[] { z4[j] });
                            ++n;
                        }
                    }
                }
            }
        }
        if (items == null || n == 0) {
            return UnicodeEncoding.EMPTY_FOLD_CODES;
        }
        if (n < items.length) {
            final CaseFoldCodeItem[] tmp = new CaseFoldCodeItem[n];
            System.arraycopy(items, 0, tmp, 0, n);
            return tmp;
        }
        return items;
    }
    
    static {
        UNICODE_ISO_8859_1_CTypeTable = new short[] { 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 17036, 17033, 17032, 17032, 17032, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 17028, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 31906, 31906, 31906, 31906, 31906, 31906, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 16800, 16800, 16800, 16800, 20896, 16800, 30946, 30946, 30946, 30946, 30946, 30946, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 16800, 16800, 16800, 16800, 16392, 8, 8, 8, 8, 8, 648, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 644, 416, 160, 160, 160, 160, 160, 160, 160, 160, 12514, 416, 160, 168, 160, 160, 160, 160, 4256, 4256, 160, 12514, 160, 416, 160, 4256, 12514, 416, 4256, 4256, 4256, 416, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 160, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 160, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514 };
    }
}
