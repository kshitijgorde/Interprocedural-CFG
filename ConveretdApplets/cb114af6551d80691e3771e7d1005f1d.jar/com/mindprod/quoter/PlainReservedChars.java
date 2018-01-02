// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class PlainReservedChars extends Translator
{
    private static final String[] trt;
    
    PlainReservedChars() {
        this.table = PlainReservedChars.trt;
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            PlainReservedChars.trt[i] = "";
        }
        for (int i = 32; i < 256; ++i) {
            PlainReservedChars.trt[i] = String.valueOf((char)i).intern();
        }
        PlainReservedChars.trt[7] = "\t";
        PlainReservedChars.trt[10] = "\n";
        PlainReservedChars.trt[13] = "";
        PlainReservedChars.trt[160] = " ";
    }
}
