import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class OutputStream_Sub2 extends OutputStream
{
    static int anInt39;
    static int anInt40;
    static byte[][][] aByteArrayArrayArray41;
    static int[] anIntArray42;
    
    @Override
    public final void write(final int n) throws IOException {
        try {
            throw new IOException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "taa.write(" + n + ')');
        }
    }
    
    public static void method132(final int n) {
        try {
            OutputStream_Sub2.anIntArray42 = null;
            if (n != -1) {
                method132(124);
            }
            OutputStream_Sub2.aByteArrayArrayArray41 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "taa.A(" + n + ')');
        }
    }
    
    static {
        OutputStream_Sub2.anInt39 = -1;
        OutputStream_Sub2.anInt40 = -50;
    }
}
