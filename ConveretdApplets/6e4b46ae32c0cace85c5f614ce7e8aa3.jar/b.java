import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private static void a(final String s) {
        System.out.println(s);
    }
    
    public static String b(String upperCase) {
        upperCase = upperCase.toUpperCase();
        String string = "";
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            if (char1 >= '0' && char1 <= 'Z') {
                string += char1;
            }
        }
        return string;
    }
    
    public static String a(String b, String c) {
        b = b(b);
        c = c(c);
        String string = "";
        if (c.length() != b.length()) {
            return string;
        }
        int length;
        for (int n = length = c.length(), i = 0; i < n; ++i) {
            length += 7 * i + b.charAt(i) - 48;
            final char c2 = (char)(c.charAt(i) - '0' - length);
            char c3;
            if (c2 >= '\0') {
                c3 = (char)(c2 % '+');
            }
            else {
                c3 = (char)(('+' - -c2 % '+') % '+');
            }
            string += (char)(c3 + '0');
        }
        return string;
    }
    
    private static String c(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            char char1;
            switch (char1 = s.charAt(i)) {
                case 'a': {
                    char1 = ':';
                    break;
                }
                case 'b': {
                    char1 = ';';
                    break;
                }
                case 'c': {
                    char1 = '<';
                    break;
                }
                case 'd': {
                    char1 = '=';
                    break;
                }
                case 'e': {
                    char1 = '>';
                    break;
                }
                case 'f': {
                    char1 = '?';
                    break;
                }
                case 'g': {
                    char1 = '@';
                    break;
                }
            }
            string += char1;
        }
        return string;
    }
    
    public static boolean b(final String s, final String s2) {
        return s2 != null && s != null && a(s, s2).indexOf(b(s)) >= 0;
    }
    
    public static boolean a(final Applet applet, String upperCase) {
        if (applet.getDocumentBase().toString().toLowerCase().indexOf("file:") == 0) {
            a("Running from local file");
            return true;
        }
        if (applet == null || upperCase == null) {
            return false;
        }
        upperCase = upperCase.toUpperCase();
        final String parameter = applet.getParameter("key");
        if (parameter == null) {
            a("No key available");
            return false;
        }
        final String parameter2 = applet.getParameter("domain");
        if (parameter2 == null) {
            a("Domain parameter not defined");
            return false;
        }
        final String upperCase2 = parameter2.toUpperCase();
        final URL documentBase = applet.getDocumentBase();
        final String string = documentBase.getHost().toUpperCase() + documentBase.getFile().toUpperCase();
        if (string.indexOf(upperCase2) < 0) {
            a("Not registered for this domain: " + string + ", registered for: " + upperCase2);
            return false;
        }
        if (!b(upperCase + upperCase2, parameter)) {
            a("Key does not match domain name!");
            return false;
        }
        return true;
    }
}
