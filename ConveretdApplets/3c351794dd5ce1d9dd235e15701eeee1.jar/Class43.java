import java.util.Random;
import java.awt.Component;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class43
{
    static int anInt372;
    private Class197 aClass197_373;
    private ha aHa374;
    static Class196 aClass196_375;
    static char[] aCharArray376;
    static int anInt377;
    
    private final void method396(final int n, final int n2, int n3, int n4, final aa aa, final int n5, final String s, final Class332[] array, final int[] array2) {
        try {
            n3 -= this.aClass197_373.anInt1518;
            int n6 = -1;
            int n7 = n5;
            for (int i = s.length(), n8 = 0; i > n8; ++n8) {
                char c = (char)(0xFF & Class349.method3843((byte)88, s.charAt(n8)));
                if (c == '<') {
                    n6 = n8;
                }
                else {
                    if (c == '>' && n6 != -1) {
                        final String substring = s.substring(n6 + 1, n8);
                        n6 = -1;
                        if (substring.equals("lt")) {
                            c = '<';
                        }
                        else if (substring.equals("gt")) {
                            c = '>';
                        }
                        else if (substring.equals("nbsp")) {
                            c = ' ';
                        }
                        else if (substring.equals("shy")) {
                            c = '\u00ad';
                        }
                        else if (!substring.equals("times")) {
                            if (!substring.equals("euro")) {
                                if (!substring.equals("copy")) {
                                    if (substring.equals("reg")) {
                                        c = '®';
                                    }
                                    else {
                                        if (substring.startsWith("img=")) {
                                            try {
                                                final int method3607 = PacketSender.method3607(42, substring.substring(4));
                                                final Class332 class332 = array[method3607];
                                                final int n9 = (array2 != null) ? array2[method3607] : class332.method3749();
                                                if ((Class223.anInt1676 & 0xFF000000) == 0xFF000000) {
                                                    class332.method3748(n4, -n9 + (this.aClass197_373.anInt1518 + n3), 1, 0, 1);
                                                }
                                                else {
                                                    class332.method3748(n4, -n9 + (n3 - -this.aClass197_373.anInt1518), 0, (Class223.anInt1676 & 0xFF000000) | 0xFFFFFF, 1);
                                                }
                                                n4 += array[method3607].method3737();
                                                n7 = -1;
                                            }
                                            catch (Exception ex2) {}
                                            continue;
                                        }
                                        this.method400(substring, -1);
                                        continue;
                                    }
                                }
                                else {
                                    c = '©';
                                }
                            }
                            else {
                                c = '\u20ac';
                            }
                        }
                        else {
                            c = '\u00d7';
                        }
                    }
                    if (~n6 == 0x0) {
                        if (n7 != -1) {
                            n4 += this.aClass197_373.method2671((byte)(-50), c, n7);
                        }
                        if (c == ' ') {
                            if (~Class93_Sub1.anInt5486 < -1) {
                                Class98_Sub47.anInt4276 += Class93_Sub1.anInt5486;
                                n4 += Class98_Sub47.anInt4276 >> 804071592;
                                Class98_Sub47.anInt4276 &= 0xFF;
                            }
                        }
                        else if (aa == null) {
                            if ((Class64_Sub17.anInt3684 & 0xFF000000) != 0x0) {
                                this.fa(c, n4 + 1, n3 + 1, Class64_Sub17.anInt3684, true);
                            }
                            this.fa(c, n4, n3, Class223.anInt1676, false);
                        }
                        else {
                            if ((Class64_Sub17.anInt3684 & 0xFF000000) != 0x0) {
                                this.method409(c, n4 + 1, n3 + 1, Class64_Sub17.anInt3684, true, aa, n, n2);
                            }
                            this.method409(c, n4, n3, Class223.anInt1676, false, aa, n, n2);
                        }
                        final int method3608 = this.aClass197_373.method2673((byte)81, c);
                        if (~Class98_Sub10_Sub5_Sub1.anInt6292 != 0x0) {
                            this.aHa374.method1753(n5 ^ 0xFFFFA8E9, method3608, Class98_Sub10_Sub5_Sub1.anInt6292, n3 + (int)(0.7 * this.aClass197_373.anInt1518), n4);
                        }
                        if (~Class91.anInt724 != 0x0) {
                            this.aHa374.method1753(22294, method3608, Class91.anInt724, this.aClass197_373.anInt1518 + n3 + 1, n4);
                        }
                        n7 = c;
                        n4 += method3608;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.N(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n5 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method397(final int n, final int n2, final int n3, final int n4, final String s, final int n5) {
        try {
            if (s != null) {
                this.method399((byte)(-47), n4, n);
                this.method396(0, n2, n5, -this.aClass197_373.method2674(s, 124) + n3, null, -1, s, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.T(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((s != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    static final void method398(final int n) {
        try {
            Class45.method431(Class215.anInt1614, true, Class265.aHa1974);
            if (~Class15.anInt185 != 0x0) {
                Class350.method3844(Class15.anInt185, -115);
            }
            for (int i = 0; i < Class69_Sub2.anInt5335; ++i) {
                if (aa_Sub3.aBooleanArray3574[i]) {
                    Class98_Sub10_Sub20.aBooleanArray5639[i] = true;
                }
                Class232.aBooleanArray1741[i] = aa_Sub3.aBooleanArray3574[i];
                aa_Sub3.aBooleanArray3574[i] = false;
            }
            Class77_Sub1.anInt3803 = Class215.anInt1614;
            if (n < 33) {
                method398(14);
            }
            if (~Class15.anInt185 != 0x0) {
                Class69_Sub2.anInt5335 = 0;
                Class215.method2791((byte)112);
            }
            Class265.aHa1974.la();
            Class98_Sub10_Sub22.method1069(256, Class265.aHa1974);
            int n2 = Class83.method824((byte)(-70));
            if (~n2 == 0x0) {
                n2 = Class21_Sub2.anInt5387;
            }
            if (n2 == -1) {
                n2 = OutputStream_Sub2.anInt39;
            }
            method401(n2, true);
            Class279.anInt2099 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.F(" + n + ')');
        }
    }
    
    private final void method399(final byte b, int anInt2096, final int n) {
        try {
            Class91.anInt724 = -1;
            Class98_Sub10_Sub5_Sub1.anInt6292 = -1;
            Class23.anInt221 = n;
            Class223.anInt1676 = n;
            Class98_Sub47.anInt4276 = 0;
            Class93_Sub1.anInt5486 = 0;
            if (anInt2096 == -1) {
                anInt2096 = 0;
            }
            Class64_Sub17.anInt3684 = (Class279.anInt2096 = anInt2096);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.P(" + b + ',' + anInt2096 + ',' + n + ')');
        }
    }
    
    private final void method400(final String s, final int n) {
        try {
            try {
                if (n != -1) {
                    this.aClass197_373 = null;
                }
                if (s.startsWith("col=")) {
                    Class223.anInt1676 = ((0xFF000000 & Class223.anInt1676) | (0xFFFFFF & Class98_Sub43_Sub4.method1508(n ^ 0xFFFFFFFE, 16, s.substring(4))));
                }
                else if (s.equals("/col")) {
                    Class223.anInt1676 = ((Class23.anInt221 & 0xFFFFFF) | (Class223.anInt1676 & 0xFF000000));
                }
                if (s.startsWith("argb=")) {
                    Class223.anInt1676 = Class98_Sub43_Sub4.method1508(1, 16, s.substring(5));
                }
                else if (!s.equals("/argb")) {
                    if (!s.startsWith("str=")) {
                        if (s.equals("str")) {
                            Class98_Sub10_Sub5_Sub1.anInt6292 = ((0xFF000000 & Class223.anInt1676) | 0x800000);
                        }
                        else if (s.equals("/str")) {
                            Class98_Sub10_Sub5_Sub1.anInt6292 = -1;
                        }
                        else if (s.startsWith("u=")) {
                            Class91.anInt724 = ((Class223.anInt1676 & 0xFF000000) | Class98_Sub43_Sub4.method1508(n + 2, 16, s.substring(2)));
                        }
                        else if (!s.equals("u")) {
                            if (!s.equals("/u")) {
                                if (!s.equalsIgnoreCase("shad=-1")) {
                                    if (s.startsWith("shad=")) {
                                        Class64_Sub17.anInt3684 = ((Class223.anInt1676 & 0xFF000000) | Class98_Sub43_Sub4.method1508(1, 16, s.substring(5)));
                                    }
                                    else if (!s.equals("shad")) {
                                        if (!s.equals("/shad")) {
                                            if (s.equals("br")) {
                                                this.method399((byte)(-35), Class279.anInt2096, Class23.anInt221);
                                            }
                                        }
                                        else {
                                            Class64_Sub17.anInt3684 = Class279.anInt2096;
                                        }
                                    }
                                    else {
                                        Class64_Sub17.anInt3684 = (Class223.anInt1676 & 0xFF000000);
                                    }
                                }
                                else {
                                    Class64_Sub17.anInt3684 = 0;
                                }
                            }
                            else {
                                Class91.anInt724 = -1;
                            }
                        }
                        else {
                            Class91.anInt724 = (Class223.anInt1676 & 0xFF000000);
                        }
                    }
                    else {
                        Class98_Sub10_Sub5_Sub1.anInt6292 = ((Class223.anInt1676 & 0xFF000000) | Class98_Sub43_Sub4.method1508(n + 2, 16, s.substring(4)));
                    }
                }
                else {
                    Class223.anInt1676 = Class23.anInt221;
                }
            }
            catch (Exception ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.V(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method401(int anInt2729, final boolean b) {
        try {
            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub29_4050.method677((byte)120) == 0) {
                anInt2729 = -1;
            }
            if (~anInt2729 != ~Class325.anInt2729) {
                Label_0108: {
                    if (anInt2729 != -1) {
                        final Class231 method202 = Class18.aClass11_213.method202(anInt2729, 25930);
                        final Class324 method203 = method202.method2876((byte)126);
                        if (method203 == null) {
                            anInt2729 = -1;
                            if (!client.aBoolean3553) {
                                break Label_0108;
                            }
                        }
                        Class98_Sub43_Sub2.aClass88_5907.method872(method203.method3686(), new Point(method202.anInt1738, method202.anInt1736), method203.method3681(), (byte)125, method203.method3689(), Class42_Sub3.aCanvas5361);
                        Class325.anInt2729 = anInt2729;
                    }
                }
                if (b) {
                    if (~anInt2729 == 0x0 && Class325.anInt2729 != -1) {
                        Class98_Sub43_Sub2.aClass88_5907.method872(null, new Point(), -1, (byte)95, -1, Class42_Sub3.aCanvas5361);
                        Class325.anInt2729 = -1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.W(" + anInt2729 + ',' + b + ')');
        }
    }
    
    private final void method402(final int n, final String s, final byte b) {
        try {
            int n2 = 0;
            int n3 = 0;
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 != '<') {
                    if (~char1 == 0xFFFFFFC1) {
                        n3 = 0;
                    }
                    else if (n3 == 0 && char1 == ' ') {
                        ++n2;
                    }
                }
                else {
                    n3 = 1;
                }
            }
            if (b < 35) {
                this.aClass197_373 = null;
            }
            if (n2 > 0) {
                Class93_Sub1.anInt5486 = (-this.aClass197_373.method2674(s, 106) + n << 1130187208) / n2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.AA(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method403(final int n, final int n2, final int n3, final int n4, final int n5, final String s, final int n6) {
        try {
            if (n5 < 3) {
                this.aClass197_373 = null;
            }
            if (s != null) {
                this.method399((byte)(-81), n, n6);
                final int i = s.length();
                final int[] array = new int[i];
                for (int n7 = 0; i > n7; ++n7) {
                    array[n7] = (int)(Math.sin(n4 / 5.0 + n7 / 2.0) * 5.0);
                }
                this.method410(array, n2 - this.aClass197_373.method2674(s, 113) / 2, true, null, null, s, null, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((s != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    static final void method404(final int n, final int n2) {
        try {
            synchronized (Class211.aClass79_1594) {
                Class211.aClass79_1594.method800((byte)62, n);
            }
            if (n2 != 2974) {
                method398(-17);
            }
            synchronized (PlayerUpdate.aClass79_3411) {
                PlayerUpdate.aClass79_3411.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.BA(" + n + ',' + n2 + ')');
        }
    }
    
    final int method405(final int n, int n2, final int n3, final aa aa, final int n4, final int n5, final int n6, int anInt1518, int n7, final int n8, final byte b, final Class332[] array, final int n9, final int n10, final String s, final int n11, final int[] array2) {
        try {
            if (s == null) {
                return 0;
            }
            this.method399((byte)108, n6, n9);
            if (~anInt1518 == -1) {
                anInt1518 = this.aClass197_373.anInt1518;
            }
            int[] array3;
            if (~(this.aClass197_373.anInt1517 - -this.aClass197_373.anInt1514 + anInt1518) >= ~n3 || anInt1518 - -anInt1518 <= n3) {
                array3 = new int[] { n5 };
            }
            else {
                array3 = null;
            }
            int i = this.aClass197_373.method2675(s, Class98_Sub43_Sub4.aStringArray5932, array3, array, -1);
            if (n2 == -1) {
                n2 = n3 / anInt1518;
                if (~n2 >= -1) {
                    n2 = 1;
                }
            }
            if (~n2 < -1 && i >= n2) {
                i = n2;
                Class98_Sub43_Sub4.aStringArray5932[-1 + n2] = this.aClass197_373.method2677(Class98_Sub43_Sub4.aStringArray5932[-1 + n2], false, array, n5);
            }
            if (~n7 == 0xFFFFFFFC && ~i == 0xFFFFFFFE) {
                n7 = 1;
            }
            int n12;
            if (n7 != 0) {
                if (~n7 == 0xFFFFFFFE) {
                    n12 = this.aClass197_373.anInt1517 + (n8 - -((-((i - 1) * anInt1518) + (-this.aClass197_373.anInt1514 + (n3 + -this.aClass197_373.anInt1517))) / 2));
                }
                else if (~n7 != 0xFFFFFFFD) {
                    int n13 = (-this.aClass197_373.anInt1514 + -this.aClass197_373.anInt1517 + (n3 - anInt1518 * (-1 + i))) / (1 + i);
                    if (~n13 > -1) {
                        n13 = 0;
                    }
                    n12 = n13 + this.aClass197_373.anInt1517 + n8;
                    anInt1518 += n13;
                }
                else {
                    n12 = -(anInt1518 * (-1 + i)) + n3 + (n8 + -this.aClass197_373.anInt1514);
                }
            }
            else {
                n12 = n8 + this.aClass197_373.anInt1517;
            }
            for (int n14 = 0; i > n14; ++n14) {
                if (~n11 == -1) {
                    this.method396(n10, n4, n12, n, aa, -1, Class98_Sub43_Sub4.aStringArray5932[n14], array, array2);
                }
                else if (n11 != 1) {
                    if (n11 == 2) {
                        this.method396(n10, n4, n12, n + (n5 - this.aClass197_373.method2674(Class98_Sub43_Sub4.aStringArray5932[n14], 123)), aa, -1, Class98_Sub43_Sub4.aStringArray5932[n14], array, array2);
                    }
                    else if (~(i - 1) != ~n14) {
                        this.method402(n5, Class98_Sub43_Sub4.aStringArray5932[n14], (byte)74);
                        this.method396(n10, n4, n12, n, aa, -1, Class98_Sub43_Sub4.aStringArray5932[n14], array, array2);
                        Class93_Sub1.anInt5486 = 0;
                    }
                    else {
                        this.method396(n10, n4, n12, n, aa, -1, Class98_Sub43_Sub4.aStringArray5932[n14], array, array2);
                    }
                }
                else {
                    this.method396(n10, n4, n12, (-this.aClass197_373.method2674(Class98_Sub43_Sub4.aStringArray5932[n14], 112) + n5) / 2 + n, aa, -1, Class98_Sub43_Sub4.aStringArray5932[n14], array, array2);
                }
                n12 += anInt1518;
            }
            return i;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.R(" + n + ',' + n2 + ',' + n3 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + anInt1518 + ',' + n7 + ',' + n8 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n9 + ',' + n10 + ',' + ((s != null) ? "{...}" : "null") + ',' + n11 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method406(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final String s) {
        try {
            if (s != null) {
                this.method399((byte)123, n2, n4);
                double n8 = -(n5 / 8.0) + 7.0;
                if (n8 < 0.0) {
                    n8 = 0.0;
                }
                final int length = s.length();
                final int[] array = new int[length];
                for (int n9 = n; ~n9 > ~length; ++n9) {
                    array[n9] = (int)(n8 * Math.sin(n6 + n9 / 1.5));
                }
                this.method410(array, -(this.aClass197_373.method2674(s, 118) / 2) + n7, true, null, null, s, null, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void fa(final char p0, final int p1, final int p2, final int p3, final boolean p4);
    
    final int method407(final int n, final int n2, final int n3, final String s, final Random random, final int[] array, final int n4, final int n5, final Class332[] array2, final int n6) {
        try {
            if (n6 >= -48) {
                this.method410(null, -54, true, null, null, null, null, -121);
            }
            if (s == null) {
                return 0;
            }
            random.setSeed(n4);
            final int n7 = (0x1F & random.nextInt()) + 192;
            this.method399((byte)(-94), (n & 0xFFFFFF) | n7 << 2106889272, n7 << 484897464 | (0xFFFFFF & n2));
            final int length = s.length();
            final int[] array3 = new int[length];
            int n8 = 0;
            for (int n9 = 0; ~n9 > ~length; ++n9) {
                array3[n9] = n8;
                if ((random.nextInt() & 0x3) == 0x0) {
                    ++n8;
                }
            }
            this.method410(null, n5, true, array3, array2, s, array, n3);
            return n8;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.H(" + n + ',' + n2 + ',' + n3 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((random != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    final int method408(final int n, final Class332[] array, final int n2, final String s, final int n3, final int n4, final aa aa, final int n5, final byte b, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final int n11) {
        try {
            if (b > -48) {
                this.fa('b', -40, -26, 60, false);
            }
            return this.method405(n, 0, n11, aa, n8, n2, n4, n7, n9, n10, (byte)(-74), array, n6, n3, s, n5, array2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.Q(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n5 + ',' + b + ',' + n6 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ')');
        }
    }
    
    abstract void method409(final char p0, final int p1, final int p2, final int p3, final boolean p4, final aa p5, final int p6, final int p7);
    
    private final void method410(final int[] array, int n, final boolean b, final int[] array2, final Class332[] array3, final String s, final int[] array4, int n2) {
        try {
            n2 -= this.aClass197_373.anInt1518;
            int n3 = -1;
            int n4 = -1;
            int n5 = 0;
            if (!b) {
                this.method403(39, -23, 82, 55, -128, null, 88);
            }
            for (int length = s.length(), n6 = 0; ~n6 > ~length; ++n6) {
                char c = (char)(0xFF & Class349.method3843((byte)88, s.charAt(n6)));
                if (~c == 0xFFFFFFC3) {
                    n3 = n6;
                }
                else {
                    if (c == '>' && ~n3 != 0x0) {
                        final String substring = s.substring(1 + n3, n6);
                        n3 = -1;
                        if (!substring.equals("lt")) {
                            if (!substring.equals("gt")) {
                                if (substring.equals("nbsp")) {
                                    c = ' ';
                                }
                                else if (substring.equals("shy")) {
                                    c = '\u00ad';
                                }
                                else if (!substring.equals("times")) {
                                    if (substring.equals("euro")) {
                                        c = '\u20ac';
                                    }
                                    else if (substring.equals("copy")) {
                                        c = '©';
                                    }
                                    else if (substring.equals("reg")) {
                                        c = '®';
                                    }
                                    else {
                                        if (!substring.startsWith("img=")) {
                                            this.method400(substring, -1);
                                            continue;
                                        }
                                        try {
                                            int n7;
                                            if (array2 == null) {
                                                n7 = 0;
                                            }
                                            else {
                                                n7 = array2[n5];
                                            }
                                            int n8;
                                            if (array != null) {
                                                n8 = array[n5];
                                            }
                                            else {
                                                n8 = 0;
                                            }
                                            ++n5;
                                            final int method3607 = PacketSender.method3607(-62, substring.substring(4));
                                            final Class332 class332 = array3[method3607];
                                            class332.method3748(n - -n7, n2 + (this.aClass197_373.anInt1518 - ((array4 != null) ? array4[method3607] : class332.method3749()) - -n8), 1, 0, 1);
                                            n += array3[method3607].method3737();
                                            n4 = -1;
                                        }
                                        catch (Exception ex2) {}
                                        continue;
                                    }
                                }
                                else {
                                    c = '\u00d7';
                                }
                            }
                            else {
                                c = '>';
                            }
                        }
                        else {
                            c = '<';
                        }
                    }
                    if (~n3 == 0x0) {
                        if (~n4 != 0x0) {
                            n += this.aClass197_373.method2671((byte)(-50), c, n4);
                        }
                        int n9;
                        if (array2 != null) {
                            n9 = array2[n5];
                        }
                        else {
                            n9 = 0;
                        }
                        int n10;
                        if (array != null) {
                            n10 = array[n5];
                        }
                        else {
                            n10 = 0;
                        }
                        ++n5;
                        if (c != ' ') {
                            if ((Class64_Sub17.anInt3684 & 0xFF000000) != 0x0) {
                                this.fa(c, 1 + n - -n9, n10 + n2 + 1, Class64_Sub17.anInt3684, true);
                            }
                            this.fa(c, n9 + n, n10 + n2, Class223.anInt1676, false);
                        }
                        else if (Class93_Sub1.anInt5486 > 0) {
                            Class98_Sub47.anInt4276 += Class93_Sub1.anInt5486;
                            n += Class98_Sub47.anInt4276 >> 146551848;
                            Class98_Sub47.anInt4276 &= 0xFF;
                        }
                        final int method3608 = this.aClass197_373.method2673((byte)(-127), c);
                        if (Class98_Sub10_Sub5_Sub1.anInt6292 != -1) {
                            this.aHa374.method1753(22294, method3608, Class98_Sub10_Sub5_Sub1.anInt6292, (int)(0.7 * this.aClass197_373.anInt1518) + n2, n);
                        }
                        if (~Class91.anInt724 != 0x0) {
                            this.aHa374.method1753(22294, method3608, Class91.anInt724, n2 + this.aClass197_373.anInt1518, n);
                        }
                        n4 = c;
                        n += method3608;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.J(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final void method411(final byte b, final int n, final String s, final int n2, final int n3, final int n4) {
        try {
            if (s != null) {
                this.method399((byte)93, n3, n2);
                this.method396(0, 0, n, n4, null, -1, s, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.M(" + b + ',' + n + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method412(final int n, final int n2, final int n3, final int n4, final int n5, final String s, final int n6) {
        try {
            if (s != null) {
                this.method399((byte)121, n, n3);
                final int length = s.length();
                final int[] array = new int[length];
                final int[] array2 = new int[length];
                for (int n7 = 0; ~length < ~n7; ++n7) {
                    array[n7] = (int)(5.0 * Math.sin(n6 / 5.0 + n7 / 5.0));
                    array2[n7] = (int)(Math.sin(n6 / 5.0 + n7 / 3.0) * 5.0);
                }
                this.method410(array2, n4 - this.aClass197_373.method2674(s, 127) / 2, true, array, null, s, null, n5);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.U(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((s != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    final void method413(final int n, final int[] array, final int n2, final String s, final int n3, final int n4, final byte b, final Class332[] array2) {
        try {
            if (b == 18 && s != null) {
                this.method399((byte)91, n2, n3);
                this.method396(0, 0, n, n4, null, -1, s, array2, array);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.K(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method414(final int n) {
        try {
            Class43.aCharArray376 = null;
            Class43.aClass196_375 = null;
            if (n != -2) {
                Class43.aCharArray376 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.CA(" + n + ')');
        }
    }
    
    final void method415(final int n, final String s, final int n2, final int n3, final byte b, final int n4) {
        try {
            if (s != null) {
                this.method399((byte)94, n3, n);
                this.method396(0, 0, n4, n2 - this.aClass197_373.method2674(s, 127) / 2, null, -1, s, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.L(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ')');
        }
    }
    
    Class43(final ha aHa374, final Class197 aClass197_373) {
        try {
            this.aClass197_373 = aClass197_373;
            this.aHa374 = aHa374;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.<init>(" + ((aHa374 != null) ? "{...}" : "null") + ',' + ((aClass197_373 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method416(final int n, final int n2, final int n3, final int n4, final int n5, final Random random, final int n6, final int[] array, final int n7, final String s, final int n8, final int[] array2, final int n9, final Class332[] array3, final int n10) {
        try {
            if (s == null) {
                return 0;
            }
            random.setSeed(n3);
            final int n11 = (random.nextInt() & 0x1F) + 192;
            this.method399((byte)(-77), (n4 & 0xFFFFFF) | n11 << 1901748184, n11 << 1707350264 | (n2 & 0xFFFFFF));
            final int length = s.length();
            final int[] array4 = new int[length];
            int n12 = 0;
            for (int n13 = 0; ~n13 > ~length; ++n13) {
                array4[n13] = n12;
                if (~(0x3 & random.nextInt()) == -1) {
                    ++n12;
                }
            }
            int n14 = n;
            int n15 = this.aClass197_373.anInt1517 + n10;
            int n16 = -1;
            if (n8 != 1) {
                if (n8 == 2) {
                    n15 = n10 + n5 - this.aClass197_373.anInt1514;
                }
            }
            else {
                n15 += (-this.aClass197_373.anInt1514 + (n5 + -this.aClass197_373.anInt1517)) / 2;
            }
            if (~n6 == 0xFFFFFFFE) {
                n16 = n12 + this.aClass197_373.method2674(s, 118);
                n14 += (-n16 + n7) / 2;
            }
            else if (n6 == 2) {
                n16 = this.aClass197_373.method2674(s, 100) - -n12;
                n14 += n7 + -n16;
            }
            this.method410(null, n14, true, array4, array3, s, array2, n15);
            if (array != null) {
                if (~n16 == 0x0) {
                    n16 = n12 + this.aClass197_373.method2674(s, 104);
                }
                array[1] = -this.aClass197_373.anInt1517 + n15;
                array[0] = n14;
                array[3] = this.aClass197_373.anInt1514 + this.aClass197_373.anInt1517;
                array[2] = n16;
            }
            return n12;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "da.O(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((random != null) ? "{...}" : "null") + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + n7 + ',' + ((s != null) ? "{...}" : "null") + ',' + n8 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n9 + ',' + ((array3 != null) ? "{...}" : "null") + ',' + n10 + ')');
        }
    }
    
    static {
        Class43.anInt372 = 0;
        Class43.aClass196_375 = new Class196("LIVE", "", "", 0);
        Class43.aCharArray376 = new char[] { ' ', ' ', '_', '-', '\u00e0', '\u00e1', '\u00e2', '\u00e4', '\u00e3', '\u00c0', '\u00c1', '\u00c2', '\u00c4', '\u00c3', '\u00e8', '\u00e9', '\u00ea', '\u00eb', '\u00c8', '\u00c9', '\u00ca', '\u00cb', '\u00ed', '\u00ee', '\u00ef', '\u00cd', '\u00ce', '\u00cf', '\u00f2', '\u00f3', '\u00f4', '\u00f6', '\u00f5', '\u00d2', '\u00d3', '\u00d4', '\u00d6', '\u00d5', '\u00f9', '\u00fa', '\u00fb', '\u00fc', '\u00d9', '\u00da', '\u00db', '\u00dc', '\u00e7', '\u00c7', '\u00ff', '\u0178', '\u00f1', '\u00d1', '\u00df' };
    }
}
