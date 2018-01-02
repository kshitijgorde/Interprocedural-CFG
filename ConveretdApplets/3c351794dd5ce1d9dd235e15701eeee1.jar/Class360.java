import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class360
{
    private Class98_Sub22_Sub2 aClass98_Sub22_Sub2_3063;
    static String aString3064;
    private float[] aFloatArray3065;
    private Interface16 anInterface16_3066;
    private Class104 aClass104_3067;
    private Class104 aClass104_3068;
    private Class104 aClass104_3069;
    static long aLong3070;
    private int anInt3071;
    private Class246_Sub4_Sub2[][] aClass246_Sub4_Sub2ArrayArray3072;
    private Class246_Sub4_Sub2[][] aClass246_Sub4_Sub2ArrayArray3073;
    private int[] anIntArray3074;
    private int[] anIntArray3075;
    private int anInt3076;
    private int[] anIntArray3077;
    
    private final void method3904(final int n, final ha_Sub1 ha_Sub1, final byte b) {
        try {
            OpenGL.glGetFloatv(2982, this.aFloatArray3065, 0);
            final float n2 = this.aFloatArray3065[0];
            final float n3 = this.aFloatArray3065[4];
            final float n4 = this.aFloatArray3065[8];
            final float n5 = this.aFloatArray3065[1];
            final float n6 = this.aFloatArray3065[5];
            final float n7 = this.aFloatArray3065[9];
            final float n8 = n2 + n5;
            final float n9 = n6 + n3;
            final float n10 = n7 + n4;
            final float n11 = n2 - n5;
            final float n12 = -n6 + n3;
            if (b == 52) {
                final float n13 = n4 - n7;
                final float n14 = n5 - n2;
                final float n15 = n6 - n3;
                final float n16 = -n4 + n7;
                this.aClass98_Sub22_Sub2_3063.anInt3991 = 0;
                if (ha_Sub1.aBoolean4397) {
                    for (int n17 = -1 + n; ~n17 <= -1; --n17) {
                        final int n18 = (this.anIntArray3075[n17] <= 64) ? this.anIntArray3075[n17] : 64;
                        if (n18 > 0) {
                            for (int n19 = -1 + n18; ~n19 <= -1; --n19) {
                                final Class246_Sub4_Sub2 class246_Sub4_Sub2 = this.aClass246_Sub4_Sub2ArrayArray3072[n17][n19];
                                final int anInt6178 = class246_Sub4_Sub2.anInt6178;
                                final byte b2 = (byte)(anInt6178 >> 1656669520);
                                final byte b3 = (byte)(anInt6178 >> -1272463480);
                                final byte b4 = (byte)anInt6178;
                                final byte b5 = (byte)(anInt6178 >>> 1975843128);
                                final float n20 = class246_Sub4_Sub2.anInt6176 >> -454584212;
                                final float n21 = class246_Sub4_Sub2.anInt6177 >> -649195284;
                                final float n22 = class246_Sub4_Sub2.anInt6175 >> 78882380;
                                final int n23 = class246_Sub4_Sub2.anInt6179 >> -2029434900;
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-118), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-123), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-96), -n23 * n8 + n20);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-1), n9 * -n23 + n21);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-96), n22 + n10 * -n23);
                                this.aClass98_Sub22_Sub2_3063.method1194(b2, -115);
                                this.aClass98_Sub22_Sub2_3063.method1194(b3, -36);
                                this.aClass98_Sub22_Sub2_3063.method1194(b4, -90);
                                this.aClass98_Sub22_Sub2_3063.method1194(b5, 118);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)81, 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-121), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)73, n23 * n11 + n20);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)81, n12 * n23 + n21);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-102), n13 * n23 + n22);
                                this.aClass98_Sub22_Sub2_3063.method1194(b2, 73);
                                this.aClass98_Sub22_Sub2_3063.method1194(b3, b - 127);
                                this.aClass98_Sub22_Sub2_3063.method1194(b4, b ^ 0xFFFFFF85);
                                this.aClass98_Sub22_Sub2_3063.method1194(b5, 106);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-104), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-98), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)66, n23 * n8 + n20);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)45, n9 * n23 + n21);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)126, n22 + n10 * n23);
                                this.aClass98_Sub22_Sub2_3063.method1194(b2, b ^ 0xFFFFFFAD);
                                this.aClass98_Sub22_Sub2_3063.method1194(b3, -99);
                                this.aClass98_Sub22_Sub2_3063.method1194(b4, 103);
                                this.aClass98_Sub22_Sub2_3063.method1194(b5, -40);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-111), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)(-126), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)88, n23 * n14 + n20);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)86, n23 * n15 + n21);
                                this.aClass98_Sub22_Sub2_3063.method1264((byte)103, n23 * n16 + n22);
                                this.aClass98_Sub22_Sub2_3063.method1194(b2, -110);
                                this.aClass98_Sub22_Sub2_3063.method1194(b3, 50);
                                this.aClass98_Sub22_Sub2_3063.method1194(b4, 126);
                                this.aClass98_Sub22_Sub2_3063.method1194(b5, 124);
                            }
                            if (~this.anIntArray3075[n17] < -65) {
                                final int n24 = -65 + this.anIntArray3075[n17];
                                for (int n25 = this.anIntArray3077[n24] - 1; ~n25 <= -1; --n25) {
                                    final Class246_Sub4_Sub2 class246_Sub4_Sub3 = this.aClass246_Sub4_Sub2ArrayArray3073[n24][n25];
                                    final int anInt6179 = class246_Sub4_Sub3.anInt6178;
                                    final byte b6 = (byte)(anInt6179 >> -1694259824);
                                    final byte b7 = (byte)(anInt6179 >> 451522632);
                                    final byte b8 = (byte)anInt6179;
                                    final byte b9 = (byte)(anInt6179 >>> -310568296);
                                    final float n26 = class246_Sub4_Sub3.anInt6176 >> -901688052;
                                    final float n27 = class246_Sub4_Sub3.anInt6177 >> 2000215468;
                                    final float n28 = class246_Sub4_Sub3.anInt6175 >> 1165920652;
                                    final int n29 = class246_Sub4_Sub3.anInt6179 >> -627874868;
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)95, 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)30, 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-113), n26 + n8 * -n29);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)43, -n29 * n9 + n27);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)32, n10 * -n29 + n28);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b6, -46);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b7, -59);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b8, b ^ 0x9);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b9, 78);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)44, 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-113), 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-125), n26 + n11 * n29);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)29, n12 * n29 + n27);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-100), n13 * n29 + n28);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b6, 66);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b7, b ^ 0xFFFFFFA7);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b8, -117);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b9, b + 44);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-117), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)115, 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)9, n26 + n8 * n29);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-103), n27 + n29 * n9);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)57, n28 + n29 * n10);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b6, 118);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b7, 90);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b8, -61);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b9, b + 71);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)30, 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)(-127), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)51, n26 + n14 * n29);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)42, n29 * n15 + n27);
                                    this.aClass98_Sub22_Sub2_3063.method1264((byte)80, n28 + n16 * n29);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b6, 43);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b7, b ^ 0xFFFFFFF7);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b8, 85);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b9, b ^ 0xA);
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n30 = n - 1; ~n30 <= -1; --n30) {
                        final int n31 = (this.anIntArray3075[n30] > 64) ? 64 : this.anIntArray3075[n30];
                        if (~n31 < -1) {
                            for (int n32 = n31 - 1; ~n32 <= -1; --n32) {
                                final Class246_Sub4_Sub2 class246_Sub4_Sub4 = this.aClass246_Sub4_Sub2ArrayArray3072[n30][n32];
                                final int anInt6180 = class246_Sub4_Sub4.anInt6178;
                                final byte b10 = (byte)(anInt6180 >> 394032304);
                                final byte b11 = (byte)(anInt6180 >> -1148711800);
                                final byte b12 = (byte)anInt6180;
                                final byte b13 = (byte)(anInt6180 >>> -634651496);
                                final float n33 = class246_Sub4_Sub4.anInt6176 >> 1288887212;
                                final float n34 = class246_Sub4_Sub4.anInt6177 >> -1848026004;
                                final float n35 = class246_Sub4_Sub4.anInt6175 >> -638538612;
                                final int n36 = class246_Sub4_Sub4.anInt6179 >> -1389801652;
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n33 + n8 * -n36);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n34 + n9 * -n36);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n35 + -n36 * n10);
                                this.aClass98_Sub22_Sub2_3063.method1194(b10, b - 94);
                                this.aClass98_Sub22_Sub2_3063.method1194(b11, b ^ 0x65);
                                this.aClass98_Sub22_Sub2_3063.method1194(b12, 120);
                                this.aClass98_Sub22_Sub2_3063.method1194(b13, -102);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n36 * n11 + n33);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n36 * n12 + n34);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n36 * n13 + n35);
                                this.aClass98_Sub22_Sub2_3063.method1194(b10, b ^ 0xFFFFFF90);
                                this.aClass98_Sub22_Sub2_3063.method1194(b11, b ^ 0xFFFFFF83);
                                this.aClass98_Sub22_Sub2_3063.method1194(b12, b - 164);
                                this.aClass98_Sub22_Sub2_3063.method1194(b13, -124);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n33 + n8 * n36);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n9 * n36 + n34);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n35 + n36 * n10);
                                this.aClass98_Sub22_Sub2_3063.method1194(b10, b ^ 0x4E);
                                this.aClass98_Sub22_Sub2_3063.method1194(b11, 64);
                                this.aClass98_Sub22_Sub2_3063.method1194(b12, 57);
                                this.aClass98_Sub22_Sub2_3063.method1194(b13, -41);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n33 + n14 * n36);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n15 * n36 + n34);
                                this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n16 * n36 + n35);
                                this.aClass98_Sub22_Sub2_3063.method1194(b10, 121);
                                this.aClass98_Sub22_Sub2_3063.method1194(b11, b - 115);
                                this.aClass98_Sub22_Sub2_3063.method1194(b12, -52);
                                this.aClass98_Sub22_Sub2_3063.method1194(b13, -35);
                            }
                            if (~this.anIntArray3075[n30] < -65) {
                                final int n37 = -64 + (this.anIntArray3075[n30] - 1);
                                for (int i = this.anIntArray3077[n37] - 1; i >= 0; --i) {
                                    final Class246_Sub4_Sub2 class246_Sub4_Sub5 = this.aClass246_Sub4_Sub2ArrayArray3073[n37][i];
                                    final int anInt6181 = class246_Sub4_Sub5.anInt6178;
                                    final byte b14 = (byte)(anInt6181 >> 117536784);
                                    final byte b15 = (byte)(anInt6181 >> 1635536040);
                                    final byte b16 = (byte)anInt6181;
                                    final byte b17 = (byte)(anInt6181 >>> -100916008);
                                    final float n38 = class246_Sub4_Sub5.anInt6176 >> -1764237876;
                                    final float n39 = class246_Sub4_Sub5.anInt6177 >> 1759716140;
                                    final float n40 = class246_Sub4_Sub5.anInt6175 >> 88626700;
                                    final int n41 = class246_Sub4_Sub5.anInt6179 >> -1925490324;
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n8 * -n41 + n38);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n9 * -n41 + n39);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n10 * -n41 + n40);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b14, 79);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b15, -56);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b16, -106);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b17, -118);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n41 * n11 + n38);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n12 * n41 + n39);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n40 + n41 * n13);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b14, b ^ 0x6F);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b15, -55);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b16, b ^ 0x1A);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b17, -41);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n38 + n41 * n8);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n39 + n9 * n41);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n10 * n41 + n40);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b14, 43);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b15, 49);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b16, -51);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b17, b - 155);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 0.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), 1.0f);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n38 + n41 * n14);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n39 + n15 * n41);
                                    this.aClass98_Sub22_Sub2_3063.method1265((byte)(-52), n41 * n16 + n40);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b14, -102);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b15, b ^ 0xFFFFFFE4);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b16, b - 136);
                                    this.aClass98_Sub22_Sub2_3063.method1194(b17, 124);
                                }
                            }
                        }
                    }
                }
                if (~this.aClass98_Sub22_Sub2_3063.anInt3991 != -1) {
                    this.anInterface16_3066.method54(this.aClass98_Sub22_Sub2_3063.anInt3991, 7896, this.aClass98_Sub22_Sub2_3063.aByteArray3992, 24);
                    ha_Sub1.method1868(this.aClass104_3068, null, this.aClass104_3067, this.aClass104_3069, 0);
                    ha_Sub1.method1910(7, this.aClass98_Sub22_Sub2_3063.anInt3991 / 24, false, 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.B(" + n + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final boolean method3905(final int n, final int n2, final int n3) {
        try {
            return (n2 & 0x8000) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method3906(final Class242 class242, final int n, final int n2, final ha_Sub1 ha_Sub1) {
        try {
            if (ha_Sub1.aClass111_Sub1_4348 != null) {
                if (~n2 > -1) {
                    this.method3908(ha_Sub1, 5228);
                }
                else {
                    this.method3911(ha_Sub1, true, n2);
                }
                final float aFloat4684 = ha_Sub1.aClass111_Sub1_4348.aFloat4684;
                final float aFloat4685 = ha_Sub1.aClass111_Sub1_4348.aFloat4676;
                final float aFloat4686 = ha_Sub1.aClass111_Sub1_4348.aFloat4673;
                final float aFloat4687 = ha_Sub1.aClass111_Sub1_4348.aFloat4677;
                try {
                    int n3 = 0;
                    int n4 = Integer.MAX_VALUE;
                    int n5 = 0;
                    final Class246_Sub4 aClass246_Sub4_3028 = class242.aClass358_1850.aClass246_Sub4_3028;
                    if (n > -40) {
                        return;
                    }
                    for (Class246_Sub4 class246_Sub4 = aClass246_Sub4_3028.aClass246_Sub4_5091; class246_Sub4 != aClass246_Sub4_3028; class246_Sub4 = class246_Sub4.aClass246_Sub4_5091) {
                        final Class246_Sub4_Sub2 class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class246_Sub4;
                        final int n6 = (int)((class246_Sub4_Sub2.anInt6175 >> 1098990956) * aFloat4686 + ((class246_Sub4_Sub2.anInt6176 >> -1531981396) * aFloat4684 + (class246_Sub4_Sub2.anInt6177 >> -572896980) * aFloat4685) + aFloat4687);
                        this.anIntArray3074[n3++] = n6;
                        if (~n4 < ~n6) {
                            n4 = n6;
                        }
                        if (n6 > n5) {
                            n5 = n6;
                        }
                    }
                    int i = -n4 + n5;
                    int n7;
                    if (i + 2 > 1600) {
                        n7 = 1 - (-Class48_Sub2_Sub1.method474(i, (byte)31) + this.anInt3071);
                        i = (i >> n7) + 2;
                    }
                    else {
                        i += 2;
                        n7 = 0;
                    }
                    int n8 = 0;
                    Class246_Sub4 class246_Sub5 = aClass246_Sub4_3028.aClass246_Sub4_5091;
                    int anInt6180 = -2;
                    boolean aBoolean6174 = true;
                    int n9 = 1;
                    while (class246_Sub5 != aClass246_Sub4_3028) {
                        this.anInt3076 = 0;
                        for (int n10 = 0; i > n10; ++n10) {
                            this.anIntArray3075[n10] = 0;
                        }
                        for (int j = 0; j < 64; ++j) {
                            this.anIntArray3077[j] = 0;
                        }
                        while (aClass246_Sub4_3028 != class246_Sub5) {
                            final Class246_Sub4_Sub2 class246_Sub4_Sub3 = (Class246_Sub4_Sub2)class246_Sub5;
                            if (n9 != 0) {
                                anInt6180 = class246_Sub4_Sub3.anInt6180;
                                aBoolean6174 = class246_Sub4_Sub3.aBoolean6174;
                                n9 = 0;
                            }
                            if (~n8 < -1 && (~class246_Sub4_Sub3.anInt6180 != ~anInt6180 || !aBoolean6174 == class246_Sub4_Sub3.aBoolean6174)) {
                                n9 = 1;
                                break;
                            }
                            final int n11 = -n4 + this.anIntArray3074[n8++] >> n7;
                            Label_0582: {
                                if (n11 < 1600) {
                                    if (this.anIntArray3075[n11] >= 64) {
                                        if (this.anIntArray3075[n11] == 64) {
                                            if (this.anInt3076 == 64) {
                                                break Label_0582;
                                            }
                                            final int[] anIntArray3075 = this.anIntArray3075;
                                            final int n12 = n11;
                                            anIntArray3075[n12] += 1 - -(this.anInt3076++);
                                        }
                                        this.aClass246_Sub4_Sub2ArrayArray3073[this.anIntArray3075[n11] - 65][this.anIntArray3077[-1 + this.anIntArray3075[n11] - 64]++] = class246_Sub4_Sub3;
                                    }
                                    else {
                                        this.aClass246_Sub4_Sub2ArrayArray3072[n11][this.anIntArray3075[n11]++] = class246_Sub4_Sub3;
                                    }
                                }
                            }
                            class246_Sub5 = class246_Sub5.aClass246_Sub4_5091;
                        }
                        if (~anInt6180 > -1) {
                            ha_Sub1.method1834(-43, -1);
                        }
                        else {
                            ha_Sub1.method1834(-84, anInt6180);
                        }
                        if (aBoolean6174 && Class49.aFloat416 != ha_Sub1.aFloat4413) {
                            ha_Sub1.xa(Class49.aFloat416);
                        }
                        else if (ha_Sub1.aFloat4413 != 1.0f) {
                            ha_Sub1.xa(1.0f);
                        }
                        this.method3904(i, ha_Sub1, (byte)52);
                    }
                }
                catch (Exception ex2) {}
                this.method3907(ha_Sub1, -124);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.J(" + ((class242 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3907(final ha_Sub1 ha_Sub1, final int n) {
        try {
            ha_Sub1.method1875((byte)(-127), true);
            OpenGL.glEnable(16384);
            if (n >= -5) {
                Class360.aLong3070 = -102L;
            }
            OpenGL.glEnable(16385);
            if (ha_Sub1.aFloat4413 != Class49.aFloat416) {
                ha_Sub1.xa(Class49.aFloat416);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.C(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3908(final ha_Sub1 ha_Sub1, final int n) {
        try {
            Class49.aFloat416 = ha_Sub1.aFloat4413;
            ha_Sub1.method1861(19330);
            if (n != 5228) {
                this.aClass246_Sub4_Sub2ArrayArray3072 = null;
            }
            OpenGL.glDisable(16384);
            OpenGL.glDisable(16385);
            ha_Sub1.method1875((byte)(-127), false);
            OpenGL.glNormal3f(0.0f, -1.0f, 0.0f);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.D(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final boolean method3909(final int n, final int n2) {
        try {
            if (n != -4) {
                Class360.aString3064 = null;
            }
            return (n2 >= 0 && ~n2 >= -4) || n2 == 9;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.H(" + n + ',' + n2 + ')');
        }
    }
    
    static final Class293 method3910(final boolean b, final Class293 class293) {
        try {
            if (~class293.anInt2234 != 0x0) {
                return Class159.method2509(class293.anInt2234, -9820);
            }
            if (!b) {
                return null;
            }
            final int n = class293.anInt2248 >>> -1305595088;
            final Class61 class294 = new Class61(Class116.aClass377_964);
            for (Class98_Sub18 class98_Sub18 = (Class98_Sub18)class294.method541(0); class98_Sub18 != null; class98_Sub18 = (Class98_Sub18)class294.method539(2)) {
                if (~class98_Sub18.anInt3945 == ~n) {
                    return Class159.method2509((int)class98_Sub18.aLong832, -9820);
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.A(" + b + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3911(final ha_Sub1 ha_Sub1, final boolean b, final int n) {
        try {
            Class49.aFloat416 = ha_Sub1.aFloat4413;
            ha_Sub1.method1890(n, b);
            ha_Sub1.method1901((byte)(-35));
            OpenGL.glDisable(16384);
            OpenGL.glDisable(16385);
            ha_Sub1.method1875((byte)(-125), false);
            OpenGL.glNormal3f(0.0f, -1.0f, 0.0f);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.G(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    final void method3912(final ha_Sub1 ha_Sub1, final int n) {
        try {
            this.anInterface16_3066 = ha_Sub1.method1878(n, true, 24, -51, null);
            this.aClass104_3067 = new Class104(this.anInterface16_3066, 5126, 2, 0);
            this.aClass104_3069 = new Class104(this.anInterface16_3066, 5126, 3, 8);
            this.aClass104_3068 = new Class104(this.anInterface16_3066, 5121, 4, 20);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.F(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method3913(final byte b) {
        try {
            Class360.aString3064 = null;
            if (b < 25) {
                Class360.aLong3070 = 55L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vq.I(" + b + ')');
        }
    }
    
    Class360() {
        this.aFloatArray3065 = new float[16];
        this.aClass98_Sub22_Sub2_3063 = new Class98_Sub22_Sub2(786336);
        this.anInt3071 = Class48_Sub2_Sub1.method474(1600, (byte)31);
        this.aClass246_Sub4_Sub2ArrayArray3072 = new Class246_Sub4_Sub2[1600][64];
        this.anIntArray3075 = new int[1600];
        this.anIntArray3077 = new int[64];
        this.anIntArray3074 = new int[8191];
        this.aClass246_Sub4_Sub2ArrayArray3073 = new Class246_Sub4_Sub2[64][768];
        this.anInt3076 = 0;
    }
    
    static {
        Class360.aString3064 = "";
    }
}
