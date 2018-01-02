// 
// Decompiled by Procyon v0.5.30
// 

final class Class86
{
    static float[] aFloatArray640;
    private int anInt641;
    int anInt642;
    private int anInt643;
    Class121 aClass121_644;
    int anInt645;
    int anInt646;
    private int anInt647;
    int anInt648;
    static Class350 aClass350_649;
    int anInt650;
    int anInt651;
    private int anInt652;
    int anInt653;
    private String aString654;
    int anInt655;
    
    private final void method841(final int n, final int n2, final Class98_Sub22 class98_Sub22) {
        try {
            if (n2 == 1) {
                this.anInt655 = class98_Sub22.readShort((byte)127);
            }
            else if (~n2 == 0xFFFFFFFD) {
                this.anInt648 = class98_Sub22.method1186(-128);
            }
            else if (~n2 == 0xFFFFFFFC) {
                this.anInt641 = class98_Sub22.readShort((byte)127);
            }
            else if (n2 == 4) {
                this.anInt643 = class98_Sub22.readShort((byte)127);
            }
            else if (~n2 == 0xFFFFFFFA) {
                this.anInt652 = class98_Sub22.readShort((byte)127);
            }
            else if (~n2 != 0xFFFFFFF9) {
                if (~n2 != 0xFFFFFFF8) {
                    if (~n2 != 0xFFFFFFF7) {
                        if (~n2 == 0xFFFFFFF6) {
                            this.anInt651 = class98_Sub22.readShort((byte)127);
                        }
                        else if (n2 == 10) {
                            this.anInt650 = class98_Sub22.readUShort(false);
                        }
                        else if (n2 == 11) {
                            this.anInt645 = 0;
                        }
                        else if (n2 == 12) {
                            this.anInt642 = class98_Sub22.readUnsignedByte((byte)(-120));
                        }
                        else if (n2 == 13) {
                            this.anInt646 = class98_Sub22.readUShort(false);
                        }
                        else if (n2 == 14) {
                            this.anInt645 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.aString654 = class98_Sub22.method1223(-1);
                    }
                }
                else {
                    this.anInt653 = class98_Sub22.readUShort(false);
                }
            }
            else {
                this.anInt647 = class98_Sub22.readShort((byte)127);
            }
            if (n > -7) {
                method844(2, false, 22, 53, -22, 114, -118, -30);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.C(" + n + ',' + n2 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method842(final boolean b) {
        try {
            Class86.aFloatArray640 = null;
            Class86.aClass350_649 = null;
            if (b) {
                Class86.aFloatArray640 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.J(" + b + ')');
        }
    }
    
    static final void method843(final int n, final ha ha, final Class148 class148, final int n2, final int n3) {
        try {
            Class8.aClass148_110.method2422((byte)47);
            if (!Class98_Sub43_Sub1.aBoolean5895) {
                for (Class98_Sub47 class98_Sub47 = (Class98_Sub47)class148.method2418(32); class98_Sub47 != null; class98_Sub47 = (Class98_Sub47)class148.method2417(n2 + 93)) {
                    final Class24 method3807 = Class278.aClass341_2057.method3807(n2 ^ 0xFFFFFF9A, class98_Sub47.anInt4268);
                    if (Class87.method855(87, method3807) && Class98_Sub40.method1473(method3807, n, class98_Sub47, n3, 15924, ha)) {
                        Class103.method1711(ha, (byte)70, class98_Sub47, method3807);
                    }
                }
                if (n2 != -1) {
                    Class86.aFloatArray640 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.F(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class148 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method844(final int n, final boolean b, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            if ((b ? Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4054.method641((byte)127) : Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)122)) != 0 && n3 != 0 && Class306.anInt2566 < 50 && n6 != -1) {
                Class245.aClass338Array1865[Class306.anInt2566++] = new Class338((byte)(b ? 3 : 2), n6, n3, n2, n4, n5, n, null);
            }
            if (n7 >= -58) {
                method844(-106, false, -53, 52, 126, 31, -86, -57);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.E(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final String method845(final byte b, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            if (b != -124) {
                method844(-38, false, 87, -74, -69, -59, 2, -40);
            }
            if (class98_Sub46_Sub8.aString5985 != null && class98_Sub46_Sub8.aString5985.length() != 0) {
                if (class98_Sub46_Sub8.aString5992 == null || ~class98_Sub46_Sub8.aString5992.length() >= -1) {
                    return class98_Sub46_Sub8.aString5994 + Class309.aClass309_2618.method3615(Class374.anInt3159, (byte)25) + class98_Sub46_Sub8.aString5985;
                }
                return class98_Sub46_Sub8.aString5994 + Class309.aClass309_2618.method3615(Class374.anInt3159, (byte)25) + class98_Sub46_Sub8.aString5992 + Class309.aClass309_2618.method3615(Class374.anInt3159, (byte)25) + class98_Sub46_Sub8.aString5985;
            }
            else {
                if (class98_Sub46_Sub8.aString5992 == null || ~class98_Sub46_Sub8.aString5992.length() >= -1) {
                    return class98_Sub46_Sub8.aString5994;
                }
                return class98_Sub46_Sub8.aString5994 + Class309.aClass309_2618.method3615(Class374.anInt3159, (byte)25) + class98_Sub46_Sub8.aString5992;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.K(" + b + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class332 method846(final boolean b, final ha ha) {
        try {
            if (this.anInt641 < 0) {
                return null;
            }
            if (b) {
                this.anInt655 = 120;
            }
            Class332 class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-125, this.anInt641);
            if (class332 == null) {
                this.method847(-109, ha);
                class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-125, this.anInt641);
            }
            return class332;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.A(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method847(final int n, final ha ha) {
        try {
            final Class207 aClass207_1005 = this.aClass121_644.aClass207_1005;
            if (this.anInt641 >= 0 && this.aClass121_644.aClass79_1003.method802(-120, this.anInt641) == null && aClass207_1005.method2742(-32, this.anInt641)) {
                this.aClass121_644.aClass79_1003.method805(this.anInt641, ha.method1758(Class324.method3683(aClass207_1005, this.anInt641), true), (byte)(-80));
            }
            if (this.anInt652 >= 0 && this.aClass121_644.aClass79_1003.method802(-119, this.anInt652) == null && aClass207_1005.method2742(-83, this.anInt652)) {
                this.aClass121_644.aClass79_1003.method805(this.anInt652, ha.method1758(Class324.method3683(aClass207_1005, this.anInt652), true), (byte)(-80));
            }
            if (~this.anInt643 <= -1 && this.aClass121_644.aClass79_1003.method802(-126, this.anInt643) == null && aClass207_1005.method2742(-91, this.anInt643)) {
                this.aClass121_644.aClass79_1003.method805(this.anInt643, ha.method1758(Class324.method3683(aClass207_1005, this.anInt643), true), (byte)(-80));
            }
            if (~this.anInt647 <= -1 && this.aClass121_644.aClass79_1003.method802(-123, this.anInt647) == null && aClass207_1005.method2742(-79, this.anInt647)) {
                this.aClass121_644.aClass79_1003.method805(this.anInt647, ha.method1758(Class324.method3683(aClass207_1005, this.anInt647), true), (byte)(-80));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.G(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final String method848(final int n, final int n2) {
        try {
            String s = this.aString654;
            if (n != 22414) {
                this.anInt652 = 8;
            }
            while (true) {
                final int index = s.indexOf("%1");
                if (~index > -1) {
                    break;
                }
                s = s.substring(0, index) + Class44.method428(n2, false, false) + s.substring(index + 2);
            }
            return s;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.L(" + n + ',' + n2 + ')');
        }
    }
    
    final Class332 method849(final ha ha, final int n) {
        try {
            if (~this.anInt643 > -1) {
                return null;
            }
            Class332 class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-127, this.anInt643);
            if (class332 == null) {
                this.method847(-83, ha);
                class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-128, this.anInt643);
            }
            return class332;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.H(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class332 method850(final int n, final ha ha) {
        try {
            if (n < ~this.anInt647) {
                return null;
            }
            Class332 class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-121, this.anInt647);
            if (class332 == null) {
                this.method847(n + 61, ha);
                class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-125, this.anInt647);
            }
            return class332;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.I(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method851(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)9);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method841(-120, unsignedByte, class98_Sub22);
            }
            if (n != -1) {
                Class86.aFloatArray640 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class332 method852(final int n, final ha ha) {
        try {
            if (this.anInt652 < 0) {
                return null;
            }
            Class332 class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-122, this.anInt652);
            if (n < 11) {
                return null;
            }
            if (class332 == null) {
                this.method847(45, ha);
                class332 = (Class332)this.aClass121_644.aClass79_1003.method802(-128, this.anInt652);
            }
            return class332;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fj.D(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class86() {
        this.anInt641 = -1;
        this.anInt642 = -1;
        this.anInt647 = -1;
        this.anInt645 = -1;
        this.anInt650 = 0;
        this.anInt648 = 16777215;
        this.anInt651 = 70;
        this.anInt643 = -1;
        this.anInt652 = -1;
        this.anInt653 = 0;
        this.anInt646 = 0;
        this.anInt655 = -1;
        this.aString654 = "";
    }
    
    static {
        Class86.aFloatArray640 = new float[16];
    }
}
