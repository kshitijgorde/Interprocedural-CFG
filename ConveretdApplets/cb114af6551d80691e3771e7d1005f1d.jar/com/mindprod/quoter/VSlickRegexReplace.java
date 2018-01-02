// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class VSlickRegexReplace extends Translator
{
    private static final String[] trt;
    
    VSlickRegexReplace() {
        this.table = VSlickRegexReplace.trt;
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            VSlickRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            VSlickRegexReplace.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            VSlickRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        VSlickRegexReplace.trt[10] = "\\n";
        VSlickRegexReplace.trt[13] = "";
        VSlickRegexReplace.trt[92] = "\\\\";
    }
}
