// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGRestartInterval extends JPEGFixedSizeSegment
{
    public JPEGRestartInterval(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public int signature() {
        return 65501;
    }
    
    public int getRestartInterval() {
        return (this.reference[4] & 0xFF) << 8 | (this.reference[5] & 0xFF);
    }
    
    public int fixedSize() {
        return 6;
    }
}
