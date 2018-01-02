// 
// Decompiled by Procyon v0.5.30
// 

final class Class38
{
    static int anInt354;
    static int anInt355;
    static d aD356;
    static Class100 aClass100_357;
    static int anInt358;
    static int anInt359;
    static int anInt360;
    
    public static void method346(final int n) {
        try {
            if (n != 255) {
                method347(null, (byte)(-123), -77);
            }
            Class38.aClass100_357 = null;
            Class38.aD356 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cm.B(" + n + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cm.toString()");
        }
    }
    
    static final String method347(final Class293 class293, final byte b, final int n) {
        try {
            if (b <= 51) {
                return null;
            }
            if (!client.method116(class293).method1666((byte)(-72), n) && class293.anObjectArray2329 == null) {
                return null;
            }
            if (class293.aStringArray2351 != null && ~n > ~class293.aStringArray2351.length && class293.aStringArray2351[n] != null && class293.aStringArray2351[n].trim().length() != 0) {
                return class293.aStringArray2351[n];
            }
            if (Class15.aBoolean169) {
                return "Hidden-" + n;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cm.A(" + ((class293 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static final int method348(final int n, final int n2) {
        try {
            final double n3 = (0xFF & n >> 598073424) / 256.0;
            final double n4 = ((0xFF1E & n) >> -2121118168) / 256.0;
            final double n5 = (0xFF & n) / 256.0;
            double n6 = n3;
            if (n4 < n6) {
                n6 = n4;
            }
            if (n6 > n5) {
                n6 = n5;
            }
            double n7 = n3;
            if (n7 < n4) {
                n7 = n4;
            }
            if (n5 > n7) {
                n7 = n5;
            }
            double n8 = 0.0;
            double n9 = 0.0;
            final double n10 = (n6 + n7) / 2.0;
            if (n6 != n7) {
                if (n10 < 0.5) {
                    n9 = (n7 - n6) / (n6 + n7);
                }
                if (n10 >= 0.5) {
                    n9 = (n7 - n6) / (-n7 + 2.0 - n6);
                }
                if (n3 != n7) {
                    if (n7 == n4) {
                        n8 = (-n3 + n5) / (n7 - n6) + 2.0;
                    }
                    else if (n5 == n7) {
                        n8 = (n3 - n4) / (n7 - n6) + 4.0;
                    }
                }
                else {
                    n8 = (n4 - n5) / (n7 - n6);
                }
            }
            final int n11 = (int)(256.0 * (n8 / 6.0));
            int n12 = (int)(n9 * 256.0);
            if (n12 >= 0) {
                if (n12 > 255) {
                    n12 = 255;
                }
            }
            else {
                n12 = 0;
            }
            int n13 = (int)(256.0 * n10);
            if (n13 >= 0) {
                if (~n13 < -256) {
                    n13 = 255;
                }
            }
            else {
                n13 = 0;
            }
            if (~n13 >= -244) {
                if (n13 <= 217) {
                    if (~n13 >= -193) {
                        if (~n13 < -180) {
                            n12 >>= 1;
                        }
                    }
                    else {
                        n12 >>= 2;
                    }
                }
                else {
                    n12 >>= 3;
                }
            }
            else {
                n12 >>= 4;
            }
            return (n12 >> -187392699 << -941273977) + ((0xFF & n11) >> 1108545090 << 440834122) - -(n13 >> -1810836799);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cm.C(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class38.anInt354 = 0;
        Class38.aClass100_357 = new Class100(128);
    }
}
