// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class VSlickRegexSearch extends Translator
{
    private static final String[] trt;
    
    VSlickRegexSearch() {
        this.table = VSlickRegexSearch.trt;
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            VSlickRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            VSlickRegexSearch.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            VSlickRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        VSlickRegexSearch.trt[10] = "\\n";
        VSlickRegexSearch.trt[13] = "";
        VSlickRegexSearch.trt[42] = "\\*";
        VSlickRegexSearch.trt[43] = "\\+";
        VSlickRegexSearch.trt[46] = "\\.";
        VSlickRegexSearch.trt[63] = "\\?";
        VSlickRegexSearch.trt[92] = "\\\\";
        VSlickRegexSearch.trt[123] = "\\{";
        VSlickRegexSearch.trt[124] = "\\|";
        VSlickRegexSearch.trt[125] = "\\}";
    }
}
