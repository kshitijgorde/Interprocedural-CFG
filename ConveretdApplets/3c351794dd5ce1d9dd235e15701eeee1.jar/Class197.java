// 
// Decompiled by Procyon v0.5.30
// 

final class Class197
{
    static int anInt1513;
    int anInt1514;
    static long aLong1515;
    private byte[][] aByteArrayArray1516;
    int anInt1517;
    int anInt1518;
    private byte[] aByteArray1519;
    static int anInt1520;
    
    final int method2669(final int n, final int n2, final String s, final Class332[] array) {
        try {
            if (n2 != 0) {
                return 121;
            }
            return this.method2675(s, Class54.aStringArray3393, new int[] { n }, array, -1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.A(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method2670(final int n, final String s, final Class332[] array, final byte b) {
        try {
            final int method2675 = this.method2675(s, Class54.aStringArray3393, new int[] { n }, array, -1);
            int n2 = 0;
            for (int i = 0; i < method2675; ++i) {
                final int method2676 = this.method2676((byte)64, array, Class54.aStringArray3393[i]);
                if (~n2 > ~method2676) {
                    n2 = method2676;
                }
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.F(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final int method2671(final byte b, final char c, final int n) {
        try {
            if (b != -50) {
                this.method2673((byte)(-79), 29);
            }
            if (this.aByteArrayArray1516 != null) {
                return this.aByteArrayArray1516[n][c];
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.H(" + b + ',' + c + ',' + n + ')');
        }
    }
    
    final int method2672(final Class332[] array, final int n, int anInt1518, final String s, final boolean b) {
        try {
            if (!b) {
                return 61;
            }
            if (anInt1518 == 0) {
                anInt1518 = this.anInt1518;
            }
            return this.anInt1514 + this.anInt1517 - -((this.method2675(s, Class54.aStringArray3393, new int[] { n }, array, -1) - 1) * anInt1518);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.C(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + anInt1518 + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final int method2673(final byte b, final int n) {
        try {
            return 0xFF & this.aByteArray1519[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.B(" + b + ',' + n + ')');
        }
    }
    
    final int method2674(final String s, final int n) {
        try {
            if (n < 98) {
                this.method2670(-112, null, null, (byte)87);
            }
            return this.method2676((byte)118, null, s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.I(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final int method2675(final String s, final String[] array, final int[] array2, final Class332[] array3, final int n) {
        try {
            if (s == null) {
                return 0;
            }
            byte b = 0;
            int n2 = 0;
            int n3 = -1;
            byte b2 = 0;
            int n4 = 0;
            int n5 = n;
            int n6 = -1;
            int n7 = 0;
            for (int i = s.length(), n8 = 0; i > n8; ++n8) {
                int n9 = 0xFF & Class349.method3843((byte)88, s.charAt(n8));
                int n10 = 0;
                if (n9 == 60) {
                    n5 = n8;
                }
                else {
                    int n11;
                    if (n5 == -1) {
                        n11 = n8;
                        n10 += this.method2673((byte)(-117), n9);
                        if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                            n10 += this.aByteArrayArray1516[n6][n9];
                        }
                        n6 = n9;
                    }
                    else {
                        if (~n9 != 0xFFFFFFC1) {
                            continue;
                        }
                        n11 = n5;
                        final String substring = s.substring(n5 + 1, n8);
                        n5 = -1;
                        if (!substring.equals("br")) {
                            if (substring.equals("lt")) {
                                n10 += this.method2673((byte)49, 60);
                                if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                    n10 += this.aByteArrayArray1516[n6][60];
                                }
                                n6 = 60;
                            }
                            else if (!substring.equals("gt")) {
                                if (!substring.equals("nbsp")) {
                                    if (!substring.equals("shy")) {
                                        if (substring.equals("times")) {
                                            n10 += this.method2673((byte)77, 215);
                                            if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                                n10 += this.aByteArrayArray1516[n6][215];
                                            }
                                            n6 = 215;
                                        }
                                        else if (substring.equals("euro")) {
                                            n10 += this.method2673((byte)(-124), 8364);
                                            if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                                n10 += this.aByteArrayArray1516[n6][8364];
                                            }
                                            n6 = 8364;
                                        }
                                        else if (!substring.equals("copy")) {
                                            if (!substring.equals("reg")) {
                                                if (substring.startsWith("img=") && array3 != null) {
                                                    try {
                                                        n10 += array3[PacketSender.method3607(48, substring.substring(4))].method3737();
                                                        n6 = -1;
                                                    }
                                                    catch (Exception ex2) {}
                                                }
                                            }
                                            else {
                                                n10 += this.method2673((byte)5, 174);
                                                if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                                    n10 += this.aByteArrayArray1516[n6][174];
                                                }
                                                n6 = 174;
                                            }
                                        }
                                        else {
                                            n10 += this.method2673((byte)(-126), 169);
                                            if (this.aByteArrayArray1516 != null && n6 != -1) {
                                                n10 += this.aByteArrayArray1516[n6][169];
                                            }
                                            n6 = 169;
                                        }
                                    }
                                    else {
                                        n10 += this.method2673((byte)(-114), 173);
                                        if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                            n10 += this.aByteArrayArray1516[n6][173];
                                        }
                                        n6 = 173;
                                    }
                                }
                                else {
                                    n10 += this.method2673((byte)9, 160);
                                    if (this.aByteArrayArray1516 != null && n6 != -1) {
                                        n10 += this.aByteArrayArray1516[n6][160];
                                    }
                                    n6 = 160;
                                }
                            }
                            else {
                                n10 += this.method2673((byte)51, 62);
                                if (this.aByteArrayArray1516 != null && ~n6 != 0x0) {
                                    n10 += this.aByteArrayArray1516[n6][62];
                                }
                                n6 = 62;
                            }
                            n9 = -1;
                        }
                        else {
                            array[n7] = s.substring(n2, n8 + 1);
                            if (++n7 >= array.length) {
                                return 0;
                            }
                            n3 = -1;
                            b = 0;
                            n6 = -1;
                            n2 = n8 + 1;
                            continue;
                        }
                    }
                    if (n10 > 0) {
                        b += (byte)n10;
                        if (array2 != null) {
                            if (n9 == 32) {
                                n4 = 1;
                                b2 = b;
                                n3 = n8;
                            }
                            if (~array2[(~n7 > ~array2.length) ? n7 : (array2.length - 1)] > ~b) {
                                if (n3 < 0) {
                                    array[n7] = s.substring(n2, n11);
                                    if (++n7 >= array.length) {
                                        return 0;
                                    }
                                    n3 = -1;
                                    n2 = n11;
                                    n6 = -1;
                                    b = (byte)n10;
                                }
                                else {
                                    array[n7] = s.substring(n2, -n4 + 1 + n3);
                                    ++n7;
                                    if (array.length <= n7) {
                                        return 0;
                                    }
                                    n2 = n3 + 1;
                                    b -= b2;
                                    n3 = -1;
                                    n6 = -1;
                                }
                            }
                            if (n9 == 45) {
                                b2 = b;
                                n4 = 0;
                                n3 = n8;
                            }
                        }
                    }
                }
            }
            if (~s.length() < ~n2) {
                array[n7] = s.substring(n2, s.length());
                ++n7;
            }
            return n7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.D(" + ((s != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class197(final byte[] array) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            if (~class98_Sub22.readUnsignedByte((byte)(-110)) != -1) {
                throw new RuntimeException("");
            }
            final boolean b = class98_Sub22.readUnsignedByte((byte)121) == 1;
            class98_Sub22.method1190(this.aByteArray1519 = new byte[256], true, 256, 0);
            if (b) {
                final int[] array2 = new int[256];
                final int[] array3 = new int[256];
                for (int n = 0; ~n > -257; ++n) {
                    array2[n] = class98_Sub22.readUnsignedByte((byte)94);
                }
                for (int n2 = 0; ~n2 > -257; ++n2) {
                    array3[n2] = class98_Sub22.readUnsignedByte((byte)(-114));
                }
                final byte[][] array4 = new byte[256][];
                for (int i = 0; i < 256; ++i) {
                    array4[i] = new byte[array2[i]];
                    byte b2 = 0;
                    for (int j = 0; j < array4[i].length; ++j) {
                        b2 += class98_Sub22.readSignedByte((byte)(-19));
                        array4[i][j] = b2;
                    }
                }
                final byte[][] array5 = new byte[256][];
                for (int n3 = 0; ~n3 > -257; ++n3) {
                    array5[n3] = new byte[array2[n3]];
                    byte b3 = 0;
                    for (int k = 0; k < array5[n3].length; ++k) {
                        b3 += class98_Sub22.readSignedByte((byte)(-19));
                        array5[n3][k] = b3;
                    }
                }
                this.aByteArrayArray1516 = new byte[256][256];
                for (int n4 = 0; ~n4 > -257; ++n4) {
                    if (n4 != 32 && n4 != 160) {
                        for (int l = 0; l < 256; ++l) {
                            if (l != 32 && l != 160) {
                                this.aByteArrayArray1516[n4][l] = (byte)Class378.method4003(l, n4, array4, false, array2, array5, array3, this.aByteArray1519);
                            }
                        }
                    }
                }
                this.anInt1518 = array2[32] + array3[32];
            }
            else {
                this.anInt1518 = class98_Sub22.readUnsignedByte((byte)(-104));
            }
            class98_Sub22.readUnsignedByte((byte)21);
            class98_Sub22.readUnsignedByte((byte)(-109));
            this.anInt1517 = class98_Sub22.readUnsignedByte((byte)119);
            this.anInt1514 = class98_Sub22.readUnsignedByte((byte)(-98));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.<init>(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method2676(final byte b, final Class332[] array, final String s) {
        try {
            if (s == null) {
                return 0;
            }
            int n = -1;
            int n2 = -1;
            int n3 = 0;
            for (int length = s.length(), n4 = 0; ~n4 > ~length; ++n4) {
                char char1 = s.charAt(n4);
                if (char1 == '<') {
                    n = n4;
                }
                else {
                    if (char1 == '>' && ~n != 0x0) {
                        final String substring = s.substring(n + 1, n4);
                        n = -1;
                        if (substring.equals("lt")) {
                            char1 = '<';
                        }
                        else if (substring.equals("gt")) {
                            char1 = '>';
                        }
                        else if (substring.equals("nbsp")) {
                            char1 = ' ';
                        }
                        else if (!substring.equals("shy")) {
                            if (substring.equals("times")) {
                                char1 = '\u00d7';
                            }
                            else if (!substring.equals("euro")) {
                                if (!substring.equals("copy")) {
                                    if (!substring.equals("reg")) {
                                        if (substring.startsWith("img=") && array != null) {
                                            try {
                                                final int method3607 = PacketSender.method3607(-51, substring.substring(4));
                                                n2 = -1;
                                                n3 += array[method3607].method3737();
                                            }
                                            catch (Exception ex2) {}
                                        }
                                        continue;
                                    }
                                    else {
                                        char1 = '®';
                                    }
                                }
                                else {
                                    char1 = '©';
                                }
                            }
                            else {
                                char1 = '\u20ac';
                            }
                        }
                        else {
                            char1 = '\u00ad';
                        }
                    }
                    if (~n == 0x0) {
                        n3 += (0xFF & this.aByteArray1519[Class349.method3843((byte)88, char1) & 0xFF]);
                        if (this.aByteArrayArray1516 != null && n2 != -1) {
                            n3 += this.aByteArrayArray1516[n2][char1];
                        }
                        n2 = char1;
                    }
                }
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.G(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    final String method2677(final String s, final boolean b, final Class332[] array, int n) {
        try {
            if (~n <= ~this.method2676((byte)(-110), array, s)) {
                return s;
            }
            n -= this.method2676((byte)87, null, "...");
            int n2 = -1;
            int n3 = -1;
            int n4 = 0;
            if (b) {
                this.aByteArrayArray1516 = null;
            }
            final int length = s.length();
            String s2 = "";
            for (int n5 = 0; ~n5 > ~length; ++n5) {
                char char1 = s.charAt(n5);
                if (~char1 == 0xFFFFFFC3) {
                    n2 = n5;
                }
                else {
                    if (char1 == '>' && n2 != -1) {
                        final String substring = s.substring(n2 + 1, n5);
                        n2 = -1;
                        if (!substring.equals("lt")) {
                            if (!substring.equals("gt")) {
                                if (substring.equals("nbsp")) {
                                    char1 = ' ';
                                }
                                else if (substring.equals("shy")) {
                                    char1 = '\u00ad';
                                }
                                else if (substring.equals("times")) {
                                    char1 = '\u00d7';
                                }
                                else if (!substring.equals("euro")) {
                                    if (substring.equals("copy")) {
                                        char1 = '©';
                                    }
                                    else if (!substring.equals("reg")) {
                                        if (substring.startsWith("img=") && array != null) {
                                            try {
                                                final int method3607 = PacketSender.method3607(-96, substring.substring(4));
                                                n3 = -1;
                                                n4 += array[method3607].method3737();
                                                if (~n > ~n4) {
                                                    return s2 + "...";
                                                }
                                                s2 = s.substring(0, 1 + n5);
                                            }
                                            catch (Exception ex2) {}
                                        }
                                        continue;
                                    }
                                    else {
                                        char1 = '®';
                                    }
                                }
                                else {
                                    char1 = '\u20ac';
                                }
                            }
                            else {
                                char1 = '>';
                            }
                        }
                        else {
                            char1 = '<';
                        }
                    }
                    if (n2 == -1) {
                        n4 += (0xFF & this.aByteArray1519[0xFF & Class349.method3843((byte)88, char1)]);
                        if (this.aByteArrayArray1516 != null && n3 != -1) {
                            n4 += this.aByteArrayArray1516[n3][char1];
                        }
                        n3 = char1;
                        byte b2 = (byte)n4;
                        if (this.aByteArrayArray1516 != null) {
                            b2 += this.aByteArrayArray1516[char1][46];
                        }
                        if (~b2 < ~n) {
                            return s2 + "...";
                        }
                        s2 = s.substring(0, n5 + 1);
                    }
                }
            }
            return s;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mv.E(" + ((s != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class197.aLong1515 = 0L;
        Class197.anInt1520 = 2;
    }
}
