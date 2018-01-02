// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class Pointer
{
    public byte[] buffer;
    public int start;
    private static final byte[] emptyBuffer;
    
    private Pointer() {
        this.start = -1;
    }
    
    public static Pointer empty() {
        return create(Pointer.emptyBuffer, 0);
    }
    
    public static Pointer nullPointer() {
        return new Pointer();
    }
    
    public static Pointer create(final byte[] buf, final int start) {
        final Pointer p = new Pointer();
        p.buffer = buf;
        p.start = start;
        return p;
    }
    
    public static Pointer create(final String buf) {
        final Pointer p = new Pointer();
        try {
            p.buffer = buf.getBytes("ISO-8859-1");
        }
        catch (Exception ex) {}
        p.start = 0;
        return p;
    }
    
    public void memcpy(final byte[] toBuffer, final int toIndex, final int len) {
        System.arraycopy(this.buffer, this.start, toBuffer, toIndex, len);
    }
    
    public void memcpy(final Pointer to, final int len) {
        System.arraycopy(this.buffer, this.start, to.buffer, to.start, len);
    }
    
    public Pointer withStart(final int start) {
        return create(this.buffer, start);
    }
    
    static {
        emptyBuffer = new byte[0];
    }
}
