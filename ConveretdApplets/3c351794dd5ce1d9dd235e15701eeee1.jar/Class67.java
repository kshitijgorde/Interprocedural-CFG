// 
// Decompiled by Procyon v0.5.30
// 

final class Class67
{
    static boolean aBoolean520;
    static int anInt521;
    
    static final void method687(final byte b, final int n) {
        try {
            synchronized (Class299.aClass79_2493) {
                Class299.aClass79_2493.method800((byte)62, n);
                if (b != 118) {
                    Class67.aBoolean520 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eh.C(" + b + ',' + n + ')');
        }
    }
    
    static final void method688(final int n, final long n2, final int n3, final int n4) {
        try {
            final int n5 = (0x7F61D & (int)n2) >> 250951790;
            final int n6 = (int)n2 >> -1669061484 & 0x3;
            final int n7 = (int)(n2 >>> -1972629216) & Integer.MAX_VALUE;
            if (n == -23) {
                if (n5 == 10 || ~n5 == 0xFFFFFFF4 || ~n5 == 0xFFFFFFE9) {
                    final Class352 method3546 = Class130.aClass302_1028.method3546(n7, (byte)119);
                    int n8 = 0;
                    int n9 = 0;
                    Label_0123: {
                        if (~n6 == -1 || n6 == 2) {
                            n8 = method3546.sizeY;
                            n9 = method3546.sizeX;
                            if (!client.aBoolean3553) {
                                break Label_0123;
                            }
                        }
                        n8 = method3546.sizeX;
                        n9 = method3546.sizeY;
                    }
                    int anInt2948 = method3546.anInt2948;
                    if (n6 != 0) {
                        anInt2948 = (anInt2948 << n6 & 0xF) + (anInt2948 >> 4 + -n6);
                    }
                    Class76_Sub2.requestFlag(0, anInt2948, n9, 0, 0, n4, n3, true, n8);
                }
                else {
                    Class76_Sub2.requestFlag(n6, 0, 0, n5, 0, n4, n3, true, 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eh.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final Class177 method689(final int n, final byte b) {
        try {
            if (b > -87) {
                Class67.anInt521 = 88;
            }
            final Class177 class177 = (Class177)Class246_Sub9.aClass79_5138.method802(-120, n);
            if (class177 != null) {
                return class177;
            }
            final byte[] method2745 = ha_Sub3.aClass207_4528.method2745(n, 0, false);
            final Class177 class178 = new Class177();
            if (method2745 != null) {
                class178.method2586((byte)77, new Class98_Sub22(method2745), n);
            }
            Class246_Sub9.aClass79_5138.method805(n, class178, (byte)(-80));
            return class178;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eh.B(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class67.aBoolean520 = false;
    }
}
