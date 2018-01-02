// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

abstract class JPEGFixedSizeSegment extends JPEGSegment
{
    public JPEGFixedSizeSegment() {
        this.reference = new byte[this.fixedSize()];
        this.setSegmentMarker(this.signature());
    }
    
    public JPEGFixedSizeSegment(final byte[] array) {
        super(array);
    }
    
    public JPEGFixedSizeSegment(final LEDataInputStream leDataInputStream) {
        this.reference = new byte[this.fixedSize()];
        try {
            leDataInputStream.read(this.reference);
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
    }
    
    public abstract int fixedSize();
    
    public int getSegmentLength() {
        return this.fixedSize() - 2;
    }
    
    public void setSegmentLength(final int n) {
    }
}
