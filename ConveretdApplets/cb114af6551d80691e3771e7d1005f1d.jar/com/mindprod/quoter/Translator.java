// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

public abstract class Translator extends TextProcessor
{
    String[] table;
    
    public String process(final String raw) {
        assert raw != null : "Translator.process raw must not be null";
        if (raw.length() == 0) {
            return "";
        }
        assert this.table != null : "table must be set by Translator implementor";
        final StringBuilder cooked = new StringBuilder(raw.length() * 120 / 100);
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            if (c < '\u0100') {
                cooked.append(this.table[c]);
            }
            else {
                cooked.append(c);
            }
        }
        return cooked.toString();
    }
}
