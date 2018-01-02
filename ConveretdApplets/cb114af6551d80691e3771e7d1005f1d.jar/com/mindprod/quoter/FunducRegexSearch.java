// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class FunducRegexSearch extends Translator
{
    private static final String[] trt;
    
    FunducRegexSearch() {
        this.table = FunducRegexSearch.trt;
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            FunducRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            FunducRegexSearch.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            FunducRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        FunducRegexSearch.trt[7] = "\\a";
        FunducRegexSearch.trt[8] = "\\b";
        FunducRegexSearch.trt[9] = "\\t";
        FunducRegexSearch.trt[10] = "$";
        FunducRegexSearch.trt[11] = "\\v";
        FunducRegexSearch.trt[12] = "\\f";
        FunducRegexSearch.trt[13] = "$";
        FunducRegexSearch.trt[33] = "\\!";
        FunducRegexSearch.trt[36] = "\\$";
        FunducRegexSearch.trt[40] = "\\(";
        FunducRegexSearch.trt[41] = "\\)";
        FunducRegexSearch.trt[42] = "\\*";
        FunducRegexSearch.trt[43] = "\\+";
        FunducRegexSearch.trt[45] = "\\-";
        FunducRegexSearch.trt[63] = "\\?";
        FunducRegexSearch.trt[91] = "\\[";
        FunducRegexSearch.trt[92] = "\\\\";
        FunducRegexSearch.trt[93] = "\\]";
        FunducRegexSearch.trt[94] = "\\^";
        FunducRegexSearch.trt[124] = "\\|";
    }
}
