// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub37 extends Class98_Sub10
{
    private int anInt5746;
    private int[] anIntArray5747;
    static Class148 aClass148_5748;
    private int anInt5749;
    private int anInt5750;
    
    public static void method1113(final int n) {
        try {
            if (n != 0) {
                method1113(126);
            }
            Class98_Sub10_Sub37.aClass148_5748 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.D(" + n + ')');
        }
    }
    
    public Class98_Sub10_Sub37() {
        super(1, true);
        this.anInt5746 = 3216;
        this.anIntArray5747 = new int[3];
        this.anInt5750 = 4096;
        this.anInt5749 = 3216;
    }
    
    private final void method1114(final byte b) {
        try {
            final double cos = Math.cos(this.anInt5749 / 4096.0f);
            this.anIntArray5747[0] = (int)(4096.0 * (cos * Math.sin(this.anInt5746 / 4096.0f)));
            this.anIntArray5747[1] = (int)(Math.cos(this.anInt5746 / 4096.0f) * cos * 4096.0);
            this.anIntArray5747[2] = (int)(4096.0 * Math.sin(this.anInt5749 / 4096.0f));
            if (b <= -73) {
                final int n = (int)(4096.0 * Math.sqrt((this.anIntArray5747[0] * this.anIntArray5747[0] >> 247401804) + (this.anIntArray5747[1] * this.anIntArray5747[1] >> 2024311884) - -(this.anIntArray5747[2] * this.anIntArray5747[2] >> -899467796) >> -1727670228));
                if (~n != -1) {
                    this.anIntArray5747[1] = (this.anIntArray5747[1] << -1369097108) / n;
                    this.anIntArray5747[2] = (this.anIntArray5747[2] << -1561597076) / n;
                    this.anIntArray5747[0] = (this.anIntArray5747[0] << -121165236) / n;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.E(" + b + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.method1114((byte)(-76));
            if (b != 66) {
                this.method1001((byte)126);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.I(" + b + ')');
        }
    }
    
    static final int method1115(int n, final byte[] array, final boolean b, final String s) {
        try {
            if (b) {
                return 5;
            }
            final int n2 = n;
            for (int length = s.length(), n3 = 0; ~n3 > ~length; n3 += 4) {
                final int method574 = Class64_Sub6.method574(120, s.charAt(n3));
                final int n4 = (~length >= ~(n3 + 1)) ? -1 : Class64_Sub6.method574(73, s.charAt(1 + n3));
                final int n5 = (~(2 + n3) > ~length) ? Class64_Sub6.method574(124, s.charAt(2 + n3)) : -1;
                final int n6 = (~(n3 + 3) <= ~length) ? -1 : Class64_Sub6.method574(103, s.charAt(3 + n3));
                array[n++] = (byte)Class41.method366(method574 << 1770288578, n4 >>> 1496720228);
                if (~n5 == 0x0) {
                    break;
                }
                array[n++] = (byte)Class41.method366(Class202.method2702(240, n4 << -243044220), n5 >>> -1134162686);
                if (~n6 == 0x0) {
                    break;
                }
                array[n++] = (byte)Class41.method366(n6, Class202.method2702(3, n5) << -391855066);
            }
            return n - n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                return null;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = this.anInt5750 * Class246_Sub3_Sub4_Sub1.anInt6241 >> -854156148;
                final int[] method238 = this.method1000(n2 - 1 & za_Sub1.anInt6075, 0, 0);
                final int[] method239 = this.method1000(n2, 0, 0);
                final int[] method240 = this.method1000(za_Sub1.anInt6075 & n2 + 1, 0, n ^ 0xFF);
                for (int n4 = 0; ~n4 > ~Class25.anInt268; ++n4) {
                    final int n5 = n3 * (method240[n4] - method238[n4]) >> -561357748;
                    final int n6 = n3 * (method239[n4 - 1 & Class329.anInt2761] - method239[Class329.anInt2761 & n4 + 1]) >> -1616087444;
                    int n7 = n6 >> 2114966948;
                    if (n7 < 0) {
                        n7 = -n7;
                    }
                    int n8 = n5 >> -373105820;
                    if (~n8 > -1) {
                        n8 = -n8;
                    }
                    if (~n7 < -256) {
                        n7 = 255;
                    }
                    if (~n8 < -256) {
                        n8 = 255;
                    }
                    final int n9 = 0xFF & Class194.aByteArray1495[(n8 * (n8 + 1) >> -714044351) + n7];
                    method237[n4] = ((n9 * n6 >> -1545737624) * this.anIntArray5747[0] >> -304764628) + ((n9 * n5 >> -419747032) * this.anIntArray5747[1] >> -2050468148) - -(this.anIntArray5747[2] * (n9 * 4096 >> -666378040) >> 1934014988);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                this.anInt5750 = 79;
            }
            if (n != 0) {
                if (~n != 0xFFFFFFFE) {
                    if (n == 2) {
                        this.anInt5749 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5746 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5750 = class98_Sub22.readShort((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vb.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub37.aClass148_5748 = new Class148();
    }
}
