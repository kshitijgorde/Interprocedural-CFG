// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

class JPEGSegment
{
    public byte[] reference;
    
    JPEGSegment() {
    }
    
    public JPEGSegment(final byte[] reference) {
        this.reference = reference;
    }
    
    public int signature() {
        return 0;
    }
    
    public boolean verify() {
        return this.getSegmentMarker() == this.signature();
    }
    
    public int getSegmentMarker() {
        return (this.reference[0] & 0xFF) << 8 | (this.reference[1] & 0xFF);
    }
    
    public void setSegmentMarker(final int n) {
        this.reference[0] = (byte)((n & 0xFF00) >> 8);
        this.reference[1] = (byte)(n & 0xFF);
    }
    
    public int getSegmentLength() {
        return (this.reference[2] & 0xFF) << 8 | (this.reference[3] & 0xFF);
    }
    
    public void setSegmentLength(final int n) {
        this.reference[2] = (byte)((n & 0xFF00) >> 8);
        this.reference[3] = (byte)(n & 0xFF);
    }
    
    public boolean writeToStream(final LEDataOutputStream leDataOutputStream) {
        try {
            leDataOutputStream.write(this.reference);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
