// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class CollapseBlankLines extends TextProcessor
{
    String process(String raw) {
        if (raw == null) {
            return null;
        }
        final char[] ca = new char[raw.length()];
        int j = 0;
        boolean suppressBlankLines = false;
        boolean inLeading = true;
        int spacesSuppressed = 0;
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            switch (c) {
                case '\t':
                case ' ': {
                    ++spacesSuppressed;
                    break;
                }
                case '\n': {
                    if (inLeading) {
                        if (suppressBlankLines) {
                            spacesSuppressed = 0;
                        }
                        else {
                            while (spacesSuppressed > 0) {
                                ca[j++] = ' ';
                                --spacesSuppressed;
                            }
                            ca[j++] = c;
                            suppressBlankLines = true;
                        }
                    }
                    else {
                        while (spacesSuppressed > 0) {
                            ca[j++] = ' ';
                            --spacesSuppressed;
                        }
                        ca[j++] = c;
                        suppressBlankLines = false;
                    }
                    inLeading = true;
                    break;
                }
                default: {
                    while (spacesSuppressed > 0) {
                        ca[j++] = ' ';
                        --spacesSuppressed;
                    }
                    ca[j++] = c;
                    inLeading = false;
                    break;
                }
            }
        }
        if (!inLeading || !suppressBlankLines) {
            while (spacesSuppressed > 0) {
                ca[j++] = ' ';
                --spacesSuppressed;
            }
        }
        if (j != raw.length()) {
            raw = new String(ca, 0, j);
        }
        return raw;
    }
}
