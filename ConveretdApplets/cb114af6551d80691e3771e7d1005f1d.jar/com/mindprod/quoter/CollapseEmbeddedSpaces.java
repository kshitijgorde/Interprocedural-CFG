// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class CollapseEmbeddedSpaces extends TextProcessor
{
    String process(String raw) {
        if (raw == null) {
            return null;
        }
        final char[] ca = new char[raw.length()];
        int j = 0;
        boolean suppress = false;
        boolean inLeading = true;
        int spacesSuppressed = 0;
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            switch (c) {
                case '\t':
                case ' ': {
                    if (inLeading || !suppress) {
                        ca[j++] = c;
                        suppress = true;
                        break;
                    }
                    ++spacesSuppressed;
                    break;
                }
                case '\n': {
                    while (spacesSuppressed > 0) {
                        ca[j++] = ' ';
                        --spacesSuppressed;
                    }
                    ca[j++] = c;
                    inLeading = true;
                    suppress = false;
                    spacesSuppressed = 0;
                    break;
                }
                default: {
                    ca[j++] = c;
                    inLeading = false;
                    suppress = false;
                    spacesSuppressed = 0;
                    break;
                }
            }
        }
        while (spacesSuppressed > 0) {
            ca[j++] = ' ';
            --spacesSuppressed;
        }
        if (j != raw.length()) {
            raw = new String(ca, 0, j);
        }
        return raw;
    }
}
