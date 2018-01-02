// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

class StylerUtils
{
    private static byte[] a;
    private static boolean[] b;
    private static boolean[] c;
    private static boolean[] d;
    private static boolean[] e;
    private static boolean[] f;
    
    static char a(final byte b) {
        return (char)((b > 0) ? b : (b + 256));
    }
    
    static int b(final byte b) {
        if (b < 0) {
            return 256 + b;
        }
        return b;
    }
    
    static void a(final int n, int length, final byte[] array, final byte[] array2, final int n2, final char c) {
        int n3 = 0;
        if (n < 0) {
            return;
        }
        if (length > array.length) {
            length = array.length;
        }
        for (int i = n; i < length; ++i) {
            if (n3 == 0) {
                n3 = array2[i];
            }
            array[i] = 32;
            array2[i] = 0;
        }
        a(array, array2, n, length, n2, n3, c);
    }
    
    static int a(final int n, int n2, int n3, int n4, int n5, final int n6, final int n7, final byte[] array, final byte[] array2, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13) {
        if (n2 > 50) {
            n2 = n8 - (99 - n2);
        }
        if (n4 > 50) {
            n4 = n8 - (99 - n4);
        }
        if (n3 > 500) {
            n3 = n9 - (999 - n3);
        }
        if (n5 > 500) {
            n5 = n9 - (999 - n5);
        }
        if (n4 < n2) {
            n4 = n2;
        }
        if (n5 < n3) {
            n5 = n3;
        }
        final int n14 = -n10;
        final int n15 = n6 - n10;
        final int n16 = -n11;
        final int n17 = n7 - n11;
        if (n2 > n15) {
            return -1;
        }
        if (n4 > n15) {
            n4 = n15;
        }
        if (n3 > n17) {
            return -1;
        }
        if (n5 > n17) {
            n5 = n17;
        }
        if (n2 < n14) {
            return -1;
        }
        if (n4 < n14) {
            n4 = n14;
        }
        if (n3 < n16) {
            return -1;
        }
        if (n5 < n16) {
            n5 = n16;
        }
        final int n18 = (n10 + n2) * n7 + (n11 + n3);
        final int n19 = n18 + n5 - n3 + 1;
        if (n < n18) {
            return n18;
        }
        if (n < n19) {
            return n;
        }
        return -1;
    }
    
    static int a(final int n, final boolean b, String s, final int n2, final char c, final char c2, final char c3, char c4, char c5, final char c6, int n3, int n4, int n5, int n6, final StringBuffer sb, final StringBuffer sb2, final StringBuffer sb3, final int n7, final int n8, final byte[] array, final byte[] array2, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final StylerField[] array3, final char c7) {
        boolean b2 = false;
        if (sb != null) {
            sb.setLength(0);
        }
        if (sb2 != null) {
            sb2.setLength(0);
        }
        if (sb3 != null) {
            sb3.setLength(0);
        }
        if (c2 == '0') {
            return -1;
        }
        if (s == null) {
            return -1;
        }
        if (c2 == 'F' && s.equals("??")) {
            s = " ??";
            b2 = true;
        }
        final int length = s.length();
        if (length < 1) {
            return -1;
        }
        final byte[] array4 = new byte[length];
        s.getBytes(0, length, array4, 0);
        if (c3 == '0') {
            for (int i = 0; i < length; ++i) {
                array4[i] = StylerUtils.a[b(array4[i])];
            }
        }
        int n15 = (c2 == 'F' || c2 == 'N') ? 1 : 0;
        final boolean b3 = n15 != 0 && s.charAt(0) == '?';
        final boolean b4 = b3 && length == 1;
        if (b4) {
            c4 = '2';
            c5 = '1';
        }
        if (c2 == 'F') {
            c4 = '1';
        }
        int index = s.indexOf(63);
        int n16 = (index < 0 || index + 1 >= length) ? -1 : s.indexOf(63, index + 1);
        if (n15 == 0) {
            index = -1;
            n16 = -1;
        }
        final int n17 = b3 ? (Math.max(index, n16) + 1) : 0;
        if (n17 >= length && !b4) {
            return -1;
        }
        if (n3 > 50) {
            n3 = n9 - (99 - n3);
        }
        if (n5 > 50) {
            n5 = n9 - (99 - n5);
        }
        if (n4 > 500) {
            n4 = n10 - (999 - n4);
        }
        if (n6 > 500) {
            n6 = n10 - (999 - n6);
        }
        if (n5 < n3) {
            n5 = n3;
        }
        if (n6 < n4) {
            n6 = n4;
        }
        final int n18 = -n11;
        final int n19 = n7 - n11;
        final int n20 = -n12;
        final int n21 = n8 - n12;
        if (n3 > n19) {
            n3 = n19;
        }
        if (n5 > n19) {
            n5 = n19;
        }
        if (n4 > n21) {
            n4 = n21;
        }
        if (n6 > n21) {
            n6 = n21;
        }
        if (n3 < n18) {
            n3 = n18;
        }
        if (n5 < n18) {
            n5 = n18;
        }
        if (n4 < n20) {
            n4 = n20;
        }
        if (n6 < n20) {
            n6 = n20;
        }
        for (int n22 = (n11 + n3) * n8 + (n12 + n4), n23 = n22 + n6 - n4 + 1, j = n5 - n3 + 1; j > 0; --j, n22 += n8, n23 += n8) {
            if (n23 < n22) {
                n23 = n22;
            }
            if (n23 >= array.length) {
                return -1;
            }
            for (int k = (n22 < n) ? n : n22; k < n23; ++k) {
                byte b5 = array[k];
                if (c != '0' || (array2[k] & 0x7) != 0x7) {
                    if (!b3 && c3 == '0') {
                        b5 = StylerUtils.a[b(b5)];
                    }
                    int n24 = 0;
                    byte b6 = 0;
                    byte b7 = 0;
                    if (b4) {
                        if (StylerUtils.f[b(b5)]) {
                            continue;
                        }
                        if (k > n22 && !StylerUtils.f[b(array[k - 1])]) {
                            continue;
                        }
                        if (k < n23 - 1 && !StylerUtils.f[b(array[k + 1])]) {
                            continue;
                        }
                        b6 = b5;
                        n24 = 1;
                    }
                    else {
                        int n25 = n2;
                        int n26 = n17;
                        if (b2 && n26 == 0) {
                            n25 = 0;
                        }
                        if (b5 == array4[n26] && (n25 == 0 || n25 == array2[k])) {
                            int n27 = k + 1;
                            ++n26;
                            while (n27 < n23 && n26 < length) {
                                byte b8 = array[n27];
                                Label_1148: {
                                    if (n26 == index) {
                                        if ((n2 != 0 && n2 != array2[n27]) || (c2 == 'F' && a(n27, array3))) {
                                            break;
                                        }
                                        if (b8 == 32 && c2 == 'F') {
                                            b6 = 48;
                                        }
                                        else {
                                            if (!a(b8, c4, c5)) {
                                                break;
                                            }
                                            b6 = b8;
                                        }
                                    }
                                    else {
                                        if (n26 == n16) {
                                            if (a(b8, c4, c5)) {
                                                if (n2 != 0 && n2 != array2[n27]) {
                                                    break;
                                                }
                                                b7 = b8;
                                                if (c2 == 'F' && n27 < n23 - 3 && array[n27 + 1] == 47 && StylerUtils.e[b(array[n27 + 2])]) {
                                                    n27 += 2;
                                                    if (n27 < n23 - 2 && StylerUtils.e[b(array[n27 + 1])]) {
                                                        ++n27;
                                                    }
                                                    final byte b9 = array[n27];
                                                }
                                                break Label_1148;
                                            }
                                            else {
                                                if (c2 == 'F' && n27 < n23 - 2 && b8 == 47 && StylerUtils.e[b(array[n27 + 1])]) {
                                                    if (n2 != 0 && n2 != array2[n27]) {
                                                        break;
                                                    }
                                                    n27 += 2;
                                                    if (n27 < n23 - 2 && StylerUtils.e[b(array[n27])]) {
                                                        ++n27;
                                                    }
                                                    b8 = array[n27];
                                                }
                                                if (++n26 >= length) {
                                                    break;
                                                }
                                            }
                                        }
                                        if (c3 == '0') {
                                            b8 = StylerUtils.a[b(b8)];
                                        }
                                        if (b8 != array4[n26]) {
                                            break;
                                        }
                                        if (n2 != 0 && n2 != array2[n27]) {
                                            break;
                                        }
                                    }
                                }
                                ++n27;
                                ++n26;
                            }
                            if (b2 && n26 == length && array[n27] != 32) {
                                continue;
                            }
                            if (n26 >= length) {
                                n24 = n27 - k;
                                if (b3) {
                                    int n28;
                                    if ((n28 = k) == n22) {
                                        n24 = 0;
                                    }
                                    else if (!a(array[k - 1], c4, c5)) {
                                        n24 = 0;
                                    }
                                    else if (n16 == 1 && k - 2 >= n22 && a(array[k - 2], c4, c5)) {
                                        b6 = array[k - 2];
                                        b7 = array[k - 1];
                                        n28 = k - 2;
                                        n24 += 2;
                                    }
                                    else {
                                        b6 = array[k - 1];
                                        n28 = k - 1;
                                        ++n24;
                                    }
                                    if (n28 > n22 && array[n28 - 1] != 32 && array[n28 - 1] != 44 && array[n28 - 1] != (byte)c6) {
                                        n28 = k;
                                        n24 = 0;
                                    }
                                    k = n28;
                                }
                                if (c2 == 'F' && index >= 0) {
                                    if (b7 == 0) {
                                        b7 = b6;
                                        b6 = 48;
                                    }
                                    switch (b6) {
                                        case 48: {
                                            if (b7 < 49 || b7 > 57) {
                                                n24 = 0;
                                                break;
                                            }
                                            break;
                                        }
                                        case 49: {
                                            if (b7 < 48 || b7 > 57) {
                                                n24 = 0;
                                                break;
                                            }
                                            break;
                                        }
                                        case 50: {
                                            if (b7 < 48 || b7 > 52) {
                                                n24 = 0;
                                                break;
                                            }
                                            break;
                                        }
                                        default: {
                                            n24 = 0;
                                            break;
                                        }
                                    }
                                    if (b6 == 48) {
                                        b6 = b7;
                                        b7 = 0;
                                    }
                                }
                            }
                            if ((c2 == '1' || c2 == '3') && (n27 < n23 || n6 < n8) && array[n27] != 32) {
                                n24 = 0;
                            }
                            if ((c2 == '1' || c2 == '2') && (k > n22 || n4 > 1) && array[k - 1] != 32) {
                                n24 = 0;
                            }
                        }
                    }
                    if (n24 != 0) {
                        Label_1676: {
                            if (c2 != '3') {
                                if (c2 != '4') {
                                    break Label_1676;
                                }
                            }
                            while (k > n22 && ((k - 1 >= n22 && array[k - 1] != 32) || (k - 2 >= n22 && array[k - 2] != 32))) {
                                --k;
                                ++n24;
                            }
                        }
                        if (c2 == '2' || c2 == '4') {
                            for (int n29 = k + n24 - 1; n29 < n23 - 1 && ((n29 + 1 < n23 && array[n29 + 1] != 32) || (n29 + 2 < n23 && array[n29 + 2] != 32)); ++n29, ++n24) {}
                        }
                        if (c2 == 'N' && (b4 || array4[0] == 40)) {
                            final int n30 = k;
                            while (k > n22 && array[k - 1] != 32) {
                                --k;
                                ++n24;
                            }
                            if (sb3 != null) {
                                for (int l = k; l < n30; ++l) {
                                    sb3.append(a(array[l]));
                                }
                                if (b6 != 0) {
                                    sb3.append(a(b6));
                                }
                                if (b7 != 0) {
                                    sb3.append(a(b7));
                                }
                            }
                        }
                        if (b2) {
                            if (b && n24 == 2 && k > 0 && array[k - 1] == 32) {
                                array2[k - 1] = 0;
                            }
                            if (b) {
                                array2[k] = 0;
                            }
                            ++k;
                            --n24;
                            n15 = 0;
                        }
                        final int n31 = k + n24;
                        if (n15 != 0) {
                            int a = a(k + n24, false, s, n2, '1', c2, c3, c4, c5, c6, n3, n4, k / n8 + 1, n6, null, null, null, n7, n8, array, array2, n9, n10, n11, n12, n13, n14, array3, c7);
                            if (a < 0) {
                                a = n23;
                            }
                            for (int n32 = k + n24; n32 < a; ++n32) {
                                if (array[n32] == 32 && array[n32 - 1] == 32) {
                                    a = n32;
                                    break;
                                }
                            }
                            if (a > k + n24) {
                                n24 = a - k;
                            }
                            while (n24 > 1 && array[k + n24 - 1] == 32) {
                                --n24;
                            }
                        }
                        int n33 = 0;
                        for (int n34 = k, n35 = 0; n34 < n23 && n35 < n24; ++n34, ++n35) {
                            if (sb != null) {
                                sb.append(a(array[n34]));
                            }
                            if (sb3 != null && n34 >= n31) {
                                sb3.append(a(array[n34]));
                            }
                            if (b) {
                                if (n33 == 0) {
                                    n33 = array2[n34];
                                }
                                array[n34] = 32;
                                array2[n34] = 0;
                            }
                        }
                        if (b) {
                            a(array, array2, k, k + n24, n8, n33, c7);
                        }
                        if (sb2 != null) {
                            if (b6 != 0) {
                                sb2.append(a(b6));
                            }
                            if (b7 != 0) {
                                sb2.append(a(b7));
                            }
                        }
                        return k;
                    }
                }
            }
        }
        return -1;
    }
    
    private static void a(final byte[] array, final byte[] array2, final int n, final int n2, final int n3, final int n4, final char c) {
        if (n4 == 0) {
            return;
        }
        if (n4 == 32) {
            return;
        }
        if (c != '1') {
            return;
        }
        final int n5 = n / n3 * n3;
        final int n6 = n5 + n3;
        int n7;
        for (n7 = n - 1; n7 >= n5 && array[n7] == 32 && (array2[n7] == 0 || array2[n7] == n4); --n7) {}
        int n8;
        for (n8 = n2; n8 < n6 && array[n8] == 32 && (array2[n8] == 0 || array2[n8] == n4); ++n8) {}
        if (n7 >= 0 && array[n7] != 32) {
            ++n7;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        for (int i = n7; i < n; ++i) {
            array2[i] = 0;
        }
        for (int j = n2; j < n8; ++j) {
            array2[j] = 0;
        }
    }
    
    private static boolean a(final byte b, final char c, final char c2) {
        final int b2 = b(b);
        if (b == 32) {
            return false;
        }
        switch (c) {
            case '1': {
                if (!StylerUtils.e[b2]) {
                    return false;
                }
                break;
            }
            case '2': {
                if (!StylerUtils.d[b2]) {
                    return false;
                }
                break;
            }
            case '3': {
                if (!StylerUtils.d[b2] && !StylerUtils.e[b]) {
                    return false;
                }
                break;
            }
        }
        switch (c2) {
            case '1': {
                if (!StylerUtils.b[b2]) {
                    return false;
                }
                break;
            }
            case '2': {
                if (!StylerUtils.c[b2]) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static boolean a(final int n, final StylerField[] array) {
        for (int i = 0; i < array.length; ++i) {
            final StylerField stylerField = array[i];
            if (stylerField.u != null && n >= stylerField.x && n <= stylerField.y) {
                return true;
            }
        }
        return false;
    }
    
    static {
        StylerUtils.a = new byte[256];
        StylerUtils.b = new boolean[256];
        StylerUtils.c = new boolean[256];
        StylerUtils.d = new boolean[256];
        StylerUtils.e = new boolean[256];
        StylerUtils.f = new boolean[256];
        for (int i = 0; i < 256; ++i) {
            final char c = (char)i;
            final char upperCase = Character.toUpperCase(c);
            final boolean letter = Character.isLetter(c);
            final boolean digit = Character.isDigit(c);
            StylerUtils.a[i] = (byte)upperCase;
            StylerUtils.b[i] = ((letter && c == upperCase) || digit);
            StylerUtils.c[i] = ((letter && c != upperCase) || digit);
            StylerUtils.d[i] = letter;
            StylerUtils.e[i] = digit;
            StylerUtils.f[i] = (letter && c == upperCase);
        }
    }
}
