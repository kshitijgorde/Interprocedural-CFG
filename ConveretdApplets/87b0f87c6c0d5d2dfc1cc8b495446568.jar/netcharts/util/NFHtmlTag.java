// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Hashtable;

public class NFHtmlTag extends Hashtable
{
    public String name;
    public String original;
    private static final boolean a = false;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    
    public NFHtmlTag() {
        this.name = null;
        this.original = null;
    }
    
    public static NFHtmlTag parse(final StringBuffer sb) {
        return parse(sb, null);
    }
    
    public static NFHtmlTag parse(final StringBuffer sb, NFHtmlTag nfHtmlTag) {
        if (nfHtmlTag == null) {
            nfHtmlTag = new NFHtmlTag();
        }
        nfHtmlTag.original = sb.toString();
        final int length = sb.length();
        int n = 1;
        String s = null;
        final StringBuffer sb2 = new StringBuffer();
        int i = 0;
        while (i < length) {
            final char char1 = sb.charAt(i);
            switch (n) {
                case 1: {
                    switch (char1) {
                        case 9:
                        case 10:
                        case 13:
                        case 32: {
                            if (sb2.length() == 0) {
                                ++i;
                                continue;
                            }
                            if (nfHtmlTag.name == null) {
                                nfHtmlTag.name = sb2.toString();
                                sb2.setLength(0);
                                continue;
                            }
                            s = sb2.toString().toLowerCase();
                            sb2.setLength(0);
                            n = 2;
                            continue;
                        }
                        case 61: {
                            if (sb2.length() == 0) {
                                ++i;
                                continue;
                            }
                            s = sb2.toString().toLowerCase();
                            sb2.setLength(0);
                            n = 2;
                            continue;
                        }
                        case 34:
                        case 39: {
                            i = a(sb, i, sb2);
                            s = sb2.toString().trim().toLowerCase();
                            sb2.setLength(0);
                            n = 2;
                            continue;
                        }
                        default: {
                            ++i;
                            sb2.append(char1);
                            continue;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (char1) {
                        case '\t':
                        case '\n':
                        case '\r':
                        case ' ': {
                            ++i;
                            continue;
                        }
                        case '=': {
                            ++i;
                            n = 3;
                            continue;
                        }
                        default: {
                            nfHtmlTag.put(s, "");
                            n = 1;
                            continue;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (char1) {
                        case '\t':
                        case '\n':
                        case '\r':
                        case ' ': {
                            if (sb2.length() == 0) {
                                ++i;
                                continue;
                            }
                            nfHtmlTag.put(s, sb2.toString());
                            sb2.setLength(0);
                            n = 1;
                            continue;
                        }
                        case '\"':
                        case '\'': {
                            if (sb2.length() > 0) {
                                ++i;
                                sb2.append(char1);
                                continue;
                            }
                            i = a(sb, i, sb2);
                            nfHtmlTag.put(s, sb2.toString());
                            sb2.setLength(0);
                            n = 1;
                            continue;
                        }
                        default: {
                            ++i;
                            sb2.append(char1);
                            continue;
                        }
                    }
                    break;
                }
                default: {
                    continue;
                }
            }
        }
        switch (n) {
            case 1:
            case 2: {
                if (sb2.length() == 0) {
                    break;
                }
                if (nfHtmlTag.name == null) {
                    nfHtmlTag.name = sb2.toString();
                    break;
                }
                nfHtmlTag.put(sb2.toString().toLowerCase(), "");
                break;
            }
            case 3: {
                if (sb2.length() == 0) {
                    break;
                }
                nfHtmlTag.put(s, sb2.toString());
                break;
            }
        }
        return nfHtmlTag;
    }
    
    private static int a(final StringBuffer sb, int i, final StringBuffer sb2) {
        final char char1 = sb.charAt(i++);
        while (i < sb.length()) {
            final char char2 = sb.charAt(i++);
            if (char2 == char1) {
                break;
            }
            sb2.append(char2);
        }
        String s = sb2.toString();
        int j = s.indexOf("&#039;");
        StringBuffer sb3 = null;
        while (j >= 0) {
            if (sb3 == null) {
                sb3 = new StringBuffer();
            }
            else {
                sb3.setLength(0);
            }
            sb3.append(s.substring(0, j));
            sb3.append("\\'");
            sb3.append(s.substring(j + 6));
            sb2.setLength(0);
            sb2.append(sb3.toString());
            s = sb2.toString();
            j = s.indexOf("&#039;");
        }
        return i;
    }
}
