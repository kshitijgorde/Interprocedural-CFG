// 
// Decompiled by Procyon v0.5.30
// 

final class Exception_Sub1 extends Exception
{
    static IncomingOpcode aClass58_43;
    static int anInt44;
    static char[] aCharArray45;
    static OutgoingOpcode aClass171_46;
    static Class98_Sub35 aClass98_Sub35_47;
    
    public static void method133(final byte b) {
        try {
            Exception_Sub1.aClass58_43 = null;
            Exception_Sub1.aClass98_Sub35_47 = null;
            Exception_Sub1.aClass171_46 = null;
            Exception_Sub1.aCharArray45 = null;
            if (b > -117) {
                Exception_Sub1.anInt44 = 109;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ak.A(" + b + ')');
        }
    }
    
    static final void method134(final byte b) {
        try {
            if (!PacketSender.aBoolean2575) {
                Class64_Sub6.aBoolean3656 = true;
                Class305.aFloat2545 += (-Class305.aFloat2545 + 12.0f) / 2.0f;
                PacketSender.aBoolean2575 = true;
                if (b != -87) {
                    method134((byte)(-86));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ak.B(" + b + ')');
        }
    }
    
    static {
        Exception_Sub1.aClass58_43 = new IncomingOpcode(117, 0);
        Exception_Sub1.aCharArray45 = new char[] { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        Exception_Sub1.aClass171_46 = new OutgoingOpcode(45, 15);
    }
}
