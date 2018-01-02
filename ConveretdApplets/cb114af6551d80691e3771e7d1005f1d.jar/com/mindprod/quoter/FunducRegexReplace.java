// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class FunducRegexReplace extends Translator
{
    private static final String[] trt;
    
    FunducRegexReplace() {
        this.table = FunducRegexReplace.trt;
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            FunducRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            FunducRegexReplace.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            FunducRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        FunducRegexReplace.trt[7] = "\\a";
        FunducRegexReplace.trt[8] = "\\b";
        FunducRegexReplace.trt[9] = "\\t";
        FunducRegexReplace.trt[10] = "\\r\\n";
        FunducRegexReplace.trt[11] = "\\v";
        FunducRegexReplace.trt[12] = "\\f";
        FunducRegexReplace.trt[13] = "\\r";
        FunducRegexReplace.trt[37] = "\\%";
        FunducRegexReplace.trt[60] = "\\<";
        FunducRegexReplace.trt[62] = "\\>";
        FunducRegexReplace.trt[92] = "\\\\";
    }
}
