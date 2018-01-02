// 
// Decompiled by Procyon v0.5.30
// 

public final class Mulaw
{
    private static final boolean ZEROTRAP = true;
    private static final int BIAS = 132;
    private static final short CLIP = 32635;
    private static final short[] exp_lut;
    private static final short[] exp_lut2;
    
    public static final short ulaw2linear(final byte b) {
        final byte b2 = (byte)~b;
        final int n = b2 & 0x80;
        final int n2 = b2 >> 4 & 0x7;
        int n3 = Mulaw.exp_lut2[n2] + ((b2 & 0xF) << n2 + 3);
        if (n != 0) {
            n3 = -n3;
        }
        return (short)n3;
    }
    
    public static final byte linear2ulaw(short n) {
        final short n2 = (short)((short)(n >> 8) & 0x80);
        if (n2 != 0) {
            n = (short)(-n);
        }
        if (n > 32635) {
            n = 32635;
        }
        final short n3 = (short)(n + 132);
        final short n4 = Mulaw.exp_lut[n3 >> 7 & 0xFF];
        byte b = (byte)~(n2 | n4 << 4 | (short)(n3 >> n4 + 3 & 0xF));
        if (b == 0) {
            b = 2;
        }
        return b;
    }
    
    static {
        exp_lut = new short[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
        exp_lut2 = new short[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
    }
}
