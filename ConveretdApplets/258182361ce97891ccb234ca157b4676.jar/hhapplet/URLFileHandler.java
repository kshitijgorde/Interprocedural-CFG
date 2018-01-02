// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;

public class URLFileHandler
{
    public static String GetNormalizedLocal2(final String s) {
        String string = s;
        boolean b = true;
        if (string.startsWith("http://")) {
            b = false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) > '\u007f' || (string.charAt(i) == ' ' && !b)) {
                String s2 = Integer.toString(string.charAt(i), 16);
                if (s2.length() > 2) {
                    if (s2.compareTo("152") == 0) {
                        s2 = "8C";
                    }
                    else {
                        s2 = s2.substring(2);
                    }
                }
                string = string.substring(0, i) + "%" + s2 + string.substring(i + 1, string.length());
            }
        }
        return string;
    }
    
    public static String GetNormalizedLocal(final String s) {
        String getNormalizedLocal2 = s;
        if (System.getProperty("java.vendor").indexOf("Netscape") != -1 || System.getProperty("java.vendor").indexOf("Sun Microsystems") != -1 || getNormalizedLocal2.startsWith("http://")) {
            getNormalizedLocal2 = GetNormalizedLocal2(s);
        }
        return getNormalizedLocal2;
    }
    
    public static String TruncURLtoQuestionMark(final String s) {
        String substring = s;
        final int index = s.indexOf(63);
        if (index != -1) {
            substring = s.substring(0, index);
        }
        return substring;
    }
    
    public static URL makeURL(final URL url, final String s, final String s2) throws MalformedURLException {
        try {
            return new URL(GetNormalizedLocal(new URL(new URL(url.getProtocol(), url.getHost(), url.getPort(), TruncURLtoQuestionMark(tuHtmlToText(url.getFile()))), tuHtmlToText(s)).toString()));
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            if (new File(s).exists()) {
                return new URL("file:/" + s);
            }
            return new URL(url, s2);
        }
    }
    
    static String tuHtmlToText(String substring) {
        if (substring == null) {
            return null;
        }
        int n = substring.indexOf(38);
        if (n < 0) {
            return substring;
        }
        String s = "";
        while (n > -1 && n < substring.length() - 2) {
            final String string = s + substring.substring(0, n);
            final String substring2 = substring.substring(n);
            final int index = substring2.indexOf(59);
            if (index < 0) {
                s = string + substring2;
                break;
            }
            if (index < substring2.length() - 1) {
                substring = substring2.substring(index + 1);
            }
            else {
                substring = "";
            }
            String substring3 = substring2.substring(1, index);
            switch (Character.toUpperCase(substring3.charAt(0))) {
                case 'A': {
                    if (substring3.equalsIgnoreCase("amp")) {
                        substring3 = "&";
                        break;
                    }
                    break;
                }
                case 'C': {
                    if (substring3.equalsIgnoreCase("copy")) {
                        substring3 = "(c)";
                        break;
                    }
                    break;
                }
                case 'G': {
                    if (substring3.equalsIgnoreCase("gt")) {
                        substring3 = ">";
                        break;
                    }
                    break;
                }
                case 'L': {
                    if (substring3.equalsIgnoreCase("lt")) {
                        substring3 = "<";
                        break;
                    }
                    break;
                }
                case 'N': {
                    if (substring3.equalsIgnoreCase("nbsp")) {
                        substring3 = " ";
                        break;
                    }
                    break;
                }
                case 'Q': {
                    if (substring3.equalsIgnoreCase("quot")) {
                        substring3 = "\"";
                        break;
                    }
                    break;
                }
                case 'R': {
                    if (substring3.equalsIgnoreCase("reg")) {
                        substring3 = "(R)";
                        break;
                    }
                    break;
                }
            }
            s = string + substring3;
            n = substring.indexOf(38);
            if (n >= 0) {
                continue;
            }
            s += substring;
        }
        return s;
    }
}
