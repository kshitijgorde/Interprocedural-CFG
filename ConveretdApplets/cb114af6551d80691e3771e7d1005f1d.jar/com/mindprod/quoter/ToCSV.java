// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class ToCSV extends Translator
{
    public String process(final String raw) {
        final StringBuilder cooked = new StringBuilder(raw.length() * 120 / 100);
        cooked.append("\"");
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            cooked.append(c);
            if (c == '\"') {
                cooked.append(c);
            }
        }
        cooked.append("\",");
        return cooked.toString();
    }
}
