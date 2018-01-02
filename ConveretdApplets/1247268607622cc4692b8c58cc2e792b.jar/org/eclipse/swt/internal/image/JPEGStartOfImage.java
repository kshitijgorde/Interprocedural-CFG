// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGStartOfImage extends JPEGFixedSizeSegment
{
    public JPEGStartOfImage() {
    }
    
    public JPEGStartOfImage(final byte[] array) {
        super(array);
    }
    
    public JPEGStartOfImage(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public int signature() {
        return 65496;
    }
    
    public int fixedSize() {
        return 2;
    }
}
