// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGComment extends JPEGVariableSizeSegment
{
    public JPEGComment(final byte[] array) {
        super(array);
    }
    
    public JPEGComment(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public int signature() {
        return 65534;
    }
}
