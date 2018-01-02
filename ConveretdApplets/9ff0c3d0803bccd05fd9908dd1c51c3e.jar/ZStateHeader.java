// 
// Decompiled by Procyon v0.5.30
// 

class ZStateHeader extends ZHeader
{
    ZStateHeader(final byte[] memory_image) {
        super.memory_image = memory_image;
    }
    
    public int file_length() {
        return 0;
    }
}
