// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

import rene.util.SimpleStringBuffer;

public class XmlTranslator
{
    static SimpleStringBuffer H;
    
    static String toXml(final String s) {
        final int length = s.length();
        XmlTranslator.H.clear();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 60: {
                    toH("&lt;");
                    break;
                }
                case 62: {
                    toH("&gt;");
                    break;
                }
                case 38: {
                    toH("&amp;");
                    break;
                }
                case 39: {
                    toH("&apos;");
                    break;
                }
                case 34: {
                    toH("&quot;");
                    break;
                }
                default: {
                    XmlTranslator.H.append(char1);
                    break;
                }
            }
        }
        return XmlTranslator.H.toString();
    }
    
    static void toH(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            XmlTranslator.H.append(s.charAt(i));
        }
    }
    
    static String toText(final String s) {
        final int length = s.length();
        XmlTranslator.H.clear();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '&') {
                if (find(s, i, "&lt;")) {
                    XmlTranslator.H.append('<');
                    i += 3;
                }
                else if (find(s, i, "&gt;")) {
                    XmlTranslator.H.append('>');
                    i += 3;
                }
                else if (find(s, i, "&quot;")) {
                    XmlTranslator.H.append('\"');
                    i += 5;
                }
                else if (find(s, i, "&apos;")) {
                    XmlTranslator.H.append('\'');
                    i += 5;
                }
                else if (find(s, i, "&amp;")) {
                    XmlTranslator.H.append('&');
                    i += 4;
                }
                else {
                    XmlTranslator.H.append(char1);
                }
            }
            else {
                XmlTranslator.H.append(char1);
            }
        }
        return XmlTranslator.H.toString();
    }
    
    static boolean find(final String s, final int n, final String s2) {
        try {
            for (int i = 0; i < s2.length(); ++i) {
                if (s.charAt(n + i) != s2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static {
        XmlTranslator.H = new SimpleStringBuffer(10000);
    }
}
