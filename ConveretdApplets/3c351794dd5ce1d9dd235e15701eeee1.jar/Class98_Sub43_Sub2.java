import jagtheora.ogg.OggPacket;
import jagtheora.ogg.OggStreamState;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub43_Sub2 extends Class98_Sub43
{
    static OutgoingOpcode aClass171_5906;
    static Class88 aClass88_5907;
    static Class196 aClass196_5908;
    static float aFloat5909;
    static int anInt5910;
    
    @Override
    final void method1487(final int n) {
        try {
            if (n != -1128) {
                Class98_Sub43_Sub2.aClass171_5906 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ria.C(" + n + ')');
        }
    }
    
    Class98_Sub43_Sub2(final OggStreamState oggStreamState) {
        super(oggStreamState);
    }
    
    @Override
    final void method1482(final OggPacket oggPacket, final boolean b) {
        try {
            if (b) {
                Class98_Sub43_Sub2.anInt5910 = 86;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ria.J(" + ((oggPacket != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1494(final int n) {
        try {
            Class98_Sub43_Sub2.aClass88_5907 = null;
            Class98_Sub43_Sub2.aClass171_5906 = null;
            Class98_Sub43_Sub2.aClass196_5908 = null;
            if (n != 4) {
                Class98_Sub43_Sub2.aClass196_5908 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ria.A(" + n + ')');
        }
    }
    
    static {
        Class98_Sub43_Sub2.aClass171_5906 = new OutgoingOpcode(49, 4);
        Class98_Sub43_Sub2.aClass196_5908 = new Class196("LOCAL", "", "local", 4);
    }
}
