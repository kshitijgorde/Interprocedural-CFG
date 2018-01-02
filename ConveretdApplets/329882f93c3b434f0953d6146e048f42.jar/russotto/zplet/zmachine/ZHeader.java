// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine;

public abstract class ZHeader
{
    protected byte[] memory_image;
    protected static final int VERSION = 0;
    protected static final int FLAGS1 = 1;
    public static final int RELEASE = 2;
    protected static final int HIGH_BASE = 4;
    protected static final int INITIAL_PC = 6;
    protected static final int DICTIONARY = 8;
    protected static final int OBJECT_TABLE = 10;
    protected static final int GLOBAL_TABLE = 12;
    protected static final int STATIC_BASE = 14;
    protected static final int FLAGS2 = 16;
    public static final int SERIAL_NUMBER = 18;
    protected static final int ABBREV_TABLE = 24;
    protected static final int FILE_LENGTH = 26;
    public static final int FILE_CHECKSUM = 28;
    protected static final int STD_REVISION = 50;
    
    public int version() {
        return this.memory_image[0];
    }
    
    public static int image_version(final byte[] array) {
        return array[0];
    }
    
    public int high_base() {
        return (this.memory_image[4] << 8 & 0xFF00) | (this.memory_image[5] & 0xFF);
    }
    
    public int initial_pc() {
        return (this.memory_image[6] << 8 & 0xFF00) | (this.memory_image[7] & 0xFF);
    }
    
    public int dictionary() {
        return (this.memory_image[8] << 8 & 0xFF00) | (this.memory_image[9] & 0xFF);
    }
    
    public int object_table() {
        return (this.memory_image[10] << 8 & 0xFF00) | (this.memory_image[11] & 0xFF);
    }
    
    public int global_table() {
        return (this.memory_image[12] << 8 & 0xFF00) | (this.memory_image[13] & 0xFF);
    }
    
    public int static_base() {
        return (this.memory_image[14] << 8 & 0xFF00) | (this.memory_image[15] & 0xFF);
    }
    
    public boolean transcripting() {
        return (this.memory_image[17] & 0x1) == 0x1;
    }
    
    public void set_transcripting(final boolean b) {
        if (b) {
            final byte[] memory_image = this.memory_image;
            final int n = 17;
            memory_image[n] |= 0x1;
        }
        else {
            final byte[] memory_image2 = this.memory_image;
            final int n2 = 17;
            memory_image2[n2] &= (byte)254;
        }
    }
    
    public int abbrev_table() {
        return (this.memory_image[24] << 8 & 0xFF00) | (this.memory_image[25] & 0xFF);
    }
    
    public boolean force_fixed() {
        return (this.memory_image[17] & 0x2) == 0x2;
    }
    
    public void set_revision(final int n, final int n2) {
        this.memory_image[50] = (byte)n;
        this.memory_image[51] = (byte)n2;
    }
    
    public short release() {
        return (short)((this.memory_image[2] & 0xFF) << 8 | (this.memory_image[3] & 0xFF));
    }
    
    public short checksum() {
        return (short)((this.memory_image[28] & 0xFF) << 8 | (this.memory_image[29] & 0xFF));
    }
    
    public abstract int file_length();
}
