// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public class CaseFoldCodeItem
{
    static final int ENC_MAX_COMP_CASE_FOLD_CODE_LEN = 3;
    public final int byteLen;
    public final int codeLen;
    public final int[] code;
    
    public CaseFoldCodeItem(final int byteLen, final int codeLen, final int[] code) {
        this.byteLen = byteLen;
        this.codeLen = codeLen;
        this.code = code;
    }
}
