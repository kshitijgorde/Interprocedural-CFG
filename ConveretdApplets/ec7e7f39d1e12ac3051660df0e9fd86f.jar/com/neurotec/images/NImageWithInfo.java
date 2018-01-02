// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

public class NImageWithInfo
{
    private NImage image;
    private NImageInfo info;
    
    public NImageWithInfo(final NImage image, final NImageInfo info) {
        this.image = image;
        this.info = info;
    }
    
    public final NImage getImage() {
        return this.image;
    }
    
    public final NImageInfo getInfo() {
        return this.info;
    }
}
