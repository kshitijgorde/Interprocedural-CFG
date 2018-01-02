// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub33 extends Class98_Sub10
{
    private int[] anIntArray5723;
    private int[][] anIntArrayArray5724;
    
    public Class98_Sub10_Sub33() {
        super(1, false);
        this.anIntArray5723 = new int[257];
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                this.anIntArrayArray5724 = null;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] method2829 = this.method1000(n2, 0, 0);
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                for (int i = 0; i < Class25.anInt268; ++i) {
                    int n3 = method2829[i] >> -1995832092;
                    if (n3 < 0) {
                        n3 = 0;
                    }
                    if (~n3 < -257) {
                        n3 = 256;
                    }
                    final int n4 = this.anIntArray5723[n3];
                    array[i] = Class202.method2702(4080, n4 >> -604582996);
                    array2[i] = Class202.method2702(n4 >> 1240244164, 4080);
                    array3[i] = Class202.method2702(n4 << 1317982468, 4080);
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1099(final int n, final int anInt6054, final byte b) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -123, 13);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.D(" + n + ',' + anInt6054 + ',' + b + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            if (this.anIntArrayArray5724 == null) {
                this.method1100(10, 1);
            }
            if (b == 66) {
                this.method1102(3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.I(" + b + ')');
        }
    }
    
    private final void method1100(final int n, final int n2) {
        try {
            if (~n2 != -1) {
                if (n2 != 1) {
                    if (~n2 != 0xFFFFFFFD) {
                        if (n2 != 3) {
                            if (n2 != 4) {
                                if (n2 != 5) {
                                    if (n2 != 6) {
                                        throw new RuntimeException("Invalid gradient preset");
                                    }
                                    this.anIntArrayArray5724 = new int[4][4];
                                    this.anIntArrayArray5724[0][0] = 2048;
                                    this.anIntArrayArray5724[0][3] = 0;
                                    this.anIntArrayArray5724[0][1] = 0;
                                    this.anIntArrayArray5724[0][2] = 4096;
                                    this.anIntArrayArray5724[1][3] = 0;
                                    this.anIntArrayArray5724[1][2] = 4096;
                                    this.anIntArrayArray5724[1][1] = 4096;
                                    this.anIntArrayArray5724[1][0] = 2867;
                                    this.anIntArrayArray5724[2][3] = 0;
                                    this.anIntArrayArray5724[2][0] = 3276;
                                    this.anIntArrayArray5724[2][1] = 4096;
                                    this.anIntArrayArray5724[2][2] = 4096;
                                    this.anIntArrayArray5724[3][1] = 4096;
                                    this.anIntArrayArray5724[3][3] = 0;
                                    this.anIntArrayArray5724[3][0] = 4096;
                                    this.anIntArrayArray5724[3][2] = 0;
                                }
                                else {
                                    this.anIntArrayArray5724 = new int[16][4];
                                    this.anIntArrayArray5724[0][0] = 0;
                                    this.anIntArrayArray5724[0][1] = 80;
                                    this.anIntArrayArray5724[0][3] = 321;
                                    this.anIntArrayArray5724[0][2] = 192;
                                    this.anIntArrayArray5724[1][2] = 449;
                                    this.anIntArrayArray5724[1][1] = 321;
                                    this.anIntArrayArray5724[1][3] = 562;
                                    this.anIntArrayArray5724[1][0] = 155;
                                    this.anIntArrayArray5724[2][3] = 803;
                                    this.anIntArrayArray5724[2][0] = 389;
                                    this.anIntArrayArray5724[2][1] = 578;
                                    this.anIntArrayArray5724[2][2] = 690;
                                    this.anIntArrayArray5724[3][1] = 947;
                                    this.anIntArrayArray5724[3][3] = 1140;
                                    this.anIntArrayArray5724[3][0] = 671;
                                    this.anIntArrayArray5724[3][2] = 995;
                                    this.anIntArrayArray5724[4][3] = 1509;
                                    this.anIntArrayArray5724[4][2] = 1397;
                                    this.anIntArrayArray5724[4][1] = 1285;
                                    this.anIntArrayArray5724[4][0] = 897;
                                    this.anIntArrayArray5724[5][2] = 1429;
                                    this.anIntArrayArray5724[5][1] = 1525;
                                    this.anIntArrayArray5724[5][0] = 1175;
                                    this.anIntArrayArray5724[5][3] = 1413;
                                    this.anIntArrayArray5724[6][2] = 1461;
                                    this.anIntArrayArray5724[6][1] = 1734;
                                    this.anIntArrayArray5724[6][0] = 1368;
                                    this.anIntArrayArray5724[6][3] = 1333;
                                    this.anIntArrayArray5724[7][0] = 1507;
                                    this.anIntArrayArray5724[7][3] = 1702;
                                    this.anIntArrayArray5724[7][2] = 1525;
                                    this.anIntArrayArray5724[7][1] = 1413;
                                    this.anIntArrayArray5724[8][2] = 1590;
                                    this.anIntArrayArray5724[8][1] = 1108;
                                    this.anIntArrayArray5724[8][0] = 1736;
                                    this.anIntArrayArray5724[8][3] = 2056;
                                    this.anIntArrayArray5724[9][2] = 2056;
                                    this.anIntArrayArray5724[9][0] = 2088;
                                    this.anIntArrayArray5724[9][3] = 2666;
                                    this.anIntArrayArray5724[9][1] = 1766;
                                    this.anIntArrayArray5724[10][0] = 2355;
                                    this.anIntArrayArray5724[10][2] = 2586;
                                    this.anIntArrayArray5724[10][1] = 2409;
                                    this.anIntArrayArray5724[10][3] = 3276;
                                    this.anIntArrayArray5724[11][2] = 3148;
                                    this.anIntArrayArray5724[11][0] = 2691;
                                    this.anIntArrayArray5724[11][1] = 3116;
                                    this.anIntArrayArray5724[11][3] = 3228;
                                    this.anIntArrayArray5724[12][2] = 3710;
                                    this.anIntArrayArray5724[12][3] = 3196;
                                    this.anIntArrayArray5724[12][1] = 3806;
                                    this.anIntArrayArray5724[12][0] = 3031;
                                    this.anIntArrayArray5724[13][3] = 3019;
                                    this.anIntArrayArray5724[13][2] = 3421;
                                    this.anIntArrayArray5724[13][1] = 3437;
                                    this.anIntArrayArray5724[13][0] = 3522;
                                    this.anIntArrayArray5724[14][2] = 3148;
                                    this.anIntArrayArray5724[14][0] = 3727;
                                    this.anIntArrayArray5724[14][3] = 3228;
                                    this.anIntArrayArray5724[14][1] = 3116;
                                    this.anIntArrayArray5724[15][2] = 2505;
                                    this.anIntArrayArray5724[15][3] = 2746;
                                    this.anIntArrayArray5724[15][0] = 4096;
                                    this.anIntArrayArray5724[15][1] = 2377;
                                }
                            }
                            else {
                                this.anIntArrayArray5724 = new int[6][4];
                                this.anIntArrayArray5724[0][2] = 0;
                                this.anIntArrayArray5724[0][1] = 0;
                                this.anIntArrayArray5724[0][3] = 0;
                                this.anIntArrayArray5724[0][0] = 0;
                                this.anIntArrayArray5724[1][2] = 0;
                                this.anIntArrayArray5724[1][1] = 0;
                                this.anIntArrayArray5724[1][3] = 1493;
                                this.anIntArrayArray5724[1][0] = 1843;
                                this.anIntArrayArray5724[2][1] = 0;
                                this.anIntArrayArray5724[2][0] = 2457;
                                this.anIntArrayArray5724[2][3] = 2939;
                                this.anIntArrayArray5724[2][2] = 0;
                                this.anIntArrayArray5724[3][2] = 1124;
                                this.anIntArrayArray5724[3][0] = 2781;
                                this.anIntArrayArray5724[3][3] = 3565;
                                this.anIntArrayArray5724[3][1] = 0;
                                this.anIntArrayArray5724[4][0] = 3481;
                                this.anIntArrayArray5724[4][3] = 4031;
                                this.anIntArrayArray5724[4][2] = 3084;
                                this.anIntArrayArray5724[4][1] = 546;
                                this.anIntArrayArray5724[5][3] = 4096;
                                this.anIntArrayArray5724[5][1] = 4096;
                                this.anIntArrayArray5724[5][0] = 4096;
                                this.anIntArrayArray5724[5][2] = 4096;
                            }
                        }
                        else {
                            this.anIntArrayArray5724 = new int[7][4];
                            this.anIntArrayArray5724[0][2] = 0;
                            this.anIntArrayArray5724[0][3] = 4096;
                            this.anIntArrayArray5724[0][0] = 0;
                            this.anIntArrayArray5724[0][1] = 0;
                            this.anIntArrayArray5724[1][2] = 4096;
                            this.anIntArrayArray5724[1][0] = 663;
                            this.anIntArrayArray5724[1][1] = 0;
                            this.anIntArrayArray5724[1][3] = 4096;
                            this.anIntArrayArray5724[2][1] = 0;
                            this.anIntArrayArray5724[2][3] = 0;
                            this.anIntArrayArray5724[2][2] = 4096;
                            this.anIntArrayArray5724[2][0] = 1363;
                            this.anIntArrayArray5724[3][0] = 2048;
                            this.anIntArrayArray5724[3][1] = 4096;
                            this.anIntArrayArray5724[3][3] = 0;
                            this.anIntArrayArray5724[3][2] = 4096;
                            this.anIntArrayArray5724[4][1] = 4096;
                            this.anIntArrayArray5724[4][0] = 2727;
                            this.anIntArrayArray5724[4][2] = 0;
                            this.anIntArrayArray5724[4][3] = 0;
                            this.anIntArrayArray5724[5][0] = 3411;
                            this.anIntArrayArray5724[5][2] = 0;
                            this.anIntArrayArray5724[5][3] = 4096;
                            this.anIntArrayArray5724[5][1] = 4096;
                            this.anIntArrayArray5724[6][2] = 0;
                            this.anIntArrayArray5724[6][0] = 4096;
                            this.anIntArrayArray5724[6][3] = 4096;
                            this.anIntArrayArray5724[6][1] = 0;
                        }
                    }
                    else {
                        this.anIntArrayArray5724 = new int[8][4];
                        this.anIntArrayArray5724[0][3] = 2361;
                        this.anIntArrayArray5724[0][0] = 0;
                        this.anIntArrayArray5724[0][1] = 2650;
                        this.anIntArrayArray5724[0][2] = 2602;
                        this.anIntArrayArray5724[1][3] = 1558;
                        this.anIntArrayArray5724[1][2] = 1799;
                        this.anIntArrayArray5724[1][1] = 2313;
                        this.anIntArrayArray5724[1][0] = 2867;
                        this.anIntArrayArray5724[2][2] = 1734;
                        this.anIntArrayArray5724[2][3] = 1413;
                        this.anIntArrayArray5724[2][1] = 2618;
                        this.anIntArrayArray5724[2][0] = 3072;
                        this.anIntArrayArray5724[3][1] = 2296;
                        this.anIntArrayArray5724[3][3] = 947;
                        this.anIntArrayArray5724[3][0] = 3276;
                        this.anIntArrayArray5724[3][2] = 1220;
                        this.anIntArrayArray5724[4][1] = 2072;
                        this.anIntArrayArray5724[4][3] = 722;
                        this.anIntArrayArray5724[4][0] = 3481;
                        this.anIntArrayArray5724[4][2] = 963;
                        this.anIntArrayArray5724[5][2] = 2152;
                        this.anIntArrayArray5724[5][0] = 3686;
                        this.anIntArrayArray5724[5][1] = 2730;
                        this.anIntArrayArray5724[5][3] = 1766;
                        this.anIntArrayArray5724[6][2] = 1060;
                        this.anIntArrayArray5724[6][0] = 3891;
                        this.anIntArrayArray5724[6][1] = 2232;
                        this.anIntArrayArray5724[6][3] = 915;
                        this.anIntArrayArray5724[7][0] = 4096;
                        this.anIntArrayArray5724[7][1] = 1686;
                        this.anIntArrayArray5724[7][2] = 1413;
                        this.anIntArrayArray5724[7][3] = 1140;
                    }
                }
                else {
                    this.anIntArrayArray5724 = new int[2][4];
                    this.anIntArrayArray5724[0][0] = 0;
                    this.anIntArrayArray5724[0][1] = 0;
                    this.anIntArrayArray5724[0][3] = 0;
                    this.anIntArrayArray5724[0][2] = 0;
                    this.anIntArrayArray5724[1][2] = 4096;
                    this.anIntArrayArray5724[1][0] = 4096;
                    this.anIntArrayArray5724[1][1] = 4096;
                    this.anIntArrayArray5724[1][3] = 4096;
                }
            }
            if (n != 10) {
                this.anIntArrayArray5724 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.E(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1101(final int n, final Class207 class207) {
        try {
            if (n != -11286) {
                return -69;
            }
            int n2 = 0;
            if (class207.method2742(n + 11209, aa.anInt51)) {
                ++n2;
            }
            if (class207.method2742(-35, Class140.anInt3243)) {
                ++n2;
            }
            if (class207.method2742(-25, Class65.anInt503)) {
                ++n2;
            }
            if (class207.method2742(-104, Class260.anInt3259)) {
                ++n2;
            }
            if (class207.method2742(-118, Class251.anInt1916)) {
                ++n2;
            }
            if (class207.method2742(-64, Class319.anInt2706)) {
                ++n2;
            }
            if (class207.method2742(-42, Class76_Sub2.anInt3728)) {
                ++n2;
            }
            if (class207.method2742(n + 11196, Class226.anInt1706)) {
                ++n2;
            }
            if (class207.method2742(-40, Class39.anInt363)) {
                ++n2;
            }
            if (class207.method2742(-94, OutputStream_Sub1.anInt37)) {
                ++n2;
            }
            if (class207.method2742(-89, Class243.anInt1852)) {
                ++n2;
            }
            if (class207.method2742(-89, Class98_Sub31_Sub4.anInt5860)) {
                ++n2;
            }
            if (class207.method2742(-111, Class75.anInt583)) {
                ++n2;
            }
            if (class207.method2742(-40, Class111_Sub2.anInt4695)) {
                ++n2;
            }
            if (class207.method2742(-108, Class264.anInt1972)) {
                ++n2;
            }
            if (class207.method2742(-38, Class76.anInt588)) {
                ++n2;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.B(" + n + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                this.anIntArray5723 = null;
            }
            if (~n == -1) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)4);
                if (unsignedByte != 0) {
                    this.method1100(10, unsignedByte);
                }
                else {
                    this.anIntArrayArray5724 = new int[class98_Sub22.readUnsignedByte((byte)(-107))][4];
                    for (int n2 = 0; ~this.anIntArrayArray5724.length < ~n2; ++n2) {
                        this.anIntArrayArray5724[n2][0] = class98_Sub22.readShort((byte)127);
                        this.anIntArrayArray5724[n2][1] = class98_Sub22.readUnsignedByte((byte)87) << -1113289820;
                        this.anIntArrayArray5724[n2][2] = class98_Sub22.readUnsignedByte((byte)10) << 801224932;
                        this.anIntArrayArray5724[n2][3] = class98_Sub22.readUnsignedByte((byte)(-124)) << 1705896548;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method1102(final int n) {
        try {
            final int length = this.anIntArrayArray5724.length;
            if (n == 3) {
                if (~length < -1) {
                    for (int n2 = 0; ~n2 > -258; ++n2) {
                        int n3 = 0;
                        final int n4 = n2 << 2145521988;
                        for (int n5 = 0; length > n5 && ~n4 <= ~this.anIntArrayArray5724[n5][0]; ++n5) {
                            ++n3;
                        }
                        int n8;
                        int n9;
                        int n10;
                        if (~length < ~n3) {
                            final int[] array = this.anIntArrayArray5724[n3];
                            if (n3 > 0) {
                                final int[] array2 = this.anIntArrayArray5724[n3 - 1];
                                final int n6 = (n4 + -array2[0] << 1394646828) / (array[0] - array2[0]);
                                final int n7 = -n6 + 4096;
                                n8 = array[2] * n6 + array2[2] * n7 >> -1318458580;
                                n9 = array2[3] * n7 + n6 * array[3] >> -1229726772;
                                n10 = n6 * array[1] - -(array2[1] * n7) >> 2142671148;
                            }
                            else {
                                n9 = array[3];
                                n8 = array[2];
                                n10 = array[1];
                            }
                        }
                        else {
                            final int[] array3 = this.anIntArrayArray5724[-1 + length];
                            n10 = array3[1];
                            n9 = array3[3];
                            n8 = array3[2];
                        }
                        int n11 = n10 >> 4;
                        int n12 = n8 >> 4;
                        int n13 = n9 >> 4;
                        if (~n12 > -1) {
                            n12 = 0;
                        }
                        else if (n12 > 255) {
                            n12 = 255;
                        }
                        if (n13 < 0) {
                            n13 = 0;
                        }
                        else if (~n13 < -256) {
                            n13 = 255;
                        }
                        if (~n11 > -1) {
                            n11 = 0;
                        }
                        else if (~n11 < -256) {
                            n11 = 255;
                        }
                        this.anIntArray5723[n2] = Class41.method366(n13, Class41.method366(n11 << 1113026160, n12 << -1375834648));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sg.F(" + n + ')');
        }
    }
}
