import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class31
{
    String aString299;
    static int anInt300;
    static Interface17 anInterface17_301;
    int anInt302;
    
    abstract Socket method305(final int p0) throws IOException;
    
    static final void method306(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (~(-class98_Sub22.anInt3991 + class98_Sub22.aByteArray3992.length) <= -2) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)38);
                if (~unsignedByte <= -1 && ~unsignedByte >= -2 && n == 10090 && ~(class98_Sub22.aByteArray3992.length - class98_Sub22.anInt3991) <= -3) {
                    final int i = class98_Sub22.readShort((byte)127);
                    if (~(class98_Sub22.aByteArray3992.length - class98_Sub22.anInt3991) <= ~(i * 6)) {
                        for (int n2 = 0; i > n2; ++n2) {
                            final int short1 = class98_Sub22.readShort((byte)127);
                            final int int1 = class98_Sub22.readInt(-2);
                            if (~Class76_Sub5.anIntArray3744.length < ~short1 && Class140.aBooleanArray3246[short1] && (~Class345.aClass132_2889.method2237(short1, 101).aChar720 != 0xFFFFFFCE || (int1 >= -1 && int1 <= 1))) {
                                Class76_Sub5.anIntArray3744[short1] = int1;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cda.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method307(final boolean b) {
        try {
            Class31.anInterface17_301 = null;
            if (!b) {
                method307(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cda.E(" + b + ')');
        }
    }
    
    final Socket method308(final byte b) throws IOException {
        try {
            if (b != -53) {
                Class31.anInterface17_301 = null;
            }
            return new Socket(this.aString299, this.anInt302);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cda.D(" + b + ')');
        }
    }
    
    static final int method309(final int n, final byte[] array, final int n2) {
        try {
            if (n2 != -30091) {
                Class31.anInterface17_301 = null;
            }
            return Class365.method3937(n, array, 0, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cda.C(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static {
        Class31.anInt300 = -2;
    }
}
