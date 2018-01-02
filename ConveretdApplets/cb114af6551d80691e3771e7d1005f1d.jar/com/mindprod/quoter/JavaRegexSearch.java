// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

class JavaRegexSearch extends Translator
{
    private static final String[] trt;
    
    JavaRegexSearch() {
        this.table = JavaRegexSearch.trt;
    }
    
    public String process(final String raw) {
        assert raw != null : "TextProcessor.process raw must not be null";
        return super.process(raw);
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            JavaRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            JavaRegexSearch.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            JavaRegexSearch.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        JavaRegexSearch.trt[7] = "\\a";
        JavaRegexSearch.trt[8] = "\\b";
        JavaRegexSearch.trt[9] = "\\t";
        JavaRegexSearch.trt[10] = "\\n";
        JavaRegexSearch.trt[11] = "\\v";
        JavaRegexSearch.trt[12] = "\\f";
        JavaRegexSearch.trt[13] = "\\r";
        JavaRegexSearch.trt[36] = "\\$";
        JavaRegexSearch.trt[40] = "\\(";
        JavaRegexSearch.trt[41] = "\\)";
        JavaRegexSearch.trt[42] = "\\*";
        JavaRegexSearch.trt[43] = "\\+";
        JavaRegexSearch.trt[45] = "\\-";
        JavaRegexSearch.trt[46] = "\\.";
        JavaRegexSearch.trt[63] = "\\?";
        JavaRegexSearch.trt[91] = "\\[";
        JavaRegexSearch.trt[92] = "\\\\";
        JavaRegexSearch.trt[93] = "\\]";
        JavaRegexSearch.trt[94] = "\\^";
        JavaRegexSearch.trt[124] = "\\|";
        JavaRegexSearch.trt[123] = "\\{";
        JavaRegexSearch.trt[125] = "\\}";
    }
}
