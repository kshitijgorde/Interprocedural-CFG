// 
// Decompiled by Procyon v0.5.30
// 

final class Class207
{
    static Class348 aClass348_1569;
    private boolean aBoolean1570;
    private Class312 aClass312_1571;
    private Object[] anObjectArray1572;
    private Object[][] anObjectArrayArray1573;
    private Class339 aClass339_1574;
    int anInt1575;
    static IncomingOpcode aClass58_1576;
    static int anInt1577;
    static Class196 aClass196_1578;
    
    final boolean method2728(String lowerCase, final int n) {
        try {
            if (!this.method2738((byte)(-123))) {
                return false;
            }
            lowerCase = lowerCase.toLowerCase();
            return n <= this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.S(" + ((lowerCase != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final boolean method2729(final int[] array, final int n, final byte b, final int n2) {
        try {
            if (!this.method2758(n2, false)) {
                return false;
            }
            if (this.anObjectArray1572[n2] == null) {
                return false;
            }
            final int n3 = this.aClass312_1571.anIntArray2670[n2];
            final int[] array2 = this.aClass312_1571.anIntArrayArray2669[n2];
            if (this.anObjectArrayArray1573[n2] == null) {
                this.anObjectArrayArray1573[n2] = new Object[this.aClass312_1571.anIntArray2671[n2]];
            }
            final Object[] array3 = this.anObjectArrayArray1573[n2];
            boolean b2 = true;
            if (b >= -62) {
                this.method2728(null, 43);
            }
            for (int n4 = 0; ~n4 > ~n3; ++n4) {
                int n5;
                if (array2 == null) {
                    n5 = n4;
                }
                else {
                    n5 = array2[n4];
                }
                if (array3[n5] == null) {
                    b2 = false;
                    break;
                }
            }
            if (b2) {
                return true;
            }
            byte[] array4;
            if (array != null && (~array[0] != -1 || ~array[1] != -1 || ~array[2] != -1 || array[3] != 0)) {
                array4 = Class98_Sub28_Sub1.method1310(false, this.anObjectArray1572[n2], true);
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array4);
                class98_Sub22.method1215(array, 5, class98_Sub22.aByteArray3992.length, (byte)30);
            }
            else {
                array4 = Class98_Sub28_Sub1.method1310(false, this.anObjectArray1572[n2], false);
            }
            byte[] method1571;
            try {
                method1571 = Class98_Sub46_Sub10.method1571(array4, (byte)(-84));
            }
            catch (RuntimeException ex) {
                throw Class64_Sub27.method667(ex, "T3 - " + (array != null) + "," + n2 + "," + array4.length + "," + Class31.method309(array4.length, array4, -30091) + "," + Class31.method309(-2 + array4.length, array4, -30091) + "," + this.aClass312_1571.anIntArray2673[n2] + "," + this.aClass312_1571.anInt2677);
            }
            if (this.aBoolean1570) {
                this.anObjectArray1572[n2] = null;
            }
            if (~n3 >= -2) {
                int n6;
                if (array2 == null) {
                    n6 = 0;
                }
                else {
                    n6 = array2[0];
                }
                if (~this.anInt1575 != -1) {
                    array3[n6] = method1571;
                }
                else {
                    array3[n6] = Class64_Sub25.method654(2, method1571, false);
                }
            }
            else if (this.anInt1575 != 2) {
                int length = method1571.length;
                final int n7 = method1571[--length] & 0xFF;
                final int n8 = length - 4 * n7 * n3;
                final Class98_Sub22 class98_Sub23 = new Class98_Sub22(method1571);
                class98_Sub23.anInt3991 = n8;
                final int[] array5 = new int[n3];
                for (int n9 = 0; ~n9 > ~n7; ++n9) {
                    int n10 = 0;
                    for (int n11 = 0; ~n3 < ~n11; ++n11) {
                        n10 += class98_Sub23.readInt(-2);
                        final int[] array6 = array5;
                        final int n12 = n11;
                        array6[n12] += n10;
                    }
                }
                final byte[][] array7 = new byte[n3][];
                for (int i = 0; i < n3; ++i) {
                    array7[i] = new byte[array5[i]];
                    array5[i] = 0;
                }
                class98_Sub23.anInt3991 = n8;
                int n13 = 0;
                for (int j = 0; j < n7; ++j) {
                    int n14 = 0;
                    for (int k = 0; k < n3; ++k) {
                        n14 += class98_Sub23.readInt(-2);
                        Class236.method2894(method1571, n13, array7[k], array5[k], n14);
                        n13 += n14;
                        final int[] array8 = array5;
                        final int n15 = k;
                        array8[n15] += n14;
                    }
                }
                for (int n16 = 0; ~n16 > ~n3; ++n16) {
                    int n17;
                    if (array2 == null) {
                        n17 = n16;
                    }
                    else {
                        n17 = array2[n16];
                    }
                    if (~this.anInt1575 != -1) {
                        array3[n17] = array7[n16];
                    }
                    else {
                        array3[n17] = Class64_Sub25.method654(2, array7[n16], false);
                    }
                }
            }
            else {
                int length2 = method1571.length;
                final int l = method1571[--length2] & 0xFF;
                final int n18 = length2 - 4 * l * n3;
                final Class98_Sub22 class98_Sub24 = new Class98_Sub22(method1571);
                int n19 = 0;
                class98_Sub24.anInt3991 = n18;
                int n20 = 0;
                for (int n21 = 0; l > n21; ++n21) {
                    int n22 = 0;
                    for (int n23 = 0; n23 < n3; ++n23) {
                        n22 += class98_Sub24.readInt(-2);
                        int n24;
                        if (array2 != null) {
                            n24 = array2[n23];
                        }
                        else {
                            n24 = n23;
                        }
                        if (n24 == n) {
                            n19 += n22;
                            n20 = n24;
                        }
                    }
                }
                if (~n19 == -1) {
                    return true;
                }
                final byte[] array9 = new byte[n19];
                class98_Sub24.anInt3991 = n18;
                int n25 = 0;
                int n26 = 0;
                for (int n27 = 0; n27 < l; ++n27) {
                    int n28 = 0;
                    for (int n29 = 0; n29 < n3; ++n29) {
                        n28 += class98_Sub24.readInt(-2);
                        int n30;
                        if (array2 != null) {
                            n30 = array2[n29];
                        }
                        else {
                            n30 = n29;
                        }
                        if (n == n30) {
                            Class236.method2894(method1571, n26, array9, n25, n28);
                            n25 += n28;
                        }
                        n26 += n28;
                    }
                }
                array3[n20] = array9;
            }
            return true;
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "ni.F(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final boolean method2730(final byte b) {
        try {
            if (b <= 8) {
                Class207.anInt1577 = 40;
            }
            if (!this.method2738((byte)(-127))) {
                return false;
            }
            boolean b2 = true;
            for (int n = 0; this.aClass312_1571.anIntArray2664.length > n; ++n) {
                final int n2 = this.aClass312_1571.anIntArray2664[n];
                if (this.anObjectArray1572[n2] == null) {
                    this.method2749(n2, 126);
                    if (this.anObjectArray1572[n2] == null) {
                        b2 = false;
                    }
                }
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.PA(" + b + ')');
        }
    }
    
    private final boolean method2731(final int n, final int n2, final int n3) {
        try {
            if (!this.method2738((byte)(-124))) {
                return false;
            }
            if (n2 >= n && n3 >= 0 && n2 < this.aClass312_1571.anIntArray2671.length && n3 < this.aClass312_1571.anIntArray2671[n2]) {
                return true;
            }
            if (Class94.aBoolean797) {
                throw new IllegalArgumentException(String.valueOf(n2) + "," + n3);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.HA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    private final void method2732(final int n, final int n2) {
        try {
            this.aClass339_1574.method3785(n, false);
            if (n2 != 32768) {
                Class207.anInt1577 = -87;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.IA(" + n + ',' + n2 + ')');
        }
    }
    
    final byte[] method2733(final int n, final int n2) {
        try {
            if (!this.method2738((byte)(-123))) {
                return null;
            }
            if (~this.aClass312_1571.anIntArray2671.length == 0xFFFFFFFE) {
                return this.method2745(n, 0, false);
            }
            if (!this.method2758(n, false)) {
                return null;
            }
            if (this.aClass312_1571.anIntArray2671[n] == 1) {
                return this.method2745(0, n, false);
            }
            throw new RuntimeException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.NA(" + n + ',' + n2 + ')');
        }
    }
    
    final boolean method2734(final String s, final boolean b) {
        try {
            if (b) {
                this.anObjectArrayArray1573 = null;
            }
            if (~this.method2750((byte)(-60), "") != 0x0) {
                return this.method2744("", s, 1);
            }
            return this.method2744(s, "", 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.MA(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final int method2735(final byte b) {
        try {
            if (b > -2) {
                this.method2731(-36, -101, 7);
            }
            if (!this.method2738((byte)(-124))) {
                throw new IllegalStateException("");
            }
            return this.aClass312_1571.anInt2677;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.DA(" + b + ')');
        }
    }
    
    final byte[] method2736(final int[] array, final int n, final int n2, final int n3) {
        try {
            if (!this.method2731(0, n3, n2)) {
                return null;
            }
            if ((this.anObjectArrayArray1573[n3] == null || this.anObjectArrayArray1573[n3][n2] == null) && !this.method2729(array, n2, (byte)(-66), n3)) {
                this.method2749(n3, 103);
                if (!this.method2729(array, n2, (byte)(-95), n3)) {
                    return null;
                }
            }
            final byte[] method1310 = Class98_Sub28_Sub1.method1310(false, this.anObjectArrayArray1573[n3][n2], false);
            Label_0163: {
                if (this.anInt1575 == 1) {
                    this.anObjectArrayArray1573[n3][n2] = null;
                    if (this.aClass312_1571.anIntArray2671[n3] != 1) {
                        break Label_0163;
                    }
                    this.anObjectArrayArray1573[n3] = null;
                    if (!client.aBoolean3553) {
                        break Label_0163;
                    }
                }
                if (this.anInt1575 == 2) {
                    this.anObjectArrayArray1573[n3] = null;
                }
            }
            if (n != 5) {
                return null;
            }
            return method1310;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.M(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final boolean method2737(final boolean b, String lowerCase, String lowerCase2) {
        try {
            if (!this.method2738((byte)(-127))) {
                return false;
            }
            if (!b) {
                return false;
            }
            lowerCase2 = lowerCase2.toLowerCase();
            lowerCase = lowerCase.toLowerCase();
            final int method2200 = this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase2, !b));
            return ~method2200 <= -1 && ~this.aClass312_1571.aClass122Array2672[method2200].method2200((byte)(-26), Class305.method3580(lowerCase, false)) <= -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.GA(" + b + ',' + ((lowerCase != null) ? "{...}" : "null") + ',' + ((lowerCase2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final boolean method2738(final byte b) {
        try {
            if (b > -122) {
                this.anObjectArray1572 = null;
            }
            if (this.aClass312_1571 == null) {
                this.aClass312_1571 = this.aClass339_1574.method3784((byte)(-124));
                if (this.aClass312_1571 == null) {
                    return false;
                }
                this.anObjectArrayArray1573 = new Object[this.aClass312_1571.anInt2668][];
                this.anObjectArray1572 = new Object[this.aClass312_1571.anInt2668];
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.H(" + b + ')');
        }
    }
    
    final byte[] method2739(String lowerCase, String lowerCase2, final int n) {
        try {
            if (!this.method2738((byte)(-124))) {
                return null;
            }
            lowerCase = lowerCase.toLowerCase();
            lowerCase2 = lowerCase2.toLowerCase();
            final int method2200 = this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false));
            if (!this.method2758(method2200, false)) {
                return null;
            }
            if (n != -32734) {
                return null;
            }
            return this.method2745(this.aClass312_1571.aClass122Array2672[method2200].method2200((byte)(-26), Class305.method3580(lowerCase2, false)), method2200, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.B(" + ((lowerCase != null) ? "{...}" : "null") + ',' + ((lowerCase2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final int method2740(final int n, final int n2) {
        try {
            if (!this.method2758(n2, false)) {
                return 0;
            }
            if (this.anObjectArray1572[n2] != null) {
                return 100;
            }
            return this.aClass339_1574.method3790(n2, (byte)(-106));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.G(" + n + ',' + n2 + ')');
        }
    }
    
    final boolean method2741(String lowerCase, final int n) {
        try {
            if (n != 0) {
                this.aBoolean1570 = true;
            }
            if (!this.method2738((byte)(-127))) {
                return false;
            }
            lowerCase = lowerCase.toLowerCase();
            return this.method2756(false, this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.Q(" + ((lowerCase != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method2742(final int n, final int n2) {
        try {
            if (!this.method2738((byte)(-126))) {
                return false;
            }
            if (~this.aClass312_1571.anIntArray2671.length == 0xFFFFFFFE) {
                return this.method2751(n2, 0, -6329);
            }
            if (n > -18) {
                return false;
            }
            if (!this.method2758(n2, false)) {
                return false;
            }
            if (~this.aClass312_1571.anIntArray2671[n2] == 0xFFFFFFFE) {
                return this.method2751(0, n2, -6329);
            }
            throw new RuntimeException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.EA(" + n + ',' + n2 + ')');
        }
    }
    
    final int[] method2743(final int n, final int n2) {
        try {
            if (n2 != 6341) {
                return null;
            }
            if (!this.method2758(n, false)) {
                return null;
            }
            int[] array = this.aClass312_1571.anIntArrayArray2669[n];
            if (array == null) {
                array = new int[this.aClass312_1571.anIntArray2670[n]];
                for (int n3 = 0; array.length > n3; ++n3) {
                    array[n3] = n3;
                }
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.D(" + n + ',' + n2 + ')');
        }
    }
    
    private final boolean method2744(String lowerCase, String lowerCase2, final int n) {
        try {
            if (!this.method2738((byte)(-128))) {
                return false;
            }
            lowerCase = lowerCase.toLowerCase();
            lowerCase2 = lowerCase2.toLowerCase();
            final int method2200 = this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false));
            if (!this.method2758(method2200, false)) {
                return false;
            }
            final int method2201 = this.aClass312_1571.aClass122Array2672[method2200].method2200((byte)(-26), Class305.method3580(lowerCase2, false));
            if (n != 1) {
                this.method2730((byte)3);
            }
            return this.method2751(method2201, method2200, n - 6330);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.U(" + ((lowerCase != null) ? "{...}" : "null") + ',' + ((lowerCase2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final byte[] method2745(final int n, final int n2, final boolean b) {
        try {
            if (b) {
                Class207.aClass348_1569 = null;
            }
            return this.method2736(null, 5, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.CA(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method2746(final Class43 aClass43_5177) {
        Class284_Sub1.aClass43_5177 = aClass43_5177;
    }
    
    final void method2747(final boolean b, final boolean b2, final byte b3) {
        try {
            if (this.method2738((byte)(-123))) {
                if (b) {
                    this.aClass312_1571.aClass122_2666 = null;
                    this.aClass312_1571.anIntArray2662 = null;
                }
                if (b2) {
                    this.aClass312_1571.aClass122Array2672 = null;
                    this.aClass312_1571.anIntArrayArray2674 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.N(" + b + ',' + b2 + ',' + b3 + ')');
        }
    }
    
    final int method2748(final int n, String lowerCase) {
        try {
            if (!this.method2738((byte)(-127))) {
                return 0;
            }
            if (n != 29952) {
                method2765((byte)(-43));
            }
            lowerCase = lowerCase.toLowerCase();
            return this.method2740(117, this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.O(" + n + ',' + ((lowerCase != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method2749(final int n, final int n2) {
        try {
            if (this.aBoolean1570) {
                this.anObjectArray1572[n] = this.aClass339_1574.method3786(n, 0);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            this.anObjectArray1572[n] = Class64_Sub25.method654(2, this.aClass339_1574.method3786(n, 0), false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.K(" + n + ',' + n2 + ')');
        }
    }
    
    final int method2750(final byte b, String lowerCase) {
        try {
            if (!this.method2738((byte)(-125))) {
                return -1;
            }
            lowerCase = lowerCase.toLowerCase();
            if (b > -49) {
                return -53;
            }
            final int method2200 = this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false));
            if (!this.method2758(method2200, false)) {
                return -1;
            }
            return method2200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.AA(" + b + ',' + ((lowerCase != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method2751(final int n, final int n2, final int n3) {
        try {
            if (!this.method2731(n3 ^ n3, n2, n)) {
                return false;
            }
            if (this.anObjectArrayArray1573[n2] != null && this.anObjectArrayArray1573[n2][n] != null) {
                return true;
            }
            if (this.anObjectArray1572[n2] != null) {
                return true;
            }
            this.method2749(n2, 103);
            return this.anObjectArray1572[n2] != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.J(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final int method2752(final byte b) {
        try {
            if (!this.method2738((byte)(-123))) {
                return -1;
            }
            if (b != -11) {
                this.method2751(2, -51, -100);
            }
            return this.aClass312_1571.anIntArray2671.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.P(" + b + ')');
        }
    }
    
    static final float method2753(final int n, final int n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        try {
            float n9 = 0.0f;
            if (n2 != 10665) {
                Class207.anInt1577 = -101;
            }
            final float n10 = -n8 + n4;
            final float n11 = -n6 + n5;
            final float n12 = -n3 + n7;
            float n13 = 0.0f;
            float n14 = 0.0f;
            float n15 = 0.0f;
            while (n9 < 1.1f) {
                final float n16 = n8 + n10 * n9;
                final float n17 = n6 + n11 * n9;
                final float n18 = n3 + n9 * n12;
                final int n19 = (int)n16 >> -1052735575;
                final int n20 = (int)n18 >> 1278945001;
                if (n19 > 0 && n20 > 0 && n19 < Class165.anInt1276 && n20 < Class98_Sub10_Sub7.anInt5572) {
                    int aByte5088 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088;
                    if (~aByte5088 > -4 && ~(0x2 & Class281.aByteArrayArrayArray2117[1][n19][n20]) != -1) {
                        ++aByte5088;
                    }
                    if (n17 > Class98_Sub46_Sub2_Sub2.aSArray6298[aByte5088].method3417((int)n16, (int)n18, true)) {
                        if (n >= 2) {
                            return 0.1f * method2753(-1 + n, 10665, n15, n16, n17, n14, n18, n13) + (n9 - 0.1f);
                        }
                        return n9;
                    }
                }
                n9 += 0.1f;
                n15 = n18;
                n13 = n16;
                n14 = n17;
            }
            return -1.0f;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.W(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    final void method2754(final int n, final int n2) {
        try {
            if (n <= -6 && this.method2758(n2, false)) {
                if (this.anObjectArrayArray1573 != null) {
                    this.anObjectArrayArray1573[n2] = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.BA(" + n + ',' + n2 + ')');
        }
    }
    
    final void method2755(String lowerCase, final int n) {
        try {
            if (n <= -90 && this.method2738((byte)(-127))) {
                lowerCase = lowerCase.toLowerCase();
                this.method2732(this.aClass312_1571.aClass122_2666.method2200((byte)(-26), Class305.method3580(lowerCase, false)), 32768);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.OA(" + ((lowerCase != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method2756(final boolean b, final int n) {
        try {
            if (!this.method2758(n, b)) {
                return false;
            }
            if (this.anObjectArray1572[n] != null) {
                return true;
            }
            this.method2749(n, 112);
            return this.anObjectArray1572[n] != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.A(" + b + ',' + n + ')');
        }
    }
    
    public static void method2757(final int n) {
        try {
            Class207.aClass58_1576 = null;
            Class207.aClass348_1569 = null;
            if (n != 1) {
                method2757(51);
            }
            Class207.aClass196_1578 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.I(" + n + ')');
        }
    }
    
    private final boolean method2758(final int n, final boolean b) {
        try {
            if (!this.method2738((byte)(-126))) {
                return false;
            }
            if (~n <= -1 && ~n > ~this.aClass312_1571.anIntArray2671.length && this.aClass312_1571.anIntArray2671[n] != 0) {
                if (b) {
                    Class207.aClass348_1569 = null;
                }
                return true;
            }
            if (!Class94.aBoolean797) {
                return false;
            }
            throw new IllegalArgumentException(Integer.toString(n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.LA(" + n + ',' + b + ')');
        }
    }
    
    static final void method2759(final boolean b, String lowerCase, final byte b2) {
        try {
            lowerCase = lowerCase.toLowerCase();
            short[] aShortArray3682 = new short[16];
            int anInt214 = 0;
            final int n = b ? 32768 : 0;
            final int n2 = n - -(b ? Class52.aClass280_3500.anInt2103 : Class52.aClass280_3500.anInt2102);
            if (b2 > 51) {
                for (int n3 = n; ~n2 < ~n3; ++n3) {
                    final Class98_Sub46_Sub11 method3325 = Class52.aClass280_3500.method3325(n3, 83);
                    if (method3325.aBoolean6027 && ~method3325.method1582(false).toLowerCase().indexOf(lowerCase) != 0x0) {
                        if (~anInt214 <= -51) {
                            Class18.anInt214 = -1;
                            Class64_Sub16.aShortArray3682 = null;
                            return;
                        }
                        if (~aShortArray3682.length >= ~anInt214) {
                            final short[] array = new short[2 * aShortArray3682.length];
                            for (int i = 0; i < anInt214; ++i) {
                                array[i] = aShortArray3682[i];
                            }
                            aShortArray3682 = array;
                        }
                        aShortArray3682[anInt214++] = (short)n3;
                    }
                }
                Class18.anInt214 = anInt214;
                Class85.anInt638 = 0;
                Class64_Sub16.aShortArray3682 = aShortArray3682;
                final String[] array2 = new String[Class18.anInt214];
                for (int j = 0; j < Class18.anInt214; ++j) {
                    array2[j] = Class52.aClass280_3500.method3325(aShortArray3682[j], 48).method1582(false);
                }
                Class98_Sub11.method1126(true, Class64_Sub16.aShortArray3682, array2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.FA(" + b + ',' + ((lowerCase != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    final void method2760(final byte b) {
        try {
            if (b > -114) {
                this.method2741(null, -42);
            }
            if (this.anObjectArrayArray1573 != null) {
                for (int n = 0; this.anObjectArrayArray1573.length > n; ++n) {
                    this.anObjectArrayArray1573[n] = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.C(" + b + ')');
        }
    }
    
    Class207(final Class339 aClass339_1574, final boolean aBoolean1570, final int anInt1575) {
        this.aClass312_1571 = null;
        try {
            if (~anInt1575 > -1 || anInt1575 > 2) {
                throw new IllegalArgumentException("js5: Invalid value " + anInt1575 + " supplied for discardunpacked");
            }
            this.aClass339_1574 = aClass339_1574;
            this.aBoolean1570 = aBoolean1570;
            this.anInt1575 = anInt1575;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.<init>(" + ((aClass339_1574 != null) ? "{...}" : "null") + ',' + aBoolean1570 + ',' + anInt1575 + ')');
        }
    }
    
    final int method2761(final int n, final int n2) {
        try {
            if (!this.method2758(n2, false)) {
                return 0;
            }
            if (n != 0) {
                method2757(-44);
            }
            return this.aClass312_1571.anIntArray2671[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.JA(" + n + ',' + n2 + ')');
        }
    }
    
    final int method2762(final byte b) {
        try {
            if (!this.method2738((byte)(-126))) {
                return 0;
            }
            int n = 0;
            int n2 = 0;
            for (int n3 = 0; ~this.anObjectArray1572.length < ~n3; ++n3) {
                if (this.aClass312_1571.anIntArray2670[n3] > 0) {
                    n2 += this.method2740(-48, n3);
                    n += 100;
                }
            }
            if (n == 0) {
                return 100;
            }
            return n2 * 100 / n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.V(" + b + ')');
        }
    }
    
    final int method2763(final int n, final int n2) {
        try {
            if (!this.method2738((byte)(-126))) {
                return -1;
            }
            final int method2200 = this.aClass312_1571.aClass122_2666.method2200((byte)(-26), n2);
            if (!this.method2758(method2200, false)) {
                return -1;
            }
            if (n <= 35) {
                method2746(null);
            }
            return method2200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.L(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2764(final int n, final int n2, final int n3) {
        try {
            if (n3 < -24 && Class85.method837(n2, 124)) {
                Class187.method2634(0, Class159.aClass293ArrayArray1252[n2], n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.T(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2765(final byte b) {
        try {
            Class154.aHa1231.a(Class200.aClass111_1543);
            if (b < 1) {
                Class207.aClass196_1578 = null;
            }
            Class154.aHa1231.DA(Class98_Sub48.anInt4279, Class54.anInt3391, Class96.anInt802, Class98_Sub10_Sub38.anInt5752);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.R(" + b + ')');
        }
    }
    
    final void method2766(final int n) {
        try {
            if (this.anObjectArray1572 != null) {
                for (int n2 = 0; ~n2 > ~this.anObjectArray1572.length; ++n2) {
                    this.anObjectArray1572[n2] = null;
                }
            }
            if (n != 16) {
                Class207.aClass58_1576 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ni.E(" + n + ')');
        }
    }
    
    static {
        Class207.aClass348_1569 = new Class348(13, 0, 1, 0);
        Class207.aClass58_1576 = new IncomingOpcode(92, 16);
        Class207.aClass196_1578 = new Class196("WTWIP", "office", "_wip", 3);
    }
}
