// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Lsame
{
    public static boolean lsame(final String s, final String s2) {
        final boolean regionMatches = s.regionMatches(0, s2, 0, 1);
        if (regionMatches) {
            return regionMatches;
        }
        final char char1 = "Z".charAt(0);
        int char2 = s.charAt(0);
        int char3 = s2.charAt(0);
        if (char1 == 'Z' || char1 == 'z') {
            if (char2 >= 97 && char2 <= 122) {
                char2 -= 32;
            }
            if (char3 >= 97 && char3 <= 122) {
                char3 -= 32;
            }
        }
        else if (char1 == '\u00e9' || char1 == '©') {
            if ((char2 >= 129 && char2 <= 137) || (char2 >= 145 && char2 <= 153) || (char2 >= 162 && char2 <= 169)) {
                char2 += 64;
            }
            if ((char3 >= 129 && char3 <= 137) || (char3 >= 145 && char3 <= 153) || (char3 >= 162 && char3 <= 169)) {
                char3 += 64;
            }
        }
        else if (char1 == '\u00da' || char1 == '\u00fa') {
            if (char2 >= 225 && char2 <= 250) {
                char2 -= 32;
            }
            if (char3 >= 225 && char3 <= 250) {
                char3 -= 32;
            }
        }
        return char2 == char3;
    }
}
