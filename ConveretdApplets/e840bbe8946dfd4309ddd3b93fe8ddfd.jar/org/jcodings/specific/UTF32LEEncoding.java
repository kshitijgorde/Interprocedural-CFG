// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.Encoding;
import org.jcodings.IntHolder;
import org.jcodings.unicode.FixedWidthUnicodeEncoding;

public final class UTF32LEEncoding extends FixedWidthUnicodeEncoding
{
    public static UTF32LEEncoding INSTANCE;
    
    protected UTF32LEEncoding() {
        super("UTF-32LE", 4);
    }
    
    public boolean isNewLine(final byte[] bytes, final int p, final int end) {
        return p + 3 < end && bytes[p] == 10 && bytes[p + 1] == 0 && bytes[p + 2] == 0 && bytes[p + 3] == 0;
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        return (((bytes[p + 3] & 0xFF) * 256 + (bytes[p + 2] & 0xFF)) * 256 + (bytes[p + 1] & 0xFF)) * 256 + (bytes[p] & 0xFF);
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        int p_ = p;
        bytes[p_++] = (byte)(code & 0xFF);
        bytes[p_++] = (byte)((code & 0xFF00) >>> 8);
        bytes[p_++] = (byte)((code & 0xFF0000) >>> 16);
        bytes[p_++] = (byte)((code & 0xFF000000) >>> 24);
        return 4;
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] fold) {
        final int p = pp.value;
        int foldP = 0;
        if (Encoding.isAscii(bytes[p] & 0xFF) && bytes[p + 1] == 0 && bytes[p + 2] == 0 && bytes[p + 3] == 0) {
            fold[foldP++] = AsciiTables.ToLowerCaseTable[bytes[p] & 0xFF];
            fold[foldP++] = 0;
            fold[foldP] = (fold[foldP++] = 0);
            pp.value += 4;
            return 4;
        }
        return super.mbcCaseFold(flag, bytes, pp, end, fold);
    }
    
    static {
        UTF32LEEncoding.INSTANCE = new UTF32LEEncoding();
    }
}
