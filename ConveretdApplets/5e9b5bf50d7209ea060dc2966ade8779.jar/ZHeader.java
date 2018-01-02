// 
// Decompiled by Procyon v0.5.30
// 

abstract class ZHeader
{
    byte[] memory_image;
    static final int VERSION = 0;
    static final int FLAGS1 = 1;
    static final int RELEASE = 2;
    static final int HIGH_BASE = 4;
    static final int INITIAL_PC = 6;
    static final int DICTIONARY = 8;
    static final int OBJECT_TABLE = 10;
    static final int GLOBAL_TABLE = 12;
    static final int STATIC_BASE = 14;
    static final int FLAGS2 = 16;
    static final int SERIAL_NUMBER = 18;
    static final int ABBREV_TABLE = 24;
    static final int FILE_LENGTH = 26;
    static final int FILE_CHECKSUM = 28;
    static final int STD_REVISION = 50;
    
    public int version() {
        return this.memory_image[0];
    }
    
    public static int image_version(final byte[] memory_image) {
        return memory_image[0];
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
    
    public void set_transcripting(final boolean onoff) {
        if (onoff) {
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
    
    public void set_revision(final int major, final int minor) {
        this.memory_image[50] = (byte)major;
        this.memory_image[51] = (byte)minor;
    }
    
    public short release() {
        return (short)((this.memory_image[2] & 0xFF) << 8 | (this.memory_image[3] & 0xFF));
    }
    
    public short checksum() {
        return (short)((this.memory_image[28] & 0xFF) << 8 | (this.memory_image[29] & 0xFF));
    }
    
    public abstract int file_length();
}
