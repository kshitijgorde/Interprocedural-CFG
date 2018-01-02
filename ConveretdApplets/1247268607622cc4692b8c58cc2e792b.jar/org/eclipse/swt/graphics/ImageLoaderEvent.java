// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.SWTEventObject;

public class ImageLoaderEvent extends SWTEventObject
{
    public ImageData imageData;
    public int incrementCount;
    public boolean endOfImage;
    static final long serialVersionUID = 3257284738325558065L;
    
    public ImageLoaderEvent(final ImageLoader imageLoader, final ImageData imageData, final int incrementCount, final boolean endOfImage) {
        super(imageLoader);
        this.imageData = imageData;
        this.incrementCount = incrementCount;
        this.endOfImage = endOfImage;
    }
    
    public String toString() {
        return "ImageLoaderEvent {source=" + this.source + " imageData=" + this.imageData + " incrementCount=" + this.incrementCount + " endOfImage=" + this.endOfImage + "}";
    }
}
