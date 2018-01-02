import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class135
{
    static int anInt1051;
    static boolean aBoolean1052;
    static float aFloat1053;
    static Class79 aClass79_1054;
    private Class215 aClass215_1055;
    static int[] anIntArray1056;
    static short aShort1057;
    private Class215 aClass215_1058;
    private Class215 aClass215_1059;
    private Class215 aClass215_1060;
    private long aLong1061;
    private int anInt1062;
    private Class361 aClass361_1063;
    private Class98_Sub22 aClass98_Sub22_1064;
    volatile int anInt1065;
    volatile int anInt1066;
    private byte aByte1067;
    private Class98_Sub22 aClass98_Sub22_1068;
    private Class98_Sub46_Sub13_Sub1 aClass98_Sub46_Sub13_Sub1_1069;
    
    final void method2249(final int n) {
        try {
            if (this.aClass361_1063 != null) {
                this.aClass361_1063.method3923(-29789);
            }
            if (n >= -34) {
                this.aLong1061 = 81L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.C(" + n + ')');
        }
    }
    
    public static void method2250(final int n) {
        try {
            if (n == 5790) {
                Class135.aClass79_1054 = null;
                Class135.anIntArray1056 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.G(" + n + ')');
        }
    }
    
    private final int method2251(final byte b) {
        try {
            if (b <= 44) {
                Class135.aClass79_1054 = null;
            }
            return this.aClass215_1059.method2788(-126) + this.aClass215_1060.method2788(-109);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.O(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub13_Sub1 method2252(final int n, final byte aByte6306, final int n2, final int n3, final boolean aBoolean6037) {
        try {
            final long aLong4259 = (n << -1790531408) - -n2;
            final Class98_Sub46_Sub13_Sub1 class98_Sub46_Sub13_Sub1 = new Class98_Sub46_Sub13_Sub1();
            class98_Sub46_Sub13_Sub1.aByte6306 = aByte6306;
            if (n3 < 104) {
                this.method2256(-109);
            }
            class98_Sub46_Sub13_Sub1.aLong4259 = aLong4259;
            if (!(class98_Sub46_Sub13_Sub1.aBoolean6037 = aBoolean6037)) {
                if (~this.method2251((byte)94) <= -21) {
                    throw new RuntimeException();
                }
                this.aClass215_1059.method2785(class98_Sub46_Sub13_Sub1, -28);
                if (!client.aBoolean3553) {
                    return class98_Sub46_Sub13_Sub1;
                }
            }
            if (this.method2261(-1) >= 20) {
                throw new RuntimeException();
            }
            this.aClass215_1055.method2785(class98_Sub46_Sub13_Sub1, -106);
            return class98_Sub46_Sub13_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.F(" + n + ',' + aByte6306 + ',' + n2 + ',' + n3 + ',' + aBoolean6037 + ')');
        }
    }
    
    final boolean method2253(final int n) {
        try {
            return n <= 23 || this.method2261(-1) >= 20;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.J(" + n + ')');
        }
    }
    
    final void method2254(final int n) {
        try {
            if (this.aClass361_1063 != null) {
                try {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(7, 77);
                    this.aClass98_Sub22_1064.method1225(-24472, 0);
                    if (n == 29043) {
                        this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                    }
                }
                catch (IOException ex2) {
                    try {
                        this.aClass361_1063.method3923(n ^ 0xFFFFFAD0);
                    }
                    catch (Exception ex3) {}
                    this.aClass361_1063 = null;
                    this.anInt1066 = -2;
                    ++this.anInt1065;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.I(" + n + ')');
        }
    }
    
    final void method2255(final int n, final boolean b) {
        try {
            if (n != 2) {
                this.method2257(121);
            }
            if (this.aClass361_1063 != null) {
                try {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(b ? 2 : 3, 121);
                    this.aClass98_Sub22_1064.method1225(-24472, 0);
                    this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                }
                catch (IOException ex2) {
                    try {
                        this.aClass361_1063.method3923(-29789);
                    }
                    catch (Exception ex3) {}
                    this.anInt1066 = -2;
                    ++this.anInt1065;
                    this.aClass361_1063 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.L(" + n + ',' + b + ')');
        }
    }
    
    final boolean method2256(final int n) {
        try {
            if (this.aClass361_1063 != null) {
                final long method3819 = Class343.method3819(-47);
                int n2 = (int)(method3819 - this.aLong1061);
                this.aLong1061 = method3819;
                if (n2 > 200) {
                    n2 = 200;
                }
                this.anInt1062 += n2;
                if (this.anInt1062 > 30000) {
                    try {
                        this.aClass361_1063.method3923(-29789);
                    }
                    catch (Exception ex2) {}
                    this.aClass361_1063 = null;
                }
            }
            if (this.aClass361_1063 == null) {
                return this.method2261(-1) == 0 && this.method2251((byte)49) == 0;
            }
            try {
                this.aClass361_1063.method3916(true);
                for (Class98_Sub46_Sub13_Sub1 class98_Sub46_Sub13_Sub1 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1055.method2792(-1); class98_Sub46_Sub13_Sub1 != null; class98_Sub46_Sub13_Sub1 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1055.method2787(0)) {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(1, n ^ 0x104C);
                    this.aClass98_Sub22_1064.method1225(-24472, (int)class98_Sub46_Sub13_Sub1.aLong4259);
                    this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                    this.aClass215_1058.method2785(class98_Sub46_Sub13_Sub1, -84);
                }
                if (n != 4096) {
                    this.method2263(67);
                }
                for (Class98_Sub46_Sub13_Sub1 class98_Sub46_Sub13_Sub2 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1059.method2792(-1); class98_Sub46_Sub13_Sub2 != null; class98_Sub46_Sub13_Sub2 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1059.method2787(n ^ 0x1000)) {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(0, n ^ 0x102A);
                    this.aClass98_Sub22_1064.method1225(n - 28568, (int)class98_Sub46_Sub13_Sub2.aLong4259);
                    this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                    this.aClass215_1060.method2785(class98_Sub46_Sub13_Sub2, -77);
                }
                for (int n3 = 0; ~n3 > -101; ++n3) {
                    final int method3820 = this.aClass361_1063.method3915(75);
                    if (method3820 < 0) {
                        throw new IOException();
                    }
                    if (method3820 == 0) {
                        break;
                    }
                    this.anInt1062 = 0;
                    int n4 = 0;
                    if (this.aClass98_Sub46_Sub13_Sub1_1069 != null) {
                        if (this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 == 0) {
                            n4 = 1;
                        }
                    }
                    else {
                        n4 = 8;
                    }
                    if (~n4 >= -1) {
                        final int n5 = this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.aByteArray3992.length + -this.aClass98_Sub46_Sub13_Sub1_1069.aByte6306;
                        int i = -this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 + 512;
                        if (-this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991 + n5 < i) {
                            i = n5 + -this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991;
                        }
                        if (i > method3820) {
                            i = method3820;
                        }
                        this.aClass361_1063.method3921(this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991, true, i, this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.aByteArray3992);
                        if (this.aByte1067 != 0) {
                            for (int n6 = 0; i > n6; ++n6) {
                                this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.aByteArray3992[n6 + this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991] = (byte)Class369.method3953(this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.aByteArray3992[n6 + this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991], this.aByte1067);
                            }
                        }
                        final Class98_Sub22 aClass98_Sub22_6305 = this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305;
                        aClass98_Sub22_6305.anInt3991 += i;
                        final Class98_Sub46_Sub13_Sub1 aClass98_Sub46_Sub13_Sub1_1069 = this.aClass98_Sub46_Sub13_Sub1_1069;
                        aClass98_Sub46_Sub13_Sub1_1069.anInt6304 += i;
                        if (~n5 != ~this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.anInt3991) {
                            if (this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 == 512) {
                                this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 = 0;
                            }
                        }
                        else {
                            this.aClass98_Sub46_Sub13_Sub1_1069.method1524((byte)(-90));
                            this.aClass98_Sub46_Sub13_Sub1_1069.aBoolean6038 = false;
                            this.aClass98_Sub46_Sub13_Sub1_1069 = null;
                        }
                    }
                    else {
                        int n7 = n4 + -this.aClass98_Sub22_1068.anInt3991;
                        if (~method3820 > ~n7) {
                            n7 = method3820;
                        }
                        this.aClass361_1063.method3921(this.aClass98_Sub22_1068.anInt3991, true, n7, this.aClass98_Sub22_1068.aByteArray3992);
                        if (~this.aByte1067 != -1) {
                            for (int n8 = 0; ~n8 > ~n7; ++n8) {
                                this.aClass98_Sub22_1068.aByteArray3992[n8 + this.aClass98_Sub22_1068.anInt3991] = (byte)Class369.method3953(this.aClass98_Sub22_1068.aByteArray3992[n8 + this.aClass98_Sub22_1068.anInt3991], this.aByte1067);
                            }
                        }
                        final Class98_Sub22 aClass98_Sub22_6306 = this.aClass98_Sub22_1068;
                        aClass98_Sub22_6306.anInt3991 += n7;
                        if (~n4 >= ~this.aClass98_Sub22_1068.anInt3991) {
                            if (this.aClass98_Sub46_Sub13_Sub1_1069 == null) {
                                this.aClass98_Sub22_1068.anInt3991 = 0;
                                final int unsignedByte = this.aClass98_Sub22_1068.readUnsignedByte((byte)29);
                                final int short1 = this.aClass98_Sub22_1068.readShort((byte)127);
                                final int unsignedByte2 = this.aClass98_Sub22_1068.readUnsignedByte((byte)(-110));
                                final int int1 = this.aClass98_Sub22_1068.readInt(n - 4098);
                                final int n9 = unsignedByte2 & 0x7F;
                                final boolean b = (0x80 & unsignedByte2) != 0x0;
                                final long n10 = (unsignedByte << 1231200656) - -short1;
                                Class98_Sub46_Sub13_Sub1 aClass98_Sub46_Sub13_Sub1_1070;
                                if (b) {
                                    for (aClass98_Sub46_Sub13_Sub1_1070 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1060.method2792(n ^ 0xFFFFEFFF); aClass98_Sub46_Sub13_Sub1_1070 != null; aClass98_Sub46_Sub13_Sub1_1070 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1060.method2787(0)) {
                                        if (~n10 == ~aClass98_Sub46_Sub13_Sub1_1070.aLong4259) {
                                            break;
                                        }
                                    }
                                }
                                else {
                                    for (aClass98_Sub46_Sub13_Sub1_1070 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1058.method2792(-1); aClass98_Sub46_Sub13_Sub1_1070 != null; aClass98_Sub46_Sub13_Sub1_1070 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1058.method2787(0)) {
                                        if (~aClass98_Sub46_Sub13_Sub1_1070.aLong4259 == ~n10) {
                                            break;
                                        }
                                    }
                                }
                                if (aClass98_Sub46_Sub13_Sub1_1070 == null) {
                                    throw new IOException();
                                }
                                final int n11 = (~n9 != -1) ? 9 : 5;
                                this.aClass98_Sub46_Sub13_Sub1_1069 = aClass98_Sub46_Sub13_Sub1_1070;
                                (this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305 = new Class98_Sub22(this.aClass98_Sub46_Sub13_Sub1_1069.aByte6306 + (int1 - -n11))).method1194(n9, 107);
                                this.aClass98_Sub46_Sub13_Sub1_1069.aClass98_Sub22_6305.writeInt(1571862888, int1);
                                this.aClass98_Sub22_1068.anInt3991 = 0;
                                this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 = 8;
                            }
                            else {
                                if (~this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 != -1) {
                                    throw new IOException();
                                }
                                if (this.aClass98_Sub22_1068.aByteArray3992[0] != -1) {
                                    this.aClass98_Sub46_Sub13_Sub1_1069 = null;
                                }
                                else {
                                    this.aClass98_Sub46_Sub13_Sub1_1069.anInt6304 = 1;
                                    this.aClass98_Sub22_1068.anInt3991 = 0;
                                }
                            }
                        }
                    }
                }
                return true;
            }
            catch (IOException ex3) {
                try {
                    this.aClass361_1063.method3923(n ^ 0xFFFF9BA3);
                }
                catch (Exception ex4) {}
                ++this.anInt1065;
                this.anInt1066 = -2;
                this.aClass361_1063 = null;
                return ~this.method2261(n ^ 0xFFFFEFFF) == -1 && ~this.method2251((byte)51) == -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.A(" + n + ')');
        }
    }
    
    final void method2257(final int n) {
        try {
            try {
                this.aClass361_1063.method3923(-29789);
                if (n <= 17) {
                    this.method2262((byte)108);
                }
            }
            catch (Exception ex2) {}
            ++this.anInt1065;
            this.aClass361_1063 = null;
            this.anInt1066 = -1;
            this.aByte1067 = (byte)(1.0 + Math.random() * 255.0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.N(" + n + ')');
        }
    }
    
    static final int[][] method2258(final int anInt5733, final int i, final boolean aBoolean5731, final int n, final float n2, final int n3, final int anInt5734, final byte b, final int anInt5735) {
        try {
            final int[][] array = new int[i][n];
            final Class98_Sub10_Sub35 class98_Sub10_Sub35 = new Class98_Sub10_Sub35();
            class98_Sub10_Sub35.anInt5734 = anInt5735;
            class98_Sub10_Sub35.anInt5737 = anInt5734;
            class98_Sub10_Sub35.anInt5733 = anInt5733;
            class98_Sub10_Sub35.aBoolean5731 = aBoolean5731;
            class98_Sub10_Sub35.anInt5739 = (int)(4096.0f * n2);
            class98_Sub10_Sub35.method1001((byte)66);
            Class64_Sub2.method559(true, n, i);
            for (int n4 = 0; i > n4; ++n4) {
                class98_Sub10_Sub35.method1107((byte)(-127), array[n4], n4);
            }
            if (b != -63) {
                Class135.anIntArray1056 = null;
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.E(" + anInt5733 + ',' + i + ',' + aBoolean5731 + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt5734 + ',' + b + ',' + anInt5735 + ')');
        }
    }
    
    final void method2259(final int n) {
        try {
            if (n != 0) {
                method2264((byte)(-57));
            }
            if (this.aClass361_1063 != null) {
                this.aClass361_1063.method3922(-69);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.H(" + n + ')');
        }
    }
    
    final void method2260(final Class361 aClass361_1063, final boolean b, final byte b2) {
        try {
            if (b2 != 74) {
                Class135.anInt1051 = 12;
            }
            if (this.aClass361_1063 != null) {
                try {
                    this.aClass361_1063.method3923(b2 ^ 0xFFFF8BE9);
                }
                catch (Exception ex2) {}
                this.aClass361_1063 = null;
            }
            this.aClass361_1063 = aClass361_1063;
            this.method2262((byte)63);
            this.method2255(2, b);
            this.aClass98_Sub22_1068.anInt3991 = 0;
            this.aClass98_Sub46_Sub13_Sub1_1069 = null;
            while (true) {
                final Class98_Sub46_Sub13_Sub1 class98_Sub46_Sub13_Sub1 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1058.method2789(-16711936);
                if (class98_Sub46_Sub13_Sub1 == null) {
                    break;
                }
                this.aClass215_1055.method2785(class98_Sub46_Sub13_Sub1, -29);
            }
            while (true) {
                final Class98_Sub46_Sub13_Sub1 class98_Sub46_Sub13_Sub2 = (Class98_Sub46_Sub13_Sub1)this.aClass215_1060.method2789(-16711936);
                if (class98_Sub46_Sub13_Sub2 == null) {
                    break;
                }
                this.aClass215_1059.method2785(class98_Sub46_Sub13_Sub2, -82);
            }
            if (~this.aByte1067 != -1) {
                try {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(4, 67);
                    this.aClass98_Sub22_1064.method1194(this.aByte1067, -41);
                    this.aClass98_Sub22_1064.writeShort(0, 1571862888);
                    this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                }
                catch (IOException ex3) {
                    try {
                        this.aClass361_1063.method3923(-29789);
                    }
                    catch (Exception ex4) {}
                    this.aClass361_1063 = null;
                    ++this.anInt1065;
                    this.anInt1066 = -2;
                }
            }
            this.anInt1062 = 0;
            this.aLong1061 = Class343.method3819(-47);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.D(" + ((aClass361_1063 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ')');
        }
    }
    
    final int method2261(final int n) {
        try {
            if (n != -1) {
                this.method2259(15);
            }
            return this.aClass215_1055.method2788(-118) + this.aClass215_1058.method2788(-121);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.M(" + n + ')');
        }
    }
    
    private final void method2262(final byte b) {
        try {
            if (b <= 37) {
                this.method2253(-82);
            }
            if (this.aClass361_1063 != null) {
                try {
                    this.aClass98_Sub22_1064.anInt3991 = 0;
                    this.aClass98_Sub22_1064.method1194(6, -111);
                    this.aClass98_Sub22_1064.method1225(-24472, 3);
                    this.aClass361_1063.method3920((byte)77, 0, 4, this.aClass98_Sub22_1064.aByteArray3992);
                }
                catch (IOException ex2) {
                    try {
                        this.aClass361_1063.method3923(-29789);
                    }
                    catch (Exception ex3) {}
                    ++this.anInt1065;
                    this.anInt1066 = -2;
                    this.aClass361_1063 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.P(" + b + ')');
        }
    }
    
    final boolean method2263(final int n) {
        try {
            if (n != 20) {
                Class135.aFloat1053 = 0.7821552f;
            }
            return this.method2251((byte)53) >= 20;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.B(" + n + ')');
        }
    }
    
    static final void method2264(final byte b) {
        try {
            if (b > -77) {
                Class135.aFloat1053 = -0.715336f;
            }
            final int method612 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058.method612((byte)125);
            if (~method612 == -1) {
                OutputStream_Sub2.aByteArrayArrayArray41 = null;
                Class36.method341(0, -1003);
            }
            else if (~method612 == 0xFFFFFFFE) {
                Class199.method2685(-15, (byte)0);
                Class36.method341(512, -1003);
                if (Class281.aByteArrayArrayArray2117 != null) {
                    Class254.method3186(59);
                }
            }
            else {
                Class199.method2685(-15, (byte)(0xFF & -4 + Class64_Sub15.anInt3676));
                Class36.method341(2, -1003);
            }
            Class145.anInt1170 = Class43.anInt377;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jd.K(" + b + ')');
        }
    }
    
    public Class135() {
        this.aClass215_1055 = new Class215();
        this.aClass215_1058 = new Class215();
        this.aClass215_1059 = new Class215();
        this.aClass215_1060 = new Class215();
        this.aClass98_Sub22_1064 = new Class98_Sub22(4);
        this.anInt1065 = 0;
        this.anInt1066 = 0;
        this.aByte1067 = 0;
        this.aClass98_Sub22_1068 = new Class98_Sub22(8);
    }
    
    static {
        Class135.anInt1051 = 0;
        Class135.aBoolean1052 = true;
        Class135.aClass79_1054 = new Class79(128, 4);
        Class135.anIntArray1056 = new int[13];
        Class135.aShort1057 = 320;
    }
}
