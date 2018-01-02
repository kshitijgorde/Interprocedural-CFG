// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.state;

import russotto.zplet.zmachine.ZHeader;

class ZStateHeader extends ZHeader
{
    ZStateHeader(final byte[] memory_image) {
        super.memory_image = memory_image;
    }
    
    public int file_length() {
        return 0;
    }
}
