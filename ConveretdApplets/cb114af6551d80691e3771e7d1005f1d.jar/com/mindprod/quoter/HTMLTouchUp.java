// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class HTMLTouchUp extends TextProcessor
{
    public String process(final String raw) {
        assert raw != null : "HTMLTouchUp.process raw must not be null";
        int pendingNlCount = 0;
        final StringBuilder result = new StringBuilder(raw.length() + raw.length() / 4);
        result.append("<p>\n");
        for (int i = 0; i < raw.length(); ++i) {
            final char c = raw.charAt(i);
            if (c == '\n') {
                ++pendingNlCount;
            }
            else {
                switch (pendingNlCount) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        result.append("\n<br />\n");
                        break;
                    }
                    default: {
                        result.append("\n</p>\n<p>\n");
                        break;
                    }
                }
                pendingNlCount = 0;
                result.append(c);
            }
        }
        result.append("</p>\n");
        return result.toString();
    }
}
