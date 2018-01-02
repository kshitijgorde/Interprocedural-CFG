// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGEndOfImage extends JPEGFixedSizeSegment
{
    public JPEGEndOfImage() {
    }
    
    public JPEGEndOfImage(final byte[] array) {
        super(array);
    }
    
    public int signature() {
        return 65497;
    }
    
    public int fixedSize() {
        return 2;
    }
}
