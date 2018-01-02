// 
// Decompiled by Procyon v0.5.30
// 

package irc.com;

public class Modes
{
    public static int alphaToMask(final char c) {
        switch (c) {
            case 'q': {
                return 32;
            }
            case 'a': {
                return 16;
            }
            case 'o': {
                return 2;
            }
            case 'h': {
                return 4;
            }
            case 'v': {
                return 8;
            }
            default: {
                return 1;
            }
        }
    }
    
    public static String canonicalizeNick(final String s) {
        if (1 == symbolicToMask(s.charAt(0))) {
            return s;
        }
        return s.substring(1);
    }
    
    public static String maskToAlpha(final int n) {
        if ((0x20 & n) != 0x0) {
            return "q";
        }
        if ((0x10 & n) != 0x0) {
            return "a";
        }
        if ((0x2 & n) != 0x0) {
            return "o";
        }
        if ((0x4 & n) != 0x0) {
            return "h";
        }
        if ((0x8 & n) != 0x0) {
            return "v";
        }
        return "";
    }
    
    public static String maskToSymbolic(final int n) {
        if ((0x20 & n) != 0x0) {
            return "~";
        }
        if ((0x10 & n) != 0x0) {
            return "&";
        }
        if ((0x2 & n) != 0x0) {
            return "@";
        }
        if ((0x4 & n) != 0x0) {
            return "%";
        }
        if ((0x8 & n) != 0x0) {
            return "+";
        }
        return "";
    }
    
    public static int symbolicToMask(final char c) {
        switch (c) {
            case '~': {
                return 32;
            }
            case '&': {
                return 16;
            }
            case '@': {
                return 2;
            }
            case '%': {
                return 4;
            }
            case '+': {
                return 8;
            }
            default: {
                return 1;
            }
        }
    }
    
    public static String uncanonicalizeNick(final String s, final int n) {
        return maskToSymbolic(n).concat(s);
    }
}
