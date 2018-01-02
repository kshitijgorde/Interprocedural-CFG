import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class78
{
    static s[] aSArray594;
    static int[] anIntArray595;
    static int anInt596;
    static int[] anIntArray597;
    
    static final void method790(final int n, final int n2, final int n3, final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1324, final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1325) {
        final Class172 method1693 = Class100.method1693(n, n2, n3);
        if (method1693 != null) {
            method1693.aClass246_Sub3_Sub3_1324 = aClass246_Sub3_Sub3_1324;
            method1693.aClass246_Sub3_Sub3_1333 = aClass246_Sub3_Sub3_1325;
            final int n4 = (Class78.aSArray594 == Class81.aSArray618) ? 1 : 0;
            if (aClass246_Sub3_Sub3_1324.method2978(-124)) {
                if (aClass246_Sub3_Sub3_1324.method2987(6540)) {
                    aClass246_Sub3_Sub3_1324.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                    Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub3_1324;
                }
                else {
                    aClass246_Sub3_Sub3_1324.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                    Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub3_1324;
                    Class358.aBoolean3033 = true;
                }
            }
            else {
                aClass246_Sub3_Sub3_1324.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub3_1324;
            }
            if (aClass246_Sub3_Sub3_1325 != null) {
                if (aClass246_Sub3_Sub3_1325.method2978(-126)) {
                    if (aClass246_Sub3_Sub3_1325.method2987(6540)) {
                        aClass246_Sub3_Sub3_1325.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                        Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub3_1325;
                    }
                    else {
                        aClass246_Sub3_Sub3_1325.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                        Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub3_1325;
                        Class358.aBoolean3033 = true;
                    }
                }
                else {
                    aClass246_Sub3_Sub3_1325.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                    Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub3_1325;
                }
            }
        }
    }
    
    static final void method791(final byte b) {
        try {
            if (b == 102) {
                Class356 class356 = null;
                try {
                    final Class143 method875 = Class98_Sub43_Sub2.aClass88_5907.method875("2", true, 21516);
                    while (~method875.anInt1163 == -1) {
                        Class246_Sub7.method3131(0, 1L);
                    }
                    if (method875.anInt1163 == 1) {
                        class356 = (Class356)method875.anObject1162;
                        final byte[] array = new byte[(int)class356.method3878((byte)(-60))];
                        int method876;
                        for (int i = 0; i < array.length; i += method876) {
                            method876 = class356.method3879(-i + array.length, (byte)(-26), i, array);
                            if (method876 == -1) {
                                throw new IOException("EOF");
                            }
                        }
                        Class31.method306(b ^ 0x270C, new Class98_Sub22(array));
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (class356 != null) {
                        class356.method3880(true);
                    }
                }
                catch (Exception ex3) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fba.B(" + b + ')');
        }
    }
    
    public static void method792(final int n) {
        try {
            Class78.anIntArray597 = null;
            Class78.aSArray594 = null;
            Class78.anIntArray595 = null;
            if (n != -17344) {
                method792(-125);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fba.A(" + n + ')');
        }
    }
    
    static {
        Class78.anIntArray595 = new int[] { -1, -1, 1, 1 };
        Class78.anIntArray597 = new int[32];
    }
}
