// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import java.util.BitSet;
import com.mindprod.common11.ST;

final class Span extends TextProcessor
{
    private static String displayChar(final char c) {
        switch (c) {
            case '\u0007': {
                return "\\a";
            }
            case '\b': {
                return "\\b";
            }
            case '\u001f': {
                return "\\e";
            }
            case '\f': {
                return "\\f";
            }
            case '\t': {
                return "\\t";
            }
            case '\n': {
                return "\\n";
            }
            case '\r': {
                return "\\r";
            }
            case '\u0013': {
                return "\\v";
            }
            case '$': {
                return "\\$";
            }
            case '&': {
                return "\\&";
            }
            case '(': {
                return "\\(";
            }
            case ')': {
                return "\\)";
            }
            case '*': {
                return "\\*";
            }
            case '+': {
                return "\\+";
            }
            case '-': {
                return "\\-";
            }
            case '.': {
                return "\\.";
            }
            case '?': {
                return "\\?";
            }
            case '[': {
                return "\\[";
            }
            case '\\': {
                return "\\\\";
            }
            case ']': {
                return "\\]";
            }
            case '^': {
                return "\\^";
            }
            case '|': {
                return "\\|";
            }
            case '{': {
                return "\\{";
            }
            case '}': {
                return "\\}";
            }
            default: {
                if (c < ' ' || c == '\u007f' || c == '\u00ff') {
                    return "\\x" + ST.toLZHexString(c, 2);
                }
                return String.valueOf(c);
            }
        }
    }
    
    String process(final String raw) {
        assert raw != null : "TextProcessor.process raw must not be null";
        final BitSet used = new BitSet(4096);
        for (int i = 0; i < raw.length(); ++i) {
            used.set(raw.charAt(i));
        }
        final StringBuilder sb = new StringBuilder(raw.length() * 2);
        sb.append('[');
        for (int j = 0; j < 4096; ++j) {
            if (used.get(j)) {
                int lastUsed = 4095;
                for (int k = j + 1; k < 4096; ++k) {
                    if (!used.get(k)) {
                        lastUsed = k - 1;
                        break;
                    }
                }
                if (lastUsed > j + 2) {
                    sb.append(displayChar((char)j));
                    sb.append('-');
                    sb.append(displayChar((char)lastUsed));
                    j = lastUsed;
                }
                else {
                    sb.append(displayChar((char)j));
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
