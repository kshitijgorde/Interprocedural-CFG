import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class196
{
    static IncomingOpcode aClass58_1507;
    static OutgoingOpcode aClass171_1508;
    static Class304 aClass304_1509;
    int anInt1510;
    static int anInt1511;
    static Class207 aClass207_1512;
    
    static final int method2665(final boolean b, final Class164 class164) {
        try {
            if (Class98_Sub40.aClass164_4190 == class164) {
                return 6407;
            }
            if (Class62.aClass164_486 == class164) {
                return 6408;
            }
            if (Class53_Sub1.aClass164_3633 == class164) {
                return 6406;
            }
            if (Class98_Sub30.aClass164_4088 == class164) {
                return 6409;
            }
            if (Class74.aClass164_547 == class164) {
                return 6410;
            }
            if (class164 == Class280.aClass164_2101) {
                return 6145;
            }
            if (b) {
                Class196.aClass58_1507 = null;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.D(" + b + ',' + ((class164 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2666(final int n, final boolean b, final Class98_Sub18 class98_Sub18, final boolean b2) {
        try {
            final int anInt3945 = class98_Sub18.anInt3945;
            final int n2 = (int)class98_Sub18.aLong832;
            class98_Sub18.method942(n - 16284);
            if (b2) {
                Class246.method2964(false, anInt3945);
            }
            Class17.method239(n ^ 0x4016, anInt3945);
            final Class293 method2509 = Class159.method2509(n2, -9820);
            if (method2509 != null) {
                Class341.method3812(1, method2509);
            }
            Class230.method2869(106);
            if (!b && ~Class15.anInt185 != 0x0) {
                Class207.method2764(1, Class15.anInt185, -44);
            }
            final Class61 class61 = new Class61(Class116.aClass377_964);
            for (Class98_Sub18 class98_Sub19 = (Class98_Sub18)class61.method541(0); class98_Sub19 != null; class98_Sub19 = (Class98_Sub18)class61.method539(n - 16396)) {
                if (!class98_Sub19.method941((byte)78)) {
                    class98_Sub19 = (Class98_Sub18)class61.method541(0);
                    if (class98_Sub19 == null) {
                        break;
                    }
                }
                if (~class98_Sub19.anInt3947 == 0xFFFFFFFC && ~anInt3945 == ~((int)class98_Sub19.aLong832 >>> 330667472)) {
                    method2666(16398, b, class98_Sub19, true);
                }
            }
            if (n != 16398) {
                method2666(-42, true, null, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.C(" + n + ',' + b + ',' + ((class98_Sub18 != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.toString()");
        }
    }
    
    public static void method2667(final byte b) {
        try {
            Class196.aClass207_1512 = null;
            Class196.aClass171_1508 = null;
            Class196.aClass304_1509 = null;
            if (b == -112) {
                Class196.aClass58_1507 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.A(" + b + ')');
        }
    }
    
    static final Class123 method2668(final Socket socket, final byte b, final int n) throws IOException {
        try {
            if (b != 11) {
                method2667((byte)24);
            }
            return new Class123_Sub1(socket, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.B(" + ((socket != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    Class196(final String s, final String s2, final String s3, final int anInt1510) {
        try {
            this.anInt1510 = anInt1510;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mu.<init>(" + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((s3 != null) ? "{...}" : "null") + ',' + anInt1510 + ')');
        }
    }
    
    static {
        Class196.aClass58_1507 = new IncomingOpcode(72, 0);
        Class196.anInt1511 = 0;
        Class196.aClass171_1508 = new OutgoingOpcode(81, 7);
    }
}
