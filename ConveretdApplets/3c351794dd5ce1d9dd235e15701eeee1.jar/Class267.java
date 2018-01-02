// 
// Decompiled by Procyon v0.5.30
// 

final class Class267
{
    private String[] aStringArray1996;
    private int anInt1997;
    static long aLong1998;
    private int anInt1999;
    static OutgoingOpcode aClass171_2000;
    private boolean aBoolean2001;
    static Class218 aClass218_2002;
    
    public static void method3240(final int n) {
        try {
            Class267.aClass171_2000 = null;
            Class267.aClass218_2002 = null;
            if (n != 1) {
                Class267.aClass218_2002 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.H(" + n + ')');
        }
    }
    
    private final int method3241(final int n, final byte b) {
        try {
            int length = this.aStringArray1996.length;
            while (~length >= ~n) {
                if (this.aBoolean2001) {
                    if (length == 0) {
                        length = 1;
                    }
                    else {
                        length *= this.anInt1999;
                    }
                }
                else {
                    length += this.anInt1999;
                }
            }
            return length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            final StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int n = 0; this.anInt1997 > n; ++n) {
                if (~n != -1) {
                    sb.append(", ");
                }
                sb.append(this.aStringArray1996[n]);
            }
            sb.append("]");
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.toString()");
        }
    }
    
    private final void method3242(final int n, final int n2) {
        try {
            if (n <= 43) {
                this.aBoolean2001 = true;
            }
            final String[] aStringArray1996 = new String[this.method3241(n2, (byte)89)];
            Class236.method2892(this.aStringArray1996, 0, aStringArray1996, 0, this.aStringArray1996.length);
            this.aStringArray1996 = aStringArray1996;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method3243(int n, int n2, final byte b, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        try {
            if (n5 > 2000 || n7 > 2000 || ~n4 < -2001 || ~n2 < -2001 || ~n < -2001 || ~n6 < -2001) {
                return false;
            }
            if (~n5 > 1999 || n7 < -2000 || ~n4 > 1999 || n2 < -2000 || ~n > 1999 || n6 < -2000) {
                return false;
            }
            if (Class287.anInt2190 == 2) {
                final int n10 = n5 * Class64_Sub3.anInt3646 + n2;
                if (n10 >= 0 && ~Class111_Sub3.anIntArray4707.length < ~n10 && ~((n8 << 472076648) - 38400) > ~Class111_Sub3.anIntArray4707[n10]) {
                    return false;
                }
                final int n11 = n - -(n7 * Class64_Sub3.anInt3646);
                if (~n11 <= -1 && ~Class111_Sub3.anIntArray4707.length < ~n11 && Class111_Sub3.anIntArray4707[n11] > (n3 << -1175483992) - 38400) {
                    return false;
                }
                final int n12 = n6 + n4 * Class64_Sub3.anInt3646;
                if (n12 >= 0 && n12 < Class111_Sub3.anIntArray4707.length && (n9 << -1032645656) - 38400 < Class111_Sub3.anIntArray4707[n12]) {
                    return false;
                }
            }
            final int n13 = n - n2;
            final int n14 = -n5 + n7;
            if (b <= 70) {
                return false;
            }
            final int n15 = n6 + -n2;
            final int n16 = n4 + -n5;
            final int n17 = n3 + -n8;
            final int n18 = -n8 + n9;
            if (n7 <= n5 || n4 <= n4) {
                if (~n4 < ~n7) {
                    if (~n4 <= ~n5) {
                        ++n4;
                    }
                    else {
                        ++n5;
                    }
                    --n7;
                }
                else {
                    if (n7 >= n5) {
                        ++n7;
                    }
                    else {
                        ++n5;
                    }
                    --n4;
                }
            }
            else {
                --n5;
                if (~n4 <= ~n7) {
                    ++n4;
                }
                else {
                    ++n7;
                }
            }
            int n19 = 0;
            if (~n7 != ~n5) {
                n19 = (-n2 + n << 548003020) / (n7 - n5);
            }
            int n20 = 0;
            if (n4 != n7) {
                n20 = (n6 - n << 1451580940) / (-n7 + n4);
            }
            int n21 = 0;
            if (n5 != n4) {
                n21 = (-n6 + n2 << -1711197332) / (n5 + -n4);
            }
            final int n22 = -(n15 * n14) + n16 * n13;
            if (~n22 == -1) {
                return false;
            }
            final int n23 = (n17 * n16 + -(n14 * n18) << 1507461352) / n22;
            final int n24 = (n13 * n18 + -(n15 * n17) << 348417896) / n22;
            if (n7 >= n5 && ~n4 <= ~n5) {
                if (~n5 <= ~IncomingOpcode.anInt461) {
                    return true;
                }
                if (~IncomingOpcode.anInt461 > ~n4) {
                    n4 = IncomingOpcode.anInt461;
                }
                n8 = n23 + ((n8 << 2088717192) - n2 * n23);
                if (~n7 < ~IncomingOpcode.anInt461) {
                    n7 = IncomingOpcode.anInt461;
                }
                if (n4 <= n7) {
                    n2 = (n = n2 << 1146700236);
                    if (~n5 > -1) {
                        n2 -= n5 * n19;
                        n8 -= n24 * n5;
                        n -= n5 * n21;
                        n5 = 0;
                    }
                    n6 <<= 1975846892;
                    if (~n4 > -1) {
                        n6 -= n20 * n4;
                        n4 = 0;
                    }
                    if ((n5 != n4 && ~n19 < ~n21) || (~n4 == ~n5 && n20 > n19)) {
                        n7 -= n4;
                        n4 -= n5;
                        n5 *= Class64_Sub3.anInt3646;
                        while (~(--n4) <= -1) {
                            if (!Class288.method3400((n >> -1543389460) - 1, n23, n5, 0, n8, (byte)(-119), (n2 >> -714372852) + 1, Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n8 += n24;
                            n2 += n19;
                            n += n21;
                            n5 += Class64_Sub3.anInt3646;
                        }
                        while (~(--n7) <= -1) {
                            if (!Class288.method3400((n6 >> -498853684) - 1, n23, n5, 0, n8, (byte)(-108), (n2 >> 1011687692) + 1, Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n6 += n20;
                            n8 += n24;
                            n2 += n19;
                            n5 += Class64_Sub3.anInt3646;
                        }
                        return true;
                    }
                    n7 -= n4;
                    n4 -= n5;
                    n5 *= Class64_Sub3.anInt3646;
                    while (--n4 >= 0) {
                        if (!Class288.method3400(-1 + (n2 >> -1969464276), n23, n5, 0, n8, (byte)(-30), (n >> -284701716) + 1, Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n2 += n19;
                        n += n21;
                        n8 += n24;
                        n5 += Class64_Sub3.anInt3646;
                    }
                    while (~(--n7) <= -1) {
                        if (!Class288.method3400((n2 >> 1277517804) - 1, n23, n5, 0, n8, (byte)(-38), (n6 >> -1240748564) + 1, Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n2 += n19;
                        n8 += n24;
                        n6 += n20;
                        n5 += Class64_Sub3.anInt3646;
                    }
                    return true;
                }
                else {
                    n2 = (n6 = n2 << -2042674164);
                    n <<= 589237868;
                    if (n5 < 0) {
                        n2 -= n19 * n5;
                        n6 -= n21 * n5;
                        n8 -= n24 * n5;
                        n5 = 0;
                    }
                    if (n7 < 0) {
                        n -= n20 * n7;
                        n7 = 0;
                    }
                    if ((~n5 != ~n7 && n19 > n21) || (n5 == n7 && ~n21 < ~n20)) {
                        n4 -= n7;
                        n7 -= n5;
                        n5 *= Class64_Sub3.anInt3646;
                        while (--n7 >= 0) {
                            if (!Class288.method3400(-1 + (n6 >> 546227020), n23, n5, 0, n8, (byte)(-104), 1 + (n2 >> -131891316), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n2 += n19;
                            n6 += n21;
                            n5 += Class64_Sub3.anInt3646;
                            n8 += n24;
                        }
                        while (--n4 >= 0) {
                            if (!Class288.method3400(-1 + (n6 >> -700493236), n23, n5, 0, n8, (byte)(-59), 1 + (n >> 1750357452), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n += n20;
                            n5 += Class64_Sub3.anInt3646;
                            n8 += n24;
                            n6 += n21;
                        }
                        return true;
                    }
                    n4 -= n7;
                    n7 -= n5;
                    n5 *= Class64_Sub3.anInt3646;
                    while (--n7 >= 0) {
                        if (!Class288.method3400(-1 + (n2 >> 720570700), n23, n5, 0, n8, (byte)(-70), 1 + (n6 >> -129521844), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n8 += n24;
                        n5 += Class64_Sub3.anInt3646;
                        n2 += n19;
                        n6 += n21;
                    }
                    while (~(--n4) <= -1) {
                        if (!Class288.method3400(-1 + (n >> -987217204), n23, n5, 0, n8, (byte)(-32), 1 + (n6 >> -1496178132), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n6 += n21;
                        n8 += n24;
                        n += n20;
                        n5 += Class64_Sub3.anInt3646;
                    }
                    return true;
                }
            }
            else if (n7 > n4) {
                if (IncomingOpcode.anInt461 <= n4) {
                    return true;
                }
                if (~n7 < ~IncomingOpcode.anInt461) {
                    n7 = IncomingOpcode.anInt461;
                }
                n9 = (n9 << -406675768) - n6 * n23 + n23;
                if (n5 > IncomingOpcode.anInt461) {
                    n5 = IncomingOpcode.anInt461;
                }
                if (n5 >= n7) {
                    n6 = (n2 = n6 << -1618820564);
                    if (n4 < 0) {
                        n2 -= n4 * n20;
                        n6 -= n4 * n21;
                        n9 -= n24 * n4;
                        n4 = 0;
                    }
                    n <<= 1191847468;
                    if (n7 < 0) {
                        n -= n7 * n19;
                        n7 = 0;
                    }
                    if (n21 <= n20) {
                        n5 -= n7;
                        n7 -= n4;
                        n4 *= Class64_Sub3.anInt3646;
                        while (~(--n7) <= -1) {
                            if (!Class288.method3400(-1 + (n6 >> -1329498292), n23, n4, 0, n9, (byte)(-62), 1 + (n2 >> 930847852), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n4 += Class64_Sub3.anInt3646;
                            n9 += n24;
                            n2 += n20;
                            n6 += n21;
                        }
                        while (~(--n5) <= -1) {
                            if (!Class288.method3400(-1 + (n6 >> -1818541364), n23, n4, 0, n9, (byte)(-104), (n >> 1716288236) + 1, Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n += n19;
                            n6 += n21;
                            n9 += n24;
                            n4 += Class64_Sub3.anInt3646;
                        }
                        return true;
                    }
                    n5 -= n7;
                    n7 -= n4;
                    n4 *= Class64_Sub3.anInt3646;
                    while (~(--n7) <= -1) {
                        if (!Class288.method3400(-1 + (n2 >> 537647756), n23, n4, 0, n9, (byte)(-24), 1 + (n6 >> 824402924), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n9 += n24;
                        n6 += n21;
                        n2 += n20;
                        n4 += Class64_Sub3.anInt3646;
                    }
                    while (~(--n5) <= -1) {
                        if (!Class288.method3400(-1 + (n >> -785194772), n23, n4, 0, n9, (byte)(-31), 1 + (n6 >> -1363296628), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n9 += n24;
                        n4 += Class64_Sub3.anInt3646;
                        n6 += n21;
                        n += n19;
                    }
                    return true;
                }
                else {
                    n6 = (n = n6 << 771320364);
                    if (n4 < 0) {
                        n -= n20 * n4;
                        n9 -= n24 * n4;
                        n6 -= n4 * n21;
                        n4 = 0;
                    }
                    n2 <<= -162226260;
                    if (n5 < 0) {
                        n2 -= n5 * n19;
                        n5 = 0;
                    }
                    if (~n21 >= ~n20) {
                        n7 -= n5;
                        n5 -= n4;
                        n4 *= Class64_Sub3.anInt3646;
                        while (--n5 >= 0) {
                            if (!Class288.method3400(-1 + (n6 >> -949447092), n23, n4, 0, n9, (byte)(-57), 1 + (n >> -492591892), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n6 += n21;
                            n9 += n24;
                            n4 += Class64_Sub3.anInt3646;
                            n += n20;
                        }
                        while (--n7 >= 0) {
                            if (!Class288.method3400(-1 + (n2 >> -374340404), n23, n4, 0, n9, (byte)(-76), 1 + (n >> -1787317524), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n2 += n19;
                            n4 += Class64_Sub3.anInt3646;
                            n9 += n24;
                            n += n20;
                        }
                        return true;
                    }
                    n7 -= n5;
                    n5 -= n4;
                    n4 *= Class64_Sub3.anInt3646;
                    while (--n5 >= 0) {
                        if (!Class288.method3400(-1 + (n >> -137930964), n23, n4, 0, n9, (byte)(-85), 1 + (n6 >> 1950546220), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n9 += n24;
                        n += n20;
                        n6 += n21;
                        n4 += Class64_Sub3.anInt3646;
                    }
                    while (--n7 >= 0) {
                        if (!Class288.method3400((n >> 1547416236) - 1, n23, n4, 0, n9, (byte)(-21), (n2 >> 1811014156) + 1, Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n += n20;
                        n9 += n24;
                        n4 += Class64_Sub3.anInt3646;
                        n2 += n19;
                    }
                    return true;
                }
            }
            else {
                if (n7 >= IncomingOpcode.anInt461) {
                    return true;
                }
                if (~n5 < ~IncomingOpcode.anInt461) {
                    n5 = IncomingOpcode.anInt461;
                }
                n3 = -(n * n23) + ((n3 << -638515768) + n23);
                if (IncomingOpcode.anInt461 < n4) {
                    n4 = IncomingOpcode.anInt461;
                }
                if (n4 < n5) {
                    n = (n2 = n << -975458356);
                    if (n7 < 0) {
                        n2 -= n19 * n7;
                        n3 -= n7 * n24;
                        n -= n20 * n7;
                        n7 = 0;
                    }
                    n6 <<= -704552628;
                    if (~n4 > -1) {
                        n6 -= n21 * n4;
                        n4 = 0;
                    }
                    if ((n4 == n7 || n19 >= n20) && (~n4 != ~n7 || n19 <= n21)) {
                        n5 -= n4;
                        n4 -= n7;
                        n7 *= Class64_Sub3.anInt3646;
                        while (--n4 >= 0) {
                            if (!Class288.method3400(-1 + (n >> -1194506228), n23, n7, 0, n3, (byte)(-87), (n2 >> 762305324) + 1, Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n7 += Class64_Sub3.anInt3646;
                            n3 += n24;
                            n2 += n19;
                            n += n20;
                        }
                        while (--n5 >= 0) {
                            if (!Class288.method3400((n6 >> 1373008300) - 1, n23, n7, 0, n3, (byte)(-74), (n2 >> -2056319956) + 1, Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n3 += n24;
                            n6 += n21;
                            n2 += n19;
                            n7 += Class64_Sub3.anInt3646;
                        }
                        return true;
                    }
                    n5 -= n4;
                    n4 -= n7;
                    n7 *= Class64_Sub3.anInt3646;
                    while (--n4 >= 0) {
                        if (!Class288.method3400((n2 >> 1724612172) - 1, n23, n7, 0, n3, (byte)(-113), (n >> 48025484) + 1, Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n7 += Class64_Sub3.anInt3646;
                        n += n20;
                        n3 += n24;
                        n2 += n19;
                    }
                    while (--n5 >= 0) {
                        if (!Class288.method3400(-1 + (n2 >> -1580865140), n23, n7, 0, n3, (byte)(-123), 1 + (n6 >> -1528143444), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n7 += Class64_Sub3.anInt3646;
                        n3 += n24;
                        n6 += n21;
                        n2 += n19;
                    }
                    return true;
                }
                else {
                    n = (n6 = n << 1287486668);
                    if (~n7 > -1) {
                        n -= n20 * n7;
                        n6 -= n19 * n7;
                        n3 -= n24 * n7;
                        n7 = 0;
                    }
                    n2 <<= 1244265292;
                    if (~n5 > -1) {
                        n2 -= n21 * n5;
                        n5 = 0;
                    }
                    if (~n20 >= ~n19) {
                        n4 -= n5;
                        n5 -= n7;
                        n7 *= Class64_Sub3.anInt3646;
                        while (~(--n5) <= -1) {
                            if (!Class288.method3400(-1 + (n >> -692850484), n23, n7, 0, n3, (byte)(-126), 1 + (n6 >> -2131253140), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n += n20;
                            n6 += n19;
                            n7 += Class64_Sub3.anInt3646;
                            n3 += n24;
                        }
                        while (--n4 >= 0) {
                            if (!Class288.method3400((n >> 150030796) - 1, n23, n7, 0, n3, (byte)(-96), 1 + (n2 >> -1233991668), Class111_Sub3.anIntArray4707)) {
                                return false;
                            }
                            n += n20;
                            n7 += Class64_Sub3.anInt3646;
                            n3 += n24;
                            n2 += n21;
                        }
                        return true;
                    }
                    n4 -= n5;
                    n5 -= n7;
                    n7 *= Class64_Sub3.anInt3646;
                    while (--n5 >= 0) {
                        if (!Class288.method3400((n6 >> 1393108428) - 1, n23, n7, 0, n3, (byte)(-87), 1 + (n >> 798966764), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n6 += n19;
                        n3 += n24;
                        n7 += Class64_Sub3.anInt3646;
                        n += n20;
                    }
                    while (--n4 >= 0) {
                        if (!Class288.method3400(-1 + (n2 >> 1333849452), n23, n7, 0, n3, (byte)(-95), 1 + (n >> -31598580), Class111_Sub3.anIntArray4707)) {
                            return false;
                        }
                        n2 += n21;
                        n3 += n24;
                        n += n20;
                        n7 += Class64_Sub3.anInt3646;
                    }
                    return true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.A(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    final String[] method3244(final int n) {
        try {
            final String[] array = new String[1 + this.anInt1997];
            Class236.method2892(this.aStringArray1996, 0, array, 0, this.anInt1997 + 1);
            if (n != 20780) {
                this.method3242(11, 74);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.C(" + n + ')');
        }
    }
    
    final void method3245(final String s, final int n) {
        try {
            this.method3246(s, 1 + this.anInt1997, n ^ 0x1);
            if (n != 0) {
                this.method3246(null, -34, -116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.E(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3246(final String s, final int anInt1997, final int n) {
        try {
            if (anInt1997 > this.anInt1997) {
                this.anInt1997 = anInt1997;
            }
            if (n == 1) {
                if (~anInt1997 <= ~this.aStringArray1996.length) {
                    this.method3242(104, anInt1997);
                }
                this.aStringArray1996[anInt1997] = s;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.G(" + ((s != null) ? "{...}" : "null") + ',' + anInt1997 + ',' + n + ')');
        }
    }
    
    static final void method3247(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            Label_0090: {
                if (~Class76_Sub8.anInt3778 >= ~(-n5 + n3) && Class3.anInt77 >= n3 + n5 && ~(n + -n5) <= ~Class98_Sub10_Sub38.anInt5753 && ~Class218.anInt1635 <= ~(n - -n5)) {
                    Class10.method196(-119, n2, n5, n3, n4, n6, n);
                    if (!client.aBoolean3553) {
                        break Label_0090;
                    }
                }
                Class29.method303(n5, n4, n2, (byte)93, n, n3, n6);
            }
            if (n7 != 1333849452) {
                method3240(-70);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    Class267(final int anInt1999, final boolean aBoolean2001) {
        this.aStringArray1996 = new String[0];
        this.anInt1997 = -1;
        this.aBoolean2001 = false;
        try {
            this.anInt1999 = anInt1999;
            this.aBoolean2001 = aBoolean2001;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qm.<init>(" + anInt1999 + ',' + aBoolean2001 + ')');
        }
    }
    
    static {
        Class267.aClass171_2000 = new OutgoingOpcode(83, 12);
    }
}
