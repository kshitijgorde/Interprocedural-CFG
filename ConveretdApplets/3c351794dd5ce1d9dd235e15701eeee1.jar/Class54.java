import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class54 implements Interface14
{
    static int anInt3391;
    private boolean aBoolean3392;
    static String[] aStringArray3393;
    static int anInt3394;
    static int anInt3395;
    private String aString3396;
    
    @Override
    public final int method51(final byte b) {
        try {
            final int method337 = Class35.method337(-120, this.aString3396);
            if (method337 >= 0 && ~method337 >= -101) {
                return method337;
            }
            if (b < 126) {
                this.method504(true);
            }
            this.aBoolean3392 = true;
            return 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.E(" + b + ')');
        }
    }
    
    static final Frame method503(int anInt1955, final byte b, final int n, final int n2, final int n3, final Class88 class88) {
        try {
            if (b <= 93) {
                Class54.anInt3395 = 45;
            }
            if (!class88.method860((byte)83)) {
                return null;
            }
            if (~anInt1955 == -1) {
                final Class259[] method490 = Class52.method490(class88, (byte)(-94));
                if (method490 == null) {
                    return null;
                }
                int n4 = 0;
                for (int n5 = 0; ~method490.length < ~n5; ++n5) {
                    if (n == method490[n5].anInt1953 && n2 == method490[n5].anInt1956 && (n3 == 0 || n3 == method490[n5].anInt1958) && (n4 == 0 || ~method490[n5].anInt1955 < ~anInt1955)) {
                        n4 = 1;
                        anInt1955 = method490[n5].anInt1955;
                    }
                }
                if (n4 == 0) {
                    return null;
                }
            }
            final Class143 method491 = class88.method869(-21605, anInt1955, n, n3, n2);
            while (~method491.anInt1163 == -1) {
                Class246_Sub7.method3131(0, 10L);
            }
            final Frame frame = (Frame)method491.anObject1162;
            if (frame == null) {
                return null;
            }
            if (method491.anInt1163 == 2) {
                Class281.method3331(false, frame, class88);
                return null;
            }
            return frame;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.A(" + anInt1955 + ',' + b + ',' + n + ',' + n2 + ',' + n3 + ',' + ((class88 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method504(final boolean b) {
        try {
            if (!b) {
                method505((byte)83);
            }
            return this.aBoolean3392;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.D(" + b + ')');
        }
    }
    
    @Override
    public final Class191 method50(final int n) {
        try {
            if (n != 15763) {
                return null;
            }
            return Class191.aClass191_1476;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.B(" + n + ')');
        }
    }
    
    public static void method505(final byte b) {
        try {
            Class54.aStringArray3393 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.C(" + b + ')');
        }
    }
    
    Class54(final String aString3396) {
        try {
            this.aString3396 = aString3396;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dm.<init>(" + ((aString3396 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class54.aStringArray3393 = new String[100];
        Class54.anInt3395 = 0;
        Class54.anInt3394 = 0;
    }
}
