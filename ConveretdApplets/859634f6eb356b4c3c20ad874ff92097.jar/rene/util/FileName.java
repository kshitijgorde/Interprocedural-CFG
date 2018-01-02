// 
// Decompiled by Procyon v0.5.30
// 

package rene.util;

import java.io.File;

public class FileName
{
    public static int ChopLength;
    
    public static String purefilename(final String s) {
        final char[] charArray = s.toCharArray();
        int i = charArray.length - 1;
        final char separatorChar = File.separatorChar;
        while (i >= 0) {
            if (charArray[i] == separatorChar || charArray[i] == '/' || i == 0) {
                if (i == 0) {
                    i = -1;
                }
                if (i >= charArray.length - 1) {
                    return "";
                }
                int n;
                for (n = charArray.length - 1; n > i && charArray[n] != '.'; --n) {}
                if (n > i + 1) {
                    return new String(charArray, i + 1, n - i - 1);
                }
                return "";
            }
            else {
                --i;
            }
        }
        return s;
    }
    
    public static String path(final String s) {
        final char[] charArray = s.toCharArray();
        int i = charArray.length - 1;
        final char separatorChar = File.separatorChar;
        while (i > 0) {
            if (charArray[i] == separatorChar || charArray[i] == '/') {
                return new String(charArray, 0, i);
            }
            --i;
        }
        return "";
    }
    
    public static String filename(final String s) {
        final char[] charArray = s.toCharArray();
        int i = charArray.length - 1;
        final char separatorChar = File.separatorChar;
        while (i > 0) {
            if (charArray[i] == separatorChar || charArray[i] == '/') {
                if (i + 1 < charArray.length) {
                    return new String(charArray, i + 1, charArray.length - i - 1);
                }
                return "";
            }
            else {
                --i;
            }
        }
        return s;
    }
    
    public static String extension(final String s) {
        final char[] charArray = s.toCharArray();
        int i = charArray.length - 1;
        final char separatorChar = File.separatorChar;
        while (i > 0) {
            if (charArray[i] == '.') {
                if (i + 1 < charArray.length) {
                    return new String(charArray, i + 1, charArray.length - i - 1);
                }
                return "";
            }
            else {
                if (charArray[i] == separatorChar) {
                    break;
                }
                if (charArray[i] == '/') {
                    break;
                }
                --i;
            }
        }
        return "";
    }
    
    public static String chop(String string) {
        if (string.length() > FileName.ChopLength) {
            string = "..." + string.substring(string.length() - FileName.ChopLength);
        }
        return string;
    }
    
    public static String relative(String string, final String s) {
        string = String.valueOf(string) + System.getProperty("file.separator");
        if (s.startsWith(string)) {
            return s.substring(string.length());
        }
        return s;
    }
    
    static {
        FileName.ChopLength = 48;
    }
}
