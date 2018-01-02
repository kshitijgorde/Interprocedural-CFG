import java.io.File;
import jaclib.memory.Buffer;
import jaclib.memory.Source;
import jaclib.memory.Stream;
import jaclib.memory.heap.NativeHeapBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub30 extends Class98
{
    int anInt4084;
    private Interface2_Impl1 anInterface2_Impl1_4085;
    int anInt4086;
    private ha_Sub3 aHa_Sub3_4087;
    static Class164 aClass164_4088;
    private NativeHeapBuffer aNativeHeapBuffer4089;
    int anInt4090;
    int anInt4091;
    float aFloat4092;
    private Stream aStream4093;
    static IncomingOpcode aClass58_4094;
    private int[] anIntArray4095;
    static float[] aFloatArray4096;
    private s_Sub2 aS_Sub2_4097;
    int anInt4098;
    static Class155[] aClass155Array4099;
    static Class aClass4100;
    
    static final void method1311(final boolean b, final boolean b2, final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2) {
        try {
            if (~Class359.anInt3058 > -401) {
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 == class246_Sub3_Sub4_Sub2_Sub2) {
                    if (Class98_Sub10_Sub9.aBoolean5585 && (0x10 & Class98_Sub4.anInt3826) != 0x0) {
                        Class293.method3470(false, true, 0L, Class336.anInt2823, 0, Class246_Sub3_Sub3.aString6156 + " -> <col=ffffff>" + Class309.aClass309_2623.method3615(Class374.anInt3159, (byte)25), false, 0, 57, class246_Sub3_Sub4_Sub2_Sub2.anInt6369, -1, false, Class287_Sub2.aString3272);
                    }
                }
                else if (b2) {
                    String s2;
                    if (class246_Sub3_Sub4_Sub2_Sub2.anInt6539 == 0) {
                        boolean b3 = true;
                        if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6535 != 0x0 && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6535 != 0x0) {
                            final int n = (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6535 >= class246_Sub3_Sub4_Sub2_Sub2.anInt6535) ? class246_Sub3_Sub4_Sub2_Sub2.anInt6535 : Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6535;
                            int n2 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519 - class246_Sub3_Sub4_Sub2_Sub2.anInt6519;
                            if (~n2 > -1) {
                                n2 = -n2;
                            }
                            if (n2 > n) {
                                b3 = false;
                            }
                        }
                        final String s = (Class4.aClass279_86 != Class64_Sub2.aClass279_3643) ? Class309.aClass309_2614.method3615(Class374.anInt3159, (byte)25) : Class309.aClass309_2616.method3615(Class374.anInt3159, (byte)25);
                        if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6519 > ~class246_Sub3_Sub4_Sub2_Sub2.anInt6542) {
                            s2 = class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true) + (b3 ? Class108.method1730(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519, class246_Sub3_Sub4_Sub2_Sub2.anInt6519, 16383) : "<col=ffffff>") + " (" + s + class246_Sub3_Sub4_Sub2_Sub2.anInt6519 + "+" + (class246_Sub3_Sub4_Sub2_Sub2.anInt6542 + -class246_Sub3_Sub4_Sub2_Sub2.anInt6519) + ")";
                        }
                        else {
                            s2 = class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true) + (b3 ? Class108.method1730(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519, class246_Sub3_Sub4_Sub2_Sub2.anInt6519, 16383) : "<col=ffffff>") + " (" + s + class246_Sub3_Sub4_Sub2_Sub2.anInt6519 + ")";
                        }
                    }
                    else if (class246_Sub3_Sub4_Sub2_Sub2.anInt6539 != -1) {
                        s2 = class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true) + " (" + Class309.aClass309_2615.method3615(Class374.anInt3159, (byte)25) + class246_Sub3_Sub4_Sub2_Sub2.anInt6539 + ")";
                    }
                    else {
                        s2 = class246_Sub3_Sub4_Sub2_Sub2.method3063(0, true);
                    }
                    if (Class98_Sub10_Sub9.aBoolean5585 && !b && (Class98_Sub4.anInt3826 & 0x8) != 0x0) {
                        Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub2.anInt6369, Class336.anInt2823, 0, Class246_Sub3_Sub3.aString6156 + " -> <col=ffffff>" + s2, false, 0, 5, class246_Sub3_Sub4_Sub2_Sub2.anInt6369, -1, false, Class287_Sub2.aString3272);
                    }
                    if (!b) {
                        for (int n3 = 7; ~n3 <= -1; --n3) {
                            if (Class269.aStringArray2021[n3] != null) {
                                short n4 = 0;
                                if (s_Sub1.aClass279_5197 == Class4.aClass279_86 && Class269.aStringArray2021[n3].equalsIgnoreCase(Class309.aClass309_2609.method3615(Class374.anInt3159, (byte)25))) {
                                    if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519 > ~class246_Sub3_Sub4_Sub2_Sub2.anInt6519) {
                                        n4 = 2000;
                                    }
                                    if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6528 != 0 && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6528 != -1) {
                                        if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6528 == ~class246_Sub3_Sub4_Sub2_Sub2.anInt6528) {
                                            n4 = 2000;
                                        }
                                        else {
                                            n4 = 0;
                                        }
                                    }
                                }
                                else if (Class146_Sub2.aBooleanArray4840[n3]) {
                                    n4 = 2000;
                                }
                                Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub2.anInt6369, (Class151_Sub9.anIntArray5019[n3] != -1) ? Class151_Sub9.anIntArray5019[n3] : Class284_Sub2.anInt5186, 0, "<col=ffffff>" + s2, false, 0, (short)(n4 + Class19.aShortArray3447[n3]), class246_Sub3_Sub4_Sub2_Sub2.anInt6369, -1, false, Class269.aStringArray2021[n3]);
                            }
                        }
                    }
                    else {
                        Class293.method3470(false, false, 0L, -1, 0, "", false, 0, -1, class246_Sub3_Sub4_Sub2_Sub2.anInt6369, 0, true, "<col=cccccc>" + s2);
                    }
                    if (!b) {
                        for (Class98_Sub46_Sub8 class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(32); class98_Sub46_Sub8 != null; class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(102)) {
                            if (~class98_Sub46_Sub8.anInt5990 == 0xFFFFFFF9) {
                                class98_Sub46_Sub8.aString5985 = "<col=ffffff>" + s2;
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.H(" + b + ',' + b2 + ',' + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1312(final int n, final boolean b) {
        try {
            this.aStream4093.b(3 + 4 * n);
            if (!b) {
                this.method1313(108, (byte)47);
            }
            this.aStream4093.e(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.D(" + n + ',' + b + ')');
        }
    }
    
    final void method1313(final int n, final byte b) {
        try {
            this.aStream4093.c();
            (this.anInterface2_Impl1_4085 = this.aHa_Sub3_4087.method2060(false, 68)).method73((byte)(-113), n * 4, 4, this.aNativeHeapBuffer4089);
            this.aNativeHeapBuffer4089 = null;
            if (b <= 18) {
                method1311(true, false, null);
            }
            this.aStream4093 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.C(" + n + ',' + b + ')');
        }
    }
    
    final void method1314(final int[] array, final int n, final int n2) {
        try {
            final Interface2_Impl2 method1963 = this.aHa_Sub3_4087.method1963(62, this.anInt4098 * 3);
            final Buffer method1964 = method1963.method78(true, -112);
            if (method1964 != null) {
                final Stream method1965 = this.aHa_Sub3_4087.method2043(24022, method1964);
                int n3 = 0;
                if (n2 != 32736) {
                    this.method1315(-55, 122, 111, -45);
                }
                int n4 = 32767;
                int n5 = -32768;
                if (!Stream.a()) {
                    for (int n6 = 0; ~n6 > ~n; ++n6) {
                        final int n7 = array[n6];
                        final int n8 = this.anIntArray4095[n7];
                        final short[] array2 = this.aS_Sub2_4097.aShortArrayArray5230[n7];
                        if (~n8 != -1 && array2 != null) {
                            int n9 = 0;
                            for (int n10 = 0; ~n10 > ~array2.length; n10 += 3) {
                                if ((n8 & 1 << n9++) != 0x0) {
                                    ++n3;
                                    for (int n11 = 0; ~n11 > -4; ++n11) {
                                        final int n12 = 0xFFFF & array2[n10++];
                                        if (n12 > n5) {
                                            n5 = n12;
                                        }
                                        method1965.d(n12);
                                        if (~n12 > ~n4) {
                                            n4 = n12;
                                        }
                                    }
                                }
                                else {}
                            }
                        }
                    }
                }
                else {
                    for (final int n13 : array) {
                        final int n14 = this.anIntArray4095[n13];
                        final short[] array3 = this.aS_Sub2_4097.aShortArrayArray5230[n13];
                        if (n14 != 0 && array3 != null) {
                            int n15 = 0;
                            int j = 0;
                            while (j < array3.length) {
                                if ((n14 & 1 << n15++) != 0x0) {
                                    for (int k = 0; k < 3; ++k) {
                                        final int n16 = 0xFFFF & array3[j++];
                                        method1965.c(n16);
                                        if (n4 > n16) {
                                            n4 = n16;
                                        }
                                        if (n5 < n16) {
                                            n5 = n16;
                                        }
                                    }
                                    ++n3;
                                }
                                else {
                                    j += 3;
                                }
                            }
                        }
                    }
                }
                method1965.c();
                if (method1963.method79((byte)60) && n3 > 0) {
                    this.aHa_Sub3_4087.method2039((0x8 & this.aS_Sub2_4097.anInt5233) != 0x0, n2 - 32736, this.anInt4084, ~(this.aS_Sub2_4097.anInt5233 & 0x7) != -1);
                    if (this.aHa_Sub3_4087.aBoolean4563) {
                        this.aHa_Sub3_4087.EA(Integer.MAX_VALUE, this.anInt4091, this.anInt4090, this.anInt4086);
                    }
                    this.aHa_Sub3_4087.method1957((byte)(-128)).method2137(1.0f / this.aFloat4092, (byte)(-122), 1.0f / this.aFloat4092, 1.0f);
                    this.aHa_Sub3_4087.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)36);
                    this.aHa_Sub3_4087.method1971(1, true, this.anInterface2_Impl1_4085);
                    this.aHa_Sub3_4087.method2042(this.aS_Sub2_4097.aClass256_5252, (byte)67);
                    this.aHa_Sub3_4087.method1973(Class336.aClass232_2822, -n4 + n5 + 1, 0, 26810, method1963, n4, n3);
                    this.aHa_Sub3_4087.method1985(2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.F(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method1315(final int n, final int n2, final int n3, final int n4) {
        try {
            this.anIntArray4095[n4 * this.aS_Sub2_4097.anInt2203 + n3] = Class41.method366(this.anIntArray4095[n4 * this.aS_Sub2_4097.anInt2203 + n3], 1 << n);
            if (n2 != -20787) {
                this.method1315(22, -33, -30, -8);
            }
            ++this.anInt4098;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method1316(final int n, final int n2) {
        try {
            this.aNativeHeapBuffer4089 = this.aHa_Sub3_4087.method1947(n * n2, true, 0);
            this.aStream4093 = new Stream(this.aNativeHeapBuffer4089, 0, 4 * n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.A(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1317(final int n, final int n2, final int n3, int n4, final float n5) {
        try {
            if (this.anInt4084 != n3) {
                final Class238 method11 = this.aHa_Sub3_4087.aD938.method11(this.anInt4084, -28755);
                final int n6 = method11.aByte1830 & 0xFF;
                if (n6 != 0 && ~method11.aByte1820 != 0xFFFFFFFB) {
                    int n7;
                    if (~n2 > -1) {
                        n7 = 0;
                    }
                    else if (n2 > 127) {
                        n7 = 16777215;
                    }
                    else {
                        n7 = n2 * 131586;
                    }
                    if (~n6 == 0xFFFFFEFF) {
                        n4 = n7;
                    }
                    else {
                        final int n8 = n6;
                        final int n9 = 256 + -n6;
                        n4 = (0xFF00FF00 & n9 * (0xFF00FF & n4) + (n7 & 0xFF00FF) * n8) + (0xFF0000 & (n7 & 0xFF00) * n8 + (0xFF00 & n4) * n9) >> 917253800;
                    }
                }
                int n10 = 0xFF & method11.aByte1829;
                if (~n10 != -1) {
                    n10 += 256;
                    int n11 = ((0xFF0000 & n4) >> -921160400) * n10;
                    if (~n11 < -65536) {
                        n11 = 65535;
                    }
                    int n12 = (n4 >> 1157906824 & 0xFF) * n10;
                    if (~n12 < -65536) {
                        n12 = 65535;
                    }
                    int n13 = (n4 & 0xFF) * n10;
                    if (~n13 < -65536) {
                        n13 = 65535;
                    }
                    n4 = (n13 >> -259571832) + ((0xFF00 & n12) + ((n11 & 0x7000FF00) << -1508903384));
                }
            }
            this.aStream4093.b(n * 4);
            if (n5 != 1.0f) {
                final int n14 = (0xFF9FC6 & n4) >> 2035833968;
                final int n15 = 0xFF & n4 >> 1655837352;
                int n16 = (int)(n14 * n5);
                final int n17 = 0xFF & n4;
                int n18 = (int)(n15 * n5);
                if (~n16 > -1) {
                    n16 = 0;
                }
                else if (~n16 < -256) {
                    n16 = 255;
                }
                int n19 = (int)(n17 * n5);
                if (n18 < 0) {
                    n18 = 0;
                }
                else if (n18 > 255) {
                    n18 = 255;
                }
                if (~n19 > -1) {
                    n19 = 0;
                }
                else if (~n19 < -256) {
                    n19 = 255;
                }
                n4 = (n16 << -663945424 | n18 << 1289794376 | n19);
            }
            if (this.aHa_Sub3_4087.anInt4580 == 0) {
                this.aStream4093.e((byte)n4);
                this.aStream4093.e((byte)(n4 >> 1356888040));
                this.aStream4093.e((byte)(n4 >> -1014001104));
            }
            else {
                this.aStream4093.e((byte)(n4 >> 771535760));
                this.aStream4093.e((byte)(n4 >> 1569612008));
                this.aStream4093.e((byte)n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    public static void method1318(final int n) {
        try {
            Class98_Sub30.aClass164_4088 = null;
            Class98_Sub30.aFloatArray4096 = null;
            Class98_Sub30.aClass58_4094 = null;
            Class98_Sub30.aClass155Array4099 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.G(" + n + ')');
        }
    }
    
    static final void method1319(final int n, final File file, final boolean b) {
        try {
            if (Class98_Sub10_Sub22.anObject5653 == null) {
                Class340.method3803(false);
            }
            try {
                final Class<?> forName = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
                if (n == 0) {
                    forName.getDeclaredMethod("dumpHeap", (Class98_Sub30.aClass4100 != null) ? Class98_Sub30.aClass4100 : (Class98_Sub30.aClass4100 = method1320("java.lang.String")), Boolean.TYPE).invoke(Class98_Sub10_Sub22.anObject5653, file.getAbsolutePath(), new Boolean(b));
                }
            }
            catch (Exception ex) {
                System.out.println("HeapDump error:");
                ex.printStackTrace();
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "li.E(" + n + ',' + ((file != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    Class98_Sub30(final s_Sub2 as_Sub2_4097, final int anInt4084, final int n, final int anInt4085, final int anInt4086, final int anInt4087) {
        this.anInt4098 = 0;
        try {
            this.aS_Sub2_4097 = as_Sub2_4097;
            this.anInt4091 = anInt4085;
            this.anInt4084 = anInt4084;
            this.anInt4090 = anInt4086;
            this.aHa_Sub3_4087 = this.aS_Sub2_4097.aHa_Sub3_5225;
            this.anIntArray4095 = new int[this.aS_Sub2_4097.anInt2204 * this.aS_Sub2_4097.anInt2203];
            this.anInt4086 = anInt4087;
            this.aFloat4092 = n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "li.<init>(" + ((as_Sub2_4097 != null) ? "{...}" : "null") + ',' + anInt4084 + ',' + n + ',' + anInt4085 + ',' + anInt4086 + ',' + anInt4087 + ')');
        }
    }
    
    static Class method1320(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class98_Sub30.aClass164_4088 = new Class164(1);
        Class98_Sub30.aFloatArray4096 = new float[16];
    }
}
