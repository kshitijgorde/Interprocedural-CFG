import jagtheora.ogg.OggStreamState;
import jagtheora.ogg.OggPacket;
import jagtheora.theora.SetupInfo;
import jagtheora.theora.DecoderContext;
import jagtheora.theora.TheoraComment;
import jagtheora.theora.GranulePos;
import jagtheora.theora.TheoraInfo;
import jagtheora.theora.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub43_Sub3 extends Class98_Sub43
{
    private Frame aFrame5911;
    private boolean aBoolean5912;
    private boolean aBoolean5913;
    private TheoraInfo aTheoraInfo5914;
    private double aDouble5915;
    private boolean aBoolean5916;
    private Class332 aClass332_5917;
    private GranulePos aGranulePos5918;
    static int[] anIntArray5919;
    private long aLong5920;
    private TheoraComment aTheoraComment5921;
    static Class245[] aClass245Array5922;
    private DecoderContext aDecoderContext5923;
    static int anInt5924;
    private int anInt5925;
    static Class65 aClass65_5926;
    private boolean aBoolean5927;
    private SetupInfo aSetupInfo5928;
    private int anInt5929;
    
    private final void method1495(final int anInt5929, final int n) {
        try {
            this.anInt5929 = anInt5929;
            if (this.aBoolean5927) {
                if (~this.anInt5925 > ~this.anInt5929) {
                    this.anInt5929 = this.anInt5925;
                }
                if (~this.anInt5929 > -1) {
                    this.anInt5929 = 0;
                }
                this.aDecoderContext5923.setPostProcessingLevel(this.anInt5929);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.B(" + anInt5929 + ',' + n + ')');
        }
    }
    
    final float method1496(final int n) {
        try {
            if (n != -1) {
                return -0.24629752f;
            }
            if (!this.aBoolean5927 || this.aTheoraInfo5914.b()) {
                return 0.0f;
            }
            return this.aTheoraInfo5914.fpsNumerator / this.aTheoraInfo5914.fpsDenominator;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.H(" + n + ')');
        }
    }
    
    public static void method1497(final byte b) {
        try {
            Class98_Sub43_Sub3.aClass245Array5922 = null;
            Class98_Sub43_Sub3.anIntArray5919 = null;
            if (b <= 74) {
                Class98_Sub43_Sub3.aClass65_5926 = null;
            }
            Class98_Sub43_Sub3.aClass65_5926 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.A(" + b + ')');
        }
    }
    
    final boolean method1498(final int n) {
        try {
            if (n != 1024) {
                this.method1500(true);
            }
            return this.aBoolean5927;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.G(" + n + ')');
        }
    }
    
    @Override
    final void method1482(final OggPacket oggPacket, final boolean b) {
        try {
            Label_0333: {
                if (!this.aBoolean5927) {
                    final int decodeHeader = this.aSetupInfo5928.decodeHeader(this.aTheoraInfo5914, this.aTheoraComment5921, oggPacket);
                    if (~decodeHeader == -1) {
                        this.aBoolean5927 = true;
                        if (this.aTheoraInfo5914.frameWidth > 2048 || this.aTheoraInfo5914.frameHeight > 1024) {
                            throw new IllegalStateException();
                        }
                        this.aDecoderContext5923 = new DecoderContext(this.aTheoraInfo5914, this.aSetupInfo5928);
                        this.aGranulePos5918 = new GranulePos();
                        this.aFrame5911 = new Frame(this.aTheoraInfo5914.frameWidth, this.aTheoraInfo5914.frameHeight);
                        this.anInt5925 = this.aDecoderContext5923.getMaxPostProcessingLevel();
                        this.method1495(this.anInt5929, -98);
                        if (!client.aBoolean3553) {
                            break Label_0333;
                        }
                    }
                    if (~decodeHeader > -1) {
                        throw new IllegalStateException(String.valueOf(decodeHeader));
                    }
                }
                else {
                    this.aLong5920 = Class343.method3819(-47);
                    final int decodePacketIn = this.aDecoderContext5923.decodePacketIn(oggPacket, this.aGranulePos5918);
                    if (~decodePacketIn > -1) {
                        throw new IllegalStateException(String.valueOf(decodePacketIn));
                    }
                    this.aDecoderContext5923.granuleFrame(this.aGranulePos5918);
                    this.aDouble5915 = this.aDecoderContext5923.granuleTime(this.aGranulePos5918);
                    if (this.aBoolean5916) {
                        if (~oggPacket.isKeyFrame() != 0xFFFFFFFE) {
                            return;
                        }
                        this.aBoolean5916 = false;
                    }
                    if (!this.aBoolean5913 || ~oggPacket.isKeyFrame() == 0xFFFFFFFE) {
                        if (~this.aDecoderContext5923.decodeFrame(this.aFrame5911) != -1) {
                            throw new IllegalStateException(String.valueOf(decodePacketIn));
                        }
                        this.aBoolean5912 = true;
                    }
                }
            }
            if (b) {
                Class98_Sub43_Sub3.anInt5924 = -89;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.J(" + ((oggPacket != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    Class98_Sub43_Sub3(final OggStreamState oggStreamState) {
        super(oggStreamState);
        try {
            this.aSetupInfo5928 = new SetupInfo();
            this.aTheoraInfo5914 = new TheoraInfo();
            this.aTheoraComment5921 = new TheoraComment();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.<init>(" + ((oggStreamState != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method1499(final byte b, final char c) {
        try {
            if (Character.isISOControl(c)) {
                return false;
            }
            if (Class114.method2147(c, 115)) {
                return true;
            }
            final char[] aCharArray376 = Class43.aCharArray376;
            for (int n = 0; aCharArray376.length > n; ++n) {
                if (aCharArray376[n] == c) {
                    return true;
                }
            }
            final char[] aCharArray377 = aa_Sub3.aCharArray3572;
            int i = 0;
            if (b != 105) {
                method1497((byte)7);
            }
            while (i < aCharArray377.length) {
                if (c == aCharArray377[i]) {
                    return true;
                }
                ++i;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.F(" + b + ',' + c + ')');
        }
    }
    
    final long method1500(final boolean b) {
        try {
            if (b) {
                return -59L;
            }
            return this.aLong5920;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.E(" + b + ')');
        }
    }
    
    @Override
    final void method1487(final int n) {
        try {
            if (this.aFrame5911 != null) {
                this.aFrame5911.a();
            }
            if (this.aDecoderContext5923 != null) {
                this.aDecoderContext5923.a();
                this.aDecoderContext5923 = null;
            }
            if (this.aGranulePos5918 != null) {
                this.aGranulePos5918.a();
                this.aGranulePos5918 = null;
            }
            this.aTheoraInfo5914.a();
            this.aTheoraComment5921.a();
            if (n != -1128) {
                this.aTheoraComment5921 = null;
            }
            this.aSetupInfo5928.a();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.C(" + n + ')');
        }
    }
    
    final Class332 method1501(final ha ha, final int n) {
        try {
            if (this.aFrame5911 == null) {
                return null;
            }
            if (!this.aBoolean5912 && this.aClass332_5917 != null) {
                return this.aClass332_5917;
            }
            if (n != 11242) {
                return null;
            }
            this.aClass332_5917 = ha.method1770(this.aFrame5911.pixels, 0, this.aFrame5911.a, this.aFrame5911.a, this.aFrame5911.b, false);
            this.aBoolean5912 = false;
            return this.aClass332_5917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.D(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final double method1502(final int n) {
        try {
            if (n != 0) {
                this.method1500(false);
            }
            return this.aDouble5915;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sb.I(" + n + ')');
        }
    }
    
    static {
        Class98_Sub43_Sub3.anInt5924 = 0;
        Class98_Sub43_Sub3.anIntArray5919 = new int[128];
        for (int n = 0; Class98_Sub43_Sub3.anIntArray5919.length > n; ++n) {
            Class98_Sub43_Sub3.anIntArray5919[n] = -1;
        }
        for (int i = 65; i <= 90; ++i) {
            Class98_Sub43_Sub3.anIntArray5919[i] = i - 65;
        }
        for (int n2 = 97; ~n2 >= -123; ++n2) {
            Class98_Sub43_Sub3.anIntArray5919[n2] = n2 - 71;
        }
        for (int n3 = 48; ~n3 >= -58; ++n3) {
            Class98_Sub43_Sub3.anIntArray5919[n3] = 52 + (-48 + n3);
        }
        Class98_Sub43_Sub3.anIntArray5919[45] = (Class98_Sub43_Sub3.anIntArray5919[47] = 63);
        Class98_Sub43_Sub3.anIntArray5919[42] = (Class98_Sub43_Sub3.anIntArray5919[43] = 62);
        Class98_Sub43_Sub3.aClass65_5926 = new Class65();
    }
}
