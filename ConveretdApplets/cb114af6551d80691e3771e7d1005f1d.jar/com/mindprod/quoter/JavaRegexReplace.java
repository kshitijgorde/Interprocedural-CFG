// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

class JavaRegexReplace extends Translator
{
    private static final String[] trt;
    
    JavaRegexReplace() {
        this.table = JavaRegexReplace.trt;
    }
    
    public String process(final String raw) {
        assert raw != null : "TextProcessor.process raw must not be null";
        return super.process(raw);
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            JavaRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        for (int i = 32; i < 127; ++i) {
            JavaRegexReplace.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 256; ++i) {
            JavaRegexReplace.trt[i] = "\\0x" + Integer.toHexString(i + 256).substring(1, 3);
        }
        JavaRegexReplace.trt[7] = "\\a";
        JavaRegexReplace.trt[8] = "\\b";
        JavaRegexReplace.trt[9] = "\\t";
        JavaRegexReplace.trt[10] = "\\r\\n";
        JavaRegexReplace.trt[11] = "\\v";
        JavaRegexReplace.trt[12] = "\\f";
        JavaRegexReplace.trt[13] = "\\r";
        JavaRegexReplace.trt[92] = "\\\\";
        JavaRegexReplace.trt[36] = "\\$";
    }
}
