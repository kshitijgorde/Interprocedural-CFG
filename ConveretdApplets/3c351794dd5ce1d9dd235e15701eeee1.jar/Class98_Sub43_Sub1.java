import jagtheora.ogg.OggPacket;
import jagtheora.ogg.OggStreamState;
import jagtheora.vorbis.VorbisInfo;
import jagtheora.vorbis.VorbisComment;
import jagtheora.vorbis.DSPState;
import jagtheora.vorbis.VorbisBlock;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub43_Sub1 extends Class98_Sub43
{
    static boolean aBoolean5895;
    static int[] anIntArray5896;
    static Class365 aClass365_5897;
    private VorbisBlock aVorbisBlock5898;
    private double aDouble5899;
    private Class314 aClass314_5900;
    private DSPState aDSPState5901;
    private Class98_Sub31_Sub4 aClass98_Sub31_Sub4_5902;
    private int anInt5903;
    private VorbisComment aVorbisComment5904;
    private VorbisInfo aVorbisInfo5905;
    
    final Class98_Sub31_Sub4 method1488(final byte b) {
        try {
            if (b <= 70) {
                return null;
            }
            return this.aClass98_Sub31_Sub4_5902;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.D(" + b + ')');
        }
    }
    
    final double method1489(final int n) {
        try {
            if (n != 0) {
                this.method1492((byte)79);
            }
            double n2 = this.aDouble5899;
            if (this.aClass98_Sub31_Sub4_5902 != null) {
                n2 = this.aClass98_Sub31_Sub4_5902.method1382(false);
                if (n2 < 0.0) {
                    n2 = this.aDouble5899;
                }
            }
            return -(256.0f / Class64_Sub15.anInt3678) + n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.F(" + n + ')');
        }
    }
    
    @Override
    final void method1487(final int n) {
        try {
            if (this.aVorbisBlock5898 != null) {
                this.aVorbisBlock5898.a();
            }
            if (n != -1128) {
                this.method1489(114);
            }
            if (this.aDSPState5901 != null) {
                this.aDSPState5901.a();
            }
            this.aVorbisComment5904.a();
            this.aVorbisInfo5905.a();
            if (this.aClass98_Sub31_Sub4_5902 != null) {
                this.aClass98_Sub31_Sub4_5902.method1379(n ^ 0xFFFFFB98);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.C(" + n + ')');
        }
    }
    
    static final void method1490(final int n) {
        try {
            if (Class98_Sub10_Sub1.aClass75Array5542 == null) {
                Class98_Sub10_Sub1.aClass75Array5542 = Class75.method735(-127);
                Class45.aClass75_381 = Class98_Sub10_Sub1.aClass75Array5542[0];
                Class235.aLong1753 = Class343.method3819(n + 16751);
            }
            if (Class140.aClass47_3241 == null) {
                Class266.method3238(n + 16798);
            }
            final Class75 aClass75_381 = Class45.aClass75_381;
            if (n == -16798) {
                final int method1624 = Class98_Sub46_Sub17.method1624(125);
                if (aClass75_381 == Class45.aClass75_381) {
                    Class107.aString912 = Class45.aClass75_381.aClass309_560.method3615(Class374.anInt3159, (byte)25);
                    if (Class45.aClass75_381.aBoolean553) {
                        Class195.anInt1506 = (-Class45.aClass75_381.anInt552 + Class45.aClass75_381.anInt557) * method1624 / 100 + Class45.aClass75_381.anInt552;
                    }
                    if (Class45.aClass75_381.aBoolean554) {
                        Class107.aString912 = Class107.aString912 + Class195.anInt1506 + "%";
                    }
                }
                else if (Class75.aClass75_579 == Class45.aClass75_381) {
                    Class140.aClass47_3241 = null;
                    Class61.method538(3, false);
                }
                else {
                    Class107.aString912 = aClass75_381.aClass309_555.method3615(Class374.anInt3159, (byte)25);
                    Class195.anInt1506 = aClass75_381.anInt557;
                    if (Class45.aClass75_381.aBoolean554) {
                        Class107.aString912 = Class107.aString912 + aClass75_381.anInt557 + "%";
                    }
                    if (Class45.aClass75_381.aBoolean553 || aClass75_381.aBoolean553) {
                        Class235.aLong1753 = Class343.method3819(-47);
                    }
                }
                if (Class140.aClass47_3241 != null) {
                    Class140.aClass47_3241.method446(Class235.aLong1753, (byte)(-119), Class195.anInt1506, Class45.aClass75_381, Class107.aString912);
                    if (Class39_Sub1.anInterface10Array3592 != null) {
                        for (int n2 = 1 + Class21_Sub3.anInt5390; ~Class39_Sub1.anInterface10Array3592.length < ~n2; ++n2) {
                            if (~Class39_Sub1.anInterface10Array3592[n2].method26(n + 16004) <= -101 && -1 + n2 == Class21_Sub3.anInt5390 && ~Class177.anInt1376 <= -2 && Class140.aClass47_3241.method441((byte)124)) {
                                try {
                                    Class39_Sub1.anInterface10Array3592[n2].method27(-31295);
                                }
                                catch (Exception ex2) {
                                    Class39_Sub1.anInterface10Array3592 = null;
                                    break;
                                }
                                Class140.aClass47_3241.method444((byte)(-74), Class39_Sub1.anInterface10Array3592[n2]);
                                ++Class21_Sub3.anInt5390;
                                if (~Class21_Sub3.anInt5390 <= ~(Class39_Sub1.anInterface10Array3592.length - 1) && ~Class39_Sub1.anInterface10Array3592.length < -2) {
                                    Class21_Sub3.anInt5390 = (Class3.aClass282_76.method3337(1) ? 0 : -1);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.G(" + n + ')');
        }
    }
    
    public static void method1491(final int n) {
        try {
            if (n == -3) {
                Class98_Sub43_Sub1.anIntArray5896 = null;
                Class98_Sub43_Sub1.aClass365_5897 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.A(" + n + ')');
        }
    }
    
    final int method1492(final byte b) {
        try {
            if (b > -14) {
                return -48;
            }
            if (this.aClass98_Sub31_Sub4_5902 == null) {
                return 0;
            }
            return this.aClass98_Sub31_Sub4_5902.method1387(true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.B(" + b + ')');
        }
    }
    
    static final void method1493(final int n) {
        try {
            if (Class140.aClass47_3241 != null) {
                Class140.aClass47_3241.method448(false);
            }
            if (Class76_Sub9.aThread3783 != null) {
                while (true) {
                    try {
                        Class76_Sub9.aThread3783.join();
                    }
                    catch (InterruptedException ex2) {
                        continue;
                    }
                    break;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.E(" + n + ')');
        }
    }
    
    Class98_Sub43_Sub1(final OggStreamState oggStreamState) {
        super(oggStreamState);
        try {
            this.aVorbisInfo5905 = new VorbisInfo();
            this.aVorbisComment5904 = new VorbisComment();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.<init>(" + ((oggStreamState != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1482(final OggPacket oggPacket, final boolean b) {
        try {
            if (!b) {
                if (~super.anInt4240 <= -4) {
                    if (~this.aVorbisBlock5898.synthesis(oggPacket) == -1) {
                        this.aDSPState5901.blockIn(this.aVorbisBlock5898);
                    }
                    final float[][] pcmOut = this.aDSPState5901.pcmOut(this.aVorbisInfo5905.channels);
                    this.aDouble5899 = this.aDSPState5901.granuleTime();
                    if (this.aDouble5899 == -1.0) {
                        this.aDouble5899 = this.anInt5903 / this.aVorbisInfo5905.rate;
                    }
                    this.aDSPState5901.read(pcmOut[0].length);
                    this.anInt5903 += pcmOut[0].length;
                    final Class98_Sub46_Sub15 method1385 = this.aClass98_Sub31_Sub4_5902.method1385(this.aDouble5899, pcmOut[0].length, -99);
                    Class151_Sub3.method2455(pcmOut, (byte)(-78), method1385.aShortArrayArray6040);
                    for (int i = 0; i < this.aVorbisInfo5905.channels; ++i) {
                        method1385.aShortArrayArray6040[i] = this.aClass314_5900.method3641(6, method1385.aShortArrayArray6040[i]);
                    }
                    this.aClass98_Sub31_Sub4_5902.method1380((byte)111, method1385);
                }
                else {
                    final int headerIn = this.aVorbisInfo5905.headerIn(this.aVorbisComment5904, oggPacket);
                    if (~headerIn > -1) {
                        throw new IllegalStateException(String.valueOf(headerIn));
                    }
                    if (~super.anInt4240 == 0xFFFFFFFD) {
                        if (~this.aVorbisInfo5905.channels < -3 || this.aVorbisInfo5905.channels < 1) {
                            throw new RuntimeException(String.valueOf(this.aVorbisInfo5905.channels));
                        }
                        this.aDSPState5901 = new DSPState(this.aVorbisInfo5905);
                        this.aVorbisBlock5898 = new VorbisBlock(this.aDSPState5901);
                        this.aClass314_5900 = new Class314(this.aVorbisInfo5905.rate, Class64_Sub15.anInt3678);
                        this.aClass98_Sub31_Sub4_5902 = new Class98_Sub31_Sub4(this.aVorbisInfo5905.channels);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cga.J(" + ((oggPacket != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub43_Sub1.anIntArray5896 = new int[] { -1, 8192, 0, -1, 12288, 10240, 14336, -1, 4096, 6144, 2048 };
        Class98_Sub43_Sub1.aBoolean5895 = false;
    }
}
