// 
// Decompiled by Procyon v0.5.30
// 

final class Class6
{
    private Class29 aClass29_98;
    private Class29 aClass29_99;
    private Class29 aClass29_100;
    private Class29 aClass29_101;
    private Class29 aClass29_102;
    private Class29 aClass29_103;
    private Class29 aClass29_104;
    private Class29 aClass29_105;
    private final int[] anIntArray106;
    private final int[] anIntArray107;
    private final int[] anIntArray108;
    private int anInt109;
    private int anInt110;
    private Class39 aClass39_111;
    private Class29 aClass29_112;
    int anInt113;
    int anInt114;
    private static int[] anIntArray115;
    private static int[] anIntArray116;
    private static int[] anIntArray117;
    private static final int[] anIntArray118;
    private static final int[] anIntArray119;
    private static final int[] anIntArray120;
    private static final int[] anIntArray121;
    private static final int[] anIntArray122;
    
    public static void method166() {
        Class6.anIntArray116 = new int[32768];
        for (int i = 0; i < 32768; ++i) {
            if (Math.random() > 0.5) {
                Class6.anIntArray116[i] = 1;
            }
            else {
                Class6.anIntArray116[i] = -1;
            }
        }
        Class6.anIntArray117 = new int[32768];
        for (int j = 0; j < 32768; ++j) {
            Class6.anIntArray117[j] = (int)(Math.sin(j / 5215.1903) * 16384.0);
        }
        Class6.anIntArray115 = new int[220500];
    }
    
    public int[] method167(final int n, final int n2) {
        for (int i = 0; i < n; ++i) {
            Class6.anIntArray115[i] = 0;
        }
        if (n2 < 10) {
            return Class6.anIntArray115;
        }
        final double n3 = n / (n2 + 0.0);
        this.aClass29_98.resetValues();
        this.aClass29_99.resetValues();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (this.aClass29_100 != null) {
            this.aClass29_100.resetValues();
            this.aClass29_101.resetValues();
            n4 = (int)((this.aClass29_100.anInt539 - this.aClass29_100.anInt538) * 32.768 / n3);
            n5 = (int)(this.aClass29_100.anInt538 * 32.768 / n3);
        }
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        if (this.aClass29_102 != null) {
            this.aClass29_102.resetValues();
            this.aClass29_103.resetValues();
            n7 = (int)((this.aClass29_102.anInt539 - this.aClass29_102.anInt538) * 32.768 / n3);
            n8 = (int)(this.aClass29_102.anInt538 * 32.768 / n3);
        }
        for (int j = 0; j < 5; ++j) {
            if (this.anIntArray106[j] != 0) {
                Class6.anIntArray118[j] = 0;
                Class6.anIntArray119[j] = (int)(this.anIntArray108[j] * n3);
                Class6.anIntArray120[j] = (this.anIntArray106[j] << 14) / 100;
                Class6.anIntArray121[j] = (int)((this.aClass29_98.anInt539 - this.aClass29_98.anInt538) * 32.768 * Math.pow(1.0057929410678534, this.anIntArray107[j]) / n3);
                Class6.anIntArray122[j] = (int)(this.aClass29_98.anInt538 * 32.768 / n3);
            }
        }
        for (int k = 0; k < n; ++k) {
            int method328 = this.aClass29_98.method328(n);
            int method329 = this.aClass29_99.method328(n);
            if (this.aClass29_100 != null) {
                final int method330 = this.aClass29_100.method328(n);
                method328 += this.method168(this.aClass29_101.method328(n), n6, this.aClass29_100.anInt540) >> 1;
                n6 += (method330 * n4 >> 16) + n5;
            }
            if (this.aClass29_102 != null) {
                final int method331 = this.aClass29_102.method328(n);
                method329 = method329 * ((this.method168(this.aClass29_103.method328(n), n9, this.aClass29_102.anInt540) >> 1) + 32768) >> 15;
                n9 += (method331 * n7 >> 16) + n8;
            }
            for (int l = 0; l < 5; ++l) {
                if (this.anIntArray106[l] != 0) {
                    final int n10 = k + Class6.anIntArray119[l];
                    if (n10 < n) {
                        final int[] anIntArray115 = Class6.anIntArray115;
                        final int n11 = n10;
                        anIntArray115[n11] += this.method168(method329 * Class6.anIntArray120[l] >> 15, Class6.anIntArray118[l], this.aClass29_98.anInt540);
                        final int[] anIntArray116 = Class6.anIntArray118;
                        final int n12 = l;
                        anIntArray116[n12] += (method328 * Class6.anIntArray121[l] >> 16) + Class6.anIntArray122[l];
                    }
                }
            }
        }
        if (this.aClass29_104 != null) {
            this.aClass29_104.resetValues();
            this.aClass29_105.resetValues();
            int n13 = 0;
            boolean b = true;
            for (int n14 = 0; n14 < n; ++n14) {
                final int method332 = this.aClass29_104.method328(n);
                final int method333 = this.aClass29_105.method328(n);
                int n15;
                if (b) {
                    n15 = this.aClass29_104.anInt538 + ((this.aClass29_104.anInt539 - this.aClass29_104.anInt538) * method332 >> 8);
                }
                else {
                    n15 = this.aClass29_104.anInt538 + ((this.aClass29_104.anInt539 - this.aClass29_104.anInt538) * method333 >> 8);
                }
                n13 += 256;
                if (n13 >= n15) {
                    n13 = 0;
                    b = !b;
                }
                if (b) {
                    Class6.anIntArray115[n14] = 0;
                }
            }
        }
        if (this.anInt109 > 0 && this.anInt110 > 0) {
            int n17;
            for (int n16 = n17 = (int)(this.anInt109 * n3); n17 < n; ++n17) {
                final int[] anIntArray117 = Class6.anIntArray115;
                final int n18 = n17;
                anIntArray117[n18] += Class6.anIntArray115[n17 - n16] * this.anInt110 / 100;
            }
        }
        if (this.aClass39_111.anIntArray665[0] > 0 || this.aClass39_111.anIntArray665[1] > 0) {
            this.aClass29_112.resetValues();
            int n19 = this.aClass29_112.method328(n + 1);
            int n20 = this.aClass39_111.method544(0, n19 / 65536.0f);
            int n21 = this.aClass39_111.method544(1, n19 / 65536.0f);
            if (n >= n20 + n21) {
                int n22 = 0;
                int n23 = n21;
                if (n23 > n - n20) {
                    n23 = n - n20;
                }
                while (n22 < n23) {
                    int n24 = Class6.anIntArray115[n22 + n20] * Class39.anInt672 >> 16;
                    for (int n25 = 0; n25 < n20; ++n25) {
                        n24 += Class6.anIntArray115[n22 + n20 - 1 - n25] * Class39.anIntArrayArray670[0][n25] >> 16;
                    }
                    for (int n26 = 0; n26 < n22; ++n26) {
                        n24 -= Class6.anIntArray115[n22 - 1 - n26] * Class39.anIntArrayArray670[1][n26] >> 16;
                    }
                    Class6.anIntArray115[n22] = n24;
                    n19 = this.aClass29_112.method328(n + 1);
                    ++n22;
                }
                int n28;
                final int n27 = n28 = 128;
                while (true) {
                    if (n28 > n - n20) {
                        n28 = n - n20;
                    }
                    while (n22 < n28) {
                        int n29 = Class6.anIntArray115[n22 + n20] * Class39.anInt672 >> 16;
                        for (int n30 = 0; n30 < n20; ++n30) {
                            n29 += Class6.anIntArray115[n22 + n20 - 1 - n30] * Class39.anIntArrayArray670[0][n30] >> 16;
                        }
                        for (int n31 = 0; n31 < n21; ++n31) {
                            n29 -= Class6.anIntArray115[n22 - 1 - n31] * Class39.anIntArrayArray670[1][n31] >> 16;
                        }
                        Class6.anIntArray115[n22] = n29;
                        n19 = this.aClass29_112.method328(n + 1);
                        ++n22;
                    }
                    if (n22 >= n - n20) {
                        break;
                    }
                    n20 = this.aClass39_111.method544(0, n19 / 65536.0f);
                    n21 = this.aClass39_111.method544(1, n19 / 65536.0f);
                    n28 += n27;
                }
                while (n22 < n) {
                    int n32 = 0;
                    for (int n33 = n22 + n20 - n; n33 < n20; ++n33) {
                        n32 += Class6.anIntArray115[n22 + n20 - 1 - n33] * Class39.anIntArrayArray670[0][n33] >> 16;
                    }
                    for (int n34 = 0; n34 < n21; ++n34) {
                        n32 -= Class6.anIntArray115[n22 - 1 - n34] * Class39.anIntArrayArray670[1][n34] >> 16;
                    }
                    Class6.anIntArray115[n22] = n32;
                    this.aClass29_112.method328(n + 1);
                    ++n22;
                }
            }
        }
        for (int n35 = 0; n35 < n; ++n35) {
            if (Class6.anIntArray115[n35] < -32768) {
                Class6.anIntArray115[n35] = -32768;
            }
            if (Class6.anIntArray115[n35] > 32767) {
                Class6.anIntArray115[n35] = 32767;
            }
        }
        return Class6.anIntArray115;
    }
    
    private int method168(final int n, final int n2, final int n3) {
        if (n3 == 1) {
            if ((n2 & 0x7FFF) < 16384) {
                return n;
            }
            return -n;
        }
        else {
            if (n3 == 2) {
                return Class6.anIntArray117[n2 & 0x7FFF] * n >> 14;
            }
            if (n3 == 3) {
                return ((n2 & 0x7FFF) * n >> 14) - n;
            }
            if (n3 == 4) {
                return Class6.anIntArray116[n2 / 2607 & 0x7FFF] * n;
            }
            return 0;
        }
    }
    
    public void method169(final Stream stream) {
        (this.aClass29_98 = new Class29()).method325(stream);
        (this.aClass29_99 = new Class29()).method325(stream);
        if (stream.readUnsignedByte() != 0) {
            --stream.currentOffset;
            (this.aClass29_100 = new Class29()).method325(stream);
            (this.aClass29_101 = new Class29()).method325(stream);
        }
        if (stream.readUnsignedByte() != 0) {
            --stream.currentOffset;
            (this.aClass29_102 = new Class29()).method325(stream);
            (this.aClass29_103 = new Class29()).method325(stream);
        }
        if (stream.readUnsignedByte() != 0) {
            --stream.currentOffset;
            (this.aClass29_104 = new Class29()).method325(stream);
            (this.aClass29_105 = new Class29()).method325(stream);
        }
        for (int i = 0; i < 10; ++i) {
            final int method422 = stream.method422();
            if (method422 == 0) {
                break;
            }
            this.anIntArray106[i] = method422;
            this.anIntArray107[i] = stream.method421();
            this.anIntArray108[i] = stream.method422();
        }
        this.anInt109 = stream.method422();
        this.anInt110 = stream.method422();
        this.anInt113 = stream.readUnsignedWord();
        this.anInt114 = stream.readUnsignedWord();
        this.aClass39_111 = new Class39();
        this.aClass29_112 = new Class29();
        this.aClass39_111.method545(stream, this.aClass29_112);
    }
    
    public Class6() {
        this.anIntArray106 = new int[5];
        this.anIntArray107 = new int[5];
        this.anIntArray108 = new int[5];
        this.anInt110 = 100;
        this.anInt113 = 500;
    }
    
    static {
        anIntArray118 = new int[5];
        anIntArray119 = new int[5];
        anIntArray120 = new int[5];
        anIntArray121 = new int[5];
        anIntArray122 = new int[5];
    }
}
