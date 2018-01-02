// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine3;

import russotto.zplet.zmachine.ZHeader;

class ZHeader3 extends ZHeader
{
    static final int FILE_LENGTH_FACTOR = 2;
    
    ZHeader3(final byte[] memory_image) {
        super.memory_image = memory_image;
    }
    
    public boolean time_game() {
        return (super.memory_image[1] & 0x2) == 0x2;
    }
    
    public void set_status_unavailable(final boolean b) {
        if (b) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x10;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)239;
        }
    }
    
    public void set_splitting_available(final boolean b) {
        if (b) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x20;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)223;
        }
    }
    
    public void set_variable_default(final boolean b) {
        if (b) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x40;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)191;
        }
    }
    
    public int file_length() {
        return ((super.memory_image[26] & 0xFF) << 8 | (super.memory_image[27] & 0xFF)) * 2;
    }
}
