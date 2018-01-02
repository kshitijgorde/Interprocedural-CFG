// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class ToJavaStringLiteral extends Translator
{
    private static final String[] trt;
    
    public ToJavaStringLiteral() {
        this.table = ToJavaStringLiteral.trt;
    }
    
    public String process(final String raw) {
        final StringBuilder cooked = new StringBuilder(raw.length() * 120 / 100);
        cooked.append("   \"");
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            if (c < '\u0100') {
                cooked.append(this.table[c]);
            }
            else {
                cooked.append("\\u").append(Integer.toHexString(c + 65536));
            }
        }
        final int length = cooked.length();
        if (length >= 5 && cooked.substring(length - 5).equals("\n + \"")) {
            cooked.setLength(length - 5);
        }
        cooked.append("\"");
        return cooked.toString();
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            ToJavaStringLiteral.trt[i] = "?";
        }
        for (int i = 32; i < 127; ++i) {
            ToJavaStringLiteral.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 161; ++i) {
            ToJavaStringLiteral.trt[i] = "?";
        }
        for (int i = 161; i < 256; ++i) {
            ToJavaStringLiteral.trt[i] = "\\u" + Integer.toHexString(i + 65536).substring(1, 5);
        }
        ToJavaStringLiteral.trt[9] = "\\t";
        ToJavaStringLiteral.trt[10] = "\\n\"\n + \"";
        ToJavaStringLiteral.trt[13] = "";
        ToJavaStringLiteral.trt[26] = "";
        ToJavaStringLiteral.trt[34] = "\\\"";
        ToJavaStringLiteral.trt[39] = "'";
        ToJavaStringLiteral.trt[92] = "\\\\";
        ToJavaStringLiteral.trt[160] = " ";
    }
}
