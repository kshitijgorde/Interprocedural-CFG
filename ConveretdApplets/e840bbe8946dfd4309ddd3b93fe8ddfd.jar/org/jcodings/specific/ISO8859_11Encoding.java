// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.CaseFoldCodeItem;
import org.jcodings.ApplyAllCaseFoldFunction;
import org.jcodings.IntHolder;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.ISOEncoding;

public final class ISO8859_11Encoding extends ISOEncoding
{
    static final short[] ISO8859_11CtypeTable;
    public static final ISO8859_11Encoding INSTANCE;
    
    protected ISO8859_11Encoding() {
        super("ISO-8859-11", ISO8859_11Encoding.ISO8859_11CtypeTable, AsciiTables.ToLowerCaseTable, null);
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        return this.asciiMbcCaseFold(flag, bytes, pp, end, lower);
    }
    
    public final byte[] toLowerCaseTable() {
        return this.LowerCaseTable;
    }
    
    public void applyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        this.asciiApplyAllCaseFold(flag, fun, arg);
    }
    
    public CaseFoldCodeItem[] caseFoldCodesByString(final int flag, final byte[] bytes, final int p, final int end) {
        return this.asciiCaseFoldCodesByString(flag, bytes, p, end);
    }
    
    static {
        ISO8859_11CtypeTable = new short[] { 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16908, 16905, 16904, 16904, 16904, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 17028, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 31906, 31906, 31906, 31906, 31906, 31906, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 16800, 16800, 16800, 16800, 20896, 16800, 30946, 30946, 30946, 30946, 30946, 30946, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 16800, 16800, 16800, 16800, 16392, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 644, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 0, 0, 0, 0, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 12450, 0, 0, 0, 0 };
        INSTANCE = new ISO8859_11Encoding();
    }
}
