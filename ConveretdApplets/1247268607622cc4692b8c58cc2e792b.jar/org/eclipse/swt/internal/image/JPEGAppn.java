// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGAppn extends JPEGVariableSizeSegment
{
    public JPEGAppn(final byte[] array) {
        super(array);
    }
    
    public JPEGAppn(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public boolean verify() {
        final int segmentMarker = this.getSegmentMarker();
        return segmentMarker >= 65504 && segmentMarker <= 65519;
    }
}
