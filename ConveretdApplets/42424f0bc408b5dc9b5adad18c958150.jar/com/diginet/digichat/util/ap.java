// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class ap
{
    public static String a(final String s, final String[] array) {
        try {
            final int length = array.length;
            int n = 0;
            int n2 = s.indexOf(37);
            if (n2 == -1) {
                return s;
            }
            String s2 = new String("");
            do {
                s2 = s2.concat(s.substring(n, n2));
                final char char1 = s.charAt(n2 + 1);
                switch (char1) {
                    case 37: {
                        s2 = s2.concat("%");
                        ++n2;
                        break;
                    }
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57: {
                        final char c = (char)(char1 - '1');
                        if (c < length && array[c] != null) {
                            s2 = s2.concat(array[c]);
                        }
                        ++n2;
                        break;
                    }
                    default: {
                        System.out.println("I don't know what to do with " + s);
                        return null;
                    }
                }
                n = n2 + 1;
                n2 = s.indexOf(37, n);
            } while (n2 != -1 && n2 < s.length());
            return s2.concat(s.substring(n));
        }
        catch (Exception ex) {
            System.err.println("format = " + s);
            ex.printStackTrace();
            return null;
        }
    }
}
