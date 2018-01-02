// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public final class Crc16
{
    private static short polynomial;
    private short crc;
    
    public Crc16() {
        this.crc = -1;
    }
    
    public void add_bits(final int n, final int n2) {
        int n3 = 1 << n2 - 1;
        do {
            if ((this.crc & 0x8000) == 0x0 ^ (n & n3) == 0x0) {
                this.crc <<= 1;
                this.crc ^= Crc16.polynomial;
            }
            else {
                this.crc <<= 1;
            }
        } while ((n3 >>>= 1) != 0);
    }
    
    public short checksum() {
        final short crc = this.crc;
        this.crc = -1;
        return crc;
    }
    
    static {
        Crc16.polynomial = -32763;
    }
}
