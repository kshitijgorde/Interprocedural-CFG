// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.Util;

public final class Ilaenv
{
    public static int ilaenv(final int n, final String s, final String s2, final int n2, final int n3, final int n4, final int n5) {
        final String s3 = new String(" ");
        final String s4 = new String("  ");
        final String s5 = new String("  ");
        final String s6 = new String("   ");
        final String s7 = new String("      ");
        if (n != 1) {
            if (n != 2) {
                if (n != 3) {
                    if (n == 4) {
                        return 6;
                    }
                    if (n == 5) {
                        return 2;
                    }
                    if (n == 6) {
                        return (int)(Math.min(n2, n3) * 1.6);
                    }
                    if (n == 7) {
                        return 1;
                    }
                    if (n == 8) {
                        return 50;
                    }
                    if (n == 9) {
                        return 25;
                    }
                    if (n == 10) {
                        int ieeeck = 1;
                        if (ieeeck == 1) {
                            ieeeck = Ieeeck.ieeeck(0, 0.0, 1.0);
                        }
                        return ieeeck;
                    }
                    if (n == 11) {
                        int ieeeck2 = 1;
                        if (ieeeck2 == 1) {
                            ieeeck2 = Ieeeck.ieeeck(1, 0.0, 1.0);
                        }
                        return ieeeck2;
                    }
                    return -1;
                }
            }
        }
        final boolean b = true;
        String s8 = s;
        final char char1 = s8.substring(1 - 1, 1).charAt(0);
        final char char2 = "Z".charAt(0);
        if (char2 == 'Z' || char2 == 'z') {
            if (char1 >= 'a' && char1 <= 'z') {
                s8 = Util.stringInsert(s8, new Character((char)(char1 - ' ')).toString(), 1, 1);
                int n6 = 2;
                for (int i = 6 - 2 + 1; i > 0; --i) {
                    final char char3 = s8.substring(n6 - 1, n6).charAt(0);
                    if (char3 >= 'a' && char3 <= 'z') {
                        s8 = Util.stringInsert(s8, new Character((char)(char3 - ' ')).toString(), n6, n6);
                    }
                    ++n6;
                }
            }
        }
        else if (char2 == '\u00e9' || char2 == '©') {
            if ((char1 >= '\u0081' && char1 <= '\u0089') || (char1 >= '\u0091' && char1 <= '\u0099') || (char1 >= '¢' && char1 <= '©')) {
                s8 = Util.stringInsert(s8, new Character((char)(char1 + '@')).toString(), 1, 1);
                int n7 = 2;
                for (int j = 6 - 2 + 1; j > 0; --j) {
                    final char char4 = s8.substring(n7 - 1, n7).charAt(0);
                    if ((char4 >= '\u0081' && char4 <= '\u0089') || (char4 >= '\u0091' && char4 <= '\u0099') || (char4 >= '¢' && char4 <= '©')) {
                        s8 = Util.stringInsert(s8, new Character((char)(char4 + '@')).toString(), n7, n7);
                    }
                    ++n7;
                }
            }
        }
        else if (char2 == '\u00da' || char2 == '\u00fa') {
            if (char1 >= '\u00e1' && char1 <= '\u00fa') {
                s8 = Util.stringInsert(s8, new Character((char)(char1 - ' ')).toString(), 1, 1);
                int n8 = 2;
                for (int k = 6 - 2 + 1; k > 0; --k) {
                    final char char5 = s8.substring(n8 - 1, n8).charAt(0);
                    if (char5 >= '\u00e1' && char5 <= '\u00fa') {
                        s8 = Util.stringInsert(s8, new Character((char)(char5 - ' ')).toString(), n8, n8);
                    }
                    ++n8;
                }
            }
        }
        final String substring = s8.substring(1 - 1, 1);
        final boolean b2 = substring.regionMatches(0, "S", 0, 1) || substring.regionMatches(0, "D", 0, 1);
        final boolean b3 = substring.regionMatches(0, "C", 0, 1) || substring.regionMatches(0, "Z", 0, 1);
        if ((b3 || b2) ^ true) {
            return b ? 1 : 0;
        }
        final String substring2 = s8.substring(2 - 1, 3);
        final String substring3 = s8.substring(4 - 1, 6);
        final String substring4 = substring3.substring(2 - 1, 3);
        if (n != 1) {
            if (n == 2) {
                int n9 = 2;
                if (substring2.regionMatches(0, "GE", 0, 2)) {
                    if (substring3.regionMatches(0, "QRF", 0, 3) || substring3.regionMatches(0, "RQF", 0, 3) || substring3.regionMatches(0, "LQF", 0, 3) || substring3.regionMatches(0, "QLF", 0, 3)) {
                        if (b2) {
                            n9 = 2;
                        }
                        else {
                            n9 = 2;
                        }
                    }
                    else if (substring3.regionMatches(0, "HRD", 0, 3)) {
                        if (b2) {
                            n9 = 2;
                        }
                        else {
                            n9 = 2;
                        }
                    }
                    else if (substring3.regionMatches(0, "BRD", 0, 3)) {
                        if (b2) {
                            n9 = 2;
                        }
                        else {
                            n9 = 2;
                        }
                    }
                    else if (substring3.regionMatches(0, "TRI", 0, 3)) {
                        if (b2) {
                            n9 = 2;
                        }
                        else {
                            n9 = 2;
                        }
                    }
                }
                else if (substring2.regionMatches(0, "SY", 0, 2)) {
                    if (substring3.regionMatches(0, "TRF", 0, 3)) {
                        if (b2) {
                            n9 = 8;
                        }
                        else {
                            n9 = 8;
                        }
                    }
                    else if (b2 && substring3.regionMatches(0, "TRD", 0, 3)) {
                        n9 = 2;
                    }
                }
                else if (b3 && substring2.regionMatches(0, "HE", 0, 2)) {
                    if (substring3.regionMatches(0, "TRD", 0, 3)) {
                        n9 = 2;
                    }
                }
                else if (b2 && substring2.regionMatches(0, "OR", 0, 2)) {
                    if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1)) {
                        if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                            n9 = 2;
                        }
                    }
                    else if (substring3.substring(1 - 1, 1).regionMatches(0, "M", 0, 1)) {
                        if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                            n9 = 2;
                        }
                    }
                }
                else if (b3 && substring2.regionMatches(0, "UN", 0, 2)) {
                    if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1)) {
                        if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                            n9 = 2;
                        }
                    }
                    else if (substring3.substring(1 - 1, 1).regionMatches(0, "M", 0, 1)) {
                        if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                            n9 = 2;
                        }
                    }
                }
                return n9;
            }
            if (n == 3) {
                int n10 = 0;
                if (substring2.regionMatches(0, "GE", 0, 2)) {
                    if (substring3.regionMatches(0, "QRF", 0, 3) || substring3.regionMatches(0, "RQF", 0, 3) || substring3.regionMatches(0, "LQF", 0, 3) || substring3.regionMatches(0, "QLF", 0, 3)) {
                        if (b2) {
                            n10 = 128;
                        }
                        else {
                            n10 = 128;
                        }
                    }
                    else if (substring3.regionMatches(0, "HRD", 0, 3)) {
                        if (b2) {
                            n10 = 128;
                        }
                        else {
                            n10 = 128;
                        }
                    }
                    else if (substring3.regionMatches(0, "BRD", 0, 3)) {
                        if (b2) {
                            n10 = 128;
                        }
                        else {
                            n10 = 128;
                        }
                    }
                }
                else if (substring2.regionMatches(0, "SY", 0, 2)) {
                    if (b2 && substring3.regionMatches(0, "TRD", 0, 3)) {
                        n10 = 32;
                    }
                }
                else if (b3 && substring2.regionMatches(0, "HE", 0, 2)) {
                    if (substring3.regionMatches(0, "TRD", 0, 3)) {
                        n10 = 32;
                    }
                }
                else if (b2 && substring2.regionMatches(0, "OR", 0, 2)) {
                    if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1) && (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2))) {
                        n10 = 128;
                    }
                }
                else if (b3 && substring2.regionMatches(0, "UN", 0, 2)) {
                    if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1) && (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2))) {
                        n10 = 128;
                    }
                }
                return n10;
            }
        }
        int n11 = 1;
        if (substring2.regionMatches(0, "GE", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
            else if (substring3.regionMatches(0, "QRF", 0, 3) || substring3.regionMatches(0, "RQF", 0, 3) || substring3.regionMatches(0, "LQF", 0, 3) || substring3.regionMatches(0, "QLF", 0, 3)) {
                if (b2) {
                    n11 = 32;
                }
                else {
                    n11 = 32;
                }
            }
            else if (substring3.regionMatches(0, "HRD", 0, 3)) {
                if (b2) {
                    n11 = 32;
                }
                else {
                    n11 = 32;
                }
            }
            else if (substring3.regionMatches(0, "BRD", 0, 3)) {
                if (b2) {
                    n11 = 32;
                }
                else {
                    n11 = 32;
                }
            }
            else if (substring3.regionMatches(0, "TRI", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
        }
        else if (substring2.regionMatches(0, "PO", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
        }
        else if (substring2.regionMatches(0, "SY", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
            else if (b2 && substring3.regionMatches(0, "TRD", 0, 3)) {
                n11 = 32;
            }
            else if (b2 && substring3.regionMatches(0, "GST", 0, 3)) {
                n11 = 64;
            }
        }
        else if (b3 && substring2.regionMatches(0, "HE", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                n11 = 64;
            }
            else if (substring3.regionMatches(0, "TRD", 0, 3)) {
                n11 = 32;
            }
            else if (substring3.regionMatches(0, "GST", 0, 3)) {
                n11 = 64;
            }
        }
        else if (b2 && substring2.regionMatches(0, "OR", 0, 2)) {
            if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1)) {
                if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                    n11 = 32;
                }
            }
            else if (substring3.substring(1 - 1, 1).regionMatches(0, "M", 0, 1)) {
                if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                    n11 = 32;
                }
            }
        }
        else if (b3 && substring2.regionMatches(0, "UN", 0, 2)) {
            if (substring3.substring(1 - 1, 1).regionMatches(0, "G", 0, 1)) {
                if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                    n11 = 32;
                }
            }
            else if (substring3.substring(1 - 1, 1).regionMatches(0, "M", 0, 1)) {
                if (substring4.regionMatches(0, "QR", 0, 2) || substring4.regionMatches(0, "RQ", 0, 2) || substring4.regionMatches(0, "LQ", 0, 2) || substring4.regionMatches(0, "QL", 0, 2) || substring4.regionMatches(0, "HR", 0, 2) || substring4.regionMatches(0, "TR", 0, 2) || substring4.regionMatches(0, "BR", 0, 2)) {
                    n11 = 32;
                }
            }
        }
        else if (substring2.regionMatches(0, "GB", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                if (b2) {
                    if (n5 <= 64) {
                        n11 = 1;
                    }
                    else {
                        n11 = 32;
                    }
                }
                else if (n5 <= 64) {
                    n11 = 1;
                }
                else {
                    n11 = 32;
                }
            }
        }
        else if (substring2.regionMatches(0, "PB", 0, 2)) {
            if (substring3.regionMatches(0, "TRF", 0, 3)) {
                if (b2) {
                    if (n3 <= 64) {
                        n11 = 1;
                    }
                    else {
                        n11 = 32;
                    }
                }
                else if (n3 <= 64) {
                    n11 = 1;
                }
                else {
                    n11 = 32;
                }
            }
        }
        else if (substring2.regionMatches(0, "TR", 0, 2)) {
            if (substring3.regionMatches(0, "TRI", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
        }
        else if (substring2.regionMatches(0, "LA", 0, 2)) {
            if (substring3.regionMatches(0, "UUM", 0, 3)) {
                if (b2) {
                    n11 = 64;
                }
                else {
                    n11 = 64;
                }
            }
        }
        else if (b2 && substring2.regionMatches(0, "ST", 0, 2)) {
            if (substring3.regionMatches(0, "EBZ", 0, 3)) {
                n11 = 1;
            }
        }
        return n11;
    }
}
