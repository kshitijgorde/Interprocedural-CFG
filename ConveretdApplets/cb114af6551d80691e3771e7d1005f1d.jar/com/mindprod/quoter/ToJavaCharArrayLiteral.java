// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class ToJavaCharArrayLiteral extends Translator
{
    private static final String[] trt;
    
    public ToJavaCharArrayLiteral() {
        this.table = ToJavaCharArrayLiteral.trt;
    }
    
    public String process(final String raw) {
        final StringBuilder cooked = new StringBuilder(raw.length() * 5);
        cooked.append("new char[] { ");
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            if (c == '\n') {
                cooked.append("'\\n',\n    ");
            }
            else if (c < '\u0100') {
                cooked.append("'");
                cooked.append(this.table[c]);
                cooked.append("', ");
            }
            else {
                cooked.append("'\\u").append(Integer.toHexString(c + 65536));
                cooked.append("', ");
            }
        }
        final int length = cooked.length();
        if (length >= 2 && cooked.substring(length - 2).equals(", ")) {
            cooked.setLength(length - 2);
        }
        else if (length >= 6 && cooked.substring(length - 6).equals(",\n    ")) {
            cooked.setLength(length - 6);
        }
        cooked.append(" }");
        return cooked.toString();
    }
    
    static {
        trt = new String[256];
        for (int i = 0; i < 32; ++i) {
            ToJavaCharArrayLiteral.trt[i] = "?";
        }
        for (int i = 32; i < 127; ++i) {
            ToJavaCharArrayLiteral.trt[i] = String.valueOf((char)i).intern();
        }
        for (int i = 127; i < 161; ++i) {
            ToJavaCharArrayLiteral.trt[i] = "?";
        }
        for (int i = 161; i < 256; ++i) {
            ToJavaCharArrayLiteral.trt[i] = "\\u" + Integer.toHexString(i + 65536).substring(1, 5);
        }
        ToJavaCharArrayLiteral.trt[9] = "\\t";
        ToJavaCharArrayLiteral.trt[10] = "\\n";
        ToJavaCharArrayLiteral.trt[13] = "";
        ToJavaCharArrayLiteral.trt[26] = "";
        ToJavaCharArrayLiteral.trt[34] = "\\\"";
        ToJavaCharArrayLiteral.trt[39] = "'";
        ToJavaCharArrayLiteral.trt[92] = "\\\\";
        ToJavaCharArrayLiteral.trt[160] = " ";
    }
}
